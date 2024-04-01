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
import com.project.GUI.Components.TextFields.InputField;
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
        lbId = new FormLabel("ID");
        lbName = new FormLabel("Tên thiết bị");
        lbDecription = new FormLabel("Mô tả");
        inputId = new InputField(7);
        inputName = new InputField(7);
        inputDecription = new InputField(7);
        pnlSearch = new FormPanel();
        lbSearch = new FormLabel("Tìm kiếm");
        inputSearch = new InputField(10);
        btnDelAll = new ButtonDelAll();
        btnSearch = new ButtonSearch();

        GridBagConstraints gbc;

        setLayout(new BorderLayout());

        pnlMain.setLayout(new GridBagLayout());

        //add Title
        pnlMain.add(lbTitle);

        //setLayout pnlInfor
        pnlInfor.setPreferredSize(new Dimension(400, 200));
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[] {0, 10, 0};
        gridBagLayout.rowHeights = new int[] {0, 10, 0, 10, 0};
        pnlInfor.setLayout(gridBagLayout);

        //add lb and txt to pnlInfor
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

        //add pnlInfor to pnlMain
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        pnlMain.add(pnlInfor, gbc);

        //pnlButtons
        pnlButtons.setPreferredSize(new Dimension(800, 60));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;

        //add button to pnlbuttons
        pnlButtons.add(btnRefresh);
        pnlButtons.add(btnExcel);

        btnAdd.addActionListener(actionAdd);
        pnlButtons.add(btnAdd);

        btnEdit.addActionListener(actionEdit);
        pnlButtons.add(btnEdit);
        pnlButtons.add(btnDel);
        pnlButtons.add(btnDelAll);
        
        //add pnlButtons to pnlMain
        pnlMain.add(pnlButtons, gbc);

        //pnlSearch
        pnlSearch.setPreferredSize(new Dimension(800, 60));
        pnlSearch.add(lbSearch);
        pnlSearch.add(inputSearch);
        pnlSearch.add(btnSearch);

        //add pnlSearch to pnlMain
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
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
    private FormLabel lbTitle;
    private JPanel pnlInfor;
    private JPanel pnlButtons;
    private JPanel pnlSearch;
    private JButton btnAdd;
    private JButton btnDel;
    private JButton btnRefresh;
    private JButton btnExcel;
    private JButton btnEdit;
    private FormLabel lbId;
    private FormLabel lbName;
    private FormLabel lbDecription;
    private InputField inputId;
    private InputField inputName;
    private InputField inputDecription;
    private FormLabel lbSearch;
    private InputField inputSearch;
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
