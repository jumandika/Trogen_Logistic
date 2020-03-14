/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trogen.controller;

import java.awt.Color;
import java.awt.Component;
import java.sql.*;
import trogen.koneksi.*;
import javax.swing.JOptionPane;
import java.util.Date;
import trogen.model.AdminModel;
import trogen.model.AdminTabelModel;
import trogen.view.AdminView111;
import trogen.service.impl.AdminDao;
import trogen.service.impl.AdminDaoImpl;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;
import trogen.view.Login;

/**
 *
 * @author Asus
 */
public class AdminController {

    AdminModel am;
    AdminView111 av;
    Login lv;
    AdminDaoImpl adi;
    List<AdminModel> atm;
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

    public AdminController(AdminView111 av) {
        this.av = av;
        adi = new AdminDao();
        connection = AdminKoneksi.connection();
    }

    public AdminController(Login lv) {
        this.lv = lv;
        adi = new AdminDao();
        connection = AdminKoneksi.connection();
    }

    public void resetAdmin() {
        av.getAdmin_ID1().setText("");
        av.getAdmin_Name1().setText("");
        av.getUsername1().setText("");
        av.getPassword().setText("");
        av.getButtonGroup1().clearSelection();
        av.getBirth().setCalendar(null);
        av.getAge().setText("");
        av.getAddress().setText("");
        av.getPhone_Number().setText("");
        av.getPhoto().setIcon(null);
        av.getUrl_Photo().setText("");
    }

//    public java.sql.Date convertJavaDateToSqlDate(java.util.Date date) {
//        return new java.sql.Date(date.getTime());
//    }
    public void insertAdmin() {
        AdminModel a = new AdminModel();
        a.setAdmin_id(av.getAdmin_ID1().getText());
        a.setAdmin_name(av.getAdmin_Name1().getText());
        a.setUsername(av.getUsername1().getText());
        a.setPassword(av.getPassword().getText());
        if (av.getMale().isSelected()) {
            a.setGender("Male");
        } else if (av.getFemale().isSelected()) {
            a.setGender("Female");
        }
        a.setDate_of_birth((Date) av.getBirth().getDate());
        a.setAge(av.getAge().getText());
        a.setAddress(av.getAddress().getText());
        a.setPhone_number(av.getPhone_Number().getText());
        File file = new File(av.getUrl_Photo().getText());
        a.setFile(file);
        try {
            InputStream is = new FileInputStream(file);
            a.setPhoto_upload(is);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }

//        if(av.getUrl_Photo().getText().equals("")){
//            ObjectOutputStream objectOutputStream=null;
//            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//            try {
//                objectOutputStream = new ObjectOutputStream(outputStream);
//                ImageIcon icon = new ImageIcon(ImageIO.read(file));
//                objectOutputStream.writeObject(icon);
//                objectOutputStream.flush();
//                objectOutputStream.close();
//            } catch (IOException ex) {
//                Logger.getLogger(FrameKaryawan.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            karyawan.setFoto(outputStream.toByteArray());
//        }
        adi.insert(a);
    }

    public void updateAdmin() {
        AdminModel a = new AdminModel();
        a.setAdmin_id(av.getAdmin_ID1().getText());
        a.setAdmin_name(av.getAdmin_Name1().getText());
        a.setUsername(av.getUsername1().getText());
        a.setPassword(av.getPassword().getText());
        if (av.getMale().isSelected()) {
            a.setGender("Male");
        } else if (av.getFemale().isSelected()) {
            a.setGender("Female");
        }
        a.setDate_of_birth((Date) av.getBirth().getDate());
        a.setAge(av.getAge().getText());
        a.setAddress(av.getAddress().getText());
        a.setPhone_number(av.getPhone_Number().getText());
        adi.update(a);
    }

    public void updateAdmin1() {
        AdminModel a = new AdminModel();
        a.setAdmin_id(av.getAdmin_ID1().getText());
        a.setAdmin_name(av.getAdmin_Name1().getText());
        a.setUsername(av.getUsername1().getText());
        a.setPassword(av.getPassword().getText());
        if (av.getMale().isSelected()) {
            a.setGender("Male");
        } else if (av.getFemale().isSelected()) {
            a.setGender("Female");
        }
        a.setDate_of_birth((Date) av.getBirth().getDate());
        a.setAge(av.getAge().getText());
        a.setAddress(av.getAddress().getText());
        a.setPhone_number(av.getPhone_Number().getText());
        File file = new File(av.getUrl_Photo().getText());
        a.setFile(file);
        try {
            InputStream is = new FileInputStream(file);
            a.setPhoto_upload(is);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        adi.update1(a);
    }

    public void deleteAdmin() {
        if (!av.getAdmin_ID1().getText().trim().isEmpty()) {
            String Admin_ID = av.getAdmin_ID1().getText();
            adi.delete(Admin_ID);
        } else {
            JOptionPane.showMessageDialog(av, "Pilih data yang akan di hapus");
        }
    }

    public void cekusername(Login lv) {
        am = adi.Username(lv.getUsername().getText());
        if (am.getUsername() == null) {
            JOptionPane.showMessageDialog(lv, "Username Unregistered");
        } else if (am.getUsername().equals(lv.getUsername().getText())) {
            lv.getPassword().requestFocus();
            lv.getPassword().setText("");
        }
    }
    
    public void cekpassword(Login lv) {
        am = adi.Password(lv.getPassword().getText());
        if (am.getPassword() == null) {
            JOptionPane.showMessageDialog(lv, "Password Wrong, Try Again!");
            lv.getPassword().setText("");
        } else if (am.getPassword().equals(lv.getPassword().getText())) {
            lv.getLogin().doClick();
        }
    }

    public void isiFieldAdmin() {
//        av.getAdmin_ID1().setText(atm.get(row).getAdmin_id());
//        av.getAdmin_Name1().setText(atm.get(row).getAdmin_name());
//        av.getUsername1().setText(atm.get(row).getUsername());
//        av.getPassword().setText(atm.get(row).getPassword());
//        if (atm.get(row).getGender().equals("Male")) {
//            av.getMale().setSelected(true);
//        } else {
//            av.getFemale().setSelected(true);
//        }
//        av.getBirth().setDate(atm.get(row).getDate_of_birth());
//        av.getAge().setText(atm.get(row).getAge());
//        av.getAddress().setText(atm.get(row).getAddress());
//        av.getPhone_Number().setText(atm.get(row).getPhone_number());
        am = adi.isifieldadmin(av.getTabel_Admin().getValueAt(av.getTabel_Admin().getSelectedRow(), 0).toString());
        av.getAdmin_ID1().setText(am.getAdmin_id());
        av.getAdmin_Name1().setText(am.getAdmin_name());
        av.getUsername1().setText(am.getUsername());
        av.getPassword().setText(am.getPassword());
        if (am.getGender().equals("Male")) {
            av.getMale().setSelected(true);
        } else {
            av.getFemale().setSelected(true);
        }
        av.getBirth().setDate(am.getDate_of_birth());
        av.getAge().setText(am.getAge());
        av.getAddress().setText(am.getAddress());
        av.getPhone_Number().setText(am.getPhone_number());
    }

    public void setAdmin_photo() {
        try {
            am = adi.photo_admin(av.getTabel_Admin().getValueAt(av.getTabel_Admin().getSelectedRow(), 0).toString());
            Blob gambar = (Blob) am.getPhoto();
            if (gambar == null) {
                av.getPhoto().setIcon(null);
            } else {
                int ukuran = (int) (gambar.length());
                ImageIcon icon = new ImageIcon(gambar.getBytes(1, ukuran));
                av.getPhoto().setIcon(icon);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void setHome_photo() {
        try {
            am = adi.home_photo(lv.getUsername().getText());
            Blob gambar = (Blob) am.getPhoto();
            int ukuran = (int) (gambar.length());
            ImageIcon icon = new ImageIcon(gambar.getBytes(1, ukuran));
            av.getAdminGlass().setBackgroundImage(icon);
        } catch (SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setAdmin_info() {
        am = adi.admin_info(lv.getUsername().getText());
        av.getHome_Adm().setText(am.getAdmin_name());
        av.getHome_ID().setText(am.getAdmin_id());
        AdminView111.getHome_Level().setText("Administrator");
    }

    public void setAuto_id() {
        am = adi.auto_id();
        av.getAdmin_ID1().setText(am.getAdmin_id());
    }

    public void isiTabelAdmin() {
        atm = adi.getALL();
        AdminTabelModel admintabelmodel = new AdminTabelModel(atm);
        av.getTabel_Admin().setModel(admintabelmodel);
        int a;
        for (a = 0; a < av.getTabel_Admin().getColumnCount(); a++) {
            av.getTabel_Admin().getColumnModel().getColumn(a).setCellRenderer(new WordWrapCellRenderer());
        }
    }

    public void isiTableCariNamaAdmin() {
        atm = adi.getCariNama(av.getSearch_Admin_Name().getText());
        AdminTabelModel admintabelmodel = new AdminTabelModel(atm);
        av.getTabel_Admin().setModel(admintabelmodel);
        int a;
        for (a = 0; a < av.getTabel_Admin().getColumnCount(); a++) {
            av.getTabel_Admin().getColumnModel().getColumn(a).setCellRenderer(new WordWrapCellRenderer());
        }
    }

    public void fieldAdmin(boolean t) {
        av.getAdmin_ID1().setEnabled(t);
        av.getAdmin_Name1().setEnabled(t);
        av.getUsername1().setEnabled(t);
        av.getPassword().setEnabled(t);
        av.getMale().setEnabled(t);
        av.getFemale().setEnabled(t);
        av.getBirth().setEnabled(t);
        av.getAge().setEnabled(t);
        av.getAddress().setEnabled(t);
        av.getPhone_Number().setEnabled(t);
        av.getUrl_Photo().setEnabled(t);
        av.getUpload().setEnabled(t);
    }

}

//String tgl_daftar = view.gettxtTgl_daftar().toString();
//        if (av.getAdmin_ID1().equals("")) {
//            JOptionPane.showMessageDialog(av, "ID Anggota Belum Terisi!!!");
//        } else if (av.getAdmin_ID1() > 6) {
//            JOptionPane.showMessageDialog(view, "ID Anggota tidak boleh lebih dari 6 karakter");
//        } else if (nama_anggota.trim().equals("")) {
//            JOptionPane.showMessageDialog(view, "Nama Anggota Belum Terisi!!!");
//        } else if (nama_anggota.length() > 35) {
//            JOptionPane.showMessageDialog(view, "Nama Anggota tidak boleh lebih dari 35 karakter");
//        } else if (alamat.trim().equals("")) {
//            JOptionPane.showMessageDialog(view, "Alamat Anggota Belum Terisi!!!");
//        } else if (alamat.length() > 75) {
//            JOptionPane.showMessageDialog(view, "Alamat Anggota tidak boleh lebih dari 75 karakter");
//        } else if (no_telp.length() > 18) {
//            JOptionPane.showMessageDialog(view, "Nomor no_telp tidak boleh lebih dari 18 digit");
//        } else if (!email.contains("@") || !email.contains(".")) {
//            JOptionPane.showMessageDialog(view, "Email Tidak Valid!!!");
//        } else if (email.length() > 25) {
//            JOptionPane.showMessageDialog(view, "Email tidak boleh lebih dari 25 karakter");
//        } else {
//            model.setId_anggota(id_anggota);
//            model.setTgl_daftar(tgl_daftar);
//            model.setNama_anggota(nama_anggota);
//            model.setAlamat(alamat);
//            model.setEmail(email);
//            model.setNo_telp(no_telp);
//            try {
//                model.insertAnggota();
//                JOptionPane.showMessageDialog(view, "Data Anggota Berhasil Tersimpan");
//                model.resetAnggota();
//            } catch (Throwable throwable) {
//                JOptionPane.showMessageDialog(view, new Object[]{
//                    "Terjadi Kesalahan Program !!!",
//                    throwable.getMessage()});
//            }
//        }
//    public void updateAdmin(AdminView111 view) {
//        if (view.gettblAnggota().getSelectedRowCount() == 0) {
//            JOptionPane.showMessageDialog(view, "Pilih Record Yang Akan Diubah");
//        } else {
//            String id_anggota = view.gettxtId_anggota().getText();
//            String nama_anggota = view.gettxtNama_anggota().getText();
//            String alamat = view.gettxtAlamat().getText();
//            String no_telp = view.gettxtNo_telp().getText();
//            String email = view.gettxtEmail().getText();
//            if (id_anggota.trim().equals("")) {
//                JOptionPane.showMessageDialog(view, "ID Anggota Belum Terisi!!!");
//            } else if (id_anggota.length() > 6) {
//                JOptionPane.showMessageDialog(view, "ID Anggota tidak boleh lebih dari 6 karakter");
//            } else if (nama_anggota.trim().equals("")) {
//                JOptionPane.showMessageDialog(view, "Nama Anggota Belum Terisi!!!");
//            } else if (nama_anggota.length() > 35) {
//                JOptionPane.showMessageDialog(view, "Nama Anggota tidak boleh lebih dari 35 karakter");
//            } else if (alamat.trim().equals("")) {
//                JOptionPane.showMessageDialog(view, "Alamat Anggota Belum Terisi!!!");
//            } else if (alamat.length() > 75) {
//                JOptionPane.showMessageDialog(view, "Alamat Anggota tidak boleh lebih dari 75 karakter");
//            } else if (no_telp.length() > 18) {
//                JOptionPane.showMessageDialog(view, "Nomor no_telp tidak boleh lebih dari 18 digit");
//            } else if (!email.contains("@") || !email.contains(".")) {
//                JOptionPane.showMessageDialog(view, "Email Tidak Valid!!!");
//            } else if (email.length() > 25) {
//                JOptionPane.showMessageDialog(view, "Email tidak boleh lebih dari 25 karakter");
//            } else {
//                model.setId_anggota(id_anggota);
//                model.setNama_anggota(nama_anggota);
//                model.setAlamat(alamat);
//                model.setEmail(email);
//                model.setNo_telp(no_telp);
//                try {
//                    model.updateAnggota();
//                    JOptionPane.showMessageDialog(view, "Data Anggota Berhasil Terupdate");
//                    model.resetAnggota();
//                } catch (SQLException | AnggotaException | HeadlessException throwable) {
//                    JOptionPane.showMessageDialog(view, new Object[]{
//                        "Terjadi Kesalahan Program !!!",
//                        throwable.getMessage()});
//                }
//            }
//        }
//    }
//
//    public void deleteAdmin(AdminView111 view) {
//        if (view.gettblAnggota().getSelectedRowCount() == 0) {
//            JOptionPane.showMessageDialog(view, "Pilih Record Yang Akan Dihapus");
//        } else {
//            if (JOptionPane.showConfirmDialog(view, "Anda yakin ingin menghapus data Anggota ini?") == JOptionPane.OK_OPTION) {
//                String id_anggota = view.gettxtId_anggota().getText();
//                model.setId_anggota(id_anggota);
//                try {
//                    model.deleteAnggota();
//                    JOptionPane.showMessageDialog(view, "Data Anggota Berhasil Terhapus!!!");
//                    model.resetAnggota();
//                } catch (Throwable throwable) {
//                    JOptionPane.showMessageDialog(view, new Object[]{
//                        "Terjadi Kesalahan Program !!!",
//                        throwable.getMessage()});
//                }
//            }
//        }
//    }

