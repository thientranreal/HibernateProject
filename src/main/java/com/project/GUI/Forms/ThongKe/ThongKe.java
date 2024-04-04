package com.project.GUI.Forms.ThongKe;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.project.GUI.Components.Buttons.ButtonNormal;
import com.project.GUI.Components.FormPanel;

public class ThongKe extends FormPanel {
    public ThongKe() {
        initCompontent();
    }

    private void initCompontent() {
        pnlHeader = new FormPanel();
        pnlMain = new FormPanel();
        btnTKUsers = new ButtonNormal("Thành viên");
        btnTKThietBi = new ButtonNormal("Thiết bị");
        btnTKViPham = new ButtonNormal("Vi phạm");

        setLayout(new BorderLayout());

        // add btn to pnlHeader
        btnTKUsers.addActionListener(actionUsers);
        pnlHeader.add(btnTKUsers);

        btnTKThietBi.addActionListener(actionThietBi);
        pnlHeader.add(btnTKThietBi);

        btnTKViPham.addActionListener(actionViPham);
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

    private ActionListener actionUsers = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            pnlMain.removeAll(); 
            TKThanhVien tkThanhVien = new TKThanhVien();
            pnlMain.add(tkThanhVien);
            pnlMain.revalidate();
            pnlMain.repaint();
        }
    };
    private ActionListener actionThietBi = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            pnlMain.removeAll(); 
            TKThietBi tkThietBi = new TKThietBi();
            pnlMain.add(tkThietBi);
            pnlMain.revalidate();
            pnlMain.repaint();
        }
    };
    private ActionListener actionViPham = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            pnlMain.removeAll(); 
            TKViPham tkViPham = new TKViPham();
            pnlMain.add(tkViPham);
            pnlMain.revalidate();
            pnlMain.repaint();
        }
    };

//    public static void main(String[] args) {
//        JFrame frame = new JFrame();
//        frame.add(new ThongKe());
//        frame.setVisible(true);
//    }
}
