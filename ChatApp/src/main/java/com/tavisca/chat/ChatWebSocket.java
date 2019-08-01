package com.tavisca.chat;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
//http://localhost:8080/ChatApp/endpoint
@ServerEndpoint("/endpoint")
public class ChatWebSocket {
    private ChatHelper chatHelper;
    private static MyRunnable myRunnable;
    private static Thread thread;
    public ChatWebSocket(){
        this.chatHelper = new ChatHelper();

    }

    @OnOpen
    public void onOpen(Session session){

        if(thread==null){
            myRunnable = new MyRunnable();
            thread = new Thread(myRunnable);
            thread.start();
        }

        System.out.println("Open connection: "+session.getId());
        chatHelper.sessionMap.put(session.getId(),session);
    }


    @OnClose
    public void onClose(Session session){
        System.out.println("close connection: "+session.getId());
        chatHelper.sessionMap.remove(session.getId());
    }

    @OnMessage
    public void onMessage(String message,Session session){
        System.out.println("Received Message from: "+session.getId()+"\n Message is "+message);
        chatHelper.sendMessage(message,session.getId());
    }

}
