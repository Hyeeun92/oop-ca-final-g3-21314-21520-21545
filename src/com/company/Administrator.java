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

public class Administrator extends Database { //action listener interface

    JFrame frame;
    JLabel title;
    ButtonGroup radioGroup;
    JRadioButton rdbCreateStudent, rdbCreateCourse, rdbCreateTimetable, rdbManageStudentInfo;
    JButton btnChangeScreen;


    public Administrator(){
        Database db = new Database();
        //List<AdmFunctions> getList = db.getList();

       //create JFrame
        frame = new JFrame();
        title = new JLabel("Administrator Functions",SwingConstants.CENTER);
        title.setBounds(0, 0, 640, 47);
        title.setBackground(new Color(0, 0, 130));
        title.setForeground(new Color(255, 250, 224));
        title.setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 20));
        title.setOpaque(true);


        rdbCreateStudent = new JRadioButton("Create student");
        rdbCreateStudent.setFont(new Font("Serif", Font.BOLD, 20));
        rdbCreateStudent.setBounds(100, 100, 175, 50);
        rdbCreateCourse = new JRadioButton("Create course");
        rdbCreateCourse.setFont(new Font("Serif", Font.BOLD, 20));
        rdbCreateCourse.setBounds(100, 150, 175, 50);
        rdbCreateTimetable = new JRadioButton("Create timetable");
        rdbCreateTimetable.setFont(new Font("Serif", Font.BOLD, 20));
        rdbCreateTimetable.setBounds(100, 200, 175, 50);
        rdbManageStudentInfo = new JRadioButton("Manage of fees paid");
        rdbManageStudentInfo.setFont(new Font("Serif", Font.BOLD, 20));
        rdbManageStudentInfo.setBounds(100, 250, 375, 50);

        btnChangeScreen = new JButton("Continue");
        btnChangeScreen.setFont(new Font("Serif", Font.BOLD, 20));
        btnChangeScreen.setBounds(400, 350, 175, 50);

        radioGroup= new ButtonGroup();
        radioGroup.add(rdbCreateStudent);
        radioGroup.add(rdbCreateCourse);
        radioGroup.add(rdbCreateTimetable);
        radioGroup.add(rdbManageStudentInfo);

        /*rdbCreateStudent.addActionListener();
        rdbCreateCourse.addActionListener();
        rdbCreateTimetable.addActionListener();
        rdbManageStudentInfo.addActionListener();
        btnChangeScreen.addActionListener();

         */


        frame.add(title);
        frame.add(rdbCreateStudent);
        frame.add(rdbCreateCourse);
        frame.add(rdbCreateTimetable);
        frame.add(rdbManageStudentInfo);
        frame.add(btnChangeScreen);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(640, 480);
        frame.setVisible(true);

    }

}
