package com.company;

/*
21545 - Hyeeun Lee
21520 - Liubov Eremenko
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
    JButton listResult;
    JButton listAttendance;
    JButton changePswd;

    JButton[] calBtn = new JButton[49];

    JPanel panWest;
    JPanel panSouth;
    JPanel panNorth;
    JPanel panEast;

    JTextField textMonth;
    JTextField textYear;
    JTextField textWrite;

    String information;
    String inputId = null;
    String inputPswd = null;
    String pickDate = null;

    Database db = new Database();

    public CalendarMemo() {


    }

    public void CalendarForStudent(String id, String pswd) {
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
        panEast.add(textWrite = new JTextField(null));
        textWrite.setEditable(false);
        textWrite.setPreferredSize(new Dimension(200, 250));
        add(panEast, "East");

        panSouth = new JPanel();
        panSouth.add(listResult = new JButton("List of Results"));
        panSouth.add(listAttendance = new JButton("List of Attendance"));
        panSouth.add(changePswd = new JButton("Change Password"));
        add(panSouth, "South");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("CALENDAR");
        setBounds(100, 100, 640, 480);
        setVisible(true);

        listResult.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        listAttendance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        changePswd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public void CalendarForLecture(String id, String pswd) {
        inputId = id;
        inputPswd = pswd;
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

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("CALENDAR");
        setBounds(100, 100, 640, 480);
        setVisible(true);

        btnBefore.addActionListener(this);
        btnAfter.addActionListener(this);

        btnAttendManage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnStudentInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        db.listOfStudents(id);
                    }
                });
            }
        });

    }

    public void calSet() {
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
        else if (Integer.parseInt(e.getActionCommand()) >= 1 && Integer.parseInt(e.getActionCommand()) <=31) {
            day = Integer.parseInt(e.getActionCommand());
            pickDate = day+"/"+month+"/"+year;
            System.out.println(pickDate);
            if (pickDate!= null) {
                btnAdd.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        information = textWrite.getText();
                        if (information!= null) {
                            checkpanel(inputId, pickDate, information);
                        }else {

                        }
                    }

                });
            }
            calSet();
        }
    }

    public void gridInit() {
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

    private void checkpanel(String inputId, String pickDate, String information) {

        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout());
        setLayout(new FlowLayout());
        frame.setSize(500, 200);
        JTextField dateText = new JTextField(null);
        JTextField infoText = new JTextField(null);
        dateText.setEditable(false);
        infoText.setEditable(false);
        dateText.setText("Date: " + pickDate);
        infoText.setText("Information: " + information);
        JRadioButton btnCa = new JRadioButton("CA", false);
        JRadioButton btnExam = new JRadioButton("EXAM", false);
        ButtonGroup caExam = new ButtonGroup();
        caExam.add(btnExam);
        caExam.add(btnCa);
        JButton btnSave = new JButton("Save");
        frame.add(dateText);
        frame.add(infoText);
        frame.add(btnExam);
        frame.add(btnCa);
        frame.add(btnSave);
        frame.setVisible(true);
        frame.show();

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (btnCa.isSelected()) {
                    String type = "ca";
                    frame.dispose();
                    System.out.println(inputId + pickDate + information + type);
                } else if (btnExam.isSelected()) {
                    String type = "Exam";
                    db.calDBAdd(inputId, pickDate, information, type);
                    frame.dispose();
                }
            }
        });

    }


}