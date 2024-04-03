package com.project.GUI.Forms.QLViPham;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

import com.project.GUI.Components.Buttons.ButtonCancel;
import com.project.GUI.Components.FormLabel;
import com.project.GUI.Components.FormPanel;
import com.project.GUI.Components.Buttons.ButtonSave;
import com.project.GUI.Components.TextFields.InputField;
import com.project.GUI.GlobalVariables.Colors;

public class ThaoTac extends JFrame {
    public ThaoTac(){
        initCompontent();

        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public void initCompontent(){
        root = new FormPanel();
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
        btnCancel = new ButtonCancel();
        btnSave = new ButtonSave();

        GridBagConstraints gbc;

        root.setLayout(new BorderLayout());
        root.setBorder(BorderFactory.createLineBorder(Colors.primaryColor, 5));


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

        root.add(pnlInfor,BorderLayout.CENTER);

        pnlButtons.add(btnSave);
        pnlButtons.add(btnCancel);
        root.add(pnlButtons, BorderLayout.SOUTH);

        // Add panel root to JFrame
        add(root);

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

//        Add Btn Cancel event handler
        btnCancel.addActionListener(e -> {
            this.dispose();
        });
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
    private JButton btnSave;
    private JButton btnCancel;
    private Point mouseDownCompCoords;
    private JPanel root;
}
