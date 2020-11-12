package com.supermarket.tmodel;

import com.supermarket.entity.Account;
import com.supermarket.entity.GroupByCommodity;
import com.supermarket.factory.ObjectFactory;
import com.supermarket.service.IOrderByCommodityService;

import javax.swing.table.AbstractTableModel;
import java.sql.SQLException;
import java.util.List;

@SuppressWarnings("all")
public class GroupByCommodityTableModel extends AbstractTableModel {
    private IOrderByCommodityService iOrderByCommodityService = (IOrderByCommodityService) ObjectFactory.getObject("IOrderByCommodityService");
    private String[] title=null;
    private Object[][] datas=new Object[0][0];

    public GroupByCommodityTableModel() throws SQLException {
        title=new String[]{"商品名称","交易数量","交易金额","供应商名称"};
        List<GroupByCommodity> list= (List<GroupByCommodity>) iOrderByCommodityService.groupByCommodity();
        loadDataToModel(list);
    }

    public GroupByCommodityTableModel(int currentPage,int pageSize) throws SQLException {
        title=new String[]{"商品名称","交易数量","交易金额","供应商名称"};
        List<GroupByCommodity> list= (List<GroupByCommodity>) iOrderByCommodityService.listByPage(currentPage, pageSize);
        loadDataToModel(list);
    }

    public void loadDataToModel(List<GroupByCommodity> list){
        if(list.size()>0&&list!=null) {
            datas = new Object[list.size()][title.length];
            int i = 0 ;
            for(GroupByCommodity groupByCommodity :list) {
                datas[i][0]=groupByCommodity.getCommodityName();
                datas[i][1] =groupByCommodity.getNum();
                datas[i][2]=groupByCommodity.getPrice();
                datas[i][3]=groupByCommodity.getSupplierName();
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
