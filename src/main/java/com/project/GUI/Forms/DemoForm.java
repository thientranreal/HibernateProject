package com.project.GUI.Forms;

import com.project.GUI.Components.Buttons.ButtonAdd;
import com.project.GUI.Components.Buttons.ButtonDel;
import com.project.GUI.Components.Buttons.ButtonRefresh;
import com.project.GUI.Components.Buttons.ButtonSearch;
import com.project.GUI.Components.FormPanel;
import com.project.GUI.Components.Table;
import com.project.GUI.Components.TextFields.SearchField;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DemoForm extends JPanel {
    public DemoForm() {
        setLayout(new BorderLayout());

//        Create search input, button search, button refresh
        JTextField searchInput = new SearchField(20);
        JButton btnSearch = new ButtonSearch();
        JButton btnRefresh = new ButtonRefresh();

//        Create panel to contain search input
        JPanel pnlSearch = new FormPanel();
        pnlSearch.add(searchInput);
        pnlSearch.add(btnSearch);
        pnlSearch.add(btnRefresh);

//        Create table for showing data
        JTable table = new Table();
//        Create header for table
        table.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {"Text",
                        "Text",
                        "Text",
                        "Text",
                        "Text"
                })
        );
//        Add data for table
        DefaultTableModel model_table = (DefaultTableModel) table.getModel();
        for (int i = 0; i < 10; i++) {
            model_table.addRow(new Object[]{
                    "Text",
                    "Text",
                    "Text",
                    "Text",
                    "Text"
            });
        }

//        Create panel to contain table
        JScrollPane pnlTable = new JScrollPane();
        pnlTable.setBorder(new EmptyBorder(10, 10, 10, 10));
        pnlTable.setViewportView(table);

//        Create button add, button delete
        JButton btnAdd = new ButtonAdd();
        JButton btnDel = new ButtonDel();
//        Create panel to contain button
        JPanel pnlAction = new FormPanel();
        pnlAction.setLayout(new GridBagLayout());

//        Add constraints to make button align vertically
        GridBagConstraints constraints = new GridBagConstraints();
        // Add padding bottom 10px
        constraints.insets = new Insets(0, 0, 10, 0);
        constraints.gridx = 0;
        constraints.gridy = 0;
        pnlAction.add(btnAdd, constraints);

        constraints.gridy = 1;
        pnlAction.add(btnDel, constraints);


//        Add all panel to this panel
        add(pnlSearch, BorderLayout.NORTH);
        add(pnlTable, BorderLayout.CENTER);
        add(pnlAction, BorderLayout.EAST);
    }
}
