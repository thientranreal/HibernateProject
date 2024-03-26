package com.project.BLL;

import java.util.ArrayList;
import java.util.List;

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
        //MaTV can't be null:
        if (usageInformation.getMaTV() == null) {
            return -1;
        }
        
        if (thongtinsdDAL.getInstance().create(usageInformation)) {
            usageInformations.add(usageInformation);
            return usageInformation.getMaTT();
        }
        return -1;
    }

    public int updateModel(thongtinsd usageInformation) {
        //MaTV can't be null:
        if (usageInformation.getMaTV() == null) {
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

    // public List<thongtinsd> searchModels(String keyword) {
    // }
}