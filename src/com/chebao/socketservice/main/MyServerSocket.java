package com.chebao.socketservice.main;

/**
 * 聊天室程序
 * 启动数个终端 cmd -> doc -> telnet localhost 12345  链接服务器
 * 发送消息
 */
public class MyServerSocket {
    public static void main(String[] args){

        new ServerListener().start();

    }
}
