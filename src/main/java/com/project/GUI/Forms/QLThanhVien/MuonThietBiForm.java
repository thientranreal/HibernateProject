package com.project.GUI.Forms.QLThanhVien;

import com.project.BLL.thanhvienBLL;
import com.project.BLL.thietbiBLL;
import com.project.BLL.thongtinsdBLL;
import com.project.BLL.xulyBLL;
import com.project.GUI.GlobalVariables.Colors;
import com.project.GUI.Components.Buttons.*;
import com.project.GUI.Components.FormLabel;
import com.project.GUI.Components.FormPanel;
import com.project.GUI.Components.Table;
import com.project.GUI.Components.TextFields.SearchField;
import com.project.GUI.GlobalVariables.Fonts;
import com.project.models.thanhvien;
import com.project.models.thietbi;
import com.project.models.thongtinsd;
import com.project.models.xuly;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.math.BigInteger;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

public class MuonThietBiForm extends JFrame {
    private Point mouseDownCompCoords;

    public static BigInteger currentSV;
    private Table table;

    public MuonThietBiForm(BigInteger maSV) {
        currentSV = maSV;
//        Add Content into JFrame
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
        QLThanhVienPanel form = new QLThanhVienPanel();

        JPanel root = new FormPanel();
        root.setLayout(new BorderLayout());

        root.setBorder(BorderFactory.createLineBorder(Colors.primaryColor, 5));

//        Create Header
        JPanel pnlHeader = new FormPanel();
        JLabel lbHeader = new FormLabel("Mượn thiết bị");
        lbHeader.setFont(Fonts.headerFont);
        lbHeader.setForeground(Color.BLACK);
        pnlHeader.add(lbHeader);

//        Create label danh sach thiet bi
        JLabel lbDSThietBi = new FormLabel("Danh sách thiết bị có thể mượn");

//        Create table for showing data
        table = new Table();
//        Create header for table
        table.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {"Mã TB",
                        "Tên TB",
                        "Mô tả",
                }) {
                           @Override
                           public boolean isCellEditable(int row, int column) {
                               return column == getColumnCount();
                           }
                       }
        );

//        Create panel to contain table
        JScrollPane pnlTable = new JScrollPane();
        pnlTable.setBorder(new EmptyBorder(10, 10, 10, 10));
        pnlTable.setViewportView(table);
        pnlTable.setBackground(Colors.bgColor);

//        Create panel to contain show data
        JPanel pnlDS = new FormPanel();
        pnlDS.setLayout(new GridBagLayout());
//        Add constraints
        GridBagConstraints constraints = new GridBagConstraints();
//          Add padding bottom 10px
        constraints.insets = new Insets(0, 0, 10, 0);

//        Row 0
        constraints.gridx = 0;
        constraints.gridy = 0;
        pnlDS.add(lbDSThietBi, constraints);

//        Row 1
        constraints.gridy = 1;
//        Create search input, button search, button refresh
        JTextField searchInput = new SearchField(20);
        JButton btnSearch = new ButtonSearch();
        JButton btnRefresh = new ButtonRefresh();

//        Create panel to contain search input
        JPanel pnlSearch = new FormPanel();
        pnlSearch.add(searchInput);
        pnlSearch.add(btnSearch);
        pnlSearch.add(btnRefresh);
//        Add panel search into pnlDS
        pnlDS.add(pnlSearch, constraints);

//        Row 2
        constraints.gridy = 2;
        pnlDS.add(pnlTable, constraints);


//        Create button
        JButton btnSave = new ButtonSave();
        JButton btnCancel = new ButtonCancel();

//        Create panel to contain button
        JPanel pnlBtn = new FormPanel();
        pnlBtn.add(btnSave);
        pnlBtn.add(btnCancel);

//        Add all panel to this panel
        root.add(pnlHeader, BorderLayout.PAGE_START);
        root.add(pnlDS, BorderLayout.CENTER);
        root.add(pnlBtn, BorderLayout.SOUTH);

//        Add event listener for cancel button
        btnCancel.addActionListener(e -> {
            dispose();
        });

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = table.getSelectedRow();

                if (index == -1) {
                    JOptionPane.showMessageDialog(null, "Bạn chưa chọn dòng muốn mượn thiết bị", "Thông báo",
                            JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                int maTB = (int) table.getModel().getValueAt(index, 0);
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(timestamp.getTime());

                calendar.add(Calendar.HOUR_OF_DAY, 7);

                if(isBorrowed(maTB,timestamp)) {
                    JOptionPane.showMessageDialog(null,"Bạn đang mượn thiết bị này");
                    dispose();
                    return;
                }

                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(timestamp.getTime());
                cal.add(Calendar.HOUR_OF_DAY, 9); // set current payback time is current time(hour) + 2

                Timestamp paybackTimestamp = new Timestamp(cal.getTime().getTime());

                thongtinsd curInfo = new thongtinsd(currentSV,maTB,null,timestamp,paybackTimestamp);
                int result = thongtinsdBLL.getInstance().addModel(curInfo);

                if(result > 0) {
                    JOptionPane.showMessageDialog(null,"Mượn thiết bị thành công");
                    dispose();
                }else {
                    JOptionPane.showMessageDialog(null,"Mượn thiết bị thất bại");
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
                    List<thietbi> searchResult = thietbiBLL.getInstance().searchListThietBi(searchValue);
                    showSearchResult(searchResult);
                }
            }
        });

        updateBorrowFromList();
        return root;
    }

    public boolean isBorrowed(int maTB, Timestamp timestamp) {
        for(thongtinsd info : thongtinsdBLL.getInstance().getAllModels()) {
            if(currentSV.equals(info.getThanhvien()) && (maTB == info.getThietbi()) && (timestamp.compareTo(info.getTGMuon()) >= 0 && timestamp.compareTo(info.getTGTra()) <= 0)) {
                return true;
            }
        }
        return false;
    }

    public void updateBorrowFromList() {
        thietbiBLL.getInstance().refresh();
        DefaultTableModel model_table = (DefaultTableModel) table.getModel();
        model_table.setRowCount(0);

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);

        for (thietbi device : thietbiBLL.getInstance().getAllModels()) {
            model_table.addRow(new Object[]{
                    device.getMaTB(),
                    device.getTenTB(),
                    device.getMoTaTB(),
            });

        }
    }

    public void showSearchResult(List<thietbi> search) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        for (thietbi device : search) {
            model.addRow(new Object[]{
                    device.getMaTB(),
                    device.getTenTB(),
                    device.getMoTaTB(),
            });
        }
    }
    



    public static void main(String[] args) {
        new MuonThietBiForm(currentSV);
    }
}
