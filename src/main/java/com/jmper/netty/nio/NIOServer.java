package com.jmper.netty.nio;

import io.netty.buffer.ByteBuf;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @author 郑和明
 * @version 1.0 (createTime:2018-03-20 16:39:22)
 */
public class NIOServer {

    private ServerSocketChannel serverSocketChannel;
    private Selector selector;
    private ByteBuffer receive = ByteBuffer.allocate(32);
    private ByteBuffer send = ByteBuffer.allocate(32);

    public NIOServer(int port) throws IOException {
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        selector = Selector.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    public void listener() throws IOException {

        while (true) {

            int i = selector.select();
            if (i == 0) {
                continue;
            }

            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                process(key);
                iterator.remove();
            }


        }


    }

    private void process(SelectionKey key) throws IOException {

        if (key.isAcceptable()) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);
        } else if (key.isReadable()) {
            SocketChannel socketChannel = (SocketChannel) key.channel();
            receive.clear();
            int read = socketChannel.read(receive);
            if (read > 0) {
                receive.flip();
                String res = new String(receive.array(), 0, read);
                System.out.println("服务器接收数据----" + res);
                socketChannel.register(selector, SelectionKey.OP_WRITE);
            }
        } else if (key.isWritable()) {
            SocketChannel socketChannel = (SocketChannel) key.channel();
            Scanner scanner = new Scanner(System.in);
            String data = scanner.nextLine();
            System.out.println("服务器发送数据----" + data);
            send.clear();
            send.put(data.getBytes());
            send.flip();
            socketChannel.write(send);
        }
    }


    public static void main(String[] args) throws IOException {
        new NIOServer(8999).listener();
    }
}
