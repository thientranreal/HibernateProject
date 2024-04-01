package com.project.GUI.Forms.QLThietBi;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.project.BLL.thietbiBLL;
import com.project.BLL.thongtinsdBLL;
import com.project.GUI.Components.FormLabel;
import com.project.GUI.Components.FormPanel;
import com.project.GUI.Components.Table;
import com.project.GUI.Components.Buttons.ButtonAdd;
import com.project.GUI.Components.Buttons.ButtonDel;
import com.project.GUI.Components.Buttons.ButtonExcel;
import com.project.GUI.Components.Buttons.ButtonRefresh;
import com.project.GUI.Components.Buttons.ButtonSearch;
import com.project.GUI.Components.TextFields.SearchField;
import com.project.GUI.GlobalVariables.Colors;
import com.project.GUI.GlobalVariables.Fonts;
import com.project.models.thietbi;
import com.project.models.thongtinsd;
import com.project.utilities.excel.thietbiExcelUtil;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Date; // Add missing import
import java.util.HashMap;
import java.util.Map;

public class QLThietBiPanel extends FormPanel {

    private final Table table;
    public Map<Integer, String> deviceAvailabilityMap = new HashMap<>();
    private final SearchField searchInput;
    public static int maTB;

    public QLThietBiPanel() {
        // Add constraints to make button align vertically
        GridBagConstraints constraints = new GridBagConstraints();
        // Add padding bottom 10px
        constraints.insets = new Insets(0, 0, 10, 0);

        // Set GridBagLayout layout
        setLayout(new BorderLayout());

        // Create Header
        JPanel pnlHeader = new FormPanel();
        JLabel lbHeader = new FormLabel("Quản lý thiết bị");
        lbHeader.setFont(Fonts.headerFont);
        lbHeader.setForeground(java.awt.Color.BLACK);
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
                new String[] { "Mã TB",
                        "Tên thiết bị",
                        "Mô tả",
                        "Đang được mượn",
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
        JButton btnExportExcel = new ButtonExcel();
        btnExportExcel.setText("Export");

        // Create panel to contain button
        JPanel pnlAction = new FormPanel();
        pnlAction.setLayout(new GridBagLayout());

        int y = 0;
        constraints.gridx = 0;
        constraints.gridy = y++;
        pnlAction.add(btnAdd, constraints);

        constraints.gridy = y++;
        pnlAction.add(btnDel, constraints);

        constraints.gridy = y;
        pnlAction.add(btnExportExcel, constraints);

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

        btnAdd.addActionListener(e -> {
            new ThemThietBiForm();
        });

        btnDel.addActionListener(e -> {
            int index = table.getSelectedRow();

            if (index == -1) {
                JOptionPane.showMessageDialog(null, "Bạn chưa chọn dòng muốn xóa", "Thông báo",
                        JOptionPane.INFORMATION_MESSAGE);
                return;
            } else {
                int choice = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa thiết bị này?");
                if (choice == JOptionPane.YES_OPTION) {
                    int thietbiId = (int) table.getModel().getValueAt(index, 0);
                    deleteThietBi(thietbiId);
                    updateThietBiFromList();
                }
            }
        });

        btnRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateThietBiFromList();
                clearForm();
            }
        });

        table.getDefaultEditor(String.class).addCellEditorListener(new CellEditorListener() {
            public void editingStopped(ChangeEvent e) {
                int selectedRow = table.getSelectedRow();
                Object maTB = table.getValueAt(selectedRow, 0);
                Object tenTB = table.getValueAt(selectedRow, 1);
                Object moTa = table.getValueAt(selectedRow, 2);

                thietbi updateThietBi = new thietbi();
                updateThietBi.setMaTB((int) maTB);
                updateThietBi.setTenTB((String) tenTB);
                updateThietBi.setMoTaTB((String) moTa);

                boolean result = thietbiBLL.getInstance().updateModel(updateThietBi);
                if (result) {
                    JOptionPane.showMessageDialog(null, "Lưu thành công");
                    updateThietBiFromList();
                } else {
                    JOptionPane.showMessageDialog(null, "Lưu thất bại");
                }
            }

            public void editingCanceled(ChangeEvent e) {
                System.out.println("Editing canceled");
            }
        });

        btnSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String searchValue = searchInput.getText().trim();
                    List<thietbi> searchResult = thietbiBLL.getInstance().searchListThietBi(searchValue);
                    showSearchResult(searchResult);
                }
            }
        });

        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchValue = searchInput.getText().trim();
                List<thietbi> searchResult = thietbiBLL.getInstance().searchListThietBi(searchValue);
                showSearchResult(searchResult);
            }
        });

        btnExportExcel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    thietbiExcelUtil.writeThietbisToExcel(thietbiBLL.getInstance().getAllModels());
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

    }

    public void updateThietBiFromList() {
        deviceAvailabilityMap.clear();
        thongtinsdBLL.getInstance().refresh();
        thietbiBLL.getInstance().refresh();
        DefaultTableModel model_table = (DefaultTableModel) table.getModel();
        model_table.setRowCount(0);

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);

        List<thongtinsd> allInfo = thongtinsdBLL.getInstance().getAllModels();
        Date timestamp = new Date(); // Move timestamp variable outside the loop

        // Use HashMap to store device availability

        for (thongtinsd info : allInfo) {
            if (info.getThietbi() != null) {
                if (timestamp.compareTo(info.getTGMuon()) >= 0 && info.getTGTra() == null) {
                    // Set string true = "Đang mượn":
                    deviceAvailabilityMap.put(info.getThietbi(), "Có"); // Set device availability to true
                }
            }
        }

        for (thietbi device : thietbiBLL.getInstance().getAllModels()) {
            // Set string false = "Không mượn":
            String isDeviceAvailable = deviceAvailabilityMap.getOrDefault(device.getMaTB(), "Không"); // Get device
                                                                                                      // availability
                                                                                                      // from the
                                                                                                      // map

            model_table.addRow(new Object[] {
                    device.getMaTB(),
                    device.getTenTB(),
                    device.getMoTaTB(),
                    isDeviceAvailable // Add device availability to the rowd
            });
        }
    }

    public void clearForm() {
        searchInput.setText("");
    }

    public void deleteThietBi(int id) {
        try {
            if (thietbiBLL.getInstance().deleteModel(id)) {
                JOptionPane.showMessageDialog(null, "Xóa thành công");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Xóa thất bại");
        }
    }

    public void showSearchResult(List<thietbi> search) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        for (thietbi thietbi : search) {

            model.addRow(new Object[] {
                    thietbi.getMaTB(),
                    thietbi.getTenTB(),
                    thietbi.getMoTaTB(),
            });
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();

        frame.add(new QLThietBiPanel());

        frame.setVisible(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
