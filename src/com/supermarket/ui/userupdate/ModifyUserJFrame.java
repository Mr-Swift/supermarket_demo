package com.supermarket.ui.userupdate;

import com.supermarket.ui.combbox.ComBoBoxOfAuthority;
import com.supermarket.ui.combbox.ComBoBoxOfSex;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

@SuppressWarnings("all")
public class ModifyUserJFrame extends JFrame {
    JComboBox jcomboBox_sex=null;
    JComboBox jcomboBox_autuority=null;

    public ModifyUserJFrame() throws SQLException {
        this.setTitle("修改用户");
        this.setResizable(false);


        this.setSize(400,600);
        int x1 = (int) (this.getToolkit().getScreenSize().getWidth() - this.getWidth()) / 2;
        int y1 = (int) (this.getToolkit().getScreenSize().getHeight() -this.getHeight()) / 2;
        this.setLocation(x1,y1);

        JPanel jPanel=new JPanel(null);
        this.add(jPanel);

        //----------------------------------------------------------

        JLabel jLabel_star_name=new JLabel("*");
        jLabel_star_name.setBounds(40,40,20,20);
        jLabel_star_name.setForeground(Color.RED);
//        jLabel_star_name.setVisible(true);
        jPanel.add(jLabel_star_name);

        JLabel jLabel_nameInput = new JLabel("用户名称：");
        jLabel_nameInput.setFont(new Font("微软雅黑",Font.BOLD,15));
        jLabel_nameInput.setBounds(60,40,80,20);
        jPanel.add(jLabel_nameInput);

        JTextField jTextField_nameinput = new JTextField(15);
        jTextField_nameinput.setBounds(140,36,250,30);
        jPanel.add(jTextField_nameinput);

        //----------------------------------------------------------

        JLabel jLabel_star_password=new JLabel("*");
        jLabel_star_password.setBounds(40,85,20,20);
        jLabel_star_password.setForeground(Color.RED);
//        jLabel_star_name.setVisible(true);
        jPanel.add(jLabel_star_password);

        JLabel jLabel_password = new JLabel("用户密码：");
        jLabel_password.setFont(new Font("微软雅黑",Font.BOLD,15));
        jLabel_password.setBounds(60,85,80,20);
        jPanel.add(jLabel_password);

        JTextField jTextField_password=new JTextField();
        jTextField_password.setBounds(140,81,250,30);
        jPanel.add(jTextField_password);

        //----------------------------------------------------------
        JLabel jLabel_star_confirm=new JLabel("*");
        jLabel_star_confirm.setBounds(40,135,20,20);
        jLabel_star_confirm.setForeground(Color.RED);
//        jLabel_star_name.setVisible(true);
        jPanel.add(jLabel_star_confirm);

        JLabel jLabel_confirm = new JLabel("确认密码：");
        jLabel_confirm.setFont(new Font("微软雅黑",Font.BOLD,15));
        jLabel_confirm.setBounds(60,135,80,20);
        jPanel.add(jLabel_confirm);

        JTextField jTextField_confirm=new JTextField();
        jTextField_confirm.setBounds(140,131,250,30);
        jPanel.add(jTextField_confirm);

        //----------------------------------------------------------
        JLabel jLabel_star_sex=new JLabel("*");
        jLabel_star_sex.setBounds(40,185,20,20);
        jLabel_star_sex.setForeground(Color.RED);
//        jLabel_star_name.setVisible(true);
        jPanel.add(jLabel_star_sex);

        JLabel jLabel_sex = new JLabel("用户性别：");
        jLabel_sex.setFont(new Font("微软雅黑",Font.BOLD,15));
        jLabel_sex.setBounds(60,185,80,20);
        jPanel.add(jLabel_sex);

        jcomboBox_sex = ComBoBoxOfSex.getComBoBoxOfSex();
        jcomboBox_sex.setBounds(140,181,250,30);
        jPanel.add(jcomboBox_sex);

        //----------------------------------------------------------
        JLabel jLabel_star_age=new JLabel("*");
        jLabel_star_age.setBounds(40,235,20,20);
        jLabel_star_age.setForeground(Color.RED);
//        jLabel_star_name.setVisible(true);
        jPanel.add(jLabel_star_age);

        JLabel jLabel_age = new JLabel("用户年龄：");
        jLabel_age.setFont(new Font("微软雅黑",Font.BOLD,15));
        jLabel_age.setBounds(60,235,80,20);
        jPanel.add(jLabel_age);

        JTextField jTextField_age=new JTextField();
        jTextField_age.setBounds(140,231,250,30);
        jPanel.add(jTextField_age);

        //----------------------------------------------------------

        JLabel jLabel_star_telphone=new JLabel("*");
        jLabel_star_telphone.setBounds(40,285,20,20);
        jLabel_star_telphone.setForeground(Color.RED);
//        jLabel_star_name.setVisible(true);
        jPanel.add(jLabel_star_telphone);

        JLabel jLabel_telphone = new JLabel("用户电话：");
        jLabel_telphone.setFont(new Font("微软雅黑",Font.BOLD,15));
        jLabel_telphone.setBounds(60,285,80,20);
        jPanel.add(jLabel_telphone);

        JTextField jTextField_telphone=new JTextField();
        jTextField_telphone.setBounds(140,281,250,30);
        jPanel.add(jTextField_telphone);

        //----------------------------------------------------------
        JLabel jLabel_address = new JLabel("用户地址：");
        jLabel_address.setFont(new Font("微软雅黑",Font.BOLD,15));
        jLabel_address.setBounds(60,335,80,20);
        jPanel.add(jLabel_address);

        JTextArea jTextArea=new JTextArea();
        jTextArea.setBounds(145,331,243,100);
        jPanel.add(jTextArea);

        //----------------------------------------------------------
        JLabel jLabel_star_authority=new JLabel("*");
        jLabel_star_authority.setBounds(40,455,20,20);
        jLabel_star_authority.setForeground(Color.RED);
        jPanel.add(jLabel_star_authority);

        JLabel jLabel_authority = new JLabel("用户权限：");
        jLabel_authority.setFont(new Font("微软雅黑",Font.BOLD,15));
        jLabel_authority.setBounds(60,455,80,20);
        jPanel.add(jLabel_authority);

        jcomboBox_autuority = ComBoBoxOfAuthority.getComBoBoxOfAuthority();
        jcomboBox_autuority.setBounds(140,451,250,30);
        jPanel.add(jcomboBox_autuority);

        //----------------------------------------------------------
        JButton jButton_add=new JButton("修改");
        jButton_add.setFont(new Font("隶书",Font.BOLD,15));
        jButton_add.setBounds(60,530,100,40);
        jPanel.add(jButton_add);

        JButton jButton_close=new JButton("关闭");
        jButton_close.setFont(new Font("隶书",Font.BOLD,15));
        jButton_close.setBounds(250,530,100,40);
        jPanel.add(jButton_close);

        //获取jpanel中鼠标点击点的坐标
        jPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("x--->"+e.getX()+"\ty--->"+e.getY());
            }
        });


        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
        this.setVisible(true);
    }

    public static void main(String[] args) throws SQLException {
        new ModifyUserJFrame();
    }
}
