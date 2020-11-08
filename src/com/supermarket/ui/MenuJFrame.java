package com.supermarket.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("all")
public class MenuJFrame extends JFrame implements Runnable{
    private String userName;
    private JLabel jLabelTime=null;
    public MenuJFrame(){
        //实现背景图片
        ImageIcon imageIcon=new ImageIcon("src/picture/MainFrame.png");
        JLabel jLabelImage=new JLabel(imageIcon);
        int x = imageIcon.getIconWidth();
        int y = imageIcon.getIconHeight();
        jLabelImage.setBounds(0,0,x,y);
        //得到第二层的pane
        Container container=this.getLayeredPane();
        //将图片放入第二层最底部
        container.add(jLabelImage, new Integer(Integer.MIN_VALUE));
        //获得第一层的panel
        JPanel jPanel = (JPanel) this.getContentPane();
        //设置第一层panel为透明
        jPanel.setOpaque(false);
        jPanel.setLayout(null);

        ImageIcon imageIcon_bill=new ImageIcon("src/picture/btn_bill.gif");
        ImageIcon imageIcon_supplier=new ImageIcon("src/picture/btn_suppliers.gif");
        ImageIcon imageIcon_user=new ImageIcon("src/picture/btn_users.gif");
        ImageIcon imageIcon_report=new ImageIcon("src/picture/btn_reports.gif");
        ImageIcon imageIcon_exit=new ImageIcon("src/picture/btn_exit.gif");

        JButton jButton_bill=new JButton(imageIcon_bill);
        JButton jButton_supplier=new JButton(imageIcon_supplier);
        JButton jButton_user=new JButton(imageIcon_user);
        JButton jButton_report=new JButton(imageIcon_report);
        JButton jButton_exit=new JButton(imageIcon_exit);

        JPanel jPanel_Left=new JPanel();
        jPanel_Left.setBounds(0,102,195,470);
//        jPanel_Left.setBackground(Color.PINK);
        jPanel.add(jPanel_Left);
        jPanel_Left.setLayout(new GridLayout(8,1,0,5));

        if(LoginToMenu.map.containsKey("部门经理")){
            jPanel_Left.add(jButton_bill);
            jPanel_Left.add(jButton_supplier);
            jPanel_Left.add(jButton_user);
            jPanel_Left.add(jButton_report);
            jPanel_Left.add(jButton_exit);
            userName= (String) LoginToMenu.map.get("部门经理");
        }else{
            jPanel_Left.add(jButton_bill);
            jPanel_Left.add(jButton_exit);
            userName= (String) LoginToMenu.map.get("普通员工");
        }

        jButton_exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginToMenu.map.clear();
                try {
                    new LoginJFrame();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                dispose();
            }
        });

        JPanel jPanel_North=new JPanel();
        jPanel_North.setOpaque(false);
        jPanel_North.setBounds(450,70,550,25);
        jPanel.add(jPanel_North);
        jPanel_North.add(new JLabel("欢迎您："+userName));
        jLabelTime=new JLabel();
        jPanel_North.add(jLabelTime);
        new Thread(this).start();

        JPanel jPanel_Right=new JPanel();
        jPanel_Right.setBounds(205,142,795,430);
//        jPanel_Right.setBackground(Color.PINK);
        jPanel.add(jPanel_Right);

        CardLayout cardLayout=new CardLayout();
        jPanel_Right.setLayout(cardLayout);

        JPanel jPanelWelcome = new JPanel();
        JLabel jLabelWelcome = new JLabel("                         欢迎使用超市管理系统！");
        jLabelWelcome.setFont(new Font("微软雅黑",Font.BOLD,30));
        jPanelWelcome.setLayout(new BorderLayout());
        jPanelWelcome.add(jLabelWelcome,BorderLayout.CENTER);
        jPanel_Right.add(jPanelWelcome, "欢迎界面");

        //获取jpanel中鼠标点击点的坐标
        jPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("x--->"+e.getX()+"\ty--->"+e.getY());
            }
        });

        int x1=(int)(this.getToolkit().getScreenSize().getWidth()-x)/2;
        int y1 = (int) (this.getToolkit().getScreenSize().getHeight() - y);
        this.setLocation(x1,y1);
        this.setSize(x,y);
//        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                LoginToMenu.map.clear();
                try {
                    new LoginJFrame();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                dispose();
            }
        });
        this.setResizable(false);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new MenuJFrame();
    }

    @Override
    public void run() {
        while(true) {
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String timeStr = sdf.format(date);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
//            timeLable.setText(timeStr);
            jLabelTime.setText("\t\t当前时间："+timeStr);
        }
    }
}
