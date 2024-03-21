package com.project.GUI.Components;

import com.project.GUI.Colors;
import com.project.GUI.Fonts;

import javax.swing.*;
import java.awt.*;

public class FormCheckBox extends JCheckBox {
    public FormCheckBox(String label) {
        super(label);
        setBackground(Colors.bgColor);
        setFont(Fonts.defaultFont);
        setForeground(Colors.fontColor);
    }
}
