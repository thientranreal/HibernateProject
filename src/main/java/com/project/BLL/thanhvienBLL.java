package com.project.BLL;

import com.project.DAL.thanhvienDAL;
import com.project.models.thanhvien;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

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
            throw new IllegalArgumentException("Mã thành viên chưa hợp lệ");
        }

        if (member.getHoTen().isEmpty() || member.getHoTen().length() > 50) {
            throw new IllegalArgumentException("Họ tên không hợp lệ");
        }
        if (member.getKhoa().isEmpty() || member.getKhoa().length() > 50) {
            throw new IllegalArgumentException("Khoa không hợp lệ");
        }
        if (member.getNganh().isEmpty() || member.getNganh().length() > 50) {
            throw new IllegalArgumentException("Ngành không hợp lệ");
        }
        if (member.getSdt().isEmpty() || member.getSdt().charAt(0) != '0' || member.getSdt().length() < 10) {
            throw new IllegalArgumentException("Số điện thoại không hợp lệ");
        }

        for(thanhvien curMem : members) {
            if(curMem.getSdt().equals(member.getSdt()) && !curMem.getMaTV().equals(member.getMaTV())) {
                throw new IllegalArgumentException("Số điện thoại đã tồn tại");
            }
        }

        for(thanhvien curMem1 : members) {
            if(curMem1.getMaTV().equals(member.getMaTV())) {
                throw new IllegalArgumentException("Mã thành viên đã tồn tại");
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
            throw new IllegalArgumentException("Mã thành viên chưa hợp lệ");
        }
        if (member.getHoTen().isEmpty() || member.getHoTen().length() > 50) {
            throw new IllegalArgumentException("Họ tên không hợp lệ");
        }
        if (member.getKhoa().isEmpty() || member.getKhoa().length() > 50) {
            throw new IllegalArgumentException("Khoa không hợp lệ");
        }
        if (member.getNganh().isEmpty() || member.getNganh().length() > 50) {
            throw new IllegalArgumentException("Ngành không hợp lệ");
        }
        if (member.getSdt().isEmpty() || member.getSdt().charAt(0) != '0' || member.getSdt().length() < 10) {
            throw new IllegalArgumentException("Số điện thoại không hợp lệ");
        }

        for(thanhvien curMem : members) {
            if(curMem.getSdt().equals(member.getSdt()) && !curMem.getMaTV().equals(member.getMaTV())) {
                throw new IllegalArgumentException("Số điện thoại đã tồn tại");
            }else if(curMem.getEmail().equals(member.getEmail()) && !curMem.getMaTV().equals(member.getMaTV())) {
                throw new IllegalArgumentException("Email đã tồn tại");
            }
        }

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
    public List<thanhvien> searchListThanhVienByYear(String keyword) {
        return thanhvienDAL.getInstance().searchByYear(keyword);
    }
    public List<thanhvien> searchListThanhVienByKhoa(String keyword) {
        return thanhvienDAL.getInstance().searchByKhoa(keyword);
    }
    public List<thanhvien> searchListThanhVienByNganh(String keyword) {
        return thanhvienDAL.getInstance().searchByNganh(keyword);
    }
    public List<thanhvien> searchListThanhVienByKhoaAndNganh(String keywordNganh,String keywordKhoa) {
        return thanhvienDAL.getInstance().searchByNganhAndKhoa(keywordNganh,keywordKhoa);
    }
}
