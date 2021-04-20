package com.company;

/* 21545 - Hyeeun Lee
21520 - Liubov Eremenko
21314 - Nathalie Flores
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Lecturer implements ActionListener { //action listener interface

    JFrame frame;
    JLabel title, lectureName, lectureLastName, lectureEmail;
    JTextField lectureNameF, lectureLastNameF, lectureEmailF;
    JButton btnSave, btnCancel;
    String nameLectureS, lastLectureS, lecture_id, lecture_password, lecture_name, lecture_email;


    Database db = new Database();

    public Lecturer() {
        Database db = new Database();
        //List<AdmFunctions> getList = db.getList();

        //create JFrame
        frame = new JFrame();
        title = new JLabel("Adm. - Create Lecturer ", SwingConstants.CENTER);
        title.setBounds(0, 0, 640, 47);
        title.setBackground(new Color(39, 87, 144));
        title.setForeground(new Color(255, 250, 224));
        title.setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 20));
        title.setOpaque(true);

        lectureName = new JLabel("Lecturer Name");
        lectureName.setBounds(50, 100, 175, 20);
        lectureName.setFont(new Font("Serif", Font.BOLD, 20));
        lectureLastName = new JLabel("Lecturer Lastname");
        lectureLastName.setBounds(50, 150, 175, 20);
        lectureLastName.setFont(new Font("Serif", Font.BOLD, 20));
        lectureEmail = new JLabel("Lecturer Email");
        lectureEmail.setBounds(50, 200, 175, 20);
        lectureEmail.setFont(new Font("Serif", Font.BOLD, 20));

        lectureNameF = new JTextField();
        lectureNameF.setBounds(240, 100, 300, 25);
        lectureLastNameF = new JTextField();
        lectureLastNameF.setBounds(240, 150, 300, 25);
        lectureEmailF = new JTextField();
        lectureEmailF.setBounds(240, 200, 300, 25);


        btnSave = new JButton("Save");
        btnSave.setFont(new Font("Serif", Font.BOLD, 20));
        btnSave.setBounds(75, 350, 175, 50);

        btnCancel = new JButton("Cancel");
        btnCancel.setFont(new Font("Serif", Font.BOLD, 20));
        btnCancel.setBounds(350, 350, 175, 50);

        frame.add(title);
        frame.add(lectureName);
        frame.add(lectureNameF);
        frame.add(lectureLastName);
        frame.add(lectureLastNameF);
        frame.add(lectureEmail);
        frame.add(lectureEmailF);
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

    /*
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

*/
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCancel) {
            this.frame.setVisible(false);
            Administrator administrator = new Administrator();
        } else if (e.getSource() ==  btnSave) {
          //  this.cleanFields();

        }
    }
}