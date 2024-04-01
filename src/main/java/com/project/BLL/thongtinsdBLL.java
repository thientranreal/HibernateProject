package com.project.BLL;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.project.DAL.thongtinsdDAL;
import com.project.models.thongtinsd;

public class thongtinsdBLL {
    private final List<thongtinsd> usageInformations = new ArrayList<>();
    private static thongtinsdBLL instance;

    private thongtinsdBLL() {
        usageInformations.addAll(thongtinsdDAL.getInstance().getUsageInformationList());
    }

    public static thongtinsdBLL getInstance() {
        if (instance == null) {
            instance = new thongtinsdBLL();
        }
        return instance;
    }

    public List<thongtinsd> getAllModels() {
        return usageInformations;
    }

    public void refresh() {
        usageInformations.clear();
        usageInformations.addAll(thongtinsdDAL.getInstance().getUsageInformationList());
    }

    public thongtinsd getModelById(int id) {
        return thongtinsdDAL.getInstance().getById(id);
    }

    public int addModel(thongtinsd usageInformation) {
        // MaTV can't be null:
        if (usageInformation.getThanhvien().compareTo(BigInteger.ZERO) <= 0) {
            return -1;
        }

        if (thongtinsdDAL.getInstance().create(usageInformation)) {
            usageInformations.add(usageInformation);
            return usageInformation.getMaTT();
        }
        return -1;
    }

    public int updateModel(thongtinsd usageInformation) {
        // MaTV can't be null:
        if (usageInformation.getThanhvien().compareTo(BigInteger.ZERO) <= 0) {
            return -1;
        }

        if (thongtinsdDAL.getInstance().update(usageInformation)) {
            return usageInformation.getMaTT();
        }
        return -1;
    }

    public boolean deleteModel(int id) {
        thongtinsd usageInformation = getModelById(id);
        boolean result = thongtinsdDAL.getInstance().delete(usageInformation.getMaTT());
        if (result) {
            usageInformations.remove(usageInformation);
        } else {
            throw new IllegalArgumentException("Cannot delete a non-existent usage information.");
        }
        return result;
    }

    public List<thongtinsd> searchThongTinSdByCriteria(String keyword, String criteria) {
        return usageInformations.stream()
                .filter(usageInformation -> {
                    if (criteria == null) {
                        return String.valueOf(usageInformation.getMaTT()).contains(keyword) ||
                                String.valueOf(usageInformation.getThanhvien()).contains(keyword) ||
                                String.valueOf(usageInformation.getThietbi()).contains(keyword);
                    } else {
                        switch (criteria) {
                            case "MaTT":
                                return String.valueOf(usageInformation.getMaTT()).contains(keyword);
                            case "MaThanhvien":
                                return String.valueOf(usageInformation.getThanhvien()).contains(keyword);
                            case "MaThietBi":
                                return String.valueOf(usageInformation.getThietbi()).contains(keyword);
                            default:
                                return false;
                        }
                    }
                })
                .collect(Collectors.toList());
    }
}