package com.chebao.socketservice.main;

import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务器监听
 */
public class ServerListener extends Thread {
    @Override
    public void run() {
        try {
            /**
             * 端口范围：1-65535
             * 采用端口：12345
             *
             */
            // 实例化Socket并设置端口12345
            ServerSocket serverSocket = new ServerSocket(12345);

            //不断接受新的客户端请求
            while (true) {
                //接受客户端请求，返回一个socket对象
                Socket socket = serverSocket.accept();

                //弹框提示连接成功
                JOptionPane.showMessageDialog(new JPanel(), "有客户端连接了12345端口");
                System.out.println("有客户端连接了12345端口");

                //将socket对象传递给新的线程执行，返回ChatSocket对象
                ChatSocket cs = new ChatSocket(socket);
                cs.start();

                //把socket封装到Vector集合中
                ChatManager.getChatManager().add(cs);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
