package com.project.BLL;

import com.project.DAL.thanhvienDAL;
import com.project.models.thanhvien;

import javax.swing.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class thanhvienBLL {
    private final List<thanhvien> members = new ArrayList<>();
    private static thanhvienBLL instance;

    private thanhvienBLL() {
        members.addAll(thanhvienDAL.getInstance().getMemberList());
    }

    public static thanhvienBLL getInstance() {
        if (instance == null) {
            instance = new thanhvienBLL();
        }
        return instance;
    }

    public List<thanhvien> getAllModels() {
        return members;
    }

    public void refresh() {
        members.clear();
        members.addAll(thanhvienDAL.getInstance().getMemberList());
    }


    public thanhvien getModelById(BigInteger id) {
        return thanhvienDAL.getInstance().getById(id);
    }

    public BigInteger addModel(thanhvien member) {
        // Nothing can be null: MaTV, HoTen, Khoa, Nganh, SDT:
        if (String.valueOf(member.getMaTV()).isEmpty() || String.valueOf(member.getMaTV()).length() < 10) {
            JOptionPane.showMessageDialog(null,"Mã thành viên chưa hợp lệ");
            return BigInteger.valueOf(-1);
        }

        if (member.getHoTen().isEmpty() || member.getHoTen().length() > 50) {
            JOptionPane.showMessageDialog(null,"Họ tên không hợp lệ");
            return BigInteger.valueOf(-1);
        }
        if (member.getKhoa().isEmpty() || member.getKhoa().length() > 50) {
            JOptionPane.showMessageDialog(null,"Khoa không hợp lệ");
            return BigInteger.valueOf(-1);
        }
        if (member.getNganh().isEmpty() || member.getNganh().length() > 50) {
            JOptionPane.showMessageDialog(null,"Ngành không hợp lệ");
            return BigInteger.valueOf(-1);
        }
        if (member.getSdt().isEmpty() || member.getSdt().charAt(0) != '0' || member.getSdt().length() < 10) {
            JOptionPane.showMessageDialog(null,"Số điện thoại không hợp lệ");
            return BigInteger.valueOf(-1);
        }

        for (thanhvien member1 : members) {
            if (member1.getMaTV().equals(member.getMaTV())) {
                JOptionPane.showMessageDialog(null, "Mã thành viên đã tồn tại");
                return BigInteger.valueOf(-1);
            }
        }


        if (thanhvienDAL.getInstance().create(member)) {
            members.add(member);
            return member.getMaTV();
        }
        return BigInteger.valueOf(-1);
    }

    public BigInteger updateModel(thanhvien member) {

        // Nothing can be null: MaTV, HoTen, Khoa, Nganh, SDT:
        if ((String.valueOf(member.getMaTV()).isEmpty() || String.valueOf(member.getMaTV()).length() < 10)) {
            JOptionPane.showMessageDialog(null,"Mã thành viên chưa hợp lệ");
            return BigInteger.valueOf(-1);
        }

        if (member.getHoTen().isEmpty() || member.getHoTen().length() > 50) {
            JOptionPane.showMessageDialog(null,"Họ tên không hợp lệ");
            return BigInteger.valueOf(-1);
        }
        if (member.getKhoa().isEmpty() || member.getKhoa().length() > 50) {
            JOptionPane.showMessageDialog(null,"Khoa không hợp lệ");
            return BigInteger.valueOf(-1);
        }
        if (member.getNganh().isEmpty() || member.getNganh().length() > 50) {
            JOptionPane.showMessageDialog(null,"Ngành không hợp lệ");
            return BigInteger.valueOf(-1);
        }
        if (member.getSdt().isEmpty() || member.getSdt().charAt(0) != '0' || member.getSdt().length() < 10) {
            JOptionPane.showMessageDialog(null,"Số điện thoại không hợp lệ");
            return BigInteger.valueOf(-1);
        }

        for (thanhvien member1 : members) {
            if (member1.getSdt().equals(member.getSdt()) && !member1.getMaTV().equals(member.getMaTV())) {
                JOptionPane.showMessageDialog(null,"Số điện thoại đã tồn tại");
                return BigInteger.valueOf(-1);
            }
        }

        System.out.println("test bus: "+member);
        if (thanhvienDAL.getInstance().update(member)) {
            return member.getMaTV();
        }
        return BigInteger.valueOf(-1);
    }

    public boolean deleteModel(BigInteger id) {
        thanhvien member = getModelById(id);
        boolean result = thanhvienDAL.getInstance().delete(member.getMaTV());
        if (result) {
            members.remove(member);
        }
        return result;
    }

    public List<thanhvien> searchListThanhVien(String keyword) {
        return thanhvienDAL.getInstance().search(keyword);
    }

}
