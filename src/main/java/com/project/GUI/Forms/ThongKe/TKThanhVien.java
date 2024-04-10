package com.project.GUI.Forms.ThongKe;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.util.List;

import com.project.models.thietbi;
import com.project.models.thanhvien;

import com.github.lgooddatepicker.components.DateTimePicker;
import com.project.BLL.thanhvienBLL;
import com.project.BLL.thietbiBLL;
import com.project.BLL.thongtinsdBLL;
import com.project.BLL.xulyBLL;
import com.project.GUI.Components.FormLabel;
import com.project.GUI.Components.FormPanel;
import com.project.GUI.Components.Buttons.ButtonSearch;
import com.project.GUI.Components.Table.TableCustom;
import com.project.GUI.Components.TextFields.InputField;
import com.project.GUI.GlobalVariables.Colors;
import com.project.models.thongtinsd;
import com.project.models.xuly;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
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
                }){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }}
        );updateMemberFromList();
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
            LocalDateTime localDateTime = LocalDateTime.of(date, time.atDate(LocalDate.now(ZoneId.of("Asia/Ho_Chi_Minh"))).toLocalTime());
            Timestamp enterTimestamp = Timestamp.valueOf(localDateTime); // lay ra ngay gio/ thoi gian sinh vien vao
            
            LocalDate dateden = dpkThoiGianDen.getDatePicker().getDate();
            LocalTime timeden = dpkThoiGianDen.getTimePicker().getTime();
            LocalDateTime localDateTime2 = LocalDateTime.of(dateden, timeden.atDate(LocalDate.now(ZoneId.of("Asia/Ho_Chi_Minh"))).toLocalTime());
            Timestamp enterTimestamp2 = Timestamp.valueOf(localDateTime2);
            
            Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
            
            if(enterTimestamp2 == null && enterTimestamp != null) {
                enterTimestamp2 = currentTimestamp;
            }else if(enterTimestamp2 != null && enterTimestamp == null){
                enterTimestamp = currentTimestamp;
                if(enterTimestamp.compareTo(enterTimestamp2) > 0){
                    JOptionPane.showMessageDialog(null, "Thoi gian hien tai khong the lon hon thoi gian den, vui long chon thoi gian tu");
                    return;
                } 
            }
            
        
            
            
            
            String searchValue = inputKhoa.getText().trim();
            String searchValue2 = inputNganh.getText().trim();
            

            List<thanhvien> memberList = checkValidTimestamp(enterTimestamp, enterTimestamp2);
            if((searchValue2 == null || searchValue2.trim().isEmpty()) && searchValue != null) {
                List<thanhvien> memberByKhoa = thanhvienBLL.getInstance().searchListThanhVienByKhoa(searchValue);
                showSearchResult(memberByKhoa);
            } else if(searchValue == null || searchValue.trim().isEmpty() && searchValue2 != null) {
                List<thanhvien> memberByNganh = thanhvienBLL.getInstance().searchListThanhVienByNganh(searchValue);
                showSearchResult(memberByNganh);
            } else if(!searchValue.isEmpty() && !searchValue2.isEmpty()) {
                List<thanhvien> memberByKhoaAndNganh = thanhvienBLL.getInstance().searchListThanhVienByKhoaAndNganh(searchValue,searchValue2);
                showSearchResult(memberByKhoaAndNganh);
            }else if((searchValue == null || searchValue.trim().isEmpty()) && (searchValue2 == null || searchValue2.trim().isEmpty())){
                List<thanhvien> memberByTimestamp = checkValidTimestamp(enterTimestamp, enterTimestamp2);
                showSearchResult(memberByTimestamp);
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
                    member.getSdt()
            });
                }
            }
        }
       soLieu.setText("Có " + userQuantity +" thành viên");
    }
    
    
    public void showSearchResult(List<thanhvien> search) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        int userQuantity = 0;
        System.out.println(search);
        for (thanhvien member : search) {
            for(thongtinsd info : thongtinsdBLL.getInstance().getAllModels()) {
                if(member.getMaTV().equals(info.getThanhvien()) && info.getTGVao() != null) {
                    userQuantity++;
                    model.addRow(new Object[] {
                    member.getMaTV(),
                    member.getHoTen(),
                    member.getKhoa(),
                    member.getNganh(),
                    member.getSdt()
            });
                }
        }
        soLieu.setText("Có " + userQuantity +" thành viên");
        if (search.size() == 0) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy kết quả");
            // Refresh table:
            updateMemberFromList();
        }
    }
    }
    
    public List<thanhvien> checkValidTimestamp(Timestamp from,Timestamp to) {
        List<thanhvien> memberList = new ArrayList<>();
        for(thanhvien member : thanhvienBLL.getInstance().getAllModels()) {
            for(thongtinsd info : thongtinsdBLL.getInstance().getAllModels()) {
                if(info.getTGVao().after(from) && info.getTGVao().before(to)) {
                    memberList.add(member);
                }
            }
        }
        return memberList;
    }
}
