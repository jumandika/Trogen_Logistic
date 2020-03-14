/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trogen.controller;

import java.awt.Color;
import java.awt.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.*;
import trogen.koneksi.*;
import javax.swing.JOptionPane;
import trogen.model.DriverModel;
import trogen.model.DriverTabelModel;
import trogen.view.AdminView111;
import trogen.service.impl.DriverDao;
import trogen.service.impl.DriverDaoImpl;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import trogen.model.DriverListModel;
import trogen.view.DriverView;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;
import trogen.model.JobOrderModel;

/**
 *
 * @author Asus
 */
public class DriverController {

    DriverModel dm;
    AdminView111 av;
    DriverView dv;
    DriverDaoImpl ddi;
    List<DriverModel> dtm;
    List<DriverModel> dlm;
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

    public DriverController(AdminView111 av) {
        this.av = av;
        ddi = new DriverDao();
//        dtm = ddi.getALL();
        connection = DriverKoneksi.connection();
    }

    public DriverController(DriverView dv) {
        this.dv = dv;
        ddi = new DriverDao();
        connection = DriverKoneksi.connection();
    }
    
    public void resetDriver() {
        av.getDriver_Id().setText("");
        av.getDriver_Name().setText("");
        av.getEducation().setSelectedItem("Select Here");
        av.getButtonGroup1().clearSelection();
        av.getBirth1().setCalendar(null);
        av.getAge1().setText("");
        av.getAddress1().setText("");
        av.getPhone_Number1().setText("");
        av.getNationality().setSelectedItem("Select Here");
        av.getDriving_License().setSelectedItem("Select Here");
        av.getStatus2().setSelectedItem("Select Here");
        av.getUrl_Photo1().setText("");
        av.getPhoto1().setIcon(null);
    }

    public void insertDriver() {
        DriverModel d = new DriverModel();
        d.setDriver_id(av.getDriver_Id().getText());
        d.setDriver_name(av.getDriver_Name().getText());
        d.setEducation(av.getEducation().getSelectedItem().toString());
        if (av.getMale1().isSelected()) {
            d.setGender("Male");
        } else if (av.getFemale1().isSelected()) {
            d.setGender("Female");
        }
        d.setDate_of_birth((Date) av.getBirth1().getDate());
        d.setAge(av.getAge1().getText());
        d.setAddress_driver(av.getAddress1().getText());
        d.setPhone_number_driver(av.getPhone_Number1().getText());
        d.setNationality(av.getNationality().getSelectedItem().toString());
        d.setDriving_license(av.getDriving_License().getSelectedItem().toString());
        File file = new File(av.getUrl_Photo1().getText());
        d.setFile(file);
        try {
            InputStream is = new FileInputStream(file);
            d.setPhoto_upload(is);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DriverController.class.getName()).log(Level.SEVERE, null, ex);
        }

//        d.setPhoto((Blob) av.getPhoto1());
        d.setStatus(av.getStatus2().getSelectedItem().toString());
        d.setAdmin_id(av.getHome_ID().getText());
        ddi.insert(d);
    }

    public void updateDriver() {
        DriverModel d = new DriverModel();
        d.setDriver_id(av.getDriver_Id().getText());
        d.setDriver_name(av.getDriver_Name().getText());
        d.setEducation(av.getEducation().getSelectedItem().toString());
        if (av.getMale1().isSelected()) {
            d.setGender("Male");
        } else if (av.getFemale1().isSelected()) {
            d.setGender("Female");
        }
        d.setDate_of_birth(av.getBirth1().getDate());
        d.setAge(av.getAge1().getText());
        d.setAddress_driver(av.getAddress1().getText());
        d.setPhone_number_driver(av.getPhone_Number1().getText());
        d.setNationality(av.getNationality().getSelectedItem().toString());
        d.setDriving_license(av.getDriving_License().getSelectedItem().toString());
//        d.setPhoto((Blob) av.getPhoto1());
        d.setStatus(av.getStatus2().getSelectedItem().toString());
        d.setAdmin_id(av.getHome_ID().getText());
        ddi.update(d);
    }

    public void updateDriver1() {
        DriverModel d = new DriverModel();
        d.setDriver_id(av.getDriver_Id().getText());
        d.setDriver_name(av.getDriver_Name().getText());
        d.setEducation(av.getEducation().getSelectedItem().toString());
        if (av.getMale1().isSelected()) {
            d.setGender("Male");
        } else if (av.getFemale1().isSelected()) {
            d.setGender("Female");
        }
        d.setDate_of_birth(av.getBirth1().getDate());
        d.setAge(av.getAge1().getText());
        d.setAddress_driver(av.getAddress1().getText());
        d.setPhone_number_driver(av.getPhone_Number1().getText());
        d.setNationality(av.getNationality().getSelectedItem().toString());
        d.setDriving_license(av.getDriving_License().getSelectedItem().toString());
        File file = new File(av.getUrl_Photo1().getText());
        d.setFile(file);
        try {
            InputStream is = new FileInputStream(file);
            d.setPhoto_upload(is);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DriverController.class.getName()).log(Level.SEVERE, null, ex);
        }
//        d.setPhoto((Blob) av.getPhoto1());
        d.setStatus(av.getStatus2().getSelectedItem().toString());
        d.setAdmin_id(av.getHome_ID().getText());
        ddi.update1(d);
    }

    public void set_available(AdminView111 av) {
        JobOrderModel jom = new JobOrderModel();
//        d.setStatus("Available");
        jom.setOrder_number(av.getTabel_JO().getValueAt(av.getTabel_JO().getSelectedRow(), 0).toString());
        ddi.set_available(jom);
        JOptionPane.showMessageDialog(null, "Status Driver " + av.getDriver_Name().getText() + " Available");

    }
    public void deleteDriver() {
        if (!av.getDriver_Id().getText().trim().isEmpty()) {
            String Driver_ID = av.getDriver_Id().getText();
            ddi.delete(Driver_ID);
        } else {
            JOptionPane.showMessageDialog(av, "Pilih data yang akan di hapus");
        }
    }

    public void isiFieldDriver() {
//        av.getDriver_Id().setText(dtm.get(row).getDriver_id());
//        av.getDriver_Name().setText(dtm.get(row).getDriver_name());
//        av.getEducation().setSelectedItem(dtm.get(row).getEducation());
//        if (null != dtm.get(row).getGender()) {
//            switch (dtm.get(row).getGender()) {
//                case "Male":
//                    av.getMale1().setSelected(true);
//                case "Female":
//                    av.getFemale1().setSelected(true);
//            }
//            av.getBirth1().setDate(dtm.get(row).getDate_of_birth());
//            av.getAge1().setText(dtm.get(row).getAge());
//            av.getAddress1().setText(dtm.get(row).getAddress_driver());
//            av.getPhone_Number1().setText(dtm.get(row).getPhone_number_driver());
//            av.getNationality().setSelectedItem(dtm.get(row).getNationality());
//            av.getDriving_License().setSelectedItem(dtm.get(row).getDriving_license());
////            av.getPhoto1().set
//            av.getStatus2().setSelectedItem(dtm.get(row).getStatus());
        dm = ddi.isifielddriver(av.getTabel_Driver().getValueAt(av.getTabel_Driver().getSelectedRow(), 0).toString());
        av.getDriver_Id().setText(dm.getDriver_id());
        av.getDriver_Name().setText(dm.getDriver_name());
        av.getEducation().setSelectedItem(dm.getEducation());
        if (dm.getGender().equals("Male")) {
            av.getMale1().setSelected(true);
        } else {
            av.getFemale1().setSelected(true);
        }
        av.getBirth1().setDate(dm.getDate_of_birth());
        av.getAge1().setText(dm.getAge());
        av.getAddress1().setText(dm.getAddress_driver());
        av.getPhone_Number1().setText(dm.getPhone_number_driver());
        av.getNationality().setSelectedItem(dm.getNationality());
        av.getDriving_License().setSelectedItem(dm.getDriving_license());
        av.getStatus2().setSelectedItem(dm.getStatus());
    }

    public void fieldDriver(boolean t) {
        av.getDriver_Id().setEnabled(t);
        av.getDriver_Name().setEnabled(t);
        av.getEducation().setEnabled(t);
        av.getMale1().setEnabled(t);
        av.getFemale1().setEnabled(t);
        av.getBirth1().setEnabled(t);
        av.getAge1().setEnabled(t);
        av.getAddress1().setEnabled(t);
        av.getPhone_Number1().setEnabled(t);
        av.getNationality().setEnabled(t);
        av.getDriving_License().setEnabled(t);
        av.getStatus2().setEnabled(t);
        av.getUrl_Photo1().setEnabled(t);
    }

    public void setDriver_photo() {
        try {
            dm = ddi.photo_driver(av.getTabel_Driver().getValueAt(av.getTabel_Driver().getSelectedRow(), 0).toString());
            Blob gambar = (Blob) dm.getPhoto();
            if (gambar == null) {
                av.getPhoto1().setIcon(null);
            } else {
                int ukuran = (int) (gambar.length());
                ImageIcon icon = new ImageIcon(gambar.getBytes(1, ukuran));
                av.getPhoto1().setIcon(icon);

            }
        } catch (SQLException ex) {
            Logger.getLogger(DriverController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setDriver_photodv() {
        try {
            dm = ddi.photo_driver(dv.getTabel_Driver().getValueAt(dv.getTabel_Driver().getSelectedRow(), 0).toString());
            Blob gambar = (Blob) dm.getPhoto();
            if (gambar == null) {
                dv.getPhoto1().setIcon(null);
            } else {
                int ukuran = (int) (gambar.length());
                ImageIcon icon = new ImageIcon(gambar.getBytes(1, ukuran));
                dv.getPhoto1().setIcon(icon);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DriverController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setStatus(AdminView111 av) {
        DriverModel d = new DriverModel();
        d.setDriver_id(av.getDriver_ID().getText());
        d.setStatus("Unavailable");
        ddi.set_status(d);
        JOptionPane.showMessageDialog(null, "Status Driver " + av.getDriver_Name().getText() + "Unavailable");
    }

    public void isiTabelDriver() {
        dtm = ddi.getALL();
        DriverTabelModel drivertabelmodel = new DriverTabelModel(dtm);
        av.getTabel_Driver().setModel(drivertabelmodel);
        int a;
        for (a = 0; a < av.getTabel_Driver().getColumnCount(); a++) {
            av.getTabel_Driver().getColumnModel().getColumn(a).setCellRenderer(new WordWrapCellRenderer());
        }
    }

    public void isiTabelDriverList() {
        dlm = ddi.getDriverList();
        DriverListModel driverlistmodel = new DriverListModel(dlm);
        dv.getTabel_Driver().setModel(driverlistmodel);

    }
    
    public void isiTableCariNamaDriver(AdminView111 av) {
        dtm = ddi.getCariNama(av.getSearch_Driver_Name().getText());
        DriverTabelModel drivertabelmodel = new DriverTabelModel(dtm);
        av.getTabel_Driver().setModel(drivertabelmodel);
        int a;
        for (a = 0; a < av.getTabel_Driver().getColumnCount(); a++) {
            av.getTabel_Driver().getColumnModel().getColumn(a).setCellRenderer(new WordWrapCellRenderer());
        }
    }

    public void isiTableCariNamaDriver(DriverView dv) {
        dtm = ddi.getCariNama(dv.getSearch_Driver_Name().getText());
        DriverTabelModel drivertabelmodel = new DriverTabelModel(dtm);
        dv.getTabel_Driver().setModel(drivertabelmodel);
    }
    
    public void setAuto_id() {
        dm = ddi.auto_id();
        av.getDriver_Id().setText(dm.getDriver_id());
    }

    public void setField_DO(JTextField driver_id, JTextField driver_name) {
        driver_id.setText((dv.getTabel_Driver().getValueAt(dv.getTabel_Driver().getSelectedRow(), 0)).toString());
        driver_name.setText((dv.getTabel_Driver().getValueAt(dv.getTabel_Driver().getSelectedRow(), 1)).toString());
    }
}
