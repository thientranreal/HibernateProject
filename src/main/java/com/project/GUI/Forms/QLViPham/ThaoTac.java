package com.project.GUI.Forms.QLViPham;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigInteger;
import javax.swing.*;

import com.project.BLL.thanhvienBLL;
import com.project.GUI.Components.Buttons.ButtonCancel;
import com.project.GUI.Components.FormLabel;
import com.project.GUI.Components.FormPanel;
import com.project.GUI.Components.Buttons.ButtonSave;
import com.project.GUI.Components.TextFields.InputField;
import com.project.GUI.GlobalVariables.Colors;
import com.project.models.thanhvien;

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
        lbTV = new FormLabel("Thành viên");
        lbHinhThuc = new FormLabel("Hình thức");
        lbSoTien = new FormLabel("Số tiền");
        inputHinhThuc = new InputField(7);
        inputSoTien = new InputField(7);
        btnCancel = new ButtonCancel();
        btnSave = new ButtonSave();
        cbThanhVien = new JComboBox<>();

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
        gbc.insets = new Insets(0, 0, 20, 0);
        gbc.gridx = 0;
        gbc.gridy = 0;
        pnlInfor.add(lbTV, gbc);

        gbc.gridx = 2;
        pnlInfor.add(cbThanhVien, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        pnlInfor.add(lbHinhThuc, gbc);

        gbc.gridx = 2;
        pnlInfor.add(inputHinhThuc, gbc);

        gbc.gridx = 4;
        pnlInfor.add(lbSoTien, gbc);

        gbc.gridx = 6;
        pnlInfor.add(inputSoTien, gbc);


        root.add(pnlInfor,BorderLayout.CENTER);

        pnlButtons.add(btnSave);
        pnlButtons.add(btnCancel);
        root.add(pnlButtons, BorderLayout.SOUTH);

        // Add panel root to JFrame
        add(root);

//        Load thanh vien into JCombo Box
        for (thanhvien tv : thanhvienBLL.getInstance().getAllModels()) {
            cbThanhVien.addItem(tv);
        }

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

//        Add Btn Save event handler
        btnSave.addActionListener(e -> {
            if (cbThanhVien.getSelectedIndex() >= 0) {
 //          Lấy ID thành viên từ combo box
                thanhvien tv = (thanhvien) cbThanhVien.getSelectedItem();
                BigInteger maTV = tv.getMaTV();

//                Xử lý tiếp theo, ngày xử lý lấy ngày giờ hiện tại, trạng thái là trạng thái xử lý
            }
        });
    }
    private JPanel pnlInfor;
    private JPanel pnlButtons;
    private FormLabel lbTV;
    private FormLabel lbHinhThuc;
    private FormLabel lbSoTien;
    private InputField inputHinhThuc;
    private InputField inputSoTien;
    private JButton btnSave;
    private JButton btnCancel;
    private Point mouseDownCompCoords;
    private JPanel root;
    private JComboBox<thanhvien> cbThanhVien;
}
