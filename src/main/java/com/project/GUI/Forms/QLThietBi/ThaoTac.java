package com.project.GUI.Forms.QLThietBi;

import java.awt.*;
import javax.swing.*;

import com.project.GUI.Components.FormLabel;
import com.project.GUI.Components.FormPanel;
import com.project.GUI.Components.Buttons.ButtonDel;
import com.project.GUI.Components.Buttons.ButtonSave;

public class ThaoTac extends JFrame{

    public ThaoTac(){
        initCompontent();
    }
    public void initCompontent(){
        pnlInfor = new FormPanel();
        pnlButtons = new FormPanel();
        lbId = new FormLabel("ID");
        lbName = new FormLabel("Tên thiết bị");
        lbDecription = new FormLabel("Mô tả");
        inputId = new JTextField();
        inputName = new JTextField();
        inputDecription = new JTextField();
        btnDel = new ButtonDel();
        btnSave = new ButtonSave();

        GridBagConstraints gbc;


        setSize(new Dimension(500,300));
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        pnlInfor.setPreferredSize(new Dimension(400, 200));
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[] {0, 10, 0};
        gridBagLayout.rowHeights = new int[] {0, 10, 0, 10, 0};
        pnlInfor.setLayout(gridBagLayout);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        pnlInfor.add(lbId, gbc);

        inputId.setPreferredSize(new Dimension(200,35));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        pnlInfor.add(inputId, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        pnlInfor.add(lbName, gbc);

        inputName.setPreferredSize(new Dimension(200,35));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        pnlInfor.add(inputName, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        pnlInfor.add(lbDecription, gbc);

        inputDecription.setPreferredSize(new Dimension(200,35));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 4;
        pnlInfor.add(inputDecription, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;

        add(pnlInfor,BorderLayout.CENTER);

        pnlButtons.add(btnSave);
        pnlButtons.add(btnDel);
        add(pnlButtons, BorderLayout.SOUTH);
    }
    private JPanel pnlInfor;
    private JPanel pnlButtons;
    private FormLabel lbId;
    private FormLabel lbName;
    private FormLabel lbDecription;
    private JTextField inputId;
    private JTextField inputName;
    private JTextField inputDecription;
    private JButton btnSave;
    private JButton btnDel;
}
