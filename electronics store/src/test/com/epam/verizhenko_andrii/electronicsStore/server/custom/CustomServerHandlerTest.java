package com.epam.verizhenko_andrii.electronicsStore.server.custom;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CustomServerHandlerTest {
    CustomServerCommands commands = mock(CustomServerCommands.class);

    @BeforeEach
    void init() {

        when(commands.getCount()).thenReturn(5);
    }

    @Test
    public void shouldSocketWrite_WhenGetRequestCount() throws IOException, ClassNotFoundException {

        CustomServer customServer = new CustomServer();
        Thread thread = new Thread(customServer);
        thread.start();
        String request = "get count";
        Socket clientSocket = new Socket(InetAddress.getByName("127.0.0.1"), 3000);
        ObjectOutputStream output = new ObjectOutputStream(clientSocket.getOutputStream());
        ObjectInputStream input = new ObjectInputStream(clientSocket.getInputStream());
        output.flush();
        output.writeObject(request);
        assertTrue(clientSocket.isConnected());
        assertEquals("0", input.readObject());
        CustomServer.closeServer();
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assertFalse(thread.isAlive());
    }


}