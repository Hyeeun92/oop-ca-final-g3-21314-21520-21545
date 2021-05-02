package com.company;

/*
21545 - Hyeeun Lee
21520 - Liubov Eremenko
21314 - Nathalie Flores
*/

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.http.WebSocket;

public class LoginPage extends JFrame{

    JFrame frameLogin;
    ButtonGroup radioGroup;
    JButton btnLogin, btnReset;
    JTextField idField;
    JPasswordField pswdField;
    JLabel title, typeOfUser, idLabel, pswdLabel, messageLabel;
    JRadioButton adminRadio, lectureRadio, studentRadio;
    String id = null;
    String pswd = null;
    JPanel panel1, panel2, panelR1, panelR2, panelR3, panelB1,panelB2;

    public LoginPage(){

        Database db = new Database();
        CalendarMemo calendarMemo = new CalendarMemo();

        frameLogin = new JFrame();

        JLabel background=new JLabel(new ImageIcon(getClass().getResource("college.jpg")));
        frameLogin.add(background);

        SpringLayout layout = new SpringLayout();
        background.setLayout(layout);

        title = new JLabel("  Virtual Global College (VGC)  ");
        title.setSize(640, 50);
        title.setBackground(new Color(26, 53, 8));
        title.setForeground(new Color(255, 250, 224));
        SpringLayout.Constraints labelCons = layout.getConstraints(title);
        labelCons.setX(Spring.constant(15));
        labelCons.setY(Spring.constant(0));
        title.setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 45));
        title.setOpaque(true);
        background.add(title);

        idLabel = new JLabel("User ID:    ");
        idLabel.setFont(new Font("Serif", Font.BOLD, 20));
        idLabel.setForeground(Color.BLACK);
        idField = new JTextField("", 20);
        panel1 = new JPanel();
        panel1.setBackground(new Color(255, 250, 222));
        layout.putConstraint(SpringLayout.WEST, panel1, 25, SpringLayout.WEST, background);
        SpringLayout.Constraints labelCons1 = layout.getConstraints(panel1);
        labelCons1.setX(Spring.constant(200));
        labelCons1.setY(Spring.constant(175));
        panel1.add(idLabel);
        panel1.add(idField);
        background.add(panel1);

        pswdLabel = new JLabel("Password: ");
        pswdLabel.setFont(new Font("Serif", Font.BOLD, 20));
        pswdLabel.setForeground(Color.BLACK);
        pswdField = new JPasswordField("", 20);
        panel2 = new JPanel();
        panel2.setBackground(new Color(255, 250, 222));
        layout.putConstraint(SpringLayout.WEST, panel2, 25, SpringLayout.WEST, background);
        SpringLayout.Constraints labelCons2 = layout.getConstraints(panel2);
        labelCons2.setX(Spring.constant(200));
        labelCons2.setY(Spring.constant(225));
        panel2.add(pswdLabel);
        panel2.add(pswdField);
        background.add(panel2);

        typeOfUser = new JLabel("   Select one:     ");
        typeOfUser.setSize(50, 50);
        typeOfUser.setBackground(new Color(255, 250, 224));
        typeOfUser.setForeground(Color.BLACK);
        SpringLayout.Constraints labelConsS = layout.getConstraints(typeOfUser);
        labelConsS.setX(Spring.constant(15));
        labelConsS.setY(Spring.constant(100));
        typeOfUser.setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 15));
        typeOfUser.setOpaque(true);
        background.add(typeOfUser);

        adminRadio = new JRadioButton("Administrative", false);
        adminRadio.setFont(new Font(null, Font.BOLD, 10));
        adminRadio.setForeground(Color.BLACK);
        adminRadio.setBackground(new Color(255, 250, 224));
        panelR1 = new JPanel();
        panelR1.setBackground(new Color(255, 250, 224));
        layout.putConstraint(SpringLayout.WEST, panelR1, 5, SpringLayout.WEST, background);
        SpringLayout.Constraints labelConsR0 = layout.getConstraints(panelR1);
        labelConsR0.setX(Spring.constant(15));
        labelConsR0.setY(Spring.constant(120));
        panelR1.add(adminRadio);
        background.add(panelR1);


        lectureRadio = new JRadioButton("Lecturer          ", false);
        lectureRadio.setFont(new Font(null, Font.BOLD, 10));
        lectureRadio.setForeground(Color.BLACK);
        lectureRadio.setBackground(new Color(255, 250, 224));
        panelR2 = new JPanel();
        panelR2.setBackground(new Color(255, 250, 224));
        layout.putConstraint(SpringLayout.WEST, panelR2, 5, SpringLayout.WEST, background);
        SpringLayout.Constraints labelConsR1 = layout.getConstraints(panelR2);
        labelConsR1.setX(Spring.constant(15));
        labelConsR1.setY(Spring.constant(145));
        panelR2.add(lectureRadio);
        background.add(panelR2);


        studentRadio = new JRadioButton("Student           ", false);
        studentRadio.setFont(new Font(null, Font.BOLD, 10));
        studentRadio.setForeground(Color.BLACK);
        studentRadio.setBackground(new Color(255, 250, 224));
        panelR3 = new JPanel();
        panelR3.setBackground(new Color(255, 250, 224));
        layout.putConstraint(SpringLayout.WEST, panelR3, 5, SpringLayout.WEST, background);
        SpringLayout.Constraints labelConsR2 = layout.getConstraints(panelR3);
        labelConsR2.setX(Spring.constant(15));
        labelConsR2.setY(Spring.constant(170));
        panelR3.add(studentRadio);
        background.add(panelR3);


        radioGroup = new ButtonGroup();
        radioGroup.add(adminRadio);
        radioGroup.add(lectureRadio);
        radioGroup.add(studentRadio);


        btnLogin = new JButton("Log in");
        btnLogin.setFont(new Font("Serif", Font.BOLD, 15));
        btnLogin.setFocusable(false);
        panelB1 = new JPanel();
        panelB1.setBackground(new Color(255, 250, 224));
        layout.putConstraint(SpringLayout.WEST, panelB1, 5, SpringLayout.WEST, background);
        SpringLayout.Constraints labelConsB1 = layout.getConstraints(panelB1);
        labelConsB1.setX(Spring.constant(490));
        labelConsB1.setY(Spring.constant(375));
        panelB1.add(btnLogin);
        background.add(panelB1);

        btnReset = new JButton("Reset");
        btnReset.setFont(new Font("Serif", Font.BOLD, 15));
        btnReset.setFocusable(false);
        panelB2 = new JPanel();
        panelB2.setBackground(new Color(255, 250, 224));
        layout.putConstraint(SpringLayout.WEST, panelB2, 5, SpringLayout.WEST, background);
        SpringLayout.Constraints labelConsB2 = layout.getConstraints(panelB2);
        labelConsB2.setX(Spring.constant(390));
        labelConsB2.setY(Spring.constant(375));
        panelB2.add(btnReset);
        background.add(panelB2);

        frameLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameLogin.setResizable(false); //prevent frame from being resized
        frameLogin.setSize(640, 480);
        frameLogin.setTitle("LOGIN PAGE");
        frameLogin.setVisible(true);
        frameLogin.setLocationRelativeTo(null); // make the frame center

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
                            db.getStudentLoginInfo(id, pswd);

                        }

                    }
                }
        );
    }



}
