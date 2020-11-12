package com.supermarket.ui.supplierupdate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

@SuppressWarnings("all")
public class ModifySupplierJFrame extends JFrame {
    public ModifySupplierJFrame() throws SQLException {
        this.setTitle("修改供应商");
        this.setResizable(false);

        this.setSize(400, 600);
        int x1 = (int) (this.getToolkit().getScreenSize().getWidth() - this.getWidth()) / 2;
        int y1 = (int) (this.getToolkit().getScreenSize().getHeight() - this.getHeight()) / 2;
        this.setLocation(x1, y1);

        JPanel jPanel = new JPanel(null);
        this.add(jPanel);

        //----------------------------------------------------------

        JLabel jLabel_star_name = new JLabel("*");
        jLabel_star_name.setBounds(30, 40, 20, 20);
        jLabel_star_name.setForeground(Color.RED);
//        jLabel_star_name.setVisible(true);
        jPanel.add(jLabel_star_name);

        JLabel jLabel_nameInput = new JLabel("供应商名称：");
        jLabel_nameInput.setFont(new Font("微软雅黑", Font.BOLD, 15));
        jLabel_nameInput.setBounds(50, 40, 90, 20);
        jPanel.add(jLabel_nameInput);

        JTextField jTextField_nameinput = new JTextField(15);
        jTextField_nameinput.setBounds(140, 36, 250, 30);
        jPanel.add(jTextField_nameinput);

        //----------------------------------------------------------
        JLabel jLabel_description = new JLabel("供应商描述：");
        jLabel_description.setFont(new Font("微软雅黑", Font.BOLD, 15));
        jLabel_description.setBounds(50, 90, 90, 20);
        jPanel.add(jLabel_description);

        JTextArea jTextArea=new JTextArea();
        jTextArea.setBounds(143,86,242,152);
        jPanel.add(jTextArea);



        //----------------------------------------------------------

        JLabel jLabel_star_linkman = new JLabel("*");
        jLabel_star_linkman.setBounds(30, 260, 20, 20);
        jLabel_star_linkman.setForeground(Color.RED);
//        jLabel_star_name.setVisible(true);
        jPanel.add(jLabel_star_linkman);

        JLabel jLabel_linkman = new JLabel("联系人：");
        jLabel_linkman.setFont(new Font("微软雅黑", Font.BOLD, 15));
        jLabel_linkman.setBounds(50, 260, 90, 20);
        jPanel.add(jLabel_linkman);

        JTextField jTextField_linkman = new JTextField(15);
        jTextField_linkman.setBounds(140, 256, 250, 30);
        jPanel.add(jTextField_linkman);

        //----------------------------------------------------------

        JLabel jLabel_star_telphone = new JLabel("*");
        jLabel_star_telphone.setBounds(30, 310, 20, 20);
        jLabel_star_telphone.setForeground(Color.RED);
//        jLabel_star_name.setVisible(true);
        jPanel.add(jLabel_star_telphone);

        JLabel jLabel_telphone = new JLabel("供应商电话：");
        jLabel_telphone.setFont(new Font("微软雅黑", Font.BOLD, 15));
        jLabel_telphone.setBounds(50, 310, 90, 20);
        jPanel.add(jLabel_telphone);

        JTextField jTextField_telphone = new JTextField(15);
        jTextField_telphone.setBounds(140, 306, 250, 30);
        jPanel.add(jTextField_telphone);

        //----------------------------------------------------------
        JLabel jLabel_star_address = new JLabel("*");
        jLabel_star_address.setBounds(30, 360, 20, 20);
        jLabel_star_address.setForeground(Color.RED);
//        jLabel_star_name.setVisible(true);
        jPanel.add(jLabel_star_address);

        JLabel jLabel_address = new JLabel("供应商地址：");
        jLabel_address.setFont(new Font("微软雅黑", Font.BOLD, 15));
        jLabel_address.setBounds(50, 360, 90, 20);
        jPanel.add(jLabel_address);

        JTextField jTextField_address = new JTextField(15);
        jTextField_address.setBounds(140, 356, 250, 30);
        jPanel.add(jTextField_address);

        //----------------------------------------------------------
        JButton jButton_add = new JButton("修改");
        jButton_add.setFont(new Font("隶书", Font.BOLD, 15));
        jButton_add.setBounds(60, 530, 100, 40);
        jPanel.add(jButton_add);

        JButton jButton_close = new JButton("关闭");
        jButton_close.setFont(new Font("隶书", Font.BOLD, 15));
        jButton_close.setBounds(250, 530, 100, 40);
        jPanel.add(jButton_close);

        //获取jpanel中鼠标点击点的坐标
        jPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("x--->" + e.getX() + "\ty--->" + e.getY());
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
        new ModifySupplierJFrame();
    }
}
