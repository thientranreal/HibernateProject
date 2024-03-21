package com.project.GUI.Components.Buttons;

import com.project.GUI.Colors;
import com.project.GUI.Fonts;

import javax.swing.*;
import java.awt.*;

public abstract class ButtonBase extends JButton {
    public ButtonBase() {
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFont(Fonts.defaultFont);
        setPreferredSize(new Dimension(100, 40));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public void setIcon(ImageIcon icon) {
        Image img = icon.getImage();
//        Set icon button size
        Image newImg = img.getScaledInstance(30, 25, Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImg);
        super.setIcon(icon);
    }

    @Override
    protected void paintComponent(Graphics g) {
        // Create a Graphics2D object from the Graphics object
        Graphics2D g2d = (Graphics2D) g.create();

        // If the button is being hovered over, change the color to light gray
        if (getModel().isRollover()) {
            g2d.setColor(Color.LIGHT_GRAY);
            setForeground(Color.BLACK);
        } else {
            // Otherwise, set the background color to blue
            g2d.setColor(Colors.primaryColor);
            // Set the text color to white
            setForeground(Color.WHITE);
        }

        // Draw a rectangle with the chosen color
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Call the parent class's paintComponent method to paint the other components of the button
        super.paintComponent(g2d);

        // Dispose of the Graphics2D object to free up system resources
        g2d.dispose();
    }
}
