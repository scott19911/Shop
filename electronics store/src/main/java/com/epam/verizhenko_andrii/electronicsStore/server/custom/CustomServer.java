package com.epam.verizhenko_andrii.electronicsStore.server.custom;

import java.awt.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;

public class CustomServer implements Runnable {
    public static LinkedList<CustomServerHandler> serverList = new LinkedList<>();
    private static ServerSocket server;
    private static Socket clientSocket;
    private static final AtomicBoolean running = new AtomicBoolean(true);
    private final int port = 3000;

    /**
     * Method for stopping the server and its handlers
     */
    public static void closeServer() {
        running.set(false);
        try {
            for (CustomServerHandler socket :
                    serverList) {
                socket.interrupt();
            }
            server.close();
            if (clientSocket != null) {
                clientSocket.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        try {
            server = new ServerSocket(port, 10);
            while (running.get()) {
                clientSocket = server.accept();
                serverList.add(new CustomServerHandler(clientSocket));
            }
        } catch (IOException | HeadlessException ex) {
            try {
                if (server != null) {
                    server.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
