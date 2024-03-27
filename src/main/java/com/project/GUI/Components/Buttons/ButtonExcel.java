package com.project.GUI.Components.Buttons;

import javax.swing.*;
import java.awt.*;

public class ButtonExcel extends ButtonBase {
    public ButtonExcel() {
        setText("Excel");

//        Set icon
        ImageIcon icon = new ImageIcon("src/main/resources/Images/excel.png");
        setIcon(icon);
    }
}
