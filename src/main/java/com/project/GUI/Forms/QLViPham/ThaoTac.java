package com.project.GUI.Forms.QLViPham;
// XIN CHAO
// TAMBIET

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigInteger;
import javax.swing.*;

import com.project.BLL.thanhvienBLL;
import com.project.BLL.xulyBLL;
import com.project.GUI.Components.Buttons.ButtonCancel;
import com.project.GUI.Components.FormLabel;
import com.project.GUI.Components.FormPanel;
import com.project.GUI.Components.Buttons.ButtonSave;
import com.project.GUI.Components.TextFields.InputField;
import com.project.GUI.GlobalVariables.Colors;
import com.project.models.thanhvien;
import com.project.models.xuly;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.Date;
import java.sql.*;
import java.util.function.Consumer;

public class ThaoTac extends JFrame {

    public ThaoTac() {
        this(null);
    }

    public ThaoTac(BigInteger memberID) {
        this.memberID = memberID;
        initCompontent();

        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }


    public void initCompontent() {
        root = new FormPanel();
        pnlInfor = new FormPanel();
        pnlButtons = new FormPanel();
        lbTV = new FormLabel("Thành viên");
        lbHinhThuc = new FormLabel("Hình thức");
        lbSoTien = new FormLabel("Số tiền");
        cbHinhThuc = new JComboBox<>(new String[]{
                "Khóa thẻ 1 tháng",
                "Khóa thẻ 2 tháng",
                "Khóa thẻ vĩnh viễn",
                "Bồi thường",
                "Khóa thẻ 1 tháng và bồi thường",
        });
        inputSoTien = new InputField(7);
        btnCancel = new ButtonCancel();
        btnSave = new ButtonSave();
        cbThanhVien = new JComboBox<>();
        inputSoTien.setText("0");
        inputSoTien.setEditable(false);

        inputSoTien.setEditable(false);

        GridBagConstraints gbc;

        root.setLayout(new BorderLayout());
        root.setBorder(BorderFactory.createLineBorder(Colors.primaryColor, 5));

        pnlInfor.setPreferredSize(new Dimension(400, 200));
        GridBagLayout gridBagLayout = new GridBagLayout();
        pnlInfor.setLayout(gridBagLayout);

        //add input + txt to pnlInfor
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 20, 20);
        gbc.anchor = GridBagConstraints.EAST; // Căn phải cho gridx = 0
        pnlInfor.add(lbTV, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST; // Căn trái cho gridx = 1
        pnlInfor.add(cbThanhVien, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST; // Căn phải cho gridx = 0
        pnlInfor.add(lbHinhThuc, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST; // Căn trái cho gridx = 1
        pnlInfor.add(cbHinhThuc, gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.EAST; // Căn phải cho gridx = 0
        pnlInfor.add(lbSoTien, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST; // Căn trái cho gridx = 1
        pnlInfor.add(inputSoTien, gbc);


        root.add(pnlInfor, BorderLayout.CENTER);

        pnlButtons.add(btnSave);
        pnlButtons.add(btnCancel);
        root.add(pnlButtons, BorderLayout.SOUTH);

        // Add panel root to JFrame
        add(root);

        if (this.memberID == null) {
            //        Load thanh vien into JCombo Box
            for (thanhvien tv : thanhvienBLL.getInstance().getAllModels()) {
                cbThanhVien.addItem(tv);
            }
        }
        else {
            cbThanhVien.addItem(thanhvienBLL.getInstance().getModelById(this.memberID));
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
//        Combo box xu ly change event
        cbHinhThuc.addActionListener(e -> {
            if (cbHinhThuc.getSelectedIndex() == 3 || cbHinhThuc.getSelectedIndex() == 4) {
                inputSoTien.setEditable(true);
                return;
            }
            inputSoTien.setText("0");
            inputSoTien.setEditable(false);
        });

//        Combo box xu ly change event
        cbHinhThuc.addActionListener(e -> {
            if (cbHinhThuc.getSelectedIndex() == 3 || cbHinhThuc.getSelectedIndex() == 4) {
                inputSoTien.setEditable(true);
                return;
            }
            inputSoTien.setText("");
            inputSoTien.setEditable(false);
        });

//        Add Btn Cancel event handler
        btnCancel.addActionListener(e -> {
            this.dispose();
        });

        btnSave.addActionListener(e -> {         
            if (cbThanhVien.getSelectedIndex() >= 0) {
                //          Lấy ID thành viên từ combo box
                thanhvien tv = (thanhvien) cbThanhVien.getSelectedItem();
                BigInteger maTV = tv.getMaTV();
                Date date = new Date();
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                String hinhthuc = cbHinhThuc.getSelectedItem().toString();
                Integer sotien = Integer.parseInt(inputSoTien.getText().trim());
                int trangthai = 0;
                xuly newXuly = new xuly(maTV, hinhthuc, sotien, sqlDate, trangthai);

                int newXulyResult = xulyBLL.getInstance().addModel(newXuly);
                if (newXulyResult >= 0) {
                    JOptionPane.showMessageDialog(null, "Thêm thành công");
                    DanhSachViPham panel = new DanhSachViPham();
                    panel.updateMemberFromList();
                } else {
                    JOptionPane.showMessageDialog(null, "Thêm thất bại");
                }
                clearForm();
                dispose();
            }
        });
//                Xử lý tiếp theo, ngày xử lý lấy ngày giờ hiện tại, trạng thái là trạng thái xử lý
//     Add Btn Save event handler
    }
    private JPanel pnlInfor;
    private JPanel pnlButtons;
    private FormLabel lbTV;
    private FormLabel lbHinhThuc;
    private FormLabel lbSoTien;
    private JComboBox<String> cbHinhThuc;
    private InputField inputSoTien;
    private JButton btnSave;
    private JButton btnCancel;
    private Point mouseDownCompCoords;
    private JPanel root;
    private JComboBox<thanhvien> cbThanhVien;
    private BigInteger memberID;

    public void clearForm() {
        inputSoTien.setText("");
    }

    public void addXuly(DanhSachViPham danhSachViPhamPanel) {
        if (cbThanhVien.getSelectedIndex() >= 0) {
            //          Lấy ID thành viên từ combo box
            thanhvien tv = (thanhvien) cbThanhVien.getSelectedItem();
            BigInteger maTV = tv.getMaTV();
            Date date = new Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            String hinhthuc = cbHinhThuc.getSelectedItem().toString();
            Integer sotien = Integer.parseInt(inputSoTien.getText().trim());
            int trangthai = 0;
            xuly newXuly = new xuly(maTV, hinhthuc, sotien, sqlDate, trangthai);
            int newXulyResult = xulyBLL.getInstance().addModel(newXuly);
            if (newXulyResult >= 0) {
                JOptionPane.showMessageDialog(null, "Thêm thành công");
                
                danhSachViPhamPanel.updateMemberFromList();
            } else {
                JOptionPane.showMessageDialog(null, "Thêm thất bại");
            }
        }

    }
}
