package com.project.GUI.Forms.ThongKe;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.util.List;

import com.project.models.thietbi;
import com.project.models.thanhvien;

import com.github.lgooddatepicker.components.DateTimePicker;
import com.project.BLL.thanhvienBLL;
import com.project.BLL.thietbiBLL;
import com.project.BLL.xulyBLL;
import com.project.GUI.Components.FormLabel;
import com.project.GUI.Components.FormPanel;
import com.project.GUI.Components.Buttons.ButtonSearch;
import com.project.GUI.Components.Table.TableCustom;
import com.project.GUI.Components.TextFields.InputField;
import com.project.GUI.GlobalVariables.Colors;
import com.project.models.xuly;
import javax.swing.table.DefaultTableCellRenderer;

public class TKThanhVien extends JPanel {
    private static JTable table;
    public TKThanhVien(){
        initCompontent();
    }
    private void initCompontent(){
        pnlHeader = new FormPanel();
        lbThoiGian = new FormLabel("Thời gian");
        lbKhoa = new FormLabel("Khoa");
        lbNganh = new FormLabel("Ngành");
        inputKhoa = new InputField(7);
        inputNganh = new InputField(7);
        btnSearch = new ButtonSearch();
        pnlMain = new JPanel();
        soLieu = new FormLabel("");
        dpkThoiGian = new DateTimePicker();

        setLayout(new BorderLayout());

        // add content to pnlHeader
        pnlHeader.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));
        pnlHeader.add(lbThoiGian);
        pnlHeader.add(dpkThoiGian);
        pnlHeader.add(lbKhoa);
        pnlHeader.add(inputKhoa);
        pnlHeader.add(lbNganh);
        pnlHeader.add(inputNganh);
        pnlHeader.add(btnSearch);

        //add pnlHeader to main
        add(pnlHeader, BorderLayout.NORTH);

        //add content to pnlMain
        pnlMain.setLayout(new BorderLayout());

        int userQuantity = 0;
        soLieu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        soLieu.setText("Có " + userQuantity +" thành viên");

        //add userQuantity to pnlMain
        pnlMain.add(soLieu, BorderLayout.NORTH);

        table = new JTable();
        // Create header for table
        table.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] { "Mã TV",
                        "Họ tên",
                        "Khoa",
                        "Ngành",
                        "Số điện thoại",
                }){
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != getColumnCount() - 5 && column != getColumnCount() - 1;
            }}
        );updateMemberFromList();
        // Add data for table
       
        // Create panel to contain table
        JScrollPane pnlTable = new JScrollPane();
        pnlTable.setBorder(new EmptyBorder(10, 10, 10, 10));
        pnlTable.setViewportView(table);
        pnlTable.setBackground(Colors.bgColor);
        pnlMain.add(pnlTable, BorderLayout.CENTER);
        TableCustom.apply(pnlTable, TableCustom.TableType.MULTI_LINE);
        //add pnlmain to pnl main
        add(pnlMain, BorderLayout.CENTER);

    }  

    private JPanel pnlHeader;
    private JLabel lbThoiGian;
    private JLabel lbKhoa;
    private JLabel lbNganh;
    private JTextField inputKhoa;
    private JTextField inputNganh;
    private JButton btnSearch;
    private JPanel pnlMain;
    private JLabel soLieu;
    private DateTimePicker dpkThoiGian;
    
    public void updateMemberFromList() {
        thanhvienBLL.getInstance().refresh();
        DefaultTableModel model_table = (DefaultTableModel) table.getModel();
        model_table.setRowCount(0);

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);

        for (thanhvien member : thanhvienBLL.getInstance().getAllModels()) {
            model_table.addRow(new Object[] {
                    member.getMaTV(),
                    member.getHoTen(),
                    member.getKhoa(),
                    member.getNganh(),
                    member.getSdt()
            });
        }
    }
}
