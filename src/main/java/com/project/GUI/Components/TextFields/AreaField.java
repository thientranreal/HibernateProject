package com.project.GUI.Components.TextFields;

import com.project.GUI.Colors;
import com.project.GUI.Fonts;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class AreaField extends JTextArea {
    private final Border focusedBorder;
    private final Border unfocusedBorder;
    public AreaField(int rows, int columns) {
        super(rows, columns);
        setLineWrap(true);

        //          Set padding and border bottom for text field
        Border padding = new EmptyBorder(5, 5, 5, 5);

//        Set font color
        setForeground(Colors.fontColor);

//        Text field when being focused when turn blue
        focusedBorder = new CompoundBorder(new MatteBorder(
                2, 2, 2, 2, Colors.secondaryColor),
                padding
        );
        unfocusedBorder = new CompoundBorder(new MatteBorder(
                2, 2, 2, 2, Colors.fadeColor),
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
