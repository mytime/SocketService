package com.chebao.socketservice.main;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * 处理socket请求
 */
public class ChatSocket extends Thread {

    Socket socket;

    //构造
    public ChatSocket(Socket s) {
        this.socket = s;
    }

    public void out(String out) {
        try {
            //向客户端发送的内容
            socket.getOutputStream().write(out.getBytes("UTF-8"));
            System.out.println("ChatSocket::"+ out);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        //1 不断的读取从客户端发送过来的数据
        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream(),"UTF-8"));

            String line = null;
            while ((line = br.readLine()) != null){

                //2 然后把读取到的信息发送给当前聊天室内所有人
                ChatManager.getChatManager().publish(ChatSocket.this,line);
            }

            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
