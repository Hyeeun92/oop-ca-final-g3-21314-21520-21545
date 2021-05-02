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

public class Database extends JFrame {
    String DB_URL = "jdbc:mysql://localhost:3306/oop_final?serverTimezone=UTC";
    String DB_USER = "root";
    String DB_PASSWORD = "ah447Sladl!"; //"YES01@";   "Memory1979@";
    Connection conn;
    PreparedStatement pstmt;
    ResultSet rs;
    String SQL = "";

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

                if (rs.getString(1).equals(pswd)) {
                    Administrator admin = new Administrator();

                } else {
                    System.out.println("!!");
                    getMessageError();
                }
            } else {
                System.out.println("!!");
                getMessageError();


            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    public void getLectureLoginInfo(String id, String pswd) {

        System.out.println("getLectureLoginIngo " + id);
        System.out.println("getLectureLoginInfo " + pswd);

        SQL = "select lecture_password from lecture where lecture_id = ?";
        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                String password = rs.getString(1);
                if (password.equals(pswd)) {
                    getLectureInformation(id, pswd);
                } else {
                    getMessageError();
                }
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }


    }

    public void getLectureInformation(String id, String pswd) {

        CalendarMemo calendarMemo = new CalendarMemo();
        String courseId = "";
        String classId = "";

        System.out.println("getLectureInfo " + id);

        ArrayList<String> classList = new ArrayList<>(2);
        ArrayList<List> clssAndCourseList= new ArrayList<>();

        SQL = "select course_id, class_id from timetable where lecture_id = ?";
        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                courseId = rs.getString("course_id");
                classId = rs.getString("class_id");
                classList.add(courseId);
                classList.add(classId);
                clssAndCourseList.add(classList);
                System.out.println("getLectureInfo " + courseId);
                System.out.println("getLectureInfo " + classId);
            }
            List getList = clssAndCourseList.get(0);
            classId = String.valueOf(getList.get(0));
            courseId = String.valueOf(getList.get(1));
            System.out.println(classId + courseId);
            calendarMemo.CalendarForLecture(id, pswd, classId, courseId);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
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
                    CalendarMemo calendarMemo = new CalendarMemo();
                    calendarMemo.CalendarForStudent(id, pswd);
                } else {
                    getMessageError();
                }
            } else {
                getMessageError();

            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void listOfStudents(String courseId) {

        String[] columns = new String[]{
                "First name", "Last name", "Email", "Address", "Gender", "Course Id"
        };

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        JTable table = new JTable(model);

        this.add(new JScrollPane(table));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);

        SQL = "select student_fname, student_lname, student_email, student_address, student_gender, course_id from student where course_id = ?";
        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, courseId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                String studentFname = rs.getString("student_fname");
                String studentLname = rs.getString("student_lname");
                String studentEmail = rs.getString("student_email");
                String studentAddress = rs.getString("student_address");
                String StudentGender = rs.getString("student_gender");
                model.addRow(new Object[]{studentFname, studentLname, studentEmail, studentAddress, StudentGender, courseId});
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void calDBAdddetail(String id, String date, String information, String type, String courseId, String classId) {
        SQL = "insert into schedule(class_id, finish_date, lecture_id, type, course_id, information)" + "values (?,?,?,?,?,?,?,?)";
        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.clearParameters();
            pstmt.setString(1, classId);
            pstmt.setString(2, date);
            pstmt.setString(3, id);
            pstmt.setString(4, type);
            pstmt.setString(5, courseId);
            pstmt.setString(6, information);
        } catch (SQLException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void calDBAdd(String id, String pswd, String courseId, String classId, String pickDate, String information, String type) {
        Model model = new Model();
        CalendarMemo calendarMemo = new CalendarMemo();

        //my mysql insert statement
        SQL = "INSERT INTO schedule (class_id, finish_date, lecture_id, type, course_id, information)" + "values (?,?,?,?,?,?)";

        try {
            //create my mysql insert preparedStatement
            pstmt = conn.prepareStatement(SQL);
            pstmt.clearParameters();
            pstmt.setString(1, classId);
            pstmt.setString(2, pickDate);
            pstmt.setString(3, id);
            pstmt.setString(4, type);
            pstmt.setString(5, courseId);
            pstmt.setString(6, information);

            // execute the pstmt
            pstmt.execute();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void calDBDelete() {
        Model model = new Model();
        CalendarMemo calendarMemo = new CalendarMemo();


        SQL = "DELETE FROM schedule WHERE finish_date = ? and lecture_id = ? ";
        try {
            pstmt = conn.prepareStatement(SQL);
          //  pstmt.setString(1, date);
           // pstmt.setString(2, id);
         //   rs = pstmt.executeQuery();

        } catch (SQLException sqlException) {
            System.out.println(sqlException);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void getMessageError() {
        JOptionPane optionPane = new JOptionPane("NOT LOG IN. Error!", JOptionPane.ERROR_MESSAGE);
        JDialog dialog = optionPane.createDialog("Failure");
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }

    public void getCourseCreateInfo(String course_id, String course_name, String course_price, String course_comments, String branch_Bno) throws SQLException {
        //my mysql insert statement
        SQL = "INSERT INTO course (course_id, course_name, course_price, course_comments, branch_Bno)" + "values (?,?,?,?,?)";

        try {
            //create my mysql insert preparedStatement
            pstmt = conn.prepareStatement(SQL);
            pstmt.clearParameters();
            pstmt.setString(1, course_id);
            pstmt.setString(2, course_name);
            pstmt.setString(3, course_price);
            pstmt.setString(4, course_comments);
            pstmt.setString(5, branch_Bno);

        } catch (SQLException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        pstmt.execute();


    }

    public void getAdmInfo(String administrator_id, String administrator_password, String administrator_name, String administrator_Lname, String administrator_email, String administrator_address, String administrator_gender, String branch_Bno) throws SQLException {

        SQL = "INSERT INTO administrator (administrator_id, administrator_password, administrator_name, administrator_Lname, administrator_email, administrator_address, administrator_gender, branch_Bno)" + "values (?,?,?,?,?,?,?,?)";

        try {
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

        } catch (SQLException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        pstmt.execute();

    }

    public void getLetInfo(String lecture_id, String lecture_password, String lecture_name, String lecture_Lname, String lecture_email, String lecture_address, String lecture_gender, String branch_Bno) throws SQLException {
        //my mysql insert statement
        SQL = "INSERT INTO lecture (lecture_id, lecture_password, lecture_name, lecture_Lname, lecture_email, lecture_address, lecture_gender, branch_Bno)" + "values (?,?,?,?,?,?,?,?)";

        try {
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

        } catch (SQLException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        pstmt.execute();

    }

    public void getMemo() {

    }

    public void listOfResultStudent(String id) {
        String[] columns = new String[]{
                "Course ID", "Class ID", "grade"
        };

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        JTable table = new JTable(model);

        this.add(new JScrollPane(table));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);

        SQL = "select course_id, class_id, grade grade where student_id = ?";
        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
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
        String[] columns = new String[]{
                "Class ID", "date", "Attendance"
        };

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        JTable table = new JTable(model);

        this.add(new JScrollPane(table));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);

        SQL = "select class_id, date, statement where student_id = ?";
        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
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
            while (rs.next()) {
                String courseId = rs.getString("course_id");

            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    public void lectureSearchMemo() {

      /*  SQL = "select finish_date, information from schedule where lecture_id = ?";
        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, lectureId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                String date = rs.getString("finish_date");
                String info = rs.getString("information");
                model.setRoadDate(date);
                model.setLectureScheduleInfo(info);
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }*/

    }
}

