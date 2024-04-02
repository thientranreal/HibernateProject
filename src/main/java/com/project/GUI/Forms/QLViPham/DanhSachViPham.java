package com.project.GUI.Forms.QLViPham;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.table.DefaultTableModel;

import com.project.GUI.Components.FormLabel;
import com.project.GUI.Components.FormPanel;
import com.project.GUI.Components.Buttons.ButtonAdd;
import com.project.GUI.Components.Buttons.ButtonDel;
import com.project.GUI.Components.Buttons.ButtonDelAll;
import com.project.GUI.Components.Buttons.ButtonEdit;
import com.project.GUI.Components.Buttons.ButtonExcel;
import com.project.GUI.Components.Buttons.ButtonRefresh;
import com.project.GUI.Components.Buttons.ButtonSearch;
import com.project.GUI.Components.Table.TableCustom;
import com.project.GUI.Components.TextFields.InputField;
import com.project.GUI.GlobalVariables.Colors;

public class DanhSachViPham extends JPanel {
    public DanhSachViPham() {
        initCompontent();
    }

    public void initCompontent() {
        pnlMain = new FormPanel();
        lbTitle = new FormLabel("Danh sách vi phạm");
        pnlInfor = new FormPanel();
        pnlButtons = new FormPanel();
        btnAdd = new ButtonAdd();
        btnDel = new ButtonDel();
        btnRefresh = new ButtonRefresh();
        btnExcel = new ButtonExcel();
        btnEdit = new ButtonEdit();
        lbMaVP = new FormLabel("Mã VP");
        lbMaTV = new FormLabel("Mã Thành viên");
        lbTenTV = new FormLabel("Tên thành viên");
        lbHinhThuc = new FormLabel("Hình thức");
        lbSoTien = new FormLabel("Số tiền");
        lbNgayXL = new FormLabel("Ngày xử lý");
        lbTrangThai = new FormLabel("Trạng thái");
        inputMaVP = new InputField(7);
        inputMaTV = new InputField(7);
        inputTenTV = new InputField(7);
        inputHinhThuc = new InputField(7);
        inputSoTien = new InputField(7);
        inputNgayXL = new InputField(7);
        inputTrangThai = new InputField(7);
        pnlSearch = new FormPanel();
        lbSearch = new FormLabel("Tìm kiếm");
        inputSearch = new InputField(10);
        btnDelAll = new ButtonDelAll();
        btnSearch = new ButtonSearch();

        GridBagConstraints gbc;

        setLayout(new BorderLayout());

        pnlMain.setLayout(new GridBagLayout());
        pnlMain.add(lbTitle);

        //addlayout to pnlInfor
        pnlInfor.setPreferredSize(new Dimension(400, 200));
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[] { 10, 10, 10, 10, 10, 10 };
        gridBagLayout.rowHeights = new int[] { 10, 10, 10, 10, 10, 10 };
        pnlInfor.setLayout(gridBagLayout);

        //add input + txt to pnlInfor

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        pnlInfor.add(lbMaVP, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        pnlInfor.add(inputMaVP, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        pnlInfor.add(lbMaTV, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        pnlInfor.add(inputMaTV, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        pnlInfor.add(lbHinhThuc, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 4;
        pnlInfor.add(inputHinhThuc, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 2;
        pnlInfor.add(lbTenTV, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 6;
        gbc.gridy = 2;
        pnlInfor.add(inputTenTV, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 4;
        pnlInfor.add(lbSoTien, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 6;
        gbc.gridy = 4;
        pnlInfor.add(inputSoTien, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        pnlInfor.add(lbNgayXL, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 6;
        pnlInfor.add(inputNgayXL, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 6;
        pnlInfor.add(lbTrangThai, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 6;
        gbc.gridy = 6;
        pnlInfor.add(inputTrangThai, gbc);

        // add pnlInfor to pnlMain
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        pnlMain.add(pnlInfor, gbc);

        // add button to pnlButtons

        btnAdd.addActionListener(actionAdd);
        pnlButtons.add(btnAdd);

        btnEdit.addActionListener(actionEdit);
        pnlButtons.add(btnEdit);
        pnlButtons.add(btnDel);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        pnlMain.add(pnlButtons, gbc);

        //pnlSearch
        pnlSearch.add(lbSearch);
        pnlSearch.add(inputSearch);
        pnlSearch.add(btnSearch);

        // add pnlSearch to pnlMain
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        pnlMain.add(pnlSearch, gbc);

        add(pnlMain, BorderLayout.NORTH);

        JTable table = new JTable();
        // Create header for table
        table.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] { "Mã VP",
                        "Mã thàn viên",
                        "Tên thành viên",
                        "Hình thức xử lý",
                        "Số tiền",
                        "Ngày xử lý",
                        "Trạng thái"
                }));
        // Add data for table
        DefaultTableModel model_table = (DefaultTableModel) table.getModel();
        for (int i = 0; i < 20; i++) {
            model_table.addRow(new Object[] {
                    "Text",
                    "Text",
                    "Text",
                    "Text",
                    "T",
                    "T",
                    "T"
            });
        }

        // Create panel to contain table
        JScrollPane pnlTable = new JScrollPane();
        pnlTable.setBorder(new EmptyBorder(10, 10, 10, 10));
        pnlTable.setViewportView(table);
        pnlTable.setBackground(Colors.bgColor);

        TableCustom.apply(pnlTable, TableCustom.TableType.MULTI_LINE);

        add(pnlTable, BorderLayout.CENTER);
    }

    private JPanel pnlMain;
    private FormLabel lbTitle;
    private JPanel pnlInfor;
    private JPanel pnlButtons;
    private JPanel pnlSearch;
    private JButton btnAdd;
    private JButton btnDel;
    private JButton btnRefresh;
    private JButton btnExcel;
    private JButton btnEdit;
    private FormLabel lbMaVP;
    private FormLabel lbMaTV;
    private FormLabel lbTenTV;
    private FormLabel lbHinhThuc;
    private FormLabel lbSoTien;
    private FormLabel lbNgayXL;
    private FormLabel lbTrangThai;
    private InputField inputMaVP;
    private InputField inputMaTV;
    private InputField inputTenTV;
    private InputField inputHinhThuc;
    private InputField inputSoTien;
    private InputField inputNgayXL;
    private InputField inputTrangThai;
    private FormLabel lbSearch;
    private InputField inputSearch;
    private JButton btnDelAll;
    private JButton btnSearch;

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.add(new DanhSachViPham());
        frame.setVisible(true);
    }

    public ActionListener actionAdd = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            ThaoTac thaoTac = new ThaoTac();
            thaoTac.setVisible(true);
        }

    };
    public ActionListener actionEdit = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            ThaoTac thaoTac = new ThaoTac();
            thaoTac.setVisible(true);
        }

    };
}
