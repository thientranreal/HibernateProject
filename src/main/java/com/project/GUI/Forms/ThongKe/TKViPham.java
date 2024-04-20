package com.project.GUI.Forms.ThongKe;

import com.project.BLL.thanhvienBLL;
import com.project.BLL.xulyBLL;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.util.List;
import java.util.Objects;

import com.project.GUI.Components.FormLabel;
import com.project.GUI.Components.FormPanel;
import com.project.GUI.Components.Table.TableCustom;
import com.project.GUI.GlobalVariables.Colors;
import com.project.models.thanhvien;
import com.project.models.xuly;
import javax.swing.table.DefaultTableCellRenderer;

public class TKViPham extends FormPanel {
    public TKViPham() {
        xulyBLL.getInstance().refresh();
        thanhvienBLL.getInstance().refresh();
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

        JLabel lbTitleChuaXuLy = new FormLabel("Vi phạm chưa xử lý");
        lbTitleChuaXuLy.setFont(myFont);
        lbValueChuaXuLy = new FormLabel("");
        lbPercentChuaXuLy = new FormLabel("");
        pnlChuaXuLy.setLayout(new GridLayout(3, 1));

        pnlChuaXuLy.add(lbTitleChuaXuLy);
        pnlChuaXuLy.add(lbValueChuaXuLy);
        pnlChuaXuLy.add(lbPercentChuaXuLy);
        pnlChuaXuLy.setCursor(cursor);

        // set to pnl đang mượn
        pnlCard.add(pnlDaXuLy);
        pnlDaXuLy.setBackground(Color.LIGHT_GRAY);
        pnlDaXuLy.addMouseListener(actionDaXuLy);
        pnlDaXuLy.setCursor(cursor);

        JLabel lbTitleDaXuLy = new FormLabel("Vi phạm đã xử lý");
        lbTitleDaXuLy.setFont(myFont);
        lbValueDaXuLy = new FormLabel("");
        lbPercentDaXuLy = new FormLabel("");
        pnlDaXuLy.setLayout(new GridLayout(3, 1));

        pnlDaXuLy.add(lbTitleDaXuLy);
        pnlDaXuLy.add(lbValueDaXuLy);
        pnlDaXuLy.add(lbPercentDaXuLy);

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
                        "Mã TV",
                        "Họ tên",
                        "Hình thức XL",
                        "Số tiền",
                        "Ngày xử lý",
                }){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        showViPhamChuaXuLy();

        int daXuLySL = (int) xulyBLL.getInstance().getAllModels()
                .stream()
                .filter(xuly -> xuly.getTrangThaiXL() == 1)
                .count();
        lbValueDaXuLy.setText(String.valueOf(daXuLySL));
        lbPercentDaXuLy.setText(String.format("%d/%d tổng vi phạm", daXuLySL, xulyBLL.getInstance().getAllModels().size()));

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
    private JLabel lbValueDaXuLy;
    private JLabel lbPercentDaXuLy;
    private JLabel lbValueChuaXuLy;
    private JLabel lbPercentChuaXuLy;


    private MouseAdapter actionDaXuLy = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            lbTitle.setText("Vi phạm đã xử lý");
            lbTotal.setVisible(true);
            showViPhamDaXuLy();
        }
    };

    private MouseAdapter actionChuaXuLy = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            lbTitle.setText("Vi phạm chưa xử lý");
            lbTotal.setVisible(false);
            showViPhamChuaXuLy();
        }
    };
    
    public void showViPhamChuaXuLy() {
        DefaultTableModel model_table = (DefaultTableModel) table.getModel();
        model_table.setRowCount(0);

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);

        List<thanhvien> listThanhVien = thanhvienBLL.getInstance().getAllModels();
        List<xuly> listChuaXL = xulyBLL.getInstance().getAllModels()
                .stream()
                .filter(xuly -> xuly.getTrangThaiXL() == 0)
                .toList();

        for (xuly xl : listChuaXL) {
            for (thanhvien member : listThanhVien) {
                if (Objects.equals(member.getMaTV(), xl.getMaTV())) {
                    model_table.addRow(new Object[]{
                            xl.getMaXL(),
                            member.getMaTV(),
                            member.getHoTen(),
                            xl.getHinhThucXL(),
                            xl.getSoTien(),
                            xl.getNgayXL()
                    });
                }
            }
        }
        lbValueChuaXuLy.setText(String.valueOf(listChuaXL.size()));
        lbPercentChuaXuLy.setText(String.format("%d/%d tổng vi phạm", listChuaXL.size(), xulyBLL.getInstance().getAllModels().size()));
    }
    public void showViPhamDaXuLy() {
        int total = 0;
        DefaultTableModel model_table = (DefaultTableModel) table.getModel();
        model_table.setRowCount(0);

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);

        List<thanhvien> listThanhVien = thanhvienBLL.getInstance().getAllModels();
        List<xuly> listDaXL = xulyBLL.getInstance().getAllModels()
                .stream()
                .filter(xuly -> xuly.getTrangThaiXL() == 1)
                .toList();

        for (xuly xl : listDaXL) {
            if (xl.getSoTien() != null) {
                total += xl.getSoTien();
            }
            for (thanhvien member : listThanhVien) {
                if (Objects.equals(member.getMaTV(), xl.getMaTV())) {
                    model_table.addRow(new Object[]{
                            xl.getMaXL(),
                            member.getMaTV(),
                            member.getHoTen(),
                            xl.getHinhThucXL(),
                            xl.getSoTien(),
                            xl.getNgayXL()
                    });
                }
            }
        }
        lbValueDaXuLy.setText(String.valueOf(listDaXL.size()));
        lbPercentDaXuLy.setText(String.format("%d/%d tổng vi phạm", listDaXL.size(), xulyBLL.getInstance().getAllModels().size()));
        lbTotal.setText("Tổng tiền bồi thường: " + total);
    }
}
