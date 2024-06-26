package com.project.GUI.Components.Buttons;

import javax.swing.*;
import java.awt.*;

public class ButtonWarning extends ButtonBase {
    public ButtonWarning() {
        setText("Warning");

 //        Set icon
        ImageIcon icon = new ImageIcon("src/main/resources/Images/warning.png");
        setIcon(icon);
    }

    @Override
    protected void paintComponent(Graphics g) {
        // Create a Graphics2D object from the Graphics object
        Graphics2D g2d = (Graphics2D) g.create();

        // If the button is being hovered over, change the color to light gray
        if (getModel().isRollover()) {
            g2d.setColor(Color.decode("#e65100"));
            setForeground(Color.WHITE);
        } else {
            // Otherwise, set the background color to blue
            g2d.setColor(Color.decode("#ff9800"));
            // Set the text color to white
            setForeground(Color.WHITE);
        }

        // Draw a rectangle with the chosen color
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Call the parent class's paintComponent method to paint the other components of the button
        super.jButtonPaintComponent(g2d);

        // Dispose of the Graphics2D object to free up system resources
        g2d.dispose();
    }
}
