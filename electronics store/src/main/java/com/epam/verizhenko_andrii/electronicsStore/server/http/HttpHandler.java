package com.epam.verizhenko_andrii.electronicsStore.server.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class HttpHandler extends Thread {
    private final Socket clientSocket;
    private final InputStream inputStream;
    private final OutputStream outputStream;
    private String answer;

    public HttpHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
        try {
            inputStream = clientSocket.getInputStream();
            outputStream = clientSocket.getOutputStream();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        start();
    }

    @Override
    public void run() {
        answer = readInputHeaders();
        writeResponse(answer);
    }

    /**
     * Sends the received response to the user and close resources
     *
     * @param answer - response
     */
    private void writeResponse(String answer) {
        String response = "HTTP/1.1 200 OK\r\n" +
                "Server: YarServer/2009-09-09\r\n" +
                "Content-Type: application/json;charset=UTF-8\r\n" +
                "Content-Length: " + answer.length() + "\r\n" +
                "Connection: close\r\n\r\n";
        String result = response + answer;
        try {
            outputStream.write(result.getBytes());
            outputStream.flush();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } finally {
            try {
                if (!clientSocket.isClosed()) {
                    clientSocket.close();
                    inputStream.close();
                    outputStream.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * read request and execute command
     */
    private String readInputHeaders() {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder requestBuilder = new StringBuilder();
        String line;
        try {
            while ((line = br.readLine()) != null && !line.isEmpty()) {
                requestBuilder.append(line + "\r\n");
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        String request = requestBuilder.toString();
        String[] requestsLines = request.split("\r\n");
        String[] requestLine = requestsLines[0].split(" ");
        String path = requestLine[1];
        CommandsFactory commandsFactory = new CommandsFactory(path);
        Commands commands = commandsFactory.getCommand();
        if (commands == null) {
            return "Unsupported command";
        }
        return commands.execute();
    }
}
