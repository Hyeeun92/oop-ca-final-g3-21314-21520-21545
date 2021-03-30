package com.company;

/* 21545 - Hyeeun Lee
21520
21314 - Nathalie Flores
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class CalendarMemo extends JFrame implements ActionListener {
    String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
    int year = 0;
    int month = 0;
    int day = 0;
    int todays = 0;
    int memoday = 0;

    Font f;
    Calendar today;
    Calendar cal;

    JButton btnBefore;
    JButton btnAfter;
    JButton btnAdd;
    JButton btnDelete;
    JButton btnAttendManage;
    JButton btnStudentInfo;
    JButton[] calBtn = new JButton[49];

    JPanel panWest;
    JPanel panSouth;
    JPanel panNorth;
    JPanel panEast;

    JTextField textMonth;
    JTextField textYear;
    JTextField textWrite;

    public CalendarMemo() {
        today = Calendar.getInstance();
        cal = new GregorianCalendar();

        year = today.get(Calendar.YEAR);
        month = today.get(Calendar.MONTH) + 1;

        panNorth = new JPanel();
        panNorth.add(btnBefore = new JButton("<"));
        panNorth.add(textMonth = new JTextField(month + ""));
        panNorth.add(textYear = new JTextField(year + ""));

        textYear.setEnabled(false);
        textMonth.setEditable(false);

        panNorth.add(btnAfter = new JButton(">"));
        f = new Font("Serif", Font.BOLD, 24);
        textYear.setFont(f);
        textMonth.setFont(f);

        add(panNorth, "North");

        panWest = new JPanel(new GridLayout(7,7));
        f = new Font("Serif", Font.BOLD, 12);

        gridInit();
        calSet();
        add(panWest, "West");

        panEast = new JPanel();
        panEast.add(textWrite = new JTextField());
        textWrite.setPreferredSize(new Dimension(200, 250));
        add(panEast, "East");

        panSouth = new JPanel();
        panSouth.add(btnAttendManage = new JButton("Manage attendance"));
        panSouth.add(btnStudentInfo = new JButton("Detail of students"));
        panSouth.add(btnAdd = new JButton("ADD MEMO"));
        panSouth.add(btnDelete = new JButton("DELETE MEMO"));
        add(panSouth, "South");

        btnBefore.addActionListener(this);
        btnAfter.addActionListener(this);
        btnAdd.addActionListener(this);
        btnDelete.addActionListener(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("CALENDAR");
        setBounds(100, 100, 650, 400);
        setVisible(true);

    }

    private void calSet() {
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH,(month-1));
        cal.set(Calendar.DATE,1);
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

        int j = 0;
        int hopping = 0;

        calBtn[0].setForeground(new Color(255, 0, 0));
        calBtn[6].setForeground(new Color(0, 0, 255));

        for(int i = cal.getFirstDayOfWeek(); i < dayOfWeek; i++){
            j++;
        }
        hopping = j;
        for (int k = 0; k<hopping; k++) {
            calBtn[k+7].setText("");
        }
        for (int i = cal.getMinimum(Calendar.DAY_OF_MONTH); i <= cal.getMaximum(Calendar.DAY_OF_MONTH); i++ ) {
            cal.set(Calendar.DATE, i);
            if (cal.get(Calendar.MONTH)!=month-1) {
                break;
            }
            todays = i;
            if (memoday == 1) {
                calBtn[i+6+hopping].setForeground(new Color(0,255,0));
            }
            else {
                calBtn[i+6+hopping].setForeground(new Color(0,0,0));
                if ((i+hopping)%7 == 0) {
                   calBtn[i+6+hopping].setForeground(new Color(0,0,255));
                }
                if ((i+hopping-1)%7 == 0) {
                    calBtn[i+6+hopping].setForeground(new Color(255,0,0));
                }
            }
            calBtn[i+6+hopping].setText((i) + "");
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnBefore) {
            this.panWest.removeAll();
            calInput(-1);
            gridInit();
            panelInit();
            calSet();
            this.textYear.setText(year + "");
            this.textMonth.setText(month + "");
        }
        else if (e.getSource() == btnAfter) {
            this.panWest.removeAll();
            calInput(1);
            gridInit();
            panelInit();
            calSet();
            this.textYear.setText(year + "");
            this.textMonth.setText(month + "");
        }
        else if (e.getSource() == btnAdd) {
            textWrite.setText("");
        }
        else if (e.getSource() == btnDelete) {
            textWrite.setText("");
        }
        else if (Integer.parseInt(e.getActionCommand()) >= 1 && Integer.parseInt(e.getActionCommand()) <=31) {
            day = Integer.parseInt(e.getActionCommand());
            System.out.println(day + "/" + month + "/" + year);
            calSet();
        }
    }

    private void gridInit() {
        for (int i = 0; i < days.length; i++) {
            panWest.add(calBtn[i] = new JButton(days[i]));
        }
        for (int i = days.length; i < 49; i++) {
            panWest.add(calBtn[i] = new JButton(""));
            calBtn[i].addActionListener((this));
        }
    }

    public void panelInit() {
        GridLayout gridLayout1 = new GridLayout(7,7);
        panWest.setLayout(gridLayout1);
    }

    public void calInput(int gap) {
        month+=(gap);
        if (month <= 0) {
            month = 12;
            year = year-1;
        }
        else if (month >= 13) {
            month = 1;
            year = year+1;
        }
    }




}
