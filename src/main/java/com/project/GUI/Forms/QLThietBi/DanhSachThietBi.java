package com.project.GUI.Forms.QLThietBi;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.table.DefaultTableModel;

import com.project.GUI.Components.FormLabel;
import com.project.GUI.Components.FormPanel;
import com.project.GUI.Components.Table;
import com.project.GUI.Components.Buttons.ButtonAdd;
import com.project.GUI.Components.Buttons.ButtonCancel;
import com.project.GUI.Components.Buttons.ButtonDel;
import com.project.GUI.Components.Buttons.ButtonDelAll;
import com.project.GUI.Components.Buttons.ButtonEdit;
import com.project.GUI.Components.Buttons.ButtonExcel;
import com.project.GUI.Components.Buttons.ButtonRefresh;
import com.project.GUI.Components.Buttons.ButtonSave;
import com.project.GUI.Components.Buttons.ButtonSearch;
import com.project.GUI.GlobalVariables.Colors;

public class DanhSachThietBi extends JPanel {
    public DanhSachThietBi() {
        initCompontent();
    }

    public void initCompontent() {
        pnlMain = new FormPanel();
        lbTitle = new FormLabel("Danh sách thiết bị");
        pnlInfor = new FormPanel();
        pnlButtons = new FormPanel();
        btnAdd = new ButtonAdd();
        btnDel = new ButtonDel();
        btnRefresh = new ButtonRefresh();
        btnExcel = new ButtonExcel();
        btnEdit = new ButtonEdit();
        lbId = new JLabel("ID");
        lbName = new JLabel("Tên thiết bị");
        lbDecription = new JLabel("Mô tả");
        inputId = new JTextField();
        inputName = new JTextField();
        inputDecription = new JTextField();
        pnlSearch = new FormPanel();
        lbSearch = new FormLabel("Tìm kiếm");
        inputSearch = new JTextField();
        btnDelAll = new ButtonDelAll();
        btnSearch = new ButtonSearch();

        GridBagConstraints gbc;

        setLayout(new BorderLayout());

        pnlMain.setLayout(new GridBagLayout());

        pnlMain.add(lbTitle);

        pnlInfor.setPreferredSize(new Dimension(400, 200));
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[] {0, 10, 0};
        gridBagLayout.rowHeights = new int[] {0, 10, 0, 10, 0};
        pnlInfor.setLayout(gridBagLayout);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        pnlInfor.add(lbId, gbc);

        inputId.setPreferredSize(new Dimension(200,35));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        pnlInfor.add(inputId, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        pnlInfor.add(lbName, gbc);

        inputName.setPreferredSize(new Dimension(200,35));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        pnlInfor.add(inputName, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        pnlInfor.add(lbDecription, gbc);

        inputDecription.setPreferredSize(new Dimension(200,35));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 4;
        pnlInfor.add(inputDecription, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        pnlMain.add(pnlInfor, gbc);

        pnlButtons.setPreferredSize(new Dimension(800, 60));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;

        pnlButtons.add(btnRefresh);
        pnlButtons.add(btnExcel);

        btnAdd.addActionListener(actionAdd);
        pnlButtons.add(btnAdd);

        btnEdit.addActionListener(actionEdit);
        pnlButtons.add(btnEdit);
        pnlButtons.add(btnDel);
        pnlButtons.add(btnDelAll);
        pnlMain.add(pnlButtons, gbc);

        pnlSearch.setPreferredSize(new Dimension(800, 60));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        pnlSearch.add(lbSearch);

        inputSearch.setPreferredSize(new Dimension(200,35));
        pnlSearch.add(inputSearch);
        pnlSearch.add(btnSearch);
        pnlMain.add(pnlSearch,gbc);

        add(pnlMain, BorderLayout.NORTH);

        JTable table = new Table();
        // Create header for table
        table.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] { "Mã TB",
                        "Tên Thiết Bị",
                        "Mô tả",
                }));
        // Add data for table
        DefaultTableModel model_table = (DefaultTableModel) table.getModel();
        for (int i = 0; i < 20; i++) {
            model_table.addRow(new Object[] {
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

        add(pnlTable, BorderLayout.CENTER);
    }

    private JPanel pnlMain;
    private JLabel lbTitle;
    private JPanel pnlInfor;
    private JPanel pnlButtons;
    private JPanel pnlSearch;
    private JButton btnAdd;
    private JButton btnDel;
    private JButton btnRefresh;
    private JButton btnExcel;
    private JButton btnEdit;
    private JLabel lbId;
    private JLabel lbName;
    private JLabel lbDecription;
    private JTextField inputId;
    private JTextField inputName;
    private JTextField inputDecription;
    private JLabel lbSearch;
    private JTextField inputSearch;
    private JButton btnDelAll;
    private JButton btnSearch;

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.add(new DanhSachThietBi());
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
