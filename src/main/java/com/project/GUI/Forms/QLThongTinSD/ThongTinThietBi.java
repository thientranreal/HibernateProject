package com.project.GUI.Forms.QLThongTinSD;

import com.project.GUI.Components.Buttons.ButtonSave;
import com.project.BLL.thietbiBLL;
import com.project.GUI.Components.FormLabel;
import com.project.GUI.Components.FormPanel;
import com.project.GUI.Components.TextFields.AreaField;
import com.project.GUI.Components.TextFields.InputField;
import com.project.GUI.GlobalVariables.Fonts;
import com.project.models.thietbi;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ThongTinThietBi extends JFrame {
    public static int currentTB;
    private Point mouseDownCompCoords;
    private InputField inputMaTB;
    private InputField inputTenTB;
    private AreaField inputMoTa;

    public ThongTinThietBi(int maTB) {
        currentTB = maTB;
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
        JLabel lbHeader = new FormLabel("Thông tin thiết bị");
        lbHeader.setFont(Fonts.headerFont);
        lbHeader.setForeground(Color.BLACK);
        pnlHeader.add(lbHeader);
        thietbi device = thietbiBLL.getInstance().getModelById(currentTB);

        JLabel lbMaTB = new FormLabel("Mã thiết bị: ");
        inputMaTB = new InputField(20);
        inputMaTB.setText(device.getMaTB() + "");
        inputMaTB.setEditable(false);

        JLabel lbTenTB = new FormLabel("Tên thiết bị: ");
        inputTenTB = new InputField(20);
        inputTenTB.setText(device.getTenTB());
        inputTenTB.setEditable(false);

        JLabel lbMoTa = new FormLabel("Mô tả: ");
        inputMoTa = new AreaField(5, 20);
        JScrollPane scrollMota = new JScrollPane(inputMoTa);
        inputMoTa.setText(device.getMoTaTB());
        inputMoTa.setEditable(false);

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
        pnlInput.add(lbMaTB, constraints);
        constraints.gridx = 1;
        pnlInput.add(inputMaTB, constraints);

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
        JButton btnConfirm = new ButtonSave();
        btnConfirm.setText("Đóng");
        // Create panel to contain button
        JPanel pnlBtn = new FormPanel();
        pnlBtn.add(btnConfirm);

        // Create panel to contain refresh, excel button
        JPanel pnlMore = new FormPanel();
        pnlMore.setLayout(new GridBagLayout());
        constraints.gridx = 0;
        constraints.gridy = 0;
        pnlMore.add(btnConfirm, constraints);

        // Add all panel to this panel
        root.add(pnlHeader, BorderLayout.PAGE_START);
        root.add(pnlInput, BorderLayout.CENTER);
        root.add(pnlBtn, BorderLayout.SOUTH);
        root.add(pnlMore, BorderLayout.EAST);

        // Add event listener for cancel button
        btnConfirm.addActionListener(e -> {
            dispose();
        });

        return root;
    }
}
