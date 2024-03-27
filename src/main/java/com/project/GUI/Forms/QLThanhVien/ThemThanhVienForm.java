package com.project.GUI.Forms.QLThanhVien;

import com.project.GUI.GlobalVariables.Colors;
import com.project.GUI.Components.Buttons.ButtonCancel;
import com.project.GUI.Components.Buttons.ButtonExcel;
import com.project.GUI.Components.Buttons.ButtonRefresh;
import com.project.GUI.Components.Buttons.ButtonSave;
import com.project.GUI.Components.FormLabel;
import com.project.GUI.Components.FormPanel;
import com.project.GUI.Components.TextFields.InputField;
import com.project.GUI.GlobalVariables.Fonts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ThemThanhVienForm extends JFrame {
    private Point mouseDownCompCoords;
    public ThemThanhVienForm() {
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
        JPanel root = new FormPanel();
        root.setLayout(new BorderLayout());

        root.setBorder(BorderFactory.createLineBorder(Colors.primaryColor, 5));

//        Create Header
        JPanel pnlHeader = new FormPanel();
        JLabel lbHeader = new FormLabel("Thêm mới thành viên");
        lbHeader.setFont(Fonts.headerFont);
        lbHeader.setForeground(Color.BLACK);
        pnlHeader.add(lbHeader);

//        Create input field
        JLabel lbMaTV = new FormLabel("Mã TV: ");
        JTextField inputMaTV = new InputField(20);
        JLabel lbHoTen = new FormLabel("Họ tên: ");
        JTextField inputHoTen = new InputField(20);
        JLabel lbKhoa = new FormLabel("Khoa: ");
        JTextField inputKhoa = new InputField(20);
        JLabel lbNganh = new FormLabel("Ngành ");
        JTextField inputNganh = new InputField(20);
        JLabel lbSDT = new FormLabel("SĐT: ");
        JTextField inputSDT = new InputField(20);

//        Create panel to contain input field
        JPanel pnlInput = new FormPanel();
        pnlInput.setLayout(new GridBagLayout());
//        Add constraints
        GridBagConstraints constraints = new GridBagConstraints();
//          Add padding bottom 10px
        constraints.insets = new Insets(0, 0, 10, 0);

        int y = 0;
//        Row 0
        constraints.gridx = 0;
        constraints.gridy = y++;
        pnlInput.add(lbMaTV, constraints);
        constraints.gridx = 1;
        pnlInput.add(inputMaTV, constraints);

//        Row 1
        constraints.gridx = 0;
        constraints.gridy = y++;
        pnlInput.add(lbHoTen, constraints);
        constraints.gridx = 1;
        pnlInput.add(inputHoTen, constraints);

//        Row 2
        constraints.gridx = 0;
        constraints.gridy = y++;
        pnlInput.add(lbKhoa, constraints);
        constraints.gridx = 1;
        pnlInput.add(inputKhoa, constraints);

//        Row 3
        constraints.gridx = 0;
        constraints.gridy = y++;
        pnlInput.add(lbNganh, constraints);
        constraints.gridx = 1;
        pnlInput.add(inputNganh, constraints);

//        Row 4
        constraints.gridx = 0;
        constraints.gridy = y;
        pnlInput.add(lbSDT, constraints);
        constraints.gridx = 1;
        pnlInput.add(inputSDT, constraints);

//        Create button
        JButton btnSave = new ButtonSave();
        JButton btnCancel = new ButtonCancel();
        JButton btnRefresh = new ButtonRefresh();
        JButton btnExcel = new ButtonExcel();
//        Create panel to contain button
        JPanel pnlBtn = new FormPanel();
        pnlBtn.add(btnSave);
        pnlBtn.add(btnCancel);

//        Create panel to contain refresh, excel button
        JPanel pnlMore = new FormPanel();
        pnlMore.setLayout(new GridBagLayout());
        constraints.gridx = 0;
        constraints.gridy = 0;
        pnlMore.add(btnRefresh, constraints);
        constraints.gridy = 1;
        pnlMore.add(btnExcel, constraints);

//        Add all panel to this panel
        root.add(pnlHeader, BorderLayout.PAGE_START);
        root.add(pnlInput, BorderLayout.CENTER);
        root.add(pnlBtn, BorderLayout.SOUTH);
        root.add(pnlMore, BorderLayout.EAST);

//        Add event listener for cancel button
        btnCancel.addActionListener(e -> {
            dispose();
        });
        return root;
    }

    public static void main(String[] args) {
        new ThemThanhVienForm();
    }
}
