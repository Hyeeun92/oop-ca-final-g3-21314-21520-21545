package com.company;

/* 21545 - Hyeeun Lee
21520 - Liubov Eremenko
21314 - Nathalie Flores
 */

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;


public class Administrator  extends JFrame implements ActionListener { //action listener interface

    JFrame frame;
    JLabel branch, form, name, lastname, email, address, gender, headerCourse, courseID, courseName, coursePrice, courseComments, manageStudentFee, createTimetable;
    JTextField nameF, lastnameF, emailF, addressF, courseIDF, courseNameF, coursePriceF, courseCommentsF;
    JPanel panelUp, panelRight, panelLeft, panelLef2, panelLeftDown, panelRightDown, controlPanel, panelLogOut;
    JComboBox<String> comboBoxBranch;
    ButtonGroup radioGroup, radioGroupGender;
    JRadioButton rdbCreateStudent, rdbCreateLecturer, rdbCreateAdministrative, rdbCreateCourse, rdbFemale, rdbMale;
    JButton btnAdd, btnUpdate, btnClear, btnLogOut, btnManaFee, btnTimetables;
    JList listCourses;
    JScrollPane scrollPaneCourses;

    Database db = new Database();

    String nameS, lastnameS, emailS, addressS, createId , branchS, selectionGender, password ;
    // String lecture_id, lecture_password, lecture_name, lecture_Lname, lecture_email, lecture_address, lecture_gender;

    // String  student_name, student_Lname, studentr_email, student_address;
    String course_id, course_name, course_price, course_comments, branch_Bno;

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
        radioGroup = new ButtonGroup();
        radioGroup.add(rdbCreateStudent);
        radioGroup.add(rdbCreateAdministrative);
        radioGroup.add(rdbCreateLecturer);
        radioGroup.add(rdbCreateCourse);


        branch = new JLabel(" 2.Branch:  ");
        branch.setForeground(Color.WHITE);
        branch.setBackground(new Color(210, 31, 78));
        branch.setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 15));
        branch.setBounds(660, 0, 70, 20);
        branch.setOpaque(true);
        comboBoxBranch = new JComboBox<String>();
        comboBoxBranch.setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 10));
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
        panelUp.setBounds(0, 10, 900, 30);
        panelUp.setBackground(new Color(210, 31, 78));
        panelUp.add(form, BorderLayout.CENTER);
        panelUp.add(rdbCreateAdministrative, BorderLayout.CENTER);
        panelUp.add(rdbCreateLecturer, BorderLayout.CENTER);
        panelUp.add(rdbCreateStudent, BorderLayout.CENTER);
        panelUp.add(rdbCreateCourse, BorderLayout.CENTER);
        panelUp.add(branch, BorderLayout.CENTER);
        panelUp.add(comboBoxBranch, BorderLayout.CENTER);

        background2.add(panelUp);

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
        email = new JLabel("Email");
        email.setBounds(10, 170, 50, 20);
        email.setFont(new Font("Serif", Font.BOLD, 15));
        email.setForeground(Color.WHITE);
        emailF = new JTextField("", 10);
        emailF.setBounds(80, 170, 180, 20);

        panelLeft = new JPanel();
        panelLeft.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(panelLeft);
        panelLeft.setLayout(null);
        panelLeft.setBounds(20, 60, 275, 200);
        panelLeft.setBackground(Color.BLACK);
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

        headerCourse = new JLabel("Courses Available to enroll");
        headerCourse.setBounds(10, 0, 200, 20);
        headerCourse.setFont(new Font("Serif", Font.BOLD, 15));
        headerCourse.setForeground(Color.WHITE);

        panelLef2 = new JPanel();
        //panelLef2.setBorder(new EmptyBorder(5, 5, 5, 5));
        //setContentPane(panelLef2);
        //panelLef2.setLayout(null);
        panelLef2.setLayout(new FlowLayout());
        panelLef2.setBounds(20, 270, 285, 270);
        panelLef2.setBackground(new Color(65, 46, 108));

        //listCourse();
        //scrollPaneCourses = new JScrollPane();
        //scrollPaneCourses.setBounds(0, 30, 400, 300);
        //scrollPaneCourses.add(listCourses);


        //panelLef2.add(headerCourse);
        //panelLef2.add(scrollPaneCourses);

        // panelLef2.add();
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
        courseComments.setForeground(Color.WHITE);
        courseCommentsF = new JTextField();
        courseCommentsF.setBounds(110, 130, 150, 50);

        panelRight = new JPanel();
        panelRight.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(panelRight);
        panelRight.setLayout(null);
        panelRight.setBounds(580, 60, 270, 200);
        panelRight.setBackground(Color.BLACK);
        panelRight.add(courseID, BorderLayout.WEST);
        panelRight.add(courseIDF, BorderLayout.WEST);
        panelRight.add(courseName, BorderLayout.WEST);
        panelRight.add(courseNameF, BorderLayout.WEST);
        panelRight.add(coursePrice, BorderLayout.WEST);
        panelRight.add(coursePriceF, BorderLayout.WEST);
        panelRight.add(courseComments, BorderLayout.WEST);
        panelRight.add(courseCommentsF, BorderLayout.WEST);

        background2.add(panelRight);


        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        controlPanel.setBounds(30, 25, 200, 60);

        panelRightDown = new JPanel();
        panelRightDown.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(panelRightDown);
        panelRightDown.setLayout(null);
        panelRightDown.setBounds(580, 270, 285, 270);
        panelLef2.setBackground(new Color(65, 46, 108));
        //panelRightDown.add(headerCourse, BorderLayout.EAST);
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
        createTimetable.setBounds(5, 150, 150, 20);
        createTimetable.setFont(new Font("Serif", Font.BOLD, 15));
        createTimetable.setForeground(Color.WHITE);
        btnTimetables = new JButton();
        btnTimetables.setIcon(new ImageIcon(getClass().getResource("calendarIcon.jpg"))); // NOI18N
        btnTimetables.setBounds(20, 180, 100, 103);
        panelLeftDown = new JPanel();
        panelLeftDown.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(panelLeftDown);
        panelLeftDown.setLayout(null);
        panelLeftDown.setBounds(380, 60, 150, 300);
        panelLeftDown.setBackground(new Color(65, 46, 108));
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
        btnClear.setBounds(0, 40, 100, 40);
        btnLogOut = new JButton("Log out");
        btnLogOut.setBounds(100, 40, 100, 40);
        panelLogOut = new JPanel();
        panelLogOut.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(panelLogOut);
        panelLogOut.setLayout(null);
        panelLogOut.setBounds(350, 400, 200, 80);
        panelLogOut.add(btnAdd);
        panelLogOut.add(btnUpdate);
        panelLogOut.add(btnClear);
        panelLogOut.add(btnLogOut);

        background2.add(panelLogOut);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.setSize(900, 600);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setTitle("ADMINISTRATIVE FUNCTIONS");
        frame.setVisible(true);


        btnLogOut.addActionListener(this);

        btnManaFee.addActionListener(this);
        btnTimetables.addActionListener(this);


        btnAdd.addActionListener(this);
        btnClear.addActionListener(this);


        btnUpdate.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnLogOut) {
            this.frame.setVisible(false);
            //LoginPage loginPage = new LoginPage();
        } else if (e.getSource() == btnManaFee) {
            this.frame.setVisible(false);
            ManagementFees managementFees = new ManagementFees();
        } else if (e.getSource() == btnTimetables) {
            this.frame.setVisible(false);

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
                //db.getStudInfo(getCreateId(),getCreatePassword(),getNameS(),getLastnameS(),getEmailS(),getAddressS(),getGender(),getBranch());
                getAddToDBmessage2();
                clearAllControls();
            }
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

        try{

            if (this.rdbCreateAdministrative.isSelected()){
                String studentId = "Adm-";
                String nam1 = nameS.substring(0, 1).toUpperCase();
                String nameNumber = String.valueOf(nameS.length() + lastnameS.length());
                createId = (studentId+nam1+nameNumber);
            } else if (this.rdbCreateLecturer.isSelected()) {
                String studentId = "st21-";
                String nam1 = nameS.substring(0, 1).toUpperCase();
                String nameNumber = String.valueOf(nameS.length() + lastnameS.length());
                createId = (studentId+nam1+nameNumber);

            }else if (this.rdbCreateStudent.isSelected()) {
                String studentId = "lect-";
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
                + "\n Student number: " + "  " + createId
                + "\n *Temporarily PASSWORD: " + "  " + password
                + "\n Name: " + " " + nameS + " " + lastnameS
                + "\n Email: " + "  " + emailS
                + "\n Address: " + "  " + addressS
                + "\n Gender: " + "  " + selectionGender;

        JOptionPane.showMessageDialog(frame,
                "NEW USER CREATE" +
                        messagePersonInfo,
                "INFORMATION SAVE DATABASES",
                JOptionPane.INFORMATION_MESSAGE);

    }

    private void showListCourses(){


        final DefaultListModel<String> courseName = new DefaultListModel<>();

        try {

            String query = "select * from course";
            db.pstmt = db.conn.prepareStatement(query);
            db.rs = db.pstmt.executeQuery();

            while (db.rs.next()) {

                courseName.addElement(db.rs.getString("course_name"));
            }

        }catch (SQLException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        final JList courseList = new JList(courseName);
        courseList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        courseList.setSelectedIndex(0);
        courseList.setVisibleRowCount(3);
        courseList.setFixedCellHeight(30);
        courseList.setFixedCellWidth(200);

        JScrollPane courseListScrollPane = new JScrollPane(courseList);
        panelLef2.add(courseListScrollPane);

    }




    /*  WORKING ON **** Nathalie
    public final void listCourse() {

        String[] columnNames = {"Course ID", "Course Name", "Course Price", "Course Comments", "Branch"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        tableModel.setColumnIdentifiers(columnNames);
        tableModel.setRowCount(0);

        JTable table = new JTable(tableModel);
        this.add(new JScrollPane(table));

        try {

            String query = "select * from course";
            db.pstmt = db.conn.prepareStatement(query);
            db.rs = db.pstmt.executeQuery();

            while (db.rs.next()) {

                Object o[] = {db.rs.getInt("course_id"),db.rs.getInt("course_name"),db.rs.getInt("course_price"),db.rs.getInt("course_comments"),db.rs.getInt("branch_Bno")};
                tableModel.addRow(o);
            }
        } catch (SQLException exe) {
            exe.printStackTrace();
        }
    }


public final void createListCourses(){

        DefaultListModel<String> model1 = new DefaultListModel<>();

        try {

            String query = "select * from course";
            db.pstmt = db.conn.prepareStatement(query);
            db.rs = db.pstmt.executeQuery();

            while (db.rs.next()) {

                model1.addElement(db.rs.getString("course_name"));
            }


        }catch (SQLException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }

       listCourses = new JList(model1);
            listCourses.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
            listCourses.setLayoutOrientation(JList.VERTICAL_WRAP);
            listCourses.setVisibleRowCount(3);
            listCourses.setSelectedIndex(0);
            scrollPaneCourses = new JScrollPane(listCourses);

            scrollPaneCourses.setBounds(0, 30, 285, 200);
            scrollPaneCourses.add(listCourses);
            panelLef2.add(scrollPaneCourses);


    }
     */




}
