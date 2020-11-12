package com.supermarket.tmodel;

import com.supermarket.entity.Account;
import com.supermarket.factory.ObjectFactory;
import com.supermarket.service.IAccountService;

import javax.swing.table.AbstractTableModel;
import java.sql.SQLException;
import java.util.List;

@SuppressWarnings("all")
public class BillTableModel extends AbstractTableModel {
    private IAccountService iAccountService= (IAccountService) ObjectFactory.getObject("IAccountService");
    private String[] title=null;
    private Object[][] datas=new Object[0][0];

    public BillTableModel() throws SQLException {
        title=new String[]{"商品编号","商品名称","商品数量","交易金额","是否付款","供应商名称","商品描述","账单时间"};
        List<Account> list= (List<Account>) iAccountService.listAccounts();
        loadDataToModel(list);
    }

    public BillTableModel(int currentPage,int pageSize) throws SQLException {
        title=new String[]{"商品编号","商品名称","商品数量","交易金额","是否付款","供应商名称","商品描述","账单时间"};
        List<Account> list = (List<Account>) iAccountService.listByPage(currentPage, pageSize);
        loadDataToModel(list);
    }

    public BillTableModel(String pay) throws SQLException {
        title=new String[]{"商品编号","商品名称","商品数量","交易金额","是否付款","供应商名称","商品描述","账单时间"};
        List<Account> list = (List<Account>) iAccountService.listByPay(pay);
        loadDataToModel(list);
    }

    public BillTableModel(String name,String name2) throws SQLException {
        title=new String[]{"商品编号","商品名称","商品数量","交易金额","是否付款","供应商名称","商品描述","账单时间"};
        List<Account> list = (List<Account>) iAccountService.listByName(name);
        loadDataToModel(list);
    }

    public void loadDataToModel(List<Account> list){
        if(list.size()>0&&list!=null) {
            datas = new Object[list.size()][title.length];
            int i = 0 ;
            for(Account s :list) {
                datas[i][0]=s.getAccount_id();
                datas[i][1] = s.getCommodity_name();
                datas[i][2]=s.getNum();
                datas[i][3]=s.getPrice();
                datas[i][4]=s.getPay_check();
                datas[i][5]=s.getSupplier_name();
                datas[i][6]=s.getAccount_description();
                datas[i][7]=s.getTime();
                i++;
            }
        }
    }


    @Override
    public int getRowCount() {
        return datas.length;
    }

    @Override
    public int getColumnCount() {
        return title.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return datas[rowIndex][columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        return title[column];
    }
}
