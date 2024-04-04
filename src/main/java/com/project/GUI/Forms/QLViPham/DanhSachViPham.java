package com.project.GUI.Forms.QLViPham;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.table.DefaultTableModel;

import com.project.GUI.Components.FormLabel;
import com.project.GUI.Components.FormPanel;
import com.project.GUI.Components.Buttons.ButtonAdd;
import com.project.GUI.Components.Buttons.ButtonDel;
import com.project.GUI.Components.Buttons.ButtonDelAll;
import com.project.GUI.Components.Buttons.ButtonExcel;
import com.project.GUI.Components.Buttons.ButtonRefresh;
import com.project.GUI.Components.Buttons.ButtonSearch;
import com.project.GUI.Components.Table.TableCustom;
import com.project.GUI.Components.TextFields.InputField;
import com.project.GUI.Components.TextFields.SearchField;
import com.project.GUI.GlobalVariables.Colors;
import com.project.GUI.GlobalVariables.Fonts;

public class DanhSachViPham extends JPanel {
    public DanhSachViPham() {
        initCompontent();
    }

    public void initCompontent() {
        pnlMain = new FormPanel();
        lbTitle = new FormLabel("Danh sách vi phạm");
        pnlButtons = new FormPanel();
        btnAdd = new ButtonAdd();
        btnDel = new ButtonDel();
        btnRefresh = new ButtonRefresh();
        btnExcel = new ButtonExcel();
        pnlSearch = new FormPanel();
        inputSearch = new SearchField(20);
        btnDelAll = new ButtonDelAll();
        btnSearch = new ButtonSearch();

        lbTitle.setFont(Fonts.headerFont);
        lbTitle.setForeground(Color.BLACK);

        GridBagConstraints gbc;

        setLayout(new BorderLayout());

        pnlMain.setLayout(new GridBagLayout());
        pnlMain.add(lbTitle);

        gbc = new GridBagConstraints();

        // add button to pnlButtons

        btnAdd.addActionListener(actionAdd);
        pnlButtons.add(btnAdd);
        pnlButtons.add(btnDel);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        pnlMain.add(pnlButtons, gbc);

        //pnlSearch
        pnlSearch.add(inputSearch);
        pnlSearch.add(btnSearch);
        pnlSearch.add(btnRefresh);

        // add pnlSearch to pnlMain
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        pnlMain.add(pnlSearch, gbc);

        add(pnlMain, BorderLayout.NORTH);

        JTable table = new JTable();
        // Create header for table
        table.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] { "Mã VP",
                        "Mã TV",
                        "Họ tên",
                        "Hình thức XL",
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
        pnlTable.setPreferredSize(new Dimension(700, 400));
        pnlTable.setViewportView(table);
        pnlTable.setBackground(Colors.bgColor);

        TableCustom.apply(pnlTable, TableCustom.TableType.MULTI_LINE);

        add(pnlTable, BorderLayout.CENTER);
    }

    private JPanel pnlMain;
    private FormLabel lbTitle;
    private JPanel pnlButtons;
    private JPanel pnlSearch;
    private JButton btnAdd;
    private JButton btnDel;
    private JButton btnRefresh;
    private JButton btnExcel;
    private InputField inputSearch;
    private JButton btnDelAll;
    private JButton btnSearch;

//    public static void main(String[] args) {
//        JFrame frame = new JFrame();
//        frame.add(new DanhSachViPham());
//        frame.setVisible(true);
//    }

    public ActionListener actionAdd = e -> {
        ThaoTac thaoTac = new ThaoTac();
        thaoTac.setVisible(true);
    };
}
