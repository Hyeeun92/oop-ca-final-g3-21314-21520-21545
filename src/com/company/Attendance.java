package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Attendance extends JFrame{
    String id;
    String pickdate;
    ArrayList<String[]> classList;
    JFrame frame;
    JLabel[] label;
    String getCourseId;
    String getClassId;
    ArrayList<String> studentList = new ArrayList<>();

    public Attendance(String getId, String pickDate, ArrayList<String[]> getClassList) {

        Database db = new Database();
        this.id = getId;
        this.pickdate = pickDate;
        this.classList = getClassList;
        String courseId;
        String classId;

        frame = new JFrame();
        frame.setSize(700, 700);

        frame.setLayout(new FlowLayout());

        JButton[] pickclass = new JButton[classList.size()];

        for (int i = 0; i < classList.size(); i++) {
            String[] list = new String[2];
            list = classList.get(i);
            courseId = list[0];
            classId = list[1];
            pickclass[i] = new JButton(courseId + " / " + classId );
            frame.add(pickclass[i]);
            pickclass[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String courseClassId = e.getActionCommand();
                    String[] list = courseClassId.split(" / ");
                    getCourseId = list[0];
                    getClassId = list[1];
                    studentList = db.studentListForAttandance(getCourseId, getClassId);
                    System.out.println(studentList.get(0));

                    JTextField[] nameField = new JTextField[studentList.size()+1];

                    for (int i = 0; i < studentList.size(); i++) {

                        if (studentList.get(i)!=null) {
                            nameField[i].setText(studentList.get(i));
                            nameField[i].setEditable(false);
                        }

                        JRadioButton firstPresent = new JRadioButton("First class - Present", false);
                        JRadioButton firstAbsent = new JRadioButton("First class - Absent", false);
                        ButtonGroup group1 = new ButtonGroup();
                        group1.add(firstAbsent);
                        group1.add(firstPresent);
                        JRadioButton secondPresent = new JRadioButton("Second class - Present", false);
                        JRadioButton secondAbsent = new JRadioButton("Second class - Absent", false);
                        ButtonGroup group2 = new ButtonGroup();
                        group2.add(secondPresent);
                        group2.add(secondAbsent);

                        frame.add(nameField[i]);
                        frame.add(firstPresent);
                        frame.add(firstAbsent);
                        frame.add(secondPresent);
                        frame.add(secondAbsent);

                    }
                }

            });
        }


        frame.setVisible(true);

    }
}
