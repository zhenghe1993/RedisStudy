package com.jmper.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.timeout.ReadTimeoutHandler;

/**
 * @author 郑和明
 * @version 1.0 (createTime:2018-01-18 10:19:18)
 */
public class Server {

    public static void main(String[] args) throws InterruptedException {
        //线程组，负责处理服务器接收客户端连接
        EventLoopGroup pGroup = new NioEventLoopGroup();
        //线程组，网络通信
        EventLoopGroup cGroup = new NioEventLoopGroup();

        //辅助工具类
        ServerBootstrap bootstrap = new ServerBootstrap();
        //绑定两个线程组
        bootstrap.group(pGroup, cGroup);
        //指定NIO模式
        bootstrap.channel(NioServerSocketChannel.class);
        //TCP缓冲区大小
        bootstrap.option(ChannelOption.SO_BACKLOG, 1024)
                //设置发送缓冲区大小
                .option(ChannelOption.SO_SNDBUF, 32 * 1024)
                //设置接收缓冲区大小
                .option(ChannelOption.SO_RCVBUF, 32 * 1024)
                //保持连接
                .option(ChannelOption.SO_KEEPALIVE, true)
                //
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
//                        ByteBuf byteBuf = Unpooled.copiedBuffer("$_".getBytes());
//                        ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, byteBuf));
                        ch.pipeline().addLast(new FixedLengthFrameDecoder(5));
                        ch.pipeline().addLast(new StringDecoder());
                        ch.pipeline().addLast(new ReadTimeoutHandler(5));
                        ch.pipeline().addLast(new ServerHandler());
                    }
                });

        //进行绑定
        ChannelFuture future = bootstrap.bind(8765).sync();
        //等待关闭
        future.channel().closeFuture().sync();

        //关闭线程组
        pGroup.shutdownGracefully();
        cGroup.shutdownGracefully();

    }
}
