package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagementFees extends Database {
    JFrame frame;
    JLabel title, amount;
    JTextField amountF;
    ButtonGroup radioGroupFee;
    JRadioButton rdbFullyP, rdbInstP;
    JButton btnChangeScreen, btnCancel;

    public ManagementFees() {
        Database db = new Database();

        //create JFrame
        frame = new JFrame();
        title = new JLabel("Adm. Manage of fees paid ", SwingConstants.CENTER);
        title.setBounds(0, 0, 640, 47);
        title.setBackground(new Color(39, 87, 144));
        title.setForeground(new Color(255, 250, 224));
        title.setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 20));
        title.setOpaque(true);

        amount = new JLabel("Amount Fee Paid");
        amount.setBounds(50, 375, 175, 20);
        amount.setFont(new Font("Serif", Font.BOLD, 15));

        amountF = new JTextField();
        amountF.setBounds(180, 375, 100, 25);

        rdbFullyP= new JRadioButton("Fully paid");
        rdbFullyP.setFont(new Font("Serif", Font.BOLD, 12));
        rdbFullyP.setBounds(50, 340, 100, 25);
        rdbInstP= new JRadioButton("Instalments paid");
        rdbInstP.setFont(new Font("Serif", Font.BOLD, 12));
        rdbInstP.setBounds(175, 340, 150, 25);

        radioGroupFee= new ButtonGroup();
        radioGroupFee.add(rdbFullyP);
        radioGroupFee.add(rdbInstP);

        btnChangeScreen = new JButton("Register");
        btnChangeScreen.setFont(new Font("Serif", Font.BOLD, 20));
        btnChangeScreen.setBounds(400, 350, 175, 50);
        btnCancel = new JButton("Cancel");
        btnCancel.setFont(new Font("Serif", Font.BOLD, 20));
        btnCancel.setBounds(400, 275, 175, 50);

        frame.add(rdbFullyP);
        frame.add(rdbInstP);
        frame.add(amount);
        frame.add(amountF);
        frame.add(btnChangeScreen);
        frame.add(btnCancel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(640, 480);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        btnCancel.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.setVisible(false);
                        Administrator administrator = new Administrator();
                        dispose();

                    }
                }
        );

    }
}
