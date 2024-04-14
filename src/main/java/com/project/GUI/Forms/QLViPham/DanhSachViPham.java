package com.project.GUI.Forms.QLViPham;

import com.project.BLL.thanhvienBLL;
import com.project.BLL.xulyBLL;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

import javax.swing.table.DefaultTableModel;

import com.project.GUI.Components.FormLabel;
import com.project.GUI.Components.FormPanel;
import com.project.GUI.Components.Buttons.ButtonAdd;
import com.project.GUI.Components.Buttons.ButtonDel;
import com.project.GUI.Components.Buttons.ButtonDelAll;
import com.project.GUI.Components.Buttons.ButtonExcel;
import com.project.GUI.Components.Buttons.ButtonRefresh;
import com.project.GUI.Components.Buttons.ButtonSearch;
import com.project.GUI.Components.Table.TableCustom;
import com.project.GUI.Components.TextFields.InputField;
import com.project.GUI.Components.TextFields.SearchField;
import com.project.GUI.GlobalVariables.Colors;
import com.project.GUI.GlobalVariables.Fonts;
import com.project.models.thanhvien;
import com.project.models.xuly;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableCellRenderer;

public class DanhSachViPham extends JPanel {

    public final JTable table;

    public DanhSachViPham() {
        pnlMain = new FormPanel();
        lbTitle = new FormLabel("Danh sách vi phạm");
        pnlButtons = new FormPanel();
        btnAdd = new ButtonAdd();
        btnDel = new ButtonDel();
        btnRefresh = new ButtonRefresh();
        btnExcel = new ButtonExcel();
        pnlSearch = new FormPanel();
        inputSearch = new SearchField(20);
        btnDelAll = new ButtonDelAll();
        btnSearch = new ButtonSearch();

        lbTitle.setFont(Fonts.headerFont);
        lbTitle.setForeground(Color.BLACK);

        GridBagConstraints gbc;

        setLayout(new BorderLayout());

        pnlMain.setLayout(new GridBagLayout());
        pnlMain.add(lbTitle);

        gbc = new GridBagConstraints();

        // add button to pnlButtons
        //btnAdd.addActionListener(actionAdd);
        btnAdd.addActionListener(e -> {
            ThaoTac thaotac = new ThaoTac(btnRefresh);
            thaotac.setVisible(true);
        });
        pnlButtons.add(btnAdd);
        pnlButtons.add(btnDel);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        pnlMain.add(pnlButtons, gbc);

        //pnlSearch
        pnlSearch.add(inputSearch);
        pnlSearch.add(btnSearch);
        pnlSearch.add(btnRefresh);
        // add pnlSearch to pnlMain
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        pnlMain.add(pnlSearch, gbc);

        add(pnlMain, BorderLayout.NORTH);

        table = new JTable();
        // Create header for table
        table.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"Mã VP",
                    "Mã TV",
                    "Họ tên",
                    "Hình thức Xử lý",
                    "Số tiền",
                    "Ngày XL",
                    "Trạng thái XL",}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 3 || column == 4 || column == 6;
            }
        });
        // Add data for table

        // Create panel to contain table
        JScrollPane pnlTable = new JScrollPane();
        pnlTable.setBorder(new EmptyBorder(10, 10, 10, 10));
        pnlTable.setPreferredSize(new Dimension(700, 400));
        pnlTable.setViewportView(table);
        pnlTable.setBackground(Colors.bgColor);

        TableCustom.apply(pnlTable, TableCustom.TableType.MULTI_LINE);
        add(pnlTable, BorderLayout.CENTER);
        updateMemberFromList();
        table.getDefaultEditor(String.class).addCellEditorListener(new CellEditorListener() {
            public void editingStopped(ChangeEvent e) {
                int selectedRow = table.getSelectedRow();
                Object maXL = table.getValueAt(selectedRow, 0);
                Object maTV = table.getValueAt(selectedRow, 1);
                Object HinhThucXL = table.getValueAt(selectedRow, 3);
                Object SoTien = table.getValueAt(selectedRow, 4);
                Object ngayXL = table.getValueAt(selectedRow, 5);
                Object TrangThaiXL = table.getValueAt(selectedRow, 6);
                
                xuly updateXuly = new xuly();
                updateXuly.setMaXL((int) maXL);
                updateXuly.setMaTV((BigInteger) maTV);
                Date date;
                try {
                    date = new SimpleDateFormat("yyyy-MM-dd").parse(ngayXL.toString());
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                updateXuly.setNgayXL(sqlDate);
                updateXuly.setHinhThucXL(HinhThucXL.toString());

                int soTien;
                try {
                    soTien = Integer.parseInt(SoTien.toString());
                    updateXuly.setSoTien(soTien);
                } catch (NumberFormatException | NullPointerException ex) {
                    updateXuly.setSoTien(null);
                }

                // Kiểm tra nếu TrangThaiXL là 0 hoặc 1 trước khi gán giá trị
                String strTrangThaiXL = TrangThaiXL.toString();
                int intTrangThaiXL = -1; // Giá trị khởi tạo không hợp lệ
                try {
                    intTrangThaiXL = Integer.parseInt(strTrangThaiXL);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Trạng thái chỉ được nhập 0 hoặc 1");
                    updateMemberFromList();
                    return; // Dừng việc thực hiện khi có lỗi
                }

                if (intTrangThaiXL != 0 && intTrangThaiXL != 1) {
                    JOptionPane.showMessageDialog(null, "Trạng thái chỉ được nhập 0 hoặc 1");
                    updateMemberFromList();
                    return; // Dừng việc thực hiện khi có lỗi
                }
                updateXuly.setTrangThaiXL(intTrangThaiXL);
                boolean result1 = xulyBLL.getInstance().updateModel(updateXuly);

                if (result1) {
                    JOptionPane.showMessageDialog(null, "Lưu thành công");
                    updateMemberFromList();
                } else {
                    JOptionPane.showMessageDialog(null, "Lưu thất bại");
                }
            }

            public void editingCanceled(ChangeEvent e) {
                System.out.println("Editing canceled");
            }
        });
        btnDel.addActionListener(e -> {
            int[] selectedRows = table.getSelectedRows();

            if (selectedRows.length == 0) {
                JOptionPane.showMessageDialog(null, "Bạn chưa chọn dòng muốn xóa", "Thông báo",
                        JOptionPane.INFORMATION_MESSAGE);
                return;
            } else {
                int choice = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa thành viên này?");
                if (choice == JOptionPane.YES_OPTION) {
                    for (int index : selectedRows) {
                        int id = (int) table.getValueAt(index, 0);
                        deleteMember(id);
                    }
                    clearForm();
                    updateMemberFromList();
                }
            }
        });
        inputSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String searchValue = inputSearch.getText().trim();
                    java.util.List<xuly> searchResult = xulyBLL.getInstance().searchListThanhVien(searchValue);
                    showSearchResult(searchResult);
                }
            }
        });
        btnRefresh.addActionListener(e -> {
            clearForm();
            updateMemberFromList();
        });
        btnSearch.addActionListener(e -> {
            String searchValue = inputSearch.getText().trim();
            java.util.List<xuly> searchResult = xulyBLL.getInstance().searchListThanhVien(searchValue);
            showSearchResult(searchResult);
        });
        updateMemberFromList();
    }
    private JPanel pnlMain;
    private FormLabel lbTitle;
    private JPanel pnlButtons;
    private JPanel pnlSearch;
    private JButton btnAdd;
    private JButton btnDel;
    private JButton btnRefresh;
    private JButton btnExcel;
    private InputField inputSearch;
    private JButton btnDelAll;
    private JButton btnSearch;

    public final void updateMemberFromList() {
        String handleStatus = null;
        xulyBLL.getInstance().refresh();
        DefaultTableModel model_table = (DefaultTableModel) table.getModel();
        model_table.setRowCount(0);
        
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (xuly member : xulyBLL.getInstance().getAllModels()) {
            thanhvien tvmodel = thanhvienBLL.getInstance().getModelById(member.getMaTV());
            if (tvmodel != null) {
                handleStatus = tvmodel.getHoTen();
            } else {
                handleStatus = "Không vi phạm";
            }
            model_table.addRow(new Object[]{
                member.getMaXL(),
                member.getMaTV(),
                handleStatus,
                member.getHinhThucXL(),
                member.getSoTien(),
                member.getNgayXL(),
                member.getTrangThaiXL()
            });
        }  
    }
    public void deleteMember(int id) {
        try {
            if (xulyBLL.getInstance().deleteModel(id)) {
                JOptionPane.showMessageDialog(null, "Xóa thành công");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Xóa thất bại");
        }
    }
    public void clearForm() {
        inputSearch.setText("");
    }
    public void showSearchResult(java.util.List<xuly> search) {
        String handleStatus = null;
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        for (xuly member : search) {
            model.addRow(new Object[]{
                member.getMaXL(),
                member.getMaTV(),
                member.getHinhThucXL(),
                member.getSoTien(),
                member.getNgayXL(),
                member.getTrangThaiXL()
            });
        }
        if (search.size() == 0) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy kết quả");
            // Refresh table:
            updateMemberFromList();
        }
    }
}
