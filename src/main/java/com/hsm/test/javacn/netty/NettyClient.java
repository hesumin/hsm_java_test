package com.hsm.test.javacn.netty;

/**
 * @projectName(��Ŀ����): hsm_java_test
 * @package(������): com.hsm.test.javacn.netty
 * @className:(������): NettyClient
 * @author: Hesumin
 * @createDate: 2025/04/30 16:36
 * @description: Netty ��ʾ�� �ͻ��˴���
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
        // ����EventLoopGroup���൱���̳߳�
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            // �����ͻ�����������
            Bootstrap bootstrap = new Bootstrap();

            // ������ز���
            bootstrap.group(group) // �����߳���
                    .channel(NioSocketChannel.class) // ���ÿͻ���ͨ��ʵ������
                    .handler(new ChannelInitializer<SocketChannel>() { // ���ô�����
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            // ��ܵ����봦����
                            ch.pipeline().addLast(new StringDecoder());
                            ch.pipeline().addLast(new StringEncoder());
                            // �Զ���Ĵ�����
                            // ch.pipeline().addLast(new ClientHandler());
                        }
                    });

            System.out.println("�ͻ��� is ready...");

            // �����첽���Ӳ���
            ChannelFuture future = bootstrap.connect("127.0.0.1", 6668).sync();

            // ������Ϣ
            future.channel().writeAndFlush("-------------------11111111111111111111111111111");

            // �Թر�ͨ�����м���
            future.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully(); // ���Źر��߳���
        }
    }
}
