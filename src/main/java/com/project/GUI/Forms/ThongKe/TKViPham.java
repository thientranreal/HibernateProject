package com.project.GUI.Forms.ThongKe;

import com.project.BLL.thanhvienBLL;
import com.project.BLL.thietbiBLL;
import com.project.BLL.xulyBLL;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.util.List;

import com.project.GUI.Components.FormLabel;
import com.project.GUI.Components.FormPanel;
import com.project.GUI.Components.Table.TableCustom;
import com.project.GUI.GlobalVariables.Colors;
import com.project.models.thanhvien;
import com.project.models.thietbi;
import com.project.models.xuly;
import javax.swing.table.DefaultTableCellRenderer;

public class TKViPham extends FormPanel {
    public TKViPham() {
        initComponent();
    }

    public void initComponent() {
        pnlContent = new FormPanel();
        pnlHeader = new FormPanel();
        pnlCard = new FormPanel();
        pnlChuaXuLy = new FormPanel();
        pnlDaXuLy = new FormPanel();
        lbTotal = new FormLabel("");

        Font myFont = new Font("Serif", Font.BOLD, 18);

        // Create cursor
        Cursor cursor = new Cursor(Cursor.HAND_CURSOR);

        setLayout(new BorderLayout());

        pnlHeader.setLayout(new BorderLayout());
        pnlCard.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));

        // add card to pnlCard

        // set to pnl đã mượn trong tháng
        pnlCard.add(pnlChuaXuLy);
        pnlChuaXuLy.setBackground(Color.LIGHT_GRAY);
        pnlChuaXuLy.addMouseListener(actionChuaXuLy);

        JLabel lbTitleDaXuLy = new FormLabel("Vi phạm chưa xử lý");
        lbTitleDaXuLy.setFont(myFont);
        JLabel lbValueDaXuLy = new FormLabel("5");
        JLabel lbPercentDaXuLy = new FormLabel("40/100 tổng vi phạm");
        pnlChuaXuLy.setLayout(new GridLayout(3, 1));

        pnlChuaXuLy.add(lbTitleDaXuLy);
        pnlChuaXuLy.add(lbValueDaXuLy);
        pnlChuaXuLy.add(lbPercentDaXuLy);
        pnlChuaXuLy.setCursor(cursor);

        // set to pnl đang mượn
        pnlCard.add(pnlDaXuLy);
        pnlDaXuLy.setBackground(Color.LIGHT_GRAY);
        pnlDaXuLy.addMouseListener(actionDaXuLy);
        pnlDaXuLy.setCursor(cursor);

        JLabel lbTitleChuaXuLy = new FormLabel("Vi phạm đã xử lý");
        lbTitleChuaXuLy.setFont(myFont);
        JLabel lbValueChuaXuLy = new FormLabel("5");
        JLabel lbPercentChuaXuLy = new FormLabel("60/100 tổng vi phạm");
        pnlDaXuLy.setLayout(new GridLayout(3, 1));

        pnlDaXuLy.add(lbTitleChuaXuLy);
        pnlDaXuLy.add(lbValueChuaXuLy);
        pnlDaXuLy.add(lbPercentChuaXuLy);

        pnlHeader.add(pnlCard, BorderLayout.NORTH);

        add(pnlHeader, BorderLayout.NORTH);

        // content
        pnlContent.setLayout(new BorderLayout());

        lbTitle = new FormLabel("Vi phạm chưa xử lý");
        lbTitle.setFont(new Font("Serif", Font.BOLD, 18));
        pnlContent.add(lbTitle, BorderLayout.NORTH);

        table = new JTable();
        // Create header for table
        table.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] { "Mã XL",
                        "Tên TB",
                        "Mã TV",
                        "Họ tên",
                        "Số tiền",
                        "Ngày xử lý",
                }){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }});
        updateViPhamFromList();
        // Create panel to contain table
        JScrollPane pnlTable = new JScrollPane();
        pnlTable.setBorder(new EmptyBorder(10, 10, 10, 10));
        pnlTable.setPreferredSize(new Dimension(700, 400));
        pnlTable.setViewportView(table);
        pnlTable.setBackground(Colors.bgColor);
        TableCustom.apply(pnlTable, TableCustom.TableType.MULTI_LINE);
        // add pnlmain to pnl main
        pnlContent.add(pnlTable, BorderLayout.CENTER);

        //add total to pnl chua xu ly
        lbTotal.setText("6.000");
        lbTotal.setVisible(false);
        lbTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        pnlContent.add(lbTotal, BorderLayout.SOUTH);

        add(pnlContent, BorderLayout.CENTER);
    }

    private JPanel pnlContent;
    private JPanel pnlHeader;
    private JPanel pnlCard;
    private JPanel pnlDaXuLy;
    private JPanel pnlChuaXuLy;
    private JTable table;
    private JLabel lbTitle;
    private JLabel lbTotal;

    private MouseAdapter actionDaXuLy = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            table.setModel(new DefaultTableModel(
                    new Object[][] {
                    },
                    new String[] { "Mã XL",
                            "Tên TB",
                            "Mã TV",
                            "Họ tên",
                            "Số tiền",
                            "Ngày xử lý",
                    }){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }});
            updateViPhamDaXuLy();
        }
    };

    private MouseAdapter actionChuaXuLy = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            table.setModel(new DefaultTableModel(
                    new Object[][] {
                    },
                    new String[] { "Mã XL",
                            "Tên TB",
                            "Mã TV",
                            "Họ tên",
                            "Số tiền",
                            "Ngày xử lý",
                    }){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }});
            updateViPhamChuaXuLy();
        }
    };
    
    public void updateViPhamFromList() {
    xulyBLL.getInstance().refresh();
    thietbiBLL.getInstance().refresh();
    thanhvienBLL.getInstance().refresh();
    DefaultTableModel model_table = (DefaultTableModel) table.getModel();
    model_table.setRowCount(0);

    DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
    renderer.setHorizontalAlignment(SwingConstants.CENTER);

    List<thietbi> listThietBi = thietbiBLL.getInstance().getAllModels();
    List<thanhvien> listThanhVien = thanhvienBLL.getInstance().getAllModels();

    for (xuly member : xulyBLL.getInstance().getAllModels()) {
        String tenTB = "";
        String hoTen = "";

        for (thietbi tb : listThietBi) {
            if (tb.getTenTB().equals(member.getMaXL())) {
                tenTB = tb.getTenTB();
                break;
            }
        }

        for (thanhvien tv : listThanhVien) {
            if (tv.getMaTV().equals(member.getMaTV())) {
                hoTen = tv.getHoTen();
                break;
            }
        }

        model_table.addRow(new Object[]{
            member.getMaXL(),
            tenTB,
            member.getMaTV(),
            hoTen,
            member.getSoTien(),
            member.getNgayXL()
        });
    }
}
    public void updateViPhamDaXuLy() {
    xulyBLL.getInstance().refresh();
    thietbiBLL.getInstance().refresh();
    thanhvienBLL.getInstance().refresh();
    DefaultTableModel model_table = (DefaultTableModel) table.getModel();
    model_table.setRowCount(0);

    DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
    renderer.setHorizontalAlignment(SwingConstants.CENTER);

    List<thietbi> listThietBi = thietbiBLL.getInstance().getAllModels();
    List<thanhvien> listThanhVien = thanhvienBLL.getInstance().getAllModels();

    for (xuly member : xulyBLL.getInstance().getAllModels()) {
        String tenTB = "";
        String hoTen = "";
        if(member.getTrangThaiXL()==1){
        for (thietbi tb : listThietBi) {
            if (tb.getTenTB().equals(member.getMaXL())) {
                tenTB = tb.getTenTB();
                break;
            }
        }

        for (thanhvien tv : listThanhVien) {
            if (tv.getMaTV().equals(member.getMaTV())) {
                hoTen = tv.getHoTen();
                break;
            }
        }

        model_table.addRow(new Object[]{
            member.getMaXL(),
            tenTB,
            member.getMaTV(),
            hoTen,
            member.getSoTien(),
            member.getNgayXL()
        });
    }}
}
    public void updateViPhamChuaXuLy() {
    xulyBLL.getInstance().refresh();
    thietbiBLL.getInstance().refresh();
    thanhvienBLL.getInstance().refresh();
    DefaultTableModel model_table = (DefaultTableModel) table.getModel();
    model_table.setRowCount(0);

    DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
    renderer.setHorizontalAlignment(SwingConstants.CENTER);

    List<thietbi> listThietBi = thietbiBLL.getInstance().getAllModels();
    List<thanhvien> listThanhVien = thanhvienBLL.getInstance().getAllModels();

    for (xuly member : xulyBLL.getInstance().getAllModels()) {
        String tenTB = "";
        String hoTen = "";
        if(member.getTrangThaiXL()==0){
        for (thietbi tb : listThietBi) {
            if (tb.getTenTB().equals(member.getMaXL())) {
                tenTB = tb.getTenTB();
                break;
            }
        }

        for (thanhvien tv : listThanhVien) {
            if (tv.getMaTV().equals(member.getMaTV())) {
                hoTen = tv.getHoTen();
                break;
            }
        }

        model_table.addRow(new Object[]{
            member.getMaXL(),
            tenTB,
            member.getMaTV(),
            hoTen,
            member.getSoTien(),
            member.getNgayXL()
        });
    }}
}
}
