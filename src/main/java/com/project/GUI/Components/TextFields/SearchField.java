package com.project.GUI.Components.TextFields;

import com.project.GUI.GlobalVariables.Colors;
import com.project.GUI.GlobalVariables.Fonts;

import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class SearchField extends InputField {
    private final String placeHolder = "search";

    public SearchField(int columns) {
        super(columns);

        setText(placeHolder);
        setForeground(Color.GRAY);
        setFont(getFont().deriveFont(Font.ITALIC));

        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
//                Active font
                setFont(Fonts.defaultFont);
                setForeground(Colors.fontColor);
                if (getText().equals(placeHolder)) {
                    setText("");
                }
            }
        });

        addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if (getText().equals("")) {
                    setText(placeHolder);
                    setForeground(Color.GRAY);
                    setFont(getFont().deriveFont(Font.ITALIC));
                }
            }
        });
    }
}
