package com.company;

/* 21545 - Hyeeun Lee
21520 - Liubov Eremenko
21314 - Nathalie Flores
 */

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;


public class Administrator  extends JFrame implements ActionListener { //action listener interface

    JFrame frame;
    JLabel branch, form,name, lastname, email, address, mobile, gender, headerCourse, courseID, courseName, coursePrice, courseComments, manageStudentFee, createTimetable;
    JTextField nameF, lastnameF, emailF, addressF, mobileF, courseIDF, courseNameF, coursePriceF, courseCommentsF;
    JPanel panelUp, panelRight, panelLeft,panelLef2, panelLeftDown,panelRightDown,controlPanel;
    JComboBox comboBoxBranch;
    ButtonGroup radioGroup, radioGroupGender;
    JRadioButton rdbCreateStudent, rdbCreateLecturer,rdbCreateAdministrative,rdbCreateCourse, rdbFemale, rdbMale;
    JButton btnAdd, btnUpdate, btnClear,btnLogOut, btnManaFee, btnTimetables;

    Database db = new Database();

    public Administrator() {

        Database db = new Database();
        //List<AdmFunctions> getList = db.getList();

        //create JFrame
        frame = new JFrame();

        JLabel background2 =new JLabel(new ImageIcon(getClass().getResource("adm_background.jpg")));
        frame.add(background2);

        form = new JLabel(" Type of Form to create:   ");
        form.setForeground(Color.WHITE);
        form.setBackground(new Color(120, 97, 201));
        form.setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 15));
        form.setBounds(0, 0, 210, 20);
        form.setOpaque(true);

        rdbCreateAdministrative = new JRadioButton("Administrative");
        rdbCreateAdministrative.setFont(new Font("Serif", Font.BOLD, 10));
        rdbCreateAdministrative.setBounds(240, 0, 95, 20);
        rdbCreateAdministrative.setForeground(Color.BLUE);
        rdbCreateLecturer = new JRadioButton("Lecturer");
        rdbCreateLecturer.setFont(new Font("Serif", Font.BOLD, 10));
        rdbCreateLecturer.setBounds(335, 0, 75, 20);
        rdbCreateLecturer.setForeground(Color.BLUE);
        rdbCreateStudent = new JRadioButton("Student");
        rdbCreateStudent.setFont(new Font("Serif", Font.BOLD, 10));
        rdbCreateStudent.setBounds(410, 0, 75, 20);
        rdbCreateStudent.setForeground(Color.BLUE);
        rdbCreateCourse = new JRadioButton("Course");
        rdbCreateCourse.setFont(new Font("Serif", Font.BOLD, 10));
        rdbCreateCourse.setBounds(485, 0, 75, 20);
        rdbCreateCourse.setForeground(Color.BLUE);

        branch = new JLabel(" Select Branch:  ");
        branch.setForeground(Color.WHITE);
        branch.setBackground(new Color(120, 97, 201));
        branch.setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 15));
        branch.setBounds(660, 0, 70, 20);
        branch.setOpaque(true);
        comboBoxBranch = new JComboBox();
        comboBoxBranch.setModel(new DefaultComboBoxModel(new String[] {"VGC-1", "VGC-2", "VGC-3"}));
        comboBoxBranch.setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 10));
        // comboBoxBranch.setBounds(7, 0, 75, 20);
        comboBoxBranch.setForeground(Color.BLUE);

        radioGroup = new ButtonGroup();
        radioGroup.add(rdbCreateStudent);
        radioGroup.add(rdbCreateAdministrative);
        radioGroup.add(rdbCreateLecturer);
        radioGroup.add(rdbCreateCourse);

        panelUp = new JPanel();
        panelUp.setBounds(0,10,700,30);
        panelUp.setBackground(new Color(120, 97, 201));
        panelUp.add(form,BorderLayout.CENTER);
        panelUp.add(rdbCreateAdministrative,BorderLayout.CENTER);
        panelUp.add(rdbCreateLecturer,BorderLayout.CENTER);
        panelUp.add(rdbCreateStudent,BorderLayout.CENTER);
        panelUp.add(rdbCreateCourse,BorderLayout.CENTER);
        panelUp.add(branch,BorderLayout.CENTER);
        panelUp.add(comboBoxBranch,BorderLayout.CENTER);

        background2.add(panelUp);

        name = new JLabel("Name");
        name.setBounds(10, 10, 50, 20);
        name.setFont(new Font("Serif", Font.BOLD, 15));
        name.setForeground(Color.WHITE);
        nameF = new JTextField();
        nameF.setBounds(80, 10, 180, 20);

        name = new JLabel("Name");
        name.setBounds(10, 10, 50, 20);
        name.setFont(new Font("Serif", Font.BOLD, 15));
        name.setForeground(Color.WHITE);
        nameF = new JTextField();
        nameF.setBounds(80, 10, 180, 20);
        lastname = new JLabel("Lastname");
        lastname.setFont(new Font("Serif", Font.BOLD, 15));
        lastname.setBounds(10, 50, 70, 20);
        lastname.setForeground(Color.WHITE);
        lastnameF = new JTextField("", 10);
        lastnameF.setBounds(80, 50, 180, 20);
        gender = new JLabel("Gender");
        gender.setBounds(10, 90, 60, 20);
        gender.setFont(new Font("Serif", Font.BOLD, 15));
        gender.setForeground(Color.WHITE);
        rdbFemale = new JRadioButton("Female");
        rdbFemale.setFont(new Font("Serif", Font.BOLD, 15));
        rdbFemale.setBounds(80, 90, 90, 20);
        rdbMale = new JRadioButton("Male");
        rdbMale.setFont(new Font("Serif", Font.BOLD, 15));
        rdbMale.setBounds(180, 90, 60, 20);
        radioGroupGender = new ButtonGroup();
        radioGroupGender.add(rdbFemale);
        radioGroupGender.add(rdbMale);
        address = new JLabel("Address");
        address.setBounds(10, 130, 60, 20);
        address.setFont(new Font("Serif", Font.BOLD, 15));
        address.setForeground(Color.WHITE);
        addressF = new JTextField("", 10);
        addressF.setBounds(80, 130, 180, 20);
        mobile = new JLabel("Mobile");
        mobile.setBounds(10, 170, 60, 20);
        mobile.setFont(new Font("Serif", Font.BOLD, 15));
        mobile.setForeground(Color.WHITE);
        mobileF= new JTextField("", 10);
        mobileF.setBounds(80, 170, 180, 20);
        email = new JLabel("Email");
        email.setBounds(10, 210, 50, 20);
        email.setFont(new Font("Serif", Font.BOLD, 15));
        email.setForeground(Color.WHITE);
        emailF = new JTextField("", 10);
        emailF.setBounds(80, 210, 180, 20);

        panelLeft = new JPanel();
        panelLeft.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(panelLeft);
        panelLeft.setLayout(null);
        panelLeft.setBounds(50,50,270,240);
        panelLeft.setBackground(Color.BLACK);
        panelLeft.add(name,BorderLayout.EAST);
        panelLeft.add(nameF,BorderLayout.EAST);
        panelLeft.add(lastname, BorderLayout.EAST);
        panelLeft.add(lastnameF,BorderLayout.EAST);
        panelLeft.add(email, BorderLayout.EAST);
        panelLeft.add(emailF,BorderLayout.EAST);
        panelLeft.add(address, BorderLayout.EAST);
        panelLeft.add(addressF,BorderLayout.EAST);
        panelLeft.add(mobile, BorderLayout.EAST);
        panelLeft.add(mobileF,BorderLayout.EAST);
        panelLeft.add(gender,BorderLayout.EAST);
        panelLeft.add(rdbFemale,BorderLayout.EAST);
        panelLeft.add(rdbMale,BorderLayout.EAST);

        background2.add(panelLeft);


        headerCourse = new JLabel("Courses Available");
        headerCourse.setBounds(10, 0, 175, 20);
        headerCourse.setFont(new Font("Serif", Font.BOLD, 15));
        headerCourse.setForeground(Color.WHITE);
        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        controlPanel.setBounds(30, 25, 200, 60);

        panelLef2 = new JPanel();
        panelLef2.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(panelLef2);
        panelLef2.setLayout(null);
        panelLef2.setBounds(50,290,270,100);
        panelLef2.setBackground(Color.BLACK);
        panelLef2.add(headerCourse,BorderLayout.EAST);
        panelLef2.add(controlPanel);

        background2.add(panelLef2);

        courseID = new JLabel("Course ID:");
        courseID.setBounds(10, 10, 100, 20);
        courseID.setFont(new Font("Serif", Font.BOLD, 15));
        courseID.setForeground(Color.WHITE);
        courseIDF = new JTextField();
        courseIDF.setBounds(110, 10, 150, 20);
        courseName = new JLabel("Course name:");
        courseName.setBounds(10, 50, 100, 20);
        courseName.setFont(new Font("Serif", Font.BOLD, 15));
        courseName.setForeground(Color.WHITE);
        courseNameF = new JTextField();
        courseNameF.setBounds(110, 50, 150, 20);
        coursePrice = new JLabel("Course price:");
        coursePrice.setBounds(10, 90, 100, 20);
        coursePrice.setFont(new Font("Serif", Font.BOLD, 15));
        coursePrice.setForeground(Color.WHITE);
        coursePriceF = new JTextField();
        coursePriceF.setBounds(110, 90, 150, 20);
        courseComments = new JLabel("Comments:");
        courseComments.setBounds(10, 130, 100, 20);
        courseComments.setFont(new Font("Serif", Font.BOLD, 15));
        courseComments .setForeground(Color.WHITE);
        courseCommentsF = new JTextField();
        courseCommentsF.setBounds(110, 130, 150, 50);

        panelRight = new JPanel();
        panelRight.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(panelRight);
        panelRight.setLayout(null);
        panelRight.setBounds(370,60,270,200);
        panelRight.setBackground(Color.BLACK);
        panelRight.add(courseID,BorderLayout.WEST);
        panelRight.add(courseIDF,BorderLayout.WEST);
        panelRight.add(courseName,BorderLayout.WEST);
        panelRight.add(courseNameF,BorderLayout.WEST);
        panelRight.add(coursePrice,BorderLayout.WEST);
        panelRight.add(coursePriceF,BorderLayout.WEST);
        panelRight.add(courseComments,BorderLayout.WEST);
        panelRight.add(courseCommentsF,BorderLayout.WEST);

        background2.add(panelRight);


        manageStudentFee = new JLabel(" Management Fees ");
        manageStudentFee.setBounds(5, 5, 150, 20);
        manageStudentFee.setFont(new Font("Serif", Font.BOLD, 15));
        manageStudentFee.setForeground(Color.WHITE);
        btnManaFee = new JButton();
        btnManaFee.setIcon(new ImageIcon(getClass().getResource("manaFee.jpg"))); // NOI18N
        btnManaFee.setBounds(20, 30, 100, 103);
        createTimetable = new JLabel(" Created Timetables ");
        createTimetable.setBounds(150, 5, 150, 20);
        createTimetable.setFont(new Font("Serif", Font.BOLD, 15));
        createTimetable.setForeground(Color.WHITE);
        btnTimetables = new JButton();
        btnTimetables.setIcon(new ImageIcon(getClass().getResource("calendarIcon.jpg"))); // NOI18N
        btnTimetables.setBounds(170, 30, 100, 103);
        panelLeftDown = new JPanel();
        panelLeftDown.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(panelLeftDown);
        panelLeftDown.setLayout(null);
        panelLeftDown.setBounds(40,395,300,150);
        panelLeftDown.setBackground(new Color(65, 46, 108));
        panelLeftDown.add(btnManaFee,BorderLayout.WEST);
        panelLeftDown.add(manageStudentFee,BorderLayout.WEST);
        panelLeftDown.add(btnTimetables,BorderLayout.WEST);
        panelLeftDown.add(createTimetable,BorderLayout.WEST);

        background2.add(panelLeftDown);

        //btnAdd, btnUpdate, btnClear,

        btnAdd = new JButton("Add Record");
        btnAdd.setFont(new Font("Serif", Font.BOLD, 10));
        btnAdd.setIcon(new ImageIcon(getClass().getResource("saveIcon.png")));
        btnAdd.setBounds(0, 0, 120, 20);

        btnUpdate = new JButton("Update");
        btnUpdate.setFont(new Font("Serif", Font.BOLD, 20));
        btnUpdate.setBounds(100, 350, 175, 50);

        panelRightDown = new JPanel();
        panelRightDown.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(panelRightDown);
        panelRightDown.setLayout(null);
        panelRightDown.setBounds(370,270,300,270);
        panelRightDown.setBackground(new Color(65, 46, 108));
        panelRightDown.add(btnAdd,BorderLayout.EAST);

        background2.add(panelRightDown);



        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.setSize(700, 600);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setTitle("ADMINISTRATIVE FUNCTIONS");
        frame.setVisible(true);

        showListCourses();

        btnUpdate.addActionListener(this);
        btnAdd.addActionListener(this);



    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    private void showListCourses(){
        final DefaultListModel courseName = new DefaultListModel();

        //courseName.getList = db.listOfListCourses());
        courseName.addElement("Computer science");
        courseName.addElement("Business");
        courseName.addElement("Mathematics II");
        courseName.addElement("Systems Operations");
        courseName.addElement("Mobile II");


        final JList courseList = new JList(courseName);
        courseList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        courseList.setSelectedIndex(0);
        courseList.setVisibleRowCount(2);
        courseList.setFixedCellHeight(20);
        courseList.setFixedCellWidth(175);

        JScrollPane courseListScrollPane = new JScrollPane(courseList);

        controlPanel.add(courseListScrollPane);

    }




}
