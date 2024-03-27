package com.project.GUI.Components.Buttons;

import com.project.GUI.Colors;
import java.awt.*;

public class ButtonCancel extends ButtonBase {
    public ButtonCancel() {
        setText("Cancel");
    }

    @Override
    protected void paintComponent(Graphics g) {
        // Create a Graphics2D object from the Graphics object
        Graphics2D g2d = (Graphics2D) g.create();

        // If the button is being hovered over, change the color to light gray
        if (getModel().isRollover()) {
            g2d.setColor(Color.decode("#353839"));
            setForeground(Color.LIGHT_GRAY);
        } else {
            // Otherwise, set the background color to blue
            g2d.setColor(Colors.bgColor);
            // Set the text color to white
            setForeground(Colors.primaryColor);
        }

        // Draw a rectangle with the chosen color
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Call the parent class's paintComponent method to paint the other components of the button
        super.jButtonPaintComponent(g2d);

        // Set the border color and draw the border
        g2d.setColor(Colors.primaryColor);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawRect(0, 0, getWidth() - 1, getHeight() - 1);

        // Dispose of the Graphics2D object to free up system resources
        g2d.dispose();
    }
}
