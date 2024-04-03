package com.project.GUI.Forms.ThongKe;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.project.GUI.Components.FormPanel;
import com.project.GUI.Components.Buttons.Button;

public class ThongKe extends FormPanel {
    public ThongKe() {
        initCompontent();
    }

    private void initCompontent() {
        pnlHeader = new FormPanel();
        pnlMain = new FormPanel();
        btnTKUsers = new Button("Thành viên");
        btnTKThietBi = new Button("Thiết bị");
        btnTKViPham = new Button("Vi phạm");

        setLayout(new BorderLayout());

        // add btn to pnlHeader
        btnTKUsers.addMouseListener(actionUsers);
        pnlHeader.add(btnTKUsers);

        btnTKThietBi.addMouseListener(actionThietBi);
        pnlHeader.add(btnTKThietBi);

        btnTKViPham.addMouseListener(actionViPham);
        pnlHeader.add(btnTKViPham);
        add(pnlHeader, BorderLayout.NORTH);

        // add content to pnlMain
        TKThanhVien tkThanhVien = new TKThanhVien();
        pnlMain.add(tkThanhVien);
        add(pnlMain, BorderLayout.CENTER);

    }

    private JPanel pnlHeader;
    private JPanel pnlMain;
    private JButton btnTKUsers;
    private JButton btnTKThietBi;
    private JButton btnTKViPham;

    private MouseAdapter actionUsers = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            pnlMain.removeAll(); 
            TKThanhVien tkThanhVien = new TKThanhVien();
            pnlMain.add(tkThanhVien);
            pnlMain.revalidate();
            pnlMain.repaint();
        }
    };
    private MouseAdapter actionThietBi = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            pnlMain.removeAll(); 
            TKThietBi tkThietBi = new TKThietBi();
            pnlMain.add(tkThietBi);
            pnlMain.revalidate();
            pnlMain.repaint();
        }
    };
    private MouseAdapter actionViPham = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            pnlMain.removeAll(); 
            TKViPham tkViPham = new TKViPham();
            pnlMain.add(tkViPham);
            pnlMain.revalidate();
            pnlMain.repaint();
        }
    };

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.add(new ThongKe());
        frame.setVisible(true);
    }
}
