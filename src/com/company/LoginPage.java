package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class LoginPage implements ActionListener { //action listener interface
    //create JFrame
    JFrame frame = new JFrame();
    JButton btnLogin = new JButton("Login");
    JButton btnReset = new JButton("Reset");
    JTextField idField = new JTextField();
    JPasswordField pswdField = new JPasswordField();
    JLabel idLabel = new JLabel("ID:");
    JLabel pswdLabel = new JLabel("Password:");
    JLabel messageLabel = new JLabel();

    //for globally available to all methods create a new hash map
    HashMap<String, String> logininfo = new HashMap<String,String>();


    //create constructor for our login page
    LoginPage(HashMap<String, String>loginInfoOriginal) {
        logininfo = loginInfoOriginal;

        idLabel.setBounds(50, 100,75,25 );
        pswdLabel.setBounds(50,150,75,25);


        messageLabel.setBounds(125,250,250,35);
        messageLabel.setFont(new Font(null,Font.BOLD,25));


        idField.setBounds(125,100,200,25);
        pswdField.setBounds(125,150,200,25);

        btnLogin.setBounds(225,200,100,25);
        btnLogin.setFocusable(false);
        btnLogin.addActionListener(this);

        btnReset.setBounds(125,200,100,25);
        btnReset.setFocusable(false);
        btnReset.addActionListener(this);


        frame.add(idLabel);
        frame.add(pswdLabel);
        frame.add(messageLabel);
        frame.add(idField);
        frame.add(pswdField);
        frame.add(btnLogin);
        frame.add(btnReset);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 700);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnReset) {
            idField.setText("");
            pswdField.setText("");
        }


        if(e.getSource()==btnLogin) {
            String id = idField.getText();
            String pswd = String.valueOf(pswdField.getPassword());

            if(logininfo.containsKey(id)) {
                if(logininfo.get(id).equals(pswd)) {
                    messageLabel.setForeground(Color.green);
                    messageLabel.setText("Login successful");
                    frame.dispose();
                    WelcomePage welcomePage = new WelcomePage(id);
                }
                else {
                    messageLabel.setForeground(Color.red);
                    messageLabel.setText("Wrong password");
                }
            }
            else {
                messageLabel.setForeground(Color.red);
                messageLabel.setText("Wrong ID");
            }

        }

    }
}
