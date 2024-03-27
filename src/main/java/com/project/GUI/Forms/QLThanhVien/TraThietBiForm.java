package com.project.GUI.Forms.QLThanhVien;

import com.project.GUI.Components.Buttons.*;
import com.project.GUI.GlobalVariables.Colors;
import com.project.GUI.Components.FormLabel;
import com.project.GUI.Components.FormPanel;
import com.project.GUI.Components.Table;
import com.project.GUI.Components.TextFields.SearchField;
import com.project.GUI.GlobalVariables.Fonts;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TraThietBiForm extends JFrame {
    private Point mouseDownCompCoords;
    public TraThietBiForm() {
//        Add Content into JFrame
        add(initCompontent());

        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        // Add mouse listener
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mouseDownCompCoords = e.getPoint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                mouseDownCompCoords = null;
            }
        });

        // Add mouse motion listener
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Point currCoords = e.getLocationOnScreen();
                setLocation(currCoords.x - mouseDownCompCoords.x, currCoords.y - mouseDownCompCoords.y);
            }
        });
    }

    private JPanel initCompontent() {
        JPanel root = new FormPanel();
        root.setLayout(new BorderLayout());

        root.setBorder(BorderFactory.createLineBorder(Colors.primaryColor, 5));

//        Create Header
        JPanel pnlHeader = new FormPanel();
        JLabel lbHeader = new FormLabel("Trả thiết bị");
        lbHeader.setFont(Fonts.headerFont);
        lbHeader.setForeground(Color.BLACK);
        pnlHeader.add(lbHeader);

//        Create label danh sach thiet bi
        JLabel lbDSThietBi = new FormLabel("Danh sách thiết bị đang mượn");

//        Create table for showing data
        JTable table = new Table();
//        Create header for table
        table.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {"Mã TB",
                        "Tên TB",
                        "Mô tả",
                })
        );
//        Add data for table
        DefaultTableModel model_table = (DefaultTableModel) table.getModel();
        for (int i = 0; i < 10; i++) {
            model_table.addRow(new Object[]{
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

//        Create panel to contain show data
        JPanel pnlDS = new FormPanel();
        pnlDS.setLayout(new GridBagLayout());
//        Add constraints
        GridBagConstraints constraints = new GridBagConstraints();
//          Add padding bottom 10px
        constraints.insets = new Insets(0, 0, 10, 0);

//        Row 0
        constraints.gridx = 0;
        constraints.gridy = 0;
        pnlDS.add(lbDSThietBi, constraints);

//        Row 1
        constraints.gridy = 1;
//        Create search input, button search, button refresh
        JTextField searchInput = new SearchField(20);
        JButton btnSearch = new ButtonSearch();
        JButton btnRefresh = new ButtonRefresh();

//        Create panel to contain search input
        JPanel pnlSearch = new FormPanel();
        pnlSearch.add(searchInput);
        pnlSearch.add(btnSearch);
        pnlSearch.add(btnRefresh);
//        Add panel search into pnlDS
        pnlDS.add(pnlSearch, constraints);

//        Row 2
        constraints.gridy = 2;
        pnlDS.add(pnlTable, constraints);


//        Create button
        JButton btnSave = new ButtonSave();
        JButton btnCancel = new ButtonCancel();

//        Create panel to contain button
        JPanel pnlBtn = new FormPanel();
        pnlBtn.add(btnSave);
        pnlBtn.add(btnCancel);

//        Add all panel to this panel
        root.add(pnlHeader, BorderLayout.PAGE_START);
        root.add(pnlDS, BorderLayout.CENTER);
        root.add(pnlBtn, BorderLayout.SOUTH);

//        Add event listener for cancel button
        btnCancel.addActionListener(e -> {
            dispose();
        });
        return root;
    }

    public static void main(String[] args) {
        new TraThietBiForm();
    }
}
