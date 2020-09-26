package com.cris.chat;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame{
    private JTextArea textArea;
    private JPanel mainPanel;
    private JButton sendButton;
    private JTextField messageBox;

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
    }
    public static void main(String[] args) throws Exception {
        /*
        Map<String, List<String>> messageDB = new HashMap<>();
        java.util.List<String> messages = new ArrayList<String>();
        JList messageList = new JList();
        messageList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        messageList.addListSelectionListener(e -> {
            System.out.println("SELECTED IS " + messageList.getSelectedValue());
        });
        messageList.setFont(new Font("Arial", Font.ITALIC, 30));

         */
        JFrame window = new Main("Extraclase");
        window.setSize(500,500);
        window.setVisible(true);
        /*ChatReceiver receiver = new ChatReceiver();
        receiver.listen(s -> {
            if (messageDB.containsKey(s.getSender())) {
                messageDB.get(s.getSender()).add(s.getPayload());
            } else {
                java.util.List<String> messageForSender = new ArrayList<>();
                messageForSender.add(s.getPayload());
                messageDB.put(s.getSender(), messages);
            }
            messageList.setListData(messageDB.keySet().toArray());

         */
    }//);
}
//}

