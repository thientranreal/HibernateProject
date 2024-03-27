package com.project.GUI.Forms;

import com.project.GUI.Components.Buttons.ButtonCancel;
import com.project.GUI.Components.Buttons.ButtonExcel;
import com.project.GUI.Components.Buttons.ButtonRefresh;
import com.project.GUI.Components.Buttons.ButtonSave;
import com.project.GUI.Components.FormLabel;
import com.project.GUI.Components.FormPanel;
import com.project.GUI.Components.TextFields.AreaField;
import com.project.GUI.Components.TextFields.InputField;
import com.project.GUI.Fonts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ThemThietBiForm extends JFrame {
    private Point mouseDownCompCoords;
    public ThemThietBiForm() {
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

//        Create Header
        JPanel pnlHeader = new FormPanel();
        JLabel lbHeader = new FormLabel("Thêm mới thiết bị");
        lbHeader.setFont(Fonts.headerFont);
        lbHeader.setForeground(Color.BLACK);
        pnlHeader.add(lbHeader);

//        Create input field
        JLabel lbTenTB = new FormLabel("Tên thiết bị: ");
        JTextField inputTenTB = new InputField(20);
        JLabel lbMoTa = new FormLabel("Mô tả: ");
        JTextArea inputMoTa = new AreaField(5,20);
        JScrollPane scrollMota = new JScrollPane(inputMoTa);

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
        pnlInput.add(lbTenTB, constraints);
        constraints.gridx = 1;
        pnlInput.add(inputTenTB, constraints);

//        Row 1
        constraints.gridx = 0;
        constraints.gridy = y;
        pnlInput.add(lbMoTa, constraints);
        constraints.gridx = 1;
        pnlInput.add(scrollMota, constraints);

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
        new ThemThietBiForm();
    }
}
