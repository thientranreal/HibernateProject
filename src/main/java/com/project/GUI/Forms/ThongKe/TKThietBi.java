package com.project.GUI.Forms.ThongKe;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

public class TKThietBi extends FormPanel {
    public TKThietBi() {
        initComponent();
    }

    public void initComponent() {
        pnlContent = new FormPanel();
        pnlHeader = new FormPanel();
        pnlCard = new FormPanel();
        pnlButtons = new FormPanel();
        pnlDaMuon = new FormPanel();
        pnlDangMuon = new FormPanel();
        inputSearch = new InputField(11);
        btnSearch = new ButtonSearch();
        btnDate = new Button("date");

        Font myFont = new Font("Serif", Font.BOLD, 18);

        setLayout(new BorderLayout());

        pnlHeader.setLayout(new BorderLayout());
        pnlCard.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));

        // add card to pnlCard

        // set to pnl đã mượn trong tháng
        pnlCard.add(pnlDaMuon);
        pnlDaMuon.setBackground(Color.LIGHT_GRAY);
        pnlDaMuon.addMouseListener(actionDaMuon);

        JLabel lbTitleDaMuon = new FormLabel("Thiết bị đã mượn");
        lbTitleDaMuon.setFont(myFont);
        JLabel lbValueDaMuon = new FormLabel("5");
        JLabel lbPercentDaMuon = new FormLabel("40/100 thiết bị đã được cho mượn");
        pnlDaMuon.setLayout(new GridLayout(3, 1));

        pnlDaMuon.add(lbTitleDaMuon);
        pnlDaMuon.add(lbValueDaMuon);
        pnlDaMuon.add(lbPercentDaMuon);

        // set to pnl đang mượn
        pnlCard.add(pnlDangMuon);
        pnlDangMuon.setBackground(Color.LIGHT_GRAY);
        pnlDangMuon.addMouseListener(actionDangMuon);


        JLabel lbTitleDangMuon = new FormLabel("Thiết bị đang mượn");
        lbTitleDangMuon.setFont(myFont);
        JLabel lbValueDangMuon = new FormLabel("5");
        JLabel lbPercentDangMuon = new FormLabel("40/100 thiết bị đã được cho mượn hôm qua");
        pnlDangMuon.setLayout(new GridLayout(3, 1));

        pnlDangMuon.add(lbTitleDangMuon);
        pnlDangMuon.add(lbValueDangMuon);
        pnlDangMuon.add(lbPercentDangMuon);

        pnlHeader.add(pnlCard, BorderLayout.NORTH);

        // add pnl buttons
        pnlButtons.add(inputSearch);
        pnlButtons.add(btnSearch);
        pnlButtons.add(btnDate);

        pnlHeader.add(pnlButtons, BorderLayout.SOUTH);
        add(pnlHeader, BorderLayout.NORTH);

        // content
        pnlContent.setLayout(new BorderLayout());

        JLabel lbTitle = new FormLabel("Thiết bị đã mượn");
        lbTitle.setFont(new Font("Serif", Font.BOLD, 18));
        pnlContent.add(lbTitle, BorderLayout.NORTH);

        table = new JTable();
        // Create header for table
        table.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] { "Mã TB",
                        "Tên thiết bị",
                        "Mô tả",
                        "Mã thành viên",
                        "Tên thành viên",
                        "Thời gian mượn",
                }));
        // Add data for table
        DefaultTableModel model_table = (DefaultTableModel) table.getModel();
        for (int i = 0; i < 20; i++) {
            model_table.addRow(new Object[] {
                    "Text",
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
        TableCustom.apply(pnlTable, TableCustom.TableType.MULTI_LINE);
        // add pnlmain to pnl main
        pnlContent.add(pnlTable, BorderLayout.CENTER);
        add(pnlContent, BorderLayout.CENTER);
    }

    private JPanel pnlContent;
    private JPanel pnlHeader;
    private JPanel pnlCard;
    private JPanel pnlButtons;
    private JPanel pnlDangMuon;
    private JPanel pnlDaMuon;
    private JTextField inputSearch;
    private JButton btnSearch;
    private JButton btnDate;
    private JTable table;

    private MouseAdapter actionDaMuon = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            table.setModel(new DefaultTableModel(
                    new Object[][] {
                    },
                    new String[] { "Mã TB",
                            "Tên thiết bị",
                            "Mô tả",
                            "Mã thành viên",
                            "Tên thành viên",
                            "Thời gian mượn",
                            "Thời gian trả",
                    }));
            // Add data for table
            DefaultTableModel model_table = (DefaultTableModel) table.getModel();
            for (int i = 0; i < 20; i++) {
                model_table.addRow(new Object[] {
                        "Text",
                        "Text",
                        "Text",
                        "Text",
                        "Text",
                        "Text",
                        "Text"
                });
            }

        }
    };

    private MouseAdapter actionDangMuon = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            table.setModel(new DefaultTableModel(
                    new Object[][] {
                    },
                    new String[] { "Mã TB",
                            "Tên thiết bị",
                            "Mô tả",
                            "Mã thành viên",
                            "Tên thành viên",
                            "Thời gian mượn",
                    }));
            // Add data for table
            DefaultTableModel model_table = (DefaultTableModel) table.getModel();
            for (int i = 0; i < 20; i++) {
                model_table.addRow(new Object[] {
                        "Text",
                        "Text",
                        "Text",
                        "Text",
                        "Text",
                        "Text"
                });
            }

        }
    };
}
