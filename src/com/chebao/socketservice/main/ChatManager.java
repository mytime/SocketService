package com.chebao.socketservice.main;

import java.util.Vector;

/**
 * 统一管理  new ChatSocket线程，实现不同客户端的相互通信
 * 一个Socket服务器只能有一个ChatManager，所以需要实现单例模式
 */
public class ChatManager {

    /**
     * 设计单例模式步骤：
     * 1.私有化构造方法
     * 2 私有化当前类的实例
     * 3 给外部提供一个可以调用 ChatManager 实例的静态方法
     */

    //1 私有化构造方法
    private ChatManager() {
    }

    ;

    //2 私有化当前类的实例
    private static final ChatManager cm = new ChatManager();

    //3 给外部提供一个可以调用 ChatManager 实例的静态方法
    public static ChatManager getChatManager() {
        return cm;
    }

    //4 外部调用方法
    //          ChatManager.getChatManager.add();
    //          ChatManager.getChatManager.publish();


    /**
     * 封装客户端的集合
     * Vector(载体)与ArrayList一样，也是通过数组实现的，不同的是它支持线程的同步，
     * 即某一时刻只有一个线程能够写Vector，避免多线程同时写而引起的不一致性，
     * 但实现同步需要很高的花费，因此，访问它比访问ArrayList慢。
     */

    Vector<ChatSocket> vector = new Vector<ChatSocket>();

    // 向Vector集合添加ChatSocket（客户端线程）
    public void add(ChatSocket cs) {
        vector.add(cs);
    }

    // 所有的ChatSocket（客户端线程） 都可以调用这个方法进行广播
    //arg1: 客户端线程，arg2：发送内容
    public void publish(ChatSocket cs, String out) {
        System.out.println("ChatManager out::" + out);
        for (ChatSocket chatSocket : vector) {
            if (!cs.equals(chatSocket)) { //发送客户端不接收自己的内容
                chatSocket.out(out);
            }
        }

//        for (int i = 0; i < vector.size() ; i++) {
//            ChatSocket chatSocket = vector.get(i);
//            //如果当前对象不是自己，就对当前对象发送消息
//            if (!cs.equals(chatSocket)){
//                chatSocket.out(out);
//            }
//        }


    }

}
