package com.company;

/* 21545 - Hyeeun Lee
21520 - Liubov Eremenko
21314 - Nathalie Flores
 */

import javax.swing.*;
import java.awt.*;

public class Results extends Database { //action listener interface

    JFrame frame;
    JLabel title;

    public Results(){
        Database db = new Database();
        //List<AdmFunctions> getList = db.getList();

       //create JFrame
        frame = new JFrame();
        title = new JLabel("Student list of results",SwingConstants.CENTER);
        title.setBounds(0, 0, 640, 47);
        title.setBackground(new Color(39, 87, 144));
        title.setForeground(new Color(255, 250, 224));
        title.setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 20));
        title.setOpaque(true);




        /*

        btnChangeScreen.addActionListener();

         */


        frame.add(title);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(640, 480);
        frame.setVisible(true);

    }

}
