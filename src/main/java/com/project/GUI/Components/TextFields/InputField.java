package com.project.GUI.Components.TextFields;

import com.project.GUI.GlobalVariables.Colors;
import com.project.GUI.GlobalVariables.Fonts;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.event.*;

public class InputField extends JTextField {
    private static final long serialVersionUID = 1L;
    private final Border focusedBorder;
    private final Border unfocusedBorder;
    public InputField(int columns) {
        super(columns);
//          Set padding and border bottom for text field
        Border padding = new EmptyBorder(5, 5, 5, 5);

//        Set font color
        setForeground(Colors.fontColor);

//        Text field when being focused when turn blue
        focusedBorder = new CompoundBorder(new MatteBorder(
                0, 0, 2, 0, Colors.secondaryColor),
                padding
        );
        unfocusedBorder = new CompoundBorder(new MatteBorder(
                0, 0, 2, 0, Colors.fadeColor),
                padding
        );

//      Set border to unfocus
        setBorder(unfocusedBorder);

//        Set font
        setFont(Fonts.defaultFont);

//       Focus gain event
        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                setBorder(focusedBorder);
            }
        });

//        Focus lost event
        addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                setBorder(unfocusedBorder);
            }
        });
    }
}
