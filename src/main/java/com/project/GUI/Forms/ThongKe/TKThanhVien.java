package com.project.GUI.Forms.ThongKe;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.project.GUI.Components.FormLabel;
import com.project.GUI.Components.FormPanel;
import com.project.GUI.Components.Buttons.Button;
import com.project.GUI.Components.Buttons.ButtonSearch;
import com.project.GUI.Components.Table.TableCustom;
import com.project.GUI.Components.TextFields.InputField;
import com.project.GUI.GlobalVariables.Colors;

public class TKThanhVien extends JPanel {
    public TKThanhVien(){
        initCompontent();
    }
    private void initCompontent(){
        pnlHeader = new FormPanel();
        lbThoiGian = new FormLabel("Thời gian");
        lbKhoa = new FormLabel("Khoa");
        lbNganh = new FormLabel("Ngành");
        btnThoiGian = new Button("Date");
        inputKhoa = new InputField(7);
        inputNganh = new InputField(7);
        btnSearch = new ButtonSearch();
        pnlMain = new JPanel();
        soLieu = new FormLabel("");

        setLayout(new BorderLayout());

        // add content to pnlHeader
        pnlHeader.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));
        pnlHeader.add(lbThoiGian);
        pnlHeader.add(btnThoiGian);
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

        JTable table = new JTable();
        // Create header for table
        table.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] { "Mã TV",
                        "Tên thanh viên",
                        "Khoa",
                        "Ngành",
                        "Số điện thoại",
                }));
        // Add data for table
        DefaultTableModel model_table = (DefaultTableModel) table.getModel();
        for (int i = 0; i < 20; i++) {
            model_table.addRow(new Object[] {
                    "Text",
                    "Text",
                    "Text",
                    "Text",
                    "Text"
            });
        }

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
    private JButton btnThoiGian;
    private JLabel lbThoiGian;
    private JLabel lbKhoa;
    private JLabel lbNganh;
    private JTextField inputKhoa;
    private JTextField inputNganh;
    private JButton btnSearch;
    private JPanel pnlMain;
    private JLabel soLieu;
}
