package com.project.GUI.Components.Buttons;

import javax.swing.*;

public class ButtonDelAll extends ButtonBase{
    public ButtonDelAll() {
        setText("Del All");

//        Set icon
        ImageIcon icon = new ImageIcon("src/main/resources/Images/delete.png");
        setIcon(icon);
    }
}
