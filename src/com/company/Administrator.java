package com.company;

/* 21545 - Hyeeun Lee
21520 - Liubov Eremenko
21314 - Nathalie Flores
 */

import javax.naming.directory.SearchResult;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.*;


public class Administrator  extends JFrame implements ActionListener { //action listener interface


    JFrame frame;
    JLabel branch, form, title1,title2,title3,  name, lastname, email, address, gender, headerCourse, courseID, courseName, coursePrice, courseComments, manageStudentFee, createTimetable,search,amount;
    JTextField nameF, lastnameF, emailF, addressF, courseIDF, courseNameF, coursePriceF, courseCommentsF, searchF, amountF;
    JPanel panelUp, panelRight, panelLeft, panelLeft1, panelLef2, panelLeftDown, panelRightDown, controlPanel, panelButtons;
    JComboBox<String> comboBoxBranch;
    ButtonGroup radioGroup, radioGroupGender, radioGroupFee;
    JRadioButton rdbCreateStudent, rdbCreateLecturer, rdbCreateAdministrative, rdbCreateCourse, rdbFemale, rdbMale,rdbFullyP,rdbInstP;
    JButton btnAdd, btnUpdate, btnClear, btnLogOut, btnManaFee, btnTimetables, btnSearh;
    JList courseList = new JList();
    static JTable table;

    Database db = new Database();

    String nameS, lastnameS, emailS, addressS, createId , branchS, selectionGender, password, searchS;
    String course_id, course_name, course_price, course_comments, branch_Bno, couSFees ;
    Calendar today;
    int year = 0;


    public Administrator() {
        createdForms();
        Database db = new Database();
        addBranchInComboBox();
        showListCourses();
        clearAllControls();

    }

    public void createdForms(){

        //create JFrame
        frame = new JFrame();

        JLabel background2 = new JLabel(new ImageIcon(getClass().getResource("adm_background.jpg")));
        frame.add(background2);

        form = new JLabel("YOU MUST SELECT     "+"     1.Type of Form:   ");
        form.setForeground(Color.WHITE);
        form.setBackground(new Color(210, 31, 78));
        form.setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 20));
        form.setBounds(0, 0, 210, 20);
        form.setOpaque(true);

        rdbCreateAdministrative = new JRadioButton("Administrative");
        rdbCreateAdministrative.setFont(new Font("Serif", Font.BOLD, 15));
        rdbCreateAdministrative.setBounds(240, 0, 95, 20);
        rdbCreateAdministrative.setForeground(Color.BLUE);
        rdbCreateLecturer = new JRadioButton("Lecturer");
        rdbCreateLecturer.setFont(new Font("Serif", Font.BOLD, 15));
        rdbCreateLecturer.setBounds(335, 0, 75, 20);
        rdbCreateLecturer.setForeground(Color.BLUE);
        rdbCreateStudent = new JRadioButton("Student");
        rdbCreateStudent.setFont(new Font("Serif", Font.BOLD, 15));
        rdbCreateStudent.setBounds(410, 0, 75, 20);
        rdbCreateStudent.setForeground(Color.BLUE);
        rdbCreateCourse = new JRadioButton("Course");
        rdbCreateCourse.setFont(new Font("Serif", Font.BOLD, 15));
        rdbCreateCourse.setBounds(485, 0, 75, 20);
        rdbCreateCourse.setForeground(Color.BLUE);
        radioGroup = new ButtonGroup();
        radioGroup.add(rdbCreateStudent);
        radioGroup.add(rdbCreateAdministrative);
        radioGroup.add(rdbCreateLecturer);
        radioGroup.add(rdbCreateCourse);


        branch = new JLabel(" 2.Branch:  ");
        branch.setForeground(Color.WHITE);
        branch.setBackground(new Color(210, 31, 78));
        branch.setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 20));
        branch.setBounds(660, 0, 70, 20);
        branch.setOpaque(true);
        comboBoxBranch = new JComboBox<String>();
        comboBoxBranch.setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 15));
        comboBoxBranch.setBounds(7, 0, 75, 20);
        comboBoxBranch.setForeground(Color.BLUE);
        comboBoxBranch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comboBoxBranch.getItemCount() > 0 && comboBoxBranch.getSelectedItem() != null) {
                    if (comboBoxBranch.getSelectedItem().equals("VGC-1") == true) {
                        branchS = "VGC-1";
                    } else if (comboBoxBranch.getSelectedItem().equals("VGC-2") == true) {
                        branchS = "VGC-2";
                    } else if (comboBoxBranch.getSelectedItem().equals("VGC-3") == true) {
                        branchS = "VGC-3";
                    }
                }
            }
        });

        panelUp = new JPanel();
        panelUp.setBounds(0, 10, 1000, 40);
        panelUp.setBackground(new Color(210, 31, 78));
        panelUp.add(form, BorderLayout.CENTER);
        panelUp.add(rdbCreateAdministrative, BorderLayout.CENTER);
        panelUp.add(rdbCreateLecturer, BorderLayout.CENTER);
        panelUp.add(rdbCreateStudent, BorderLayout.CENTER);
        panelUp.add(rdbCreateCourse, BorderLayout.CENTER);
        panelUp.add(branch, BorderLayout.CENTER);
        panelUp.add(comboBoxBranch, BorderLayout.CENTER);

        background2.add(panelUp);

        title1 = new JLabel("Adm/Lecturer/Student Form");
        title1.setBounds(10, 5, 250, 20);
        title1.setFont(new Font("Serif", Font.BOLD, 20));
        title1.setForeground(Color.PINK);
        name = new JLabel("Name");
        name.setBounds(10, 50, 50, 20);
        name.setFont(new Font("Serif", Font.BOLD, 15));
        name.setForeground(Color.WHITE);
        nameF = new JTextField();
        nameF.setBounds(80, 50, 180, 20);
        lastname = new JLabel("Lastname");
        lastname.setFont(new Font("Serif", Font.BOLD, 15));
        lastname.setBounds(10, 90, 70, 20);
        lastname.setForeground(Color.WHITE);
        lastnameF = new JTextField("", 10);
        lastnameF.setBounds(80, 90, 180, 20);
        gender = new JLabel("Gender");
        gender.setBounds(10, 130, 60, 20);
        gender.setFont(new Font("Serif", Font.BOLD, 15));
        gender.setForeground(Color.WHITE);
        rdbFemale = new JRadioButton("Female");
        rdbFemale.setFont(new Font("Serif", Font.BOLD, 15));
        rdbFemale.setBounds(80, 130, 90, 20);
        rdbMale = new JRadioButton("Male");
        rdbMale.setFont(new Font("Serif", Font.BOLD, 15));
        rdbMale.setBounds(180, 130, 60, 20);
        radioGroupGender = new ButtonGroup();
        radioGroupGender.add(rdbFemale);
        radioGroupGender.add(rdbMale);
        address = new JLabel("Address");
        address.setBounds(10, 170, 60, 20);
        address.setFont(new Font("Serif", Font.BOLD, 15));
        address.setForeground(Color.WHITE);
        addressF = new JTextField("", 10);
        addressF.setBounds(80, 170, 180, 20);
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
        panelLeft.setBounds(20, 60, 275, 250);
        panelLeft.setBackground(Color.BLACK);
        panelLeft.add(title1, BorderLayout.EAST);
        panelLeft.add(name, BorderLayout.EAST);
        panelLeft.add(nameF, BorderLayout.EAST);
        panelLeft.add(lastname, BorderLayout.EAST);
        panelLeft.add(lastnameF, BorderLayout.EAST);
        panelLeft.add(email, BorderLayout.EAST);
        panelLeft.add(emailF, BorderLayout.EAST);
        panelLeft.add(address, BorderLayout.EAST);
        panelLeft.add(addressF, BorderLayout.EAST);
        panelLeft.add(gender, BorderLayout.EAST);
        panelLeft.add(rdbFemale, BorderLayout.EAST);
        panelLeft.add(rdbMale, BorderLayout.EAST);

        background2.add(panelLeft);


        amount = new JLabel("Amount Fee Paid");
        amount.setBounds(10, 10, 175, 20);
        amount.setFont(new Font("Serif", Font.BOLD, 15));
        amount.setForeground(Color.WHITE);
        amountF = new JTextField();
        amountF.setBounds(150, 10, 100, 20);
        rdbFullyP= new JRadioButton("Fully paid");
        rdbFullyP.setFont(new Font("Serif", Font.BOLD, 12));
        rdbFullyP.setBounds(20, 40, 100, 25);
        rdbInstP= new JRadioButton("Instalments paid");
        rdbInstP.setFont(new Font("Serif", Font.BOLD, 12));
        rdbInstP.setBounds(130, 40, 120, 25);
        radioGroupFee= new ButtonGroup();
        radioGroupFee.add(rdbFullyP);
        radioGroupFee.add(rdbInstP);
        headerCourse = new JLabel("Courses Available to enroll");
        headerCourse.setBounds(10, 90, 200, 20);
        headerCourse.setFont(new Font("Serif", Font.BOLD, 15));
        headerCourse.setForeground(Color.WHITE);
        panelLeft1 = new JPanel();
        panelLeft1.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(panelLeft1);
        panelLeft1.setLayout(null);
        panelLeft1.setBounds(20, 325, 285, 110);
        panelLeft1.setBackground(Color.black);
        panelLeft1.add(amount);
        panelLeft1.add(amountF);
        panelLeft1.add(rdbFullyP);
        panelLeft1.add(rdbInstP);
        panelLeft1.add(headerCourse);
        background2.add(panelLeft1);

        panelLef2 = new JPanel();
        panelLef2.setLayout(new FlowLayout());
        panelLef2.setBounds(20, 430, 285, 100);
        panelLef2.setBackground(new Color(38,0,77));

        background2.add(panelLef2);


        title2 = new JLabel("Course Form");
        title2.setBounds(10, 5, 200, 20);
        title2.setFont(new Font("Serif", Font.BOLD, 20));
        title2.setForeground(Color.PINK);
        courseID = new JLabel("Course ID:");
        courseID.setBounds(10, 50, 100, 20);
        courseID.setFont(new Font("Serif", Font.BOLD, 15));
        courseID.setForeground(Color.WHITE);
        courseIDF = new JTextField();
        courseIDF.setBounds(110, 50, 150, 20);
        courseName = new JLabel("Course name:");
        courseName.setBounds(10, 90, 100, 20);
        courseName.setFont(new Font("Serif", Font.BOLD, 15));
        courseName.setForeground(Color.WHITE);
        courseNameF = new JTextField();
        courseNameF.setBounds(110, 90, 150, 20);
        coursePrice = new JLabel("Course price:");
        coursePrice.setBounds(10, 130, 100, 20);
        coursePrice.setFont(new Font("Serif", Font.BOLD, 15));
        coursePrice.setForeground(Color.WHITE);
        coursePriceF = new JTextField();
        coursePriceF.setBounds(110, 130, 150, 20);
        courseComments = new JLabel("Comments:");
        courseComments.setBounds(10, 170, 100, 20);
        courseComments.setFont(new Font("Serif", Font.BOLD, 15));
        courseComments.setForeground(Color.WHITE);
        courseCommentsF = new JTextField();
        courseCommentsF.setBounds(110, 170, 150, 50);

        panelRight = new JPanel();
        panelRight.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(panelRight);
        panelRight.setLayout(null);
        panelRight.setBounds(320, 60, 280, 250);
        panelRight.setBackground(Color.BLACK);
        panelRight.add(title2, BorderLayout.WEST);
        panelRight.add(courseID, BorderLayout.WEST);
        panelRight.add(courseIDF, BorderLayout.WEST);
        panelRight.add(courseName, BorderLayout.WEST);
        panelRight.add(courseNameF, BorderLayout.WEST);
        panelRight.add(coursePrice, BorderLayout.WEST);
        panelRight.add(coursePriceF, BorderLayout.WEST);
        panelRight.add(courseComments, BorderLayout.WEST);
        panelRight.add(courseCommentsF, BorderLayout.WEST);

        background2.add(panelRight);


        title3 = new JLabel("In order to update or delete");
        title3.setBounds(10, 10, 300, 25);
        title3.setFont(new Font("Serif", Font.BOLD, 25));
        title3.setForeground(Color.PINK);
        search = new JLabel("Unique ID:");
        search.setBounds(10, 50, 100, 20);
        search.setFont(new Font("Serif", Font.BOLD, 15));
        search.setForeground(Color.WHITE);
        searchF = new JTextField();
        searchF.setBounds(100, 50, 100, 20);
        btnSearh = new JButton("Search");
        btnSearh.setFont(new Font("Serif", Font.BOLD, 17));
        //btnSearh.setIcon(new ImageIcon(getClass().getResource("searchIcon.png")));
        btnSearh.setBounds(225, 50, 90, 30);

        controlPanel = new JPanel();
        controlPanel.setBounds(10,90,330,300);
        controlPanel.setForeground(Color.LIGHT_GRAY);
        panelRightDown = new JPanel();
        panelRightDown.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(panelRightDown);
        panelRightDown.setLayout(null);
        panelRightDown.setBounds(635, 60, 350, 410);
        panelRightDown.setBackground(Color.BLACK);
        panelRightDown.add(search, BorderLayout.EAST);
        panelRightDown.add(searchF, BorderLayout.EAST);
        panelRightDown.add(title3,BorderLayout.EAST);
        panelRightDown.add(btnSearh,BorderLayout.EAST);
        panelRightDown.add(controlPanel, BorderLayout.EAST);
        // panelRightDown.add(controlPanel);
        background2.add(panelRightDown);

        manageStudentFee = new JLabel(" Management Fees ");
        manageStudentFee.setBounds(5, 5, 150, 20);
        manageStudentFee.setFont(new Font("Serif", Font.BOLD, 15));
        manageStudentFee.setForeground(Color.WHITE);
        btnManaFee = new JButton();
        btnManaFee.setIcon(new ImageIcon(getClass().getResource("manaFee.jpg"))); // NOI18N
        btnManaFee.setBounds(20, 30, 100, 103);
        createTimetable = new JLabel(" Created Timetables ");
        createTimetable.setBounds(160, 5, 150, 20);
        createTimetable.setFont(new Font("Serif", Font.BOLD, 15));
        createTimetable.setForeground(Color.WHITE);
        btnTimetables = new JButton();
        btnTimetables.setIcon(new ImageIcon(getClass().getResource("calendarIcon.jpg"))); // NOI18N
        btnTimetables.setBounds(170, 30, 100, 103);
        panelLeftDown = new JPanel();
        panelLeftDown.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(panelLeftDown);
        panelLeftDown.setLayout(null);
        panelLeftDown.setBounds(320, 320, 300, 150);
        panelLeftDown.setBackground(new Color(38,0,77));
        panelLeftDown.add(btnManaFee, BorderLayout.WEST);
        panelLeftDown.add(manageStudentFee, BorderLayout.WEST);
        panelLeftDown.add(btnTimetables, BorderLayout.WEST);
        panelLeftDown.add(createTimetable, BorderLayout.WEST);

        background2.add(panelLeftDown);

        btnAdd = new JButton(" Add ");
        btnAdd.setFont(new Font("Serif", Font.BOLD, 10));
        btnAdd.setIcon(new ImageIcon(getClass().getResource("saveIcon.png")));
        btnAdd.setBounds(0, 0, 100, 40);
        btnUpdate = new JButton("Update");
        btnUpdate.setFont(new Font("Serif", Font.BOLD, 10));
        btnUpdate.setIcon(new ImageIcon(getClass().getResource("updateIcon.png")));
        btnUpdate.setBounds(100, 0, 100, 40);
        btnClear = new JButton("Delete");
        btnClear.setFont(new Font("Serif", Font.BOLD, 10));
        btnClear.setIcon(new ImageIcon(getClass().getResource("eraseIcon.png")));
        btnClear.setBounds(200, 0, 100, 40);
        btnLogOut = new JButton("Log out");
        btnLogOut.setBounds(300, 0, 100, 40);
        btnLogOut.setForeground(Color.RED);
        panelButtons = new JPanel();
        panelButtons.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(panelButtons);
        panelButtons.setLayout(null);
        panelButtons.setBounds(550, 490, 400, 40);
        panelButtons.add(btnAdd);
        panelButtons.add(btnUpdate);
        panelButtons.add(btnClear);
        panelButtons.add(btnLogOut);

        background2.add(panelButtons);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.setSize(1000, 600);
        frame.setLocationRelativeTo(null);
        //frame.setResizable(false);
        frame.setTitle("ADMINISTRATIVE FUNCTIONS");
        frame.setVisible(true);


        btnLogOut.addActionListener(this);

        btnManaFee.addActionListener(this);
        btnTimetables.addActionListener(this);


        btnAdd.addActionListener(this);
        btnClear.addActionListener(this);
        btnSearh.addActionListener(this);
        btnUpdate.addActionListener(this);



    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnLogOut) {
            this.frame.setVisible(false);
        } else if (e.getSource() == btnManaFee) {
            this.frame.setVisible(false);
            ManagementFees managementFees = new ManagementFees();
        } else if (e.getSource() == btnTimetables) {
            //this.frame.setVisible(false);
        } else if (e.getSource() == btnAdd) {
            if (rdbCreateCourse.isSelected()) {
                try {
                    db.getCourseCreateInfo(getCourseIdS(), getCourseNameS(), getCoursePriceS(), getCourseCommentsS(), getBranch());
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                getAddToDBmessage1();
                clearAllControls();

            } else if (rdbCreateAdministrative.isSelected()) {
                try {
                    db.getAdmInfo(getCreateId(), getCreatePassword(), getNameS(), getLastnameS(), getEmailS(), getAddressS(), getGender(), getBranch());
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                getAddToDBmessage2();
                clearAllControls();

            } else if (rdbCreateLecturer.isSelected()) {
                try {
                    db.getLetInfo(getCreateId(), getCreatePassword(), getNameS(), getLastnameS(), getEmailS(), getAddressS(), getGender(), getBranch());
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                getAddToDBmessage2();
                clearAllControls();

            } else if (rdbCreateStudent.isSelected()) {
                try {
                    db.getStuInfo(getCreateId(), getCreatePassword(), getNameS(), getLastnameS(), getEmailS(), getAddressS(), getGender(), getStudentBranch(),getStudentCourse());
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                getAddToDBmessage2();
                clearAllControls();
            }
        } else if (e.getSource() == btnClear) {


        }else if (e.getSource() == btnUpdate){

        }else if (e.getSource() == btnSearh){




        }

    }



    public void addBranchInComboBox() {
        try {

            String query = "select * from branch";
            db.pstmt = db.conn.prepareStatement(query);
            db.rs = db.pstmt.executeQuery();

            while (db.rs.next()) {

                comboBoxBranch.addItem(db.rs.getString("branch_Bno"));
            }
        }catch (SQLException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }



    public void clearAllControls() {
        try {
            nameF.setText("");
            lastnameF.setText("");
            emailF.setText("");
            addressF.setText("");
            courseIDF.setText("");
            courseNameF.setText("");
            coursePriceF.setText("");
            courseCommentsF.setText("");
            comboBoxBranch.setSelectedIndex(-1);
            radioGroup.clearSelection();
            radioGroupGender.clearSelection();


        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }


    public String getBranch(){
        branch_Bno = branchS;
        return branch_Bno;
    }

    public String getCourseIdS() {
        course_id = courseIDF.getText();
        return course_id;
    }

    public String getCourseNameS() {
        course_name = courseNameF.getText();
        return course_name;
    }

    public String getCoursePriceS() {
        course_price= coursePriceF.getText();
        return course_price;
    }

    public String getCourseCommentsS() {
        course_comments = courseCommentsF.getText();
        return course_comments;
    }


    public String getNameS() {
        nameS = nameF.getText();
        return nameS;
    }

    public String getLastnameS() {
        lastnameS = lastnameF.getText();
        return lastnameS;
    }
    public String getEmailS() {
        emailS = emailF.getText();
        return emailS;
    }
    public String getAddressS() {
        addressS = addressF.getText();
        return addressS;
    }

    public String getGender() {
        try{

            if (this.rdbFemale.isSelected()) {
                selectionGender = "F";
            } else if (this.rdbMale.isSelected()) {
                selectionGender = "M";
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }

        return selectionGender;
    }

    public String getCreatePassword() {

        nameS = nameF.getText();
        lastnameS = lastnameF.getText();

        try{
            int iFirst = 1, iLast = 1;

            char[] letterArray = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
                    'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

            System.out.println();
            for (int i = 0; i < letterArray.length; i++) {
                if (Character.toLowerCase(nameS.charAt(0)) == letterArray[i]) {
                    iFirst += i;
                }
                if (Character.toLowerCase(lastnameS.charAt(0)) == letterArray[i]) {
                    iLast += i;
                }

            }
            password = String.format("%c%c-%s-%02d%02d", Character.toLowerCase(nameS.charAt(0)), Character.toLowerCase(lastnameS.charAt(0)),
                    (nameS.length() + lastnameS.length()), iFirst, iLast);

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return password;

    }

    public String getCreateId() {
        nameS = nameF.getText();
        lastnameS = lastnameF.getText();

        today = Calendar.getInstance();
        year = today.get(Calendar.YEAR);

        try{

            if (this.rdbCreateAdministrative.isSelected()){
                String studentId = "Adm-";
                String nam1 = nameS.substring(0, 1).toUpperCase();
                String nameNumber = String.valueOf(nameS.length() + lastnameS.length());
                createId = (studentId+nam1+nameNumber);
            } else if (this.rdbCreateLecturer.isSelected()) {
                String studentId = "lect-";
                String nam1 = nameS.substring(0, 1).toUpperCase();
                String nameNumber = String.valueOf(nameS.length() + lastnameS.length());
                createId = (studentId+nam1+nameNumber);

            }else if (this.rdbCreateStudent.isSelected()) {
                String studentId = "st"+year+"-";
                String nam1 = nameS.substring(0, 1).toUpperCase();
                String nameNumber = String.valueOf(nameS.length() + lastnameS.length());
                createId = (studentId+nam1+nameNumber);
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return createId;

    }

    public void getAddToDBmessage1(){
        JOptionPane.showMessageDialog(frame,
                "New information SAVE in the DateBases",
                "Successfully connection",
                JOptionPane.INFORMATION_MESSAGE );
    }

    public void getAddToDBmessage2() {

        String messagePersonInfo = "\n *** INFORMATION ****"
                + "\n User ID: " + "  " + createId
                + "\n **PASSWORD: " + "  " + password
                + "\n Name: " + " " + nameS + " " + lastnameS
                + "\n Email: " + "  " + emailS
                + "\n Address: " + "  " + addressS;

        JOptionPane.showMessageDialog(frame,
                "NEW USER CREATE" +
                        messagePersonInfo,
                "INFORMATION SAVE DATABASES",
                JOptionPane.INFORMATION_MESSAGE);

    }


    private void showListCourses(){

        final DefaultListModel<String> courseNameModel = new DefaultListModel<>();

        try {

            String query = "select * from course";
            db.pstmt = db.conn.prepareStatement(query);
            db.rs = db.pstmt.executeQuery();

            while (db.rs.next()) {
                courseNameModel.addElement(db.rs.getString("course_name"));
            }

        }catch (SQLException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        courseList = new JList(courseNameModel);
        courseList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        courseList.setSelectedIndex(0);
        courseList.setVisibleRowCount(3);
        courseList.setFixedCellHeight(30);
        courseList.setFixedCellWidth(270);

        JScrollPane courseListScrollPane = new JScrollPane(courseList);
        panelLef2.add(courseListScrollPane);



    }

    public String getStudentCourse(){
        couSFees = (String)db.getCourseIDInfo(CIDFee());

        return couSFees;

    }

    public String CIDFee(){
        String data = new String();
        if (courseList.getSelectedIndex() != -1) {
            data= (String) courseList.getSelectedValue();

        }

        return data;
    }

    public String getStudentBranch(){
        getStudentCourse();
        String studentBranch = (String)db.getStudentBranchWithCourseEnroll(couSFees);

        return studentBranch;
    }

    public String getIdToSearch() {
        searchS = searchF.getText();
        return searchS;
    }



 /* ***** WORKING ON NATHALIE FLORES



    public boolean ifCourseIdExist(String course_id) {
        db.SQL = "select course_id from course where course_id=?";
        try {
            db.pstmt = db.conn.prepareStatement(db.SQL);
            db.pstmt.setString(1, course_id);
            db.rs = db.pstmt.executeQuery();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        try {
            // check for any facultyID already in use or not
            // check for any record, if the first call to next() returns false then there is
            // no data in the ResultSet.
            if (db.rs.next() == false) {
                //JavaWindowsFormUserInformers
                JOptionPane.showMessageDialog(frame,
                        "This Course ID already exists, please choose the other one." ,
                        "POSSIBLE DUPLICATE IN DB",
                        JOptionPane.WARNING_MESSAGE);

                return false;
            } else
                return true;
        } catch (SQLException e) {

            e.printStackTrace();

        }
        return false;
    }


    public void showTableData(){

        String[] columnNames = new String[] {"ID", "Password", "Name"};
        Object[][] data =  null;


        //DefaultTableModel model = new DefaultTableModel();
        //model.setColumnIdentifiers(columnNames);

        table = new JTable(data, columnNames);
        //table.setModel(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);

        JScrollPane scroll = new JScrollPane(table);
        scroll.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        String textvalue = searchF.getText();
        String roll= "";
        String name= "";
        String cl = "";
        try
        {
            db.SQL = "select student_id,student_password, student_name from student where student_id = ?";
            db.pstmt = db.conn.prepareStatement(db.SQL);
            db.pstmt.setString(1,textvalue);
            db.rs = db.pstmt.executeQuery();
            if(db.rs.next())
            {
                if (db.rs.getString(1).equals(db.rs.getString("student_id"))) {
                    roll = db.rs.getString("student_id");
                    name = db.rs.getString("student_password");
                    cl = db.rs.getString("student_name");
                    model.addRow(new Object[]{roll, name, cl,});
                } else {
                    System.out.println("!!");

                }

            }

        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",
                    JOptionPane.ERROR_MESSAGE);
        }

        this.controlPanel.add(scroll);
        controlPanel.add(table.getTableHeader(), BorderLayout.NORTH);
        controlPanel.add(table, BorderLayout.CENTER);

    }



     */


}