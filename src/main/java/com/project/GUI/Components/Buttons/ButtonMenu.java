package com.project.GUI.Components.Buttons;

import com.project.GUI.GlobalVariables.Colors;
import java.awt.*;

public class ButtonMenu extends ButtonBase {
    public static ButtonMenu activeButton;
    public ButtonMenu(String text) {
        setText(text);
        setPreferredSize(new Dimension(230, 100));

        addActionListener(e -> {
            if (activeButton != this) {
                activeButton.repaint();
                activeButton = this;
                activeButton.repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        // Create a Graphics2D object from the Graphics object
        Graphics2D g2d = (Graphics2D) g.create();

        // If the button is being hovered over, change the color to light gray
        if (getModel().isRollover()) {
            g2d.setColor(Colors.fadeColor);
            setForeground(Color.WHITE);
        } else {
            if (activeButton.getText().equals(this.getText())) {
                // Otherwise, set the background color to blue
                g2d.setColor(Color.BLACK);
                // Set the text color to white
                setForeground(Color.WHITE);
            } else {
                // Otherwise, set the background color to blue
                g2d.setColor(Colors.primaryColor);
                // Set the text color to white
                setForeground(Color.WHITE);
            }
        }

        // Draw a rectangle with the chosen color
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Call the parent class's paintComponent method to paint the other components of the button
        super.jButtonPaintComponent(g2d);

        // Dispose of the Graphics2D object to free up system resources
        g2d.dispose();
    }
}
