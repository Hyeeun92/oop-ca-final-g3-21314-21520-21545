package com.company;

/* 21545 - Hyeeun Lee
21520 - Liubov Eremenko
21314 - Nathalie Flores
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LoginPage extends Database { //action listener interface

    String typeAdmin = "Admin";
    String typeLecture = "Lecture";
    String typeStudent = "Student";
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
        List<LoginInfo> getList = db.getList();

        //create JFrame
        frame = new JFrame();
        title = new JLabel("Virtual Global College (VGC)",SwingConstants.CENTER);
        title.setBounds(0, 0, 640, 47);
        title.setBackground(new Color(0 , 0, 130));
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

        adminRadio.setBounds(150, 75, 150, 50);
        adminRadio.setFont(new Font(null, Font.BOLD, 12));
        lectureRadio.setBounds(300, 75, 100, 50);
        lectureRadio.setFont(new Font(null, Font.BOLD, 12));
        studentRadio.setBounds(420, 75, 100, 50);
        studentRadio.setFont(new Font(null, Font.BOLD, 12));

        radioGroup = new ButtonGroup();
        radioGroup.add(adminRadio);
        radioGroup.add(lectureRadio);
        radioGroup.add(studentRadio);

        idLabel.setBounds(150, 200, 75, 25);
        pswdLabel.setBounds(150, 250, 75, 25);

        messageLabel.setBounds(325, 250, 250, 35);
        messageLabel.setFont(new Font(null, Font.BOLD, 25));

        idField.setBounds(225, 200, 200, 25);
        pswdField.setBounds(225, 250, 200, 25);

        btnLogin.setBounds(300, 350, 100, 25);
        btnLogin.setFocusable(false);

        btnReset.setBounds(150, 350, 100, 25);
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
                        String values = null;
                        String gettingInfo = null;

                        for (int i = 0; i < getList.size(); i++) {
                            values = String.format("%s", getList.get(i));

                            if (adminRadio.isSelected()) {
                                gettingInfo = String.format("%s %s %s", typeAdmin, id, pswd);

                                if (values.equals(gettingInfo)) {
                                    messageLabel.setForeground(Color.green);
                                    messageLabel.setText("Login successful");
                                    frame.dispose();
                                    WelcomePage welcomePage = new WelcomePage(id);
                                } else {
                                    messageLabel.setForeground(Color.red);
                                    messageLabel.setText("Please check one more");
                                }
                            }

                        else if (lectureRadio.isSelected()) {
                                    gettingInfo = String.format("%s %s %s", typeLecture, id, pswd);

                                    // System.out.println(gettingInfo);
                                    if (values.equals(gettingInfo)) {
                                        messageLabel.setForeground(Color.green);
                                        messageLabel.setText("Login successful");
                                        frame.dispose();
                                        WelcomePage welcomePage = new WelcomePage(id);
                                    } else {
                                        messageLabel.setForeground(Color.red);
                                        messageLabel.setText("Please check one more");
                                    }

                                } else if (studentRadio.isSelected()) {
                                    gettingInfo = String.format("%s %s %s", typeStudent, id, pswd);
                                    if (values.equals(gettingInfo)) {
                                        messageLabel.setForeground(Color.green);
                                        messageLabel.setText("Login successful");
                                        frame.dispose();
                                        WelcomePage welcomePage = new WelcomePage(id);
                                    } else {
                                        messageLabel.setForeground(Color.red);
                                        messageLabel.setText("Please check one more");
                                    }
                                }
                            }

                        }

                }
        );
    }
}








