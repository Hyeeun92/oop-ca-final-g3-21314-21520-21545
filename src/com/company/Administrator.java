package com.company;

/* 21545 - Hyeeun Lee
21520
21314 - Nathalie Flores
 */

import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Administrator extends JFrame {

    Font f;

    JLabel title;

    JRadioButton rdbCreateStudent;
    JRadioButton rdbCreateCourse;
    JRadioButton rdbCreateTimetable;
    JRadioButton rdbManageStudentInfo;

    ButtonGroup radioGroup;

    JButton btnChangeScreen;

    JPanel panWest;
    JPanel PanSouth;
    JPanel panNorth;
    JPanel panEast;

    public Administrator(){

        super("Administrator.1");

        panNorth= new JPanel();

        panNorth.add(title = new JLabel("Administrator Functions"));
        title.setBounds(0, 0, 460, 47);
        title.setBackground(new Color(107, 142, 35));
        title.setForeground(new Color(255, 250, 224));
        title.setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 20));
        title.setOpaque(true);

        panWest= new JPanel();
        panWest.add(rdbCreateStudent = new JRadioButton("Create student"));
        rdbCreateStudent.setFont(new Font("Serif", Font.BOLD, 12));
        rdbCreateStudent.setBounds(205, 64, 135, 21);

        panWest.add(rdbCreateCourse = new JRadioButton("Create course"));
        panWest.add(rdbCreateTimetable = new JRadioButton("Create timetable"));
        panWest.add(rdbManageStudentInfo = new JRadioButton("Manage student information"));
        panWest.add(btnChangeScreen = new JButton("Continue"));

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

        f = new Font("Serif", Font.BOLD, 12);
        add(panWest, SwingConstants.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Administrator");
        setBounds(100, 100, 600, 300);
        setVisible(true);
    }

}
