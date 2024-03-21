package com.project.GUI.Forms;

import com.project.GUI.Components.Buttons.ButtonCancel;
import com.project.GUI.Components.Buttons.ButtonSave;
import com.project.GUI.Components.FormCheckBox;
import com.project.GUI.Components.FormLabel;
import com.project.GUI.Components.FormPanel;
import com.project.GUI.Components.RadioButton;
import com.project.GUI.Components.TextFields.InputField;

import javax.swing.*;
import java.awt.*;

public class DemoAddForm extends JPanel {
    public DemoAddForm() {
        setLayout(new BorderLayout());

//        Create input field
        JLabel label = new FormLabel("Text");
        JTextField input = new InputField(20);
        JLabel label2 = new FormLabel("Text");
        JTextField input2 = new InputField(20);
        JLabel label3 = new FormLabel("Text");
        JTextField input3 = new InputField(20);

//        Create panel to contain input field
        JPanel pnlInput = new FormPanel();
        pnlInput.setLayout(new GridBagLayout());
//        Add constraints
        GridBagConstraints constraints = new GridBagConstraints();
//          Add padding bottom 10px
        constraints.insets = new Insets(0, 0, 10, 0);

//        Row 0
        constraints.gridx = 0;
        constraints.gridy = 0;
        pnlInput.add(label, constraints);
        constraints.gridx = 1;
        pnlInput.add(input, constraints);

//        Row 1
        constraints.gridx = 0;
        constraints.gridy = 1;
        pnlInput.add(label2, constraints);
        constraints.gridx = 1;
        pnlInput.add(input2, constraints);

//        Row 2
        constraints.gridx = 0;
        constraints.gridy = 2;
        pnlInput.add(label3, constraints);
        constraints.gridx = 1;
        pnlInput.add(input3, constraints);

//        Create button save, button cancel
        JButton btnSave = new ButtonSave();
        JButton btnCancel = new ButtonCancel();
//        Create panel to contain button
        JPanel pnlBtn = new FormPanel();
        pnlBtn.add(btnSave);
        pnlBtn.add(btnCancel);
        pnlBtn.add(new FormCheckBox("CheckBox"));
        pnlBtn.add(new RadioButton("RadioButton"));

//        Add all panel to this panel
        add(pnlInput, BorderLayout.CENTER);
        add(pnlBtn, BorderLayout.SOUTH);
    }
}
