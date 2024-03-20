package com.project.GUI.Components;

import com.project.GUI.Colors;

import javax.swing.*;
import java.awt.*;

public class FormLabel extends JLabel {
    public FormLabel(String text) {
        super(text);
        setFont(new Font("sanserif", 1, 12));
        setForeground(Colors.fontColor);
    }
}
