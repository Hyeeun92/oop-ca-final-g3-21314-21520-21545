package com.company;

/* 21545 - Hyeeun Lee
21520 - Liubov Eremenko
21314 - Nathalie Flores
 */

import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Administrator implements ActionListener { //action listener interface

    JFrame frame;
    JLabel title;
    ButtonGroup radioGroup;
    JRadioButton rdbCreateStudent, rdbCreateCourse, rdbCreateTimetable, rdbManageStudentInfo;
    JButton btnContinue, btnCancel;

    public Administrator() {

        Database db = new Database();
        //List<AdmFunctions> getList = db.getList();

        //create JFrame
        frame = new JFrame();
        title = new JLabel("Administrator Functions - CREATING", SwingConstants.CENTER);
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

        btnContinue = new JButton("Continue");
        btnContinue.setFont(new Font("Serif", Font.BOLD, 20));
        btnContinue.setBounds(400, 350, 175, 50);

        btnCancel = new JButton("Log out");
        btnCancel.setFont(new Font("Serif", Font.BOLD, 20));
        btnCancel.setBounds(100, 350, 175, 50);

        radioGroup = new ButtonGroup();
        radioGroup.add(rdbCreateStudent);
        radioGroup.add(rdbCreateCourse);
        radioGroup.add(rdbCreateTimetable);
        radioGroup.add(rdbManageStudentInfo);

        frame.add(title);
        frame.add(rdbCreateStudent);
        frame.add(rdbCreateCourse);
        frame.add(rdbCreateTimetable);
        frame.add(rdbManageStudentInfo);
        frame.add(btnContinue);
        frame.add(btnCancel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(640, 480);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        btnCancel.addActionListener(this);
        btnContinue.addActionListener(this);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCancel) {
            this.frame.setVisible(false);
            LoginPage loginPage = new LoginPage();
        } else if (e.getSource() == btnContinue) {
            if (this.rdbCreateStudent.isSelected()) {
                Student student = new Student();
                frame.setVisible(false);
            } else if (this.rdbCreateCourse.isSelected()) {
                Course course = new Course();
                frame.setVisible(false);
            } else if (this.rdbCreateTimetable.isSelected()) {

                frame.setVisible(false);
            } else if (this.rdbManageStudentInfo.isSelected()) {
                ManagementFees managementFees = new ManagementFees();
                frame.setVisible(false);

            }
        }
    }
}
