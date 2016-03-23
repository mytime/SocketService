package com.chebao.socketservice.main;

import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 客户端请求监听
 */
public class ServerListener extends Thread {
    @Override
    public void run() {
        try {
            /**
             * 端口范围：1-65535
             * 采用端口：12345
             */
            ServerSocket serverSocket = new ServerSocket(12345);

            //不断接受新的客户端请求
            while (true) {
                //会阻塞主线程
                //接受请求
                Socket socket = serverSocket.accept();
                //建立连接弹框
                JOptionPane.showMessageDialog(new JPanel(), "有客户端连接了12345端口");

                //将socket传递给新的线程执行
                ChatSocket cs = new ChatSocket(socket);
                cs.start();

                ChatManager.getChatManager().add(cs); //把ChatSocket(客户端线程)添加到ChatManager的Vector集合中
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
