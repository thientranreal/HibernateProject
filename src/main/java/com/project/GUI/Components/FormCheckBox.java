package com.project.GUI.Components;

import com.project.GUI.GlobalVariables.Colors;
import com.project.GUI.GlobalVariables.Fonts;

import javax.swing.*;

public class FormCheckBox extends JCheckBox {
    public FormCheckBox(String label) {
        super(label);
        setBackground(Colors.bgColor);
        setFont(Fonts.defaultFont);
        setForeground(Colors.fontColor);
    }
}
