package com.company;

/* 21545 - Hyeeun Lee
21520 - Liubov Eremenko
21314 - Nathalie Flores
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Student extends Database { //action listener interface

    JFrame frame;
    JLabel title, nameStudent, email, address, gender,amount, headerCourse;
    JTextField nameStudentF, emailF, addressF, amountF;
    ButtonGroup radioGroupGender, radioGroupFee;
    JRadioButton rdbFemale,rdbMale, rdbFullyP, rdbInstP;
    JButton btnChangeScreen, btnCancel;
    JPanel controlPanel;

    public Student(){
        Database db = new Database();

       //create JFrame
        frame = new JFrame();
        title = new JLabel("Adm. - Create Student ",SwingConstants.CENTER);
        title.setBounds(0, 0, 640, 47);
        title.setBackground(new Color(39, 87, 144));
        title.setForeground(new Color(255, 250, 224));
        title.setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 20));
        title.setOpaque(true);

        nameStudent = new JLabel("Name");
        nameStudent.setBounds(50, 75, 175, 20);
        nameStudent.setFont(new Font("Serif", Font.BOLD, 15));
        email = new JLabel("Email");
        email.setBounds(50, 110, 175, 20);
        email.setFont(new Font("Serif", Font.BOLD, 15));
        address = new JLabel("Address");
        address.setBounds(50, 145, 175, 20);
        address.setFont(new Font("Serif", Font.BOLD, 15));
        gender = new JLabel("Gender");
        gender.setBounds(450, 75, 175, 20);
        gender.setFont(new Font("Serif", Font.BOLD, 18));
        amount = new JLabel("Amount Fee Paid");
        amount.setBounds(50, 375, 175, 20);
        amount.setFont(new Font("Serif", Font.BOLD, 15));
        headerCourse = new JLabel("COURSES AVAILABLE");
        headerCourse.setBounds(150, 175, 225, 20);
        headerCourse.setFont(new Font("Serif", Font.BOLD, 20));

        nameStudentF = new JTextField();
        nameStudentF.setBounds(150, 75, 200, 25);
        emailF = new JTextField();
        emailF.setBounds(150, 110, 200, 25);
        addressF = new JTextField();
        addressF.setBounds(150, 145, 200, 25);
        amountF = new JTextField();
        amountF.setBounds(180, 375, 100, 25);

        rdbFemale = new JRadioButton("Female");
        rdbFemale.setFont(new Font("Serif", Font.BOLD, 15));
        rdbFemale.setBounds(450, 100, 100, 25);
        rdbMale = new JRadioButton("Male");
        rdbMale.setFont(new Font("Serif", Font.BOLD, 15));
        rdbMale.setBounds(450, 125, 100, 25);

        radioGroupGender= new ButtonGroup();
        radioGroupGender.add(rdbFemale);
        radioGroupGender.add(rdbMale);

        rdbFullyP= new JRadioButton("Fully paid");
        rdbFullyP.setFont(new Font("Serif", Font.BOLD, 12));
        rdbFullyP.setBounds(50, 340, 100, 25);
        rdbInstP= new JRadioButton("Instalments paid");
        rdbInstP.setFont(new Font("Serif", Font.BOLD, 12));
        rdbInstP.setBounds(175, 340, 150, 25);

        radioGroupFee= new ButtonGroup();
        radioGroupFee.add(rdbFullyP);
        radioGroupFee.add(rdbInstP);

        btnChangeScreen = new JButton("Register");
        btnChangeScreen.setFont(new Font("Serif", Font.BOLD, 20));
        btnChangeScreen.setBounds(400, 350, 175, 50);

        btnCancel = new JButton("Cancel");
        btnCancel.setFont(new Font("Serif", Font.BOLD, 20));
        btnCancel.setBounds(400, 275, 175, 50);

        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        controlPanel.setBounds(150, 200, 200, 200);

        frame.add(title);
        frame.add(nameStudent);
        frame.add(nameStudentF);
        frame.add(email);
        frame.add(emailF);
        frame.add(address);
        frame.add(addressF);
        frame.add(gender);
        frame.add(rdbFemale);
        frame.add(rdbMale);
        frame.add(rdbFullyP);
        frame.add(rdbInstP);
        frame.add(amount);
        frame.add(amountF);
        frame.add(btnChangeScreen);
        frame.add(btnCancel);
        frame.add(headerCourse);
        frame.add(controlPanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(640, 480);
        frame.setVisible(true);

        showListCourses();

        btnCancel.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        Administrator administrator = new Administrator();
                    }
                }
        );

    }

    private void showListCourses(){
        //List<Course> getList = db.getList();
        //demo list until populated our DateBase

        final DefaultListModel courseName = new DefaultListModel();

        courseName.addElement("Computer science");
        courseName.addElement("Business");
        courseName.addElement("Mathematics II");
        courseName.addElement("Systems Operations");
        courseName.addElement("Mobile II");
        
        final JList courseList = new JList(courseName);
        courseList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        courseList.setSelectedIndex(0);
        courseList.setVisibleRowCount(3);
        courseList.setFixedCellHeight(30);
        courseList.setFixedCellWidth(175);

        JScrollPane courseListScrollPane = new JScrollPane(courseList);

        controlPanel.add(courseListScrollPane);

    }

}
