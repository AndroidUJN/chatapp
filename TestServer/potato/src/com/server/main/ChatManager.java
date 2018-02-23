package com.server.main;

import java.util.Vector;

public class ChatManager {
    private ChatManager() {
    }

    private static final ChatManager cm = new ChatManager();

    public static ChatManager getChatManager() {
        return cm;
    }

    Vector<chatsocket> vector = new Vector<chatsocket>();

    public void add(chatsocket cs) {
        vector.add(cs);
    }

    public void publish(chatsocket cs, String out) {
        for (int i = 0; i < vector.size(); i++) {
            chatsocket csChatsocket = vector.get(i);
            if (cs.equals(csChatsocket)) {
                csChatsocket.out(out);

            }
        }
    }
}
