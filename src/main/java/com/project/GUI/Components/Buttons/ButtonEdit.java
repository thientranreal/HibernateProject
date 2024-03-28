package com.project.GUI.Components.Buttons;

import javax.swing.*;

public class ButtonEdit extends ButtonBase{
    public ButtonEdit() {
        setText("Edit");

//        Set icon
        ImageIcon icon = new ImageIcon("src/main/resources/Images/add.png");
        setIcon(icon);
    }
}
