package com.hsm.test.javacn.netty;

/**
 * @projectName(��Ŀ����): hsm_java_test
 * @package(������): com.hsm.test.javacn.netty
 * @className:(������): NettyServer
 * @author: Hesumin
 * @createDate: 2025/04/30 16:32
 * @description: Netty ��ʾ�� ��������
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
        // ����BossGroup��WorkerGroup�����Ƕ���EventLoopGroup��ʵ��
        // BossGroup������ս���������
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        // WorkerGroup�������Ѿ������յ�����
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            // �����������˵������������ò���
            ServerBootstrap bootstrap = new ServerBootstrap();

            // ���������߳���
            bootstrap.group(bossGroup, workerGroup)
                    // ���÷�����ͨ��ʵ������
                    .channel(NioServerSocketChannel.class)
                    // ����ͨ����ʼ��������Ҫ�������ùܵ��еĴ�����
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            // ��ܵ����봦����
                            // ��������ByteBuf -> String
                            ch.pipeline().addLast(new StringDecoder());
                            // ��������String -> ByteBuf
                            ch.pipeline().addLast(new StringEncoder());

                            // �Զ���Ĵ�����
                            //ch.pipeline().addLast(new ServerHandler());
                        }
                    });

            System.out.println("������ is ready...");

            // ��һ���˿ڲ���ͬ����������һ��ChannelFuture����
            ChannelFuture cf = bootstrap.bind(6668).sync();

            // �Թر�ͨ�����м���
            cf.channel().closeFuture().sync();
        } finally {
            // ���Źر��߳���
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}