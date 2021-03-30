package com.company;

/* 21545 - Hyeeun Lee
21520
21314 - Nathalie Flores
 */

public class Main {

   /* public static void main(String[] args) {
        CalendarMemo calendarMemo = new CalendarMemo();
    }

    */

 /*   public static void main(String[] args) {
        Administrator administrator = new Administrator();
    } */
 public static void main(String[] args) {
     // create an instance of ID and passwords class
     IDandPasswords idandPasswords = new IDandPasswords();

     //create an instance of login page
     LoginPage loginPage = new LoginPage(idandPasswords.getLoginInfo());  // set login info as argument, it will return hash map







}
