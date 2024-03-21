package com.project.GUI.Components;

import com.project.GUI.Colors;
import com.project.GUI.Fonts;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class Table extends JTable {
    private int hoveredRow = -1;
    public Table() {
        setFont(Fonts.defaultFont);
        setShowHorizontalLines(true);
        setGridColor(new Color(230, 230, 230));
        setRowHeight(40);

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int row = rowAtPoint(e.getPoint());
                if (row != hoveredRow) {
                    hoveredRow = row;
                    repaint();
                }
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                hoveredRow = -1;
                repaint();
            }
        });

        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
                return new TableHeader(o + "");
            }
        });
        setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean selected, boolean bln1, int row, int column) {
                Component com = super.getTableCellRendererComponent(jtable, o, selected, bln1, row, column);
                setBorder(noFocusBorder);

                if (selected) {
                    com.setBackground(Colors.primaryColor);
                    com.setForeground(Color.WHITE);
                } else {
                    com.setBackground(Color.WHITE);
                    com.setForeground(Colors.fontColor);
                }

                if (row == hoveredRow && !selected) {
                    com.setBackground(Colors.fadeColor);
                    com.setForeground(Color.WHITE);
                }
                return com;
            }
        });
    }
}
