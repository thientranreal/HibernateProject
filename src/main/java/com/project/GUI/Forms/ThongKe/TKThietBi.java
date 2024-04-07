package com.project.GUI.Forms.ThongKe;

import com.project.BLL.thanhvienBLL;
import com.project.BLL.thietbiBLL;
import com.project.BLL.thongtinsdBLL;
import com.project.BLL.xulyBLL;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormatSymbols;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.project.GUI.Components.FormLabel;
import com.project.GUI.Components.FormPanel;
import com.project.GUI.Components.Buttons.ButtonSearch;
import com.project.GUI.Components.Table.TableCustom;
import com.project.GUI.Components.TextFields.SearchField;
import com.project.GUI.GlobalVariables.Colors;
import com.project.models.thanhvien;
import com.project.models.thietbi;
import com.project.models.thongtinsd;
import com.project.models.xuly;
import java.math.BigInteger;
import javax.swing.table.DefaultTableCellRenderer;

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
        inputSearch = new SearchField(11);
        btnSearch = new ButtonSearch();

//        Set Month for combo box
        String[] months = new DateFormatSymbols().getMonths();
        cbMonth = new JComboBox<>(months);

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

        // Create cursor
        Cursor cursor = new Cursor(Cursor.HAND_CURSOR);

        pnlDaMuon.add(lbTitleDaMuon);
        pnlDaMuon.add(lbValueDaMuon);
        pnlDaMuon.add(lbPercentDaMuon);
        pnlDaMuon.setCursor(cursor);

        // set to pnl đang mượn
        pnlCard.add(pnlDangMuon);
        pnlDangMuon.setBackground(Color.LIGHT_GRAY);
        pnlDangMuon.addMouseListener(actionDangMuon);
        pnlDangMuon.setCursor(cursor);


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
        pnlButtons.add(cbMonth);

        pnlHeader.add(pnlButtons, BorderLayout.SOUTH);
        add(pnlHeader, BorderLayout.NORTH);

        // content
        pnlContent.setLayout(new BorderLayout());

        lbTitle = new FormLabel("Thiết bị đang mượn");
        lbTitle.setFont(new Font("Serif", Font.BOLD, 18));
        pnlContent.add(lbTitle, BorderLayout.NORTH);

        table = new JTable();
        // Create header for table
        table.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] { "Mã TB",
                        "Tên TB",
                        "Mô tả",
                        "Mã TV",
                        "Họ tên",
                        "Thời gian mượn"
                }){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }});
        // Add data for table
        updateThietbiFromList();
        // Create panel to contain table
        JScrollPane pnlTable = new JScrollPane();
        pnlTable.setBorder(new EmptyBorder(10, 10, 10, 10));
        pnlTable.setPreferredSize(new Dimension(850, 400));
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
    private JComboBox<String> cbMonth;
    private JTable table;
    private JLabel lbTitle;

    private MouseAdapter actionDaMuon = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            lbTitle.setText("Thiết bị đã mượn");
            table.setModel(new DefaultTableModel(
                    new Object[][] {
                    },
                    new String[] { "Mã TB",
                            "Tên TB",
                            "Mô tả",
                            "Mã TV",
                            "Họ tên",
                            "Thời gian mượn",
                            "Thời gian trả",
                    }){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }});
            // Add data for table
          
        }
    };

    private MouseAdapter actionDangMuon = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            lbTitle.setText("Thiết bị đang mượn");
            table.setModel(new DefaultTableModel(
                    new Object[][] {
                    },
                    new String[] { "Mã TB",
                            "Tên TB",
                            "Mô tả",
                            "Mã TV",
                            "Họ tên",
                            "Thời gian mượn",
                    }){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }});
        }
    };
    
    public void updateThietbiFromList() {
        thongtinsdBLL.getInstance().refresh();
        thietbiBLL.getInstance().refresh();
        thanhvienBLL.getInstance().refresh();
    
        DefaultTableModel model_table = (DefaultTableModel) table.getModel();
        model_table.setRowCount(0);

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);

        java.util.List<thietbi> listThietbi = thietbiBLL.getInstance().getAllModels();
        java.util.List<thanhvien> listThanhVien = thanhvienBLL.getInstance().getAllModels();

        for (thongtinsd member : thongtinsdBLL.getInstance().getAllModels()) {
            String tenTB = "";
            String HoTen = "";
            int maTB = 0;
            String mota ="";
            
            for (thanhvien tv : listThanhVien) {
                if (tv.getMaTV().equals(member.getThanhvien())) {
                    HoTen = tv.getHoTen();
                    break;
                }
            }
            for (thietbi tb : listThietbi){
                if (tb.getMaTB()== member.getThietbi()) {
                maTB = tb.getMaTB();
                tenTB = tb.getTenTB();
                mota = tb.getMoTaTB();         
                }
            }
            model_table.addRow(new Object[]{
                maTB,
                tenTB,
                mota,
                member.getThanhvien(),
                HoTen,
                member.getTGMuon()
            });
        }
    }
}
