package com.jmper.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * @author 郑和明
 * @version 1.0 (createTime:2018-03-20 17:20:16)
 */
public class NIOClient {
    private SocketChannel socketChannel;
    private Selector selector;
    private ByteBuffer receive = ByteBuffer.allocate(32);
    private ByteBuffer send = ByteBuffer.allocate(32);

    public NIOClient(int port) throws IOException {
        socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress(port));
//        selector = Selector.open();
//        socketChannel.register(selector, SelectionKey.OP_CONNECT);
    }


    public void sendData() throws IOException {
        Scanner scanner = new Scanner(System.in);
        if (socketChannel.finishConnect()) {
            String data = scanner.nextLine();
            send.clear();
            send.put(data.getBytes());
            send.flip();
            System.out.println("客户端发送数据---" + data);
            socketChannel.write(send);
            receive.clear();
            int read = socketChannel.read(receive);
            if (read > 0) {
                receive.flip();
                data = new String(receive.array(), 0, read);
                System.out.println("客户端接收数据---" + data);
            }


        }


    }


    public static void main(String[] args) throws IOException {
        new NIOClient(8999).sendData();
    }
}
