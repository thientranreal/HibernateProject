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
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.swing.table.DefaultTableCellRenderer;

public class TKThietBi extends FormPanel {
    
    public Map<Integer, String> deviceAvailabilityMap = new HashMap<>();
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
                        "Thời gian mượn",
                        "Thời gian Trả"
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
        
        btnSearch.addActionListener((e) -> {
            String searchValue = inputSearch.getText().trim();
            String Month = cbMonth.getSelectedItem().toString();
            if (searchValue != null && "".equals(Month)) {
                List<thietbi> Tbbyname = thietbiBLL.getInstance().searchListThietBibyName(searchValue);
                showSearchResult(Tbbyname);
            }else if (searchValue == null && !"".equals(Month)) {
                List<thietbi> Tbbymonth = thietbiBLL.getInstance().searchListThietBibyMonth(Month);
                showSearchResult(Tbbymonth);
            }// nó k hiện search
            
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
            updateThietbiDaMuon();
          
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
                            "Thời gian trả",
                    }){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }});
            updateThietbiDangMuon();
        }
    };
    
    public void updateThietbiFromList() {
        deviceAvailabilityMap.clear();
        thongtinsdBLL.getInstance().refresh();
        thietbiBLL.getInstance().refresh();
        DefaultTableModel model_table = (DefaultTableModel) table.getModel();
        model_table.setRowCount(0);

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);

        java.util.List<thongtinsd> allInfo = thongtinsdBLL.getInstance().getAllModels();

        Map<Integer, BigInteger> borrowedStudentId = new HashMap<>();

        String ngayMuon = "";String ngayTra = "";
        
        // Use HashMap to store device availability
        for (thongtinsd info : allInfo) {
            if (info.getThietbi() != null) {
                if (info.getTGMuon() != null && info.getTGTra() == null) {
                    borrowedStudentId.put(info.getThietbi(), info.getThanhvien());
                    // Set string true = "Đang mượn":
                    deviceAvailabilityMap.put(info.getThietbi(), "Có"); // Set device availability to true
                }
                ngayMuon = info.getTGMuon().toString();
                ngayTra = info.getTGTra().toString();
            }
            
        }

        for (thietbi device : thietbiBLL.getInstance().getAllModels()) {
            // Set string false = "Không mượn":
            String isDeviceAvailable = deviceAvailabilityMap.getOrDefault(device.getMaTB(), "Không"); // Get device
                                                                                                      // availability
                                                                                                      // from the map

            String studentId = "Không";
            if (borrowedStudentId.containsKey(device.getMaTB())) {
                studentId = borrowedStudentId.get(device.getMaTB()).toString();
            }

            model_table.addRow(new Object[] {
                    device.getMaTB(),
                    device.getTenTB(),
                    device.getMoTaTB(),
                    isDeviceAvailable,
                    studentId,
                    ngayMuon,
                    ngayTra
            });
        }
    }
    public void updateThietbiDaMuon() {
        deviceAvailabilityMap.clear();
        thongtinsdBLL.getInstance().refresh();
        thietbiBLL.getInstance().refresh();
        DefaultTableModel model_table = (DefaultTableModel) table.getModel();
        model_table.setRowCount(0);

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);

        java.util.List<thongtinsd> allInfo = thongtinsdBLL.getInstance().getAllModels();

        Map<Integer, BigInteger> borrowedStudentId = new HashMap<>();

        String ngayMuon = "";String ngayTra = "";
        // Use HashMap to store device availability
        for (thongtinsd info : allInfo) {
            if (info.getThietbi() != null) {
                if (info.getTGMuon() != null) {
                    borrowedStudentId.put(info.getThietbi(), info.getThanhvien());
                    // Set string true = "Đang mượn":
                    deviceAvailabilityMap.put(info.getThietbi(), "Có"); // Set device availability to true
                }
                ngayMuon = info.getTGMuon().toString();
                ngayTra = info.getTGTra().toString();
            }
            
        }

        for (thietbi device : thietbiBLL.getInstance().getAllModels()) {
            // Set string false = "Không mượn":
            String isDeviceAvailable = deviceAvailabilityMap.getOrDefault(device.getMaTB(), "Không"); // Get device
                                                                                                      // availability
                                                                                                      // from the map

            String studentId = "Không";
            if (borrowedStudentId.containsKey(device.getMaTB())) {
                studentId = borrowedStudentId.get(device.getMaTB()).toString();
            }

            model_table.addRow(new Object[] {
                    device.getMaTB(),
                    device.getTenTB(),
                    device.getMoTaTB(),
                    isDeviceAvailable, // Add device availability to the row
                    studentId,
                    ngayMuon,
                    ngayTra
            });
        }
    }
    
    public void updateThietbiDangMuon() {
        deviceAvailabilityMap.clear();
        thongtinsdBLL.getInstance().refresh();
        thietbiBLL.getInstance().refresh();
        DefaultTableModel model_table = (DefaultTableModel) table.getModel();
        model_table.setRowCount(0);

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);

        java.util.List<thongtinsd> allInfo = thongtinsdBLL.getInstance().getAllModels();

        Map<Integer, BigInteger> borrowedStudentId = new HashMap<>();
        List<thongtinsd> listTTSD = thongtinsdBLL.getInstance().getAllModels();

        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis()); 
        // Use HashMap to store device availability
        
        for (thongtinsd info : allInfo) {
            if (info.getThietbi() != null) {
                if (info.getTGTra().compareTo(currentTimestamp) > 0) {
                    borrowedStudentId.put(info.getThietbi(), info.getThanhvien());
                    // Set string true = "Đang mượn":
                    deviceAvailabilityMap.put(info.getThietbi(), "Có"); // Set device availability to true
                }
            }
            
        }

        for (thietbi device : thietbiBLL.getInstance().getAllModels()) {
            // Set string false = "Không mượn":
            String isDeviceAvailable = deviceAvailabilityMap.getOrDefault(device.getMaTB(), "Không"); // Get device
            String ngayTra ="";                                                                       // availability
                                                                                                      // from the map

            String studentId = "Không";
            if (borrowedStudentId.containsKey(device.getMaTB())) {
                studentId = borrowedStudentId.get(device.getMaTB()).toString();
            }
            for(thongtinsd ttsd : listTTSD){
                if (ttsd.getThietbi().equals(device.getMaTB())) {
                    ngayTra = ttsd.getTGTra().toString();
                }
                
            }

            model_table.addRow(new Object[] {
                    device.getMaTB(),
                    device.getTenTB(),
                    device.getMoTaTB(),
                    isDeviceAvailable, // Add device availability to the row
                    studentId,
                    ngayTra
            });
        }
    }// không hiểu vì sao không thể get value trong get thiết bị
    
    public void showSearchResult(java.util.List<thietbi> search) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        
        for (thietbi tb : search) {
            for(thongtinsd ttsd : thongtinsdBLL.getInstance().getAllModels()){
                for(thanhvien tv : thanhvienBLL.getInstance().getAllModels()){
                    if (tb.getMaTB() == ttsd.getThietbi() && ttsd.getThanhvien() == tv.getMaTV()) {
                        String maTV = ttsd.getThanhvien().toString();
                        String HoTen = tv.getHoTen();
                        String NgayMuon = ttsd.getTGMuon().toString();
                        String NgayTra = ttsd.getTGTra().toString();
                        model.addRow(new Object[] {
                        tb.getMaTB(),
                        tb.getTenTB(),
                        tb.getMoTaTB(),
                        maTV,
                        HoTen,
                        NgayMuon,
                        NgayTra
                    
            });
                    }
                }
                
            }
            
        }

        if (search.size() == 0) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy kết quả");
            // Refresh table:
            updateThietbiFromList();
        }
    }
}
