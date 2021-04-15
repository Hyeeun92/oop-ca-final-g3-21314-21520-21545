package com.company;

/* 21545 - Hyeeun Lee
21520 - Liubov Eremenko
21314 - Nathalie Flores
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Course implements ActionListener { //action listener interface

    JFrame frame;
    JLabel title, titleCourse, lectureName, otherInfo;
    JTextField titleCourseF, lectureNameF, otherInfoF;
    JButton btnSave, btnCancel;
    String titleCourseS = null;
    String lectureNameS = null;
    String otherInfoS = null;
    String courseId;

    Database db = new Database();

    public Course() {
        Database db = new Database();
        //List<AdmFunctions> getList = db.getList();

        //create JFrame
        frame = new JFrame();
        title = new JLabel("Adm. - Create Course ", SwingConstants.CENTER);
        title.setBounds(0, 0, 640, 47);
        title.setBackground(new Color(39, 87, 144));
        title.setForeground(new Color(255, 250, 224));
        title.setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 20));
        title.setOpaque(true);

        titleCourse = new JLabel("Course Name");
        titleCourse.setBounds(50, 75, 175, 20);
        titleCourse.setFont(new Font("Serif", Font.BOLD, 20));
        lectureName = new JLabel("Lecture ID");
        lectureName.setBounds(50, 125, 175, 20);
        lectureName.setFont(new Font("Serif", Font.BOLD, 20));
        otherInfo = new JLabel("Course Price");
        otherInfo.setBounds(50, 175, 175, 20);
        otherInfo.setFont(new Font("Serif", Font.BOLD, 20));

        titleCourseF = new JTextField();
        titleCourseF.setBounds(200, 75, 300, 25);
        lectureNameF = new JTextField();
        lectureNameF.setBounds(200, 125, 300, 25);
        otherInfoF = new JTextField();
        otherInfoF.setBounds(200, 200, 300, 25);


        btnSave = new JButton("Save");
        btnSave.setFont(new Font("Serif", Font.BOLD, 20));
        btnSave.setBounds(75, 350, 175, 50);

        btnCancel = new JButton("Cancel");
        btnCancel.setFont(new Font("Serif", Font.BOLD, 20));
        btnCancel.setBounds(350, 350, 175, 50);

        frame.add(title);
        frame.add(titleCourse);
        frame.add(titleCourseF);
        frame.add(lectureName);
        frame.add(lectureNameF);
        frame.add(otherInfo);
        frame.add(otherInfoF);
        frame.add(btnSave);
        frame.add(btnCancel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(640, 480);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        btnCancel.addActionListener(this);
        btnSave.addActionListener(this);

    }


    public void cleanFields(){
    titleCourseS = titleCourseF.getText();
    //lectureNameS = lectureNameF.getText();
    otherInfoS = otherInfoF.getText();

    createCourseId();

    db.getCourseCreateInfo( courseId,titleCourseS, otherInfoS );

    JOptionPane.showMessageDialog(frame,
            "New course create",
            "Information save in DateBase",
    JOptionPane.INFORMATION_MESSAGE );

    titleCourseF.setText("    ");
    lectureNameF.setText("    ");
    otherInfoF.setText("      ");
     }

    private void createCourseId() {
        createCourseId2();
    }

    private String createCourseId2() {
        titleCourseS = titleCourseF.getText();

        String sample = new String("ABCDEFGHIJKLMNOPQRSTUVWXYZ");

        String code = String.valueOf((sample.indexOf(titleCourseS.charAt(0))) + 1);

        courseId = String.join(code);

        return courseId;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCancel) {
            this.frame.setVisible(false);
            Administrator administrator = new Administrator();
        } else if (e.getSource() ==  btnSave) {
            this.cleanFields();

        }
    }
}



