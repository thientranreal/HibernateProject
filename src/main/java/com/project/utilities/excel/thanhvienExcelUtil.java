package com.project.utilities.excel;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.project.BLL.thanhvienBLL;
import com.project.models.thanhvien;

public class thanhvienExcelUtil extends ExcelUtil {

    private static final String[] EXCEL_EXTENSIONS = { "xls", "xlsx", "xlsm" };
    private static final Logger LOGGER = Logger.getLogger(thanhvienExcelUtil.class.getName());

    public static List<thanhvien> readthanhviensFromExcel() throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel File", EXCEL_EXTENSIONS);
        fileChooser.setFileFilter(filter);
        int option = fileChooser.showOpenDialog(null);

        if (option == JFileChooser.APPROVE_OPTION) {
            File inputFile = fileChooser.getSelectedFile();
            String filePath = inputFile.getAbsolutePath();

            try {
                List<List<String>> data = ExcelUtil.readExcel(filePath, 0);
                List<thanhvien> thanhviens = convertTothanhvienList(data);

                JOptionPane.showMessageDialog(null,
                        "Data has been read successfully from " + inputFile.getName() + ".");
                return thanhviens;
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "Error occurred while reading data from file: " + inputFile.getName(), e);
                showErrorDialog(e.getMessage(), "File Input Error");
                throw e;
            } catch (IllegalArgumentException e) {
                LOGGER.log(Level.SEVERE, "Error occurred while converting data to thanhvien: " + e.getMessage());
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

    private static List<thanhvien> convertTothanhvienList(List<List<String>> data) {
        List<thanhvien> thanhviens = new ArrayList<>();
        DecimalFormat decimalFormat = new DecimalFormat("0");
        for (int i = 1; i < data.size(); i++) {
            List<String> row = data.get(i);
            BigInteger id;
            try {
                String idString = row.get(0);
                if (idString.contains(".")) {
                    double idDouble = Double.parseDouble(idString);
                    idString = decimalFormat.format(idDouble);
                }
                id = new BigInteger(idString);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid integer value in input data", e);
            }
            String HoTen = row.get(1);
            String Khoa = row.get(2);
            String Nganh = row.get(3);
            // Sdt is int type in database:
            String Sdt;
            try {
                if (row.get(4).contains(".")) {
                    float floatValue = Float.parseFloat(row.get(4));
                    int intValue = (int) floatValue;
                    Sdt = String.valueOf(intValue);
                } else {
                    Sdt = row.get(4);
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid integer value in input data", e);
            }

            thanhvien model = new thanhvien(id, HoTen, Khoa, Nganh, Sdt);
            thanhviens.add(model);
            thanhvienBLL.getInstance().addModel(model);
        }

        return thanhviens;
    }

    public static void writethanhviensToExcel(List<thanhvien> thanhviens) throws IOException {
        List<List<String>> data = new ArrayList<>();

        // Create header row
        List<String> headerValues = Arrays.asList("MaTV", "HoTen", "Khoa", "Nganh", "Sdt");
        data.add(headerValues);

        // Write data rows
        for (thanhvien thanhvien : thanhviens) {
            List<String> values = Arrays.asList(
                    thanhvien.getMaTV().toString(),
                    thanhvien.getHoTen(),
                    thanhvien.getKhoa(),
                    thanhvien.getNganh(),
                    thanhvien.getSdt());
            data.add(values);
        }

        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel File", EXCEL_EXTENSIONS);
        fileChooser.setFileFilter(filter);
        int option = fileChooser.showSaveDialog(null);

        if (option == JFileChooser.APPROVE_OPTION) {
            File outputFile = fileChooser.getSelectedFile();
            String filePath = outputFile.getAbsolutePath();

            if (!filePath.endsWith(".xlsx")) {
                filePath += ".xlsx";
            }

            if (outputFile.exists()) {
                int overwriteOption = JOptionPane.showConfirmDialog(null,
                        "The file already exists. Do you want to overwrite it?", "File Exists",
                        JOptionPane.YES_NO_OPTION);
                if (overwriteOption == JOptionPane.NO_OPTION) {
                    return;
                }
            }

            try {
                writeExcel(data, filePath, "thanhviens");
                JOptionPane.showMessageDialog(null,
                        "Data has been written successfully to " + outputFile.getName() + ".");
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "Error occurred while writing data to file: " + outputFile.getName(), e);
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "File Output Error",
                        JOptionPane.ERROR_MESSAGE);
                throw e;
            }
        }
    }

}