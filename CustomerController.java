/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trogen.controller;

import java.awt.Color;
import java.awt.Component;
import trogen.model.CustomerModel;
import trogen.model.CustomerTabelModel;
import trogen.service.impl.CustomerDao;
import trogen.service.impl.CustomerDaoImpl;
import trogen.view.AdminView111;
import trogen.view.InputJobOrderView;
import java.util.List;
import javax.swing.JOptionPane;
import java.sql.*;
import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;
import trogen.koneksi.CustomerKoneksi;
import trogen.view.Customer_List;
import trogen.view.EditJobOrderView;
import trogen.view.InvoicePreview;
import trogen.view.JobOrderView;

/**
 *
 * @author Asus
 */
public class CustomerController {

    CustomerModel cm;
    InputJobOrderView ijov;
    EditJobOrderView ejov;
    AdminView111 av;
    CustomerDaoImpl cdi;
    List<CustomerModel> ctm;
    Customer_List cl;
    InvoicePreview ip;
    JobOrderView jov;
    Connection connection;

    public CustomerController(InputJobOrderView ijov) {
        this.ijov = ijov;
        connection = CustomerKoneksi.connection();
        cdi = new CustomerDao();
    }

    public CustomerController(EditJobOrderView ejov) {
        this.ejov = ejov;
        connection = CustomerKoneksi.connection();
        cdi = new CustomerDao();
    }

    static class WordWrapCellRenderer extends JTextArea implements TableCellRenderer {

        WordWrapCellRenderer() {
            setLineWrap(true);
            setWrapStyleWord(true);
            setRows(2);
//            setFont(label30.getFont());
//            setForeground(label30.getForeground());
            Border border = BorderFactory.createEmptyBorder();
            setBorder(border);
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText(value.toString());
            setSize(table.getColumnModel().getColumn(column).getWidth(), getPreferredSize().height);
            if (table.getRowHeight(row) != getPreferredSize().height) {
                table.setRowHeight(row, getPreferredSize().height);
            }
            if (isSelected) {
                setBackground(table.getSelectionBackground());
                setForeground(Color.white);
            } else {
                setBackground(table.getBackground());
                setForeground(Color.BLACK);

            }
            return this;
        }
    }

    public CustomerController(AdminView111 av) {
        this.av = av;
        connection = CustomerKoneksi.connection();
        cdi = new CustomerDao();
    }

    public CustomerController(InvoicePreview ip) {
        this.ip = ip;
        connection = CustomerKoneksi.connection();
        cdi = new CustomerDao();
    }

    public CustomerController(JobOrderView jov) {
        this.jov = jov;
        connection = CustomerKoneksi.connection();
        cdi = new CustomerDao();
    }

    public CustomerController(Customer_List cl) {
        this.cl = cl;
        connection = CustomerKoneksi.connection();
        cdi = new CustomerDao();
    }

    public void set_Autocustid() {
        cm = cdi.auto_custid();
        ijov.getSearch_Cust_ID().setText(cm.getCust_id());
    }

    /**
     *
     * @param av
     * @param ip
     */
    public void set_Selectcust(AdminView111 av, InvoicePreview ip) {
        cm = cdi.select_cust(av.getSender_Comp_Name().getText());
        ip.getCust_ID().setText(cm.getCust_id());
        ip.getCust_Name().setText(cm.getSender_name());
        ip.getSender_Company().setText(cm.getSender_comp_name());
        ip.getComp_Address().setText(cm.getSender_comp_address());
        ip.getSender_Phone().setText(cm.getSender_phone());
    }

    public void get_ForInvoice(AdminView111 av) {
        String phone_numb = av.getTabel_JO().getValueAt(av.getTabel_JO().getSelectedRow(), 4).toString();
        cm = cdi.getForInvoice(phone_numb);
//        System.out.println(phone_numb);
        av.getSender_Comp_Name().setText(cm.getSender_comp_name());
    }

    public void getForInvoiceAdd(AdminView111 av, JobOrderView jov) {
        cm = cdi.getForInvoice(jov.getTabel_Job_Order().getValueAt(jov.getTabel_Job_Order().getSelectedRow(), 3).toString());
        av.getCust_ID().setText(cm.getCust_id());
    }

    public void insertCustomer() {
        if (ijov.getSender_Name1().getText().equals("")) {
            JOptionPane.showMessageDialog(ijov, "Please fill out the Sender Name");
        } else if (ijov.getSender_Comp_Name1().getText().equals("")) {
            JOptionPane.showMessageDialog(ijov, "Please fill out the \nSender Company Name");
        } else if (ijov.getSender_Comp_Address1().getText().equals("")) {
            JOptionPane.showMessageDialog(ijov, "Please fill out the \nSender Company Address");
        } else if (ijov.getSender_Phone1().getText().equals("")) {
            JOptionPane.showMessageDialog(ijov, "Please fill out the Sender Phone");
        } else {
            CustomerModel c = new CustomerModel();
            c.setCust_id(ijov.getSearch_Cust_ID().getText());
            c.setSender_name(ijov.getSender_Name1().getText());
            c.setSender_comp_name(ijov.getSender_Comp_Name1().getText());
            c.setCust_region(ijov.getPick_City().getSelectedItem().toString());
            c.setSender_comp_address(ijov.getSender_Comp_Address1().getText());
            c.setSender_phone(ijov.getSender_Phone1().getText());
            c.setAdmin_id(av.getHome_ID().getText());
            cdi.insert(c);
            JOptionPane.showMessageDialog(ijov, "New Customer Saved.");
        }
    }

    public void insertCustomer1() {
        if (av.getSender_Name2().getText().equals("")) {
            JOptionPane.showMessageDialog(av, "Please fill out the Sender Name");
        } else if (av.getSender_Comp_Name2().getText().equals("")) {
            JOptionPane.showMessageDialog(av, "Please fill out the \nSender Company Name");
        } else if (av.getSender_Comp_Address2().getText().equals("")) {
            JOptionPane.showMessageDialog(av, "Please fill out the \nSender Company Address");
        } else if (av.getSender_Phone2().getText().equals("")) {
            JOptionPane.showMessageDialog(av, "Please fill out the Sender Phone");
        } else {
            CustomerModel c = new CustomerModel();
            c.setCust_id(av.getCust_ID2().getText());
            c.setSender_name(av.getSender_Name2().getText());
            c.setSender_comp_name(av.getSender_Comp_Name2().getText());
            c.setCust_region(av.getPick_City2().getSelectedItem().toString());
            c.setSender_comp_address(av.getSender_Comp_Address2().getText());
            c.setSender_phone(av.getSender_Phone2().getText());
            c.setAdmin_id(av.getHome_ID().getText());
            cdi.insert(c);
            JOptionPane.showMessageDialog(av, "New Customer Saved.");
        }
    }

    public void updateCustomer() {
        CustomerModel c = new CustomerModel();
        c.setCust_id(ijov.getSearch_Cust_ID().getText());
        c.setSender_name(ijov.getSender_Name1().getText());
        c.setSender_comp_name(ijov.getSender_Comp_Name1().getText());
        c.setCust_region(ijov.getPick_City().getSelectedItem().toString());
        c.setSender_comp_address(ijov.getSender_Comp_Address1().getText());
        c.setSender_phone(ijov.getSender_Phone1().getText());
        c.setAdmin_id(av.getHome_ID().getText());
        cdi.update(c);
        JOptionPane.showMessageDialog(ijov, "Customer Data Updated");
    }

    public void updateCustomer1() {
        CustomerModel c = new CustomerModel();
        c.setCust_id(av.getCust_ID2().getText());
        c.setSender_name(av.getSender_Name2().getText());
        c.setSender_comp_name(av.getSender_Comp_Name2().getText());
        c.setCust_region(av.getPick_City2().getSelectedItem().toString());
        c.setSender_comp_address(av.getSender_Comp_Address2().getText());
        c.setSender_phone(av.getSender_Phone2().getText());
        c.setAdmin_id(av.getHome_ID().getText());
        cdi.update(c);
        JOptionPane.showMessageDialog(av, "Customer Data Updated");
    }

    public void deleteCustomer() {
//        if (!av.getCust_ID().getText().trim().isEmpty()) {
        String Cust_ID = av.getTabel_Customer().getValueAt(av.getTabel_Customer().getSelectedRow(), 0).toString();
        cdi.delete(Cust_ID);
//        } else {
        JOptionPane.showMessageDialog(ijov, "Customer Data deleted");
//        }
    }

    public void deleteCustomer1() {
        if (!av.getCust_ID2().getText().trim().isEmpty()) {
            String Cust_ID = av.getCust_ID2().getText();
            cdi.delete(Cust_ID);
        } else {
            JOptionPane.showMessageDialog(av, "Pilih data yang akan di hapus");
        }
    }

    public void isiTabelCustomer() {
        ctm = cdi.getALL();
        CustomerTabelModel customertabelmodel = new CustomerTabelModel(ctm);
        av.getTabel_Customer().setModel(customertabelmodel);
        int a;
        for (a = 0; a < av.getTabel_Customer().getColumnCount(); a++) {
            av.getTabel_Customer().getColumnModel().getColumn(a).setCellRenderer(new WordWrapCellRenderer());
        }
    }

    public void isiTabelListCustomer() {
        ctm = cdi.getALL();
        CustomerTabelModel customertabelmodel = new CustomerTabelModel(ctm);
        cl.getTabel_Customer().setModel(customertabelmodel);
    }

    public void isiTableCariNamaCustomer1() {
        ctm = cdi.getCariNama(av.getSearch_Cust_Name2().getText());
        CustomerTabelModel customertabelmodel = new CustomerTabelModel(ctm);
        av.getTabel_Customer().setModel(customertabelmodel);
        int a;
        for (a = 0; a < av.getTabel_Customer().getColumnCount(); a++) {
            av.getTabel_Customer().getColumnModel().getColumn(a).setCellRenderer(new WordWrapCellRenderer());
        }
    }

    public void isiFieldCustomer(int row) {
        av.getCust_ID2().setText(ctm.get(row).getCust_id());
        av.getSender_Name2().setText(ctm.get(row).getSender_name());
        av.getSender_Comp_Name2().setText(ctm.get(row).getSender_comp_name());
        av.getSender_Comp_Address2().setText(ctm.get(row).getSender_comp_address());
        av.getPick_City2().setSelectedItem(ctm.get(row).getCust_region());
        av.getSender_Phone2().setText(ctm.get(row).getSender_phone());
    }

    public void isiTableCariNamaCustomer(Customer_List cl) {
        ctm = cdi.getCariNama(cl.getSearch_Cust_Name().getText());
        CustomerTabelModel customertabelmodel = new CustomerTabelModel(ctm);
        cl.getTabel_Customer().setModel(customertabelmodel);
    }

    public void fieldCustomer(boolean t) {
        av.getCust_ID2().setEnabled(t);
        av.getSender_Name2().setEnabled(t);
        av.getSender_Comp_Name2().setEnabled(t);
        av.getPick_City2().setEnabled(t);
        av.getSender_Comp_Address2().setEnabled(t);
        av.getSender_Phone2().setEnabled(t);
    }

    public void resetCustomer() {
        av.getCust_ID2().setText("");
        av.getSender_Name2().setText("");
        av.getSender_Comp_Name2().setText("");
        av.getPick_City2().setSelectedItem(null);
        av.getSender_Comp_Address2().setText("");
        av.getSender_Phone2().setText("");
    }

    public void setAuto_id() {
        cm = cdi.auto_custid();
        av.getCust_ID2().setText(cm.getCust_id());
    }

}
