package com.project.GUI.Forms.QLViPham;

import java.awt.*;
import javax.swing.*;

import com.project.GUI.Components.FormLabel;
import com.project.GUI.Components.FormPanel;
import com.project.GUI.Components.Buttons.ButtonDel;
import com.project.GUI.Components.Buttons.ButtonSave;
import com.project.GUI.Components.TextFields.InputField;

public class ThaoTac extends JFrame{

    public ThaoTac(){
        initCompontent();
    }
    public void initCompontent(){
        pnlInfor = new FormPanel();
        pnlButtons = new FormPanel();
        lbMaVP = new FormLabel("Mã VP");
        lbMaTV = new FormLabel("Mã Thành viên");
        lbTenTV = new FormLabel("Tên thành viên");
        lbHinhThuc = new FormLabel("Hình thức");
        lbSoTien = new FormLabel("Số tiền");
        lbNgayXL = new FormLabel("Ngày xử lý");
        lbTrangThai = new FormLabel("Trạng thái");
        inputMaVP = new InputField(7);
        inputMaTV = new InputField(7);
        inputTenTV = new InputField(7);
        inputHinhThuc = new InputField(7);
        inputSoTien = new InputField(7);
        inputNgayXL = new InputField(7);
        inputTrangThai = new InputField(7);
        btnDel = new ButtonDel();
        btnSave = new ButtonSave();

        GridBagConstraints gbc;


        setSize(new Dimension(500,300));
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        pnlInfor.setPreferredSize(new Dimension(400, 200));
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[] { 10, 10, 10, 10, 10, 10 };
        gridBagLayout.rowHeights = new int[] { 10, 10, 10, 10, 10, 10 };
        pnlInfor.setLayout(gridBagLayout);

         //add input + txt to pnlInfor

         gbc = new GridBagConstraints();
         gbc.gridx = 0;
         gbc.gridy = 0;
         pnlInfor.add(lbMaVP, gbc);
 
         gbc = new GridBagConstraints();
         gbc.gridx = 2;
         gbc.gridy = 0;
         pnlInfor.add(inputMaVP, gbc);
 
         gbc = new GridBagConstraints();
         gbc.gridx = 0;
         gbc.gridy = 2;
         pnlInfor.add(lbMaTV, gbc);
 
         gbc = new GridBagConstraints();
         gbc.gridx = 2;
         gbc.gridy = 2;
         pnlInfor.add(inputMaTV, gbc);
 
         gbc = new GridBagConstraints();
         gbc.gridx = 0;
         gbc.gridy = 4;
         pnlInfor.add(lbHinhThuc, gbc);
 
         gbc = new GridBagConstraints();
         gbc.gridx = 2;
         gbc.gridy = 4;
         pnlInfor.add(inputHinhThuc, gbc);
 
         gbc = new GridBagConstraints();
         gbc.gridx = 4;
         gbc.gridy = 2;
         pnlInfor.add(lbTenTV, gbc);
 
         gbc = new GridBagConstraints();
         gbc.gridx = 6;
         gbc.gridy = 2;
         pnlInfor.add(inputTenTV, gbc);
 
         gbc = new GridBagConstraints();
         gbc.gridx = 4;
         gbc.gridy = 4;
         pnlInfor.add(lbSoTien, gbc);
 
         gbc = new GridBagConstraints();
         gbc.gridx = 6;
         gbc.gridy = 4;
         pnlInfor.add(inputSoTien, gbc);
 
         gbc = new GridBagConstraints();
         gbc.gridx = 0;
         gbc.gridy = 6;
         pnlInfor.add(lbNgayXL, gbc);
 
         gbc = new GridBagConstraints();
         gbc.gridx = 2;
         gbc.gridy = 6;
         pnlInfor.add(inputNgayXL, gbc);
 
         gbc = new GridBagConstraints();
         gbc.gridx = 4;
         gbc.gridy = 6;
         pnlInfor.add(lbTrangThai, gbc);
 
         gbc = new GridBagConstraints();
         gbc.gridx = 6;
         gbc.gridy = 6;
         pnlInfor.add(inputTrangThai, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;

        add(pnlInfor,BorderLayout.CENTER);

        pnlButtons.add(btnSave);
        pnlButtons.add(btnDel);
        add(pnlButtons, BorderLayout.SOUTH);
    }
    private JPanel pnlInfor;
    private JPanel pnlButtons;
    private FormLabel lbMaVP;
    private FormLabel lbMaTV;
    private FormLabel lbTenTV;
    private FormLabel lbHinhThuc;
    private FormLabel lbSoTien;
    private FormLabel lbNgayXL;
    private FormLabel lbTrangThai;
    private InputField inputMaVP;
    private InputField inputMaTV;
    private InputField inputTenTV;
    private InputField inputHinhThuc;
    private InputField inputSoTien;
    private InputField inputNgayXL;
    private InputField inputTrangThai;
    private FormLabel lbSearch;
    private InputField inputSearch;
    private JButton btnSave;
    private JButton btnDel;
}
