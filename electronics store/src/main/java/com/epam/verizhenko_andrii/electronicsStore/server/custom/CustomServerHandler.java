package com.epam.verizhenko_andrii.electronicsStore.server.custom;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * handles custom client requests from sockets
 * if request unsupported return error message*
 */
public class CustomServerHandler extends Thread {
    private final Socket clientSocket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    /**
     * Based on the client socket, we get the input and output stream
     */
    private void init(){
        try {
            in = new ObjectInputStream(clientSocket.getInputStream());
            out = new ObjectOutputStream(clientSocket.getOutputStream());
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

    }
    public CustomServerHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
        init();
        start();
    }

    @Override
    public void run() {
        CustomServerCommands commands = new CustomServerCommands();
        String request;
        String response;
        try {
            request = (String) in.readObject();
            if (request.contains("get count")) {
                response = String.valueOf(commands.getCount());
            } else if (request.contains("get item=")) {
                response = commands.getItem(request.substring(request.indexOf('=') + 1));
            } else {
                response = "Unsupported command";
            }
            out.writeObject(response);
            out.flush();
        } catch (IOException ex) {
            try {
                if (!clientSocket.isClosed()) {
                    clientSocket.close();
                    in.close();
                    out.close();
                }
            } catch (IOException exception) {
                throw new RuntimeException(exception);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (!clientSocket.isClosed()) {
                    clientSocket.close();
                    in.close();
                    out.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}


