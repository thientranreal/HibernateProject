package com.project.GUI.Forms.QLThanhVien;

import com.project.BLL.thanhvienBLL;
import com.project.BLL.thongtinsdBLL;
import com.project.BLL.xulyBLL;
import com.project.GUI.GlobalVariables.Colors;
import com.project.GUI.Components.Buttons.*;
import com.project.GUI.Components.FormLabel;
import com.project.GUI.Components.FormPanel;
import com.project.GUI.Components.Table;
import com.project.GUI.Components.TextFields.InputField;
import com.project.GUI.Components.TextFields.SearchField;
import com.project.GUI.GlobalVariables.Fonts;
import com.project.models.thanhvien;
import com.project.models.thongtinsd;
import com.project.models.xuly;
import com.project.utilities.excel.thanhvienExcelUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

public class QLThanhVienPanel extends FormPanel {

    private final Table table;
    private final InputField inputMaTV;
    private final SearchField searchInput;
    public static int maSV;

    public QLThanhVienPanel() {
        // Add constraints to make button align vertically
        GridBagConstraints constraints = new GridBagConstraints();
        // Add padding bottom 10px
        constraints.insets = new Insets(0, 0, 10, 0);

        // Set GridBagLayout layout
        setLayout(new BorderLayout());

        // Create Header
        JPanel pnlHeader = new FormPanel();
        JLabel lbHeader = new FormLabel("Quản lý thành viên");
        lbHeader.setFont(Fonts.headerFont);
        lbHeader.setForeground(Color.BLACK);
        pnlHeader.add(lbHeader);

        // Create search input, button search, button refresh
        searchInput = new SearchField(20);
        JButton btnSearch = new ButtonSearch();
        JButton btnRefresh = new ButtonRefresh();

        // Create panel to contain search input
        JPanel pnlSearch = new FormPanel();
        pnlSearch.add(searchInput);
        pnlSearch.add(btnSearch);
        pnlSearch.add(btnRefresh);

        // Create table for showing data
        table = new Table();
        // Create header for table
        table.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] { "Mã TV",
                        "Họ Tên",
                        "Khoa",
                        "Ngành",
                        "Số ĐT",
                        "Xử Lý"
                }) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != getColumnCount() - 6 && column != getColumnCount() - 1;
            }
        });

        // Create panel to contain table
        JScrollPane pnlTable = new JScrollPane();
        pnlTable.setBorder(new EmptyBorder(10, 10, 10, 10));
        pnlTable.setViewportView(table);
        pnlTable.setBackground(Colors.bgColor);

        // Create button add, button delete
        JButton btnAdd = new ButtonAdd();
        JButton btnDel = new ButtonDel();
        JButton btnBorrow = new ButtonNormal("Borrow");
        JButton btnReturn = new ButtonNormal("Return");
        JButton btnWarning = new ButtonWarning();
        JButton btnExportExcel = new ButtonExcel();

        // Create panel to contain button
        JPanel pnlAction = new FormPanel();
        pnlAction.setLayout(new GridBagLayout());

        int y = 0;
        constraints.gridx = 0;
        constraints.gridy = y++;
        pnlAction.add(btnAdd, constraints);

        constraints.gridy = y++;
        pnlAction.add(btnDel, constraints);

        constraints.gridy = y++;
        pnlAction.add(btnBorrow, constraints);

        constraints.gridy = y++;
        pnlAction.add(btnReturn, constraints);

        constraints.gridy = y++;
        pnlAction.add(btnWarning, constraints);

        constraints.gridy = y;
        pnlAction.add(btnExportExcel, constraints);

        // Panel check in
        JLabel lbMaTV = new FormLabel("Mã TV");
        inputMaTV = new InputField(20);
        JButton btnCheckIn = new ButtonNormal("Check in");
        JPanel pnlCheckIn = new FormPanel();
        pnlCheckIn.setLayout(new GridBagLayout());
        updateMemberFromList();
        btnCheckIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BigInteger maSV = new BigInteger(inputMaTV.getText());

                thanhvien currentMember = thanhvienBLL.getInstance().getModelById(maSV);

                if (currentMember == null) {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy thành viên");
                    return;
                } else if (currentMember != null) {
                    Timestamp timestamp = new Timestamp(System.currentTimeMillis());

                    Calendar cal = Calendar.getInstance();
                    cal.setTimeInMillis(timestamp.getTime());
                    cal.add(Calendar.HOUR_OF_DAY, 7);

                    Timestamp newTimestamp = new Timestamp(cal.getTimeInMillis());

                    BigInteger curSV = new BigInteger(inputMaTV.getText());
                    thongtinsd curInfo = new thongtinsd(curSV, null, newTimestamp, null, null);

                    int result = thongtinsdBLL.getInstance().addModel(curInfo);

                    if (result > 0) {
                        JOptionPane.showMessageDialog(null, "Mã số: " + curSV + "\n"
                                + "Tên thành viên: " + currentMember.getHoTen() + "\n"
                                + "Khoa: " + currentMember.getKhoa() + "\n"
                                + "Ngành: " + currentMember.getNganh() + "\n"
                                + "SĐT: " + currentMember.getSdt());
                    }
                }
            }
        });

        constraints.gridx = 0;
        constraints.gridy = 0;
        pnlCheckIn.add(lbMaTV, constraints);

        constraints.gridx = 1;
        pnlCheckIn.add(inputMaTV, constraints);

        constraints.gridy = 1;
        pnlCheckIn.add(btnCheckIn, constraints);

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
        add(pnlAction, BorderLayout.EAST);
        add(pnlCheckIn, BorderLayout.WEST);

        // Event listener
        // Btn Add event listener
        btnAdd.addActionListener(e -> {
            new ThemThanhVienForm();
        });

        // Btn Borrow event listener
        btnBorrow.addActionListener(e -> {
            int index = table.getSelectedRow();

            if (index == -1) {
                JOptionPane.showMessageDialog(null, "Chọn thành viên để cho mượn thiết bị");
                return;
            } else {
                BigInteger maSV = (BigInteger) table.getModel().getValueAt(index, 0);
                new MuonThietBiForm(maSV);
            }
        });

        // Btn Return event listener
        btnReturn.addActionListener(e -> {
            int index = table.getSelectedRow();

            if (index == -1) {
                JOptionPane.showMessageDialog(null, "Chọn thành viên để xem thời gian trả thiết bị");
                return;
            } else {
                BigInteger maSV = (BigInteger) table.getModel().getValueAt(index, 0);
                new TraThietBiForm(maSV);
            }
        });

        btnDel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] selectedRows = table.getSelectedRows();

                if (selectedRows.length == 0) {
                    JOptionPane.showMessageDialog(null, "Bạn chưa chọn dòng muốn xóa", "Thông báo",
                            JOptionPane.INFORMATION_MESSAGE);
                    return;
                } else {
                    int choice = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa thành viên này?");
                    if (choice == JOptionPane.YES_OPTION) {
                        for (int index : selectedRows) {
                            BigInteger id = (BigInteger) table.getValueAt(index, 0);
                            deleteMember(id);
                        }
                        updateMemberFromList();
                    }
                }
            }
        });

        btnRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateMemberFromList();
                clearForm();
            }
        });

        table.getDefaultEditor(String.class).addCellEditorListener(new CellEditorListener() {
            public void editingStopped(ChangeEvent e) {
                int selectedRow = table.getSelectedRow();
                Object maTV = table.getValueAt(selectedRow, 0);
                Object hoTen = table.getValueAt(selectedRow, 1);
                Object khoa = table.getValueAt(selectedRow, 2);
                Object nganh = table.getValueAt(selectedRow, 3);
                Object sdt = table.getValueAt(selectedRow, 4);

                thanhvien updateMember = new thanhvien(new BigInteger(maTV.toString()), hoTen.toString(),
                        khoa.toString(), nganh.toString(), sdt.toString());

                BigInteger result = thanhvienBLL.getInstance().updateModel(updateMember);

                if (result.compareTo(BigInteger.ZERO) > 0) {
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

        searchInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String searchValue = searchInput.getText().trim();
                    List<thanhvien> searchResult = thanhvienBLL.getInstance().searchListThanhVien(searchValue);
                    showSearchResult(searchResult);
                }
            }
        });

        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchValue = searchInput.getText().trim();
                List<thanhvien> searchResult = thanhvienBLL.getInstance().searchListThanhVien(searchValue);
                showSearchResult(searchResult);
            }
        });

        btnExportExcel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    thanhvienExcelUtil.writethanhviensToExcel(thanhvienBLL.getInstance().getAllModels());
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        updateMemberFromList();
    }

    public void updateMemberFromList() {
        String handleStatus = null;
        thanhvienBLL.getInstance().refresh();
        DefaultTableModel model_table = (DefaultTableModel) table.getModel();
        model_table.setRowCount(0);

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);

        for (thanhvien member : thanhvienBLL.getInstance().getAllModels()) {
            for (xuly handle : xulyBLL.getInstance().getAllModels()) {
                if (member.getMaTV().equals(handle.getMaTV())) {
                    handleStatus = handle.getHinhThucXL();
                }
            }

            model_table.addRow(new Object[] {
                    member.getMaTV(),
                    member.getHoTen(),
                    member.getKhoa(),
                    member.getNganh(),
                    member.getSdt(),
                    handleStatus == null ? "Không vi phạm" : handleStatus
            });
        }
    }

    public void clearForm() {
        searchInput.setText("");
        inputMaTV.setText("");
    }

    public void deleteMember(BigInteger id) {
        try {
            if (thanhvienBLL.getInstance().deleteModel(id)) {
                JOptionPane.showMessageDialog(null, "Xóa thành công");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Xóa thất bại");
        }
    }

    public void showSearchResult(List<thanhvien> search) {
        String handleStatus = null;
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        for (thanhvien member : search) {
            for (xuly handle : xulyBLL.getInstance().getAllModels()) {
                if (member.getMaTV().equals(handle.getMaTV())) {
                    handleStatus = handle.getHinhThucXL();
                }
            }

            model.addRow(new Object[] {
                    member.getMaTV(),
                    member.getHoTen(),
                    member.getKhoa(),
                    member.getNganh(),
                    member.getSdt(),
                    handleStatus == null ? "Không vi phạm" : handleStatus
            });
        }

        if (search.size() == 0) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy kết quả");
            // Refresh table:
            updateMemberFromList();
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();

        frame.add(new QLThanhVienPanel());

        frame.setVisible(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
