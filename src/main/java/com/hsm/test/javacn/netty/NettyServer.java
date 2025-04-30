package com.hsm.test.javacn.netty;

/**
 * @projectName(项目名称): hsm_java_test
 * @package(包名称): com.hsm.test.javacn.netty
 * @className:(类名称): NettyServer
 * @author: Hesumin
 * @createDate: 2025/04/30 16:32
 * @description: Netty 简单示例 服务器端
 */

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class NettyServer {

    public static void main(String[] args) throws Exception {
        // 创建BossGroup和WorkerGroup，它们都是EventLoopGroup的实现
        // BossGroup负责接收进来的连接
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        // WorkerGroup负责处理已经被接收的连接
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            // 创建服务器端的启动对象，配置参数
            ServerBootstrap bootstrap = new ServerBootstrap();

            // 设置两个线程组
            bootstrap.group(bossGroup, workerGroup)
                    // 设置服务器通道实现类型
                    .channel(NioServerSocketChannel.class)
                    // 设置通道初始化器，主要用来配置管道中的处理器
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            // 向管道加入处理器
                            // 解码器：ByteBuf -> String
                            ch.pipeline().addLast(new StringDecoder());
                            // 编码器：String -> ByteBuf
                            ch.pipeline().addLast(new StringEncoder());

                            // 自定义的处理器
                            //ch.pipeline().addLast(new ServerHandler());
                        }
                    });

            System.out.println("服务器 is ready...");

            // 绑定一个端口并且同步，生成了一个ChannelFuture对象
            ChannelFuture cf = bootstrap.bind(6668).sync();

            // 对关闭通道进行监听
            cf.channel().closeFuture().sync();
        } finally {
            // 优雅关闭线程组
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}