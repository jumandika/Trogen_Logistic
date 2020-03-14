/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trogen.controller;

import java.awt.Color;
import java.awt.Component;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;
import trogen.koneksi.JobOrderKoneksi;
import trogen.model.JOListInvoiceModel;
import trogen.model.JOListModel;
import trogen.model.JOTabelModel;
import trogen.model.JobOrderModel;
import trogen.service.impl.JobOrderDao;
import trogen.service.impl.JobOrderDaoImpl;
import trogen.view.AdminView111;
import trogen.view.TruckView;
import trogen.view.DeliveryOrderPreview;
import trogen.view.EditJobOrderView;
import trogen.view.InputJobOrderView;
import trogen.view.JobOrderView;
import trogen.view.TruckView;

/**
 *
 * @author Asus
 */
public class JobOrderController {

    JobOrderModel jom;
    AdminView111 av;
    TruckView tv;
    InputJobOrderView ijov;
    EditJobOrderView ejov;
    DeliveryOrderPreview dop;
    JobOrderView jov;
    JobOrderDaoImpl joi;
    List<JobOrderModel> jotm;
    List<JobOrderModel> jolm;
    List<JobOrderModel> jlim;
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

    public JobOrderController(AdminView111 av) {
        this.av = av;
        joi = new JobOrderDao();
        connection = JobOrderKoneksi.connection();
    }

    public JobOrderController(InputJobOrderView ijov) {
        this.ijov = ijov;
        connection = JobOrderKoneksi.connection();
        joi = new JobOrderDao();
    }

    public JobOrderController(EditJobOrderView ejov) {
        this.ejov = ejov;
        connection = JobOrderKoneksi.connection();
        joi = new JobOrderDao();
    }

    public JobOrderController(JobOrderView jov) {
        this.jov = jov;
        connection = JobOrderKoneksi.connection();
        joi = new JobOrderDao();
    }

    public JobOrderController(TruckView tv, AdminView111 av) {
        this.tv = tv;
        this.av = av;
        connection = JobOrderKoneksi.connection();
        joi = new JobOrderDao();
    }

    public JobOrderController(DeliveryOrderPreview dop, AdminView111 av) {
        this.dop = dop;
        this.av = av;
        connection = JobOrderKoneksi.connection();
        joi = new JobOrderDao();
    }

    public void updateStatusJobOrder(AdminView111 av) {
        JobOrderModel j = new JobOrderModel();
        j.setStatus("On Progress");
        joi.update(j);
    }

    public void resetInputJobOrder() {
//        ijov.getCust_ID().setText("");
//        ijov.getSender_Name().setText("");
//        ijov.getSender_Comp_Name().setText("");
//        ijov.getSender_Comp_Address().setText("");
//        ijov.getSender_Phone().setText("");
        ijov.getSearch_Cust_ID().setText("");
        ijov.getSender_Name1().setText("");
        ijov.getSender_Comp_Name1().setText("");
        ijov.getSender_Comp_Address1().setText("");
        ijov.getSender_Phone1().setText("");
        ijov.getGoods_Cat().setSelectedIndex(0);
        ijov.getP().setText("");
        ijov.getL().setText("");
        ijov.getT().setText("");
        ijov.getWeight().setText("");
        ijov.getDescription().setText("");
        ijov.getReceiver_Name().setText("");
        ijov.getReceiver_Comp_Name().setText("");
        ijov.getReceiver_Comp_Address().setText("");
        ijov.getReceiver_Phone().setText("");
        ijov.getTruck_Cat().setSelectedIndex(0);
        ijov.getCaro_Size().setText("");
        ijov.getMax_Capacity().setText("");
        ijov.getDelivery_Date().setCalendar(null);
    }

    public void distance() {
        if (av.getPickCity().getSelectedItem().equals("Bandung")
                && av.getShipCity().getSelectedItem().equals("Sukabumi")
                || av.getPickCity().getSelectedItem().equals("Bandung")) {
            av.getCheckDistance().setText("96");
        }
        if (av.getPickCity().getSelectedItem().equals("Bandung")
                && av.getShipCity().getSelectedItem().equals("Tasikmalaya")
                || av.getPickCity().getSelectedItem().equals("Tasikmalaya")
                && av.getShipCity().getSelectedItem().equals("Bandung")) {
            av.getCheckDistance().setText("106");
        }
        if (av.getPickCity().getSelectedItem().equals("Bandung")
                && av.getShipCity().getSelectedItem().equals("Bogor")
                || av.getPickCity().getSelectedItem().equals("Bogor")
                && av.getShipCity().getSelectedItem().equals("Bandung")) {
            av.getCheckDistance().setText("126");
        }
        if (av.getPickCity().getSelectedItem().equals("Bandung")
                && av.getShipCity().getSelectedItem().equals("Cirebon")
                || av.getPickCity().getSelectedItem().equals("Cirebon")
                && av.getShipCity().getSelectedItem().equals("Bandung")) {
            av.getCheckDistance().setText("130");
        }
        if (av.getPickCity().getSelectedItem().equals("Bandung")
                && av.getShipCity().getSelectedItem().equals("Jakarta")
                || av.getPickCity().getSelectedItem().equals("Jakarta")
                && av.getShipCity().getSelectedItem().equals("Bandung")) {
            av.getCheckDistance().setText("180");
        }
        if (av.getPickCity().getSelectedItem().equals("Bandung")
                && av.getShipCity().getSelectedItem().equals("Tegal")
                || av.getPickCity().getSelectedItem().equals("Tegal")
                && av.getShipCity().getSelectedItem().equals("Bandung")) {
            av.getCheckDistance().setText("202");
        }
        if (av.getPickCity().getSelectedItem().equals("Bandung")
                && av.getShipCity().getSelectedItem().equals("Serang")
                || av.getPickCity().getSelectedItem().equals("Serang")
                && av.getShipCity().getSelectedItem().equals("Bandung")) {
            av.getCheckDistance().setText("258");
        }
        if (av.getPickCity().getSelectedItem().equals("Bandung")
                && av.getShipCity().getSelectedItem().equals("Cilacap")
                || av.getPickCity().getSelectedItem().equals("Cilacap")
                && av.getShipCity().getSelectedItem().equals("Bandung")) {
            av.getCheckDistance().setText("259");
        }
        if (av.getPickCity().getSelectedItem().equals("Bandung")
                && av.getShipCity().getSelectedItem().equals("Pekalongan")
                || av.getPickCity().getSelectedItem().equals("Pekalongan")
                && av.getShipCity().getSelectedItem().equals("Bandung")) {
            av.getCheckDistance().setText("266");
        }
        if (av.getPickCity().getSelectedItem().equals("Bandung")
                && av.getShipCity().getSelectedItem().equals("Wonosobo")
                || av.getPickCity().getSelectedItem().equals("Wonosobo")
                && av.getShipCity().getSelectedItem().equals("Bandung")) {
            av.getCheckDistance().setText("339");
        }
        if (av.getPickCity().getSelectedItem().equals("Bandung")
                && av.getShipCity().getSelectedItem().equals("Purworejo")
                || av.getPickCity().getSelectedItem().equals("Purworejo")
                && av.getShipCity().getSelectedItem().equals("Bandung")) {
            av.getCheckDistance().setText("362");
        }
        if (av.getPickCity().getSelectedItem().equals("Bandung")
                && av.getShipCity().getSelectedItem().equals("Semarang")
                || av.getPickCity().getSelectedItem().equals("Semarang")
                && av.getShipCity().getSelectedItem().equals("Bandung")) {
            av.getCheckDistance().setText("367");
        }
        if (av.getPickCity().getSelectedItem().equals("Bandung")
                && av.getShipCity().getSelectedItem().equals("Magelang")
                || av.getPickCity().getSelectedItem().equals("Magelang")
                && av.getShipCity().getSelectedItem().equals("Bandung")) {
            av.getCheckDistance().setText("403");
        }
        if (av.getPickCity().getSelectedItem().equals("Bandung")
                && av.getShipCity().getSelectedItem().equals("Yogyakarta")
                || av.getPickCity().getSelectedItem().equals("Yogyakarta")
                && av.getShipCity().getSelectedItem().equals("Bandung")) {
            av.getCheckDistance().setText("428");
        }
        if (av.getPickCity().getSelectedItem().equals("Bandung")
                && av.getShipCity().getSelectedItem().equals("Solo")
                || av.getPickCity().getSelectedItem().equals("Solo")
                && av.getShipCity().getSelectedItem().equals("Bandung")) {
            av.getCheckDistance().setText("467");
        }
        if (av.getPickCity().getSelectedItem().equals("Bandung")
                && av.getShipCity().getSelectedItem().equals("Rembang")
                || av.getPickCity().getSelectedItem().equals("Rembang")
                && av.getShipCity().getSelectedItem().equals("Bandung")) {
            av.getCheckDistance().setText("476");
        }
        if (av.getPickCity().getSelectedItem().equals("Bandung")
                && av.getShipCity().getSelectedItem().equals("Madiun")
                || av.getPickCity().getSelectedItem().equals("Madiun")
                && av.getShipCity().getSelectedItem().equals("Bandung")) {
            av.getCheckDistance().setText("581");
        }
        if (av.getPickCity().getSelectedItem().equals("Bandung")
                && av.getShipCity().getSelectedItem().equals("Kediri")
                || av.getPickCity().getSelectedItem().equals("Kediri")
                && av.getShipCity().getSelectedItem().equals("Bandung")) {
            av.getCheckDistance().setText("662");
        }
        if (av.getPickCity().getSelectedItem().equals("Bandung")
                && av.getShipCity().getSelectedItem().equals("Surabaya")
                || av.getPickCity().getSelectedItem().equals("Surabaya")
                && av.getShipCity().getSelectedItem().equals("Bandung")) {
            av.getCheckDistance().setText("675");
        }
        if (av.getPickCity().getSelectedItem().equals("Bandung")
                && av.getShipCity().getSelectedItem().equals("Malang")
                || av.getPickCity().getSelectedItem().equals("Malang")
                && av.getShipCity().getSelectedItem().equals("Bandung")) {
            av.getCheckDistance().setText("764");
        }
        if (av.getPickCity().getSelectedItem().equals("Bandung")
                && av.getShipCity().getSelectedItem().equals("Probolinggo")
                || av.getPickCity().getSelectedItem().equals("Probolinggo")
                && av.getShipCity().getSelectedItem().equals("Bandung")) {
            av.getCheckDistance().setText("774");
        }
        if (av.getPickCity().getSelectedItem().equals("Bandung")
                && av.getShipCity().getSelectedItem().equals("Bondowoso")
                || av.getPickCity().getSelectedItem().equals("Bondowoso")
                && av.getShipCity().getSelectedItem().equals("Bandung")) {
            av.getCheckDistance().setText("869");
        }
        if (av.getPickCity().getSelectedItem().equals("Bandung")
                && av.getShipCity().getSelectedItem().equals("Jember")
                || av.getPickCity().getSelectedItem().equals("Jember")
                && av.getShipCity().getSelectedItem().equals("Bandung")) {
            av.getCheckDistance().setText("873");
        }
        if (av.getPickCity().getSelectedItem().equals("Bandung")
                && av.getShipCity().getSelectedItem().equals("Banyuwangi")
                || av.getPickCity().getSelectedItem().equals("Banyuwangi")
                && av.getShipCity().getSelectedItem().equals("Bandung")) {
            av.getCheckDistance().setText("964");
        }
        if (av.getPickCity().getSelectedItem().equals("Bandung")
                && av.getShipCity().getSelectedItem().equals("Merak")
                || av.getPickCity().getSelectedItem().equals("Merak")
                && av.getShipCity().getSelectedItem().equals("Bandung")) {
            av.getCheckDistance().setText("261");
        }
        if (av.getPickCity().getSelectedItem().equals("Sukabumi")
                && av.getShipCity().getSelectedItem().equals("Tasikmalaya")
                || av.getPickCity().getSelectedItem().equals("Tasikmalaya")
                && av.getShipCity().getSelectedItem().equals("Sukabumi")) {
            av.getCheckDistance().setText("202");
        }
        if (av.getPickCity().getSelectedItem().equals("Sukabumi")
                && av.getShipCity().getSelectedItem().equals("Bogor")
                || av.getPickCity().getSelectedItem().equals("Bogor")
                && av.getShipCity().getSelectedItem().equals("Sukabumi")) {
            av.getCheckDistance().setText("61");
        }
        if (av.getPickCity().getSelectedItem().equals("Sukabumi")
                && av.getShipCity().getSelectedItem().equals("Cirebon")
                || av.getPickCity().getSelectedItem().equals("Cirebon")
                && av.getShipCity().getSelectedItem().equals("Sukabumi")) {
            av.getCheckDistance().setText("226");
        }
        if (av.getPickCity().getSelectedItem().equals("Sukabumi")
                && av.getShipCity().getSelectedItem().equals("Jakarta")
                || av.getPickCity().getSelectedItem().equals("Jakarta")
                && av.getShipCity().getSelectedItem().equals("Sukabumi")) {
            av.getCheckDistance().setText("115");
        }
        if (av.getPickCity().getSelectedItem().equals("Sukabumi")
                && av.getShipCity().getSelectedItem().equals("Tegal")
                || av.getPickCity().getSelectedItem().equals("Tegal")
                && av.getShipCity().getSelectedItem().equals("Sukabumi")) {
            av.getCheckDistance().setText("298");
        }
        if (av.getPickCity().getSelectedItem().equals("Sukabumi")
                && av.getShipCity().getSelectedItem().equals("Serang")
                || av.getPickCity().getSelectedItem().equals("Serang")
                && av.getShipCity().getSelectedItem().equals("Sukabumi")) {
            av.getCheckDistance().setText("203");
        }
        if (av.getPickCity().getSelectedItem().equals("Sukabumi")
                && av.getShipCity().getSelectedItem().equals("Cilacap")
                || av.getPickCity().getSelectedItem().equals("Cilacap")
                && av.getShipCity().getSelectedItem().equals("Sukabumi")) {
            av.getCheckDistance().setText("355");
        }
        if (av.getPickCity().getSelectedItem().equals("Sukabumi")
                && av.getShipCity().getSelectedItem().equals("Pekalongan")
                || av.getPickCity().getSelectedItem().equals("Pekalongan")
                && av.getShipCity().getSelectedItem().equals("Sukabumi")) {
            av.getCheckDistance().setText("362");
        }
        if (av.getPickCity().getSelectedItem().equals("Sukabumi")
                && av.getShipCity().getSelectedItem().equals("Wonosobo")
                || av.getPickCity().getSelectedItem().equals("Wonosobo")
                && av.getShipCity().getSelectedItem().equals("Sukabumi")) {
            av.getCheckDistance().setText("435");
        }
        if (av.getPickCity().getSelectedItem().equals("Sukabumi")
                && av.getShipCity().getSelectedItem().equals("Purworejo")
                || av.getPickCity().getSelectedItem().equals("Purworejo")
                && av.getShipCity().getSelectedItem().equals("Sukabumi")) {
            av.getCheckDistance().setText("458");
        }
        if (av.getPickCity().getSelectedItem().equals("Sukabumi")
                && av.getShipCity().getSelectedItem().equals("Semarang")
                || av.getPickCity().getSelectedItem().equals("Semarang")
                && av.getShipCity().getSelectedItem().equals("Sukabumi")) {
            av.getCheckDistance().setText("463");
        }
        if (av.getPickCity().getSelectedItem().equals("Sukabumi")
                && av.getShipCity().getSelectedItem().equals("Magelang")
                || av.getPickCity().getSelectedItem().equals("Magelang")
                && av.getShipCity().getSelectedItem().equals("Sukabumi")) {
            av.getCheckDistance().setText("500");
        }
        if (av.getPickCity().getSelectedItem().equals("Sukabumi")
                && av.getShipCity().getSelectedItem().equals("Yogyakarta")
                || av.getPickCity().getSelectedItem().equals("Yogyakarta")
                && av.getShipCity().getSelectedItem().equals("Sukabumi")) {
            av.getCheckDistance().setText("524");
        }
        if (av.getPickCity().getSelectedItem().equals("Sukabumi")
                && av.getShipCity().getSelectedItem().equals("Solo")
                || av.getPickCity().getSelectedItem().equals("Solo")
                && av.getShipCity().getSelectedItem().equals("Sukabumi")) {
            av.getCheckDistance().setText("563");
        }
        if (av.getPickCity().getSelectedItem().equals("Sukabumi")
                && av.getShipCity().getSelectedItem().equals("Rembang")
                || av.getPickCity().getSelectedItem().equals("Rembang")
                && av.getShipCity().getSelectedItem().equals("Sukabumi")) {
            av.getCheckDistance().setText("572");
        }
        if (av.getPickCity().getSelectedItem().equals("Sukabumi")
                && av.getShipCity().getSelectedItem().equals("Madiun")
                || av.getPickCity().getSelectedItem().equals("Madiun")
                && av.getShipCity().getSelectedItem().equals("Sukabumi")) {
            av.getCheckDistance().setText("677");
        }
        if (av.getPickCity().getSelectedItem().equals("Sukabumi")
                && av.getShipCity().getSelectedItem().equals("Kediri")
                || av.getPickCity().getSelectedItem().equals("Kediri")
                && av.getShipCity().getSelectedItem().equals("Sukabumi")) {
            av.getCheckDistance().setText("758");
        }
        if (av.getPickCity().getSelectedItem().equals("Sukabumi")
                && av.getShipCity().getSelectedItem().equals("Surabaya")
                || av.getPickCity().getSelectedItem().equals("Surabaya")
                && av.getShipCity().getSelectedItem().equals("Sukabumi")) {
            av.getCheckDistance().setText("771");
        }
        if (av.getPickCity().getSelectedItem().equals("Sukabumi")
                && av.getShipCity().getSelectedItem().equals("Malang")
                || av.getPickCity().getSelectedItem().equals("Malang")
                && av.getShipCity().getSelectedItem().equals("Sukabumi")) {
            av.getCheckDistance().setText("860");
        }
        if (av.getPickCity().getSelectedItem().equals("Sukabumi")
                && av.getShipCity().getSelectedItem().equals("Probolinggo")
                || av.getPickCity().getSelectedItem().equals("Probolinggo")
                && av.getShipCity().getSelectedItem().equals("Sukabumi")) {
            av.getCheckDistance().setText("870");
        }
        if (av.getPickCity().getSelectedItem().equals("Sukabumi")
                && av.getShipCity().getSelectedItem().equals("Bondowoso")
                || av.getPickCity().getSelectedItem().equals("Bondowoso")
                && av.getShipCity().getSelectedItem().equals("Sukabumi")) {
            av.getCheckDistance().setText("965");
        }
        if (av.getPickCity().getSelectedItem().equals("Sukabumi")
                && av.getShipCity().getSelectedItem().equals("Jember")
                || av.getPickCity().getSelectedItem().equals("Jember")
                && av.getShipCity().getSelectedItem().equals("Sukabumi")) {
            av.getCheckDistance().setText("969");
        }
        if (av.getPickCity().getSelectedItem().equals("Sukabumi")
                && av.getShipCity().getSelectedItem().equals("Banyuwangi")
                || av.getPickCity().getSelectedItem().equals("Banyuwangi")
                && av.getShipCity().getSelectedItem().equals("Sukabumi")) {
            av.getCheckDistance().setText("1060");
        }
        if (av.getPickCity().getSelectedItem().equals("Sukabumi")
                && av.getShipCity().getSelectedItem().equals("Merak")
                || av.getPickCity().getSelectedItem().equals("Merak")
                && av.getShipCity().getSelectedItem().equals("Sukabumi")) {
            av.getCheckDistance().setText("222");
        }
        if (av.getPickCity().getSelectedItem().equals("Tasikmalaya")
                && av.getShipCity().getSelectedItem().equals("Bogor")
                || av.getPickCity().getSelectedItem().equals("Bogor")
                && av.getShipCity().getSelectedItem().equals("Tasikmalaya")) {
            av.getCheckDistance().setText("232");
        }
        if (av.getPickCity().getSelectedItem().equals("Tasikmalaya")
                && av.getShipCity().getSelectedItem().equals("Cirebon")
                || av.getPickCity().getSelectedItem().equals("Cirebon")
                && av.getShipCity().getSelectedItem().equals("Tasikmalaya")) {
            av.getCheckDistance().setText("121");
        }
        if (av.getPickCity().getSelectedItem().equals("Tasikmalaya")
                && av.getShipCity().getSelectedItem().equals("Jakarta")
                || av.getPickCity().getSelectedItem().equals("Jakarta")
                && av.getShipCity().getSelectedItem().equals("Tasikmalaya")) {
            av.getCheckDistance().setText("275");
        }
        if (av.getPickCity().getSelectedItem().equals("Tasikmalaya")
                && av.getShipCity().getSelectedItem().equals("Tegal")
                || av.getPickCity().getSelectedItem().equals("Tegal")
                && av.getShipCity().getSelectedItem().equals("Tasikmalaya")) {
            av.getCheckDistance().setText("195");
        }
        if (av.getPickCity().getSelectedItem().equals("Tasikmalaya")
                && av.getShipCity().getSelectedItem().equals("Serang")
                || av.getPickCity().getSelectedItem().equals("Serang")
                && av.getShipCity().getSelectedItem().equals("Tasikmalaya")) {
            av.getCheckDistance().setText("364");
        }
        if (av.getPickCity().getSelectedItem().equals("Tasikmalaya")
                && av.getShipCity().getSelectedItem().equals("Cilacap")
                || av.getPickCity().getSelectedItem().equals("Cilacap")
                && av.getShipCity().getSelectedItem().equals("Tasikmalaya")) {
            av.getCheckDistance().setText("153");
        }
        if (av.getPickCity().getSelectedItem().equals("Tasikmalaya")
                && av.getShipCity().getSelectedItem().equals("Pekalongan")
                || av.getPickCity().getSelectedItem().equals("Pekalongan")
                && av.getShipCity().getSelectedItem().equals("Tasikmalaya")) {
            av.getCheckDistance().setText("257");
        }
        if (av.getPickCity().getSelectedItem().equals("Tasikmalaya")
                && av.getShipCity().getSelectedItem().equals("Wonosobo")
                || av.getPickCity().getSelectedItem().equals("Wonosobo")
                && av.getShipCity().getSelectedItem().equals("Tasikmalaya")) {
            av.getCheckDistance().setText("233");
        }
        if (av.getPickCity().getSelectedItem().equals("Tasikmalaya")
                && av.getShipCity().getSelectedItem().equals("Purworejo")
                || av.getPickCity().getSelectedItem().equals("Purworejo")
                && av.getShipCity().getSelectedItem().equals("Tasikmalaya")) {
            av.getCheckDistance().setText("256");
        }
        if (av.getPickCity().getSelectedItem().equals("Tasikmalaya")
                && av.getShipCity().getSelectedItem().equals("Semarang")
                || av.getPickCity().getSelectedItem().equals("Semarang")
                && av.getShipCity().getSelectedItem().equals("Tasikmalaya")) {
            av.getCheckDistance().setText("358");
        }
        if (av.getPickCity().getSelectedItem().equals("Tasikmalaya")
                && av.getShipCity().getSelectedItem().equals("Magelang")
                || av.getPickCity().getSelectedItem().equals("Magelang")
                && av.getShipCity().getSelectedItem().equals("Tasikmalaya")) {
            av.getCheckDistance().setText("297");
        }
        if (av.getPickCity().getSelectedItem().equals("Tasikmalaya")
                && av.getShipCity().getSelectedItem().equals("Yogyakarta")
                || av.getPickCity().getSelectedItem().equals("Yogyakarta")
                && av.getShipCity().getSelectedItem().equals("Tasikmalaya")) {
            av.getCheckDistance().setText("322");
        }
        if (av.getPickCity().getSelectedItem().equals("Tasikmalaya")
                && av.getShipCity().getSelectedItem().equals("Solo")
                || av.getPickCity().getSelectedItem().equals("Solo")
                && av.getShipCity().getSelectedItem().equals("Tasikmalaya")) {
            av.getCheckDistance().setText("387");
        }
        if (av.getPickCity().getSelectedItem().equals("Tasikmalaya")
                && av.getShipCity().getSelectedItem().equals("Rembang")
                || av.getPickCity().getSelectedItem().equals("Rembang")
                && av.getShipCity().getSelectedItem().equals("Tasikmalaya")) {
            av.getCheckDistance().setText("467");
        }
        if (av.getPickCity().getSelectedItem().equals("Tasikmalaya")
                && av.getShipCity().getSelectedItem().equals("Madiun")
                || av.getPickCity().getSelectedItem().equals("Madiun")
                && av.getShipCity().getSelectedItem().equals("Tasikmalaya")) {
            av.getCheckDistance().setText("501");
        }
        if (av.getPickCity().getSelectedItem().equals("Tasikmalaya")
                && av.getShipCity().getSelectedItem().equals("Kediri")
                || av.getPickCity().getSelectedItem().equals("Kediri")
                && av.getShipCity().getSelectedItem().equals("Tasikmalaya")) {
            av.getCheckDistance().setText("562");
        }
        if (av.getPickCity().getSelectedItem().equals("Tasikmalaya")
                && av.getShipCity().getSelectedItem().equals("Surabaya")
                || av.getPickCity().getSelectedItem().equals("Surabaya")
                && av.getShipCity().getSelectedItem().equals("Tasikmalaya")) {
            av.getCheckDistance().setText("849");
        }
        if (av.getPickCity().getSelectedItem().equals("Tasikmalaya")
                && av.getShipCity().getSelectedItem().equals("Malang")
                || av.getPickCity().getSelectedItem().equals("Malang")
                && av.getShipCity().getSelectedItem().equals("Tasikmalaya")) {
            av.getCheckDistance().setText("682");
        }
        if (av.getPickCity().getSelectedItem().equals("Tasikmalaya")
                && av.getShipCity().getSelectedItem().equals("Probolinggo")
                || av.getPickCity().getSelectedItem().equals("Probolinggo")
                && av.getShipCity().getSelectedItem().equals("Tasikmalaya")) {
            av.getCheckDistance().setText("702");
        }
        if (av.getPickCity().getSelectedItem().equals("Tasikmalaya")
                && av.getShipCity().getSelectedItem().equals("Bondowoso")
                || av.getPickCity().getSelectedItem().equals("Bondowoso")
                && av.getShipCity().getSelectedItem().equals("Tasikmalaya")) {
            av.getCheckDistance().setText("796");
        }
        if (av.getPickCity().getSelectedItem().equals("Tasikmalaya")
                && av.getShipCity().getSelectedItem().equals("Jember")
                || av.getPickCity().getSelectedItem().equals("Jember")
                && av.getShipCity().getSelectedItem().equals("Tasikmalaya")) {
            av.getCheckDistance().setText("801");
        }
        if (av.getPickCity().getSelectedItem().equals("Tasikmalaya")
                && av.getShipCity().getSelectedItem().equals("Banyuwangi")
                || av.getPickCity().getSelectedItem().equals("Banyuwangi")
                && av.getShipCity().getSelectedItem().equals("Tasikmalaya")) {
            av.getCheckDistance().setText("893");
        }
        if (av.getPickCity().getSelectedItem().equals("Tasikmalaya")
                && av.getShipCity().getSelectedItem().equals("Merak")
                || av.getPickCity().getSelectedItem().equals("Merak")
                && av.getShipCity().getSelectedItem().equals("Tasikmalaya")) {
            av.getCheckDistance().setText("369");
        }
        if (av.getPickCity().getSelectedItem().equals("Bogor")
                && av.getShipCity().getSelectedItem().equals("Cirebon")
                || av.getPickCity().getSelectedItem().equals("Cirebon")
                && av.getShipCity().getSelectedItem().equals("Bogor")) {
            av.getCheckDistance().setText("256");
        }
        if (av.getPickCity().getSelectedItem().equals("Bogor")
                && av.getShipCity().getSelectedItem().equals("Jakarta")
                || av.getPickCity().getSelectedItem().equals("Jakarta")
                && av.getShipCity().getSelectedItem().equals("Bogor")) {
            av.getCheckDistance().setText("54");
        }
        if (av.getPickCity().getSelectedItem().equals("Bogor")
                && av.getShipCity().getSelectedItem().equals("Tegal")
                || av.getPickCity().getSelectedItem().equals("Tegal")
                && av.getShipCity().getSelectedItem().equals("Bogor")) {
            av.getCheckDistance().setText("328");
        }
        if (av.getPickCity().getSelectedItem().equals("Bogor")
                && av.getShipCity().getSelectedItem().equals("Serang")
                || av.getPickCity().getSelectedItem().equals("Serang")
                && av.getShipCity().getSelectedItem().equals("Bogor")) {
            av.getCheckDistance().setText("143");
        }
        if (av.getPickCity().getSelectedItem().equals("Bogor")
                && av.getShipCity().getSelectedItem().equals("Cilacap")
                || av.getPickCity().getSelectedItem().equals("Cilacap")
                && av.getShipCity().getSelectedItem().equals("Bogor")) {
            av.getCheckDistance().setText("385");
        }
        if (av.getPickCity().getSelectedItem().equals("Bogor")
                && av.getShipCity().getSelectedItem().equals("Pekalongan")
                || av.getPickCity().getSelectedItem().equals("Pekalongan")
                && av.getShipCity().getSelectedItem().equals("Bogor")) {
            av.getCheckDistance().setText("392");
        }
        if (av.getPickCity().getSelectedItem().equals("Bogor")
                && av.getShipCity().getSelectedItem().equals("Wonosobo")
                || av.getPickCity().getSelectedItem().equals("Wonosobo")
                && av.getShipCity().getSelectedItem().equals("Bogor")) {
            av.getCheckDistance().setText("465");
        }
        if (av.getPickCity().getSelectedItem().equals("Bogor")
                && av.getShipCity().getSelectedItem().equals("Purworejo")
                || av.getPickCity().getSelectedItem().equals("Purworejo")
                && av.getShipCity().getSelectedItem().equals("Bogor")) {
            av.getCheckDistance().setText("488");
        }
        if (av.getPickCity().getSelectedItem().equals("Bogor")
                && av.getShipCity().getSelectedItem().equals("Semarang")
                || av.getPickCity().getSelectedItem().equals("Semarang")
                && av.getShipCity().getSelectedItem().equals("Bogor")) {
            av.getCheckDistance().setText("493");
        }
        if (av.getPickCity().getSelectedItem().equals("Bogor")
                && av.getShipCity().getSelectedItem().equals("Magelang")
                || av.getPickCity().getSelectedItem().equals("Magelang")
                && av.getShipCity().getSelectedItem().equals("Bogor")) {
            av.getCheckDistance().setText("530");
        }
        if (av.getPickCity().getSelectedItem().equals("Bogor")
                && av.getShipCity().getSelectedItem().equals("Yogyakarta")
                || av.getPickCity().getSelectedItem().equals("Yogyakarta")
                && av.getShipCity().getSelectedItem().equals("Bogor")) {
            av.getCheckDistance().setText("554");
        }
        if (av.getPickCity().getSelectedItem().equals("Bogor")
                && av.getShipCity().getSelectedItem().equals("Solo")
                || av.getPickCity().getSelectedItem().equals("Solo")
                && av.getShipCity().getSelectedItem().equals("Bogor")) {
            av.getCheckDistance().setText("593");
        }
        if (av.getPickCity().getSelectedItem().equals("Bogor")
                && av.getShipCity().getSelectedItem().equals("Rembang")
                || av.getPickCity().getSelectedItem().equals("Rembang")
                && av.getShipCity().getSelectedItem().equals("Bogor")) {
            av.getCheckDistance().setText("602");
        }
        if (av.getPickCity().getSelectedItem().equals("Bogor")
                && av.getShipCity().getSelectedItem().equals("Madiun")
                || av.getPickCity().getSelectedItem().equals("Madiun")
                && av.getShipCity().getSelectedItem().equals("Bogor")) {
            av.getCheckDistance().setText("707");
        }
        if (av.getPickCity().getSelectedItem().equals("Bogor")
                && av.getShipCity().getSelectedItem().equals("Kediri")
                || av.getPickCity().getSelectedItem().equals("Kediri")
                && av.getShipCity().getSelectedItem().equals("Bogor")) {
            av.getCheckDistance().setText("788");
        }
        if (av.getPickCity().getSelectedItem().equals("Bogor")
                && av.getShipCity().getSelectedItem().equals("Surabaya")
                || av.getPickCity().getSelectedItem().equals("Surabaya")
                && av.getShipCity().getSelectedItem().equals("Bogor")) {
            av.getCheckDistance().setText("801");
        }
        if (av.getPickCity().getSelectedItem().equals("Bogor")
                && av.getShipCity().getSelectedItem().equals("Malang")
                || av.getPickCity().getSelectedItem().equals("Malang")
                && av.getShipCity().getSelectedItem().equals("Bogor")) {
            av.getCheckDistance().setText("890");
        }
        if (av.getPickCity().getSelectedItem().equals("Bogor")
                && av.getShipCity().getSelectedItem().equals("Probolinggo")
                || av.getPickCity().getSelectedItem().equals("Probolinggo")
                && av.getShipCity().getSelectedItem().equals("Bogor")) {
            av.getCheckDistance().setText("900");
        }
        if (av.getPickCity().getSelectedItem().equals("Bogor")
                && av.getShipCity().getSelectedItem().equals("Bondowoso")
                || av.getPickCity().getSelectedItem().equals("Bondowoso")
                && av.getShipCity().getSelectedItem().equals("Bogor")) {
            av.getCheckDistance().setText("993");
        }
        if (av.getPickCity().getSelectedItem().equals("Bogor")
                && av.getShipCity().getSelectedItem().equals("Jember")
                || av.getPickCity().getSelectedItem().equals("Jember")
                && av.getShipCity().getSelectedItem().equals("Bogor")) {
            av.getCheckDistance().setText("999");
        }
        if (av.getPickCity().getSelectedItem().equals("Bogor")
                && av.getShipCity().getSelectedItem().equals("Banyuwangi")
                || av.getPickCity().getSelectedItem().equals("Banyuwangi")
                && av.getShipCity().getSelectedItem().equals("Bogor")) {
            av.getCheckDistance().setText("1090");
        }
        if (av.getPickCity().getSelectedItem().equals("Bogor")
                && av.getShipCity().getSelectedItem().equals("Merak")
                || av.getPickCity().getSelectedItem().equals("Merak")
                && av.getShipCity().getSelectedItem().equals("Bogor")) {
            av.getCheckDistance().setText("163");
        }
        if (av.getPickCity().getSelectedItem().equals("Cirebon")
                && av.getShipCity().getSelectedItem().equals("Jakarta")
                || av.getPickCity().getSelectedItem().equals("Jakarta")
                && av.getShipCity().getSelectedItem().equals("Cirebon")) {
            av.getCheckDistance().setText("248");
        }
        if (av.getPickCity().getSelectedItem().equals("Cirebon")
                && av.getShipCity().getSelectedItem().equals("Tegal")
                || av.getPickCity().getSelectedItem().equals("Tegal")
                && av.getShipCity().getSelectedItem().equals("Cirebon")) {
            av.getCheckDistance().setText("72");
        }
        if (av.getPickCity().getSelectedItem().equals("Cirebon")
                && av.getShipCity().getSelectedItem().equals("Serang")
                || av.getPickCity().getSelectedItem().equals("Serang")
                && av.getShipCity().getSelectedItem().equals("Cirebon")) {
            av.getCheckDistance().setText("337");
        }
        if (av.getPickCity().getSelectedItem().equals("Cirebon")
                && av.getShipCity().getSelectedItem().equals("Cilacap")
                || av.getPickCity().getSelectedItem().equals("Cilacap")
                && av.getShipCity().getSelectedItem().equals("Cirebon")) {
            av.getCheckDistance().setText("203");
        }
        if (av.getPickCity().getSelectedItem().equals("Cirebon")
                && av.getShipCity().getSelectedItem().equals("Pekalongan")
                || av.getPickCity().getSelectedItem().equals("Pekalongan")
                && av.getShipCity().getSelectedItem().equals("Cirebon")) {
            av.getCheckDistance().setText("136");
        }
        if (av.getPickCity().getSelectedItem().equals("Cirebon")
                && av.getShipCity().getSelectedItem().equals("Wonosobo")
                || av.getPickCity().getSelectedItem().equals("Wonosobo")
                && av.getShipCity().getSelectedItem().equals("Cirebon")) {
            av.getCheckDistance().setText("233");
        }
        if (av.getPickCity().getSelectedItem().equals("Cirebon")
                && av.getShipCity().getSelectedItem().equals("Purworejo")
                || av.getPickCity().getSelectedItem().equals("Purworejo")
                && av.getShipCity().getSelectedItem().equals("Cirebon")) {
            av.getCheckDistance().setText("256");
        }
        if (av.getPickCity().getSelectedItem().equals("Cirebon")
                && av.getShipCity().getSelectedItem().equals("Semarang")
                || av.getPickCity().getSelectedItem().equals("Semarang")
                && av.getShipCity().getSelectedItem().equals("Cirebon")) {
            av.getCheckDistance().setText("237");
        }
        if (av.getPickCity().getSelectedItem().equals("Cirebon")
                && av.getShipCity().getSelectedItem().equals("Magelang")
                || av.getPickCity().getSelectedItem().equals("Magelang")
                && av.getShipCity().getSelectedItem().equals("Cirebon")) {
            av.getCheckDistance().setText("274");
        }
        if (av.getPickCity().getSelectedItem().equals("Cirebon")
                && av.getShipCity().getSelectedItem().equals("Yogyakarta")
                || av.getPickCity().getSelectedItem().equals("Yogyakarta")
                && av.getShipCity().getSelectedItem().equals("Cirebon")) {
            av.getCheckDistance().setText("317");
        }
        if (av.getPickCity().getSelectedItem().equals("Cirebon")
                && av.getShipCity().getSelectedItem().equals("Solo")
                || av.getPickCity().getSelectedItem().equals("Solo")
                && av.getShipCity().getSelectedItem().equals("Cirebon")) {
            av.getCheckDistance().setText("337");
        }
        if (av.getPickCity().getSelectedItem().equals("Cirebon")
                && av.getShipCity().getSelectedItem().equals("Rembang")
                || av.getPickCity().getSelectedItem().equals("Rembang")
                && av.getShipCity().getSelectedItem().equals("Cirebon")) {
            av.getCheckDistance().setText("346");
        }
        if (av.getPickCity().getSelectedItem().equals("Cirebon")
                && av.getShipCity().getSelectedItem().equals("Madiun")
                || av.getPickCity().getSelectedItem().equals("Madiun")
                && av.getShipCity().getSelectedItem().equals("Cirebon")) {
            av.getCheckDistance().setText("451");
        }
        if (av.getPickCity().getSelectedItem().equals("Cirebon")
                && av.getShipCity().getSelectedItem().equals("Kediri")
                || av.getPickCity().getSelectedItem().equals("Kediri")
                && av.getShipCity().getSelectedItem().equals("Cirebon")) {
            av.getCheckDistance().setText("532");
        }
        if (av.getPickCity().getSelectedItem().equals("Cirebon")
                && av.getShipCity().getSelectedItem().equals("Surabaya")
                || av.getPickCity().getSelectedItem().equals("Surabaya")
                && av.getShipCity().getSelectedItem().equals("Cirebon")) {
            av.getCheckDistance().setText("545");
        }
        if (av.getPickCity().getSelectedItem().equals("Cirebon")
                && av.getShipCity().getSelectedItem().equals("Malang")
                || av.getPickCity().getSelectedItem().equals("Malang")
                && av.getShipCity().getSelectedItem().equals("Cirebon")) {
            av.getCheckDistance().setText("634");
        }
        if (av.getPickCity().getSelectedItem().equals("Cirebon")
                && av.getShipCity().getSelectedItem().equals("Probolinggo")
                || av.getPickCity().getSelectedItem().equals("Probolinggo")
                && av.getShipCity().getSelectedItem().equals("Cirebon")) {
            av.getCheckDistance().setText("644");
        }
        if (av.getPickCity().getSelectedItem().equals("Cirebon")
                && av.getShipCity().getSelectedItem().equals("Bondowoso")
                || av.getPickCity().getSelectedItem().equals("Bondowoso")
                && av.getShipCity().getSelectedItem().equals("Cirebon")) {
            av.getCheckDistance().setText("739");
        }
        if (av.getPickCity().getSelectedItem().equals("Cirebon")
                && av.getShipCity().getSelectedItem().equals("Jember")
                || av.getPickCity().getSelectedItem().equals("Jember")
                && av.getShipCity().getSelectedItem().equals("Cirebon")) {
            av.getCheckDistance().setText("743");
        }
        if (av.getPickCity().getSelectedItem().equals("Cirebon")
                && av.getShipCity().getSelectedItem().equals("Banyuwangi")
                || av.getPickCity().getSelectedItem().equals("Banyuwangi")
                && av.getShipCity().getSelectedItem().equals("Cirebon")) {
            av.getCheckDistance().setText("834");
        }
        if (av.getPickCity().getSelectedItem().equals("Cirebon")
                && av.getShipCity().getSelectedItem().equals("Merak")
                || av.getPickCity().getSelectedItem().equals("Merak")
                && av.getShipCity().getSelectedItem().equals("Cirebon")) {
            av.getCheckDistance().setText("328");
        }
        if (av.getPickCity().getSelectedItem().equals("Jakarta")
                && av.getShipCity().getSelectedItem().equals("Tegal")
                || av.getPickCity().getSelectedItem().equals("Tegal")
                && av.getShipCity().getSelectedItem().equals("Jakarta")) {
            av.getCheckDistance().setText("320");
        }
        if (av.getPickCity().getSelectedItem().equals("Jakarta")
                && av.getShipCity().getSelectedItem().equals("Serang")
                || av.getPickCity().getSelectedItem().equals("Serang")
                && av.getShipCity().getSelectedItem().equals("Jakarta")) {
            av.getCheckDistance().setText("189");
        }
        if (av.getPickCity().getSelectedItem().equals("Jakarta")
                && av.getShipCity().getSelectedItem().equals("Cilacap")
                || av.getPickCity().getSelectedItem().equals("Cilacap")
                && av.getShipCity().getSelectedItem().equals("Jakarta")) {
            av.getCheckDistance().setText("428");
        }
        if (av.getPickCity().getSelectedItem().equals("Jakarta")
                && av.getShipCity().getSelectedItem().equals("Pekalongan")
                || av.getPickCity().getSelectedItem().equals("Pekalongan")
                && av.getShipCity().getSelectedItem().equals("Jakarta")) {
            av.getCheckDistance().setText("384");
        }
        if (av.getPickCity().getSelectedItem().equals("Jakarta")
                && av.getShipCity().getSelectedItem().equals("Wonosobo")
                || av.getPickCity().getSelectedItem().equals("Wonosobo")
                && av.getShipCity().getSelectedItem().equals("Jakarta")) {
            av.getCheckDistance().setText("481");
        }
        if (av.getPickCity().getSelectedItem().equals("Jakarta")
                && av.getShipCity().getSelectedItem().equals("Purworejo")
                || av.getPickCity().getSelectedItem().equals("Purworejo")
                && av.getShipCity().getSelectedItem().equals("Jakarta")) {
            av.getCheckDistance().setText("504");
        }
        if (av.getPickCity().getSelectedItem().equals("Jakarta")
                && av.getShipCity().getSelectedItem().equals("Semarang")
                || av.getPickCity().getSelectedItem().equals("Semarang")
                && av.getShipCity().getSelectedItem().equals("Jakarta")) {
            av.getCheckDistance().setText("485");
        }
        if (av.getPickCity().getSelectedItem().equals("Jakarta")
                && av.getShipCity().getSelectedItem().equals("Magelang")
                || av.getPickCity().getSelectedItem().equals("Magelang")
                && av.getShipCity().getSelectedItem().equals("Jakarta")) {
            av.getCheckDistance().setText("522");
        }
        if (av.getPickCity().getSelectedItem().equals("Jakarta")
                && av.getShipCity().getSelectedItem().equals("Yogyakarta")
                || av.getPickCity().getSelectedItem().equals("Yogyakarta")
                && av.getShipCity().getSelectedItem().equals("Jakarta")) {
            av.getCheckDistance().setText("565");
        }
        if (av.getPickCity().getSelectedItem().equals("Jakarta")
                && av.getShipCity().getSelectedItem().equals("Solo")
                || av.getPickCity().getSelectedItem().equals("Solo")
                && av.getShipCity().getSelectedItem().equals("Jakarta")) {
            av.getCheckDistance().setText("585");
        }
        if (av.getPickCity().getSelectedItem().equals("Jakarta")
                && av.getShipCity().getSelectedItem().equals("Rembang")
                || av.getPickCity().getSelectedItem().equals("Rembang")
                && av.getShipCity().getSelectedItem().equals("Jakarta")) {
            av.getCheckDistance().setText("594");
        }
        if (av.getPickCity().getSelectedItem().equals("Jakarta")
                && av.getShipCity().getSelectedItem().equals("Madiun")
                || av.getPickCity().getSelectedItem().equals("Madiun")
                && av.getShipCity().getSelectedItem().equals("Jakarta")) {
            av.getCheckDistance().setText("699");
        }
        if (av.getPickCity().getSelectedItem().equals("Jakarta")
                && av.getShipCity().getSelectedItem().equals("Kediri")
                || av.getPickCity().getSelectedItem().equals("Kediri")
                && av.getShipCity().getSelectedItem().equals("Jakarta")) {
            av.getCheckDistance().setText("780");
        }
        if (av.getPickCity().getSelectedItem().equals("Jakarta")
                && av.getShipCity().getSelectedItem().equals("Surabaya")
                || av.getPickCity().getSelectedItem().equals("Surabaya")
                && av.getShipCity().getSelectedItem().equals("Jakarta")) {
            av.getCheckDistance().setText("793");
        }
        if (av.getPickCity().getSelectedItem().equals("Jakarta")
                && av.getShipCity().getSelectedItem().equals("Malang")
                || av.getPickCity().getSelectedItem().equals("Malang")
                && av.getShipCity().getSelectedItem().equals("Jakarta")) {
            av.getCheckDistance().setText("882");
        }
        if (av.getPickCity().getSelectedItem().equals("Jakarta")
                && av.getShipCity().getSelectedItem().equals("Probolinggo")
                || av.getPickCity().getSelectedItem().equals("Probolinggo")
                && av.getShipCity().getSelectedItem().equals("Jakarta")) {
            av.getCheckDistance().setText("892");
        }
        if (av.getPickCity().getSelectedItem().equals("Jakarta")
                && av.getShipCity().getSelectedItem().equals("Bondowoso")
                || av.getPickCity().getSelectedItem().equals("Bondowoso")
                && av.getShipCity().getSelectedItem().equals("Jakarta")) {
            av.getCheckDistance().setText("985");
        }
        if (av.getPickCity().getSelectedItem().equals("Jakarta")
                && av.getShipCity().getSelectedItem().equals("Jember")
                || av.getPickCity().getSelectedItem().equals("Jember")
                && av.getShipCity().getSelectedItem().equals("Jakarta")) {
            av.getCheckDistance().setText("991");
        }
        if (av.getPickCity().getSelectedItem().equals("Jakarta")
                && av.getShipCity().getSelectedItem().equals("Banyuwangi")
                || av.getPickCity().getSelectedItem().equals("Banyuwangi")
                && av.getShipCity().getSelectedItem().equals("Jakarta")) {
            av.getCheckDistance().setText("1082");
        }
        if (av.getPickCity().getSelectedItem().equals("Jakarta")
                && av.getShipCity().getSelectedItem().equals("Merak")
                || av.getPickCity().getSelectedItem().equals("Merak")
                && av.getShipCity().getSelectedItem().equals("Jakarta")) {
            av.getCheckDistance().setText("116");
        }
        if (av.getPickCity().getSelectedItem().equals("Tegal")
                && av.getShipCity().getSelectedItem().equals("Serang")
                || av.getPickCity().getSelectedItem().equals("Serang")
                && av.getShipCity().getSelectedItem().equals("Tegal")) {
            av.getCheckDistance().setText("409");
        }
        if (av.getPickCity().getSelectedItem().equals("Tegal")
                && av.getShipCity().getSelectedItem().equals("Cilacap")
                || av.getPickCity().getSelectedItem().equals("Cilacap")
                && av.getShipCity().getSelectedItem().equals("Tegal")) {
            av.getCheckDistance().setText("131");
        }
        if (av.getPickCity().getSelectedItem().equals("Tegal")
                && av.getShipCity().getSelectedItem().equals("Pekalongan")
                || av.getPickCity().getSelectedItem().equals("Pekalongan")
                && av.getShipCity().getSelectedItem().equals("Tegal")) {
            av.getCheckDistance().setText("64");
        }
        if (av.getPickCity().getSelectedItem().equals("Tegal")
                && av.getShipCity().getSelectedItem().equals("Wonosobo")
                || av.getPickCity().getSelectedItem().equals("Wonosobo")
                && av.getShipCity().getSelectedItem().equals("Tegal")) {
            av.getCheckDistance().setText("197");
        }
        if (av.getPickCity().getSelectedItem().equals("Tegal")
                && av.getShipCity().getSelectedItem().equals("Purworejo")
                || av.getPickCity().getSelectedItem().equals("Purworejo")
                && av.getShipCity().getSelectedItem().equals("Tegal")) {
            av.getCheckDistance().setText("223");
        }
        if (av.getPickCity().getSelectedItem().equals("Tegal")
                && av.getShipCity().getSelectedItem().equals("Semarang")
                || av.getPickCity().getSelectedItem().equals("Semarang")
                && av.getShipCity().getSelectedItem().equals("Tegal")) {
            av.getCheckDistance().setText("165");
        }
        if (av.getPickCity().getSelectedItem().equals("Tegal")
                && av.getShipCity().getSelectedItem().equals("Magelang")
                || av.getPickCity().getSelectedItem().equals("Magelang")
                && av.getShipCity().getSelectedItem().equals("Tegal")) {
            av.getCheckDistance().setText("202");
        }
        if (av.getPickCity().getSelectedItem().equals("Tegal")
                && av.getShipCity().getSelectedItem().equals("Yogyakarta")
                || av.getPickCity().getSelectedItem().equals("Yogyakarta")
                && av.getShipCity().getSelectedItem().equals("Tegal")) {
            av.getCheckDistance().setText("245");
        }
        if (av.getPickCity().getSelectedItem().equals("Tegal")
                && av.getShipCity().getSelectedItem().equals("Solo")
                || av.getPickCity().getSelectedItem().equals("Solo")
                && av.getShipCity().getSelectedItem().equals("Tegal")) {
            av.getCheckDistance().setText("265");
        }
        if (av.getPickCity().getSelectedItem().equals("Tegal")
                && av.getShipCity().getSelectedItem().equals("Rembang")
                || av.getPickCity().getSelectedItem().equals("Rembang")
                && av.getShipCity().getSelectedItem().equals("Tegal")) {
            av.getCheckDistance().setText("274");
        }
        if (av.getPickCity().getSelectedItem().equals("Tegal")
                && av.getShipCity().getSelectedItem().equals("Madiun")
                || av.getPickCity().getSelectedItem().equals("Madiun")
                && av.getShipCity().getSelectedItem().equals("Tegal")) {
            av.getCheckDistance().setText("379");
        }
        if (av.getPickCity().getSelectedItem().equals("Tegal")
                && av.getShipCity().getSelectedItem().equals("Kediri")
                || av.getPickCity().getSelectedItem().equals("Kediri")
                && av.getShipCity().getSelectedItem().equals("Tegal")) {
            av.getCheckDistance().setText("460");
        }
        if (av.getPickCity().getSelectedItem().equals("Tegal")
                && av.getShipCity().getSelectedItem().equals("Surabaya")
                || av.getPickCity().getSelectedItem().equals("Surabaya")
                && av.getShipCity().getSelectedItem().equals("Tegal")) {
            av.getCheckDistance().setText("473");
        }
        if (av.getPickCity().getSelectedItem().equals("Tegal")
                && av.getShipCity().getSelectedItem().equals("Malang")
                || av.getPickCity().getSelectedItem().equals("Malang")
                && av.getShipCity().getSelectedItem().equals("Tegal")) {
            av.getCheckDistance().setText("562");
        }
        if (av.getPickCity().getSelectedItem().equals("Tegal")
                && av.getShipCity().getSelectedItem().equals("Probolinggo")
                || av.getPickCity().getSelectedItem().equals("Probolinggo")
                && av.getShipCity().getSelectedItem().equals("Tegal")) {
            av.getCheckDistance().setText("572");
        }
        if (av.getPickCity().getSelectedItem().equals("Tegal")
                && av.getShipCity().getSelectedItem().equals("Bondowoso")
                || av.getPickCity().getSelectedItem().equals("Bondowoso")
                && av.getShipCity().getSelectedItem().equals("Tegal")) {
            av.getCheckDistance().setText("665");
        }
        if (av.getPickCity().getSelectedItem().equals("Tegal")
                && av.getShipCity().getSelectedItem().equals("Jember")
                || av.getPickCity().getSelectedItem().equals("Jember")
                && av.getShipCity().getSelectedItem().equals("Tegal")) {
            av.getCheckDistance().setText("671");
        }
        if (av.getPickCity().getSelectedItem().equals("Tegal")
                && av.getShipCity().getSelectedItem().equals("Banyuwangi")
                || av.getPickCity().getSelectedItem().equals("Banyuwangi")
                && av.getShipCity().getSelectedItem().equals("Tegal")) {
            av.getCheckDistance().setText("762");
        }
        if (av.getPickCity().getSelectedItem().equals("Tegal")
                && av.getShipCity().getSelectedItem().equals("Merak")
                || av.getPickCity().getSelectedItem().equals("Merak")
                && av.getShipCity().getSelectedItem().equals("Tegal")) {
            av.getCheckDistance().setText("397");
        }
        if (av.getPickCity().getSelectedItem().equals("Serang")
                && av.getShipCity().getSelectedItem().equals("Cilacap")
                || av.getPickCity().getSelectedItem().equals("Cilacap")
                && av.getShipCity().getSelectedItem().equals("Serang")) {
            av.getCheckDistance().setText("517");
        }
        if (av.getPickCity().getSelectedItem().equals("Serang")
                && av.getShipCity().getSelectedItem().equals("Pekalongan")
                || av.getPickCity().getSelectedItem().equals("Pekalongan")
                && av.getShipCity().getSelectedItem().equals("Serang")) {
            av.getCheckDistance().setText("473");
        }
        if (av.getPickCity().getSelectedItem().equals("Serang")
                && av.getShipCity().getSelectedItem().equals("Wonosobo")
                || av.getPickCity().getSelectedItem().equals("Wonosobo")
                && av.getShipCity().getSelectedItem().equals("Serang")) {
            av.getCheckDistance().setText("570");
        }
        if (av.getPickCity().getSelectedItem().equals("Serang")
                && av.getShipCity().getSelectedItem().equals("Purworejo")
                || av.getPickCity().getSelectedItem().equals("Purworejo")
                && av.getShipCity().getSelectedItem().equals("Serang")) {
            av.getCheckDistance().setText("593");
        }
        if (av.getPickCity().getSelectedItem().equals("Serang")
                && av.getShipCity().getSelectedItem().equals("Semarang")
                || av.getPickCity().getSelectedItem().equals("Semarang")
                && av.getShipCity().getSelectedItem().equals("Serang")) {
            av.getCheckDistance().setText("574");
        }
        if (av.getPickCity().getSelectedItem().equals("Serang")
                && av.getShipCity().getSelectedItem().equals("Magelang")
                || av.getPickCity().getSelectedItem().equals("Magelang")
                && av.getShipCity().getSelectedItem().equals("Serang")) {
            av.getCheckDistance().setText("611");
        }
        if (av.getPickCity().getSelectedItem().equals("Serang")
                && av.getShipCity().getSelectedItem().equals("Yogyakarta")
                || av.getPickCity().getSelectedItem().equals("Yogyakarta")
                && av.getShipCity().getSelectedItem().equals("Serang")) {
            av.getCheckDistance().setText("654");
        }
        if (av.getPickCity().getSelectedItem().equals("Serang")
                && av.getShipCity().getSelectedItem().equals("Solo")
                || av.getPickCity().getSelectedItem().equals("Solo")
                && av.getShipCity().getSelectedItem().equals("Serang")) {
            av.getCheckDistance().setText("674");
        }
        if (av.getPickCity().getSelectedItem().equals("Serang")
                && av.getShipCity().getSelectedItem().equals("Rembang")
                || av.getPickCity().getSelectedItem().equals("Rembang")
                && av.getShipCity().getSelectedItem().equals("Serang")) {
            av.getCheckDistance().setText("683");
        }
        if (av.getPickCity().getSelectedItem().equals("Serang")
                && av.getShipCity().getSelectedItem().equals("Madiun")
                || av.getPickCity().getSelectedItem().equals("Madiun")
                && av.getShipCity().getSelectedItem().equals("Serang")) {
            av.getCheckDistance().setText("788");
        }
        if (av.getPickCity().getSelectedItem().equals("Serang")
                && av.getShipCity().getSelectedItem().equals("Kediri")
                || av.getPickCity().getSelectedItem().equals("Kediri")
                && av.getShipCity().getSelectedItem().equals("Serang")) {
            av.getCheckDistance().setText("869");
        }
        if (av.getPickCity().getSelectedItem().equals("Serang")
                && av.getShipCity().getSelectedItem().equals("Surabaya")
                || av.getPickCity().getSelectedItem().equals("Surabaya")
                && av.getShipCity().getSelectedItem().equals("Serang")) {
            av.getCheckDistance().setText("882");
        }
        if (av.getPickCity().getSelectedItem().equals("Serang")
                && av.getShipCity().getSelectedItem().equals("Malang")
                || av.getPickCity().getSelectedItem().equals("Malang")
                && av.getShipCity().getSelectedItem().equals("Serang")) {
            av.getCheckDistance().setText("971");
        }
        if (av.getPickCity().getSelectedItem().equals("Serang")
                && av.getShipCity().getSelectedItem().equals("Probolinggo")
                || av.getPickCity().getSelectedItem().equals("Probolinggo")
                && av.getShipCity().getSelectedItem().equals("Serang")) {
            av.getCheckDistance().setText("981");
        }
        if (av.getPickCity().getSelectedItem().equals("Serang")
                && av.getShipCity().getSelectedItem().equals("Bondowoso")
                || av.getPickCity().getSelectedItem().equals("Bondowoso")
                && av.getShipCity().getSelectedItem().equals("Serang")) {
            av.getCheckDistance().setText("1074");
        }
        if (av.getPickCity().getSelectedItem().equals("Serang")
                && av.getShipCity().getSelectedItem().equals("Jember")
                || av.getPickCity().getSelectedItem().equals("Jember")
                && av.getShipCity().getSelectedItem().equals("Serang")) {
            av.getCheckDistance().setText("1080");
        }
        if (av.getPickCity().getSelectedItem().equals("Serang")
                && av.getShipCity().getSelectedItem().equals("Banyuwangi")
                || av.getPickCity().getSelectedItem().equals("Banyuwangi")
                && av.getShipCity().getSelectedItem().equals("Serang")) {
            av.getCheckDistance().setText("1117");
        }
        if (av.getPickCity().getSelectedItem().equals("Serang")
                && av.getShipCity().getSelectedItem().equals("Merak")
                || av.getPickCity().getSelectedItem().equals("Merak")
                && av.getShipCity().getSelectedItem().equals("Serang")) {
            av.getCheckDistance().setText("34");
        }
        if (av.getPickCity().getSelectedItem().equals("Cilacap")
                && av.getShipCity().getSelectedItem().equals("Pekalongan")
                || av.getPickCity().getSelectedItem().equals("Pekalongan")
                && av.getShipCity().getSelectedItem().equals("Cilacap")) {
            av.getCheckDistance().setText("193");
        }
        if (av.getPickCity().getSelectedItem().equals("Cilacap")
                && av.getShipCity().getSelectedItem().equals("Wonosobo")
                || av.getPickCity().getSelectedItem().equals("Wonosobo")
                && av.getShipCity().getSelectedItem().equals("Cilacap")) {
            av.getCheckDistance().setText("127");
        }
        if (av.getPickCity().getSelectedItem().equals("Cilacap")
                && av.getShipCity().getSelectedItem().equals("Purworejo")
                || av.getPickCity().getSelectedItem().equals("Purworejo")
                && av.getShipCity().getSelectedItem().equals("Cilacap")) {
            av.getCheckDistance().setText("131");
        }
        if (av.getPickCity().getSelectedItem().equals("Cilacap")
                && av.getShipCity().getSelectedItem().equals("Semarang")
                || av.getPickCity().getSelectedItem().equals("Semarang")
                && av.getShipCity().getSelectedItem().equals("Cilacap")) {
            av.getCheckDistance().setText("246");
        }
        if (av.getPickCity().getSelectedItem().equals("Cilacap")
                && av.getShipCity().getSelectedItem().equals("Magelang")
                || av.getPickCity().getSelectedItem().equals("Magelang")
                && av.getShipCity().getSelectedItem().equals("Cilacap")) {
            av.getCheckDistance().setText("174");
        }
        if (av.getPickCity().getSelectedItem().equals("Cilacap")
                && av.getShipCity().getSelectedItem().equals("Yogyakarta")
                || av.getPickCity().getSelectedItem().equals("Yogyakarta")
                && av.getShipCity().getSelectedItem().equals("Cilacap")) {
            av.getCheckDistance().setText("197");
        }
        if (av.getPickCity().getSelectedItem().equals("Cilacap")
                && av.getShipCity().getSelectedItem().equals("Solo")
                || av.getPickCity().getSelectedItem().equals("Solo")
                && av.getShipCity().getSelectedItem().equals("Cilacap")) {
            av.getCheckDistance().setText("262");
        }
        if (av.getPickCity().getSelectedItem().equals("Cilacap")
                && av.getShipCity().getSelectedItem().equals("Rembang")
                || av.getPickCity().getSelectedItem().equals("Rembang")
                && av.getShipCity().getSelectedItem().equals("Cilacap")) {
            av.getCheckDistance().setText("355");
        }
        if (av.getPickCity().getSelectedItem().equals("Cilacap")
                && av.getShipCity().getSelectedItem().equals("Madiun")
                || av.getPickCity().getSelectedItem().equals("Madiun")
                && av.getShipCity().getSelectedItem().equals("Cilacap")) {
            av.getCheckDistance().setText("375");
        }
        if (av.getPickCity().getSelectedItem().equals("Cilacap")
                && av.getShipCity().getSelectedItem().equals("Kediri")
                || av.getPickCity().getSelectedItem().equals("Kediri")
                && av.getShipCity().getSelectedItem().equals("Cilacap")) {
            av.getCheckDistance().setText("437");
        }
        if (av.getPickCity().getSelectedItem().equals("Cilacap")
                && av.getShipCity().getSelectedItem().equals("Surabaya")
                || av.getPickCity().getSelectedItem().equals("Surabaya")
                && av.getShipCity().getSelectedItem().equals("Cilacap")) {
            av.getCheckDistance().setText("523");
        }
        if (av.getPickCity().getSelectedItem().equals("Cilacap")
                && av.getShipCity().getSelectedItem().equals("Malang")
                || av.getPickCity().getSelectedItem().equals("Malang")
                && av.getShipCity().getSelectedItem().equals("Cilacap")) {
            av.getCheckDistance().setText("560");
        }
        if (av.getPickCity().getSelectedItem().equals("Cilacap")
                && av.getShipCity().getSelectedItem().equals("Probolinggo")
                || av.getPickCity().getSelectedItem().equals("Probolinggo")
                && av.getShipCity().getSelectedItem().equals("Cilacap")) {
            av.getCheckDistance().setText("598");
        }
        if (av.getPickCity().getSelectedItem().equals("Cilacap")
                && av.getShipCity().getSelectedItem().equals("Bondowoso")
                || av.getPickCity().getSelectedItem().equals("Bondowoso")
                && av.getShipCity().getSelectedItem().equals("Cilacap")) {
            av.getCheckDistance().setText("671");
        }
        if (av.getPickCity().getSelectedItem().equals("Cilacap")
                && av.getShipCity().getSelectedItem().equals("Jember")
                || av.getPickCity().getSelectedItem().equals("Jember")
                && av.getShipCity().getSelectedItem().equals("Cilacap")) {
            av.getCheckDistance().setText("695");
        }
        if (av.getPickCity().getSelectedItem().equals("Cilacap")
                && av.getShipCity().getSelectedItem().equals("Banyuwangi")
                || av.getPickCity().getSelectedItem().equals("Banyuwangi")
                && av.getShipCity().getSelectedItem().equals("Cilacap")) {
            av.getCheckDistance().setText("768");
        }
        if (av.getPickCity().getSelectedItem().equals("Cilacap")
                && av.getShipCity().getSelectedItem().equals("Merak")
                || av.getPickCity().getSelectedItem().equals("Merak")
                && av.getShipCity().getSelectedItem().equals("Cilacap")) {
            av.getCheckDistance().setText("487");
        }
        if (av.getPickCity().getSelectedItem().equals("Pekalongan")
                && av.getShipCity().getSelectedItem().equals("Wonosobo")
                || av.getPickCity().getSelectedItem().equals("Wonosobo")
                && av.getShipCity().getSelectedItem().equals("Pekalongan")) {
            av.getCheckDistance().setText("134");
        }
        if (av.getPickCity().getSelectedItem().equals("Pekalongan")
                && av.getShipCity().getSelectedItem().equals("Purworejo")
                || av.getPickCity().getSelectedItem().equals("Purworejo")
                && av.getShipCity().getSelectedItem().equals("Pekalongan")) {
            av.getCheckDistance().setText("170");
        }
        if (av.getPickCity().getSelectedItem().equals("Pekalongan")
                && av.getShipCity().getSelectedItem().equals("Semarang")
                || av.getPickCity().getSelectedItem().equals("Semarang")
                && av.getShipCity().getSelectedItem().equals("Pekalongan")) {
            av.getCheckDistance().setText("101");
        }
        if (av.getPickCity().getSelectedItem().equals("Pekalongan")
                && av.getShipCity().getSelectedItem().equals("Magelang")
                || av.getPickCity().getSelectedItem().equals("Magelang")
                && av.getShipCity().getSelectedItem().equals("Pekalongan")) {
            av.getCheckDistance().setText("138");
        }
        if (av.getPickCity().getSelectedItem().equals("Pekalongan")
                && av.getShipCity().getSelectedItem().equals("Yogyakarta")
                || av.getPickCity().getSelectedItem().equals("Yogyakarta")
                && av.getShipCity().getSelectedItem().equals("Pekalongan")) {
            av.getCheckDistance().setText("181");
        }
        if (av.getPickCity().getSelectedItem().equals("Pekalongan")
                && av.getShipCity().getSelectedItem().equals("Solo")
                || av.getPickCity().getSelectedItem().equals("Solo")
                && av.getShipCity().getSelectedItem().equals("Pekalongan")) {
            av.getCheckDistance().setText("201");
        }
        if (av.getPickCity().getSelectedItem().equals("Pekalongan")
                && av.getShipCity().getSelectedItem().equals("Rembang")
                || av.getPickCity().getSelectedItem().equals("Rembang")
                && av.getShipCity().getSelectedItem().equals("Pekalongan")) {
            av.getCheckDistance().setText("210");
        }
        if (av.getPickCity().getSelectedItem().equals("Pekalongan")
                && av.getShipCity().getSelectedItem().equals("Madiun")
                || av.getPickCity().getSelectedItem().equals("Madiun")
                && av.getShipCity().getSelectedItem().equals("Pekalongan")) {
            av.getCheckDistance().setText("315");
        }
        if (av.getPickCity().getSelectedItem().equals("Pekalongan")
                && av.getShipCity().getSelectedItem().equals("Kediri")
                || av.getPickCity().getSelectedItem().equals("Kediri")
                && av.getShipCity().getSelectedItem().equals("Pekalongan")) {
            av.getCheckDistance().setText("378");
        }
        if (av.getPickCity().getSelectedItem().equals("Pekalongan")
                && av.getShipCity().getSelectedItem().equals("Surabaya")
                || av.getPickCity().getSelectedItem().equals("Surabaya")
                && av.getShipCity().getSelectedItem().equals("Pekalongan")) {
            av.getCheckDistance().setText("409");
        }
        if (av.getPickCity().getSelectedItem().equals("Pekalongan")
                && av.getShipCity().getSelectedItem().equals("Malang")
                || av.getPickCity().getSelectedItem().equals("Malang")
                && av.getShipCity().getSelectedItem().equals("Pekalongan")) {
            av.getCheckDistance().setText("499");
        }
        if (av.getPickCity().getSelectedItem().equals("Pekalongan")
                && av.getShipCity().getSelectedItem().equals("Probolinggo")
                || av.getPickCity().getSelectedItem().equals("Probolinggo")
                && av.getShipCity().getSelectedItem().equals("Pekalongan")) {
            av.getCheckDistance().setText("508");
        }
        if (av.getPickCity().getSelectedItem().equals("Pekalongan")
                && av.getShipCity().getSelectedItem().equals("Bondowoso")
                || av.getPickCity().getSelectedItem().equals("Bondowoso")
                && av.getShipCity().getSelectedItem().equals("Pekalongan")) {
            av.getCheckDistance().setText("601");
        }
        if (av.getPickCity().getSelectedItem().equals("Pekalongan")
                && av.getShipCity().getSelectedItem().equals("Jember")
                || av.getPickCity().getSelectedItem().equals("Jember")
                && av.getShipCity().getSelectedItem().equals("Pekalongan")) {
            av.getCheckDistance().setText("607");
        }
        if (av.getPickCity().getSelectedItem().equals("Pekalongan")
                && av.getShipCity().getSelectedItem().equals("Banyuwangi")
                || av.getPickCity().getSelectedItem().equals("Banyuwangi")
                && av.getShipCity().getSelectedItem().equals("Pekalongan")) {
            av.getCheckDistance().setText("698");
        }
        if (av.getPickCity().getSelectedItem().equals("Pekalongan")
                && av.getShipCity().getSelectedItem().equals("Merak")
                || av.getPickCity().getSelectedItem().equals("Merak")
                && av.getShipCity().getSelectedItem().equals("Pekalongan")) {
            av.getCheckDistance().setText("469");
        }
        if (av.getPickCity().getSelectedItem().equals("Wonosobo")
                && av.getShipCity().getSelectedItem().equals("Purworejo")
                || av.getPickCity().getSelectedItem().equals("Purworejo")
                && av.getShipCity().getSelectedItem().equals("Wonosobo")) {
            av.getCheckDistance().setText("54");
        }
        if (av.getPickCity().getSelectedItem().equals("Wonosobo")
                && av.getShipCity().getSelectedItem().equals("Semarang")
                || av.getPickCity().getSelectedItem().equals("Semarang")
                && av.getShipCity().getSelectedItem().equals("Wonosobo")) {
            av.getCheckDistance().setText("119");
        }
        if (av.getPickCity().getSelectedItem().equals("Wonosobo")
                && av.getShipCity().getSelectedItem().equals("Magelang")
                || av.getPickCity().getSelectedItem().equals("Magelang")
                && av.getShipCity().getSelectedItem().equals("Wonosobo")) {
            av.getCheckDistance().setText("64");
        }
        if (av.getPickCity().getSelectedItem().equals("Wonosobo")
                && av.getShipCity().getSelectedItem().equals("Yogyakarta")
                || av.getPickCity().getSelectedItem().equals("Yogyakarta")
                && av.getShipCity().getSelectedItem().equals("Wonosobo")) {
            av.getCheckDistance().setText("107");
        }
        if (av.getPickCity().getSelectedItem().equals("Wonosobo")
                && av.getShipCity().getSelectedItem().equals("Solo")
                || av.getPickCity().getSelectedItem().equals("Solo")
                && av.getShipCity().getSelectedItem().equals("Wonosobo")) {
            av.getCheckDistance().setText("151");
        }
        if (av.getPickCity().getSelectedItem().equals("Wonosobo")
                && av.getShipCity().getSelectedItem().equals("Rembang")
                || av.getPickCity().getSelectedItem().equals("Rembang")
                && av.getShipCity().getSelectedItem().equals("Wonosobo")) {
            av.getCheckDistance().setText("228");
        }
        if (av.getPickCity().getSelectedItem().equals("Wonosobo")
                && av.getShipCity().getSelectedItem().equals("Madiun")
                || av.getPickCity().getSelectedItem().equals("Madiun")
                && av.getShipCity().getSelectedItem().equals("Wonosobo")) {
            av.getCheckDistance().setText("265");
        }
        if (av.getPickCity().getSelectedItem().equals("Wonosobo")
                && av.getShipCity().getSelectedItem().equals("Kediri")
                || av.getPickCity().getSelectedItem().equals("Kediri")
                && av.getShipCity().getSelectedItem().equals("Wonosobo")) {
            av.getCheckDistance().setText("326");
        }
        if (av.getPickCity().getSelectedItem().equals("Wonosobo")
                && av.getShipCity().getSelectedItem().equals("Surabaya")
                || av.getPickCity().getSelectedItem().equals("Surabaya")
                && av.getShipCity().getSelectedItem().equals("Wonosobo")) {
            av.getCheckDistance().setText("434");
        }
        if (av.getPickCity().getSelectedItem().equals("Wonosobo")
                && av.getShipCity().getSelectedItem().equals("Malang")
                || av.getPickCity().getSelectedItem().equals("Malang")
                && av.getShipCity().getSelectedItem().equals("Wonosobo")) {
            av.getCheckDistance().setText("449");
        }
        if (av.getPickCity().getSelectedItem().equals("Wonosobo")
                && av.getShipCity().getSelectedItem().equals("Probolinggo")
                || av.getPickCity().getSelectedItem().equals("Probolinggo")
                && av.getShipCity().getSelectedItem().equals("Wonosobo")) {
            av.getCheckDistance().setText("487");
        }
        if (av.getPickCity().getSelectedItem().equals("Wonosobo")
                && av.getShipCity().getSelectedItem().equals("Bondowoso")
                || av.getPickCity().getSelectedItem().equals("Bondowoso")
                && av.getShipCity().getSelectedItem().equals("Wonosobo")) {
            av.getCheckDistance().setText("560");
        }
        if (av.getPickCity().getSelectedItem().equals("Wonosobo")
                && av.getShipCity().getSelectedItem().equals("Jember")
                || av.getPickCity().getSelectedItem().equals("Jember")
                && av.getShipCity().getSelectedItem().equals("Wonosobo")) {
            av.getCheckDistance().setText("565");
        }
        if (av.getPickCity().getSelectedItem().equals("Wonosobo")
                && av.getShipCity().getSelectedItem().equals("Banyuwangi")
                || av.getPickCity().getSelectedItem().equals("Banyuwangi")
                && av.getShipCity().getSelectedItem().equals("Wonosobo")) {
            av.getCheckDistance().setText("578");
        }
        if (av.getPickCity().getSelectedItem().equals("Wonosobo")
                && av.getShipCity().getSelectedItem().equals("Merak")
                || av.getPickCity().getSelectedItem().equals("Merak")
                && av.getShipCity().getSelectedItem().equals("Wonosobo")) {
            av.getCheckDistance().setText("575");
        }
        if (av.getPickCity().getSelectedItem().equals("Purworejo")
                && av.getShipCity().getSelectedItem().equals("Semarang")
                || av.getPickCity().getSelectedItem().equals("Semarang")
                && av.getShipCity().getSelectedItem().equals("Purworejo")) {
            av.getCheckDistance().setText("119");
        }
        if (av.getPickCity().getSelectedItem().equals("Purworejo")
                && av.getShipCity().getSelectedItem().equals("Magelang")
                || av.getPickCity().getSelectedItem().equals("Magelang")
                && av.getShipCity().getSelectedItem().equals("Purworejo")) {
            av.getCheckDistance().setText("44");
        }
        if (av.getPickCity().getSelectedItem().equals("Purworejo")
                && av.getShipCity().getSelectedItem().equals("Yogyakarta")
                || av.getPickCity().getSelectedItem().equals("Yogyakarta")
                && av.getShipCity().getSelectedItem().equals("Purworejo")) {
            av.getCheckDistance().setText("66");
        }
        if (av.getPickCity().getSelectedItem().equals("Purworejo")
                && av.getShipCity().getSelectedItem().equals("Solo")
                || av.getPickCity().getSelectedItem().equals("Solo")
                && av.getShipCity().getSelectedItem().equals("Purworejo")) {
            av.getCheckDistance().setText("131");
        }
        if (av.getPickCity().getSelectedItem().equals("Purworejo")
                && av.getShipCity().getSelectedItem().equals("Rembang")
                || av.getPickCity().getSelectedItem().equals("Rembang")
                && av.getShipCity().getSelectedItem().equals("Purworejo")) {
            av.getCheckDistance().setText("228");
        }
        if (av.getPickCity().getSelectedItem().equals("Purworejo")
                && av.getShipCity().getSelectedItem().equals("Madiun")
                || av.getPickCity().getSelectedItem().equals("Madiun")
                && av.getShipCity().getSelectedItem().equals("Purworejo")) {
            av.getCheckDistance().setText("245");
        }
        if (av.getPickCity().getSelectedItem().equals("Purworejo")
                && av.getShipCity().getSelectedItem().equals("Kediri")
                || av.getPickCity().getSelectedItem().equals("Kediri")
                && av.getShipCity().getSelectedItem().equals("Purworejo")) {
            av.getCheckDistance().setText("306");
        }
        if (av.getPickCity().getSelectedItem().equals("Purworejo")
                && av.getShipCity().getSelectedItem().equals("Surabaya")
                || av.getPickCity().getSelectedItem().equals("Surabaya")
                && av.getShipCity().getSelectedItem().equals("Purworejo")) {
            av.getCheckDistance().setText("393");
        }
        if (av.getPickCity().getSelectedItem().equals("Purworejo")
                && av.getShipCity().getSelectedItem().equals("Malang")
                || av.getPickCity().getSelectedItem().equals("Malang")
                && av.getShipCity().getSelectedItem().equals("Purworejo")) {
            av.getCheckDistance().setText("429");
        }
        if (av.getPickCity().getSelectedItem().equals("Purworejo")
                && av.getShipCity().getSelectedItem().equals("Probolinggo")
                || av.getPickCity().getSelectedItem().equals("Probolinggo")
                && av.getShipCity().getSelectedItem().equals("Purworejo")) {
            av.getCheckDistance().setText("445");
        }
        if (av.getPickCity().getSelectedItem().equals("Purworejo")
                && av.getShipCity().getSelectedItem().equals("Bondowoso")
                || av.getPickCity().getSelectedItem().equals("Bondowoso")
                && av.getShipCity().getSelectedItem().equals("Purworejo")) {
            av.getCheckDistance().setText("540");
        }
        if (av.getPickCity().getSelectedItem().equals("Purworejo")
                && av.getShipCity().getSelectedItem().equals("Jember")
                || av.getPickCity().getSelectedItem().equals("Jember")
                && av.getShipCity().getSelectedItem().equals("Purworejo")) {
            av.getCheckDistance().setText("545");
        }
        if (av.getPickCity().getSelectedItem().equals("Purworejo")
                && av.getShipCity().getSelectedItem().equals("Banyuwangi")
                || av.getPickCity().getSelectedItem().equals("Banyuwangi")
                && av.getShipCity().getSelectedItem().equals("Purworejo")) {
            av.getCheckDistance().setText("637");
        }
        if (av.getPickCity().getSelectedItem().equals("Purworejo")
                && av.getShipCity().getSelectedItem().equals("Merak")
                || av.getPickCity().getSelectedItem().equals("Merak")
                && av.getShipCity().getSelectedItem().equals("Purworejo")) {
            av.getCheckDistance().setText("661");
        }
        if (av.getPickCity().getSelectedItem().equals("Semarang")
                && av.getShipCity().getSelectedItem().equals("Magelang")
                || av.getPickCity().getSelectedItem().equals("Magelang")
                && av.getShipCity().getSelectedItem().equals("Semarang")) {
            av.getCheckDistance().setText("75");
        }
        if (av.getPickCity().getSelectedItem().equals("Semarang")
                && av.getShipCity().getSelectedItem().equals("Yogyakarta")
                || av.getPickCity().getSelectedItem().equals("Yogyakarta")
                && av.getShipCity().getSelectedItem().equals("Semarang")) {
            av.getCheckDistance().setText("118");
        }
        if (av.getPickCity().getSelectedItem().equals("Semarang")
                && av.getShipCity().getSelectedItem().equals("Solo")
                || av.getPickCity().getSelectedItem().equals("Solo")
                && av.getShipCity().getSelectedItem().equals("Semarang")) {
            av.getCheckDistance().setText("100");
        }
        if (av.getPickCity().getSelectedItem().equals("Semarang")
                && av.getShipCity().getSelectedItem().equals("Rembang")
                || av.getPickCity().getSelectedItem().equals("Rembang")
                && av.getShipCity().getSelectedItem().equals("Semarang")) {
            av.getCheckDistance().setText("109");
        }
        if (av.getPickCity().getSelectedItem().equals("Semarang")
                && av.getShipCity().getSelectedItem().equals("Madiun")
                || av.getPickCity().getSelectedItem().equals("Madiun")
                && av.getShipCity().getSelectedItem().equals("Semarang")) {
            av.getCheckDistance().setText("214");
        }
        if (av.getPickCity().getSelectedItem().equals("Semarang")
                && av.getShipCity().getSelectedItem().equals("Kediri")
                || av.getPickCity().getSelectedItem().equals("Kediri")
                && av.getShipCity().getSelectedItem().equals("Semarang")) {
            av.getCheckDistance().setText("295");
        }
        if (av.getPickCity().getSelectedItem().equals("Semarang")
                && av.getShipCity().getSelectedItem().equals("Surabaya")
                || av.getPickCity().getSelectedItem().equals("Surabaya")
                && av.getShipCity().getSelectedItem().equals("Semarang")) {
            av.getCheckDistance().setText("308");
        }
        if (av.getPickCity().getSelectedItem().equals("Semarang")
                && av.getShipCity().getSelectedItem().equals("Malang")
                || av.getPickCity().getSelectedItem().equals("Malang")
                && av.getShipCity().getSelectedItem().equals("Semarang")) {
            av.getCheckDistance().setText("397");
        }
        if (av.getPickCity().getSelectedItem().equals("Semarang")
                && av.getShipCity().getSelectedItem().equals("Probolinggo")
                || av.getPickCity().getSelectedItem().equals("Probolinggo")
                && av.getShipCity().getSelectedItem().equals("Semarang")) {
            av.getCheckDistance().setText("408");
        }
        if (av.getPickCity().getSelectedItem().equals("Semarang")
                && av.getShipCity().getSelectedItem().equals("Bondowoso")
                || av.getPickCity().getSelectedItem().equals("Bondowoso")
                && av.getShipCity().getSelectedItem().equals("Semarang")) {
            av.getCheckDistance().setText("500");
        }
        if (av.getPickCity().getSelectedItem().equals("Semarang")
                && av.getShipCity().getSelectedItem().equals("Jember")
                || av.getPickCity().getSelectedItem().equals("Jember")
                && av.getShipCity().getSelectedItem().equals("Semarang")) {
            av.getCheckDistance().setText("506");
        }
        if (av.getPickCity().getSelectedItem().equals("Semarang")
                && av.getShipCity().getSelectedItem().equals("Banyuwangi")
                || av.getPickCity().getSelectedItem().equals("Banyuwangi")
                && av.getShipCity().getSelectedItem().equals("Semarang")) {
            av.getCheckDistance().setText("597");
        }
        if (av.getPickCity().getSelectedItem().equals("Semarang")
                && av.getShipCity().getSelectedItem().equals("Merak")
                || av.getPickCity().getSelectedItem().equals("Merak")
                && av.getShipCity().getSelectedItem().equals("Semarang")) {
            av.getCheckDistance().setText("550");
        }
        if (av.getPickCity().getSelectedItem().equals("Magelang")
                && av.getShipCity().getSelectedItem().equals("Yogyakarta")
                || av.getPickCity().getSelectedItem().equals("Yogyakarta")
                && av.getShipCity().getSelectedItem().equals("Magelang")) {
            av.getCheckDistance().setText("44");
        }
        if (av.getPickCity().getSelectedItem().equals("Magelang")
                && av.getShipCity().getSelectedItem().equals("Solo")
                || av.getPickCity().getSelectedItem().equals("Solo")
                && av.getShipCity().getSelectedItem().equals("Magelang")) {
            av.getCheckDistance().setText("108");
        }
        if (av.getPickCity().getSelectedItem().equals("Magelang")
                && av.getShipCity().getSelectedItem().equals("Rembang")
                || av.getPickCity().getSelectedItem().equals("Rembang")
                && av.getShipCity().getSelectedItem().equals("Magelang")) {
            av.getCheckDistance().setText("184");
        }
        if (av.getPickCity().getSelectedItem().equals("Magelang")
                && av.getShipCity().getSelectedItem().equals("Madiun")
                || av.getPickCity().getSelectedItem().equals("Madiun")
                && av.getShipCity().getSelectedItem().equals("Magelang")) {
            av.getCheckDistance().setText("221");
        }
        if (av.getPickCity().getSelectedItem().equals("Magelang")
                && av.getShipCity().getSelectedItem().equals("Kediri")
                || av.getPickCity().getSelectedItem().equals("Kediri")
                && av.getShipCity().getSelectedItem().equals("Magelang")) {
            av.getCheckDistance().setText("83");
        }
        if (av.getPickCity().getSelectedItem().equals("Magelang")
                && av.getShipCity().getSelectedItem().equals("Surabaya")
                || av.getPickCity().getSelectedItem().equals("Surabaya")
                && av.getShipCity().getSelectedItem().equals("Magelang")) {
            av.getCheckDistance().setText("370");
        }
        if (av.getPickCity().getSelectedItem().equals("Magelang")
                && av.getShipCity().getSelectedItem().equals("Malang")
                || av.getPickCity().getSelectedItem().equals("Malang")
                && av.getShipCity().getSelectedItem().equals("Magelang")) {
            av.getCheckDistance().setText("406");
        }
        if (av.getPickCity().getSelectedItem().equals("Magelang")
                && av.getShipCity().getSelectedItem().equals("Probolinggo")
                || av.getPickCity().getSelectedItem().equals("Probolinggo")
                && av.getShipCity().getSelectedItem().equals("Magelang")) {
            av.getCheckDistance().setText("443");
        }
        if (av.getPickCity().getSelectedItem().equals("Magelang")
                && av.getShipCity().getSelectedItem().equals("Bondowoso")
                || av.getPickCity().getSelectedItem().equals("Bondowoso")
                && av.getShipCity().getSelectedItem().equals("Magelang")) {
            av.getCheckDistance().setText("517");
        }
        if (av.getPickCity().getSelectedItem().equals("Magelang")
                && av.getShipCity().getSelectedItem().equals("Jember")
                || av.getPickCity().getSelectedItem().equals("Jember")
                && av.getShipCity().getSelectedItem().equals("Magelang")) {
            av.getCheckDistance().setText("522");
        }
        if (av.getPickCity().getSelectedItem().equals("Magelang")
                && av.getShipCity().getSelectedItem().equals("Banyuwangi")
                || av.getPickCity().getSelectedItem().equals("Banyuwangi")
                && av.getShipCity().getSelectedItem().equals("Magelang")) {
            av.getCheckDistance().setText("633");
        }
        if (av.getPickCity().getSelectedItem().equals("Magelang")
                && av.getShipCity().getSelectedItem().equals("Merak")
                || av.getPickCity().getSelectedItem().equals("Merak")
                && av.getShipCity().getSelectedItem().equals("Magelang")) {
            av.getCheckDistance().setText("618");
        }
        if (av.getPickCity().getSelectedItem().equals("Yogyakarta")
                && av.getShipCity().getSelectedItem().equals("Solo")
                || av.getPickCity().getSelectedItem().equals("Solo")
                && av.getShipCity().getSelectedItem().equals("Yogyakarta")) {
            av.getCheckDistance().setText("65");
        }
        if (av.getPickCity().getSelectedItem().equals("Yogyakarta")
                && av.getShipCity().getSelectedItem().equals("Rembang")
                || av.getPickCity().getSelectedItem().equals("Rembang")
                && av.getShipCity().getSelectedItem().equals("Yogyakarta")) {
            av.getCheckDistance().setText("212");
        }
        if (av.getPickCity().getSelectedItem().equals("Yogyakarta")
                && av.getShipCity().getSelectedItem().equals("Madiun")
                || av.getPickCity().getSelectedItem().equals("Madiun")
                && av.getShipCity().getSelectedItem().equals("Yogyakarta")) {
            av.getCheckDistance().setText("179");
        }
        if (av.getPickCity().getSelectedItem().equals("Yogyakarta")
                && av.getShipCity().getSelectedItem().equals("Kediri")
                || av.getPickCity().getSelectedItem().equals("Kediri")
                && av.getShipCity().getSelectedItem().equals("Yogyakarta")) {
            av.getCheckDistance().setText("246");
        }
        if (av.getPickCity().getSelectedItem().equals("Yogyakarta")
                && av.getShipCity().getSelectedItem().equals("Surabaya")
                || av.getPickCity().getSelectedItem().equals("Surabaya")
                && av.getShipCity().getSelectedItem().equals("Yogyakarta")) {
            av.getCheckDistance().setText("427");
        }
        if (av.getPickCity().getSelectedItem().equals("Yogyakarta")
                && av.getShipCity().getSelectedItem().equals("Malang")
                || av.getPickCity().getSelectedItem().equals("Malang")
                && av.getShipCity().getSelectedItem().equals("Yogyakarta")) {
            av.getCheckDistance().setText("363");
        }
        if (av.getPickCity().getSelectedItem().equals("Yogyakarta")
                && av.getShipCity().getSelectedItem().equals("Probolinggo")
                || av.getPickCity().getSelectedItem().equals("Probolinggo")
                && av.getShipCity().getSelectedItem().equals("Yogyakarta")) {
            av.getCheckDistance().setText("381");
        }
        if (av.getPickCity().getSelectedItem().equals("Yogyakarta")
                && av.getShipCity().getSelectedItem().equals("Bondowoso")
                || av.getPickCity().getSelectedItem().equals("Bondowoso")
                && av.getShipCity().getSelectedItem().equals("Yogyakarta")) {
            av.getCheckDistance().setText("474");
        }
        if (av.getPickCity().getSelectedItem().equals("Yogyakarta")
                && av.getShipCity().getSelectedItem().equals("Jember")
                || av.getPickCity().getSelectedItem().equals("Jember")
                && av.getShipCity().getSelectedItem().equals("Yogyakarta")) {
            av.getCheckDistance().setText("479");
        }
        if (av.getPickCity().getSelectedItem().equals("Yogyakarta")
                && av.getShipCity().getSelectedItem().equals("Banyuwangi")
                || av.getPickCity().getSelectedItem().equals("Banyuwangi")
                && av.getShipCity().getSelectedItem().equals("Yogyakarta")) {
            av.getCheckDistance().setText("571");
        }
        if (av.getPickCity().getSelectedItem().equals("Yogyakarta")
                && av.getShipCity().getSelectedItem().equals("Merak")
                || av.getPickCity().getSelectedItem().equals("Merak")
                && av.getShipCity().getSelectedItem().equals("Yogyakarta")) {
            av.getCheckDistance().setText("670");
        }
        if (av.getPickCity().getSelectedItem().equals("Solo")
                && av.getShipCity().getSelectedItem().equals("Rembang")
                || av.getPickCity().getSelectedItem().equals("Rembang")
                && av.getShipCity().getSelectedItem().equals("Solo")) {
            av.getCheckDistance().setText("147");
        }
        if (av.getPickCity().getSelectedItem().equals("Solo")
                && av.getShipCity().getSelectedItem().equals("Madiun")
                || av.getPickCity().getSelectedItem().equals("Madiun")
                && av.getShipCity().getSelectedItem().equals("Solo")) {
            av.getCheckDistance().setText("114");
        }
        if (av.getPickCity().getSelectedItem().equals("Solo")
                && av.getShipCity().getSelectedItem().equals("Kediri")
                || av.getPickCity().getSelectedItem().equals("Kediri")
                && av.getShipCity().getSelectedItem().equals("Solo")) {
            av.getCheckDistance().setText("175");
        }
        if (av.getPickCity().getSelectedItem().equals("Solo")
                && av.getShipCity().getSelectedItem().equals("Surabaya")
                || av.getPickCity().getSelectedItem().equals("Surabaya")
                && av.getShipCity().getSelectedItem().equals("Solo")) {
            av.getCheckDistance().setText("282");
        }
        if (av.getPickCity().getSelectedItem().equals("Solo")
                && av.getShipCity().getSelectedItem().equals("Malang")
                || av.getPickCity().getSelectedItem().equals("Malang")
                && av.getShipCity().getSelectedItem().equals("Solo")) {
            av.getCheckDistance().setText("298");
        }
        if (av.getPickCity().getSelectedItem().equals("Solo")
                && av.getShipCity().getSelectedItem().equals("Probolinggo")
                || av.getPickCity().getSelectedItem().equals("Probolinggo")
                && av.getShipCity().getSelectedItem().equals("Solo")) {
            av.getCheckDistance().setText("316");
        }
        if (av.getPickCity().getSelectedItem().equals("Solo")
                && av.getShipCity().getSelectedItem().equals("Bondowoso")
                || av.getPickCity().getSelectedItem().equals("Bondowoso")
                && av.getShipCity().getSelectedItem().equals("Solo")) {
            av.getCheckDistance().setText("409");
        }
        if (av.getPickCity().getSelectedItem().equals("Solo")
                && av.getShipCity().getSelectedItem().equals("Jember")
                || av.getPickCity().getSelectedItem().equals("Jember")
                && av.getShipCity().getSelectedItem().equals("Solo")) {
            av.getCheckDistance().setText("414");
        }
        if (av.getPickCity().getSelectedItem().equals("Solo")
                && av.getShipCity().getSelectedItem().equals("Banyuwangi")
                || av.getPickCity().getSelectedItem().equals("Banyuwangi")
                && av.getShipCity().getSelectedItem().equals("Solo")) {
            av.getCheckDistance().setText("506");
        }
        if (av.getPickCity().getSelectedItem().equals("Solo")
                && av.getShipCity().getSelectedItem().equals("Merak")
                || av.getPickCity().getSelectedItem().equals("Merak")
                && av.getShipCity().getSelectedItem().equals("Solo")) {
            av.getCheckDistance().setText("646");
        }
        if (av.getPickCity().getSelectedItem().equals("Rembang")
                && av.getShipCity().getSelectedItem().equals("Madiun")
                || av.getPickCity().getSelectedItem().equals("Madiun")
                && av.getShipCity().getSelectedItem().equals("Rembang")) {
            av.getCheckDistance().setText("145");
        }
        if (av.getPickCity().getSelectedItem().equals("Rembang")
                && av.getShipCity().getSelectedItem().equals("Kediri")
                || av.getPickCity().getSelectedItem().equals("Kediri")
                && av.getShipCity().getSelectedItem().equals("Rembang")) {
            av.getCheckDistance().setText("206");
        }
        if (av.getPickCity().getSelectedItem().equals("Rembang")
                && av.getShipCity().getSelectedItem().equals("Surabaya")
                || av.getPickCity().getSelectedItem().equals("Surabaya")
                && av.getShipCity().getSelectedItem().equals("Rembang")) {
            av.getCheckDistance().setText("201");
        }
        if (av.getPickCity().getSelectedItem().equals("Rembang")
                && av.getShipCity().getSelectedItem().equals("Malang")
                || av.getPickCity().getSelectedItem().equals("Malang")
                && av.getShipCity().getSelectedItem().equals("Rembang")) {
            av.getCheckDistance().setText("275");
        }
        if (av.getPickCity().getSelectedItem().equals("Rembang")
                && av.getShipCity().getSelectedItem().equals("Probolinggo")
                || av.getPickCity().getSelectedItem().equals("Probolinggo")
                && av.getShipCity().getSelectedItem().equals("Rembang")) {
            av.getCheckDistance().setText("306");
        }
        if (av.getPickCity().getSelectedItem().equals("Rembang")
                && av.getShipCity().getSelectedItem().equals("Bondowoso")
                || av.getPickCity().getSelectedItem().equals("Bondowoso")
                && av.getShipCity().getSelectedItem().equals("Rembang")) {
            av.getCheckDistance().setText("393");
        }
        if (av.getPickCity().getSelectedItem().equals("Rembang")
                && av.getShipCity().getSelectedItem().equals("Jember")
                || av.getPickCity().getSelectedItem().equals("Jember")
                && av.getShipCity().getSelectedItem().equals("Rembang")) {
            av.getCheckDistance().setText("399");
        }
        if (av.getPickCity().getSelectedItem().equals("Rembang")
                && av.getShipCity().getSelectedItem().equals("Banyuwangi")
                || av.getPickCity().getSelectedItem().equals("Banyuwangi")
                && av.getShipCity().getSelectedItem().equals("Rembang")) {
            av.getCheckDistance().setText("490");
        }
        if (av.getPickCity().getSelectedItem().equals("Rembang")
                && av.getShipCity().getSelectedItem().equals("Merak")
                || av.getPickCity().getSelectedItem().equals("Merak")
                && av.getShipCity().getSelectedItem().equals("Rembang")) {
            av.getCheckDistance().setText("666");
        }
        if (av.getPickCity().getSelectedItem().equals("Madiun")
                && av.getShipCity().getSelectedItem().equals("Kediri")
                || av.getPickCity().getSelectedItem().equals("Kediri")
                && av.getShipCity().getSelectedItem().equals("Madiun")) {
            av.getCheckDistance().setText("81");
        }
        if (av.getPickCity().getSelectedItem().equals("Madiun")
                && av.getShipCity().getSelectedItem().equals("Surabaya")
                || av.getPickCity().getSelectedItem().equals("Surabaya")
                && av.getShipCity().getSelectedItem().equals("Madiun")) {
            av.getCheckDistance().setText("169");
        }
        if (av.getPickCity().getSelectedItem().equals("Madiun")
                && av.getShipCity().getSelectedItem().equals("Malang")
                || av.getPickCity().getSelectedItem().equals("Malang")
                && av.getShipCity().getSelectedItem().equals("Madiun")) {
            av.getCheckDistance().setText("184");
        }
        if (av.getPickCity().getSelectedItem().equals("Madiun")
                && av.getShipCity().getSelectedItem().equals("Probolinggo")
                || av.getPickCity().getSelectedItem().equals("Probolinggo")
                && av.getShipCity().getSelectedItem().equals("Madiun")) {
            av.getCheckDistance().setText("222");
        }
        if (av.getPickCity().getSelectedItem().equals("Madiun")
                && av.getShipCity().getSelectedItem().equals("Bondowoso")
                || av.getPickCity().getSelectedItem().equals("Bondowoso")
                && av.getShipCity().getSelectedItem().equals("Madiun")) {
            av.getCheckDistance().setText("315");
        }
        if (av.getPickCity().getSelectedItem().equals("Madiun")
                && av.getShipCity().getSelectedItem().equals("Jember")
                || av.getPickCity().getSelectedItem().equals("Jember")
                && av.getShipCity().getSelectedItem().equals("Madiun")) {
            av.getCheckDistance().setText("320");
        }
        if (av.getPickCity().getSelectedItem().equals("Madiun")
                && av.getShipCity().getSelectedItem().equals("Banyuwangi")
                || av.getPickCity().getSelectedItem().equals("Banyuwangi")
                && av.getShipCity().getSelectedItem().equals("Madiun")) {
            av.getCheckDistance().setText("412");
        }
        if (av.getPickCity().getSelectedItem().equals("Madiun")
                && av.getShipCity().getSelectedItem().equals("Merak")
                || av.getPickCity().getSelectedItem().equals("Merak")
                && av.getShipCity().getSelectedItem().equals("Madiun")) {
            av.getCheckDistance().setText("744");
        }
        if (av.getPickCity().getSelectedItem().equals("Kediri")
                && av.getShipCity().getSelectedItem().equals("Surabaya")
                || av.getPickCity().getSelectedItem().equals("Surabaya")
                && av.getShipCity().getSelectedItem().equals("Kediri")) {
            av.getCheckDistance().setText("124");
        }
        if (av.getPickCity().getSelectedItem().equals("Kediri")
                && av.getShipCity().getSelectedItem().equals("Malang")
                || av.getPickCity().getSelectedItem().equals("Malang")
                && av.getShipCity().getSelectedItem().equals("Kediri")) {
            av.getCheckDistance().setText("103");
        }
        if (av.getPickCity().getSelectedItem().equals("Kediri")
                && av.getShipCity().getSelectedItem().equals("Probolinggo")
                || av.getPickCity().getSelectedItem().equals("Probolinggo")
                && av.getShipCity().getSelectedItem().equals("Kediri")) {
            av.getCheckDistance().setText("177");
        }
        if (av.getPickCity().getSelectedItem().equals("Kediri")
                && av.getShipCity().getSelectedItem().equals("Bondowoso")
                || av.getPickCity().getSelectedItem().equals("Bondowoso")
                && av.getShipCity().getSelectedItem().equals("Kediri")) {
            av.getCheckDistance().setText("270");
        }
        if (av.getPickCity().getSelectedItem().equals("Kediri")
                && av.getShipCity().getSelectedItem().equals("Jember")
                || av.getPickCity().getSelectedItem().equals("Jember")
                && av.getShipCity().getSelectedItem().equals("Kediri")) {
            av.getCheckDistance().setText("275");
        }
        if (av.getPickCity().getSelectedItem().equals("Kediri")
                && av.getShipCity().getSelectedItem().equals("Banyuwangi")
                || av.getPickCity().getSelectedItem().equals("Banyuwangi")
                && av.getShipCity().getSelectedItem().equals("Kediri")) {
            av.getCheckDistance().setText("367");
        }
        if (av.getPickCity().getSelectedItem().equals("Kediri")
                && av.getShipCity().getSelectedItem().equals("Merak")
                || av.getPickCity().getSelectedItem().equals("Merak")
                && av.getShipCity().getSelectedItem().equals("Kediri")) {
            av.getCheckDistance().setText("816");
        }
        if (av.getPickCity().getSelectedItem().equals("Surabaya")
                && av.getShipCity().getSelectedItem().equals("Malang")
                || av.getPickCity().getSelectedItem().equals("Malang")
                && av.getShipCity().getSelectedItem().equals("Surabaya")) {
            av.getCheckDistance().setText("89");
        }
        if (av.getPickCity().getSelectedItem().equals("Surabaya")
                && av.getShipCity().getSelectedItem().equals("Probolinggo")
                || av.getPickCity().getSelectedItem().equals("Probolinggo")
                && av.getShipCity().getSelectedItem().equals("Surabaya")) {
            av.getCheckDistance().setText("99");
        }
        if (av.getPickCity().getSelectedItem().equals("Surabaya")
                && av.getShipCity().getSelectedItem().equals("Bondowoso")
                || av.getPickCity().getSelectedItem().equals("Bondowoso")
                && av.getShipCity().getSelectedItem().equals("Surabaya")) {
            av.getCheckDistance().setText("192");
        }
        if (av.getPickCity().getSelectedItem().equals("Surabaya")
                && av.getShipCity().getSelectedItem().equals("Jember")
                || av.getPickCity().getSelectedItem().equals("Jember")
                && av.getShipCity().getSelectedItem().equals("Surabaya")) {
            av.getCheckDistance().setText("198");
        }
        if (av.getPickCity().getSelectedItem().equals("Surabaya")
                && av.getShipCity().getSelectedItem().equals("Banyuwangi")
                || av.getPickCity().getSelectedItem().equals("Banyuwangi")
                && av.getShipCity().getSelectedItem().equals("Surabaya")) {
            av.getCheckDistance().setText("289");
        }
        if (av.getPickCity().getSelectedItem().equals("Surabaya")
                && av.getShipCity().getSelectedItem().equals("Merak")
                || av.getPickCity().getSelectedItem().equals("Merak")
                && av.getShipCity().getSelectedItem().equals("Surabaya")) {
            av.getCheckDistance().setText("887");
        }
        if (av.getPickCity().getSelectedItem().equals("Malang")
                && av.getShipCity().getSelectedItem().equals("Probolinggo")
                || av.getPickCity().getSelectedItem().equals("Probolinggo")
                && av.getShipCity().getSelectedItem().equals("Malang")) {
            av.getCheckDistance().setText("91");
        }
        if (av.getPickCity().getSelectedItem().equals("Malang")
                && av.getShipCity().getSelectedItem().equals("Bondowoso")
                || av.getPickCity().getSelectedItem().equals("Bondowoso")
                && av.getShipCity().getSelectedItem().equals("Malang")) {
            av.getCheckDistance().setText("184");
        }
        if (av.getPickCity().getSelectedItem().equals("Malang")
                && av.getShipCity().getSelectedItem().equals("Jember")
                || av.getPickCity().getSelectedItem().equals("Jember")
                && av.getShipCity().getSelectedItem().equals("Malang")) {
            av.getCheckDistance().setText("185");
        }
        if (av.getPickCity().getSelectedItem().equals("Malang")
                && av.getShipCity().getSelectedItem().equals("Banyuwangi")
                || av.getPickCity().getSelectedItem().equals("Banyuwangi")
                && av.getShipCity().getSelectedItem().equals("Malang")) {
            av.getCheckDistance().setText("281");
        }
        if (av.getPickCity().getSelectedItem().equals("Malang")
                && av.getShipCity().getSelectedItem().equals("Merak")
                || av.getPickCity().getSelectedItem().equals("Merak")
                && av.getShipCity().getSelectedItem().equals("Malang")) {
            av.getCheckDistance().setText("957");
        }
        if (av.getPickCity().getSelectedItem().equals("Probolinggo")
                && av.getShipCity().getSelectedItem().equals("Bondowoso")
                || av.getPickCity().getSelectedItem().equals("Bondowoso")
                && av.getShipCity().getSelectedItem().equals("Probolinggo")) {
            av.getCheckDistance().setText("93");
        }
        if (av.getPickCity().getSelectedItem().equals("Probolinggo")
                && av.getShipCity().getSelectedItem().equals("Jember")
                || av.getPickCity().getSelectedItem().equals("Jember")
                && av.getShipCity().getSelectedItem().equals("Probolinggo")) {
            av.getCheckDistance().setText("99");
        }
        if (av.getPickCity().getSelectedItem().equals("Probolinggo")
                && av.getShipCity().getSelectedItem().equals("Banyuwangi")
                || av.getPickCity().getSelectedItem().equals("Banyuwangi")
                && av.getShipCity().getSelectedItem().equals("Probolinggo")) {
            av.getCheckDistance().setText("190");
        }
        if (av.getPickCity().getSelectedItem().equals("Probolinggo")
                && av.getShipCity().getSelectedItem().equals("Merak")
                || av.getPickCity().getSelectedItem().equals("Merak")
                && av.getShipCity().getSelectedItem().equals("Probolinggo")) {
            av.getCheckDistance().setText("969");
        }
        if (av.getPickCity().getSelectedItem().equals("Bondowoso")
                && av.getShipCity().getSelectedItem().equals("Jember")
                || av.getPickCity().getSelectedItem().equals("Jember")
                && av.getShipCity().getSelectedItem().equals("Bondowoso")) {
            av.getCheckDistance().setText("31");
        }
        if (av.getPickCity().getSelectedItem().equals("Bondowoso")
                && av.getShipCity().getSelectedItem().equals("Banyuwangi")
                || av.getPickCity().getSelectedItem().equals("Banyuwangi")
                && av.getShipCity().getSelectedItem().equals("Bondowoso")) {
            av.getCheckDistance().setText("128");
        }
        if (av.getPickCity().getSelectedItem().equals("Bondowoso")
                && av.getShipCity().getSelectedItem().equals("Merak")
                || av.getPickCity().getSelectedItem().equals("Merak")
                && av.getShipCity().getSelectedItem().equals("Bondowoso")) {
            av.getCheckDistance().setText("1071");
        }
        if (av.getPickCity().getSelectedItem().equals("Jember")
                && av.getShipCity().getSelectedItem().equals("Banyuwangi")
                || av.getPickCity().getSelectedItem().equals("Banyuwangi")
                && av.getShipCity().getSelectedItem().equals("Jember")) {
            av.getCheckDistance().setText("104");
        }
        if (av.getPickCity().getSelectedItem().equals("Jember")
                && av.getShipCity().getSelectedItem().equals("Merak")
                || av.getPickCity().getSelectedItem().equals("Merak")
                && av.getShipCity().getSelectedItem().equals("Jember")) {
            av.getCheckDistance().setText("1059");
        }
    }

    public void insertJobOrder() {
        JobOrderModel j = new JobOrderModel();
//        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        j.setOrder_number(ijov.getOrder_Number().getText());
        j.setDate(ijov.getOrder_Date().getDate());
        j.setDelivery_date(ijov.getDelivery_Date().getDate());
        j.setSender_name(ijov.getSender_Name1().getText());
        j.setSender_comp_name(ijov.getSender_Comp_Name1().getText());
        j.setSender_comp_address(ijov.getSender_Comp_Address1().getText());
        j.setSender_phone(ijov.getSender_Phone1().getText());
        j.setReceiver_name(ijov.getReceiver_Name().getText());
        j.setReceiver_comp_name(ijov.getReceiver_Comp_Name().getText());
        j.setReceiver_comp_address(ijov.getReceiver_Comp_Address().getText());
        j.setReceiver_phone(ijov.getReceiver_Phone().getText());
        j.setGoods_cat(ijov.getGoods_Cat().getSelectedItem().toString());
        j.setDimension(ijov.getP().getText() + " X " + ijov.getL().getText() + " X "
                + ijov.getT().getText() + " " + ijov.getDimension_Unit().getSelectedItem().toString());
        j.setWeight(ijov.getWeight().getText() + " " + ijov.getWeight_Unit().getSelectedItem().toString());
        j.setDescription(ijov.getDescription().getText());
        j.setTruck_cat(ijov.getTruck_Cat().getSelectedItem().toString());
        j.setCaro_type(ijov.getCaro_Type().getSelectedItem().toString());
        j.setDistance(ijov.getDistance().getText());
        j.setStatus("Waiting");
        j.setAdmin_id(av.getHome_ID().getText());
        joi.insert(j);
        JOptionPane.showMessageDialog(ijov, "Job Order Successfully Saved.");

    }

    public void insertJobOrder1() {
        JobOrderModel j = new JobOrderModel();
//        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        j.setOrder_number(ijov.getOrder_Number().getText());
        j.setDate(ijov.getOrder_Date().getDate());
        j.setDelivery_date(ijov.getDelivery_Date().getDate());
        j.setSender_name(ijov.getSender_Name1().getText());
        j.setSender_comp_name(ijov.getSender_Comp_Name1().getText());
        j.setPick_region(ijov.getPick_City().getSelectedItem().toString());
        j.setSender_comp_address(ijov.getSender_Comp_Address1().getText());
        j.setSender_phone(ijov.getSender_Phone1().getText());
        j.setReceiver_name(ijov.getReceiver_Name().getText());
        j.setReceiver_comp_name(ijov.getReceiver_Comp_Name().getText());
        j.setShip_region(ijov.getShip_City().getSelectedItem().toString());
        j.setReceiver_comp_address(ijov.getReceiver_Comp_Address().getText());
        j.setReceiver_phone(ijov.getReceiver_Phone().getText());
        j.setGoods_cat(ijov.getGoods_Cat().getSelectedItem().toString());
        j.setDimension(ijov.getP().getText() + " X " + ijov.getL().getText() + " X "
                + ijov.getT().getText() + " " + ijov.getDimension_Unit().getSelectedItem().toString());
        j.setWeight(ijov.getWeight().getText() + " " + ijov.getWeight_Unit().getSelectedItem().toString());
        j.setDescription(ijov.getDescription().getText());
        j.setTruck_cat(ijov.getTruck_Cat().getSelectedItem().toString());
        j.setCaro_type(ijov.getCaro_Type().getSelectedItem().toString());
        j.setDistance(ijov.getDistance().getText());
        j.setStatus("Waiting");
        j.setAdmin_id(av.getHome_ID().getText());
        joi.insert(j);
        JOptionPane.showMessageDialog(ijov, "Job Order Successfully Saved.");
        resetInputJobOrder();
    }

    public void updateJobOrder() {
        JobOrderModel j = new JobOrderModel();
//        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        j.setOrder_number(ejov.getOrder_Number().getText());
        j.setDate(ejov.getOrder_Date().getDate());
        j.setDelivery_date(ejov.getDelivery_Date().getDate());
        j.setSender_name(ejov.getSender_Name1().getText());
        j.setSender_comp_name(ejov.getSender_Comp_Name1().getText());
        j.setSender_comp_address(ejov.getSender_Comp_Address1().getText());
        j.setSender_phone(ejov.getSender_Phone1().getText());
        j.setPick_region(ejov.getPick_City().getSelectedItem().toString());
        j.setReceiver_name(ejov.getReceiver_Name().getText());
        j.setReceiver_comp_name(ejov.getReceiver_Comp_Name().getText());
        j.setReceiver_comp_address(ejov.getReceiver_Comp_Address().getText());
        j.setReceiver_phone(ejov.getReceiver_Phone().getText());
        j.setShip_region(ejov.getShip_City().getSelectedItem().toString());
        j.setGoods_cat(ejov.getGoods_Cat().getSelectedItem().toString());
        j.setDimension(ejov.getP().getText());
        j.setWeight(ejov.getWeight().getText());
        j.setDescription(ejov.getDescription().getText());
        j.setTruck_cat(ejov.getTruck_Cat().getSelectedItem().toString());
        j.setCaro_type(ejov.getCaro_Type().getSelectedItem().toString());
        j.setDistance(ejov.getDistance().getText());
        j.setAdmin_id(av.getHome_ID().getText());
//        j.setStatus("Waiting");
        joi.update(j);
        JOptionPane.showMessageDialog(ijov, "Job Order Successfully Updated.");
    }

    public void setStatus(AdminView111 av) {
        JobOrderModel jom = new JobOrderModel();
        jom.setOrder_number(av.getOrder_Number().getText());
        jom.setStatus("On Progress");
        joi.setStatus(jom);
        JOptionPane.showMessageDialog(av, "Status On Progress");
    }

    public void setStatusCompleted(AdminView111 av) {
        JobOrderModel jom = new JobOrderModel();
        jom.setStatus("Completed");
        jom.setOrder_number(av.getTabel_JO().getValueAt(av.getTabel_JO().getSelectedRow(), 0).toString());
        joi.setStatus(jom);
        JOptionPane.showMessageDialog(av, av.getTabel_JO().getValueAt(av.getTabel_JO().getSelectedRow(), 0).toString() + " Status Completed");
    }

    public void setAuto_ordernumber() {
        jom = joi.auto_ordernumber();
        ijov.getOrder_Number().setText(jom.getOrder_number());
    }

    public void jobordercount() {
        jom = joi.jobordercount();
        av.getJobOrderCount().setText(jom.getJumlah());
    }

    public void deleteJobOrder() {
        String Order_Number = av.getTabel_JO().getValueAt(av.getTabel_JO().getSelectedRow(), 0).toString();
        joi.delete(Order_Number);
    }

    public void isiTabelJobOrder() {
        jotm = joi.getALL();
        JOTabelModel jotabelmodel = new JOTabelModel(jotm);
        av.getTabel_JO().setModel(jotabelmodel);
        int a;
        for (a = 0; a < av.getTabel_JO().getColumnCount(); a++) {
            av.getTabel_JO().getColumnModel().getColumn(a).setCellRenderer(new WordWrapCellRenderer());
        }
    }

    public void getListJobOrder() {
        jolm = joi.getJobOrderList();
        JOListModel jolistmodel = new JOListModel(jolm);
        jov.getTabel_Job_Order().setModel(jolistmodel);
    }

    public void getListJobOrderInvoice() {
        jlim = joi.getJobOrderInvoice();
        JOListInvoiceModel jolim = new JOListInvoiceModel(jlim);
        jov.getTabel_Job_Order().setModel(jolim);
    }

    public void isiTableCariOrderNumber(AdminView111 av) {
        jotm = joi.getALL();
        jotm = joi.getCariOrder_Number(av.getSearch_Order_Number().getText());
        JOTabelModel jotabelmodel = new JOTabelModel(jotm);
        av.getTabel_JO().setModel(jotabelmodel);
        int a;
        for (a = 0; a < av.getTabel_JO().getColumnCount(); a++) {
            av.getTabel_JO().getColumnModel().getColumn(a).setCellRenderer(new WordWrapCellRenderer());
        }
    }

    public String a = null;

    public void getForDeliveryOrder(AdminView111 av) {
        jom = joi.getForDeliveryOrder(av.getTabel_JO().getValueAt(av.getTabel_JO().getSelectedRow(), 0).toString());
        av.getOrder_Number().setText(jom.getOrder_number());
        av.getDelivery_Date().setText(jom.getDelivery_date().toString());
        av.getGoods_Cat().setText(jom.getGoods_cat());
        av.getDescription().setText(jom.getDescription());
        av.getSender_Name().setText(jom.getSender_name());
        av.getReceiver_Name().setText(jom.getReceiver_name());
    }

    public void getJobOrderDetail(EditJobOrderView ejov) {
        jom = joi.getJobOrderDetail(av.getTabel_JO().getValueAt(av.getTabel_JO().getSelectedRow(), 0).toString());
//        ejov.getSearch_Cust_ID().setText(jom.get);
        ejov.getOrder_Number().setText(jom.getOrder_number());
        ejov.getDelivery_Date().setDate(jom.getDelivery_date());
        ejov.getOrder_Date().setDate(jom.getDate());
        ejov.getSender_Name1().setText(jom.getSender_name());
        ejov.getSender_Comp_Name1().setText(jom.getSender_comp_name());
        ejov.getPick_City().setSelectedItem(jom.getPick_region());
        ejov.getSender_Comp_Address1().setText(jom.getSender_comp_address());
        ejov.getSender_Phone1().setText(jom.getSender_phone());
        ejov.getReceiver_Name().setText(jom.getReceiver_name());
        ejov.getReceiver_Comp_Name().setText(jom.getReceiver_comp_name());
        ejov.getShip_City().setSelectedItem(jom.getShip_region());
        ejov.getReceiver_Comp_Address().setText(jom.getReceiver_comp_address());
        ejov.getReceiver_Phone().setText(jom.getReceiver_phone());
        ejov.getTruck_Cat().setSelectedItem(jom.getTruck_cat());
        ejov.getCaro_Type().setSelectedItem(jom.getCaro_type());
        ejov.getGoods_Cat().setSelectedItem(jom.getGoods_cat());
        ejov.getP().setText(jom.getDimension());
        ejov.getWeight().setText(jom.getWeight());
        ejov.getDescription().setText(jom.getDescription());
    }

    public void getOrderedTruck(TruckView tv) {
        jom = joi.getOrderedTruck(tv.getOrder_Number().getText());
        tv.getTruck_Cat().setText(jom.getTruck_cat());
        tv.getCaro_Type().setText(jom.getCaro_type());
        tv.getCari_Truck_Cat().setText(jom.getTruck_cat());
    }

    public void getForDO_Preview(AdminView111 av) {
        jom = joi.getForDOPreview(av.getOrder_Number().getText());
        dop.getOrder_Number().setText(jom.getOrder_number());
        dop.getDelivery_Date().setText(jom.getDelivery_date().toString());
        dop.getOrder_Number().setText(jom.getOrder_number());
        dop.getOrder_Number().setText(jom.getOrder_number());
    }

    public void getForInvoice(AdminView111 av) {
        jom = joi.getForInvoice(av.getTabel_JO().getValueAt(av.getTabel_JO().getSelectedRow(), 0).toString());
        av.getOrder_Number1().setText(jom.getOrder_number());
//        av.getSender_Comp_Name().setText(jom.getSender_comp_name());
        av.getPick_Place().setText(jom.getSender_comp_address());
        av.getDestination().setText(jom.getReceiver_comp_address());
        av.getTruck_Cat1().setText(jom.getTruck_cat());
        av.getCaro_Type1().setText(jom.getCaro_type());
        av.getDistance().setText(jom.getDistance());
    }
//
//    public void addJobOrder() {
//        JobOrderModel d = new JobOrderModel();
//        ijov.getOrder_Number().setEditable(true);
//        ijov.getOrder_Date().setEnabled(true);
//        ijov.getDelivery_Date().setEnabled(true);
//        ijov.getCaro_Size().setEditable(true);
//        ijov.getCaro_Type().setEditable(true);
//        ijov.getDimension_Unit().setEditable(true);
//        ijov.getDescription().setEditable(true);
//        ijov.getP().setEditable(true);
//        ijov.getL().setEditable(true);
//        ijov.getT().setEditable(true);
//        ijov.getOrder_Number().setEditable(true);
//        ijov.getOrder_Number().setEditable(true);
//        ijov.getOrder_Number().setEditable(true);
//        ijov.getOrder_Number().setEditable(true);
//        ijov.getOrder_Number().setEditable(true);
//        ijov.getOrder_Number().setEditable(true);
//        ijov.getOrder_Number().setEditable(true);
//    }
}
