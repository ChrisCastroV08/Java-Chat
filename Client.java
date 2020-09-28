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
    static Socket client;
    static DataOutputStream dout;
    static DataInputStream din;

    public Client(String title) throws IOException {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(clientPanel);
        this.pack();
        sendButton.setEnabled(false);
        textArea2.setEditable(false);


        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                String i = messageBox.getText();
                if (i.equals("")){
                    return;
                } else{
                    textArea2.append("Client: " + i);
                    textArea2.append("\n");
                    try {
                        String msgout = "";
                        msgout = messageBox.getText().trim();
                        dout.writeUTF(msgout);

                    } catch (Exception e) {

                    }

                }
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
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

    public static void main(String[] args) throws IOException {

        JTextArea textArea2 = new JTextArea();

        if (Client.flag == Boolean.TRUE){
            String msgin = "";
            try{
                System.out.println("Searching for Host...");
                client = new Socket("127.0.0.1", b);
                System.out.println("Connected");
                din = new DataInputStream(client.getInputStream());
                dout = new DataOutputStream(client.getOutputStream());



                msgin = din.readUTF();
                textArea2.setText(textArea2.getText().trim() + "\n Server: \t"+msgin);


            } catch (Exception e){

            }



        } else{
            JFrame window = new Client("Client");
            window.setSize(510,400);
            window.setVisible(true);
        }


    }
}
