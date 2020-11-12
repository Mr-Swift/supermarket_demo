package com.supermarket.util;

import com.supermarket.entity.Account;
import com.supermarket.entity.Supplier;
import com.supermarket.factory.ObjectFactory;
import com.supermarket.service.IAccountService;
import com.supermarket.service.IOrderBySupplierService;
import com.supermarket.service.ISupplierService;
import com.supermarket.ui.MenuJFrame;

import javax.swing.*;
import java.io.*;
import java.sql.SQLException;
import java.util.List;


@SuppressWarnings("all")
public class FileActionUtil {
    public  static IAccountService iAccountService= (IAccountService) ObjectFactory.getObject("IAccountService");
    public static ISupplierService iSupplierService= (ISupplierService) ObjectFactory.getObject("ISupplierService");
    public static boolean fileOutput(File file)  {
        Writer writer=null;
        BufferedWriter bufferedWriter=null;
        try {
            writer=new FileWriter(file,true);
            bufferedWriter=new BufferedWriter(writer);
            List list= (List) iAccountService.listAccounts();
            for (Object obj:list){
                Account account= (Account) obj;
                bufferedWriter.write(account.getAccount_id()+" ");
                bufferedWriter.write(account.getCommodity_name()+" ");
                bufferedWriter.write(account.getNum()+" ");
                bufferedWriter.write(account.getPrice()+" ");
                bufferedWriter.write(account.getPay_check()+" ");
                bufferedWriter.write(account.getSupplier_name()+" ");
                bufferedWriter.write(account.getAccount_description()+" ");
                bufferedWriter.write(account.getTime()+" ");
                bufferedWriter.newLine();
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }finally {
            if (bufferedWriter!=null){
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return true;
    }

    public static boolean fileInput(File file){
        BufferedReader bufferedReader=null;
        try {
            bufferedReader=new BufferedReader(new FileReader(file));
            String msg=null;
            while(null!=(msg= bufferedReader.readLine())){
                String[] strings=msg.split(" ");
                Supplier supplier = new Supplier();
                supplier.setSupplier_name(strings[0]);
                supplier.setSupplier_description(strings[1]);
                supplier.setLinkman(strings[2]);
                supplier.setSupplier_telphone(strings[3]);
                supplier.setSupplier_address(strings[4]);
                iSupplierService.insertSupplier(supplier);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            if(bufferedReader!=null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }
}
