package com.server.main;

import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class listener extends Thread {

    @Override
    public void run() {
        try {
            ServerSocket serverSocket =new ServerSocket(10086);
            while(true) {
                Socket socket = serverSocket.accept();
                JOptionPane.showMessageDialog(null,"有客户端连接到本机10086接口");
                chatsocket cs= new chatsocket(socket);
                        cs.start();
                ChatManager.getChatManager().add(cs);
            }
            //block
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
