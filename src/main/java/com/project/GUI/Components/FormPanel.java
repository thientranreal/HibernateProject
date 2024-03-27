package com.project.GUI.Components;

import com.project.GUI.GlobalVariables.Colors;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class FormPanel extends JPanel {
    public FormPanel() {
        setBackground(Colors.bgColor);
//        Add padding for this panel
        setBorder(new EmptyBorder(10,10,10,10));
    }
}
