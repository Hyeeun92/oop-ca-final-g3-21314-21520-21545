package com.company;

/*
21545 - Hyeeun Lee
21520 - Liubov Eremenko
21314 - Nathalie Flores
*/

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database extends JFrame{
    String DB_URL = "jdbc:mysql://localhost:3306/oop_final?serverTimezone=UTC";
    String DB_USER = "root";
    String DB_PASSWORD =  "YES01@"; //"ah447Sladl!";
    Connection conn;
    PreparedStatement pstmt = null;
    ResultSet rs;
    String SQL = null;

    public Database() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void getAdminLoginInfo(String id, String pswd) {
        SQL = "select administrator_password from administrator where administrator_id = ?";
        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println(pstmt);
                if (rs.getString(1).equals(pswd)) {
                    Administrator admin = new Administrator();
                } else {
                    System.out.println("!!");
                    getMessageError();
                    LoginPage loginPage = new LoginPage();
                }
            }
            else {
                System.out.println("!!");
                getMessageError();
                LoginPage loginPage = new LoginPage();
            }
        }catch (SQLException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    public void getLectureLoginInfo(String id, String pswd) {
        SQL = "select lecture_password from lecture where lecture_id = ?";
        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println(pstmt);
                if (rs.getString(1).equals(pswd)) {
                    CalendarMemo calendarMemo = new CalendarMemo();
                    calendarMemo.CalendarForLecture(id, pswd);
                } else {
                    System.out.println("!!");
                    getMessageError();
                    LoginPage loginPage = new LoginPage();
                }
            }
            else {
                System.out.println("!!");
                getMessageError();
                LoginPage loginPage = new LoginPage();
            }
        }catch (SQLException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    public void getStudentLoginInfo(String id, String pswd) {
        SQL = "select student_password from student where student_id = ?";
        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                if (rs.getString(1).equals(pswd)) {
                    CalendarMemo calendarMemo = new CalendarMemo();
                    calendarMemo.CalendarForStudent(id, pswd);
                } else {
                    System.out.println("!!");
                    getMessageError();
                    LoginPage loginPage = new LoginPage();
                }
            }
            else {
                System.out.println("!!");
                getMessageError();
                LoginPage loginPage = new LoginPage();
            }
        }catch (SQLException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void listOfStudents(String id){
        SQL = "select course_id from timetable where lecture_id like ?";

        String getCourseId = null;
        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();

            while(rs.next()) {
                getCourseId=rs.getString(1);
                Listing(getCourseId);
            }

        } catch (SQLException sqlException) {
            System.out.println(sqlException);
        } catch (Exception e) {
            System.out.println(e);
        }



    }

    public void Listing(String getCourseId){
        String[] columns = new String[] {
                "Student Id", "Student name", "Student Email", "Student Address", "Student Gender", "Course Id"
        };

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        JTable table = new JTable(model);

        this.add(new JScrollPane(table));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);

        SQL = "select student_id, student_name, student_email, student_address, student_gender, course_id from student where course_id = ?" ;
        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, getCourseId);
            rs = pstmt.executeQuery();

            while(rs.next()){
                String student_id = rs.getString("student_id");
                String student_name = rs.getString("student_name");
                String student_email = rs.getString("student_email");
                String student_address = rs.getString("student_address");
                String student_gender = rs.getString("student_gender");
                String course_id = rs.getString("course_id");
                model.addRow(new Object[]{student_id, student_name, student_email, student_address, student_gender, course_id});

            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void calDBAdd(String id, String date, String information, String type) {

        SQL = "select * from timetable where lecture_id = ?";
        String courseId, classId, className;

        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                courseId = rs.getString("course_id");
                classId = rs.getString("class_id");
                className = rs.getString("className");
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException);
        } catch (Exception e) {
            System.out.println(e);
        }

        if (information.equals("")) {
            JOptionPane.showMessageDialog(null, "Please input information");
            return;
        }

        SQL = "insert into schedule()";

    }

    public void getMessageError(){

        JOptionPane.showMessageDialog(null, "NOT LOG IN. Error!");
    }

    public void getCourseCreateInfo(String courseId, String titleCourseS, String otherInfoS) {
        //my mysql insert statement
        SQL = "INSERT INTO course (course_id, course_name, course_price)" + "values (?,?,?)";

        try{
            //create my mysql insert preparedStatement
            pstmt = conn.prepareStatement(SQL);
            pstmt.clearParameters();
            pstmt.setString(1, courseId);
            pstmt.setString(2, titleCourseS);
            pstmt.setString(3, otherInfoS);

            // execute the pstmt
            pstmt.execute();
            conn.close();

        }catch (SQLException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }


    public void getStudentCreateInfo(String student_id, String student_name, String student_email, String student_address, String student_gender, String student_password) {

        SQL = "INSERT INTO course (student_id, student_name, student_email, student_address, student_gender, student_password)" + "values (?,?,?,?,?,?)";

        try{
            //create my mysql insert preparedStatement
            pstmt = conn.prepareStatement(SQL);
            pstmt.clearParameters();
            pstmt.setString(1, student_id);
            pstmt.setString(2, student_name);
            pstmt.setString(3, student_email);
            pstmt.setString(4, student_address);
            pstmt.setString(5, student_gender);
            pstmt.setString(6, student_password);

            // execute the pstmt
            pstmt.execute();
            conn.close();

        }catch (SQLException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}

