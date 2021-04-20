package com.company;

/*
21545 - Hyeeun Lee
21520 - Liubov Eremenko
21314 - Nathalie Flores
*/


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RenewLogin extends JFrame {

    JFrame frame;
    JButton btnSave;
    JTextField idField;
    JPasswordField pswdField, newField, confirmField;
    JLabel title, idLabel, pswdLabel, newLabel, confirmLabel, messageLabel;
    String pswd = null;
    String pswdNew = null;
    String pswdConfirm = null;
    String id = null;

    public RenewLogin() {

        Database db = new Database();

        //create JFrame
        frame = new JFrame();
        title = new JLabel("Virtual Global College (VGC)",SwingConstants.CENTER);
        title.setBounds(0, 0, 640, 47);
        title.setBackground(new Color(0, 0, 130));
        title.setForeground(new Color(255, 250, 224));
        title.setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 20));
        title.setOpaque(true);

        btnSave = new JButton("Save");

        idField = new JTextField(null);
        pswdField = new JPasswordField(null);
        newField = new JPasswordField(null);
        confirmField = new JPasswordField(null);

        idLabel = new JLabel("ID:");
        pswdLabel = new JLabel("Current password:");
        newLabel = new JLabel("New password:");
        confirmLabel = new JLabel("Confirm new password:");

        messageLabel = new JLabel();

        idLabel.setBounds(100, 125, 150, 25);
        idLabel.setFont(new Font("Serif", Font.BOLD, 16));
        pswdLabel.setBounds(100, 175, 150, 25);
        pswdLabel.setFont(new Font("Serif", Font.BOLD, 16));
        newLabel.setBounds(100, 225, 150, 25);
        newLabel.setFont(new Font("Serif", Font.BOLD, 16));
        confirmLabel.setBounds(100, 275, 170, 25);
        confirmLabel.setFont(new Font("Serif", Font.BOLD, 16));


        messageLabel.setBounds(325, 250, 250, 35);
        messageLabel.setFont(new Font(null, Font.BOLD, 25));

        idField.setBounds(100, 149, 300, 25);
        pswdField.setBounds(100, 199, 300, 25);
        newField.setBounds(100, 249, 300, 25);
        confirmField.setBounds(100,299,300, 25);

        btnSave.setBounds(375, 350, 175, 50);
        btnSave.setFont(new Font("Serif", Font.BOLD, 20));
        btnSave.setFocusable(false);



        frame.add(title);
        frame.add(idLabel);
        frame.add(pswdLabel);
        frame.add(newLabel);
        frame.add(confirmLabel);
        frame.add(messageLabel);

        frame.add(idField);
        frame.add(pswdField);
        frame.add(newField);
        frame.add(confirmField);
        frame.add(btnSave);



        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(640, 480);
        frame.setLayout(null);
        frame.setVisible(true);

        btnSave.addActionListener(
               new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                        String id, pswdOld, pswdNew, pswdConfirm;
                        id = idField.getText();
                        pswd = pswdField.getPassword();
                        pswdNew = newField.getPassword();
                        pswdConfirm = confirmField.getPassword();
                        String db1 = "select * from student where student_id = '"+id+"' ";
                        String db2 = "update student set student_password ='"+pswdNew+"' where student_id = '"+id+"' ";

                  if (pswdNew.equals(pswdConfirm)){

                            try {
                                st=con.prepareStatement(db.getStudentLoginInfo(id, pswd);
                                if() {
                                    st=con.prepareStatement("update login set password = ? where id = ?" );
                                    st.setString( , pswdNew);
                                    st.setString( , id);
                                    JOptionPane.showMessageDialog(null, "Password Changed Successfully");
                                                db //set new pswd to
                                                    id.setText("");
                                                    pswd ;
                                                    pswdNew;
                                                    pswdConfirm ;

                                }else {
                                    JOptionPane.showMessageDialog(null, "Invalid ID or Old password");

                                } catch (Exception e) {
                                    System.out.println(e);
                                }
                                }

                        } else {
                            JOptionPane.showMessageDialog(null, "New Password does not match with Confirm Password");

                        }

                        id = pswdField.getPassword();
                        pswd = String.valueOf(newField.getPassword());

                        /*if (adminRadio.isSelected()) {
                           db.getAdminLoginInfo(id, pswd);
                        } else if (lectureRadio.isSelected()) {
                            db.getLectureLoginInfo(id, pswd);
                        } else if (studentRadio.isSelected()) {
                          db.getStudentLoginInfo(id,pswd);*/
                       }
                    }
               }
        );
    }



}
