package com.tavisca.chat;

import javax.websocket.Session;
import java.io.IOException;

public class MyRunnable implements Runnable {

    ChatHelper chatHelper;
    public MyRunnable(){
        chatHelper = new ChatHelper();
    }

    @Override
    public void run() {
        while(true){
            for(Session session:chatHelper.sessionMap.values()){
                try {
                    session.getBasicRemote().sendText("hello "+session.getId());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
