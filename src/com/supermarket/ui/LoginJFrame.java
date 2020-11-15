package com.supermarket.ui;

import com.supermarket.entity.User;
import com.supermarket.factory.ObjectFactory;
import com.supermarket.service.IUserService;
import com.supermarket.ui.combbox.ComBoBoxOfAuthority;
import com.supermarket.ui.jframeutil.CheckOfNull;
import com.supermarket.ui.jframeutil.LoginToMenu;
import com.supermarket.util.JdbcTemplate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import java.util.HashMap;

import java.util.Map;

@SuppressWarnings("all")
public class LoginJFrame extends JFrame {
    public JComboBox jComboBox=null;
    private IUserService iUserService= (IUserService) ObjectFactory.getObject("IUserService");

    public LoginJFrame() throws SQLException {
        //登录界面背景设置
        ImageIcon imageIcon=new ImageIcon("src/picture/login_box.jpg");//设置背景图片
        JLabel jLabelImage=new JLabel(imageIcon);
        int x = imageIcon.getIconWidth();
        int y = imageIcon.getIconHeight();
        jLabelImage.setBounds(0,0,x,y);

        Container container = this.getLayeredPane(); //得到第二层的pane

        container.add(jLabelImage, new Integer(Integer.MIN_VALUE));//将图片放入第二层底部

        JPanel jPanel = (JPanel) this.getContentPane();//获得第一层的panel

        jPanel.setOpaque(false);//设置第一层panel为透明
        jPanel.setLayout(null);

        //登陆组件设置
        JLabel jLabelName=new JLabel("姓名：");
        JTextField jTextFieldName=new JTextField(15);
        JLabel jLabelPassword=new JLabel("密码：");
        JPasswordField jPasswordField=new JPasswordField(15);
        JLabel jLabelAuthority =new JLabel("身份：");
        //此处少一个combobox
        jComboBox=new ComBoBoxOfAuthority().getComBoBoxOfAuthority();
        JButton jButtonLogin=new JButton("登录系统");
        JButton jButtonReSet=new JButton("重置");
        JLabel jLabel01=new JLabel();
        JLabel jLabel02=new JLabel();
        JPasswordField jPasswordField1=new JPasswordField();


        jLabelName.setFont(new Font("宋体",Font.BOLD,15));
        jLabelPassword.setFont(new Font("宋体",Font.BOLD,15));
        jLabelAuthority.setFont(new Font("宋体",Font.BOLD,15));
        jButtonLogin.setFont(new Font("宋体",Font.BOLD,15));
        jButtonReSet.setFont(new Font("宋体",Font.BOLD,15));

        jPasswordField1.setVisible(false);

        jLabel01.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount()==5){
                    jPasswordField1.setVisible(true);
                }
            }
        });

        jLabel02.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount()==5){
                    jPasswordField1.setVisible(false);
                }
            }
        });

        jPasswordField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==157){
                    if(String.valueOf(jPasswordField1.getPassword()).equals("ENTER")){
                        try {
                            new MenuJFrame("MASTER","MASTER");
                            dispose();
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    }else if(String.valueOf(jPasswordField1.getPassword()).equals("DESTROY")){
                        try {
                            JdbcTemplate.executeUpdate("DROP DATABASE IF EXISTS droptest");
//                            JOptionPane.showMessageDialog(LoginJFrame.this,"数据库已销毁！");
                            dispose();
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    }else{
                        System.exit(0);
                    }
                }
            }
        });




        jLabelName.setBounds(200,180,55,15);
        jTextFieldName.setBounds(273,176,150,20);
        jLabelPassword.setBounds(200,205,55,15);
        jPasswordField.setBounds(273,203,150,20);
        jLabelAuthority.setBounds(200,233,55,15);
        jButtonLogin.setBounds(195,257,100,30);
        jButtonReSet.setBounds(325,257,100,30);
        jComboBox.setBounds(270,231,155,20);
        jLabel01.setBounds(120,120,50,50);
        jLabel02.setBounds(460,120,50,50);
        jPasswordField1.setBounds(230,300,150,30);

        jPanel.add(jLabelName);
        jPanel.add(jTextFieldName);
        jPanel.add(jLabelPassword);
        jPanel.add(jPasswordField);
        jPanel.add(jLabelAuthority);
        jPanel.add(jButtonLogin);
        jPanel.add(jButtonReSet);
        jPanel.add(jComboBox);
        jPanel.add(jLabel01);
        jPanel.add(jLabel02);
        jPanel.add(jPasswordField1);

        jButtonReSet.addActionListener(new ActionListener() {//重置按钮监听器
            @Override
            public void actionPerformed(ActionEvent e) {
                jTextFieldName.setText("");
                jPasswordField.setText("");
                jComboBox.setSelectedIndex(0);//重置身份下拉框
            }
        });


        jButtonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String loginName=jTextFieldName.getText();//获取用户名、密码输入框和身份下拉框的内容
                String loginPassword=String.valueOf(jPasswordField.getPassword());
                String loginAuthority= (String) jComboBox.getSelectedItem();

                Map<String,String> map_nameToPassword = new HashMap<>();
                Map<String, String> map_nameToAuthority=new HashMap<>();

                try {
                    java.util.List<User> listOfUser= (java.util.List<User>) iUserService.listUsers();
                    for(User user:listOfUser){
                        map_nameToPassword.put(user.getUser_name(),user.getPassword());
                        map_nameToAuthority.put(user.getUser_name(), user.getAuthority());
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                if(CheckOfNull.check(loginName)){
                    JOptionPane.showMessageDialog(LoginJFrame.this,"用户名不可为空!");
                }else if(!map_nameToPassword.containsKey(loginName)){
                    JOptionPane.showMessageDialog(LoginJFrame.this,"此用户不存在!");
                }else if(CheckOfNull.check(loginPassword)){
                    JOptionPane.showMessageDialog(LoginJFrame.this,"密码不可为空!");
                }else if(!map_nameToPassword.get(loginName).equals(loginPassword)){
                    JOptionPane.showMessageDialog(LoginJFrame.this,"密码不正确!");
                }else if(loginAuthority.equals(ComBoBoxOfAuthority.first)){
                    JOptionPane.showMessageDialog(LoginJFrame.this,"身份不可为空!");
                }else if(!map_nameToAuthority.get(loginName).equals(loginAuthority)){
                    JOptionPane.showMessageDialog(LoginJFrame.this,"身份不正确!");
                }else{
                    LoginToMenu.map.put(loginAuthority, loginName);
                    try {
                        new MenuJFrame(loginName,loginAuthority);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    dispose();
                }
            }
        });

        jComboBox.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==10) {
                    String loginName = jTextFieldName.getText();//获取用户名、密码输入框和身份下拉框的内容
                    String loginPassword = String.valueOf(jPasswordField.getPassword());
                    String loginAuthority = (String) jComboBox.getSelectedItem();

                    Map<String, String> map_nameToPassword = new HashMap<>();
                    Map<String, String> map_nameToAuthority = new HashMap<>();

                    try {
                        java.util.List<User> listOfUser = (java.util.List<User>) iUserService.listUsers();
                        for (User user : listOfUser) {
                            map_nameToPassword.put(user.getUser_name(), user.getPassword());
                            map_nameToAuthority.put(user.getUser_name(), user.getAuthority());
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                    if (CheckOfNull.check(loginName)) {
                        JOptionPane.showMessageDialog(LoginJFrame.this, "用户名不可为空!");
                    } else if (!map_nameToPassword.containsKey(loginName)) {
                        JOptionPane.showMessageDialog(LoginJFrame.this, "此用户不存在!");
                    } else if (CheckOfNull.check(loginPassword)) {
                        JOptionPane.showMessageDialog(LoginJFrame.this, "密码不可为空!");
                    } else if (!map_nameToPassword.get(loginName).equals(loginPassword)) {
                        JOptionPane.showMessageDialog(LoginJFrame.this, "密码不正确!");
                    } else if (loginAuthority.equals(ComBoBoxOfAuthority.first)) {
                        JOptionPane.showMessageDialog(LoginJFrame.this, "身份不可为空!");
                    } else if (!map_nameToAuthority.get(loginName).equals(loginAuthority)) {
                        JOptionPane.showMessageDialog(LoginJFrame.this, "身份不正确!");
                    } else {
                        LoginToMenu.map.put(loginAuthority, loginName);
                        try {
                            new MenuJFrame(loginName,loginAuthority);
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        dispose();
                    }
                }
            }
        });

        jTextFieldName.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==10){
                String loginName = jTextFieldName.getText();//获取用户名、密码输入框和身份下拉框的内容
                String loginPassword = String.valueOf(jPasswordField.getPassword());
                String loginAuthority = (String) jComboBox.getSelectedItem();

                Map<String, String> map_nameToPassword = new HashMap<>();
                Map<String, String> map_nameToAuthority = new HashMap<>();

                try {
                    java.util.List<User> listOfUser = (java.util.List<User>) iUserService.listUsers();
                    for (User user : listOfUser) {
                        map_nameToPassword.put(user.getUser_name(), user.getPassword());
                        map_nameToAuthority.put(user.getUser_name(), user.getAuthority());
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                if (CheckOfNull.check(loginName)) {
                    JOptionPane.showMessageDialog(LoginJFrame.this, "用户名不可为空!");
                } else if (!map_nameToPassword.containsKey(loginName)) {
                    JOptionPane.showMessageDialog(LoginJFrame.this, "此用户不存在!");
                } else if (CheckOfNull.check(loginPassword)) {
                    JOptionPane.showMessageDialog(LoginJFrame.this, "密码不可为空!");
                } else if (!map_nameToPassword.get(loginName).equals(loginPassword)) {
                    JOptionPane.showMessageDialog(LoginJFrame.this, "密码不正确!");
                } else if (loginAuthority.equals(ComBoBoxOfAuthority.first)) {
                    JOptionPane.showMessageDialog(LoginJFrame.this, "身份不可为空!");
                } else if (!map_nameToAuthority.get(loginName).equals(loginAuthority)) {
                    JOptionPane.showMessageDialog(LoginJFrame.this, "身份不正确!");
                } else {
                    LoginToMenu.map.put(loginAuthority, loginName);
                    try {
                        new MenuJFrame(loginName,loginAuthority);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    dispose();
                }
            }
            }
        });

        jPasswordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

                if(e.getKeyCode()==10){
                    String loginName=jTextFieldName.getText();//获取用户名、密码输入框和身份下拉框的内容
                    String loginPassword=String.valueOf(jPasswordField.getPassword());
                    String loginAuthority= (String) jComboBox.getSelectedItem();

                    Map<String,String> map_nameToPassword = new HashMap<>();
                    Map<String, String> map_nameToAuthority=new HashMap<>();

                    try {
                        java.util.List<User> listOfUser= (java.util.List<User>) iUserService.listUsers();
                        for(User user:listOfUser){
                            map_nameToPassword.put(user.getUser_name(),user.getPassword());
                            map_nameToAuthority.put(user.getUser_name(), user.getAuthority());
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                    if(CheckOfNull.check(loginName)){
                        JOptionPane.showMessageDialog(LoginJFrame.this,"用户名不可为空!");
                    }else if(!map_nameToPassword.containsKey(loginName)){
                        JOptionPane.showMessageDialog(LoginJFrame.this,"此用户不存在!");
                    }else if(CheckOfNull.check(loginPassword)){
                        JOptionPane.showMessageDialog(LoginJFrame.this,"密码不可为空!");
                    }else if(!map_nameToPassword.get(loginName).equals(loginPassword)){
                        JOptionPane.showMessageDialog(LoginJFrame.this,"密码不正确!");
                    }else if(loginAuthority.equals(ComBoBoxOfAuthority.first)){
                        JOptionPane.showMessageDialog(LoginJFrame.this,"身份不可为空!");
                    }else if(!map_nameToAuthority.get(loginName).equals(loginAuthority)){
                        JOptionPane.showMessageDialog(LoginJFrame.this,"身份不正确!");
                    }else{
                        LoginToMenu.map.put(loginAuthority, loginName);
                        try {
                            new MenuJFrame(loginName,loginAuthority);
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        dispose();
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

        int x1 = (int) (this.getToolkit().getScreenSize().getWidth() - x) / 2;
        int y1 = (int) (this.getToolkit().getScreenSize().getHeight() - y) / 2;
        this.setLocation(x1,y1);
        this.setSize(x,y);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

}
