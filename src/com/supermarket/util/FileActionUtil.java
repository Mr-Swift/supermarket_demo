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
import java.util.ArrayList;
import java.util.List;


@SuppressWarnings("all")
public class FileActionUtil {
    public static IAccountService iAccountService = (IAccountService) ObjectFactory.getObject("IAccountService");
    public static ISupplierService iSupplierService = (ISupplierService) ObjectFactory.getObject("ISupplierService");

    public static boolean fileOutput(File file, List list) {

        List listPart = new ArrayList();
        if (list.size() != 0 || list != null) {
            for (int i = 0; i < list.size(); i++) {
                List listById = (List) iAccountService.listById((Integer) list.get(i));
                listPart.add(listById.get(0));
            }
        }


        Writer writer = null;
        BufferedWriter bufferedWriter = null;
        try {

            writer = new FileWriter(file, true);
            bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.newLine();
            if (listPart.size()!=0) {
                for (Object obj : listPart) {
                    Account account = (Account) obj;
                    bufferedWriter.write(account.getAccount_id() + " ");
                    bufferedWriter.write(account.getCommodity_name() + " ");
                    bufferedWriter.write(account.getNum() + " ");
                    bufferedWriter.write(account.getPrice() + " ");
                    bufferedWriter.write(account.getPay_check() + " ");
                    bufferedWriter.write(account.getSupplier_name() + " ");
                    bufferedWriter.write(account.getAccount_description() + " ");
                    bufferedWriter.write(account.getTime() + " ");
                    bufferedWriter.newLine();
                }
            } else {
                List listAll = (List) iAccountService.listAccounts();
                for (Object obj : listAll) {
                    Account account = (Account) obj;
                    bufferedWriter.write(account.getAccount_id() + " ");
                    bufferedWriter.write(account.getCommodity_name() + " ");
                    bufferedWriter.write(account.getNum() + " ");
                    bufferedWriter.write(account.getPrice() + " ");
                    bufferedWriter.write(account.getPay_check() + " ");
                    bufferedWriter.write(account.getSupplier_name() + " ");
                    bufferedWriter.write(account.getAccount_description() + " ");
                    bufferedWriter.write(account.getTime() + " ");
                    bufferedWriter.newLine();
                }
            }
            bufferedWriter.newLine();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (bufferedWriter != null) {
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

    public static boolean fileInput(File file) {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            String msg = null;
            while (null != (msg = bufferedReader.readLine())) {
                String[] strings = msg.split(" ");
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
        } finally {
            if (bufferedReader != null) {
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
