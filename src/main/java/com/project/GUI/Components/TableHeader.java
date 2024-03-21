package com.project.GUI.Components;

import com.project.GUI.Colors;
import com.project.GUI.Fonts;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TableHeader extends JLabel {
    public TableHeader(String text) {
        super(text);
        setOpaque(true);
        setBackground(Colors.secondaryColor);
        setFont(Fonts.defaultFont);
        setForeground(Color.WHITE);
        setBorder(new EmptyBorder(10, 5, 10, 5));
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        grphcs.setColor(Colors.primaryColor);
        grphcs.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
    }
}
