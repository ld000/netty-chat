package com.ld000.demo.chat;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * @author lidong9144@163.com 2018/11/6
 */
public class FirstServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;

        System.out.println(new Date() + " 服务端读到数据：" + byteBuf.toString(Charset.forName("utf-8")));

        System.out.println(new Date() + ": 服务端写出数据");
        ByteBuf buf = getByteBuf(ctx);
        ctx.writeAndFlush(buf);
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
        byte[] bytes = "服务端返回".getBytes(Charset.forName("utf-8"));

        ByteBuf byteBuf = ctx.alloc().buffer();

        byteBuf.writeBytes(bytes);

        return byteBuf;
    }

}
