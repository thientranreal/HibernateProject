package com.project.utilities.excel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.project.BLL.thietbiBLL;
import com.project.models.thietbi;

public class thietbiExcelUtil extends ExcelUtil {
    // Finish the code like thanhvienExcelUItil.java:
    private static final String[] EXCEL_EXTENSIONS = { "xls", "xlsx", "xlsm" };
    private static final Logger LOGGER = Logger.getLogger(thietbiExcelUtil.class.getName());

    public static List<thietbi> readThietbisFromExcel() throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel File", EXCEL_EXTENSIONS);
        fileChooser.setFileFilter(filter);
        int option = fileChooser.showOpenDialog(null);

        if (option == JFileChooser.APPROVE_OPTION) {
            File inputFile = fileChooser.getSelectedFile();
            String filePath = inputFile.getAbsolutePath();

            try {
                List<List<String>> data = ExcelUtil.readExcel(filePath, 0);
                List<thietbi> thietbis = convertToThietbiList(data);

                JOptionPane.showMessageDialog(null,
                        "Data has been read successfully from " + inputFile.getName() + ".");
                return thietbis;
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "Error occurred while reading data from file: " + inputFile.getName(), e);
                showErrorDialog(e.getMessage(), "File Input Error");
                throw e;
            } catch (IllegalArgumentException e) {
                LOGGER.log(Level.SEVERE, "Error occurred while converting data to thietbi: " + e.getMessage());
                showErrorDialog(e.getMessage(), "Data Conversion Error");
                throw e;
            }
        }

        return null;
    }

    private static void showErrorDialog(String message, String title) {
        LOGGER.log(Level.WARNING, "Error occurred: " + message);
        JOptionPane.showMessageDialog(null, "Error: " + message, title, JOptionPane.ERROR_MESSAGE);
    }

    private static List<thietbi> convertToThietbiList(List<List<String>> data) {
        List<thietbi> thietbis = new ArrayList<>();
        for (int i = 1; i < data.size(); i++) {
            List<String> row = data.get(i);
            try {
//                int MaTB;
//                try {
//                    if (row.get(0).contains("."))
//                        MaTB = (int) Float.parseFloat(row.get(0));
//                    else
//                        MaTB = Integer.parseInt(row.get(0));
//                } catch (NumberFormatException e) {
//                    throw new IllegalArgumentException("Invalid integer value in input data", e);
//                }
                String TenTB = row.get(1);
                String MoTaTB = row.get(2);

                thietbi thietbi = new thietbi(TenTB, MoTaTB);
                thietbis.add(thietbi);
                thietbiBLL.getInstance().addModel(thietbi);
            } catch (Exception e) {
                LOGGER.log(Level.WARNING, "Error occurred while converting data to thietbi: " + e.getMessage());
                throw new IllegalArgumentException("Invalid data found in Excel file.");
            }
        }
        return thietbis;
    }

    public static void writeThietbisToExcel(List<thietbi> thietbis) throws IOException {
        List<List<String>> data = new ArrayList<>();

        // Create header row
        List<String> header = Arrays.asList("MaTB", "TenTB", "MoTaTB");
        data.add(header);

        // Write data rows:
        for (thietbi thietbi : thietbis) {
            List<String> row = new ArrayList<>();
            row.add(String.valueOf(thietbi.getMaTB()));
            row.add(thietbi.getTenTB());
            row.add(thietbi.getMoTaTB());
            data.add(row);
        }

        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel File", EXCEL_EXTENSIONS);
        fileChooser.setFileFilter(filter);
        int option = fileChooser.showSaveDialog(null);

        if (option == JFileChooser.APPROVE_OPTION) {
            File outputFile = fileChooser.getSelectedFile();
            String filePath = outputFile.getAbsolutePath();
            if (!filePath.endsWith(".xls") && !filePath.endsWith(".xlsx") && !filePath.endsWith(".xlsm")) {
                filePath += ".xlsx";
            }

            try {
                ExcelUtil.writeExcel(data, filePath, "Thietbis");
                JOptionPane.showMessageDialog(null,
                        "Data has been written successfully to " + outputFile.getName() + ".");
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "Error occurred while writing data to file: " + outputFile.getName(), e);
                showErrorDialog(e.getMessage(), "File Output Error");
                throw e;
            }
        }
    }
}
