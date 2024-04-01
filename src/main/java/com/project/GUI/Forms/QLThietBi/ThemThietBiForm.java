package com.project.GUI.Forms.QLThietBi;

import com.project.GUI.Components.Buttons.ButtonCancel;
import com.project.GUI.Components.Buttons.ButtonExcel;
import com.project.GUI.Components.Buttons.ButtonRefresh;
import com.project.GUI.Components.Buttons.ButtonSave;
import com.project.BLL.thietbiBLL;
import com.project.GUI.Components.FormLabel;
import com.project.GUI.Components.FormPanel;
import com.project.GUI.Components.TextFields.AreaField;
import com.project.GUI.Components.TextFields.InputField;
import com.project.GUI.GlobalVariables.Fonts;
import com.project.models.thietbi;
import com.project.utilities.excel.thietbiExcelUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Objects;

public class ThemThietBiForm extends JFrame {
    private Point mouseDownCompCoords;
    private InputField inputTenTB;
    private AreaField inputMoTa;
    //private InputField inputMaTB;

    public ThemThietBiForm() {
        // Add Content into JFrame
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

        // Create Header
        JPanel pnlHeader = new FormPanel();
        JLabel lbHeader = new FormLabel("Thêm mới thiết bị");
        lbHeader.setFont(Fonts.headerFont);
        lbHeader.setForeground(Color.BLACK);
        pnlHeader.add(lbHeader);

        // Create input field
        //JLabel lbMaTB = new FormLabel("Mã thiết bị: ");
        //inputMaTB = new InputField(20);
        JLabel lbTenTB = new FormLabel("Tên thiết bị: ");
        inputTenTB = new InputField(20);
        JLabel lbMoTa = new FormLabel("Mô tả: ");
        inputMoTa = new AreaField(5, 20);
        JScrollPane scrollMota = new JScrollPane(inputMoTa);

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
        //pnlInput.add(lbMaTB, constraints);
        constraints.gridx = 1;
        //pnlInput.add(inputMaTB, constraints);

        constraints.gridx = 0;
        constraints.gridy = y++;
        pnlInput.add(lbTenTB, constraints);
        constraints.gridx = 1;
        pnlInput.add(inputTenTB, constraints);

        // Row 1
        constraints.gridx = 0;
        constraints.gridy = y;
        pnlInput.add(lbMoTa, constraints);
        constraints.gridx = 1;
        pnlInput.add(scrollMota, constraints);

        // Create button
        JButton btnSave = new ButtonSave();
        JButton btnCancel = new ButtonCancel();
        JButton btnRefresh = new ButtonRefresh();
        JButton btnExcel = new ButtonExcel();
        btnExcel.setText("Nhập từ Excel");

        // Create panel to contain button
        JPanel pnlBtn = new FormPanel();
        pnlBtn.add(btnSave);
        pnlBtn.add(btnCancel);

        // Create panel to contain refresh, excel button
        JPanel pnlMore = new FormPanel();
        pnlMore.setLayout(new GridBagLayout());
        constraints.gridx = 0;
        constraints.gridy = 0;
        pnlMore.add(btnRefresh, constraints);
        constraints.gridy = 1;
        pnlMore.add(btnExcel, constraints);

        // Add all panel to this panel
        root.add(pnlHeader, BorderLayout.PAGE_START);
        root.add(pnlInput, BorderLayout.CENTER);
        root.add(pnlBtn, BorderLayout.SOUTH);
        root.add(pnlMore, BorderLayout.EAST);

        // Add event listener for cancel button
        btnCancel.addActionListener(e -> {
            dispose();
        });

        btnSave.addActionListener(e -> {
            // Get data from input field
            String tenTB = inputTenTB.getText();
            String moTa = inputMoTa.getText();
            thietbi device = new thietbi(tenTB, moTa);
            // Validate data
            // if (thietbiBLL.getInstance().getModelById(maTB) != null) {
            //     JOptionPane.showMessageDialog(null, "Mã thiết bị đã tồn tại", "Lỗi", JOptionPane.ERROR_MESSAGE);
            //     return;
            // }

            if (tenTB.isEmpty() || moTa.isEmpty() || tenTB.isBlank() || moTa.isBlank()) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Save data to database
            int result = thietbiBLL.getInstance().addModel(device);
            if (result > 0) {
                // If success
                JOptionPane.showMessageDialog(null, "Thêm mới thiết bị thành công", "Thành công",
                        JOptionPane.INFORMATION_MESSAGE);
                QLThietBiPanel panel = new QLThietBiPanel();
                panel.updateThietBiFromList();
                clearForm();
            } else {
                JOptionPane.showMessageDialog(null, "Thêm mới thiết bị thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);

            }
        });

        btnExcel.addActionListener(e -> {
            try {
                if (!Objects.requireNonNull(thietbiExcelUtil.readThietbisFromExcel()).isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Thêm thành công");

                } else {
                    JOptionPane.showMessageDialog(null, "Thêm thất bại");
                }

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        btnRefresh.addActionListener(e -> {
            clearForm();
        });

        return root;
    }

    public void clearForm() {
        inputTenTB.setText("");
        inputMoTa.setText("");
    }

    public static void main(String[] args) {
        new ThemThietBiForm();
    }
}
