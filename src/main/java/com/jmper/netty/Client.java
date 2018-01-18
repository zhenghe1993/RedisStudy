package com.jmper.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.timeout.ReadTimeoutHandler;

/**
 * @author 郑和明
 * @version 1.0 (createTime:2018-01-18 11:07:23)
 */
public class Client {

    public static void main(String[] args) throws InterruptedException {


        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group);

        bootstrap.channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
//                        ByteBuf byteBuf = Unpooled.copiedBuffer("$_".getBytes());
//                        ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, byteBuf));
                        ch.pipeline().addLast(new FixedLengthFrameDecoder(5));
                        ch.pipeline().addLast(new StringDecoder());
                        ch.pipeline().addLast(new ReadTimeoutHandler(5));
                        ch.pipeline().addLast(new ClientHandler());
                    }
                });

        ChannelFuture future = bootstrap.connect("127.0.0.1", 8765).sync();

        future.channel().writeAndFlush(Unpooled.copiedBuffer("hello netty$_hello$_".getBytes()));
        Thread.sleep(1000);
        future.channel().writeAndFlush(Unpooled.copiedBuffer("hello netty$_ffff".getBytes()));
        Thread.sleep(1000);
        future.channel().writeAndFlush(Unpooled.copiedBuffer("hello netty$_gggg".getBytes()));


        future.channel().closeFuture().sync();

        group.shutdownGracefully();
    }
}
