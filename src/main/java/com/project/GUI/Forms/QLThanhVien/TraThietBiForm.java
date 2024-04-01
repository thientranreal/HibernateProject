package com.project.GUI.Forms.QLThanhVien;

import com.project.BLL.thietbiBLL;
import com.project.BLL.thongtinsdBLL;
import com.project.GUI.Components.Buttons.*;
import com.project.GUI.GlobalVariables.Colors;
import com.project.GUI.Components.FormLabel;
import com.project.GUI.Components.FormPanel;
import com.project.GUI.Components.Table;
import com.project.GUI.Components.TextFields.SearchField;
import com.project.GUI.GlobalVariables.Fonts;
import com.project.models.thietbi;
import com.project.models.thongtinsd;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

public class TraThietBiForm extends JFrame {
    private Point mouseDownCompCoords;
    private static BigInteger currentSV;
    private Table table;

    public TraThietBiForm(BigInteger maSV) {
        currentSV = maSV;
        // Add Content into JFrame
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

        // Create Header
        JPanel pnlHeader = new FormPanel();
        JLabel lbHeader = new FormLabel("Trả thiết bị");
        lbHeader.setFont(Fonts.headerFont);
        lbHeader.setForeground(Color.BLACK);
        pnlHeader.add(lbHeader);

        // Create label danh sach thiet bi
        JLabel lbDSThietBi = new FormLabel("Danh sách thiết bị đang mượn");

        // Create table for showing data
        table = new Table();
        // Create header for table
        table.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] { "Mã TB",
                        "Tên TB",
                        "Mô tả",
                        "Thời gian mượn"
                }) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == getColumnCount();
            }
        });

        // Create panel to contain table
        JScrollPane pnlTable = new JScrollPane();
        pnlTable.setBorder(new EmptyBorder(10, 10, 10, 10));
        pnlTable.setViewportView(table);
        pnlTable.setBackground(Colors.bgColor);

        // Create panel to contain show data
        JPanel pnlDS = new FormPanel();
        pnlDS.setLayout(new GridBagLayout());
        // Add constraints
        GridBagConstraints constraints = new GridBagConstraints();
        // Add padding bottom 10px
        constraints.insets = new Insets(0, 0, 10, 0);

        // Row 0
        constraints.gridx = 0;
        constraints.gridy = 0;
        pnlDS.add(lbDSThietBi, constraints);

        // Row 1
        constraints.gridy = 1;
        // Create search input, button search, button refresh
        JTextField searchInput = new SearchField(20);
        JButton btnSearch = new ButtonSearch();
        JButton btnRefresh = new ButtonRefresh();

        // Create panel to contain search input
        JPanel pnlSearch = new FormPanel();
        pnlSearch.add(searchInput);
        pnlSearch.add(btnSearch);
        pnlSearch.add(btnRefresh);
        // Add panel search into pnlDS
        pnlDS.add(pnlSearch, constraints);

        // Row 2
        constraints.gridy = 2;
        pnlDS.add(pnlTable, constraints);

        // Create button
        JButton btnSave = new ButtonSave();
        JButton btnCancel = new ButtonCancel();

        // Create panel to contain button
        JPanel pnlBtn = new FormPanel();
        pnlBtn.add(btnSave);
        pnlBtn.add(btnCancel);

        // Add all panel to this panel
        root.add(pnlHeader, BorderLayout.PAGE_START);
        root.add(pnlDS, BorderLayout.CENTER);
        root.add(pnlBtn, BorderLayout.SOUTH);

        // Add event listener for cancel button
        btnCancel.addActionListener(e -> {
            dispose();
        });

        searchInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String searchValue = searchInput.getText().trim();
                    List<thongtinsd> searchResult = thongtinsdBLL.getInstance().searchThongTinSdByCriteria(searchValue,
                            null);
                    showSearchResult(searchResult);
                }
            }
        });

        btnRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchInput.setText("");
                updateReturnFromList();
            }
        });

        btnSave.addActionListener(e -> {
            int[] selectedRows = table.getSelectedRows();

            if (selectedRows.length == 0) {
                JOptionPane.showMessageDialog(null, "Bạn chưa chọn dòng muốn trả thiết bị", "Thông báo",
                        JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            for (int index : selectedRows) {
                int maTB = (int) table.getValueAt(index, 0);
                boolean result = traThietBi(currentSV, maTB);
                if (result) {
                    JOptionPane.showMessageDialog(null, "Trả thiết bị thành công", "Thông báo",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Trả thiết bị thất bại", "Thông báo",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }

            updateReturnFromList();
            dispose();
        });

        updateReturnFromList();
        return root;
    }

    private boolean traThietBi(BigInteger currentSV, int maTB) {
        boolean updated = false;
        for (thongtinsd info : thongtinsdBLL.getInstance().getAllModels()) {
            if (currentSV.equals(info.getThanhvien()) && info.getThietbi() != null && info.getThietbi() == maTB
                    && info.getTGMuon() != null) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                calendar.add(Calendar.HOUR_OF_DAY, 7);
                Timestamp newTimestamp = new Timestamp(calendar.getTimeInMillis());
                info.setTGTra(newTimestamp);
                int result = thongtinsdBLL.getInstance().updateModel(info);
                if (result > 0) {
                    updated = true;
                }
            }
        }
        return updated;
    }

    public void updateReturnFromList() {
        thongtinsdBLL.getInstance().refresh();
        thietbiBLL.getInstance().refresh();
        DefaultTableModel model_table = (DefaultTableModel) table.getModel();
        model_table.setRowCount(0);

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);

        List<thongtinsd> currentList = thongtinsdBLL.getInstance().getAllModels();

        for (thongtinsd info : currentList) {
            if (info.getTGTra() != null) {
                continue;
            }
            if (currentSV.equals(info.getThanhvien()) && info.getThietbi() != null
                    && info.getTGMuon() != null) {
                thietbi device = thietbiBLL.getInstance().getModelById(info.getThietbi());
                model_table.addRow(new Object[] {
                        device.getMaTB(),
                        device.getTenTB(),
                        device.getMoTaTB(),
                        // Get TGMuon from database:
                        info.getTGMuon()
                });
            }
        }
    }

    public void showSearchResult(List<thongtinsd> search) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);

        for (thietbi device : thietbiBLL.getInstance().getAllModels()) {
            for (thongtinsd info : thongtinsdBLL.getInstance().getAllModels()) {
                if (currentSV.equals(info.getThanhvien()) && info.getThietbi() != null
                        && info.getThietbi() == device.getMaTB()) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(info.getTGTra().getTime());

                    calendar.add(Calendar.HOUR_OF_DAY, -7);

                    Timestamp newTimestamp = new Timestamp(calendar.getTimeInMillis());

                    model.addRow(new Object[] {
                            device.getMaTB(),
                            device.getTenTB(),
                            device.getMoTaTB(),
                            newTimestamp
                    });
                }
            }
        }
    }

    public static void main(String[] args) {
        new TraThietBiForm(currentSV);
    }
}
