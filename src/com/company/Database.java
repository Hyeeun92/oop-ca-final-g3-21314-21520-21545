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

public class Database extends JFrame {
    String DB_URL = "jdbc:mysql://localhost:3306/oop_final?serverTimezone=UTC";
    String DB_USER = "root";
    String DB_PASSWORD = "ah447Sladl!";  //"YES01@";"Memory1979@";
    Connection conn;
    PreparedStatement pstmt;
    ResultSet rs;
    String SQL = "";
    CalendarMemo calendarMemo = new CalendarMemo();

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

        SQL = "select lecture_password from lecture where lecture_id = ?";
        String password = null;
        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                password = rs.getString(1);
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

        String courseId;
        String classId;

        ArrayList<String[]> classAndCourseList = new ArrayList<>();

        SQL = "select course_id, class_id from timetable where lecture_id = ?";
        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            ResultSetMetaData rsmt = rs.getMetaData();
            int count = rsmt.getColumnCount();
            for (int i = 0; i <= count; i++) {
                rs.next();
                String[] classList = new String[2];
                courseId = rs.getString("course_id");
                classId = rs.getString("class_id");
                classList[0] = courseId;
                classList[1] = classId;
                System.out.println(count + courseId + classId);
                classAndCourseList.add(i, classList);
            }
            calendarMemo.CalendarForLecture(id, pswd, classAndCourseList);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getStudentLoginInfo(String id, String pswd) {

        String courseId;
        SQL = "select student_password, course_id from student where student_id = ?";
        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                if (rs.getString(1).equals(pswd)) {
                    courseId = rs.getString("course_id");
                    getStudentInformation(id, pswd, courseId);
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

    public void getStudentInformation(String id, String pswd, String courseId) {

        CalendarMemo calendarMemo = new CalendarMemo();

        ArrayList<String[]> classAndCourseList = new ArrayList<>();

        String classId;

        SQL = "select class_id from timetable where course_id = ?";
        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, courseId);
            rs = pstmt.executeQuery();
            ResultSetMetaData rsmt = rs.getMetaData();
            int count = rsmt.getColumnCount();
            for (int i = 0; i <= count; i++) {
                rs.next();
                String[] classList = new String[2];
                classId = rs.getString("class_id");
                classList[0] = courseId;
                classList[1] = classId;
                classAndCourseList.add(i, classList);
            }
            calendarMemo.CalendarForStudent(id, pswd, classAndCourseList);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listOfStudents(ArrayList<String[]> classList) {

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
        ArrayList<String[]> getClassList = classList;
        String courseId;

        try {
            pstmt = conn.prepareStatement(SQL);
            for (int i = 0; i < getClassList.size(); i++) {
                String[] courseId_classId_List = new String[2];
                courseId_classId_List = getClassList.get(i);
                courseId = courseId_classId_List[0];
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
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void calDBAdd(String id, String pswd, String courseId, String classId, String pickDate, String information, String type) {

        //my mysql insert statement
        SQL = "INSERT INTO schedule (class_id, finish_date, lecture_id, type, course_id, information)"
                + "values (?,?,?,?,?,?)";

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

    public void calDBDelete(String id, String courseId, String classId, String pickDate, String information) {

        SQL = "DELETE FROM schedule WHERE class_id = ? and lecture_id = ? and finish_date = ?";

        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, classId);
            pstmt.setString(2, id);
            pstmt.setString(3, pickDate);
            pstmt.executeUpdate();

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

    public String getCourseIDInfo(String data) {

        String CIDFee = null;

        SQL = "SELECT course_id FROM course WHERE course_name = ?";

        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, data);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                //System.out.println(pstmt);
                if (rs.getString(1).equals(rs.getString("course_id"))) {
                    CIDFee = rs.getString("course_id");

                } else {
                    System.out.println("!!");

                }
            } else {
                System.out.println("!!");

            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return CIDFee;
    }

    public void getStuInfo(String student_id, String student_password, String student_name, String student_Lname, String student_email, String student_address, String student_gender, String branch_Bno, String course_id) throws SQLException {
        //my mysql insert statement
        SQL = "INSERT INTO student ( student_id, student_password, student_name, student_Lname, student_email, student_address, student_gender, branch_Bno, course_id)" + "values (?,?,?,?,?,?,?,?,?)";

        try {
            //create my mysql insert preparedStatement
            pstmt = conn.prepareStatement(SQL);
            pstmt.clearParameters();
            pstmt.setString(1, student_id);
            pstmt.setString(2, student_password);
            pstmt.setString(3, student_name);
            pstmt.setString(4, student_Lname);
            pstmt.setString(5, student_email);
            pstmt.setString(6, student_address);
            pstmt.setString(7, student_gender);
            pstmt.setString(8, branch_Bno);
            pstmt.setString(9, course_id);

            pstmt.execute();

        } catch (SQLException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    public String getStudentBranchWithCourseEnroll(String couSFees) {

        String studentBranch = null;

        SQL = "SELECT branch_Bno FROM course WHERE course_id = ?";

        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, couSFees);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println(pstmt);
                if (rs.getString(1).equals(rs.getString("branch_Bno"))) {
                    studentBranch = rs.getString("branch_Bno");

                } else {
                    System.out.println("!!");

                }
            } else {
                System.out.println("!!");

            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return studentBranch;
    }

    public String checkMemo(String id, ArrayList<String[]> classList, String pickDate) {

        SQL = "select information from schedule where finish_date = ? and course_id = ? and class_id = ?";
        String info = "";
        String information = "";
        String classId = "";
        String courseId = "";
        ArrayList<String[]> getClassList = classList;

        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, pickDate);
            for (int i = 0; i < getClassList.size(); i++) {
                String[] courseId_classId_List = new String[2];
                courseId_classId_List = getClassList.get(i);
                courseId = courseId_classId_List[0];
                classId = courseId_classId_List[1];
                pstmt.setString(2, courseId);
                pstmt.setString(3, classId);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    info = rs.getString("information");
                    information = courseId + " / " + classId + " / " + info;
                    System.out.println(information);
                }
            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return information;
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

    public ArrayList<Integer> checkDay(int year, int month, ArrayList<String[]> classList) {

        SQL = "select finish_date from schedule where course_id = ?";
        String roadDate;

        ArrayList<Integer> list = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement(SQL);
            for (int i = 0; i < classList.size(); i++) {
                String[] courseId_classId_List = new String[2];
                courseId_classId_List = classList.get(i);
                String courseId = courseId_classId_List[0];
                pstmt.setString(1, courseId);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    roadDate = rs.getString("finish_date");
                    String[] date = roadDate.split("/");
                    int getDay = Integer.parseInt(date[0]);
                    int getMonth = Integer.parseInt(date[1]);
                    int getYear = Integer.parseInt(date[2]);
                    if (getMonth == month && getYear == year) {
                        list.add(getDay);
                    }

                }
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return list;
    }

    public void changeStudentPswd(String getId, String pswdNew) {

        SQL = "Update student set student_password = ? where student_id = ?";
        try {
            //create my mysql insert preparedStatement
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, pswdNew);
            pstmt.setString(2, getId);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public ArrayList<String> studentListForAttandance(String getCourseId, String getClassId) {

        String fullName;
        ArrayList<String> studentList = new ArrayList<>();

        SQL = "select student_Fname, student_lName from student where course_id = ?";

        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, getCourseId);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                fullName = rs.getString("student_fname") + " "  + rs.getString("student_lname");
                studentList.add(fullName);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentList;
    }
}

