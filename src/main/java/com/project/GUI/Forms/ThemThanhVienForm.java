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

public class ThemThanhVienForm extends JPanel {
    public ThemThanhVienForm() {
        setLayout(new BorderLayout());

//        Create input field
        JLabel lbHoTen = new FormLabel("Họ tên");
        JTextField inputHoTen = new InputField(20);
        JLabel lbKhoa = new FormLabel("Khoa");
        JTextField inputKhoa = new InputField(20);
        JLabel lbNganh = new FormLabel("Nganh");
        JTextField inputNganh = new InputField(20);
        JLabel lbSDT = new FormLabel("SDT");
        JTextField inputSDT = new InputField(20);

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
        pnlInput.add(lbHoTen, constraints);
        constraints.gridx = 1;
        pnlInput.add(inputHoTen, constraints);

//        Row 1
        constraints.gridx = 0;
        constraints.gridy = 1;
        pnlInput.add(lbKhoa, constraints);
        constraints.gridx = 1;
        pnlInput.add(inputKhoa, constraints);

//        Row 2
        constraints.gridx = 0;
        constraints.gridy = 2;
        pnlInput.add(lbNganh, constraints);
        constraints.gridx = 1;
        pnlInput.add(inputNganh, constraints);

//        Row 3
        constraints.gridx = 0;
        constraints.gridy = 3;
        pnlInput.add(lbSDT, constraints);
        constraints.gridx = 1;
        pnlInput.add(inputSDT, constraints);

//        Create button save, button cancel
        JButton btnSave = new ButtonSave();
        JButton btnCancel = new ButtonCancel();
//        Create panel to contain button
        JPanel pnlBtn = new FormPanel();
        pnlBtn.add(btnSave);
        pnlBtn.add(btnCancel);

//        Add all panel to this panel
        add(pnlInput, BorderLayout.CENTER);
        add(pnlBtn, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();

        frame.add(new ThemThanhVienForm());

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
