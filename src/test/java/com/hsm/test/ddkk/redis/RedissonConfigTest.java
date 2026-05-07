package com.hsm.test.ddkk.redis;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Unit tests for {@link RedissonConfig}.
 *
 * <p>测试目标：
 * <ul>
 *     <li>验证类与方法上的 Spring 注解（@Configuration、@Bean）</li>
 *     <li>验证 redissonClient() 方法签名（返回类型、可见性、非 static）</li>
 *     <li>验证调用 redissonClient() 能正确创建 RedissonClient，并使用单机模式 + 期望地址</li>
 *     <li>验证多次调用返回独立实例</li>
 *     <li>验证可通过 Spring 容器加载并按 Singleton 注册</li>
 *     <li>验证客户端可正常 shutdown（资源释放）</li>
 * </ul>
 *
 * <p>注意：Redisson 客户端创建时不会立即建立 TCP 连接，因此本类的测试无需真实 Redis 实例。
 */
public class RedissonConfigTest {

    private static final String EXPECTED_ADDRESS = "redis://127.0.0.1:6379";

    private RedissonConfig redissonConfig;
    private RedissonClient createdClient;

    @Before
    public void setUp() {
        redissonConfig = new RedissonConfig();
    }

    @After
    public void tearDown() {
        // 释放测试中创建的客户端，避免泄漏 Netty 线程
        if (createdClient != null && !createdClient.isShutdown()) {
            try {
                createdClient.shutdown();
            } catch (Exception ignored) {
                // 忽略关闭异常，仅做清理
            }
        }
    }

    // -------------------- 1. 注解元信息 --------------------

    @Test
    public void testClassHasConfigurationAnnotation() {
        assertTrue("RedissonConfig 类应被 @Configuration 标注",
                RedissonConfig.class.isAnnotationPresent(Configuration.class));
    }

    @Test
    public void testRedissonClientMethodHasBeanAnnotation() throws NoSuchMethodException {
        Method method = RedissonConfig.class.getDeclaredMethod("redissonClient");
        assertTrue("redissonClient() 方法应被 @Bean 标注",
                method.isAnnotationPresent(Bean.class));
    }

    // -------------------- 2. 方法签名 --------------------

    @Test
    public void testRedissonClientMethodSignature() throws NoSuchMethodException {
        Method method = RedissonConfig.class.getDeclaredMethod("redissonClient");

        assertEquals("方法返回类型应为 RedissonClient",
                RedissonClient.class, method.getReturnType());
        assertTrue("方法应为 public", Modifier.isPublic(method.getModifiers()));
        assertFalse("方法不应为 static", Modifier.isStatic(method.getModifiers()));
        assertEquals("方法不应有任何参数", 0, method.getParameterCount());
    }

    // -------------------- 3. 创建客户端的行为 --------------------

    @Test
    public void testRedissonClientReturnsNonNull() {
        createdClient = redissonConfig.redissonClient();
        assertNotNull("redissonClient() 应返回非空实例", createdClient);
    }

    @Test
    public void testRedissonClientIsRedissonInstance() {
        createdClient = redissonConfig.redissonClient();
        assertTrue("返回值应为 Redisson 实例",
                createdClient instanceof Redisson);
    }

    @Test
    public void testRedissonClientNotShutdownOnCreation() {
        createdClient = redissonConfig.redissonClient();
        assertFalse("新创建的客户端不应处于 shutdown 状态", createdClient.isShutdown());
        assertFalse("新创建的客户端不应处于 shutting down 状态", createdClient.isShuttingDown());
    }

    // -------------------- 4. 配置内容验证 --------------------

    /**
     * 通过反射读取 Redisson 内部持有的 Config，验证使用单机模式以及地址正确。
     */
    @Test
    public void testRedissonClientUsesExpectedSingleServerAddress() throws Exception {
        createdClient = redissonConfig.redissonClient();

        Config config = extractConfig(createdClient);
        assertNotNull("内部 Config 不应为空", config);

        SingleServerConfig singleServerConfig = extractSingleServerConfig(config);
        assertNotNull("应配置为单机模式（SingleServerConfig 非空）", singleServerConfig);

        assertEquals("单机模式地址应等于 " + EXPECTED_ADDRESS,
                EXPECTED_ADDRESS, singleServerConfig.getAddress());
    }

    @Test
    public void testEachInvocationCreatesNewClientInstance() {
        RedissonClient first = redissonConfig.redissonClient();
        RedissonClient second = redissonConfig.redissonClient();
        try {
            assertNotNull(first);
            assertNotNull(second);
            assertNotSame("每次调用应返回不同的 RedissonClient 实例", first, second);
        } finally {
            safeShutdown(first);
            safeShutdown(second);
        }
    }

    // -------------------- 5. Spring 容器集成 --------------------

    @Test
    public void testBeanRegisteredInSpringContext() {
        try (AnnotationConfigApplicationContext ctx =
                     new AnnotationConfigApplicationContext(RedissonConfig.class)) {

            RedissonClient bean = ctx.getBean(RedissonClient.class);
            assertNotNull("Spring 容器应能解析出 RedissonClient bean", bean);

            // 默认 @Bean 为 singleton，多次获取应为同一实例
            RedissonClient again = ctx.getBean(RedissonClient.class);
            assertSame("Spring 容器中默认应为 singleton", bean, again);
        }
        // try-with-resources 关闭容器时应触发 RedissonClient 的销毁，不抛异常
    }

    // -------------------- 6. 客户端 shutdown --------------------

    @Test
    public void testClientCanBeShutdownGracefully() {
        RedissonClient client = redissonConfig.redissonClient();
        try {
            client.shutdown();
            assertTrue("调用 shutdown 后客户端应处于 shutdown 状态",
                    client.isShutdown());
        } catch (Exception e) {
            fail("shutdown 不应抛出异常: " + e.getMessage());
        }
    }

    // -------------------- 工具方法 --------------------

    /**
     * 反射读取 {@link Redisson} 私有字段 {@code config} 来获取其持有的 {@link Config}。
     */
    private static Config extractConfig(RedissonClient client) throws Exception {
        Field configField = findField(client.getClass(), "config");
        assertNotNull("应能在 Redisson 类层次中找到 'config' 字段", configField);
        configField.setAccessible(true);
        Object value = configField.get(client);
        assertTrue("config 字段应为 Config 类型", value instanceof Config);
        return (Config) value;
    }

    /**
     * 反射读取 {@link Config} 私有字段 {@code singleServerConfig}，验证使用了单机模式。
     */
    private static SingleServerConfig extractSingleServerConfig(Config config) throws Exception {
        Field f = findField(config.getClass(), "singleServerConfig");
        assertNotNull("Config 中应存在 'singleServerConfig' 字段", f);
        f.setAccessible(true);
        Object value = f.get(config);
        if (value == null) {
            return null;
        }
        assertTrue("singleServerConfig 应为 SingleServerConfig 类型",
                value instanceof SingleServerConfig);
        return (SingleServerConfig) value;
    }

    /**
     * 在类层次结构中查找指定名称的字段。
     */
    private static Field findField(Class<?> clazz, String name) {
        Class<?> current = clazz;
        while (current != null && current != Object.class) {
            try {
                return current.getDeclaredField(name);
            } catch (NoSuchFieldException ignored) {
                current = current.getSuperclass();
            }
        }
        return null;
    }

    private static void safeShutdown(RedissonClient client) {
        if (client != null && !client.isShutdown()) {
            try {
                client.shutdown();
            } catch (Exception ignored) {
                // ignore
            }
        }
    }
}
