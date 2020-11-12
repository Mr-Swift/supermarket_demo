package com.supermarket.tmodel;


import com.supermarket.entity.Supplier;
import com.supermarket.factory.ObjectFactory;
import com.supermarket.service.ISupplierService;

import javax.swing.table.AbstractTableModel;
import java.sql.SQLException;
import java.util.List;

@SuppressWarnings("all")
public class SupplierTableModel extends AbstractTableModel {
    private ISupplierService iSupplierService= (ISupplierService) ObjectFactory.getObject("ISupplierService");
    private String[] title=null;
    private Object[][] datas=new Object[0][0];

    public SupplierTableModel() throws SQLException {
        title = new String[]{"编号","供应商名称","供应商描述","联系人","电话","地址"};
        List<Supplier> list= (List<Supplier>) iSupplierService.listSuppliers();
        loadDataToModel(list);
    }

    public SupplierTableModel(int currentPage,int pageSize) throws SQLException {
        title = new String[]{"编号","供应商名称","供应商描述","联系人","电话","地址"};
        List<Supplier> list = (List<Supplier>) iSupplierService.listByPage(currentPage, pageSize);
        loadDataToModel(list);
    }

    public SupplierTableModel(String name) throws SQLException {
        title = new String[]{"编号","供应商名称","供应商描述","联系人","电话","地址"};
        List<Supplier> list = (List<Supplier>) iSupplierService.listBySupplierName(name);
        loadDataToModel(list);
    }

    public void loadDataToModel(List<Supplier> list){
        if(list.size()>0&&list!=null) {
            datas = new Object[list.size()][title.length];
            int i = 0 ;
            for(Supplier s:list) {
                datas[i][0]=s.getSupplier_id();
                datas[i][1]=s.getSupplier_name();
                datas[i][2] = s.getSupplier_description();
                datas[i][3]=s.getLinkman();
                datas[i][4]=s.getSupplier_telphone();
                datas[i][5] = s.getSupplier_address();
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
