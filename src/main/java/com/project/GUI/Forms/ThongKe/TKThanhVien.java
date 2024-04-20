package com.project.GUI.Forms.ThongKe;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.util.List;

import com.project.models.thanhvien;

import com.github.lgooddatepicker.components.DateTimePicker;
import com.project.BLL.thanhvienBLL;
import com.project.BLL.thongtinsdBLL;
import com.project.GUI.Components.FormLabel;
import com.project.GUI.Components.FormPanel;
import com.project.GUI.Components.Buttons.ButtonSearch;
import com.project.GUI.Components.Table.TableCustom;
import com.project.GUI.Components.TextFields.InputField;
import com.project.GUI.GlobalVariables.Colors;
import com.project.models.thongtinsd;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Objects;
import javax.swing.table.DefaultTableCellRenderer;

public class TKThanhVien extends JPanel {
    private static JTable table;
    public TKThanhVien(){
        initCompontent();
    }
    private void initCompontent(){
        pnlHeader = new FormPanel();
        lbThoiGian = new FormLabel("Thời gian");
        lbKhoa = new FormLabel("Khoa");
        lbNganh = new FormLabel("Ngành");
        inputKhoa = new InputField(7);
        inputNganh = new InputField(7);
        btnSearch = new ButtonSearch();
        pnlMain = new JPanel();
        soLieu = new FormLabel("");
        dpkThoiGianTu = new DateTimePicker();
        dpkThoiGianDen = new DateTimePicker();
        

        setLayout(new BorderLayout());

        // add content to pnlHeader
        pnlHeader.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));
        pnlHeader.add(lbThoiGian);
        pnlHeader.add(dpkThoiGianTu);
        pnlHeader.add(dpkThoiGianDen);
        pnlHeader.add(lbKhoa);
        pnlHeader.add(inputKhoa);
        pnlHeader.add(lbNganh);
        pnlHeader.add(inputNganh);
        pnlHeader.add(btnSearch);

        //add pnlHeader to main
        add(pnlHeader, BorderLayout.NORTH);

        //add content to pnlMain
        pnlMain.setLayout(new BorderLayout());

        soLieu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        //add userQuantity to pnlMain
        pnlMain.add(soLieu, BorderLayout.NORTH);

        table = new JTable();
        // Create header for table
        table.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] { "Mã TV",
                        "Họ tên",
                        "Khoa",
                        "Ngành",
                        "Số điện thoại",
                        "Thời gian vào"
                }){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }}
        );
        updateMemberFromList();
        // Add data for table
       
        // Create panel to contain table
        
        JScrollPane pnlTable = new JScrollPane();
        pnlTable.setBorder(new EmptyBorder(10, 10, 10, 10));
        pnlTable.setViewportView(table);
        pnlTable.setBackground(Colors.bgColor);
        pnlMain.add(pnlTable, BorderLayout.CENTER);
        TableCustom.apply(pnlTable, TableCustom.TableType.MULTI_LINE);
        //add pnlmain to pnl main
        add(pnlMain, BorderLayout.CENTER);
        btnSearch.addActionListener(e -> {
            LocalDate date = dpkThoiGianTu.getDatePicker().getDate();
            LocalTime time = dpkThoiGianTu.getTimePicker().getTime();
            if (time == null) {
                time = LocalTime.MIDNIGHT;
            }
            LocalDateTime localDateTime, localDateTime2;
            Timestamp enterTimestamp, enterTimestamp2;
            try {
                localDateTime = LocalDateTime.of(date, time.atDate(LocalDate.now(ZoneId.of("Asia/Ho_Chi_Minh"))).toLocalTime());
                enterTimestamp = Timestamp.valueOf(localDateTime); // lay ra ngay gio/ thoi gian sinh vien vao
            } catch (NullPointerException ex) {
                ex.printStackTrace();
                enterTimestamp = null;
            }
            
            LocalDate dateden = dpkThoiGianDen.getDatePicker().getDate();
            LocalTime timeden = dpkThoiGianDen.getTimePicker().getTime();
            if (timeden == null) {
                timeden = LocalTime.MIDNIGHT;
            }
            try {
                localDateTime2 = LocalDateTime.of(dateden, timeden.atDate(LocalDate.now(ZoneId.of("Asia/Ho_Chi_Minh"))).toLocalTime());
                enterTimestamp2 = Timestamp.valueOf(localDateTime2);
            } catch (NullPointerException ex) {
                ex.printStackTrace();
                enterTimestamp2 = null;
            }
            
            Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
            String searchValueKhoa = inputKhoa.getText().trim();
            String searchValueNganh = inputNganh.getText().trim();

            if (enterTimestamp2 == null && enterTimestamp == null) {
                if (searchValueKhoa.isEmpty() && searchValueNganh.isEmpty()) {
                    updateMemberFromList();
                    return;
                }
                if (searchValueKhoa.isEmpty()) {
                    List<thongtinsd> infoList = new ArrayList<>();
                    for(thanhvien member : thanhvienBLL.getInstance().getAllModels()) {
                        for(thongtinsd info : thongtinsdBLL.getInstance().getAllModels()) {
                            if(Objects.equals(member.getMaTV(), info.getMember().getMaTV()) && member.getNganh().toLowerCase().contains(searchValueNganh.toLowerCase())) {
                                infoList.add(info);
                            }
                        }
                    }
                    showSearchResult(infoList);
                    return;
                }
                if (searchValueNganh.isEmpty()) {
                    List<thongtinsd> infoList = new ArrayList<>();
                    for(thanhvien member : thanhvienBLL.getInstance().getAllModels()) {
                        for(thongtinsd info : thongtinsdBLL.getInstance().getAllModels()) {
                            if(Objects.equals(member.getMaTV(), info.getMember().getMaTV()) && member.getKhoa().toLowerCase().contains(searchValueKhoa.toLowerCase())) {
                                infoList.add(info);
                            }
                        }
                    }
                    showSearchResult(infoList);
                    return;
                }
            }

            if (enterTimestamp2 == null && enterTimestamp != null) {
                enterTimestamp2 = currentTimestamp;
            } else if(enterTimestamp2 != null && enterTimestamp == null){
                enterTimestamp = currentTimestamp;
                if(enterTimestamp.compareTo(enterTimestamp2) > 0){
                    JOptionPane.showMessageDialog(null, "Thoi gian hien tai khong the lon hon thoi gian den, vui long chon thoi gian tu");
                    return;
                } 
            }

            if (searchValueKhoa.isEmpty() && searchValueNganh.isEmpty()) {
                List<thongtinsd> memberByTimestamp = checkValidTimestamp(enterTimestamp, enterTimestamp2);
                showSearchResult(memberByTimestamp);
                return;
            }
            if (searchValueKhoa.isEmpty()) {
                List<thanhvien> memberByNganh = thanhvienBLL.getInstance().searchListThanhVienByNganh(searchValueNganh);
                showSearchResult(checkValidTimestampWithList(memberByNganh, enterTimestamp, enterTimestamp2));
                return;
            }
            if (searchValueNganh.isEmpty()) {
                List<thanhvien> memberByKhoa = thanhvienBLL.getInstance().searchListThanhVienByKhoa(searchValueKhoa);
                showSearchResult(checkValidTimestampWithList(memberByKhoa, enterTimestamp, enterTimestamp2));
                return;
            }
        });
    }  

    private JPanel pnlHeader;
    private JLabel lbThoiGian;
    private JLabel lbKhoa;
    private JLabel lbNganh;
    private JTextField inputKhoa;
    private JTextField inputNganh;
    private JButton btnSearch;
    private JPanel pnlMain;
    private JLabel soLieu;
    private DateTimePicker dpkThoiGianTu;
    private DateTimePicker dpkThoiGianDen;
    
    public void updateMemberFromList() {
        int userQuantity = 0;
        thanhvienBLL.getInstance().refresh();
        DefaultTableModel model_table = (DefaultTableModel) table.getModel();
        model_table.setRowCount(0);

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);

        for (thanhvien member : thanhvienBLL.getInstance().getAllModels()) {
            for(thongtinsd info : thongtinsdBLL.getInstance().getAllModels()) {
                if(member.getMaTV().equals(info.getThanhvien()) && info.getTGVao() != null) {
                    userQuantity++;
                    model_table.addRow(new Object[] {
                    member.getMaTV(),
                    member.getHoTen(),
                    member.getKhoa(),
                    member.getNganh(),
                    member.getSdt(),
                    info.getTGVao(),
            });
                }
            }
        }
       soLieu.setText("Có " + userQuantity +" thành viên");
    }
    
    
    public void showSearchResult(List<thongtinsd> search) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        int userQuantity = 0;
        System.out.println(search);
        for (thongtinsd info : search) {
            for(thanhvien member : thanhvienBLL.getInstance().getAllModels()) {
                if(member.getMaTV().equals(info.getThanhvien()) && info.getTGVao() != null) {
                                userQuantity++;
                                model.addRow(new Object[] {
                                member.getMaTV(),
                                member.getHoTen(),
                                member.getKhoa(),
                                member.getNganh(),
                                member.getSdt(),
                                info.getTGVao(),
                        });
                }
            }
        }
        soLieu.setText("Có " + userQuantity +" thành viên");
    }
    
    public List<thongtinsd> checkValidTimestamp(Timestamp from,Timestamp to) {
        List<thongtinsd> infoList = new ArrayList<>();
        for(thanhvien member : thanhvienBLL.getInstance().getAllModels()) {
            for(thongtinsd info : thongtinsdBLL.getInstance().getAllModels()) {
                try {
                    if(Objects.equals(member.getMaTV(), info.getMember().getMaTV()) && info.getTGVao().after(from) && info.getTGVao().before(to)) {
                        infoList.add(info);
                    }
                } catch (NullPointerException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return infoList;
    }

    public List<thongtinsd> checkValidTimestampWithList(List<thanhvien> list, Timestamp from,Timestamp to) {
        List<thongtinsd> infoList = new ArrayList<>();
        for(thanhvien member : list) {
            for(thongtinsd info : thongtinsdBLL.getInstance().getAllModels()) {
                try {
                    if(Objects.equals(member.getMaTV(), info.getMember().getMaTV()) && info.getTGVao().after(from) && info.getTGVao().before(to)) {
                        infoList.add(info);
                    }
                } catch (NullPointerException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return infoList;
    }
}
