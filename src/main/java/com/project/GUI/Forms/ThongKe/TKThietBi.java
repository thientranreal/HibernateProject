package com.project.GUI.Forms.ThongKe;

import com.project.BLL.thanhvienBLL;
import com.project.BLL.thietbiBLL;
import com.project.BLL.thongtinsdBLL;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import javax.swing.table.DefaultTableCellRenderer;

public class TKThietBi extends FormPanel {
    public TKThietBi() {
        thongtinsdBLL.getInstance().refresh();
        thietbiBLL.getInstance().refresh();
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
        lbValueDaMuon = new FormLabel("");
        lbPercentDaMuon = new FormLabel("");
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
        lbValueDangMuon = new FormLabel("");
        lbPercentDangMuon = new FormLabel("");
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

        lbTitle = new FormLabel("Thiết bị đã mượn");
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
                        "Thời gian mượn",
                        "Thời gian Trả"
                }){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }});
        // Add data for table
        showDevicesBorrowed("", "");

        int devicesBorrowing = (int) thongtinsdBLL.getInstance().getAllModels()
                .stream()
                .filter(info -> info.getTGMuon() != null && info.getTGTra() == null)
                .count();
        lbValueDangMuon.setText(String.valueOf(devicesBorrowing));
        lbPercentDangMuon.setText(String.format(
                "%d/%d thiết bị đang được mượn",
                devicesBorrowing,
                thietbiBLL.getInstance().getAllModels().size())
        );

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

        btnSearch.addActionListener(e -> {
            String searchValue = inputSearch.getText().trim();
            String Month = Objects.requireNonNull(cbMonth.getSelectedItem()).toString();
            if (searchValue.equals("search")) {
                searchValue = "";
            }
            if (lbTitle.getText().equals("Thiết bị đã mượn")) {
                showDevicesBorrowed(searchValue, Month);
            }
            else if (lbTitle.getText().equals("Thiết bị đang mượn")) {
                showDevicesBorrowing(searchValue, Month);
            }
        });

        inputSearch.addActionListener(e -> {
            btnSearch.doClick();
        });
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
    private  JLabel lbValueDaMuon;
    private JLabel lbPercentDaMuon;
    private  JLabel lbValueDangMuon;
    private JLabel lbPercentDangMuon;
    private final Calendar calendar = Calendar.getInstance();


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
          showDevicesBorrowed("", "");
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
            showDevicesBorrowing("", "");
        }
    };

    public void showDevicesBorrowed(String search, String month) {
        int monthIndex;
        String monthName = null;
        thanhvienBLL.getInstance().refresh();
        DefaultTableModel model_table = (DefaultTableModel) table.getModel();
        model_table.setRowCount(0);

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);

        List<thongtinsd> allInfo = thongtinsdBLL.getInstance().getAllModels()
                .stream()
                .filter(info -> info.getTGMuon() != null && info.getTGTra() != null)
                .toList();

        for (thongtinsd info : allInfo) {
            if ( !month.equals("") ) {
                calendar.setTimeInMillis(info.getTGMuon().getTime());
                monthIndex = calendar.get(Calendar.MONTH);
                monthName = cbMonth.getItemAt(monthIndex);
            }
            for (thietbi device : thietbiBLL.getInstance().getAllModels()) {
                if (device.getMaTB() == info.getThietbi()
                        && ( search.equals("") || device.getTenTB().toLowerCase().contains(search.toLowerCase()) )
                        && ( month.equals("") || month.equals(monthName) )
                ) {
                    for (thanhvien member : thanhvienBLL.getInstance().getAllModels()) {
                        if (Objects.equals(member.getMaTV(), info.getThanhvien())) {
                            model_table.addRow(new Object[] {
                                    device.getMaTB(),
                                    device.getTenTB(),
                                    device.getMoTaTB(),
                                    member.getMaTV(),
                                    member.getHoTen(),
                                    info.getTGMuon(),
                                    info.getTGTra()
                            });
                        }
                    }
                }
            }
        }
        int devicesBorrowed = table.getRowCount();
        lbValueDaMuon.setText(String.valueOf(devicesBorrowed));
        lbPercentDaMuon.setText(String.format(
                "%d/%d thiết bị đã được mượn",
                devicesBorrowed,
                thietbiBLL.getInstance().getAllModels().size())
        );
    }

    public void showDevicesBorrowing(String search, String month) {
        int monthIndex;
        String monthName = null;
        thanhvienBLL.getInstance().refresh();
        DefaultTableModel model_table = (DefaultTableModel) table.getModel();
        model_table.setRowCount(0);

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);

        List<thongtinsd> allInfo = thongtinsdBLL.getInstance().getAllModels()
                .stream()
                .filter(info -> info.getTGMuon() != null && info.getTGTra() == null)
                .toList();

        for (thongtinsd info : allInfo) {
            if ( !month.equals("") ) {
                calendar.setTimeInMillis(info.getTGMuon().getTime());
                monthIndex = calendar.get(Calendar.MONTH);
                monthName = cbMonth.getItemAt(monthIndex);
            }
            for (thietbi device : thietbiBLL.getInstance().getAllModels()) {
                if (device.getMaTB() == info.getThietbi()
                        && ( search.equals("") || device.getTenTB().toLowerCase().contains(search.toLowerCase()) )
                        && ( month.equals("") || month.equals(monthName) )
                ) {
                    for (thanhvien member : thanhvienBLL.getInstance().getAllModels()) {
                        if (Objects.equals(member.getMaTV(), info.getThanhvien())) {
                            model_table.addRow(new Object[] {
                                    device.getMaTB(),
                                    device.getTenTB(),
                                    device.getMoTaTB(),
                                    member.getMaTV(),
                                    member.getHoTen(),
                                    info.getTGMuon()
                            });
                        }
                    }
                }
            }
        }
        int devicesBorrowing = table.getRowCount();
        lbValueDangMuon.setText(String.valueOf(devicesBorrowing));
        lbPercentDangMuon.setText(String.format(
                "%d/%d thiết bị đang được mượn",
                devicesBorrowing,
                thietbiBLL.getInstance().getAllModels().size())
        );
    }
}
