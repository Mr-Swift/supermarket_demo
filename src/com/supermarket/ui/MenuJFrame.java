package com.supermarket.ui;

import com.supermarket.factory.ObjectFactory;
import com.supermarket.service.*;
import com.supermarket.tmodel.*;
import com.supermarket.ui.billupdate.AddAccountJFrame;
import com.supermarket.ui.billupdate.ModifyAccountJFrame;
import com.supermarket.ui.combbox.ComBoBoxOfPayCheck;
import com.supermarket.ui.jframeutil.CheckOfNull;
import com.supermarket.ui.jframeutil.LoginToMenu;
import com.supermarket.ui.supplierupdate.AddSupplierJFrame;
import com.supermarket.ui.userupdate.AddUserJFrame;
import com.supermarket.ui.userupdate.ModifyUserJFrame;
import com.supermarket.util.FileActionUtil;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("all")

public class MenuJFrame extends JFrame implements Runnable{
    String userName;
    JLabel jLabelTime=null;

    JTable jTable_bill=null;
    JTable jTable_supplier=null;
    JTable jTable_user=null;
    JTable jTable_report_commodity=null;
    JTable jTable_report_supplier=null;

    JLabel jLabelPageInfo_bill = null;
    JLabel jLabelPageInfo_supplier = null;
    JLabel jLabelPageInfo_user = null;
    JLabel jLabelPageInfo_report_commodity = null;
    JLabel jLabelPageInfo_report_supplier = null;

    JComboBox jComboBox_CheckOfPay=null;


    //当前页
    private int currentPage_bill=1;
    private int currentPage_supplier=1;
    private int currentPage_user=1;
    private int currentPage_report_commodity=1;
    private int currentPage_report_supplier=1;
    //每页显示5条数据
    private int pageSize=5;

    private int allPages_bill = 0;
    private int allCounts_bill=0;

    private int allPages_supplier = 0;
    private int allCounts_supplier=0;

    private int allPages_user = 0;
    private int allCounts_user=0;

    private int allPages_report_commodity = 0;
    private int allCounts_report_commodity=0;

    private int allPages_report_supplier = 0;
    private int allCounts_report_supplier=0;


    private IAccountService iAccountService= (IAccountService) ObjectFactory.getObject("IAccountService");
    private ISupplierService iSupplierService= (ISupplierService) ObjectFactory.getObject("ISupplierService");
    private IUserService iUserService= (IUserService) ObjectFactory.getObject("IUserService");
    private IOrderByCommodityService iOrderByCommodityService= (IOrderByCommodityService) ObjectFactory.getObject("IOrderByCommodityService");
    private IOrderBySupplierService iOrderBySupplierService = (IOrderBySupplierService) ObjectFactory.getObject("IOrderBySupplierService");

    public void initPageParams_bill() throws SQLException {
        allCounts_bill = (int) iAccountService.getCountsOfId();
        allPages_bill=(allCounts_bill-1)/pageSize+1;
    }

    public void initPageParams_supplier() throws SQLException {
        allCounts_supplier = (int) iSupplierService.getCountsOfId();
        allPages_supplier=(allCounts_supplier-1)/pageSize+1;
    }

    public void initPageParams_user() throws SQLException {
        allCounts_user = (int) iUserService.getCountsOfId();
        allPages_user=(allCounts_user-1)/pageSize+1;
    }

    public void init_initPageParams_report_commodity() throws SQLException {
        allCounts_report_commodity = (int) iOrderByCommodityService.groupByCommodity_getCount();
        allPages_report_commodity=(allCounts_report_commodity-1)/pageSize+1;

    }

    public void init_initPageParams_report_supplier() throws SQLException {

        allCounts_report_supplier = (int) iOrderBySupplierService.OrderBySupplier_getCount();
        allPages_report_supplier=(allCounts_report_supplier-1)/pageSize+1;
    }

    public MenuJFrame() throws SQLException {
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


        //初始化菜单界面左区
        JPanel jPanel_Left=new JPanel();
        jPanel_Left.setBounds(0,102,195,470);
//        jPanel_Left.setBackground(Color.PINK);
        jPanel.add(jPanel_Left);
        jPanel_Left.setLayout(new GridLayout(8,1,0,5));


        //初始化菜单界面右区
        JPanel jPanel_Right=new JPanel();
        jPanel_Right.setBounds(205,142,795,430);
//        jPanel_Right.setBackground(Color.PINK);
        CardLayout cardLayout=new CardLayout();
        jPanel_Right.setLayout(cardLayout);
        jPanel.add(jPanel_Right);

        //判断登陆者身份是部门经理、普通员工
//        if(LoginToMenu.map.containsKey("部门经理")){
//            jPanel_Left.add(jButton_bill);
//            jPanel_Left.add(jButton_supplier);
//            jPanel_Left.add(jButton_user);
//            jPanel_Left.add(jButton_report);
//            jPanel_Left.add(jButton_exit);
//            userName= (String) LoginToMenu.map.get("部门经理");
//        }else{
//            jPanel_Left.add(jButton_bill);
//            jPanel_Left.add(jButton_exit);
//            userName= (String) LoginToMenu.map.get("普通员工");
//        }


        if(LoginToMenu.map.containsKey("普通员工")){
            jPanel_Left.add(jButton_bill);
            jPanel_Left.add(jButton_exit);
            userName= (String) LoginToMenu.map.get("普通员工");
        }else{
            jPanel_Left.add(jButton_bill);
            jPanel_Left.add(jButton_supplier);
            jPanel_Left.add(jButton_user);
            jPanel_Left.add(jButton_report);
            jPanel_Left.add(jButton_exit);
            userName= (String) LoginToMenu.map.get("部门经理");
//            LoginToMenu.map.clear();

        }






        //菜单界面北区显示欢迎、系统时间
        JPanel jPanel_North=new JPanel();
        jPanel_North.setOpaque(false);
        jPanel_North.setBounds(450,70,550,25);
        jPanel.add(jPanel_North);
        jPanel_North.add(new JLabel("欢迎您："+userName));
        jLabelTime=new JLabel();
        jPanel_North.add(jLabelTime);
        new Thread(this).start();




        //----------------------------------------------------------------------------------------
        //卡片布局第一页（默认页）
        JPanel jPanelWelcome = new JPanel();
        JLabel jLabelWelcome = new JLabel("                         欢迎使用超市管理系统！");
        jLabelWelcome.setFont(new Font("微软雅黑",Font.BOLD,30));
        jPanelWelcome.setLayout(new BorderLayout());
        jPanelWelcome.add(jLabelWelcome,BorderLayout.CENTER);
        jPanel_Right.add(jPanelWelcome, "欢迎界面");

        //-----------------------------------------------------------------------------------------
        //卡片布局（账单管理按钮显示界面）
        JPanel jPanel_Bill = new JPanel();
        jPanel_Bill.setLayout(null);
        jPanel_Right.add(jPanel_Bill,"订单管理");

        //查询按钮及输入框
        JPanel jPanel_Bill_North = new JPanel();
        jPanel_Bill_North.setBackground(Color.PINK);
        jPanel_Bill_North.setBounds(0,0,795,45);
        jPanel_Bill_North.setLayout(new FlowLayout(FlowLayout.LEFT,15,10));
        jPanel_Bill.add(jPanel_Bill_North);

        JLabel jLabel_NameOfCommodity = new JLabel("商品名称：");
        JTextField jTextField_Bill_North=new JTextField(15);
        JLabel jLabel_CheckOfPay = new JLabel("是否付款：");



        //是否付款下拉框
        Object[] datas = new Object[3];
        datas[0]="请选择";
        datas[1]="是";
        datas[2]="否";
        jComboBox_CheckOfPay= ComBoBoxOfPayCheck.getComBoxOfPayCheck();

        jPanel_Bill_North.add(jLabel_NameOfCommodity);
        jPanel_Bill_North.add(jTextField_Bill_North);
        jPanel_Bill_North.add(jLabel_CheckOfPay);
        jPanel_Bill_North.add(jComboBox_CheckOfPay);
        JButton button_bill_search = new JButton("查询");
        jPanel_Bill_North.add(button_bill_search);




        //账单管理>>
        JPanel jPanel_Bill_button = new JPanel();
        //jPanel_Bill_button.setOpaque(false);
        jPanel_Bill_button.setLayout(new FlowLayout(FlowLayout.LEFT,20,10));
        jPanel_Bill_button.setBounds(0,45,795,55);
        jPanel_Bill_button.setBackground(Color.CYAN);
        jPanel_Bill.add(jPanel_Bill_button);

        JLabel jLabel_Bill_title=new JLabel("账单管理 >>     ");
        jLabel_Bill_title.setFont(new Font("微软雅黑",Font.BOLD,25));
        jPanel_Bill_button.add(jLabel_Bill_title);

        //导出按钮
        /*
        mac os 默认的border是aqua border（look and feel设置），这个border是一个灰白色填充外加灰色边界的矩形bai，在初始化button的时候默认是显示border的.
         */
        JButton jButton_output=new JButton("导出");
        jButton_output.setBackground(Color.YELLOW);//Black By Default
        jButton_output.setOpaque(true);
        jButton_output.setBorderPainted(false);
//        jButton_output.setForeground(Color.YELLOW);//设置按钮中字体颜色
        jPanel_Bill_button.add(jButton_output);
        jButton_output.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                JFileChooser chooser = new JFileChooser(new File("/Users/air/Desktop"));
                JFileChooser chooser = new JFileChooser(new File("/Users/air/Desktop"));
                int result = chooser.showSaveDialog(MenuJFrame.this);
                //默认选择一个文件
                chooser.setMultiSelectionEnabled(false);
//                chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);//设置文件和目录都可选
                if(result==JFileChooser.APPROVE_OPTION) {
                    File f = chooser.getSelectedFile();
                    String pathName=f.getAbsolutePath();
//                    System.out.println(pathName);

                    if(f.isFile()){//判断获取到的是否是文件
                        if(!f.getAbsolutePath().endsWith(".txt")){//判断文件是否是文本文件
                            String fileParent=f.getParent();
                            File output = new File(fileParent+File.separator+"订单导出.txt");
                            FileActionUtil.fileOutput(output);
                        }else{
                            FileActionUtil.fileOutput(f);
                        }
                    }else if(f.isDirectory()){//Mac OS端不允许选择目录，虽然上面已经设置为文件和目录都可选
                        File output=new File(f.getAbsolutePath()+File.separator+"订单导出.txt");
                        FileActionUtil.fileOutput(output);
                    }else {
                        FileActionUtil.fileOutput(f);
                    }
                    JOptionPane.showMessageDialog(MenuJFrame.this,"您的文件已成功导出！");
                }
            }
        });

        //增删改按钮
        JButton jButton_bill_add=new JButton("添加数据");
        JButton jButton_bill_modify=new JButton("修改数据");
        JButton jButton_bill_delete=new JButton("删除数据");
        jPanel_Bill_button.add(jButton_bill_add);
        jPanel_Bill_button.add(jButton_bill_modify);
        jPanel_Bill_button.add(jButton_bill_delete);

        jButton_bill_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new AddAccountJFrame(jTable_bill);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });



        //table
        JPanel jPanel_Bill_table=new JPanel();
        jPanel_Bill_table.setLayout(new BorderLayout());
        jPanel_Bill_table.setBackground(Color.YELLOW);
        jPanel_Bill_table.setBounds(0,100,795,300);
        jPanel_Bill.add(jPanel_Bill_table);

        initPageParams_bill();
        jTable_bill=new JTable(new BillTableModel(currentPage_bill,pageSize));
        JScrollPane jScrollPane_bill=new JScrollPane(jTable_bill);
        jPanel_Bill_table.add(jScrollPane_bill,BorderLayout.CENTER);

        //页面切换按钮区
        JPanel jPanel_Bill_pageArea=new JPanel();
        jPanel_Bill_pageArea.setLayout(new FlowLayout(FlowLayout.CENTER));
        jPanel_Bill_pageArea.setBounds(0,400,795,30);
        jPanel_Bill_pageArea.setBackground(Color.PINK);
        jPanel_Bill.add(jPanel_Bill_pageArea);

        JButton jButton_Bill_headPage=new JButton("首页");
        JButton jButton_Bill_endPage=new JButton("尾页");
        JButton jButton_Bill_previousPage=new JButton("上一页");
        JButton jButton_Bill_next=new JButton("下一页");
        jLabelPageInfo_bill = new JLabel("一共有"+allPages_bill+"页数据,当前为"+currentPage_bill+"页");

        jButton_bill_modify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(jTable_bill.getSelectedRows().length>1){
                    JOptionPane.showMessageDialog(MenuJFrame.this,"只能修改单行数据");
                }else if(jTable_bill.getSelectedRow()==-1){
                    JOptionPane.showMessageDialog(MenuJFrame.this,"请选中要修改的内容");
                }else{
                    int account_id_modify= (int) jTable_bill.getValueAt(jTable_bill.getSelectedRow(),0);
                    try {
                        new ModifyAccountJFrame(jTable_bill,account_id_modify);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }
            }
        });

        jButton_bill_delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(jTable_bill.getSelectedRow()==-1){
                    JOptionPane.showMessageDialog(MenuJFrame.this,"请选中要修改的内容");
                }else{
                    int[] account_rows=jTable_bill.getSelectedRows();
                    Set accountSet=new HashSet();
                    for(Object obj:account_rows){
                        accountSet.add(jTable_bill.getValueAt((Integer) obj,0));
                    }
                    try {
                        iAccountService.deleteByID(accountSet);
                        jTable_bill.setModel(new BillTableModel());
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        });


        jTextField_Bill_North.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jComboBox_CheckOfPay.setSelectedIndex(0);
            }
        });

        jComboBox_CheckOfPay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTextField_Bill_North.setText("");
            }
        });

        button_bill_search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(new CheckOfNull().check(jTextField_Bill_North.getText()) && jComboBox_CheckOfPay.getSelectedIndex()!=0){
                    /**进行是否付款查询*/
                    try {
                        jTable_bill.setModel(new BillTableModel((String) jComboBox_CheckOfPay.getSelectedItem()));
                        jLabelPageInfo_bill.setText("");
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }else if(!new CheckOfNull().check(jTextField_Bill_North.getText()) && jComboBox_CheckOfPay.getSelectedIndex()==0){
                    /**进行商品名称查询*/
                    try {
                        jTable_bill.setModel(new BillTableModel(jTextField_Bill_North.getText(),jTextField_Bill_North.getText()));
                        jLabelPageInfo_bill.setText("");
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    jLabelPageInfo_bill.setText("");
                }
            }
        });


        /**首页*/
        jButton_Bill_headPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    initPageParams_bill();
                    currentPage_bill=1;
                    jTable_bill.setModel(new BillTableModel(currentPage_bill,pageSize));
                    jLabelPageInfo_bill.setText("一共有"+allPages_bill+"页数据,当前为"+currentPage_bill+"页");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        /**尾页*/
        jButton_Bill_endPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    initPageParams_bill();
                    currentPage_bill=allPages_bill;
                    jTable_bill.setModel(new BillTableModel(currentPage_bill,pageSize));
                    jLabelPageInfo_bill.setText("一共有"+allPages_bill+"页数据,当前为"+currentPage_bill+"页");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        });

        /**下一页*/
        jButton_Bill_next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    initPageParams_bill();
                    if(currentPage_bill==allPages_bill){
                        currentPage_bill=0;
                    }
                    currentPage_bill=currentPage_bill+1;
                    jTable_bill.setModel(new BillTableModel(currentPage_bill,pageSize));
                    jLabelPageInfo_bill.setText("一共有"+allPages_bill+"页数据,当前为"+currentPage_bill+"页");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });


        /**上一页*/
        jButton_Bill_previousPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    initPageParams_bill();
                    if(currentPage_bill==1){
                        currentPage_bill=allPages_bill+1;
                    }
                    currentPage_bill=currentPage_bill-1;
                    jTable_bill.setModel(new BillTableModel(currentPage_bill,pageSize));
                    jLabelPageInfo_bill.setText("一共有"+allPages_bill+"页数据,当前为"+currentPage_bill+"页");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        
        jPanel_Bill_pageArea.add(jButton_Bill_headPage);
        jPanel_Bill_pageArea.add(jButton_Bill_previousPage);
        jPanel_Bill_pageArea.add(jLabelPageInfo_bill);
        jPanel_Bill_pageArea.add(jButton_Bill_next);
        jPanel_Bill_pageArea.add(jButton_Bill_endPage);

        //------------------------------------------------------------------------------------------------------------
        /**
         * 卡片布局（供应商管理按钮显示信息）
         */
        JPanel jPanel_Supplier=new JPanel();
        jPanel_Supplier.setLayout(null);
        //jPanel_Supplier.setBackground(Color.PINK);
        jPanel_Right.add(jPanel_Supplier, "供应商管理");

        //供应商查询按钮及输入框
        JPanel jPanel_Supplier_North = new JPanel();
        jPanel_Supplier_North.setBackground(Color.PINK);
        jPanel_Supplier_North.setBounds(0,0,795,45);
        jPanel_Supplier_North.setLayout(new FlowLayout(FlowLayout.LEFT,30,10));
        jPanel_Supplier.add(jPanel_Supplier_North);

        JLabel jLabel_NameOfSupplier = new JLabel("供应商名称：");
        JTextField jTextField_Supplier_North=new JTextField(15);
        JButton jButton_Supplier_search=new JButton("查询");
        jButton_Supplier_search.setFont(new Font("宋体",Font.BOLD,15));

        jPanel_Supplier_North.add(jLabel_NameOfSupplier);
        jPanel_Supplier_North.add(jTextField_Supplier_North);
        jPanel_Supplier_North.add(jButton_Supplier_search);

        //供应商管理>>
        JPanel jPanel_Supplier_button = new JPanel();
        jPanel_Supplier_button.setLayout(new FlowLayout(FlowLayout.LEFT,20,10));
        jPanel_Supplier_button.setBounds(0,45,795,55);
        jPanel_Supplier_button.setBackground(Color.CYAN);
        jPanel_Supplier.add(jPanel_Supplier_button);

        JLabel jLabel_Supplier_title=new JLabel("供应商管理 >>     ");
        jLabel_Supplier_title.setFont(new Font("微软雅黑",Font.BOLD,25));
        jPanel_Supplier_button.add(jLabel_Supplier_title);


        /**
         * 导入按钮
         * mac os 默认的border是aqua border（look and feel设置），这个border是一个灰白色填充外加灰色边界的矩形bai，在初始化button的时候默认是显示border的.
         */
        JButton jButton_input=new JButton("导入");
        jButton_input.setBackground(Color.YELLOW);//Black By Default
        jButton_input.setOpaque(true);
        jButton_input.setBorderPainted(false);
//        jButton_output.setForeground(Color.YELLOW);//设置按钮中字体颜色
        jPanel_Supplier_button.add(jButton_input);


        jButton_input.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser(new File("/Users/air/Desktop"));
                int result = chooser.showOpenDialog(MenuJFrame.this);
                //默认选择一个文件
                chooser.setMultiSelectionEnabled(false);
                if(result==JFileChooser.APPROVE_OPTION) {

                    try {
                        int countOfSupplier01= (int) iSupplierService.getCountsOfId();
                        File f = chooser.getSelectedFile();
                        FileActionUtil.fileInput(f);
                        int countOfSupplier02= (int) iSupplierService.getCountsOfId();
                        if(countOfSupplier02==countOfSupplier01+1){
                            JOptionPane.showMessageDialog(MenuJFrame.this,"您的文件已成功导入！");
                        }else{
                            JOptionPane.showMessageDialog(MenuJFrame.this,"导入失败！");
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }
            }
        });



        //增删改按钮
        JButton jButton_supplier_add=new JButton("添加数据");
        JButton jButton_supplier_modify=new JButton("修改数据");
        JButton jButton_supplier_delete=new JButton("删除数据");
        jPanel_Supplier_button.add(jButton_supplier_add);
        jPanel_Supplier_button.add(jButton_supplier_modify);
        jPanel_Supplier_button.add(jButton_supplier_delete);

        jButton_supplier_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new AddSupplierJFrame(jTable_supplier);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        //table
        JPanel jPanel_Supplier_table=new JPanel();
        jPanel_Supplier_table.setLayout(new BorderLayout());
        jPanel_Supplier_table.setBackground(Color.YELLOW);
        jPanel_Supplier_table.setBounds(0,100,795,300);
        jPanel_Supplier.add(jPanel_Supplier_table);

        initPageParams_supplier();
        jTable_supplier=new JTable(new SupplierTableModel(currentPage_supplier,pageSize));
        JScrollPane jScrollPane_supplier=new JScrollPane(jTable_supplier);
        jPanel_Supplier_table.add(jScrollPane_supplier,BorderLayout.CENTER);

        //页面切换按钮区
        JPanel jPanel_supplier_pageArea=new JPanel();
        jPanel_supplier_pageArea.setLayout(new FlowLayout(FlowLayout.CENTER));
        jPanel_supplier_pageArea.setBounds(0,400,795,30);
        jPanel_supplier_pageArea.setBackground(Color.PINK);
        jPanel_Supplier.add(jPanel_supplier_pageArea);

        JButton jButton_Supplier_headPage=new JButton("首页");
        JButton jButton_Supplier_endPage=new JButton("尾页");
        JButton jButton_Supplier_previousPage=new JButton("上一页");
        JButton jButton_Supplier_next=new JButton("下一页");
        jLabelPageInfo_supplier = new JLabel("一共有"+allPages_supplier+"页数据,当前为"+currentPage_supplier+"页");

        jButton_supplier_modify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /**测试预留*/

            }
        });

        /**首页*/
        jButton_Supplier_headPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    initPageParams_supplier();
                    currentPage_supplier=1;
                    jTable_supplier.setModel(new SupplierTableModel(currentPage_supplier,pageSize));
                    jLabelPageInfo_supplier.setText("一共有"+allPages_supplier+"页数据,当前为"+currentPage_supplier+"页");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        /**尾页*/
        jButton_Supplier_endPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    initPageParams_supplier();
                    currentPage_supplier=allPages_supplier;
                    jTable_supplier.setModel(new SupplierTableModel(currentPage_supplier,pageSize));
                    jLabelPageInfo_supplier.setText("一共有"+allPages_supplier+"页数据,当前为"+currentPage_supplier+"页");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        });

        /**下一页*/
        jButton_Supplier_next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    initPageParams_supplier();
                    if(currentPage_supplier==allPages_supplier){
                        currentPage_supplier=0;
                    }
                    currentPage_supplier=currentPage_supplier+1;
                    jTable_supplier.setModel(new SupplierTableModel(currentPage_supplier,pageSize));
                    jLabelPageInfo_supplier.setText("一共有"+allPages_supplier+"页数据,当前为"+currentPage_supplier+"页");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });


        /**上一页*/
        jButton_Supplier_previousPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    initPageParams_supplier();
                    if(currentPage_supplier==1){
                        currentPage_supplier=allPages_supplier+1;
                    }
                    currentPage_supplier=currentPage_supplier-1;
                    jTable_supplier.setModel(new SupplierTableModel(currentPage_supplier,pageSize));
                    jLabelPageInfo_supplier.setText("一共有"+allPages_supplier+"页数据,当前为"+currentPage_supplier+"页");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        jButton_Supplier_search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(!CheckOfNull.check(jTextField_Supplier_North.getText()))
                    jTable_supplier.setModel(new SupplierTableModel(jTextField_Supplier_North.getText()));
                    jLabelPageInfo_supplier.setText("");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                jLabelPageInfo_supplier.setText("");
            }
        });

        
        
        jPanel_supplier_pageArea.add(jButton_Supplier_headPage);
        jPanel_supplier_pageArea.add(jButton_Supplier_previousPage);
        jPanel_supplier_pageArea.add(jLabelPageInfo_supplier);
        jPanel_supplier_pageArea.add(jButton_Supplier_next);
        jPanel_supplier_pageArea.add(jButton_Supplier_endPage);


        /**
         * 卡片布局（用户管理按钮显示界面）------------------------------------------------------------------
         */
        JPanel jPanel_user = new JPanel();
        jPanel_user.setLayout(null);
        jPanel_Right.add(jPanel_user,"用户管理");

        /**查询按钮及输入*/
        JPanel jPanel_user_North = new JPanel();
        jPanel_user_North.setBackground(Color.PINK);
        jPanel_user_North.setBounds(0,0,795,45);
        jPanel_user_North.setLayout(new FlowLayout(FlowLayout.LEFT,15,10));
        jPanel_user.add(jPanel_user_North);

        JLabel jLabel_nameOfUser=new JLabel("用户名称");
        JTextField jTextField_user_north=new JTextField(15);
        JButton jButton_user_search=new JButton("查询");
        jButton_user_search.setFont(new Font("宋体",Font.BOLD,15));

        jPanel_user_North.add(jLabel_nameOfUser);
        jPanel_user_North.add(jTextField_user_north);
        jPanel_user_North.add(jButton_user_search);

        /**用户管理>>*/
        JPanel jPanel_user_button = new JPanel();
        jPanel_user_button.setLayout(new FlowLayout(FlowLayout.LEFT,20,10));
        jPanel_user_button.setBounds(0,45,795,55);
        jPanel_user_button.setBackground(Color.CYAN);
        jPanel_user.add(jPanel_user_button);

        JLabel jLabel_user_title=new JLabel("用户管理 >>     ");
        jLabel_user_title.setFont(new Font("微软雅黑",Font.BOLD,25));
        jPanel_user_button.add(jLabel_user_title);

        JButton jButton_user_add=new JButton("添加数据");
        JButton jButton_user_modify=new JButton("修改数据");
        JButton jButton_user_delete=new JButton("删除数据");
        jPanel_user_button.add(jButton_user_add);
        jPanel_user_button.add(jButton_user_modify);
        jPanel_user_button.add(jButton_user_delete);

        jButton_user_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new AddUserJFrame(jTable_user);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        /**table*/
        JPanel jPanel_user_table=new JPanel();
        jPanel_user_table.setLayout(new BorderLayout());
        jPanel_user_table.setBackground(Color.YELLOW);
        jPanel_user_table.setBounds(0,100,795,300);
        jPanel_user.add(jPanel_user_table);

        initPageParams_user();
        jTable_user = new JTable(new UserTableModel(currentPage_user, pageSize));
        JScrollPane jScrollPane_user = new JScrollPane(jTable_user);
        jPanel_user_table.add(jScrollPane_user, BorderLayout.CENTER);

        /**页面切换按钮区*/
        JPanel jPanel_user_pageArea=new JPanel();
        jPanel_user_pageArea.setLayout(new FlowLayout(FlowLayout.CENTER));
        jPanel_user_pageArea.setBounds(0,400,795,30);
        jPanel_user_pageArea.setBackground(Color.PINK);
        jPanel_user.add(jPanel_user_pageArea);

        JButton jButton_user_headPage=new JButton("首页");
        JButton jButton_user_endPage=new JButton("尾页");
        JButton jButton_user_previousPage=new JButton("上一页");
        JButton jButton_user_next=new JButton("下一页");
        jLabelPageInfo_user = new JLabel("一共有"+allPages_user+"页数据,当前为"+currentPage_user+"页");


        /**首页*/
        jButton_user_headPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    initPageParams_user();
                    currentPage_user=1;
                    jTable_user.setModel(new UserTableModel(currentPage_user,pageSize));
                    jLabelPageInfo_user.setText("一共有"+allPages_user+"页数据,当前为"+currentPage_user+"页");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        /**尾页*/
        jButton_user_endPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    initPageParams_user();
                    currentPage_user=allPages_user;
                    jTable_user.setModel(new UserTableModel(currentPage_user,pageSize));
                    jLabelPageInfo_user.setText("一共有"+allPages_user+"页数据,当前为"+currentPage_user+"页");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        });

        /**下一页*/
        jButton_user_next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    initPageParams_user();
                    if(currentPage_user==allPages_user){
                        currentPage_user=0;
                    }
                    currentPage_user=currentPage_user+1;
                    jTable_user.setModel(new UserTableModel(currentPage_user,pageSize));
                    jLabelPageInfo_user.setText("一共有"+allPages_user+"页数据,当前为"+currentPage_user+"页");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });


        /**上一页*/
        jButton_user_previousPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    initPageParams_user();
                    if(currentPage_user==1){
                        currentPage_user=allPages_user+1;
                    }
                    currentPage_user=currentPage_user-1;
                    jTable_user.setModel(new UserTableModel(currentPage_user,pageSize));
                    jLabelPageInfo_user.setText("一共有"+allPages_user+"页数据,当前为"+currentPage_user+"页");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        jButton_user_search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(!CheckOfNull.check(jTextField_user_north.getText())) {
                        jTable_user.setModel(new UserTableModel(jTextField_user_north.getText()));
                        jLabelPageInfo_user.setText("");
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        });

        jPanel_user_pageArea.add(jButton_user_headPage);
        jPanel_user_pageArea.add(jButton_user_previousPage);
        jPanel_user_pageArea.add(jLabelPageInfo_user);
        jPanel_user_pageArea.add(jButton_user_next);
        jPanel_user_pageArea.add(jButton_user_endPage);


        /**
         * 卡片布局（报表管理显示界面）
         */
        JPanel jPanel_report=new JPanel();
        jPanel_report.setLayout(new BorderLayout());
        jPanel_Right.add(jPanel_report,"报表管理");

        JPanel jPanel_report_north=new JPanel();
        jPanel_report_north.setLayout(new GridLayout(1,2));
        jPanel_report_north.setBackground(Color.CYAN);
        jPanel_report.add(jPanel_report_north, BorderLayout.NORTH);

        JPanel jPanel_groupByCommodity = new JPanel();
        JPanel jPanel_groupBySupplier = new JPanel();
        jPanel_groupByCommodity.setLayout(new FlowLayout(FlowLayout.CENTER));
        jPanel_groupBySupplier.setLayout(new FlowLayout(FlowLayout.CENTER));
        jPanel_report_north.add(jPanel_groupByCommodity);
        jPanel_report_north.add(jPanel_groupBySupplier);

        JRadioButton jRadioButton_groupByCommodity=new JRadioButton("商品分组查询",true);
        JRadioButton jRadioButton_groupBySupplier=new JRadioButton("供应商分组查询",false);
        jPanel_groupByCommodity.add(jRadioButton_groupByCommodity);
        jPanel_groupBySupplier.add(jRadioButton_groupBySupplier);

        /**将两个单选按钮加入到一个按钮组*/
        ButtonGroup buttonGroup=new ButtonGroup();
        buttonGroup.add(jRadioButton_groupByCommodity);
        buttonGroup.add(jRadioButton_groupBySupplier);

        JPanel jPanel_report_center=new JPanel();
        CardLayout cardLayout_report=new CardLayout();
        jPanel_report_center.setLayout(cardLayout_report);
        jPanel_report_center.setBackground(Color.yellow);
        jPanel_report.add(jPanel_report_center,BorderLayout.CENTER);

        JPanel jPanel_report_center_commodity=new JPanel(new BorderLayout());
        JPanel jPanel_report_center_supplier=new JPanel(new BorderLayout());
        jPanel_report_center_commodity.setBackground(Color.CYAN);
        jPanel_report_center_supplier.setBackground(Color.green);
        jPanel_report_center.add(jPanel_report_center_commodity,"商品分组查询");
        jPanel_report_center.add(jPanel_report_center_supplier, "供应商分组查询");

        init_initPageParams_report_commodity();
        jTable_report_commodity = new JTable(new GroupByCommodityTableModel(currentPage_report_commodity, pageSize));
        JScrollPane jScrollPane_report_commodity = new JScrollPane(jTable_report_commodity);
        jPanel_report_center_commodity.add(jScrollPane_report_commodity, BorderLayout.CENTER);
        
        init_initPageParams_report_supplier();
        jTable_report_supplier=new JTable(new GroupBySupplierTableModel(currentPage_report_supplier, pageSize));
        JScrollPane jScrollPane_report_supplier=new JScrollPane(jTable_report_supplier);
        jPanel_report_center_supplier.add(jScrollPane_report_supplier, BorderLayout.CENTER);

        /**页面切换按钮区*/
        JPanel jPanel_report_pageArea=new JPanel();
        CardLayout cardLayout_report_pageArea=new CardLayout();
        jPanel_report_pageArea.setLayout(cardLayout_report_pageArea);
        jPanel_report.add(jPanel_report_pageArea, BorderLayout.SOUTH);

        JPanel jPanel_report_pageArea_commodity=new JPanel(new FlowLayout(FlowLayout.CENTER));
        jPanel_report_pageArea_commodity.setBackground(Color.PINK);
        jPanel_report_pageArea.add(jPanel_report_pageArea_commodity,"商品分组查询");

        JPanel jPanel_report_pageArea_supplier=new JPanel(new FlowLayout(FlowLayout.CENTER));
        jPanel_report_pageArea_supplier.setBackground(Color.CYAN);
        jPanel_report_pageArea.add(jPanel_report_pageArea_supplier,"供应商分组查询");

        JButton jButton_report_pageArea_commodity_headPage=new JButton("首页");
        JButton jButton_report_pageArea_commodity_endPage=new JButton("尾页");
        JButton jButton_report_pageArea_commodity_previousPage=new JButton("上一页");
        JButton jButton_report_pageArea_commodity_next=new JButton("下一页");
        jLabelPageInfo_report_commodity = new JLabel("一共有"+allPages_report_commodity+"页数据,当前为"+currentPage_report_commodity+"页");

        /**首页*/
        jButton_report_pageArea_commodity_headPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    init_initPageParams_report_commodity();
                    currentPage_report_commodity=1;
                    jTable_report_commodity.setModel(new GroupByCommodityTableModel(currentPage_report_commodity,pageSize));
                    jLabelPageInfo_report_commodity.setText("一共有"+allPages_report_commodity+"页数据,当前为"+currentPage_report_commodity+"页");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        /**尾页*/
        jButton_report_pageArea_commodity_endPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    init_initPageParams_report_commodity();
                    currentPage_report_commodity=allPages_report_commodity;
                    jTable_report_commodity.setModel(new GroupByCommodityTableModel(currentPage_report_commodity,pageSize));
                    jLabelPageInfo_report_commodity.setText("一共有"+allPages_report_commodity+"页数据,当前为"+currentPage_report_commodity+"页");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        });

        /**下一页*/
        jButton_report_pageArea_commodity_next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    init_initPageParams_report_commodity();
                    if(currentPage_report_commodity==allPages_report_commodity){
                        currentPage_report_commodity=0;
                    }
                    currentPage_report_commodity=currentPage_report_commodity+1;
                    jTable_report_commodity.setModel(new GroupByCommodityTableModel(currentPage_report_commodity,pageSize));
                    jLabelPageInfo_report_commodity.setText("一共有"+allPages_report_commodity+"页数据,当前为"+currentPage_report_commodity+"页");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });


        /**上一页*/
        jButton_report_pageArea_commodity_previousPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    init_initPageParams_report_commodity();
                    if(currentPage_report_commodity==1){
                        currentPage_report_commodity=allPages_report_commodity+1;
                    }
                    currentPage_report_commodity=currentPage_report_commodity-1;
                    jTable_report_commodity.setModel(new GroupByCommodityTableModel(currentPage_report_commodity,pageSize));
                    jLabelPageInfo_report_commodity.setText("一共有"+allPages_report_commodity+"页数据,当前为"+currentPage_report_commodity+"页");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        
        

        jPanel_report_pageArea_commodity.add(jButton_report_pageArea_commodity_headPage);
        jPanel_report_pageArea_commodity.add(jButton_report_pageArea_commodity_previousPage);
        jPanel_report_pageArea_commodity.add(jLabelPageInfo_report_commodity);
        jPanel_report_pageArea_commodity.add(jButton_report_pageArea_commodity_next);
        jPanel_report_pageArea_commodity.add(jButton_report_pageArea_commodity_endPage);

        JButton jButton_report_pageArea_supplier_headPage=new JButton("首页");
        JButton jButton_report_pageArea_supplier_endPage=new JButton("尾页");
        JButton jButton_report_pageArea_supplier_previousPage=new JButton("上一页");
        JButton jButton_report_pageArea_supplier_next=new JButton("下一页");
        jLabelPageInfo_report_supplier = new JLabel("一共有"+allPages_report_supplier+"页数据,当前为"+currentPage_report_supplier+"页");

        /**首页*/
        jButton_report_pageArea_supplier_headPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    init_initPageParams_report_supplier();
                    currentPage_report_supplier=1;
                    jTable_report_supplier.setModel(new GroupBySupplierTableModel(currentPage_report_supplier,pageSize));
                    jLabelPageInfo_report_supplier.setText("一共有"+allPages_report_supplier+"页数据,当前为"+currentPage_report_supplier+"页");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        /**尾页*/
        jButton_report_pageArea_supplier_endPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    init_initPageParams_report_supplier();
                    currentPage_report_supplier=allPages_report_supplier;
                    jTable_report_supplier.setModel(new GroupBySupplierTableModel(currentPage_report_supplier,pageSize));
                    jLabelPageInfo_report_supplier.setText("一共有"+allPages_report_supplier+"页数据,当前为"+currentPage_report_supplier+"页");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        });

        /**下一页*/
        jButton_report_pageArea_supplier_next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    init_initPageParams_report_supplier();
                    if(currentPage_report_supplier==allPages_report_supplier){
                        currentPage_report_supplier=0;
                    }
                    currentPage_report_supplier=currentPage_report_supplier+1;
                    jTable_report_supplier.setModel(new GroupBySupplierTableModel(currentPage_report_supplier,pageSize));
                    jLabelPageInfo_report_supplier.setText("一共有"+allPages_report_supplier+"页数据,当前为"+currentPage_report_supplier+"页");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });


        /**上一页*/
        jButton_report_pageArea_supplier_previousPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    init_initPageParams_report_supplier();
                    if(currentPage_report_supplier==1){
                        currentPage_report_supplier=allPages_report_supplier+1;
                    }
                    currentPage_report_supplier=currentPage_report_supplier-1;
                    jTable_report_supplier.setModel(new GroupBySupplierTableModel(currentPage_report_supplier,pageSize));
                    jLabelPageInfo_report_supplier.setText("一共有"+allPages_report_supplier+"页数据,当前为"+currentPage_report_supplier+"页");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        jPanel_report_pageArea_supplier.add(jButton_report_pageArea_supplier_headPage);
        jPanel_report_pageArea_supplier.add(jButton_report_pageArea_supplier_previousPage);
        jPanel_report_pageArea_supplier.add(jLabelPageInfo_report_supplier);
        jPanel_report_pageArea_supplier.add(jButton_report_pageArea_supplier_next);
        jPanel_report_pageArea_supplier.add(jButton_report_pageArea_supplier_endPage);

        jRadioButton_groupByCommodity.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange()==ItemEvent.SELECTED){
                    cardLayout_report.show(jPanel_report_center,"商品分组查询");
                    cardLayout_report_pageArea.show(jPanel_report_pageArea, "商品分组查询");
                    System.out.println("1");
                }
            }
        });

        jRadioButton_groupBySupplier.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange()==ItemEvent.SELECTED){
                    cardLayout_report.show(jPanel_report_center,"供应商分组查询");
                    cardLayout_report_pageArea.show(jPanel_report_pageArea, "供应商分组查询");
                    System.out.println("2");
                }
            }
        });


        /**
         * 菜单界面左侧五个按钮绑定监听器
         */

        /**绑定订单管理按钮监听器*/
        jButton_bill.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(jPanel_Right,"订单管理");
            }
        });
        /**绑定供应商管理按钮监听器*/
        jButton_supplier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(jPanel_Right,"供应商管理");
            }
        });

        /**绑定用户管理按钮监听器*/
        jButton_user.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(jPanel_Right, "用户管理");
            }
        });
        /**绑定报表管理按钮监听*/
        jButton_report.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(jPanel_Right,"报表管理");
            }
        });



        /**绑定退出系统按钮监听器*/
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


        /**获取jpanel中鼠标点击点的坐标*/
        jPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("x--->"+(e.getX()-205)+"\ty--->"+(e.getY()-142));
            }
        });

        int x1=(int)(this.getToolkit().getScreenSize().getWidth()-x)/2;
        int y1 = (int) (this.getToolkit().getScreenSize().getHeight()-y)/2;
        this.setLocation(x1,y1);
        this.setSize(x,y);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        this.addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent e) {
//                LoginToMenu.map.clear();
//                try {
//                    new LoginJFrame();
//                } catch (SQLException throwables) {
//                    throwables.printStackTrace();
//                }
//                dispose();
//            }
//        });
        this.setResizable(false);
        this.setVisible(true);
    }

    public static void main(String[] args) throws SQLException {
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
