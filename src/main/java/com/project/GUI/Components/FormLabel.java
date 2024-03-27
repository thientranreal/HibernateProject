package com.project.GUI.Components;

import com.project.GUI.GlobalVariables.Colors;
import com.project.GUI.GlobalVariables.Fonts;

import javax.swing.*;

public class FormLabel extends JLabel {
    public FormLabel(String text) {
        super(text);
        setFont(Fonts.defaultFont);
        setForeground(Colors.fontColor);
    }
}
