/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trogen.controller;

import java.awt.Color;
import java.awt.Component;
import java.sql.Connection;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;
import trogen.koneksi.InvoiceKoneksi;
import trogen.model.CustomerModel;
import trogen.model.DeliveryOrderModel;
import trogen.model.InvoiceModel;
import trogen.model.InvoicePreviewModel;
import trogen.model.InvoiceTabelModel;
import trogen.model.TruckModel;
import trogen.model.JobOrderModel;
import trogen.service.impl.DeliveryOrderDao;
import trogen.service.impl.InvoiceDao;
import trogen.service.impl.InvoiceDaoImpl;
import trogen.view.AdminView111;
import trogen.view.InvoicePreview;
import trogen.view.JobOrderView;

/**
 *
 * @author Asus
 */
public class InvoiceController {

    InvoiceModel im;
    TruckModel tm;
    DeliveryOrderModel dom;
    JobOrderModel jom;
    AdminView111 av;
    InvoiceDaoImpl ii;
    DeliveryOrderDao doi;
    CustomerModel cm;
    InvoicePreview ip;
    JobOrderView jov;
    List<InvoiceModel> itm;
    Connection connection;
    
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

    public InvoiceController(AdminView111 av) {
        this.av = av;
        connection = InvoiceKoneksi.connection();
        ii = new InvoiceDao();
        doi = new DeliveryOrderDao();

    }

    public InvoiceController(InvoicePreview ip) {
        this.ip = ip;
        connection = InvoiceKoneksi.connection();
        ii = new InvoiceDao();
    }

    public InvoiceController(JobOrderView jov) {
        this.jov = jov;

    }

    public void resetInvoice() {
        av.getInvoice_Number().setText("");
        av.getInvoice_Date().setDate(null);
        av.getOrder_Number1().setText("");
        av.getSubtotal().setText("");
        av.getSender_Comp_Name().setText("");
        av.getPick_Place().setText("");
        av.getDestination().setText("");
        av.getDistance().setText("");
        av.getDist_Cost().setText("");
        av.getTruck_Cost().setText("");
        av.getTruck_Cat1().setText("");
        av.getCaro_Type1().setText("");
        av.getInsurance().setText("");
        av.getTax().setText("");
        av.getTotal_Cost().setText("");

    }

    public void insertInvoice() {
        InvoiceModel i = new InvoiceModel();
        i.setInvoice_number(av.getInvoice_Number().getText());
        i.setInvoice_date(av.getInvoice_Date().getDate());
        i.setOrder_number(av.getOrder_Number1().getText());
        i.setSender_comp_name(av.getSender_Comp_Name().getText());
        i.setPick_place(av.getPick_Place().getText());
        i.setDestination(av.getDestination().getText());
        i.setDistance(av.getDistance().getText());
        i.setDist_cost(av.getDist_Cost().getText());
        i.setTruck_cat(av.getTruck_Cat1().getText());
        i.setCaro_type(av.getCaro_Type1().getText());
        i.setSubtotal(av.getSubtotal().getText());
        i.setTax(av.getTax().getText());
        i.setTruck_rent_cost(av.getTruck_Cost().getText());
        i.setInsurance(av.getInsurance().getText());
        i.setTotal_cost(av.getTotal_Cost().getText());
        i.setAdmin_id(av.getHome_ID().getText());
        ii.insert(i);
    }

    public void updateInvoice() {
        InvoiceModel i = new InvoiceModel();
        i.setInvoice_number(av.getInvoice_Number().getText());
        i.setInvoice_date(av.getInvoice_Date().getDate());
        i.setOrder_number(av.getOrder_Number1().getText());
        i.setSender_comp_name(av.getSender_Comp_Name().getText());
        i.setPick_place(av.getPick_Place().getText());
        i.setDestination(av.getDestination().getText());
        i.setDistance(av.getDistance().getText());
        i.setDist_cost(av.getDist_Cost().getText());
        i.setTruck_cat(av.getTruck_Cat1().getText());
        i.setCaro_type(av.getCaro_Type1().getText());
        i.setSubtotal(av.getSubtotal().getText());
        i.setTax(av.getTax().getText());
        i.setTruck_rent_cost(av.getTruck_Cost().getText());
        i.setInsurance(av.getInsurance().getText());
        i.setTotal_cost(av.getTotal_Cost().getText());
        i.setAdmin_id(av.getHome_ID().getText());
        ii.update(i);
    }

    public void deleteInvoice() {
        if (!av.getInvoice_Number().getText().trim().isEmpty()) {
            String Invoice_Number = av.getInvoice_Number().getText();
            ii.delete(Invoice_Number);
        } else {
            JOptionPane.showMessageDialog(av, "Pilih data yang akan di hapus");
        }
    }

    public void set_AutoINVnumber() {
        im = ii.auto_number();
        av.getInvoice_Number().setText(im.getInvoice_number());

    }
    
    public void invoicecount(){
        im = ii.invoicecount();
        av.getInvoiceCount().setText(im.getJumlah());
    }

    public void isiTabelInvoice() {
        itm = ii.getALL();
        InvoiceTabelModel invoicetabelmodel = new InvoiceTabelModel(itm);
        av.getTabel_Invoice().setModel(invoicetabelmodel);
        int a;
        for (a = 0; a < av.getTabel_Invoice().getColumnCount(); a++) {
            av.getTabel_Invoice().getColumnModel().getColumn(a).setCellRenderer(new WordWrapCellRenderer());
        }
    }

    public void isiTableCariInvoice_Number(AdminView111 av) {
        itm = ii.getCariInvoice_Number(av.getSearch_Invoice_Number().getText());
        InvoiceTabelModel invoicetabelmodel = new InvoiceTabelModel(itm);
        av.getTabel_Invoice().setModel(invoicetabelmodel);
        int a;
        for (a = 0; a < av.getTabel_Invoice().getColumnCount(); a++) {
            av.getTabel_Invoice().getColumnModel().getColumn(a).setCellRenderer(new WordWrapCellRenderer());
        }
    }

    public void getTableInvPreview(InvoicePreview ip) {
        itm = ii.getTabel_InvPreview(ip.getInvoice_Number().getText());
        InvoicePreviewModel invoicepreviewmodel = new InvoicePreviewModel(itm);
        ip.getTabel_Invoice().setModel(invoicepreviewmodel);
        int a;
        for (a = 0; a < ip.getTabel_Invoice().getColumnCount(); a++) {
            ip.getTabel_Invoice().getColumnModel().getColumn(a).setCellRenderer(new WordWrapCellRenderer());
        }
    }

    public void getDetail(InvoicePreview ip) {
        im = ii.getInvoicePreview(ip.getInvoice_Number().getText());
        ip.getSubtotal().setText(im.getSubtotal());
        ip.getTax().setText(im.getTax());
        ip.getInsurance().setText(im.getInsurance());
        ip.getCost_Total().setText(im.getTotal_cost());
    }

    public void getSelectedTruck(AdminView111 av) {
        tm = ii.getSelectedTruck(av.getplatenumber().getText());
        av.getTruck_Cat1().setText(tm.getTruck_cat());
        av.getCaro_Type1().setText(tm.getCaro_type());
    }

    public void getDistance(AdminView111 av) {
        jom = ii.getDistance(av.getOrder_Number1().getText());
        av.getDistance().setText(jom.getDistance());
    }

    public void getTruckID(AdminView111 av) {
        dom = ii.getTruckID(av.getOrder_Number1().getText());
        av.getplatenumber().setText(dom.getPlate_numb());
    }

    public void fieldInvoice(boolean t) {
        av.getInvoice_Number().setEnabled(t);
        av.getInvoice_Date().setEnabled(t);
        av.getOrder_Number1().setEnabled(t);
        av.getSender_Comp_Name().setEnabled(t);
        av.getPick_Place().setEnabled(t);
        av.getDestination().setEnabled(t);
        av.getDistance().setEnabled(t);
        av.getDist_Cost().setEnabled(t);
        av.getTruck_Cost().setEnabled(t);
        av.getSubtotal().setEnabled(t);
        av.getTax().setEnabled(t);
        av.getInsurance().setEnabled(t);
        av.getTotal_Cost().setEnabled(t);
    }

    public void isiFieldInvoice(int row) {
        av.getInvoice_Number().setText(itm.get(row).getInvoice_number());
        av.getInvoice_Date().setDate((itm.get(row).getInvoice_date()));
        av.getOrder_Number1().setText(itm.get(row).getOrder_number());
        av.getSender_Comp_Name().setText(itm.get(row).getSender_comp_name());
        av.getPick_Place().setText(itm.get(row).getPick_place());
        av.getDestination().setText(itm.get(row).getDestination());
        av.getDistance().setText(itm.get(row).getDistance());
        av.getDist_Cost().setText(itm.get(row).getDist_cost());
        av.getTruck_Cat1().setText(itm.get(row).getTruck_cat());
        av.getCaro_Type1().setText(itm.get(row).getCaro_type());
        av.getTruck_Cost().setText(itm.get(row).getTruck_rent_cost());
        av.getSubtotal().setText(itm.get(row).getSubtotal());
        av.getInsurance().setText(itm.get(row).getInsurance());
        av.getTax().setText(itm.get(row).getTax());
        av.getTotal_Cost().setText(itm.get(row).getTotal_cost());
    }

}
