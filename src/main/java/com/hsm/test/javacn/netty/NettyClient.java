package com.hsm.test.javacn.netty;

/**
 * @projectName(项目名称): hsm_java_test
 * @package(包名称): com.hsm.test.javacn.netty
 * @className:(类名称): NettyClient
 * @author: Hesumin
 * @createDate: 2025/04/30 16:36
 * @description: Netty 简单示例 客户端代码
 */

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class NettyClient {

    public static void main(String[] args) throws Exception {
        // 创建EventLoopGroup，相当于线程池
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            // 创建客户端启动对象
            Bootstrap bootstrap = new Bootstrap();

            // 设置相关参数
            bootstrap.group(group) // 设置线程组
                    .channel(NioSocketChannel.class) // 设置客户端通道实现类型
                    .handler(new ChannelInitializer<SocketChannel>() { // 设置处理器
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            // 向管道加入处理器
                            ch.pipeline().addLast(new StringDecoder());
                            ch.pipeline().addLast(new StringEncoder());
                            // 自定义的处理器
                            // ch.pipeline().addLast(new ClientHandler());
                        }
                    });

            System.out.println("客户端 is ready...");

            // 发起异步连接操作
            ChannelFuture future = bootstrap.connect("127.0.0.1", 6668).sync();

            // 发送消息
            future.channel().writeAndFlush("-------------------11111111111111111111111111111");

            // 对关闭通道进行监听
            future.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully(); // 优雅关闭线程组
        }
    }
}
