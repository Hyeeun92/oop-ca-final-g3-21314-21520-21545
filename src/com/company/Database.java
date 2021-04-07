package com.company;

/* 21545 - Hyeeun Lee
21520 - Liubov Eremenko
21314 - Nathalie Flores
 */


import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Database {
    String DB_URL = "jdbc:mysql://localhost:3306/oop_final?serverTimezone=UTC";
    String DB_USER = "root";
    String DB_PASSWORD =  "YES01@"; //"ah447Sladl!";

   public List<LoginInfo> getList() {
        List<LoginInfo> lists = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Statement stmt = con.createStatement();

            String adminList = "select * from administrator";
            ResultSet adminResult = stmt.executeQuery(adminList);


            while(adminResult.next()) {
                LoginInfo loginfoList = new LoginInfo();
                loginfoList.type = "Admin";
                loginfoList.id = adminResult.getString("administrator_id");
                loginfoList.password = adminResult.getString("administrator_password");
                lists.add(loginfoList);

            }

            String studentList = "select * from lecture";
            ResultSet studentResult = stmt.executeQuery(studentList);

            while(studentResult.next()) {
                LoginInfo loginfoList = new LoginInfo();
                loginfoList.type = "Lecture";
                loginfoList.id = studentResult.getString("lecture_id");
                loginfoList.password = studentResult.getString("lecture_password");
                lists.add(loginfoList);
                break;
            }

            String lectureList = "select * from student";
            ResultSet lectureResult = stmt.executeQuery(lectureList);

            while(lectureResult.next()) {
                LoginInfo loginfoList = new LoginInfo();
                loginfoList.type = "Student";
                loginfoList.id = lectureResult.getString("student_id");
                loginfoList.password = lectureResult.getString("student_password");
                lists.add(loginfoList);
                break;
            }

        }catch (SQLException e) {
            System.out.println(e.toString());
        } catch (Exception e) {

        }
        return lists;

    }

}






