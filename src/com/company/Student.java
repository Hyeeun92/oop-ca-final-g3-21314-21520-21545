package com.company;

/* 21545 - Hyeeun Lee
21520 - Liubov Eremenko
21314 - Nathalie Flores
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Student implements ActionListener { //action listener interface

    JFrame frame;
    JLabel title, nameStudent, lastname, email, address, gender,headerCourse;
    JTextField nameStudentF,lastnameF, emailF, addressF;
    ButtonGroup radioGroupGender;
    JRadioButton rdbFemale,rdbMale;
    JButton btnChangeScreen, btnCancel;
    JPanel controlPanel;
    String nameS, lastnameS,createPassword;

    Database db = new Database();

    public Student(){

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
        lastname = new JLabel("Lastname");
        lastname.setBounds(50, 110, 175, 20);
        lastname.setFont(new Font("Serif", Font.BOLD, 15));
        email = new JLabel("Email");
        email.setBounds(50, 145, 175, 20);
        email.setFont(new Font("Serif", Font.BOLD, 15));
        address = new JLabel("Address");
        address.setBounds(50, 180, 175, 20);
        address.setFont(new Font("Serif", Font.BOLD, 15));
        gender = new JLabel("Gender");
        gender.setBounds(450, 75, 175, 20);
        gender.setFont(new Font("Serif", Font.BOLD, 18));
        headerCourse = new JLabel("COURSES AVAILABLE");
        headerCourse.setBounds(150, 220, 225, 20);
        headerCourse.setFont(new Font("Serif", Font.BOLD, 20));

        nameStudentF = new JTextField();
        nameStudentF.setBounds(150, 75, 200, 25);
        lastnameF = new JTextField();
        lastnameF.setBounds(150, 110, 200, 25);
        emailF = new JTextField();
        emailF.setBounds(150, 145, 200, 25);
        addressF = new JTextField();
        addressF.setBounds(150, 180, 200, 25);

        rdbFemale = new JRadioButton("Female");
        rdbFemale.setFont(new Font("Serif", Font.BOLD, 15));
        rdbFemale.setBounds(450, 110, 100, 25);
        rdbMale = new JRadioButton("Male");
        rdbMale.setFont(new Font("Serif", Font.BOLD, 15));
        rdbMale.setBounds(450, 145, 100, 25);

        radioGroupGender= new ButtonGroup();
        radioGroupGender.add(rdbFemale);
        radioGroupGender.add(rdbMale);

        btnChangeScreen = new JButton("Save");
        btnChangeScreen.setFont(new Font("Serif", Font.BOLD, 20));
        btnChangeScreen.setBounds(400, 370, 175, 50);
        btnCancel = new JButton("Cancel");
        btnCancel.setFont(new Font("Serif", Font.BOLD, 20));
        btnCancel.setBounds(200, 370, 175, 50);

        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        controlPanel.setBounds(150, 250, 200, 200);

        frame.add(title);
        frame.add(nameStudent);
        frame.add(nameStudentF);
        frame.add(lastname);
        frame.add(lastnameF);
        frame.add(email);
        frame.add(emailF);
        frame.add(address);
        frame.add(addressF);
        frame.add(gender);
        frame.add(rdbFemale);
        frame.add(rdbMale);
        frame.add(btnChangeScreen);
        frame.add(btnCancel);
        frame.add(headerCourse);
        frame.add(controlPanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(640, 480);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        //JScrollPane
        showListCourses();

        btnCancel.addActionListener(this);
        btnChangeScreen.addActionListener(this);


    }


    private void showListCourses(){
        //List<Course> getList = db.getList();

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
        createPassword();
        controlPanel.add(courseListScrollPane);

    }
    public void cleanFields(){

    }

    private void createPassword() {
        createPassword2();
    }

    private String createPassword2() {
        nameS = nameStudentF.getText();
        lastnameS = lastnameF.getText();


        String sample = new String("ABCDEFGHIJKLMNOPQRSTUVWXYZ");

        String code1 = String.valueOf((sample.indexOf(nameS.charAt(0))) + 1);
        String code2 = String.valueOf((sample.indexOf(lastnameS.charAt(0))) + 1);
        String codeA = nameS.substring(0, 1).toUpperCase() + nameS.substring(1).toLowerCase();
        String codeB = lastnameS.substring(0, 1).toUpperCase() + lastnameS.substring(1).toLowerCase();
        String code3 = nameS.substring(0, 1).toLowerCase() + lastnameS.substring(0, 1).toLowerCase();
        String code4 = String.valueOf(nameS.length() + lastnameS.length());

        createPassword = String.join(code1,code2,code3,code4,codeA,codeB);

        return createPassword;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCancel) {
            this.frame.setVisible(false);
            Administrator administrator = new Administrator();
        } else if (e.getSource() ==  btnChangeScreen) {


        }
    }
}