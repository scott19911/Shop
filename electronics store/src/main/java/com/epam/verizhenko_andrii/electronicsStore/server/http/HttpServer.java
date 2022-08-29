package com.epam.verizhenko_andrii.electronicsStore.server.http;

import java.awt.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class HttpServer implements Runnable {
    public static LinkedList<HttpHandler> serverList = new LinkedList<>();
    private static ServerSocket server;
    private static boolean exit;

    /**
     * Method for stopping the server and its handlers
     */
    public static void closeServer() {
        exit = true;
        try {
            for (HttpHandler socket :
                    serverList) {
                socket.interrupt();
            }
            server.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void run() {
        try {
            server = new ServerSocket(8080);
            while (!exit) {
                Socket clientSocket = server.accept();
                serverList.add(new HttpHandler(clientSocket));
            }
        } catch (IOException | HeadlessException ex) {
            closeServer();
        }
    }
}
