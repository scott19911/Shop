package com.epam.verizhenko_andrii.electronicsStore.server.custom;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Client application with single line command entry
 */
public class CustomClient extends JFrame implements Runnable {
    /**
     * address for server connection
     */
    static private final String address = "127.0.0.1";
    static private final int PORT = 3000;
    static private Socket clientSocket;
    static private ObjectOutputStream output;
    static private ObjectInputStream input;

    /**
     * Initializes a working window with a command input field and a send request button
     */
    public CustomClient() {
        super();
        setLayout(new FlowLayout());
        setTitle("Shop client");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        JTextArea textArea = new JTextArea("commands:\n get count \n " +
                "get item=item_brand");
        final JTextField t1 = new JTextField(10);
        final JButton b1 = new JButton("Send");
        b1.addActionListener(arg0 -> {
            if (arg0.getSource() == b1) {
                sendDate(t1.getText());
            }
        });
        add(t1);
        add(b1);
        add(new JScrollPane(textArea));
    }

    public static void main(String[] args) {
        new Thread(new CustomClient()).start();
    }

    /**
     * Sends data to the backend server
     *
     * @param obj - request command
     */
    static private void sendDate(Object obj) {
        try {
            output.flush();
            output.writeObject(obj);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                clientSocket = new Socket(InetAddress.getByName(address), PORT);
                output = new ObjectOutputStream(clientSocket.getOutputStream());
                input = new ObjectInputStream(clientSocket.getInputStream());
                JOptionPane.showMessageDialog(null, input.readObject());
            }
        } catch (IOException | HeadlessException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "server close");
        } finally {
            try {
                output.close();
                input.close();
                clientSocket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
