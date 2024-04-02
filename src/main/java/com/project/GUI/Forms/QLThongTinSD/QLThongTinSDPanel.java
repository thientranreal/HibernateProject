package com.project.GUI.Forms.QLThongTinSD;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.project.BLL.thongtinsdBLL;
import com.project.GUI.Components.FormLabel;
import com.project.GUI.Components.FormPanel;
import com.project.GUI.Components.Buttons.ButtonRefresh;
import com.project.GUI.Components.Buttons.ButtonSearch;
import com.project.GUI.Components.Table.TableCustom;
import com.project.GUI.Components.TextFields.SearchField;
import com.project.GUI.GlobalVariables.Colors;
import com.project.GUI.GlobalVariables.Fonts;
import com.project.models.thongtinsd;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.math.BigInteger;

public class QLThongTinSDPanel extends FormPanel {
    private final JTable table;
    private final SearchField searchInput;

    public QLThongTinSDPanel() {
        // Add constraints to make button align vertically
        GridBagConstraints constraints = new GridBagConstraints();
        // Add padding bottom 10px
        constraints.insets = new Insets(0, 0, 10, 0);

        // Set GridBagLayout layout
        setLayout(new BorderLayout());

        // Create Header
        JPanel pnlHeader = new FormPanel();
        JLabel lbHeader = new FormLabel("Quản lý thông tin sử dụng");
        lbHeader.setFont(Fonts.headerFont);
        lbHeader.setForeground(java.awt.Color.BLACK);
        pnlHeader.add(lbHeader);

        // Create search input, button search, button refresh
        searchInput = new SearchField(20);
        JButton btnSearch = new ButtonSearch();
        // add Jcalendar button:
        // JCalendar jCalendar = new JCalendar();
        JButton btnRefresh = new ButtonRefresh();

        // Create panel to contain search input
        JPanel pnlSearch = new FormPanel();
        pnlSearch.add(searchInput);
        pnlSearch.add(btnSearch);
        pnlSearch.add(btnRefresh);
        // pnlSearch.add(jCalendar);

        // Create table for showing data
        table = new JTable();
        // Create header for table
        table.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] { "Mã thông tin",
                        "Mã thành viên",
                        "Mã thiết bị",
                        "Thời gian vào",
                        "Thời gian mượn",
                        "Thời gian trả"
                }) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });

        // Create panel to contain table
        JScrollPane pnlTable = new JScrollPane();
        pnlTable.setBorder(new EmptyBorder(10, 10, 10, 10));
        pnlTable.setViewportView(table);
        pnlTable.setBackground(Colors.bgColor);

        TableCustom.apply(pnlTable, TableCustom.TableType.MULTI_LINE);

        // Create panel Show Table
        JPanel pnlData = new FormPanel();
        pnlData.setLayout(new GridBagLayout());

        constraints.gridx = 0;
        constraints.gridy = 0;
        pnlData.add(pnlSearch, constraints);
        constraints.gridy = 1;
        pnlData.add(pnlTable, constraints);

        // Add all panel to this panel
        add(pnlHeader, BorderLayout.NORTH);
        add(pnlData, BorderLayout.CENTER);
        updateThongtinsdFromList();
        btnRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateThongtinsdFromList();
                clearForm();
            }
        });

        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchResult = searchInput.getText();
                List<thongtinsd> thongtinsdList = thongtinsdBLL.getInstance().searchThongTinSdByCriteria(searchResult,
                        null);
                showSearchResult(thongtinsdList);
            }
        });

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = table.rowAtPoint(evt.getPoint());
                int col = table.columnAtPoint(evt.getPoint());
                if (row >= 0 && col == 1) { // Only handle clicks on columns 1 and 2
                    Object value = table.getValueAt(row, col);
                    if (value instanceof BigInteger) {
                        BigInteger maTV = (BigInteger) value;
                        new ThongTinThanhVien(maTV); // Fix: Use the correct constructor
                    }
                }
                if (row >= 0 && col == 2) {
                    Object value = table.getValueAt(row, col);
                    if (value instanceof Integer) {
                        int maTB = (Integer) value;
                        new ThongTinThietBi(maTB);
                    }
                }
            }
        });
    }

    public void clearForm() {
        searchInput.setText("");
    }

    public void updateThongtinsdFromList() {
        List<thongtinsd> thongtinsdList = thongtinsdBLL.getInstance().getAllModels();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for (thongtinsd thongtinsd : thongtinsdList) {
            model.addRow(new Object[] {
                    thongtinsd.getMaTT(),
                    thongtinsd.getThanhvien(),
                    thongtinsd.getThietbi(),
                    thongtinsd.getTGVao(),
                    thongtinsd.getTGMuon(),
                    thongtinsd.getTGTra()
            });
        }
    }

    public void showSearchResult(List<thongtinsd> searchResult) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for (thongtinsd thongtinsd : searchResult) {
            model.addRow(new Object[] {
                    thongtinsd.getMaTT(),
                    thongtinsd.getThanhvien(),
                    thongtinsd.getThietbi(),
                    thongtinsd.getTGVao(),
                    thongtinsd.getTGMuon(),
                    thongtinsd.getTGTra()
            });
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();

        frame.add(new QLThongTinSDPanel());
        frame.setVisible(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
