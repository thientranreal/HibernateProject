package com.project.GUI.Forms.QLThanhVien;

import com.github.lgooddatepicker.components.DateTimePicker;
import com.project.BLL.thietbiBLL;
import com.project.BLL.thongtinsdBLL;
import com.project.GUI.Components.Table.TableCustom;
import com.project.GUI.GlobalVariables.Colors;
import com.project.GUI.Components.Buttons.*;
import com.project.GUI.Components.FormLabel;
import com.project.GUI.Components.FormPanel;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MuonThietBiForm extends JFrame {
    private Point mouseDownCompCoords;
    private Map<Integer, Boolean> deviceAvailabilityMap = new HashMap<>();
    public static BigInteger currentSV;
    private JTable table;

    public MuonThietBiForm(BigInteger maSV) {
        currentSV = maSV;
        // Add Content into JFrame
        add(initComponent());

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

    private JPanel initComponent() {
        JPanel root = new FormPanel();
        root.setLayout(new BorderLayout());

        root.setBorder(BorderFactory.createLineBorder(Colors.primaryColor, 5));

        // Create Header
        JPanel pnlHeader = new FormPanel();
        JLabel lbHeader = new FormLabel("Mượn thiết bị");
        lbHeader.setFont(Fonts.headerFont);
        lbHeader.setForeground(Color.BLACK);
        pnlHeader.add(lbHeader);

        // Create label danh sach thiet bi
        JLabel lbDSThietBi = new FormLabel("Danh sách thiết bị có thể mượn");

        // Create table for showing data
        table = new JTable();
        // Create header for table
        table.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] { "Mã TB",
                        "Tên TB",
                        "Mô tả",
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

        TableCustom.apply(pnlTable, TableCustom.TableType.MULTI_LINE);

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

        constraints.anchor = GridBagConstraints.WEST;
        JLabel lbReturnDate = new FormLabel("Ngày trả");
        DateTimePicker dpkReturnDate = new DateTimePicker();
        pnlSearch.add(lbReturnDate);
        pnlSearch.add(dpkReturnDate);
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

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] selectedRows;

                if (table.getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(null, "Bạn chưa chọn dòng muốn mượn thiết bị", "Thông báo",
                            JOptionPane.INFORMATION_MESSAGE);
                    return;
                } else if (table.getSelectedRow() == 1) {
                    selectedRows = new int[] { table.getSelectedRow() };
                } else {
                    selectedRows = table.getSelectedRows();
                }

                for (int index : selectedRows) {
                    int maTB = (int) table.getModel().getValueAt(index, 0);
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(System.currentTimeMillis());
                    calendar.add(Calendar.HOUR_OF_DAY, 7);
                    Timestamp newTimestamp = new Timestamp(calendar.getTimeInMillis());

                    calendar.add(Calendar.HOUR_OF_DAY, 2);
                    Timestamp paybackTimestamp = new Timestamp(calendar.getTimeInMillis());

                    for(thongtinsd info : thongtinsdBLL.getInstance().getAllModels()) {
                        if(info.getTGMuon() != null && info.getTGTra() != null) {
                            if((maTB == info.getThietbi())) {
                                Timestamp muonTime = info.getTGMuon();
                                Timestamp traTime = info.getTGTra();

                                if (newTimestamp.after(muonTime) && newTimestamp.before(traTime)) {
                                    JOptionPane.showMessageDialog(null, "Thiết bị này đang được mượn");
                                    return;
                                }
                            }
                        }
                    }

                    thongtinsd curInfo = new thongtinsd(currentSV, maTB, null, newTimestamp, paybackTimestamp);
                    int result = thongtinsdBLL.getInstance().addModel(curInfo);

                    if (result > 0) {
                        JOptionPane.showMessageDialog(null, "Mượn thiết bị thành công");
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Mượn thiết bị thất bại");
                    }
                }
            }
        });

        btnRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchInput.setText("");
                updateBorrowFromList();
            }
        });

        searchInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String searchValue = searchInput.getText().trim();
                    showSearchResult(searchValue);
                }
            }
        });

        btnSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String searchValue = searchInput.getText().trim();
                    showSearchResult(searchValue);
                }
            }
        });
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchValue = searchInput.getText().trim();
                showSearchResult(searchValue);
            }
        });

        updateBorrowFromList();
        return root;
    }

    // Students can borrow multiple devices at the same time, when completing the
    // borrowing process,
    // students will return the device and there will be a return time,
    // if there is no return time, the student is still borrowing the device
    // Students can have usage information
    // but will not have MaTB if not borrowing a device
    public void updateBorrowFromList() {
        deviceAvailabilityMap.clear();
        thongtinsdBLL.getInstance().refresh();
        thietbiBLL.getInstance().refresh();
        DefaultTableModel model_table = (DefaultTableModel) table.getModel();
        model_table.setRowCount(0);

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);

        List<thongtinsd> allInfo = thongtinsdBLL.getInstance().getAllModels();

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.HOUR_OF_DAY, 7);
        Timestamp newTimestamp = new Timestamp(calendar.getTimeInMillis());

        // Use HashMap to store device availability
        for (thongtinsd info : allInfo) {
            if (info.getThietbi() != null) {
                if (info.getTGMuon() != null && info.getTGTra() != null) {
                    if (newTimestamp.after(info.getTGMuon()) && newTimestamp.before(info.getTGTra())) {
                        deviceAvailabilityMap.put(info.getThietbi(), true); // Set device availability to true
                    }
                }
            }
        }

        for (thietbi device : thietbiBLL.getInstance().getAllModels()) {
            boolean isDeviceAvailable = deviceAvailabilityMap.getOrDefault(device.getMaTB(), false); // Get device
                                                                                                     // availability
                                                                                                     // from the map
            if (!isDeviceAvailable) {
                model_table.addRow(new Object[] {
                        device.getMaTB(),
                        device.getTenTB(),
                        device.getMoTaTB(),
                        isDeviceAvailable // Add device availability to the row
                });
            }
        }
    }

    // Only show available devices
    // Based on updateBorrowFromList, make show search result:
    public void showSearchResult(String value) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        for (thietbi device : thietbiBLL.getInstance().getAllModels()) {
            boolean isDeviceAvailable = deviceAvailabilityMap.getOrDefault(device.getMaTB(), true); // Get device
                                                                                                    // availability from
                                                                                                    // the map
            if (isDeviceAvailable && (device.getTenTB().toLowerCase().contains(value.toLowerCase())
                    || device.getMoTaTB().toLowerCase().contains(value.toLowerCase())
                    || String.valueOf(device.getMaTB()).contains(value))) {
                model.addRow(new Object[] {
                        device.getMaTB(),
                        device.getTenTB(),
                        device.getMoTaTB(),
                });
            }
        }
        if (model.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy thiết bị", "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE);
            updateBorrowFromList();
        }
    }

    public static void main(String[] args) {
        new MuonThietBiForm(currentSV);
    }
}
