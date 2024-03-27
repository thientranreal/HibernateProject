package com.project.GUI.Forms.QLThanhVien;

import com.project.GUI.GlobalVariables.Colors;
import com.project.GUI.Components.Buttons.*;
import com.project.GUI.Components.FormLabel;
import com.project.GUI.Components.FormPanel;
import com.project.GUI.Components.Table;
import com.project.GUI.Components.TextFields.InputField;
import com.project.GUI.Components.TextFields.SearchField;
import com.project.GUI.GlobalVariables.Fonts;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class QLThanhVienPanel extends FormPanel {
    public QLThanhVienPanel() {
//        Add constraints to make button align vertically
        GridBagConstraints constraints = new GridBagConstraints();
//      Add padding bottom 10px
        constraints.insets = new Insets(0, 0, 10, 0);

//        Set GridBagLayout layout
        setLayout(new BorderLayout());

//        Create Header
        JPanel pnlHeader = new FormPanel();
        JLabel lbHeader = new FormLabel("Quản lý thành viên");
        lbHeader.setFont(Fonts.headerFont);
        lbHeader.setForeground(Color.BLACK);
        pnlHeader.add(lbHeader);

//        Create search input, button search, button refresh
        JTextField searchInput = new SearchField(20);
        JButton btnSearch = new ButtonSearch();
        JButton btnRefresh = new ButtonRefresh();

//        Create panel to contain search input
        JPanel pnlSearch = new FormPanel();
        pnlSearch.add(searchInput);
        pnlSearch.add(btnSearch);
        pnlSearch.add(btnRefresh);

//        Create table for showing data
        JTable table = new Table();
//        Create header for table
        table.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {"Mã TV",
                        "Họ Tên",
                        "Khoa",
                        "Ngành",
                        "Số ĐT",
                        "Xử Lý"
                })
        );
//        Add data for table
        DefaultTableModel model_table = (DefaultTableModel) table.getModel();
        for (int i = 0; i < 10; i++) {
            model_table.addRow(new Object[]{
                    "Text",
                    "Text",
                    "Text",
                    "Text",
                    "Text",
                    "Text"
            });
        }

//        Create panel to contain table
        JScrollPane pnlTable = new JScrollPane();
        pnlTable.setBorder(new EmptyBorder(10, 10, 10, 10));
        pnlTable.setViewportView(table);
        pnlTable.setBackground(Colors.bgColor);

//        Create button add, button delete
        JButton btnAdd = new ButtonAdd();
        JButton btnDel = new ButtonDel();
        JButton btnBorrow = new ButtonNormal("Borrow");
        JButton btnReturn = new ButtonNormal("Return");
        JButton btnWarning = new ButtonWarning();
//        Create panel to contain button
        JPanel pnlAction = new FormPanel();
        pnlAction.setLayout(new GridBagLayout());

        int y = 0;
        constraints.gridx = 0;
        constraints.gridy = y++;
        pnlAction.add(btnAdd, constraints);

        constraints.gridy = y++;
        pnlAction.add(btnDel, constraints);

        constraints.gridy = y++;
        pnlAction.add(btnBorrow, constraints);

        constraints.gridy = y++;
        pnlAction.add(btnReturn, constraints);

        constraints.gridy = y;
        pnlAction.add(btnWarning, constraints);

//        Panel check in
        JLabel lbMaTV = new FormLabel("Mã TV");
        JTextField inputMaTV = new InputField(20);
        JButton btnCheckIn = new ButtonNormal("Check in");
        JPanel pnlCheckIn = new FormPanel();
        pnlCheckIn.setLayout(new GridBagLayout());

        constraints.gridx = 0;
        constraints.gridy = 0;
        pnlCheckIn.add(lbMaTV, constraints);

        constraints.gridx = 1;
        pnlCheckIn.add(inputMaTV, constraints);

        constraints.gridy = 1;
        pnlCheckIn.add(btnCheckIn, constraints);

//        Create panel Show Table
        JPanel pnlData = new FormPanel();
        pnlData.setLayout(new GridBagLayout());

        constraints.gridx = 0;
        constraints.gridy = 0;
        pnlData.add(pnlSearch, constraints);
        constraints.gridy = 1;
        pnlData.add(pnlTable, constraints);

//        Add all panel to this panel
        add(pnlHeader, BorderLayout.NORTH);
        add(pnlData, BorderLayout.CENTER);
        add(pnlAction, BorderLayout.EAST);
        add(pnlCheckIn, BorderLayout.WEST);

//        Event listener
//        Btn Add event listener
        btnAdd.addActionListener(e -> {
            new ThemThanhVienForm();
        });

//        Btn Borrow event listener
        btnBorrow.addActionListener(e -> {
            new MuonThietBiForm();
        });

//        Btn Return event listener
        btnReturn.addActionListener(e -> {
            new TraThietBiForm();
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();

        frame.add(new QLThanhVienPanel());

        frame.setVisible(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
