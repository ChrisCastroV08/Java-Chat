package com.cris.chat;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;


public class Client extends JFrame{
    private JPanel clientPanel;
    private JTextArea textArea2;
    private JButton searchButton;
    private JTextField portField2;
    private JTextField messageBox;
    private JButton sendButton;
    public static int b;
    public static boolean flag = Boolean.FALSE;

    public Client(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(clientPanel);
        this.pack();
        sendButton.setEnabled(false);
        textArea2.setEditable(false);


        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String i = messageBox.getText();
                if (i.equals("")){
                    return;
                } else{
                    messageBox.setText("");
                    textArea2.append("Client: " + i);
                    textArea2.append("\n");
                    try {
                        Messages();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }


                }
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Client.b = Integer.parseInt(portField2.getText());
                }
                catch (Exception error){
                    System.err.println("Cannot use Strings. Error code: " + error.getMessage());
                    return;
                }

                //JOptionPane.showMessageDialog(null, "Hosted port: " + Main.a);
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

    protected static void Messages () throws IOException {
        if (Client.flag == Boolean.TRUE){
            System.out.println("Searching for Host...");
            Socket client = new Socket("127.0.0.1", b);
            System.out.println("Connected");
            PrintStream pr = new PrintStream(client.getOutputStream());
        } else {
            return;
        }


    }
    public static void main(String[] args) throws IOException {

        if (Client.flag == Boolean.TRUE){
            Messages();

        } else{
            JFrame window = new Client("Client");
            window.setSize(510,400);
            window.setVisible(true);
        }


    }
}
