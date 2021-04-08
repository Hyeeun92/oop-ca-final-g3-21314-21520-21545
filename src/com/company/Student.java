package com.company;

/* 21545 - Hyeeun Lee
21520 - Liubov Eremenko
21314 - Nathalie Flores
 */

import javax.swing.*;
import java.awt.*;

public class Student extends Database { //action listener interface

    JFrame frame;
    JLabel title, nameStudent, email, address;
    JTextField nameStudentF, emailF, addressF;
    JButton btnChangeScreen;

    public Student(){
        Database db = new Database();
        //List<AdmFunctions> getList = db.getList();

       //create JFrame
        frame = new JFrame();
        title = new JLabel("Adm. - Create Student ",SwingConstants.CENTER);
        title.setBounds(0, 0, 640, 47);
        title.setBackground(new Color(39, 87, 144));
        title.setForeground(new Color(255, 250, 224));
        title.setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 20));
        title.setOpaque(true);

        nameStudent = new JLabel("Name Student");
        nameStudent.setBounds(50, 75, 175, 20);
        nameStudent.setFont(new Font("Serif", Font.BOLD, 12));
        email = new JLabel("Lecture Name");
        email.setBounds(50, 125, 175, 20);
        email.setFont(new Font("Serif", Font.BOLD, 12));
        address = new JLabel("Other information");
        address.setBounds(50, 175, 175, 20);
        address.setFont(new Font("Serif", Font.BOLD, 12));

        nameStudentF = new JTextField();
        nameStudentF.setBounds(150, 75, 200, 25);
        emailF = new JTextField();
        emailF.setBounds(150, 125, 200, 25);
        addressF = new JTextField();
        addressF.setBounds(150, 175, 200, 25);


        btnChangeScreen = new JButton("Register");
        btnChangeScreen.setFont(new Font("Serif", Font.BOLD, 20));
        btnChangeScreen.setBounds(200, 350, 175, 50);


        /*

        btnChangeScreen.addActionListener();

         */


        frame.add(title);
        frame.add(nameStudent);
        frame.add(nameStudentF);
        frame.add(email);
        frame.add(emailF);
        frame.add(address);
        frame.add(addressF);
        frame.add(btnChangeScreen);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(640, 480);
        frame.setVisible(true);

    }

}
