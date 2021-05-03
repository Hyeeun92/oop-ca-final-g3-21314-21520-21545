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

public class CalendarMemo extends JFrame implements ActionListener{

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
    JPanel panSouth,panSouth1, panSouth2;
    JPanel panNorth;
    JPanel panEast;

    JTextField textMonth;
    JTextField textYear;
    JTextField textWrite;

    String information;
    String inputId = null;
    String inputPswd = null;
    String pickDate = null;

    SpringLayout layout;
    String id, pswd, courseId, classId;
    String getId, getPswd;
    ArrayList<String[]> getClassList = new ArrayList<>();
    String inputCourseId;
    String inputClassId;
    //SpringLayout.Constraints labelCons2;

    public CalendarMemo() {

    }

    public void CalendarForStudent(String id, String pswd, ArrayList<String[]> classList) {

        this.getId = id;
        this.getPswd = pswd;
        this.getClassList = classList;

        JLabel backgroundS = new JLabel(new ImageIcon(getClass().getResource("greenChalkboard.jpg")));
        backgroundS.setSize(960,533);
        add(backgroundS);

        layout = new SpringLayout();
        backgroundS.setLayout(layout);

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

        //add(panNorth, "North");
        panNorth.setBackground(Color.GREEN);
        layout.putConstraint(SpringLayout.NORTH, panNorth, 5, SpringLayout.NORTH, backgroundS);
        SpringLayout.Constraints labelCons1 = layout.getConstraints(panNorth);
        labelCons1.setX(Spring.constant(280));
        labelCons1.setY(Spring.constant(100));
        backgroundS.add(panNorth);

        panWest = new JPanel(new GridLayout(7,7));
        f = new Font("Serif", Font.BOLD, 12);

        gridInit();
        calSet();
        hideInit();
        //add(panWest, "West");

        panWest.setBackground(Color.MAGENTA);
        layout.putConstraint(SpringLayout.WEST, panWest, 5, SpringLayout.WEST, backgroundS);
        SpringLayout.Constraints labelCons2 = layout.getConstraints(panWest);
        labelCons2.setX(Spring.constant(280));
        labelCons2.setY(Spring.constant(150));
        backgroundS.add(panWest);


        panEast = new JPanel();
        panEast.add(textWrite = new JTextField(""));
        textWrite.setEditable(false);
        textWrite.setPreferredSize(new Dimension(180, 180));

        //add(panEast, "East");
        panEast.setBackground(Color.BLUE);
        layout.putConstraint(SpringLayout.EAST, panEast, 5, SpringLayout.EAST, backgroundS);
        SpringLayout.Constraints labelCons3 = layout.getConstraints(panEast);
        labelCons3.setHeight(Spring.constant(180));
        labelCons3.setWidth(Spring.constant(180));
        labelCons3.setX(Spring.constant(730));
        labelCons3.setY(Spring.constant(120));
        backgroundS.add(panEast);

        panSouth = new JPanel();
        panSouth.add(listResult = new JButton());
        listResult.setIcon(new ImageIcon(getClass().getResource("grades.jpg")));
        listResult.setSize(20,20);
        panSouth.add(listAttendance = new JButton());
        listAttendance.setSize(20,20);
        listAttendance.setIcon(new ImageIcon(getClass().getResource("attandance.jpg")));
        panSouth.add(changePswd = new JButton());
        changePswd.setIcon(new ImageIcon(getClass().getResource("password.jpg")));
        changePswd.setSize(20,20);
        //add(panSouth, "South");
        panSouth.setBackground(Color.YELLOW);
        layout.putConstraint(SpringLayout.SOUTH, panSouth, 1, SpringLayout.SOUTH, backgroundS);
        SpringLayout.Constraints labelCons4 = layout.getConstraints(panSouth);
        labelCons4.setHeight(Spring.constant(120));
        labelCons4.setWidth(Spring.constant(450));
        labelCons4.setX(Spring.constant(350));
        labelCons4.setY(Spring.constant(400));
        backgroundS.add(panSouth);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("CALENDAR");
        setSize(1000,600);
        setVisible(true);
        setLocationRelativeTo(null);

        btnBefore.addActionListener(this);
        btnAfter.addActionListener(this);
        listResult.addActionListener(this);
        listAttendance.addActionListener(this);
        changePswd.addActionListener(this);

    }

    public void CalendarForLecture(String id, String pswd, ArrayList<String[]> classList) {
        this.getId = id;
        this.getPswd = pswd;
        this.getClassList = classList;

        JLabel backgroundS = new JLabel(new ImageIcon(getClass().getResource("lecturerBg.png")));
        backgroundS.setSize(1000,600);
        add(backgroundS);

        layout = new SpringLayout();
        backgroundS.setLayout(layout);

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

        //add(panNorth, "North");
        panNorth.setBackground(Color.GREEN);
        layout.putConstraint(SpringLayout.NORTH, panNorth, 5, SpringLayout.NORTH, backgroundS);
        SpringLayout.Constraints labelCons1 = layout.getConstraints(panNorth);
        labelCons1.setHeight(Spring.constant(35));
        labelCons1.setWidth(Spring.constant(214));
        labelCons1.setX(Spring.constant(700));
        labelCons1.setY(Spring.constant(40));
        backgroundS.add(panNorth);

        panWest = new JPanel(new GridLayout(7,7));
        f = new Font("Serif", Font.BOLD, 12);

        gridInit();
        calSet();
        hideInit();

        //add(panWest, "West");
        panWest.setBackground(Color.MAGENTA);
        layout.putConstraint(SpringLayout.WEST, panWest, 5, SpringLayout.WEST, backgroundS);
        SpringLayout.Constraints labelCons2 = layout.getConstraints(panWest);
        labelCons2.setHeight(Spring.constant(190));
        labelCons2.setWidth(Spring.constant(415));
        labelCons2.setX(Spring.constant(565));
        labelCons2.setY(Spring.constant(80));
        backgroundS.add(panWest);

        panEast = new JPanel();
        panEast.add(textWrite = new JTextField(""));
        textWrite.setPreferredSize(new Dimension(180, 180));
        //add(panEast, "East");
        panEast.setBackground(Color.BLUE);
        layout.putConstraint(SpringLayout.EAST, panEast, 5, SpringLayout.EAST, backgroundS);
        SpringLayout.Constraints labelCons3 = layout.getConstraints(panEast);
        labelCons3.setHeight(Spring.constant(180));
        labelCons3.setWidth(Spring.constant(180));
        labelCons3.setX(Spring.constant(298));
        labelCons3.setY(Spring.constant(200));
        backgroundS.add(panEast);

        panSouth = new JPanel();
        panSouth.add(btnAttendManage = new JButton("Manage attendance"));
        panSouth.add(btnStudentInfo = new JButton("Detail of students"));
        panSouth.add(btnAdd = new JButton("ADD MEMO"));
        panSouth.add(btnDelete = new JButton("DELETE MEMO"));
        //add(panSouth, "South");
        panSouth.setBackground(Color.YELLOW);
        layout.putConstraint(SpringLayout.SOUTH, panSouth, 1, SpringLayout.SOUTH, backgroundS);
        SpringLayout.Constraints labelCons4 = layout.getConstraints(panSouth);
        labelCons4.setHeight(Spring.constant(140));
        labelCons4.setWidth(Spring.constant(150));
        labelCons4.setX(Spring.constant(750));
        labelCons4.setY(Spring.constant(400));
        backgroundS.add(panSouth);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("CALENDAR LECTURER");
        setSize(1000,600);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

        btnBefore.addActionListener(this);
        btnAfter.addActionListener(this);
        btnAdd.addActionListener(this);
        btnDelete.addActionListener(this);
        btnAttendManage.addActionListener(this);
        btnStudentInfo.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {

        Database db = new Database();

        if (e.getSource() == btnBefore) {
            this.panWest.removeAll();     //panWest
            calInput(-1);
            gridInit();
            panelInit();
            calSet();
            hideInit();
            this.textYear.setText(year + "");
            this.textMonth.setText(month + "");
        }
        else if (e.getSource() == btnAfter) {
            this.panWest.removeAll();    //panWest
            calInput(1);
            gridInit();
            panelInit();
            calSet();
            hideInit();
            this.textYear.setText(year + "");
            this.textMonth.setText(month + "");
        }
        else if (e.getSource() == btnStudentInfo) {
            db.listOfStudents(getClassList);
        }
        else if (e.getSource() == btnAdd) {
            information = textWrite.getText();
            if (pickDate != null && information != null) {
                checkpanel(getId, getPswd, getClassList, pickDate, information);
            }
        }
        else if (e.getSource() == btnDelete) {
            information = textWrite.getText();
            String[] info = information.split(" / ");
            String getCourseId = info[0];
            String getClassId = info[1];
            String getInfo = info[2];
            if (pickDate != null && information != null) {
                db.calDBDelete(getId, getCourseId, getClassId, pickDate, getInfo);
            }
        }
        else if (e.getSource() == btnAttendManage) {

        }
        else if (e.getSource() == listResult) {

        }
        else if (e.getSource() == listAttendance) {

        }
        else if (e.getSource() == changePswd) {
            RenewLogin renewLogin = new RenewLogin(id, pswd);
        }

        else if (Integer.parseInt(e.getActionCommand()) >= 1 && Integer.parseInt(e.getActionCommand()) <=31) {
            day = Integer.parseInt(e.getActionCommand());
            pickDate = day+"/"+month+"/"+year;
            System.out.println(pickDate);
            System.out.println(getId);
            textWrite.setText(db.checkMemo(getId, getClassList , pickDate));
            calSet();
        }
    }

    public void calSet() {
        Database db = new Database();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, (month - 1));
        cal.set(Calendar.DATE, 1);
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        ArrayList<Integer> list = db.checkDay(year, month, getClassList);

        int j = 0;
        int hopping = 0;

        calBtn[0].setForeground(new Color(255, 0, 0));
        calBtn[6].setForeground(new Color(0, 0, 255));

        for (int i = cal.getFirstDayOfWeek(); i < dayOfWeek; i++) {
            j++;
        }
        hopping = j;
        for (int k = 0; k < hopping; k++) {
            calBtn[k + 7].setText("");
        }
        for (int i = cal.getMinimum(Calendar.DAY_OF_MONTH); i <= cal.getMaximum(Calendar.DAY_OF_MONTH); i++) {
            cal.set(Calendar.DATE, i);
            if (cal.get(Calendar.MONTH) != month - 1) {
                break;
            } else {
                // get day if there are has Ca or exam to set text as green
                for (int g = 0; g < list.size(); g++) {
                    int day = list.get(g);
                    calBtn[day + 6 + hopping].setForeground(new Color(0, 255, 0));
                }
                //set text color black
                calBtn[i + 6 + hopping].setForeground(new Color(0, 0, 0));
                //Saturday set text blue
                if ((i + hopping) % 7 == 0) {
                    calBtn[i + 6 + hopping].setForeground(new Color(0, 0, 255));
                }
                //Sunday set text red
                if ((i + hopping - 1) % 7 == 0) {
                    calBtn[i + 6 + hopping].setForeground(new Color(255, 0, 0));
                }

                calBtn[i + 6 + hopping].setText((i) + "");
            }
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

    private void checkpanel(String id, String pswd, ArrayList<String[]> classList, String pickDate, String information) {

        Database db = new Database();
        this.getId = id;
        this.pickDate = pickDate;
        this.information = information;

        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout());
        setLayout(new FlowLayout());
        frame.setSize(300, 300);
        JTextField dateText = new JTextField(null);
        JTextField infoText = new JTextField(null);
        dateText.setEditable(false);
        infoText.setEditable(false);
        dateText.setText(pickDate);
        infoText.setText(information);
        JRadioButton btnCa = new JRadioButton("CA", false);
        JRadioButton btnExam = new JRadioButton("EXAM", false);
        ButtonGroup caExam = new ButtonGroup();
        caExam.add(btnExam);
        caExam.add(btnCa);
        frame.add(dateText);
        frame.add(infoText);
        ButtonGroup radioButtonGroup = new ButtonGroup();
        for (int i = 0; i < classList.size(); i++) {
            JRadioButton button[] = new JRadioButton[classList.size()];
            String[] courseId_classId_List = new String[2];
            courseId_classId_List = getClassList.get(i);
            courseId = courseId_classId_List[0];
            classId = courseId_classId_List[1];
            button[i] = new JRadioButton(courseId +"," +classId, false);
            radioButtonGroup.add(button[i]);
            frame.add(button[i]);
            button[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String courseClassId = e.getActionCommand();
                    String[] list = courseClassId.split(",");
                    inputCourseId = list[0];
                    inputClassId = list[1];

                }
            });
        }

        JButton btnSave = new JButton("Save");

        frame.add(btnExam);
        frame.add(btnCa);
        frame.add(btnSave);
        frame.setVisible(true);
        frame.show();

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (btnCa.isSelected()) {
                    String type = "CA";
                    System.out.println(getId + pickDate + information + inputCourseId + inputClassId +  type);
                    db.calDBAdd(getId, pswd, inputCourseId, inputClassId, pickDate, information, type);

                } else if (btnExam.isSelected()) {
                    String type = "Exam";
                    System.out.println(getId + pickDate + information + inputCourseId + inputClassId +  type);
                    db.calDBAdd(getId, pswd, inputCourseId, inputClassId, pickDate, information, type);

                }
                //db.calDBAdd();
                frame.dispose();
            }
        });

    }

    public void hideInit() {
        for(int i = 0; i< calBtn.length; i++) {
            if((calBtn[i].getText()).equals(""))
                calBtn[i].setEnabled(false);
        }
    }

}