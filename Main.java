package com.cris.chat;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;

public class Main extends JFrame{
    private JTextArea textArea;
    private JPanel mainPanel;
    private JButton sendButton;
    private JTextField messageBox;
    private JButton uploadButton;
    private JTextField portField;
    public static int a;
    public static boolean flag = Boolean.FALSE;

    public Main(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();


        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String i = messageBox.getText();
                if (i.equals("")){
                    return;
                } else{
                    messageBox.setText("");
                    textArea.append("Server: " + i);
                    textArea.append("\n");
                }
            }
        });
        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Main.a = Integer.parseInt(portField.getText());
                }
                catch (Exception error){
                    System.err.println("Cannot use Strings. Error code: " + error.getMessage());
                    return;
                }

                JOptionPane.showMessageDialog(null, "Hosted port: " + Main.a);
                flag = Boolean.TRUE;
                String[] met = null;
                try {
                    main(met);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
    }
    public static void main(String[] args) throws IOException {



        if (Main.flag == Boolean.TRUE){

            System.out.println("Waiting for client...");
            ServerSocket server = new ServerSocket(a);
            Socket client = server.accept();
            System.out.println("Client Connected");
        } else{
            JFrame window = new Main("Extraclase");
            window.setSize(510,400);
            window.setVisible(true);
        }


    }
}
