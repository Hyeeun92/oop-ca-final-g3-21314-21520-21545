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

public class LoginPage extends JFrame{

    ButtonGroup radioGroup;
    JFrame frame;
    JButton btnLogin, btnReset;
    JTextField idField;
    JPasswordField pswdField;
    JLabel title,idLabel, pswdLabel, messageLabel;
    JRadioButton adminRadio, lectureRadio, studentRadio;
    String id = null;
    String pswd = null;

    public LoginPage() {

        Database db = new Database();

        //create JFrame
        frame = new JFrame();
        title = new JLabel("Virtual Global College (VGC)",SwingConstants.CENTER);
        title.setBounds(0, 0, 640, 47);
        title.setBackground(new Color(107, 142, 35));
        title.setForeground(new Color(255, 250, 224));
        title.setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 20));
        title.setOpaque(true);

        btnLogin = new JButton("Login");
        btnReset = new JButton("Reset");
        idField = new JTextField(null);
        pswdField = new JPasswordField(null);
        idLabel = new JLabel("ID:");
        pswdLabel = new JLabel("Password:");
        messageLabel = new JLabel();

        adminRadio = new JRadioButton("Administrator", false);
        lectureRadio = new JRadioButton("Lecture", false);
        studentRadio = new JRadioButton("Student", false);

        adminRadio.setBounds(150, 100, 150, 50);
        adminRadio.setFont(new Font(null, Font.BOLD, 12));
        lectureRadio.setBounds(300, 100, 100, 50);
        lectureRadio.setFont(new Font(null, Font.BOLD, 12));
        studentRadio.setBounds(420, 100, 100, 50);
        studentRadio.setFont(new Font(null, Font.BOLD, 12));

        radioGroup = new ButtonGroup();
        radioGroup.add(adminRadio);
        radioGroup.add(lectureRadio);
        radioGroup.add(studentRadio);

        idLabel.setBounds(150, 150, 75, 25);
        pswdLabel.setBounds(150, 200, 75, 25);

        messageLabel.setBounds(325, 250, 250, 35);
        messageLabel.setFont(new Font(null, Font.BOLD, 25));

        idField.setBounds(225, 150, 200, 25);
        pswdField.setBounds(225, 200, 200, 25);

        btnLogin.setBounds(300, 250, 100, 25);
        btnLogin.setFocusable(false);

        btnReset.setBounds(150, 250, 100, 25);
        btnReset.setFocusable(false);

        frame.add(title);
        frame.add(idLabel);
        frame.add(pswdLabel);
        frame.add(messageLabel);
        frame.add(idField);
        frame.add(pswdField);
        frame.add(btnLogin);
        frame.add(btnReset);
        frame.add(adminRadio);
        frame.add(lectureRadio);
        frame.add(studentRadio);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(640, 480);
        frame.setLayout(null);
        frame.setVisible(true);

        btnLogin.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        id = idField.getText();
                        pswd = String.valueOf(pswdField.getPassword());

                        if (adminRadio.isSelected()) {
                            db.getAdminLoginInfo(id, pswd);
                        } else if (lectureRadio.isSelected()) {
                            db.getLectureLoginInfo(id, pswd);
                        } else if (studentRadio.isSelected()) {
                            db.getStudentLoginInfo(id,pswd);
                        }
                    }
                }
        );
    }
}