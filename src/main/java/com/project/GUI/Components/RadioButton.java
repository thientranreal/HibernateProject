package com.project.GUI.Components;

import com.project.GUI.GlobalVariables.Colors;
import com.project.GUI.GlobalVariables.Fonts;

import javax.swing.*;

public class RadioButton extends JRadioButton {
    public RadioButton(String label) {
        super(label);
        setBackground(Colors.bgColor);
        setFont(Fonts.defaultFont);
        setForeground(Colors.fontColor);
    }
}
