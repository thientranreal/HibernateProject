package com.project.GUI;

import com.project.GUI.Components.Buttons.ButtonAdd;
import com.project.GUI.Components.Buttons.ButtonDel;
import com.project.GUI.Components.Buttons.ButtonRefresh;
import com.project.GUI.Components.Buttons.ButtonSearch;
import com.project.GUI.Components.FormLabel;
import com.project.GUI.Components.FormPanel;
import com.project.GUI.Components.Table;
import com.project.GUI.Components.TextFields.InputField;
import com.project.GUI.Components.TextFields.SearchField;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TestUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame();

        JPanel root = new FormPanel();
        root.setLayout(new GridLayout(4,1));

        FormLabel label = new FormLabel("Text");
        InputField input = new InputField(20);
        FormLabel label2 = new FormLabel("Text");
        InputField input2 = new InputField(20);

        JPanel pnlInput = new FormPanel();
        pnlInput.setLayout(new GridLayout(2,2));
        pnlInput.add(label);
        pnlInput.add(input);
        pnlInput.add(label2);
        pnlInput.add(input2);

        ButtonSearch btnSearch = new ButtonSearch();
        ButtonAdd btnAdd = new ButtonAdd();
        ButtonDel btnDel = new ButtonDel();
        ButtonRefresh btnRefresh = new ButtonRefresh();

        JPanel pnlBtn = new FormPanel();
        pnlBtn.add(btnAdd);
        pnlBtn.add(btnDel);

        SearchField search = new SearchField(20);
        JPanel pnlSearch = new FormPanel();
        pnlSearch.add(search);
        pnlSearch.add(btnSearch);
        pnlSearch.add(btnRefresh);

        root.add(pnlInput);
        root.add(pnlBtn);
        root.add(pnlSearch);

//      JTable
        JTable table = new Table();
        table.setModel(new DefaultTableModel(
                                new Object[][] {
                                },
                                new String[] {"CourseID",
                                        "Title",
                                        "PersonID",
                                        "LastName",
                                        "FirstName"
                                })
        );

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

        JScrollPane pnlTable = new JScrollPane();
        pnlTable.setViewportView(table);
        root.add(pnlTable);

        frame.add(root);

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
