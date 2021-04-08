package com.company;

/* 21545 - Hyeeun Lee
21520 - Liubov Eremenko
21314 - Nathalie Flores
 */

import javax.swing.*;
import java.awt.*;

public class Results extends Database { //action listener interface

    JFrame frame;
    JLabel title, titleCourse, lectureName, otherInfo;
    JTextField titleCourseF, lectureNameF, otherInfoF;
    JButton btnChangeScreen;

    public Results(){
        Database db = new Database();
        //List<AdmFunctions> getList = db.getList();

       //create JFrame
        frame = new JFrame();
        title = new JLabel("Student list of results",SwingConstants.CENTER);
        title.setBounds(0, 0, 640, 47);
        title.setBackground(new Color(39, 87, 144));
        title.setForeground(new Color(255, 250, 224));
        title.setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 20));
        title.setOpaque(true);

        titleCourse = new JLabel("Course Name");
        titleCourse.setBounds(50, 75, 175, 20);
        titleCourse.setFont(new Font("Serif", Font.BOLD, 20));
        lectureName = new JLabel("Lecture Name");
        lectureName.setBounds(50, 125, 175, 20);
        lectureName.setFont(new Font("Serif", Font.BOLD, 20));
        otherInfo = new JLabel("Other information");
        otherInfo.setBounds(50, 175, 175, 20);
        otherInfo.setFont(new Font("Serif", Font.BOLD, 20));

        titleCourseF = new JTextField();
        titleCourseF.setBounds(200, 75, 300, 25);
        lectureNameF = new JTextField();
        lectureNameF.setBounds(200, 125, 300, 25);
        otherInfoF = new JTextField();
        otherInfoF.setBounds(200, 200, 300, 140);


        btnChangeScreen = new JButton("Register");
        btnChangeScreen.setFont(new Font("Serif", Font.BOLD, 20));
        btnChangeScreen.setBounds(200, 350, 175, 50);


        /*

        btnChangeScreen.addActionListener();

         */


        frame.add(title);
        frame.add(titleCourse);
        frame.add(titleCourseF);
        frame.add(lectureName);
        frame.add(lectureNameF);
        frame.add(otherInfo);
        frame.add(otherInfoF);
        frame.add(btnChangeScreen);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(640, 480);
        frame.setVisible(true);

    }

}
