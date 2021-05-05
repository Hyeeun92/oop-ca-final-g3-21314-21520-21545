package com.company;

/*
21545 - Hyeeun Lee
21520 - Liubov Eremenko
21314 - Nathalie Flores
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RenewLogin extends JFrame {

    JFrame frame;
    JButton btnSave;
    JTextField idField;
    JPasswordField pswdField, newField, confirmField;
    JLabel title, idLabel, pswdLabel, newLabel, confirmLabel, messageLabel;
    String inputPswd = "";
    String pswdNew = "";
    String pswdConfirm = "";
    String getId;
    String getpswd;

    public RenewLogin(String id, String pswd) {

        Database db = new Database();
        this.getId = id;
        this.getpswd = pswd;

        //create JFrame
        frame = new JFrame();
        title = new JLabel("Virtual Global College (VGC)",SwingConstants.CENTER);
        title.setBounds(0, 0, 640, 47);
        title.setBackground(new Color(0, 0, 130));
        title.setForeground(new Color(255, 250, 224));
        title.setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 20));
        title.setOpaque(true);

        btnSave = new JButton("Save");

        idField = new JTextField(null);
        pswdField = new JPasswordField(null);
        newField = new JPasswordField(null);
        confirmField = new JPasswordField(null);

        idLabel = new JLabel("ID:");
        pswdLabel = new JLabel("Current password:");
        newLabel = new JLabel("New password:");
        confirmLabel = new JLabel("Confirm new password:");

        messageLabel = new JLabel();

        idLabel.setBounds(100, 125, 150, 25);
        idLabel.setFont(new Font("Serif", Font.BOLD, 16));
        pswdLabel.setBounds(100, 175, 150, 25);
        pswdLabel.setFont(new Font("Serif", Font.BOLD, 16));
        newLabel.setBounds(100, 225, 150, 25);
        newLabel.setFont(new Font("Serif", Font.BOLD, 16));
        confirmLabel.setBounds(100, 275, 170, 25);
        confirmLabel.setFont(new Font("Serif", Font.BOLD, 16));

        messageLabel.setBounds(325, 250, 250, 35);
        messageLabel.setFont(new Font(null, Font.BOLD, 25));

        idField.setBounds(100, 149, 300, 25);
        pswdField.setBounds(100, 199, 300, 25);
        newField.setBounds(100, 249, 300, 25);
        confirmField.setBounds(100,299,300, 25);

        btnSave.setBounds(375, 350, 175, 50);
        btnSave.setFont(new Font("Serif", Font.BOLD, 20));
        btnSave.setFocusable(false);

        frame.add(title);
        frame.add(idLabel);
        frame.add(pswdLabel);
        frame.add(newLabel);
        frame.add(confirmLabel);
        frame.add(messageLabel);

        frame.add(idField);
        frame.add(pswdField);
        frame.add(newField);
        frame.add(confirmField);
        frame.add(btnSave);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(640, 480);
        frame.setLayout(null);
        frame.setVisible(true);
        idField.setText(getId);
        System.out.println(getId);
        idField.setEditable(false);

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputPswd = String.valueOf(pswdField.getPassword());
                pswdNew = String.valueOf(newField.getPassword());
                pswdConfirm = String.valueOf(confirmField.getPassword());
                //start if to compare user input password and database password
                if (inputPswd.equals(getpswd)) {
                    //start if to compare user input pasword and database password
                    if (pswdNew.equals(getpswd)) {
                        JOptionPane optionPane = new JOptionPane("Current password and new password are same", JOptionPane.ERROR_MESSAGE);
                        JDialog dialog = optionPane.createDialog("Failure");
                        dialog.setAlwaysOnTop(true);
                        dialog.setVisible(true);
                    } else {
                        //start if to compare user input new password and confirm password
                        if (pswdNew.equals(pswdConfirm)) {
                            //call database class changeStudentPswd to update new password
                            db.changeStudentPswd(getId, pswdNew);
                        } else {
                            JOptionPane optionPane = new JOptionPane("New password and confirm new password are different", JOptionPane.ERROR_MESSAGE);
                            JDialog dialog = optionPane.createDialog("Failure");
                            dialog.setAlwaysOnTop(true);
                            dialog.setVisible(true);
                        }
                    }
                } else {
                    JOptionPane optionPane = new JOptionPane("Incorrect password", JOptionPane.ERROR_MESSAGE);
                    JDialog dialog = optionPane.createDialog("Failure");
                    dialog.setAlwaysOnTop(true);
                    dialog.setVisible(true);
                }
            }
        });
    }
}
