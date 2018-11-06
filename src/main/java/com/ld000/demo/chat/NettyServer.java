package com.ld000.demo.chat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * @author lidong9144@163.com 2018/11/6
 */
public class NettyServer {

    public static void main(String[] args) {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap
                .group(bossGroup, workerGroup)  // 线程模型
                .channel(NioServerSocketChannel.class)  // IO 模型
                .childHandler(new ChannelInitializer<NioSocketChannel>() {  // 读写处理逻辑
                    @Override
                    protected void initChannel(NioSocketChannel channel) throws Exception {
                        channel.pipeline().addLast(new FirstServerHandler());
                    }
                });

        // 启动过程逻辑
//        serverBootstrap.handler(new ChannelInitializer<NioServerSocketChannel>() {
//            protected void initChannel(NioServerSocketChannel ch) {
//                System.out.println("服务端启动中");
//            }
//        });

        // 设置自定义属性
//        serverBootstrap.attr(AttributeKey.newInstance("serverName"), "nettyServer");
//        serverBootstrap.childAttr(AttributeKey.newInstance("clientKey"), "clientValue");

//        serverBootstrap
//                .childOption(ChannelOption.SO_KEEPALIVE, true)
//                .childOption(ChannelOption.TCP_NODELAY, true);

//        serverBootstrap.option(ChannelOption.SO_BACKLOG, 1024);

        serverBootstrap.bind(8000).addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                if (future.isSuccess()) {
                    System.out.println("端口绑定成功");
                } else {
                    System.out.println("端口绑定失败");
                }
            }
        });
    }

}
