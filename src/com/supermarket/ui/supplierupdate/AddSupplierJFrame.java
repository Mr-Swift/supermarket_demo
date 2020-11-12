package com.supermarket.ui.supplierupdate;

import com.supermarket.entity.Supplier;
import com.supermarket.factory.ObjectFactory;
import com.supermarket.service.ISupplierService;
import com.supermarket.tmodel.SupplierTableModel;
import com.supermarket.ui.jframeutil.CheckOfNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

@SuppressWarnings("all")
public class AddSupplierJFrame extends JFrame implements Runnable{
    JLabel jLabel_star_name=null;
    JTextField jTextField_nameinput=null;

    JTextArea jTextArea=null;

    JLabel jLabel_star_linkman=null;
    JTextField jTextField_linkman=null;

    JLabel jLabel_star_telphone=null;
    JTextField jTextField_telphone=null;

    JLabel jLabel_star_address=null;
    JTextField jTextField_address=null;

    ISupplierService iSupplierService = (ISupplierService) ObjectFactory.getObject("ISupplierService");
    public AddSupplierJFrame(JTable jTable) throws SQLException {
        this.setTitle("添加供应商");
        this.setResizable(false);


        this.setSize(400, 600);
        int x1 = (int) (this.getToolkit().getScreenSize().getWidth() - this.getWidth()) / 2;
        int y1 = (int) (this.getToolkit().getScreenSize().getHeight() - this.getHeight()) / 2;
        this.setLocation(x1, y1);

        JPanel jPanel = new JPanel(null);
        this.add(jPanel);

        //----------------------------------------------------------

        jLabel_star_name = new JLabel("*");
        jLabel_star_name.setBounds(30, 40, 20, 20);
        jLabel_star_name.setForeground(Color.RED);
        jPanel.add(jLabel_star_name);

        JLabel jLabel_nameInput = new JLabel("供应商名称：");
        jLabel_nameInput.setFont(new Font("微软雅黑", Font.BOLD, 15));
        jLabel_nameInput.setBounds(50, 40, 90, 20);
        jPanel.add(jLabel_nameInput);

       jTextField_nameinput = new JTextField(15);
        jTextField_nameinput.setBounds(140, 36, 250, 30);
        jPanel.add(jTextField_nameinput);

        //----------------------------------------------------------
        JLabel jLabel_description = new JLabel("供应商描述：");
        jLabel_description.setFont(new Font("微软雅黑", Font.BOLD, 15));
        jLabel_description.setBounds(50, 90, 90, 20);
        jPanel.add(jLabel_description);

        jTextArea=new JTextArea();
        jTextArea.setBounds(143,86,242,152);
        jPanel.add(jTextArea);



        //----------------------------------------------------------

       jLabel_star_linkman = new JLabel("*");
        jLabel_star_linkman.setBounds(30, 260, 20, 20);
        jLabel_star_linkman.setForeground(Color.RED);
        jPanel.add(jLabel_star_linkman);

        JLabel jLabel_linkman = new JLabel("联系人：");
        jLabel_linkman.setFont(new Font("微软雅黑", Font.BOLD, 15));
        jLabel_linkman.setBounds(50, 260, 90, 20);
        jPanel.add(jLabel_linkman);

        jTextField_linkman = new JTextField(15);
        jTextField_linkman.setBounds(140, 256, 250, 30);
        jPanel.add(jTextField_linkman);

        //----------------------------------------------------------

        jLabel_star_telphone = new JLabel("*");
        jLabel_star_telphone.setBounds(30, 310, 20, 20);
        jLabel_star_telphone.setForeground(Color.RED);
        jPanel.add(jLabel_star_telphone);

        JLabel jLabel_telphone = new JLabel("供应商电话：");
        jLabel_telphone.setFont(new Font("微软雅黑", Font.BOLD, 15));
        jLabel_telphone.setBounds(50, 310, 90, 20);
        jPanel.add(jLabel_telphone);

       jTextField_telphone = new JTextField(15);
        jTextField_telphone.setBounds(140, 306, 250, 30);
        jPanel.add(jTextField_telphone);

        //----------------------------------------------------------
        jLabel_star_address = new JLabel("*");
        jLabel_star_address.setBounds(30, 360, 20, 20);
        jLabel_star_address.setForeground(Color.RED);
        jPanel.add(jLabel_star_address);

        JLabel jLabel_address = new JLabel("供应商地址：");
        jLabel_address.setFont(new Font("微软雅黑", Font.BOLD, 15));
        jLabel_address.setBounds(50, 360, 90, 20);
        jPanel.add(jLabel_address);

        jTextField_address = new JTextField(15);
        jTextField_address.setBounds(140, 356, 250, 30);
        jPanel.add(jTextField_address);

        //----------------------------------------------------------
        JButton jButton_add = new JButton("添加");
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

        /**绑定"关闭"按钮操作*/
        jButton_close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        /**绑定"添加"按钮操作*/
        jButton_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(new CheckOfNull().check(jTextField_nameinput.getText())){
                    JOptionPane.showMessageDialog(AddSupplierJFrame.this,"供应商名称不可为空！");
                }else if(CheckOfNull.check(jTextField_linkman.getText())){
                    JOptionPane.showMessageDialog(AddSupplierJFrame.this,"联系人不可为空！");
                }else if(CheckOfNull.check(jTextField_telphone.getText())){
                    JOptionPane.showMessageDialog(AddSupplierJFrame.this,"供应商电话不可为空！");
                }else if(CheckOfNull.check(jTextField_address.getText())){
                    JOptionPane.showMessageDialog(AddSupplierJFrame.this,"供应商地址不可为空！");
                }else{
                    try {
                        boolean checkOfSuccess=insertSupplier();
                        if(checkOfSuccess){
                            JOptionPane.showMessageDialog(AddSupplierJFrame.this,"添加成功！");
                            jTable.setModel(new SupplierTableModel());
                            dispose();
                        }else{
                            JOptionPane.showMessageDialog(AddSupplierJFrame.this,"添加失败！");
                        }
                    } catch (SQLException throwables) {
                        JOptionPane.showMessageDialog(AddSupplierJFrame.this,"添加失败！");
                        throwables.printStackTrace();
                    }
                }
            }
        });

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        /**创建线程，启动线程*/
        Thread threadCheck_supplier =new Thread(this);
        threadCheck_supplier.start();
        this.setVisible(true);
    }

    private boolean insertSupplier() throws SQLException {
        Supplier supplier=new Supplier();
        supplier.setSupplier_name(jTextField_nameinput.getText());
        supplier.setSupplier_description(jTextArea.getText());
        supplier.setLinkman(jTextField_linkman.getText());
        supplier.setSupplier_telphone(jTextField_telphone.getText());
        supplier.setSupplier_address(jTextField_address.getText());

        int countPrevious= (int) iSupplierService.getCountsOfId();
        int result = (int) iSupplierService.insertSupplier(supplier);
        int countNow= (int) iSupplierService.getCountsOfId();

        if(result>0 && countNow==countPrevious+1){
            return true;
        }else{
            return false;
        }
    }


/**--------------------------------------------------------------------------------*/

    /**
     * 不可为空项的输入框如果为空，则前面的警示红星亮起；飞空，则红星熄灭
     * */
    @Override
    public void run() {
        while(true){
            if(new CheckOfNull().check(jTextField_nameinput.getText())){
                jLabel_star_name.setVisible(true);
            }else{
                jLabel_star_name.setVisible(false);
            }

            if(new CheckOfNull().check(jTextField_linkman.getText())){
                jLabel_star_linkman.setVisible(true);
            }else{
                jLabel_star_linkman.setVisible(false);
            }

            if(CheckOfNull.check(jTextField_telphone.getText())){
                jLabel_star_telphone.setVisible(true);
            }else{
                jLabel_star_telphone.setVisible(false);
            }

            if(CheckOfNull.check(jTextField_address.getText())){
                jLabel_star_address.setVisible(true);
            }else{
                jLabel_star_address.setVisible(false);
            }
        }
    }
}
