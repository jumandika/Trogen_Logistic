/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trogen.controller;

import java.awt.Color;
import java.awt.Component;
import java.util.List;
import java.sql.*;
import javax.swing.BorderFactory;
import trogen.koneksi.TruckKoneksi;
import trogen.model.TruckModel;
import trogen.model.TruckTabelModel;
import trogen.service.impl.TruckDao;
import trogen.service.impl.TruckDaoImpl;
import trogen.view.AdminView111;
import trogen.view.TruckView;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;
import trogen.model.JobOrderModel;
import trogen.model.TruckListModel;
//import trogen.view.TruckView;

/**
 *
 * @author Asus
 */
public class TruckController {

    TruckModel tm;
    TruckDaoImpl tdi;
    AdminView111 av;
    TruckView tv;
    List<TruckModel> ttm;
    List<TruckModel> tlm;
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

    public TruckController(AdminView111 av) {
        this.av = av;
        tdi = new TruckDao();
        connection = TruckKoneksi.connection();
    }

    public TruckController(TruckView tv) {
        this.tv = tv;
        tdi = new TruckDao();
        connection = TruckKoneksi.connection();
    }
    public void resetTruck() {
        av.getTruck_Cat().setSelectedItem("Select Here");
        av.getCaro_Type().setSelectedItem("Select Here");
        av.getCaro_Size().setText("");
        av.getMax_Capacity().setText("");
        av.getPlate().setText("");
        av.getTruck_Id().setText("");
        av.getBrand().setSelectedItem("Select Here");
        av.getColor().setSelectedItem("Select Here");
        av.getCondition().setSelectedItem("Select Here");
        av.getStatus1().setSelectedItem("Select Here");
        av.getSample().setIcon(null);
    }
    
    public void fieldTruck(boolean t) {
        av.getTruck_Cat().setEnabled(t);
        av.getCaro_Type().setEnabled(t);
        av.getCaro_Size().setEnabled(t);
        av.getMax_Capacity().setEnabled(t);
        av.getPlate().setEnabled(t);
        av.getTruck_Id().setEnabled(t);
        av.getBrand().setEnabled(t);
        av.getColor().setEnabled(t);
        av.getCondition().setEnabled(t);
        av.getStatus1().setEnabled(t);
//        av.getSample().setIcon(null);
    }

    public void insertTruck() {
        TruckModel t = new TruckModel();
        t.setTruck_cat(av.getTruck_Cat().getSelectedItem().toString());
        t.setCaro_type(av.getCaro_Type().getSelectedItem().toString());
        t.setCaro_size(av.getCaro_Size().getText());
        t.setMax_cap(av.getMax_Capacity().getText());
        t.setPlate_numb(av.getPlate().getText());
        t.setTruck_id(av.getTruck_Id().getText());
        t.setBrand(av.getBrand().getSelectedItem().toString());
        t.setColor(av.getColor().getSelectedItem().toString());
        t.setCond(av.getCondition().getSelectedItem().toString());
        t.setStatus(av.getStatus1().getSelectedItem().toString());
        t.setAdmin_id(av.getHome_ID().getText());
        tdi.insert(t);
    }

    public void updateTruck() {
        TruckModel t = new TruckModel();
        t.setTruck_cat(av.getTruck_Cat().getSelectedItem().toString());
        t.setCaro_type(av.getCaro_Type().getSelectedItem().toString());
        t.setCaro_size(av.getCaro_Size().getText());
        t.setMax_cap(av.getMax_Capacity().getText());
        t.setPlate_numb(av.getPlate().getText());
        t.setTruck_id(av.getTruck_Id().getText());
        t.setBrand(av.getBrand().getSelectedItem().toString());
        t.setColor(av.getColor().getSelectedItem().toString());
        t.setCond(av.getCondition().getSelectedItem().toString());
        t.setStatus(av.getStatus1().getSelectedItem().toString());
        t.setAdmin_id(av.getHome_ID().getText());
        tdi.update(t);
    }

    public void setStatus(AdminView111 av) {
        TruckModel t = new TruckModel();
        t.setPlate_numb(av.getPlate_Number().getText());
        t.setStatus("Unavailable");
        tdi.set_status(t);
        JOptionPane.showMessageDialog(av, "Truck " + av.getPlate_Number().getText() + "Unavailable");
    }
    
    public void set_available(AdminView111 av) {
        JobOrderModel jom = new JobOrderModel();
        jom.setOrder_number(av.getTabel_JO().getValueAt(av.getTabel_JO().getSelectedRow(), 0).toString());
        tdi.set_available(jom);
        JOptionPane.showMessageDialog(av, "Truck Available");
    }
    
    public void deleteTruck() {
        if (!av.getTruck_Id().getText().trim().isEmpty()) {
            String Truck_ID = av.getTruck_Id().getText();
            tdi.delete(Truck_ID);
        } else {
            JOptionPane.showMessageDialog(av, "Pilih data yang akan di hapus");
        }

    }

    public void isiFieldTruck() {
        tm = tdi.isifieldtruck(av.getTabel_Truck().getValueAt(av.getTabel_Truck().getSelectedRow(), 3).toString());
        av.getTruck_Cat().setSelectedItem(tm.getTruck_cat());
        av.getCaro_Type().setSelectedItem(tm.getCaro_type());
        av.getCaro_Size().setText(tm.getCaro_size());
        av.getMax_Capacity().setText(tm.getMax_cap());
        av.getPlate().setText(tm.getPlate_numb());
        av.getTruck_Id().setText(tm.getTruck_id());
        av.getBrand().setSelectedItem(tm.getBrand());
        av.getColor().setSelectedItem(tm.getColor());
        av.getCondition().setSelectedItem(tm.getCond());
        av.getStatus1().setSelectedItem(tm.getStatus());
    }

    public void isiTabelTruck() {
        ttm = tdi.getALL();
        TruckTabelModel trucktabelmodel = new TruckTabelModel(ttm);
        av.getTabel_Truck().setModel(trucktabelmodel);
        int a;
        for (a = 0; a < av.getTabel_Truck().getColumnCount(); a++) {
            av.getTabel_Truck().getColumnModel().getColumn(a).setCellRenderer(new WordWrapCellRenderer());
        }
    }

    public void getTruckList(TruckView tv) {
        tlm = tdi.getTruckList(tv.getCaro_Type().getText());
        TruckListModel trucklistmodel = new TruckListModel(tlm);
        tv.getTabel_Truck().setModel(trucklistmodel);
    }
    
    public void isiTabelCariNamaTruck() {
        ttm = tdi.getCariNama(av.getCari_Truck_Cat().getText());
        TruckTabelModel trucktabelmodel = new TruckTabelModel(ttm);
        av.getTabel_Truck().setModel(trucktabelmodel);
        int a;
        for (a = 0; a < av.getTabel_Truck().getColumnCount(); a++) {
            av.getTabel_Truck().getColumnModel().getColumn(a).setCellRenderer(new WordWrapCellRenderer());
        }
    }

    public void isiTabelCariNamaTruckTV(TruckView tv) {
        tlm = tdi.getCariNamaTV(tv.getCari_Truck_Cat().getText());
        TruckListModel trucklistmodel = new TruckListModel(tlm);
        tv.getTabel_Truck().setModel(trucklistmodel);
    }

    

    public void setTruckID() {
        tm = tdi.auto_id();
        av.getTruck_Id().setText(tm.getTruck_id());
    }
}
