package com.company;


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
    JLabel idLabel, pswdLabel, messageLabel;
    JRadioButton adminRadio, lectureRadio, studentRadio;
    String id = null;
    String pswd = null;

    public LoginPage() {

        Database db = new Database();
        List<LoginInfo> getList = db.getList();
        System.out.println("1");

        System.out.println(getList);
        System.out.println("2");


        //create JFrame
        frame = new JFrame();
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


        adminRadio.setBounds(25, 50, 100, 25);
        adminRadio.setFont(new Font(null, Font.BOLD, 10));
        lectureRadio.setBounds(175, 50, 100, 25);
        lectureRadio.setFont(new Font(null, Font.BOLD, 10));
        studentRadio.setBounds(325, 50, 100, 25);
        studentRadio.setFont(new Font(null, Font.BOLD, 10));

        radioGroup = new ButtonGroup();
        radioGroup.add(adminRadio);
        radioGroup.add(lectureRadio);
        radioGroup.add(studentRadio);

        idLabel.setBounds(50, 100, 75, 25);
        pswdLabel.setBounds(50, 150, 75, 25);


        messageLabel.setBounds(125, 250, 250, 35);
        messageLabel.setFont(new Font(null, Font.BOLD, 25));


        idField.setBounds(125, 100, 200, 25);
        pswdField.setBounds(125, 150, 200, 25);

        btnLogin.setBounds(225, 200, 100, 25);
        btnLogin.setFocusable(false);

        btnReset.setBounds(125, 200, 100, 25);
        btnReset.setFocusable(false);

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
        frame.setSize(1200, 700);
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
                            System.out.println(values);
                            System.out.println("??");
                            System.out.println(gettingInfo);

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








