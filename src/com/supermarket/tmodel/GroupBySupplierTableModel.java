package com.supermarket.tmodel;

import com.supermarket.entity.GroupByCommodity;
import com.supermarket.entity.GroupBySupplier;
import com.supermarket.factory.ObjectFactory;
import com.supermarket.service.IOrderBySupplierService;

import javax.swing.table.AbstractTableModel;
import java.sql.SQLException;
import java.util.List;

@SuppressWarnings("all")
public class GroupBySupplierTableModel extends AbstractTableModel {
    private IOrderBySupplierService iOrderBySupplierService= (IOrderBySupplierService) ObjectFactory.getObject("IOrderBySupplierService");
    private String[] title=null;
    private Object[][] datas=new Object[0][0];

    public GroupBySupplierTableModel() throws SQLException {
        title=new String[]{"供应商编号","供应商名称","总交易金额","商品种类","总商品数量"};
        List<GroupBySupplier> list= (List<GroupBySupplier>) iOrderBySupplierService.OrderBySupplier();
        loadDataToModel(list);
    }

    public GroupBySupplierTableModel(int currentPage,int pageSize) throws SQLException {
        title=new String[]{"供应商编号","供应商名称","总交易金额","商品种类","总商品数量"};
        List<GroupBySupplier> list = (List<GroupBySupplier>) iOrderBySupplierService.listByPage(currentPage, pageSize);
        loadDataToModel(list);
    }

    public void loadDataToModel(List<GroupBySupplier> list){
        if(list.size()>0&&list!=null) {
            datas = new Object[list.size()][title.length];
            int i = 0 ;
            for(GroupBySupplier groupBySupplier :list) {
                datas[i][0]=groupBySupplier.getSupplierId();
                datas[i][1] =groupBySupplier.getSupplierName();
                datas[i][2]=groupBySupplier.getPrice();
                datas[i][3]=groupBySupplier.getType();
                datas[i][4]=groupBySupplier.getNum();
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
