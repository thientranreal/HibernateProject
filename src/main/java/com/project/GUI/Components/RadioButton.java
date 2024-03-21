package com.project.GUI.Components;

import com.project.GUI.Colors;
import com.project.GUI.Fonts;

import javax.swing.*;

public class RadioButton extends JRadioButton {
    public RadioButton(String label) {
        super(label);
        setBackground(Colors.bgColor);
        setFont(Fonts.defaultFont);
        setForeground(Colors.fontColor);
    }
}
