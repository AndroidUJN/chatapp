package com.server.main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class socket {
    public static void main(String[] args) {
    new listener().start();
    }
}
