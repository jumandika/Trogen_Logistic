/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trogen.controller;

import java.awt.Color;
import java.awt.Component;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;
import trogen.koneksi.DeliveryOrderKoneksi;
import trogen.koneksi.JobOrderKoneksi;
import trogen.model.DOTabelModel;
import trogen.model.DeliveryOrderModel;
import trogen.model.JODOPreviewModel;
import trogen.model.JobOrderModel;
import trogen.service.impl.DeliveryOrderDao;
import trogen.service.impl.DeliveryOrderDaoImpl;
import trogen.service.impl.JobOrderDao;
import trogen.service.impl.JobOrderDaoImpl;
import trogen.view.AdminView111;
import trogen.view.DeliveryOrderPreview;

/**
 *
 * @author Asus
 */
public class DeliveryOrderController {

    DeliveryOrderModel dom;
    JODOPreviewModel jodom;
    JobOrderModel jom;
    AdminView111 av;
    DeliveryOrderPreview dop;
    DeliveryOrderDaoImpl doi;
    JobOrderDaoImpl joi;
    List<DeliveryOrderModel> dotm;
    List<JobOrderModel> jodotm;
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

    public DeliveryOrderController(AdminView111 av) {
        this.av = av;
        connection = DeliveryOrderKoneksi.connection();
        doi = new DeliveryOrderDao();
    }

    public DeliveryOrderController(DeliveryOrderPreview dop) {
        this.dop = dop;
        connection = JobOrderKoneksi.connection();
        joi = new JobOrderDao();
    }

    public void resetDeliveryOrder() {
        this.set_AutoDOnumber();
        av.getOrder_Number().setText("");
        av.getDelivery_Date().setText("");
        av.getDriver_ID().setText("");
        av.getDriver_Name1().setText("");
        av.getPlate_Number().setText("");
        av.getGoods_Cat().setText("");
        av.getDescription().setText("");
        av.getSender_Name().setText("");
        av.getReceiver_Name().setText("");
    }

    public void insertDeliveryOrder() {
        DeliveryOrderModel d = new DeliveryOrderModel();
        d.setDo_number(av.getDO_Number().getText());
        d.setOrder_number(av.getOrder_Number().getText());
        d.setDate_delivery(av.getDelivery_Date().getText());
        d.setDriver_id(av.getDriver_ID().getText());
        d.setDriver_name(av.getDriver_Name1().getText());
        d.setPlate_numb(av.getPlate_Number().getText());
        d.setGoods_cat(av.getGoods_Cat().getText());
        d.setDescription(av.getDescription().getText());
        d.setSender_name(av.getSender_Name().getText());
        d.setReceiver_name(av.getReceiver_Name().getText());
        d.setAdmin_id(av.getHome_ID().getText());
        doi.insert(d);
    }

    public void updateDeliveryOrder(AdminView111 av) {
        DeliveryOrderModel d = new DeliveryOrderModel();
        d.setDo_number(av.getDO_Number().getText());
        d.setOrder_number(av.getOrder_Number().getText());
        d.setDate_delivery(av.getDelivery_Date().getText());
        d.setDriver_id(av.getDriver_ID().getText());
        d.setDriver_name(av.getDriver_Name1().getText());
        d.setPlate_numb(av.getPlate_Number().getText());
        d.setGoods_cat(av.getGoods_Cat().getText());
        d.setDescription(av.getDescription().getText());
        d.setSender_name(av.getSender_Name().getText());
        d.setReceiver_name(av.getReceiver_Name().getText());
        d.setAdmin_id(av.getHome_ID().getText());
        doi.update(d);

    }

    public void deleteDeliveryOrder() {
        if (!av.getDO_Number().getText().trim().isEmpty()) {
            String DO_Number = av.getDO_Number().getText();
            doi.delete(DO_Number);
        } else {
            JOptionPane.showMessageDialog(av, "Pilih data yang akan di hapus");
        }
    }

    public void set_AutoDOnumber() {
        dom = doi.auto_number();
        av.getDO_Number().setText(dom.getDo_number());
    }
    
    public void docount(){
        dom = doi.deliveryordercount();
        av.getDOCount().setText(dom.getJumlah());
    }

    public void setTabelDOPreview(DeliveryOrderPreview dop) {
        jodotm = joi.getTabel_DOPreview(dop.getOrder_Number().getText());
        JODOPreviewModel jodopreviewmodel = new JODOPreviewModel(jodotm);
        dop.getTabel_DO().setModel(jodopreviewmodel);
        int a;
        for (a = 0; a < dop.getTabel_DO().getColumnCount(); a++) {
            dop.getTabel_DO().getColumnModel().getColumn(a).setCellRenderer(new WordWrapCellRenderer());
        }
    }

    public void setDOPreview(DeliveryOrderPreview dop) {
        jom = joi.getForDOPreview(dop.getOrder_Number().getText());
        dop.getSender_Company().setText(jom.getSender_comp_name());
        dop.getComp_Address().setText(jom.getSender_comp_address());
        dop.getSender_Phone().setText(jom.getSender_phone());
        dop.getDeliver_To().setText(jom.getReceiver_comp_name());
        dop.getDel_Address().setText(jom.getReceiver_comp_address());
        dop.getReceiver_Phone().setText(jom.getReceiver_phone());
        dop.getOrder_Date().setText(jom.getDate().toString());
    }

    public void isiTabelDeliveryOrder() {
        dotm = doi.getALL();
        DOTabelModel dotabelmodel = new DOTabelModel(dotm);
        av.getTabel_DO().setModel(dotabelmodel);
        int a;
        for (a = 0; a < av.getTabel_DO().getColumnCount(); a++) {
            av.getTabel_DO().getColumnModel().getColumn(a).setCellRenderer(new WordWrapCellRenderer());
        }
    }

    public void isiTableCariDO_Number(AdminView111 av) {
        dotm = doi.getCariDO_Number(av.getSearch_DO_Number().getText());
        DOTabelModel dotabelmodel = new DOTabelModel(dotm);
        av.getTabel_DO().setModel(dotabelmodel);
        int a;
        for (a = 0; a < av.getTabel_DO().getColumnCount(); a++) {
            av.getTabel_DO().getColumnModel().getColumn(a).setCellRenderer(new WordWrapCellRenderer());
        }
    }

    public void fieldDeliveryOrder(boolean t) {
        av.getDO_Number().setEnabled(t);
        av.getOrder_Number().setEnabled(t);
        av.getDelivery_Date().setEnabled(t);
        av.getDriver_ID().setEnabled(t);
        av.getDriver_Name().setEnabled(t);
        av.getPlate_Number().setEnabled(t);
        av.getGoods_Cat().setEnabled(t);
        av.getDescription().setEnabled(t);
        av.getSender_Name().setEnabled(t);
        av.getReceiver_Name().setEnabled(t);
    }

    public void isiFieldDeliveryOrder(int row) {
//        av.getDO_Number().setText(dotm.get(row).getDo_number());
//        av.getOrder_Number().setText(dotm.get(row).getOrder_number());
//        av.getDelivery_Date().setText(dotm.get(row).getDate_delivery());
//        av.getDriver_ID().setText(dotm.get(row).getDriver_id());
//        av.getDriver_Name1().setText(dotm.get(row).getDriver_name());
//        av.getTruck_ID().setText(dotm.get(row).getTruck_id());
//        av.getGoods_Cat().setText(dotm.get(row).getGoods_cat());
//        av.getDescription().setText(dotm.get(row).getDescription());
//        av.getSender_Name().setText(dotm.get(row).getSender_name());
//        av.getReceiver_Name().setText(dotm.get(row).getReceiver_name());
        av.getDO_Number().setText(av.getTabel_DO().getValueAt(av.getTabel_DO().getSelectedRow(), 0).toString());
        av.getOrder_Number().setText(av.getTabel_DO().getValueAt(av.getTabel_DO().getSelectedRow(), 1).toString());
        av.getDelivery_Date().setText(av.getTabel_DO().getValueAt(av.getTabel_DO().getSelectedRow(), 2).toString());
        av.getDriver_ID().setText(av.getTabel_DO().getValueAt(av.getTabel_DO().getSelectedRow(), 3).toString());
        av.getDriver_Name1().setText(av.getTabel_DO().getValueAt(av.getTabel_DO().getSelectedRow(), 4).toString());
        av.getPlate_Number().setText(av.getTabel_DO().getValueAt(av.getTabel_DO().getSelectedRow(), 5).toString());
        av.getGoods_Cat().setText(av.getTabel_DO().getValueAt(av.getTabel_DO().getSelectedRow(), 6).toString());
        av.getDescription().setText(av.getTabel_DO().getValueAt(av.getTabel_DO().getSelectedRow(), 7).toString());
        av.getSender_Name().setText(av.getTabel_DO().getValueAt(av.getTabel_DO().getSelectedRow(), 8).toString());
        av.getReceiver_Name().setText(av.getTabel_DO().getValueAt(av.getTabel_DO().getSelectedRow(), 9).toString());

    }
}
