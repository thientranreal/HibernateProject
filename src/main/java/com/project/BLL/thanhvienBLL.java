package com.project.BLL;

import com.project.DAL.thanhvienDAL;
import com.project.models.thanhvien;

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

    public thanhvien getModelById(int id) {
        return thanhvienDAL.getInstance().getById(id);
    }

    public int addModel(thanhvien member) {
        // Nothing can be null: MaTV, HoTen, Khoa, Nganh, SDT:
        if (member.getHoTen().isEmpty() || member.getHoTen().length() > 50 || member.getHoTen().trim() == null) {
            return -1;
        }
        if (member.getKhoa().isEmpty() || member.getKhoa().length() > 50 || member.getKhoa().trim() == null) {
            return -1;
        }
        if (member.getNganh().isEmpty() || member.getNganh().length() > 50 || member.getNganh().trim() == null) {
            return -1;
        }
        if (member.getSdt() <= 0) {
            return -1;
        }

        if (thanhvienDAL.getInstance().create(member)) {
            members.add(member);
            return member.getMaTV();
        }
        return -1;
    }

    public int updateModel(thanhvien member) {
        // Nothing can be null: MaTV, HoTen, Khoa, Nganh, SDT:
        if (member.getHoTen().isEmpty() || member.getHoTen().length() > 50 || member.getHoTen().trim() == null) {
            return -1;
        }
        if (member.getKhoa().isEmpty() || member.getKhoa().length() > 50 || member.getKhoa().trim() == null) {
            return -1;
        }
        if (member.getNganh().isEmpty() || member.getNganh().length() > 50 || member.getNganh().trim() == null) {
            return -1;
        }
        if (member.getSdt() <= 0) {
            return -1;
        }

        if (thanhvienDAL.getInstance().update(member)) {
            return member.getMaTV();
        }
        return -1;
    }

    public boolean deleteModel(int id) {
        thanhvien member = getModelById(id);
        boolean result = thanhvienDAL.getInstance().delete(member.getMaTV());
        if (result) {
            members.remove(member);
        }
        return result;
    }

    public List<thanhvien> searchModelByCriteria(Predicate<thanhvien> criteria) {
        List<thanhvien> result = new ArrayList<>();
        for (thanhvien member : members) {
            if (criteria.test(member)) {
                result.add(member);
            }
        }
        return result;
    }

    public List<thanhvien> searchModelByHoTen(String hoTen) {
        return searchModelByCriteria(member -> member.getHoTen().toLowerCase().contains(hoTen.toLowerCase()));
    }

    public List<thanhvien> searchModelByKhoa(String khoa) {
        return searchModelByCriteria(member -> member.getKhoa().toLowerCase().contains(khoa.toLowerCase()));
    }

    public List<thanhvien> searchModelByNganh(String nganh) {
        return searchModelByCriteria(member -> member.getNganh().toLowerCase().contains(nganh.toLowerCase()));
    }

    public List<thanhvien> searchModelBySDT(int sdt) {
        return searchModelByCriteria(member -> member.getSdt() == sdt);
    }

}
