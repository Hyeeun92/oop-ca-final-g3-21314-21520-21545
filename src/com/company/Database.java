package com.company;

/*
21545 - Hyeeun Lee
21520 - Liubov Eremenko
21314 - Nathalie Flores
*/


import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class Database {
    String DB_URL = "jdbc:mysql://localhost:3306/oop_final?serverTimezone=UTC";
    String DB_USER = "root";
    String DB_PASSWORD =  "ah447Sladl!"; //"YES01@";
    Connection conn;
    PreparedStatement pstmt;
    ResultSet rs;

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
        String SQL = "select administrator_password from administrator where administrator_id = ?";
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
                }
            }
            else {
                System.out.println("!!");
            }
        }catch (SQLException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    public void getLectureLoginInfo(String id, String pswd) {
        String SQL = "select lecture_password from lecture where lecture_id = ?";
        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println(pstmt);
                if (rs.getString(1).equals(pswd)) {
                    System.out.println("in");
                } else {
                    System.out.println("!!");
                }
            }
            else {
                System.out.println("!!");
            }
        }catch (SQLException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    public void getStudentLoginInfo(String id, String pswd) {
        String SQL = "select student_password from student where student_id = ?";
        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println(pstmt);
                if (rs.getString(1).equals(pswd)) {
                    System.out.println("in");
                } else {
                    System.out.println("!!");
                }
            }
            else {
                System.out.println("!!");
            }
        }catch (SQLException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

}







