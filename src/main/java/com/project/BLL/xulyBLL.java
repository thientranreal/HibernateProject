package com.project.BLL;

import com.project.DAL.xulyDAL;
import com.project.models.xuly;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class xulyBLL {
    private final List<xuly> processings = new ArrayList<>();
    private static xulyBLL instance;

    private xulyBLL() {
        processings.addAll(xulyDAL.getInstance().getHandleList());
    }

    public static xulyBLL getInstance() {
        if (instance == null) {
            instance = new xulyBLL();
        }
        return instance;
    }

    public List<xuly> getAllModels() {
        return processings;
    }

    public void refresh() {
        processings.clear();
        processings.addAll(xulyDAL.getInstance().getHandleList());
    }

    public xuly getModelById(int id) {
        return xulyDAL.getInstance().getHandleById(id);
    }

    public int addModel(xuly processing) {
        // MaTV, HinhThucXL can't be null:
        if (processing.getMaTV() <= 0 || processing.getHinhThucXL().isEmpty()
                || processing.getHinhThucXL().length() > 200) {
            return -1;
        }

        if (xulyDAL.getInstance().create(processing)) {
            processings.add(processing);
            return processing.getMaXL();
        }
        return -1;
    }

    public boolean updateModel(xuly processing) {
        // MaTV, HinhThucXL can't be null:
        if (processing.getMaTV() <= 0 || processing.getHinhThucXL().isEmpty()
                || processing.getHinhThucXL().length() > 200) {
            return false;
        }
        boolean result = xulyDAL.getInstance().update(processing);
        if (result) {
            int index = processings.indexOf(processing);
            if (index != -1) {
                processings.set(index, processing);
            }
        }
        return result;
    }

    public boolean deleteModel(int id) {
        xuly processing = xulyDAL.getInstance().getHandleById(id);
        boolean result = xulyDAL.getInstance().delete(id);
        if (result) {
            processings.remove(processing);
        } else {
            throw new RuntimeException("Can't delete this processing");
        }
        return result;
    }

    private List<xuly> searchByCondition(Predicate<xuly> condition) {
        List<xuly> result = new ArrayList<>();
        for (xuly processing : processings) {
            if (condition.test(processing)) {
                result.add(processing);
            }
        }
        return result;
    }

    public List<xuly> searchByMaTV(int id) {
        return searchByCondition(processing -> processing.getMaTV() == id);
    }

    public List<xuly> searchByHinhThucXL(String keyword) {
        return searchByCondition(processing -> processing.getHinhThucXL().contains(keyword));
    }

    public List<xuly> searchByTrangThaiXL(int keyword) {
        return searchByCondition(processing -> processing.getTrangThaiXL() == keyword);
    }

}
