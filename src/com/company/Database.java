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

public class Database extends JFrame{
    String DB_URL = "jdbc:mysql://localhost:3306/oop_final?";
    String unicode = "useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    String DB_USER = "root";
    String DB_PASSWORD ="ah447Sladl!";  //"YES01@";   "Memory1979@";
    Connection conn;
    PreparedStatement pstmt = null;
    ResultSet rs;
    String SQL = null;
    CalendarMemo calendarMemo = new CalendarMemo();

    public Database() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL+unicode, DB_USER,DB_PASSWORD);
        } catch (ClassNotFoundException exe) {
            exe.printStackTrace();
        } catch (SQLException exe) {
            exe.printStackTrace();
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
                    getMessageError();
                }
            }
            else {
                getMessageError();
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
                    dispose();
                    CalendarMemo calendarMemo = new CalendarMemo();
                    calendarMemo.CalendarForLecture(id, pswd);
                } else {
                    System.out.println("!!");
                    getMessageError();
                }
            }
            else {
                System.out.println("!!");
                getMessageError();
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
                    dispose();
                    checkDay(id);
                } else {
                    System.out.println("!!");
                    getMessageError();
                }
            }
            else {
                System.out.println("!!");
                getMessageError();

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
                "Id", "First name","Last name","Email", "Address", "Gender", "Course Id"
        };

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        JTable table = new JTable(model);

        this.add(new JScrollPane(table));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);

        SQL = "select student_id, student_Fname, student_Lname, student_email, student_address, student_gender, course_id from student where course_id = ?" ;
        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, getCourseId);
            rs = pstmt.executeQuery();

            while(rs.next()){
                String student_id = rs.getString("student_id");
                String student_Fname = rs.getString("student_Fname");
                String student_Lname = rs.getString("student_Lname");
                String student_email = rs.getString("student_email");
                String student_address = rs.getString("student_address");
                String student_gender = rs.getString("student_gender");
                String course_id = rs.getString("course_id");
                model.addRow(new Object[]{student_id, student_Fname, student_Lname,  student_email, student_address, student_gender, course_id});

            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void calDBAdd(String id, String date, String information, String type) {

        SQL = "select * from timetable where lecture_id = ?";
        String courseId = "";
        String classId = "";

        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                courseId = rs.getString("course_id");
                classId = rs.getString("class_id");
                calDBAdddetail(id, date, information, type, courseId, classId);
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void calDBAdddetail(String id,String date, String information,String type, String courseId, String classId) {
        SQL = "insert into schedule(class_id, finish_date, lecture_id, type, course_id, information)"+ "values (?,?,?,?,?,?,?,?)";
        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.clearParameters();
            pstmt.setString(1, classId);
            pstmt.setString(2, date);
            pstmt.setString(3, id);
            pstmt.setString(4, type);
            pstmt.setString(5, courseId);
            pstmt.setString(6, information);
        }catch (SQLException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void calDBDel(String id, String date){

        SQL = "DELETE FROM schedule WHERE finish_date = ? and lecture_id = ? ";
        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, date);
            pstmt.setString(2, id);
            rs = pstmt.executeQuery();

        } catch (SQLException sqlException) {
            System.out.println(sqlException);
        } catch (Exception e) {
            System.out.println(e);
        }


    }

    public void getMessageError(){
        JOptionPane optionPane = new JOptionPane("NOT LOG IN. Error!", JOptionPane.ERROR_MESSAGE);
        JDialog dialog = optionPane.createDialog("Failure");
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }

    public void getCourseCreateInfo(String course_id, String course_name, String course_price, String course_comments, String branch_Bno) throws SQLException {

        SQL = "INSERT INTO course (course_id, course_name, course_price, course_comments, branch_Bno)" + "values (?,?,?,?,?)";

        try{
            //create my mysql insert preparedStatement
            pstmt = conn.prepareStatement(SQL);
            pstmt.clearParameters();
            pstmt.setString(1, course_id);
            pstmt.setString(2, course_name);
            pstmt.setString(3, course_price);
            pstmt.setString(4, course_comments);
            pstmt.setString(5, branch_Bno);

        }catch (SQLException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        pstmt.execute();

    }

    public void getAdmInfo(String administrator_id, String administrator_password, String administrator_name, String administrator_Lname, String administrator_email, String administrator_address, String administrator_gender, String branch_Bno) throws SQLException {

        SQL = "INSERT INTO administrator (administrator_id, administrator_password, administrator_name, administrator_Lname, administrator_email, administrator_address, administrator_gender, branch_Bno)" + "values (?,?,?,?,?,?,?,?)";

        try{
            //create my mysql insert preparedStatement
            pstmt = conn.prepareStatement(SQL);
            pstmt.clearParameters();
            pstmt.setString(1, administrator_id);
            pstmt.setString(2, administrator_password);
            pstmt.setString(3, administrator_name);
            pstmt.setString(4, administrator_Lname);
            pstmt.setString(5, administrator_email);
            pstmt.setString(6, administrator_address);
            pstmt.setString(7, administrator_gender);
            pstmt.setString(8, branch_Bno);

        }catch (SQLException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        pstmt.execute();

    }

    public void getLetInfo(String lecture_id, String lecture_password, String lecture_name, String lecture_Lname, String lecture_email, String lecture_address, String lecture_gender, String branch_Bno) throws SQLException {
        //my mysql insert statement
        SQL = "INSERT INTO lecture (lecture_id, lecture_password, lecture_name, lecture_Lname, lecture_email, lecture_address, lecture_gender, branch_Bno)" + "values (?,?,?,?,?,?,?,?)";

        try{
            //create my mysql insert preparedStatement
            pstmt = conn.prepareStatement(SQL);
            pstmt.clearParameters();
            pstmt.setString(1, lecture_id);
            pstmt.setString(2, lecture_password);
            pstmt.setString(3, lecture_name);
            pstmt.setString(4, lecture_Lname);
            pstmt.setString(5, lecture_email);
            pstmt.setString(6, lecture_address);
            pstmt.setString(7, lecture_gender);
            pstmt.setString(8, branch_Bno);

        }catch (SQLException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        pstmt.execute();

    }

    public void getMemo(){

    }

    public void listOfResultStudent(String id) {
        String[] columns = new String[] {
                "Course ID", "Class ID","grade"
        };

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        JTable table = new JTable(model);

        this.add(new JScrollPane(table));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);

        SQL = "select course_id, class_id, grade grade where student_id = ?" ;
        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            while(rs.next()){
                String courseId = rs.getString("course_id");
                String classID = rs.getString("class_id");
                String grade = rs.getString("grade");
                model.addRow(new Object[]{courseId, classID, grade});
            }
            pstmt.execute();
        } catch (SQLException sqlException) {
            System.out.println(sqlException);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void listOfAttendenceStudent(String id) {
        String[] columns = new String[] {
                "Class ID","date","Attendance"
        };

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        JTable table = new JTable(model);

        this.add(new JScrollPane(table));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);

        SQL = "select class_id, date, statement where student_id = ?" ;
        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            while(rs.next()){
                String courseId = rs.getString("course_id");
                String date = rs.getString("date");
                String statement = rs.getString("statement");
                model.addRow(new Object[]{courseId, date, statement});
            }
            pstmt.execute();
        } catch (SQLException sqlException) {
            System.out.println(sqlException);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void checkDay(String id) {

        SQL = "select course_id from student where student_id = ?";
        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            while(rs.next()){
                String courseId = rs.getString("course_id");

            }
        }catch (SQLException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

}

