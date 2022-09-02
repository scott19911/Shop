package com.epam.verizhenko_andrii.electronicsStore.server.http;

import java.awt.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

/**
 * The class is waiting for a client socket connection on port 8080
 *then passes to the command handler
 */
public class HttpServer implements Runnable {
    public static LinkedList<HttpHandler> serverList = new LinkedList<>();
    private static ServerSocket server;
    private static boolean exit;
    private final static int PORT = 8080;
    /**
     * Method for stopping the server and its handlers
     */
    public static void closeServer() {
        exit = true;
        try {
            for (HttpHandler socket : serverList) {
                socket.interrupt();
            }
            server.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void run() {
        try {
            server = new ServerSocket(PORT);
            while (!exit) {
                Socket clientSocket = server.accept();
                serverList.add(new HttpHandler(clientSocket));
            }
        } catch (IOException | HeadlessException ex) {
            closeServer();
        }
    }
}
