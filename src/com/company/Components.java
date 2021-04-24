package com.company;

import javax.swing.*;
import java.awt.*;

public class Components {
    public static void Run() {
        firstFrame();

    }
    private static void firstFrame(){
        LoginPage login = new LoginPage();
        login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        login.setResizable(false); //prevent frame from being resized
        login.setSize(640, 480);
        login.setVisible(true);
        login.setLocationRelativeTo(null); // make the frame center
    }
}
