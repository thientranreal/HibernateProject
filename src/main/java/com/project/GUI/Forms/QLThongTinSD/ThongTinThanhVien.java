package com.project.GUI.Forms.QLThongTinSD;

import com.project.GUI.GlobalVariables.Colors;
import com.project.BLL.thanhvienBLL;
import com.project.GUI.Components.FormLabel;
import com.project.GUI.Components.FormPanel;
import com.project.GUI.Components.Buttons.ButtonSave;
import com.project.GUI.Components.TextFields.InputField;
import com.project.GUI.GlobalVariables.Fonts;
import com.project.models.thanhvien;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.math.BigInteger;

public class ThongTinThanhVien extends JFrame {
    private Point mouseDownCompCoords;
    public static BigInteger currentSV;

    private InputField inputMaTV;
    private InputField inputHoTen;
    private InputField inputKhoa;
    private InputField inputNganh;
    private InputField inputSDT;
    private ButtonSave btnOk;

    public ThongTinThanhVien(BigInteger maSV) {
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
        JLabel lbHeader = new FormLabel("Thông tin thành viên");
        lbHeader.setFont(Fonts.headerFont);
        lbHeader.setForeground(Color.BLACK);
        pnlHeader.add(lbHeader);

        // Create input field
        thanhvien tv = thanhvienBLL.getInstance().getModelById(currentSV);
        JLabel lbMaTV = new FormLabel("Mã TV: ");
        inputMaTV = new InputField(20);
        inputMaTV.setText(tv.getMaTV().toString());
        inputMaTV.setEditable(false);
        JLabel lbHoTen = new FormLabel("Họ tên: ");
        inputHoTen = new InputField(20);
        inputHoTen.setText(tv.getHoTen());
        inputHoTen.setEditable(false);
        JLabel lbKhoa = new FormLabel("Khoa: ");
        inputKhoa = new InputField(20);
        inputKhoa.setText(tv.getKhoa());
        inputKhoa.setEditable(false);
        JLabel lbNganh = new FormLabel("Ngành ");
        inputNganh = new InputField(20);
        inputNganh.setText(tv.getNganh());
        inputNganh.setEditable(false);
        JLabel lbSDT = new FormLabel("SĐT: ");
        inputSDT = new InputField(20);
        inputSDT.setText(tv.getSdt());
        inputSDT.setEditable(false);

        // Create panel to contain input field
        JPanel pnlInput = new FormPanel();
        pnlInput.setLayout(new GridBagLayout());
        // Add constraints
        GridBagConstraints constraints = new GridBagConstraints();
        // Add padding bottom 10px
        constraints.insets = new Insets(0, 0, 10, 0);

        int y = 0;
        // Row 0
        constraints.gridx = 0;
        constraints.gridy = y++;
        pnlInput.add(lbMaTV, constraints);
        constraints.gridx = 1;
        pnlInput.add(inputMaTV, constraints);

        // Row 1
        constraints.gridx = 0;
        constraints.gridy = y++;
        pnlInput.add(lbHoTen, constraints);
        constraints.gridx = 1;
        pnlInput.add(inputHoTen, constraints);

        // Row 2
        constraints.gridx = 0;
        constraints.gridy = y++;
        pnlInput.add(lbKhoa, constraints);
        constraints.gridx = 1;
        pnlInput.add(inputKhoa, constraints);

        // Row 3
        constraints.gridx = 0;
        constraints.gridy = y++;
        pnlInput.add(lbNganh, constraints);
        constraints.gridx = 1;
        pnlInput.add(inputNganh, constraints);

        // Row 4
        constraints.gridx = 0;
        constraints.gridy = y;
        pnlInput.add(lbSDT, constraints);
        constraints.gridx = 1;
        pnlInput.add(inputSDT, constraints);

        // Create button
        btnOk = new ButtonSave();
        btnOk.setText("Đóng");

        // Create panel to contain button
        JPanel pnlBtn = new FormPanel();
        pnlBtn.add(btnOk);

        // Add all panel to this panel
        root.add(pnlHeader, BorderLayout.NORTH);
        root.add(pnlInput, BorderLayout.CENTER);
        root.add(pnlBtn, BorderLayout.SOUTH);

        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        return root;
    }

}
