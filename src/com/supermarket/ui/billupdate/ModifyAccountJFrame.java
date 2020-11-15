package com.supermarket.ui.billupdate;

import com.eltima.components.ui.DatePicker;
import com.supermarket.entity.Account;
import com.supermarket.factory.ObjectFactory;
import com.supermarket.service.IAccountService;
import com.supermarket.tmodel.BillTableModel;
import com.supermarket.ui.combbox.ComBoBoxOfPayCheck;
import com.supermarket.ui.combbox.ComBoBoxOfSupplier;
import com.supermarket.ui.date.DateStringCheck;
import com.supermarket.ui.date.GetDate;
import com.supermarket.ui.jframeutil.CheckOfNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@SuppressWarnings("all")
public class ModifyAccountJFrame extends JFrame implements Runnable{
    IAccountService iAccountService = (IAccountService) ObjectFactory.getObject("IAccountService");

    JLabel jLabel_star_name=null;
    JTextField jTextField_nameinput=null;

    JLabel jLabel_star_num=null;
    JTextField jTextField_numInput=null;

    JLabel jLabel_star_price=null;
    JTextField jTextField_priceInput=null;

    JLabel jLabel_star_payChoice=null;
    JComboBox jComboBox_payChoice=null;

    JLabel jLabel_star_supplierhoice=null;
    JComboBox jComboBox_supplierChoice=null;

    JLabel jLabel_star_timeInput=null;
    JTextField jTextField_timeInput=null;

    JTextArea jTextArea=null;

    public ModifyAccountJFrame(JTable jTable,int id_modify) throws SQLException {
        this.setTitle("修改商品");
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

        JLabel jLabel_nameInput = new JLabel("商品名称：");
        jLabel_nameInput.setFont(new Font("微软雅黑",Font.BOLD,15));
        jLabel_nameInput.setBounds(60,40,80,20);
        jPanel.add(jLabel_nameInput);

        jTextField_nameinput = new JTextField(15);
        jTextField_nameinput.setBounds(140,36,250,30);
        jPanel.add(jTextField_nameinput);

        //----------------------------------------------------------

        jLabel_star_num=new JLabel("*");
        jLabel_star_num.setBounds(40,85,20,20);
        jLabel_star_num.setForeground(Color.RED);
        jPanel.add(jLabel_star_num);

        JLabel jLabel_numInput = new JLabel("交易数量：");
        jLabel_numInput.setFont(new Font("微软雅黑",Font.BOLD,15));
        jLabel_numInput.setBounds(60,85,80,20);
        jPanel.add(jLabel_numInput);

        jTextField_numInput=new JTextField();
        jTextField_numInput.setBounds(140,81,250,30);
        jPanel.add(jTextField_numInput);

        //----------------------------------------------------------
        jLabel_star_price=new JLabel("*");
        jLabel_star_price.setBounds(40,135,20,20);
        jLabel_star_price.setForeground(Color.RED);
//        jLabel_star_name.setVisible(true);
        jPanel.add(jLabel_star_price);

        JLabel jLabel_priceInput = new JLabel("交易金额：");
        jLabel_priceInput.setFont(new Font("微软雅黑",Font.BOLD,15));
        jLabel_priceInput.setBounds(60,135,80,20);
        jPanel.add(jLabel_priceInput);

        jTextField_priceInput=new JTextField();
        jTextField_priceInput.setBounds(140,131,250,30);
        jPanel.add(jTextField_priceInput);

        //----------------------------------------------------------
        jLabel_star_payChoice=new JLabel("*");
        jLabel_star_payChoice.setBounds(40,185,20,20);
        jLabel_star_payChoice.setForeground(Color.RED);
        jPanel.add(jLabel_star_payChoice);

        JLabel jLabel_payChoice = new JLabel("是否付款：");
        jLabel_payChoice.setFont(new Font("微软雅黑",Font.BOLD,15));
        jLabel_payChoice.setBounds(60,185,80,20);
        jPanel.add(jLabel_payChoice);

        jComboBox_payChoice= ComBoBoxOfPayCheck.getComBoxOfPayCheck();
        jComboBox_payChoice.setBounds(140,181,252,30);
        jPanel.add(jComboBox_payChoice);

        //----------------------------------------------------------
        jLabel_star_supplierhoice=new JLabel("*");
        jLabel_star_supplierhoice.setBounds(40,235,20,20);
        jLabel_star_supplierhoice.setForeground(Color.RED);
        jPanel.add(jLabel_star_supplierhoice);

        JLabel jLabel_supplierChoice = new JLabel("供应商：");
        jLabel_supplierChoice.setFont(new Font("微软雅黑",Font.BOLD,15));
        jLabel_supplierChoice.setBounds(60,235,80,20);
        jPanel.add(jLabel_supplierChoice);

        jComboBox_supplierChoice= ComBoBoxOfSupplier.getComBoBoxOfSupplier();
        jComboBox_supplierChoice.setBounds(140,231,252,30);
        jPanel.add(jComboBox_supplierChoice);


        //----------------------------------------------------------
        JLabel jLabel_supplierDescription = new JLabel("商品描述：");
        jLabel_supplierDescription.setFont(new Font("微软雅黑",Font.BOLD,15));
        jLabel_supplierDescription.setBounds(60,285,80,20);
        jPanel.add(jLabel_supplierDescription);

        jTextArea=new JTextArea();
        jTextArea.setBounds(140,280,247,152);
        jPanel.add(jTextArea);

        //----------------------------------------------------------
        jLabel_star_timeInput=new JLabel("*");
        jLabel_star_timeInput.setBounds(40,455,20,20);
        jLabel_star_timeInput.setForeground(Color.RED);
        jPanel.add(jLabel_star_timeInput);

        JLabel jLabel_timeInput = new JLabel("交易时间：");
        jLabel_timeInput.setFont(new Font("微软雅黑",Font.BOLD,15));
        jLabel_timeInput.setBounds(60,455,80,20);
        jPanel.add(jLabel_timeInput);

        jTextField_timeInput=new JTextField();
        jTextField_timeInput.setBounds(140,451,250,30);
        jPanel.add(jTextField_timeInput);

        JLabel jLabel_worning=new JLabel("(日期格式为：yyyy-MM-dd)");
        jLabel_worning.setBounds(140,485,250,30);
        jLabel_worning.setForeground(Color.PINK);
        jPanel.add(jLabel_worning);

        //----------------------------------------------------------
        JButton jButton_modify=new JButton("修改");
        jButton_modify.setFont(new Font("隶书",Font.BOLD,15));
        jButton_modify.setBounds(60,530,100,40);
        jPanel.add(jButton_modify);

        JButton jButton_close=new JButton("关闭");
        jButton_close.setFont(new Font("隶书",Font.BOLD,15));
        jButton_close.setBounds(250,530,100,40);
        jPanel.add(jButton_close);

        /**
         * 按钮绑定
         *
         *  */

        /**双击"交易时间弹出图形化获取日期界面"*/
        jLabel_timeInput.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    new TimeSelectGUI().TimeSelectJFrame();
                }
            }
        });


        /**双击时间输入框自动填充当前日期*/
        jTextField_timeInput.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2){
                    jTextField_timeInput.setText("");
                    jTextField_timeInput.setText(GetDate.get());
                }
            }
        });

        /**绑定"关闭"按钮操作*/
        jButton_close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        jButton_modify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(new CheckOfNull().check(jTextField_nameinput.getText())){
                    JOptionPane.showMessageDialog(ModifyAccountJFrame.this,"商品名称不可为空！");
                }else if(CheckOfNull.check(jTextField_numInput.getText())){
                    JOptionPane.showMessageDialog(ModifyAccountJFrame.this,"交易数量不可为空！");
                }else if(CheckOfNull.check(jTextField_priceInput.getText())){
                    JOptionPane.showMessageDialog(ModifyAccountJFrame.this,"交易金额不可为空！");
                }else if(jComboBox_payChoice.getSelectedItem().equals("请选择")){
                    JOptionPane.showMessageDialog(ModifyAccountJFrame.this,"请选择是否付款！");
                }else if(jComboBox_supplierChoice.getSelectedItem().equals("请选择供应商")){
                    JOptionPane.showMessageDialog(ModifyAccountJFrame.this,"请选择供应商！");
                }else if(CheckOfNull.check(jTextField_timeInput.getText())){
                    JOptionPane.showMessageDialog(ModifyAccountJFrame.this,"交易时间不可为空！");
                }else if(!DateStringCheck.isDateString(jTextField_timeInput.getText().trim())){
                    JOptionPane.showMessageDialog(ModifyAccountJFrame.this,"日期格式不正确！");
                }else{
                    /**预留*/
                    try {
                        boolean checkOfSuccess=modifyAccount(id_modify);
                        if(checkOfSuccess){
                            JOptionPane.showMessageDialog(ModifyAccountJFrame.this,"修改成功！");
//                            jTable.setModel(new BillTableModel());
                            dispose();
                        }else{
                            JOptionPane.showMessageDialog(ModifyAccountJFrame.this,"修改失败！");
                        }
                    } catch (SQLException throwables) {
                        JOptionPane.showMessageDialog(ModifyAccountJFrame.this,"修改失败！");
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


        /**创建线程，启动线程*/
        init(id_modify);
        Thread threadCheck_account_modify =new Thread(this);
        threadCheck_account_modify.start();

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
        this.setVisible(true);
    }

    private void init(int id) {
        List<Account> list = (List<Account>) iAccountService.listById(id);
        for (Account account : list) {
            jTextField_nameinput.setText(account.getCommodity_name());
            jTextField_numInput.setText(String.valueOf(account.getNum()));
            jTextField_priceInput.setText(String.valueOf(account.getPrice()));
            if (account.getPay_check().equals("是")) {
                jComboBox_payChoice.setSelectedIndex(1);
            } else if (account.getPay_check().equals("否")) {
                jComboBox_payChoice.setSelectedIndex(2);
            } else {
                jComboBox_payChoice.setSelectedIndex(0);
            }
            for (int i = 0; i < jComboBox_supplierChoice.getItemCount(); i++) {
                if (jComboBox_supplierChoice.getItemAt(i).equals(account.getSupplier_name())) {
                    jComboBox_supplierChoice.setSelectedIndex(i);
                }
            }
            jTextArea.setText(account.getAccount_description());
            jTextField_timeInput.setText(account.getTime());
        }

    }



    private boolean modifyAccount(int id) throws SQLException {
        Account account=new Account();
        account.setAccount_id(id);
        account.setCommodity_name(jTextField_nameinput.getText());
        account.setNum(Integer.parseInt(jTextField_numInput.getText()));
        account.setPrice(Double.parseDouble(jTextField_priceInput.getText()));
        account.setPay_check((String) jComboBox_payChoice.getSelectedItem());
        account.setSupplier_name((String) jComboBox_supplierChoice.getSelectedItem());
        account.setAccount_description(jTextArea.getText());
        account.setTime(jTextField_timeInput.getText().trim());
        int result = (int) iAccountService.modifyAccount(account);
        if(result>0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 获取时间的图形化界面（内部类）
     * */
    /**--------------------------------------------------------------------------------*/
    public class TimeSelectGUI {
        public void TimeSelectJFrame() {
            JFrame f = new JFrame("时间选择");
            f.setSize(400, 300);
            f.setLocation(200, 200);
            f.setLayout(null);

            final DatePicker datepick;
            datepick = getDatePicker();

            f.add(datepick);

            JButton b = new JButton("获取时间");
            b.setBounds(137, 183, 100, 30);
            f.add(b);
            String getTime = null;

            Thread thread=new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {

                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        //将date类型转换成字符串
                        String str = sdf.format(datepick.getValue());

                        if(!jTextField_timeInput.getText().equals(str) || (jTextField_timeInput.getText()==null || jTextField_timeInput.getText().trim().isEmpty()) ){
                            jTextField_timeInput.setText("");
                            jTextField_timeInput.setText(str);
                        }

//                    jLabel_star_timeInput.setVisible(true);
                    }
                }
            });
            thread.start();

            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(f, "获取控件中的日期：" + datepick.getValue());
                    //创建SimpleDateFormat对象
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    //将date类型转换成字符串
                    String str = sdf.format(datepick.getValue());
                    /**预留*/
//                jTextField_timeInput.setText("");
//                jTextField_timeInput.setText(str);
                    thread.interrupt();
//                thread.stop();//不安全，已废弃
                    f.dispose();
                }
            });
//        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

//        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    f.dispose();
                }
            });

            f.setVisible(true);
        }

        private  DatePicker getDatePicker() {//
            final DatePicker datepick;
            // 格式
            String DefaultFormat = "yyyy-MM-dd";
            // 当前时间
            Date date = new Date();
            // 字体
            Font font = new Font("Times New Roman", Font.BOLD, 14);

            Dimension dimension = new Dimension(177, 24);//?

            int[] hilightDays = {1, 3, 5, 7};

            int[] disabledDays = {4, 6, 5, 9};

            datepick = new DatePicker(date, DefaultFormat, font, dimension);

            datepick.setLocation(137, 83);
            datepick.setBounds(137, 83, 177, 24);
            // 设置一个月份中需要高亮显示的日子
            datepick.setHightlightdays(hilightDays, Color.red);
            // 设置一个月份中不需要的日子，呈灰色显示
//        datepick.setDisableddays(disabledDays);
            // 设置国家
            datepick.setLocale(Locale.CHINA);
            // 设置时钟面板可见
            datepick.setTimePanleVisible(true);
            return datepick;
        }


    }

    /**--------------------------------------------------------------------------------*/

    @Override
    public void run() {
        while(true){
//            try {
//                Thread.sleep(0);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            if(new CheckOfNull().check(jTextField_nameinput.getText())){
                jLabel_star_name.setVisible(true);
            }else{
                jLabel_star_name.setVisible(false);
            }

            if(new CheckOfNull().check(jTextField_numInput.getText())){
                jLabel_star_num.setVisible(true);
            }else{
                jLabel_star_num.setVisible(false);
            }

            if(new CheckOfNull().check(jTextField_priceInput.getText())){
                jLabel_star_price.setVisible(true);
            }else{
                jLabel_star_price.setVisible(false);
            }

            if(jComboBox_payChoice.getSelectedItem().equals("请选择")){
                jLabel_star_payChoice.setVisible(true);
            }else{
                jLabel_star_payChoice.setVisible(false);
            }

            if (jComboBox_supplierChoice.getSelectedItem().equals("请选择供应商")) {
                jLabel_star_supplierhoice.setVisible(true);
            }else{
                jLabel_star_supplierhoice.setVisible(false);
            }

            if(new CheckOfNull().check(jTextField_timeInput.getText())){
                jLabel_star_timeInput.setVisible(true);
            }else{
                jLabel_star_timeInput.setVisible(false);
            }
        }

    }
}
