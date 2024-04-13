package com.project.GUI.Forms.QLThanhVien;

import com.project.BLL.thanhvienBLL;
import com.project.GUI.Components.TextFields.IDField;
import com.project.GUI.GlobalVariables.Colors;
import com.project.GUI.Components.Buttons.ButtonCancel;
import com.project.GUI.Components.Buttons.ButtonExcel;
import com.project.GUI.Components.Buttons.ButtonRefresh;
import com.project.GUI.Components.Buttons.ButtonSave;
import com.project.GUI.Components.FormLabel;
import com.project.GUI.Components.FormPanel;
import com.project.GUI.Components.TextFields.InputField;
import com.project.GUI.GlobalVariables.Fonts;
import com.project.models.thanhvien;
import com.project.utilities.excel.thanhvienExcelUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Objects;

public class ThemThanhVienForm extends JFrame {
    private Point mouseDownCompCoords;
    private ButtonSave btnSave;
    private ButtonCancel btnCancel;
    private ButtonRefresh btnRefresh;
    private ButtonExcel btnExcel;
    private IDField inputMaTV;
    private InputField inputHoTen;
    private InputField inputKhoa;
    private InputField inputNganh;
    private InputField inputSDT;
    private InputField inputPassword;
    private InputField inputEmail;
    private JButton refresh;
    private JPanel pnlInput;

    public ThemThanhVienForm(JButton refresh) {
        this.refresh = refresh;
//        Add Content into JFrame
        add(initCompontent());

        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        // Add mouse listener
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mouseDownCompCoords = e.getPoint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                mouseDownCompCoords = null;
            }
        });

        // Add mouse motion listener
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Point currCoords = e.getLocationOnScreen();
                setLocation(currCoords.x - mouseDownCompCoords.x, currCoords.y - mouseDownCompCoords.y);
            }
        });
    }

    private JPanel initCompontent() {
        JPanel root = new FormPanel();
        root.setLayout(new BorderLayout());

        root.setBorder(BorderFactory.createLineBorder(Colors.primaryColor, 5));

//        Create Header
        JPanel pnlHeader = new FormPanel();
        JLabel lbHeader = new FormLabel("Thêm mới thành viên");
        lbHeader.setFont(Fonts.headerFont);
        lbHeader.setForeground(Color.BLACK);
        pnlHeader.add(lbHeader);

//        Create input field
        JLabel lbMaTV = new FormLabel("Mã TV: ");
        inputMaTV = new IDField(20);
        JLabel lbHoTen = new FormLabel("Họ tên: ");
        inputHoTen = new InputField(20);
        JLabel lbKhoa = new FormLabel("Khoa: ");
        inputKhoa = new InputField(20);
        JLabel lbNganh = new FormLabel("Ngành ");
        inputNganh = new InputField(20);
        JLabel lbSDT = new FormLabel("SĐT: ");
        inputSDT = new InputField(20);
        JLabel lbPassword = new FormLabel("Password: ");
        inputPassword = new InputField(20);
        JLabel lbEmail = new FormLabel("Email: ");
        inputEmail = new InputField(20);

//        Create panel to contain input field
        pnlInput = new FormPanel();
        pnlInput.setLayout(new GridBagLayout());
//        Add constraints
        GridBagConstraints constraints = new GridBagConstraints();
//          Add padding bottom 10px
        constraints.insets = new Insets(0, 0, 10, 0);

        int y = 0;
//        Row 0
        constraints.gridx = 0;
        constraints.gridy = y++;
        pnlInput.add(lbMaTV, constraints);
        constraints.gridx = 1;
        pnlInput.add(inputMaTV, constraints);

//        Row 1
        constraints.gridx = 0;
        constraints.gridy = y++;
        pnlInput.add(lbHoTen, constraints);
        constraints.gridx = 1;
        pnlInput.add(inputHoTen, constraints);

//        Row 2
        constraints.gridx = 0;
        constraints.gridy = y++;
        pnlInput.add(lbKhoa, constraints);
        constraints.gridx = 1;
        pnlInput.add(inputKhoa, constraints);

//        Row 3
        constraints.gridx = 0;
        constraints.gridy = y++;
        pnlInput.add(lbNganh, constraints);
        constraints.gridx = 1;
        pnlInput.add(inputNganh, constraints);

//        Row 4
        constraints.gridx = 0;
        constraints.gridy = y++;
        pnlInput.add(lbSDT, constraints);
        constraints.gridx = 1;
        pnlInput.add(inputSDT, constraints);

        constraints.gridx = 0;
        constraints.gridy = y++;
        pnlInput.add(lbPassword, constraints);
        constraints.gridx = 1;
        pnlInput.add(inputPassword, constraints);

        constraints.gridx = 0;
        constraints.gridy = y++;
        pnlInput.add(lbEmail, constraints);
        constraints.gridx = 1;
        pnlInput.add(inputEmail, constraints);

//        Create button
        btnSave = new ButtonSave();
        btnCancel = new ButtonCancel();
        btnRefresh = new ButtonRefresh();
        btnExcel = new ButtonExcel();
//        Create panel to contain button
        JPanel pnlBtn = new FormPanel();
        pnlBtn.add(btnSave);
        pnlBtn.add(btnCancel);

//        Create panel to contain refresh, excel button
        JPanel pnlMore = new FormPanel();
        pnlMore.setLayout(new GridBagLayout());
        constraints.gridx = 0;
        constraints.gridy = 0;
        pnlMore.add(btnRefresh, constraints);
        constraints.gridy = 1;
        pnlMore.add(btnExcel, constraints);

//        Add all panel to this panel
        root.add(pnlHeader, BorderLayout.PAGE_START);
        root.add(pnlInput, BorderLayout.CENTER);
        root.add(pnlBtn, BorderLayout.SOUTH);
        root.add(pnlMore, BorderLayout.EAST);

//        Add event listener for cancel button
        btnCancel.addActionListener(e -> {
            dispose();
        });

        btnSave.addActionListener(e -> {
            addMember();
            refresh.doClick();
            clearForm();
        });

        btnExcel.addActionListener(e -> {
            try {
                if(!Objects.requireNonNull(thanhvienExcelUtil.readthanhviensFromExcel()).isEmpty()) {
                    JOptionPane.showMessageDialog(null,"Thêm thành công");

                }else {
                    JOptionPane.showMessageDialog(null,"Thêm thất bại");
                }

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        btnRefresh.addActionListener(e -> clearForm());

        return root;
    }

    public void clearForm() {
        for (Component comp : pnlInput.getComponents()) {
            if (comp instanceof JTextField input) {
                input.setText("");
            }
        }
    }

    public void addMember() {
        BigInteger maSV = new BigInteger(inputMaTV.getText().trim());
        String tenSV =inputHoTen.getText();
        String khoa =inputKhoa.getText();
        String nganh =inputNganh.getText();
        String sdt =inputSDT.getText();
        String password =inputPassword.getText();
        String email =inputEmail.getText();

        thanhvien newMember = new thanhvien(maSV,tenSV,khoa,nganh,sdt,password,email);

        BigInteger newMemberResult = thanhvienBLL.getInstance().addModel(newMember);

        if(newMemberResult.compareTo(BigInteger.ZERO) > 0) {
            JOptionPane.showMessageDialog(null,"Thêm thành công");
            QLThanhVienPanel panel = new QLThanhVienPanel();
            panel.updateMemberFromList();
            clearForm();
        }else {
            JOptionPane.showMessageDialog(null,"Thêm thất bại");
        }
    }

//    public static void main(String[] args) {
//        new ThemThanhVienForm();
//    }
}
