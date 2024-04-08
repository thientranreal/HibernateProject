package com.project.GUI.Forms;

import com.project.GUI.Components.Buttons.ButtonMenu;
import com.project.GUI.Components.FormPanel;
import com.project.GUI.Components.MenuPanel;
import com.project.GUI.Forms.QLThanhVien.QLThanhVienPanel;
import com.project.GUI.Forms.QLThietBi.QLThietBiPanel;
import com.project.GUI.Forms.QLThongTinSD.QLThongTinSDPanel;
import com.project.GUI.Forms.QLViPham.DanhSachViPham;
import com.project.GUI.Forms.ThongKe.ThongKe;

import javax.swing.*;
import java.awt.*;

public class MenuForm extends JFrame {
    private ButtonMenu btnQLTV;
    private ButtonMenu btnQLTB;
    private  ButtonMenu btnXLVP;
    private ButtonMenu btnTK;
    private ButtonMenu btnTTSD;
    private JPanel pnlBtn;
    private JPanel pnlForm;

    public MenuForm() {
        add(initComponent());
        setVisible(true);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

//        Add event listener btn Quan ly thanh vien
        btnQLTV.addActionListener(e -> {
            pnlForm.removeAll();
            pnlForm.add(new QLThanhVienPanel());
            pnlForm.revalidate();
            pnlForm.repaint();
        });

//        Add event listener btn Quan ly thiet bi
        btnQLTB.addActionListener(e -> {
            pnlForm.removeAll();
            pnlForm.add(new QLThietBiPanel());
            pnlForm.revalidate();
            pnlForm.repaint();
        });

//          Add event listener btn Thong tin su dung:
        btnTTSD.addActionListener(e -> {
            pnlForm.removeAll();
            pnlForm.add(new QLThongTinSDPanel());
            pnlForm.revalidate();
            pnlForm.repaint();
        });

 //        Add event listener btn Xu ly vi pham
        btnXLVP.addActionListener(e -> {
            pnlForm.removeAll();
            pnlForm.add(new DanhSachViPham());
            pnlForm.revalidate();
            pnlForm.repaint();
        });

//        Add event listener btn Thống kê
        btnTK.addActionListener(e -> {
            pnlForm.removeAll();
            pnlForm.add(new ThongKe());
            pnlForm.revalidate();
            pnlForm.repaint();
        });
    }

    private JPanel initComponent() {
        JPanel root = new JPanel();
        root.setLayout(new BorderLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(0, 0, 10, 0);

        btnQLTV = new ButtonMenu("Quản lý thành viên");

        btnQLTB = new ButtonMenu("Quản lý thiết bị");

        btnTTSD = new ButtonMenu("Quản lý thông tin sử dụng");

        btnXLVP = new ButtonMenu("Xử lý vi phạm");

        btnTK = new ButtonMenu("Thống kê");

        ButtonMenu.activeButton = btnQLTV;
        pnlBtn = new MenuPanel();
        pnlBtn.setLayout(new GridBagLayout());

        constraints.gridx = 0;
        constraints.gridy = 0;
        pnlBtn.add(btnQLTV, constraints);

        constraints.gridy = 1;
        pnlBtn.add(btnQLTB, constraints);

        constraints.gridy = 2;
        pnlBtn.add(btnXLVP, constraints);

        constraints.gridy = 3;
        pnlBtn.add(btnTK, constraints);

        constraints.gridy = 4;
        pnlBtn.add(btnTTSD, constraints);

        pnlForm = new FormPanel();
        pnlForm.add(new QLThanhVienPanel());

        root.add(pnlBtn, BorderLayout.WEST);
        root.add(pnlForm, BorderLayout.CENTER);
        return root;
    }
}
