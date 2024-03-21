package com.project.GUI.Components;

import com.project.GUI.Colors;
import com.project.GUI.Fonts;

import javax.swing.*;
import java.awt.*;

public class FormLabel extends JLabel {
    public FormLabel(String text) {
        super(text);
        setFont(Fonts.defaultFont);
        setForeground(Colors.fontColor);
    }
}
