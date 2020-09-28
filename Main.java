package com.cris.chat;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;

public class Main extends JFrame {
    private JTextArea textArea1;
    private JPanel mainPanel;
    private JButton sendButton;
    private JTextField messageBox;
    private JButton uploadButton;
    private JTextField portField;
    public static int a;
    public static boolean flag = Boolean.FALSE;
    static Socket client;
    static ServerSocket server;
    static DataOutputStream dout;
    static DataInputStream din;

    public Main(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        sendButton.setEnabled(false);
        textArea1.setEditable(false);


        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                String i = messageBox.getText();
                if (i.equals("")) {
                    return;
                } else {
                    textArea1.append("Server: " + i);
                    textArea1.append("\n");

                    try {
                        String msgout = "";
                        msgout = messageBox.getText().trim();
                        dout.writeUTF(msgout);

                    } catch (Exception e) {

                    }
                }
            }
        });
        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    Main.a = Integer.parseInt(portField.getText());
                } catch (Exception error) {
                    System.err.println("Cannot use Strings. Error code: " + error.getMessage());
                    return;
                }

                JOptionPane.showMessageDialog(null, "Hosted port: " + Main.a);
                flag = Boolean.TRUE;
                sendButton.setEnabled(true);
                String[] met = null;
                try {
                    main(met);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
    }

    public void Messages () throws IOException {
        String msgin = "";

        System.out.println("Waiting for client...");
        server = new ServerSocket(a);
        client = server.accept();
        System.out.println("Client Connected");
        din = new DataInputStream(client.getInputStream());
        dout = new DataOutputStream(client.getOutputStream());
        while (!msgin.equals("exit")) {
            msgin = din.readUTF();
            textArea1.setText(textArea1.getText() + "\n" + msgin);

        }
    }

    public static void main(String[] args) throws IOException {
        JTextArea textArea1 = new JTextArea();


        if (Main.flag == Boolean.TRUE) {
            String msgin = "";
            try{
                System.out.println("Waiting for client...");
                server = new ServerSocket(a);
                client = server.accept();
                System.out.println("Client Connected");
                din = new DataInputStream(client.getInputStream());
                dout = new DataOutputStream(client.getOutputStream());
                msgin = din.readUTF();
                textArea1.setText(textArea1.getText() + "\n" + msgin);


            } catch (Exception e){

            }

        } else {
            JFrame window = new Main("Main");
            window.setSize(510, 400);
            window.setVisible(true);
        }


    }
}


