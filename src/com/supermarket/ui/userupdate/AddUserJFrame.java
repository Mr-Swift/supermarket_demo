package com.supermarket.ui.userupdate;

import com.supermarket.entity.User;
import com.supermarket.factory.ObjectFactory;
import com.supermarket.service.IUserService;
import com.supermarket.tmodel.UserTableModel;
import com.supermarket.ui.combbox.ComBoBoxOfAuthority;
import com.supermarket.ui.combbox.ComBoBoxOfSex;
import com.supermarket.ui.jframeutil.CheckOfNull;
import com.supermarket.ui.jframeutil.CheckOfPhone;
import com.supermarket.ui.supplierupdate.AddSupplierJFrame;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

@SuppressWarnings("all")
public class AddUserJFrame extends JFrame implements Runnable{
    JLabel jLabel_star_name=null;
    JTextField jTextField_nameinput=null;

    JLabel jLabel_star_password=null;
    JPasswordField jPasswordField_password=null;

    JLabel jLabel_star_confirm=null;
    JPasswordField jPasswordField_confirm=null;

    JLabel jLabel_star_sex=null;
    JComboBox jcomboBox_sex=null;

    JLabel jLabel_star_age=null;
    JTextField jTextField_age=null;

    JLabel jLabel_star_telphone=null;
    JTextField jTextField_telphone=null;

    JTextArea jTextArea=null;

    JLabel jLabel_star_authority=null;
    JComboBox jcomboBox_autuority=null;

    IUserService iUserService= (IUserService) ObjectFactory.getObject("IUserService");

    public AddUserJFrame(JTable jTable) throws SQLException {
        this.setTitle("添加用户");
        this.setResizable(false);


        this.setSize(400,600);
        int x1 = (int) (this.getToolkit().getScreenSize().getWidth() - this.getWidth()) / 2;
        int y1 = (int) (this.getToolkit().getScreenSize().getHeight() -this.getHeight()) / 2;
        this.setLocation(x1,y1);

        JPanel jPanel=new JPanel(null);
        this.add(jPanel);

        //----------------------------------------------------------

        jLabel_star_name=new JLabel("*");
        jLabel_star_name.setBounds(40,40,20,20);
        jLabel_star_name.setForeground(Color.RED);
        jPanel.add(jLabel_star_name);

        JLabel jLabel_nameInput = new JLabel("用户名称：");
        jLabel_nameInput.setFont(new Font("微软雅黑",Font.BOLD,15));
        jLabel_nameInput.setBounds(60,40,80,20);
        jPanel.add(jLabel_nameInput);

        jTextField_nameinput = new JTextField(15);
        jTextField_nameinput.setBounds(140,36,250,30);
        jPanel.add(jTextField_nameinput);

        //----------------------------------------------------------

        jLabel_star_password=new JLabel("*");
        jLabel_star_password.setBounds(40,85,20,20);
        jLabel_star_password.setForeground(Color.RED);
        jPanel.add(jLabel_star_password);

        JLabel jLabel_password = new JLabel("用户密码：");
        jLabel_password.setFont(new Font("微软雅黑",Font.BOLD,15));
        jLabel_password.setBounds(60,85,80,20);
        jPanel.add(jLabel_password);

        jPasswordField_password=new JPasswordField();
        jPasswordField_password.setBounds(140,81,250,30);
        jPanel.add(jPasswordField_password);

        //----------------------------------------------------------
        jLabel_star_confirm=new JLabel("*");
        jLabel_star_confirm.setBounds(40,135,20,20);
        jLabel_star_confirm.setForeground(Color.RED);
        jPanel.add(jLabel_star_confirm);

        JLabel jLabel_confirm = new JLabel("确认密码：");
        jLabel_confirm.setFont(new Font("微软雅黑",Font.BOLD,15));
        jLabel_confirm.setBounds(60,135,80,20);
        jPanel.add(jLabel_confirm);

        jPasswordField_confirm=new JPasswordField();
        jPasswordField_confirm.setBounds(140,131,250,30);
        jPanel.add(jPasswordField_confirm);

        //----------------------------------------------------------
        jLabel_star_sex=new JLabel("*");
        jLabel_star_sex.setBounds(40,185,20,20);
        jLabel_star_sex.setForeground(Color.RED);
        jPanel.add(jLabel_star_sex);

        JLabel jLabel_sex = new JLabel("用户性别：");
        jLabel_sex.setFont(new Font("微软雅黑",Font.BOLD,15));
        jLabel_sex.setBounds(60,185,80,20);
        jPanel.add(jLabel_sex);

        jcomboBox_sex = ComBoBoxOfSex.getComBoBoxOfSex();
        jcomboBox_sex.setBounds(140,181,250,30);
        jPanel.add(jcomboBox_sex);

        //----------------------------------------------------------
        jLabel_star_age=new JLabel("*");
        jLabel_star_age.setBounds(40,235,20,20);
        jLabel_star_age.setForeground(Color.RED);
        jPanel.add(jLabel_star_age);

        JLabel jLabel_age = new JLabel("用户年龄：");
        jLabel_age.setFont(new Font("微软雅黑",Font.BOLD,15));
        jLabel_age.setBounds(60,235,80,20);
        jPanel.add(jLabel_age);

        jTextField_age=new JTextField();
        jTextField_age.setBounds(140,231,250,30);
        jPanel.add(jTextField_age);

        //----------------------------------------------------------

        jLabel_star_telphone=new JLabel("*");
        jLabel_star_telphone.setBounds(40,285,20,20);
        jLabel_star_telphone.setForeground(Color.RED);
        jPanel.add(jLabel_star_telphone);

        JLabel jLabel_telphone = new JLabel("用户电话：");
        jLabel_telphone.setFont(new Font("微软雅黑",Font.BOLD,15));
        jLabel_telphone.setBounds(60,285,80,20);
        jPanel.add(jLabel_telphone);

        jTextField_telphone=new JTextField();
        jTextField_telphone.setBounds(140,281,250,30);
        jPanel.add(jTextField_telphone);

        //----------------------------------------------------------
        JLabel jLabel_address = new JLabel("用户地址：");
        jLabel_address.setFont(new Font("微软雅黑",Font.BOLD,15));
        jLabel_address.setBounds(60,335,80,20);
        jPanel.add(jLabel_address);

        jTextArea=new JTextArea();
        jTextArea.setBounds(145,331,243,100);
        jPanel.add(jTextArea);

        //----------------------------------------------------------
        jLabel_star_authority=new JLabel("*");
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
        JButton jButton_add=new JButton("添加");
        jButton_add.setFont(new Font("隶书",Font.BOLD,15));
        jButton_add.setBounds(60,530,100,40);
        jPanel.add(jButton_add);

        JButton jButton_close=new JButton("关闭");
        jButton_close.setFont(new Font("隶书",Font.BOLD,15));
        jButton_close.setBounds(250,530,100,40);
        jPanel.add(jButton_close);

        jButton_close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        jButton_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(new CheckOfNull().check(jTextField_nameinput.getText())){
                    JOptionPane.showMessageDialog(AddUserJFrame.this,"用户名称不可为空!");
                }else if(jTextField_nameinput.getText().equals("MASTER")){
                    JOptionPane.showMessageDialog(AddUserJFrame.this,"你无权使用这个用户名!");
                }else if(CheckOfNull.check(String.valueOf(jPasswordField_password.getPassword()))){
                    JOptionPane.showMessageDialog(AddUserJFrame.this,"用户密码不可为空!");
                }else if(CheckOfNull.check(String.valueOf(jPasswordField_confirm.getPassword()))){
                    JOptionPane.showMessageDialog(AddUserJFrame.this,"确认密码不可为空!");
                }else if(!String.valueOf(jPasswordField_confirm.getPassword()).equals(String.valueOf(jPasswordField_password.getPassword()))){
                    JOptionPane.showMessageDialog(AddUserJFrame.this,"两次输入的密码不一致!");
                }else if(jcomboBox_sex.getSelectedItem().equals("请选择")){
                    JOptionPane.showMessageDialog(AddUserJFrame.this,"请选择用户性别!");
                }else if(CheckOfNull.check(jTextField_age.getText())){
                    JOptionPane.showMessageDialog(AddUserJFrame.this,"用户年龄不可为空!");
                }else if(Integer.parseInt(jTextField_age.getText())<18){
                    JOptionPane.showMessageDialog(AddUserJFrame.this,"此用户未成年!");
                }else if(CheckOfNull.check(jLabel_star_telphone.getText())){
                    JOptionPane.showMessageDialog(AddUserJFrame.this,"用户电话不可为空!");
                }else if(!CheckOfPhone.checkPhone(jTextField_telphone.getText().trim())){
                    JOptionPane.showMessageDialog(AddUserJFrame.this,"电话号码格式不正确！");
                }else if(jcomboBox_autuority.getSelectedItem().equals("请选择身份")){
                    JOptionPane.showMessageDialog(AddUserJFrame.this,"请选择用户权限!");
                }else{
                    /**预留*/
                    try {
                        boolean checkOfSuccess=insertUser();
                        if(checkOfSuccess){
                            JOptionPane.showMessageDialog(AddUserJFrame.this,"添加成功！");
                            jTable.setModel(new UserTableModel());
                            dispose();
                        }else{
                            JOptionPane.showMessageDialog(AddUserJFrame.this,"添加失败！");
                        }
                    } catch (SQLException throwables) {
                        JOptionPane.showMessageDialog(AddUserJFrame.this,"添加失败！");
                        throwables.printStackTrace();
                    }
                }

            }
        });

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

        Thread threadCheck_user_add=new Thread(this);
        threadCheck_user_add.start();
        this.setVisible(true);
    }

    private boolean insertUser() throws SQLException {
        User user=new User();
        user.setUser_name(jTextField_nameinput.getText());
        user.setPassword(String.valueOf(jPasswordField_password.getPassword()));
        user.setSex((String) jcomboBox_sex.getSelectedItem());
        user.setAge(Integer.parseInt(jTextField_age.getText()));
        user.setUser_telphone(jTextField_telphone.getText());
        user.setUser_address((String)jTextArea.getText());
        user.setAuthority((String) jcomboBox_autuority.getSelectedItem());
        int countPrevious= (int) iUserService.getCountsOfId();
        int result = (int) iUserService.insertUser(user);
        int countNow= (int) iUserService.getCountsOfId();
        if(result>0 && countNow>countPrevious){
            return true;
        }else{
            return false;
        }

    }

    @Override
    public void run() {
        while (true){
            if(new CheckOfNull().check(jTextField_nameinput.getText())){
                jLabel_star_name.setVisible(true);
            }else{
                jLabel_star_name.setVisible(false);
            }

            if(CheckOfNull.check(String.valueOf(jPasswordField_password.getPassword()))){
                jLabel_star_password.setVisible(true);
            }else{
                jLabel_star_password.setVisible(false);
            }

            if(CheckOfNull.check(String.valueOf(jPasswordField_confirm.getPassword()))){
                jLabel_star_confirm.setVisible(true);
            }else{
                jLabel_star_confirm.setVisible(false);
            }


            if(jcomboBox_sex.getSelectedItem().equals("请选择")){
                jLabel_star_sex.setVisible(true);
            }else{
                jLabel_star_sex.setVisible(false);
            }

            if(CheckOfNull.check(jTextField_age.getText())){
                jLabel_star_age.setVisible(true);
            }else{
                jLabel_star_age.setVisible(false);
            }

            if(CheckOfNull.check(jTextField_telphone.getText())){
                jLabel_star_telphone.setVisible(true);
            }else{
                jLabel_star_telphone.setVisible(false);
            }

            if (jcomboBox_autuority.getSelectedItem().equals("请选择身份")) {
                jLabel_star_authority.setVisible(true);
            }else{
                jLabel_star_authority.setVisible(false);
            }
        }
    }
}
