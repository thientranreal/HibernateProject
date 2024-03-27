package com.project.GUI.Components.Buttons;

import javax.swing.*;

public class ButtonSave extends ButtonBase {
    public ButtonSave() {
        setText("Save");

 //        Set icon
        ImageIcon icon = new ImageIcon("src/main/resources/Images/save.png");
        setIcon(icon);
    }
}
