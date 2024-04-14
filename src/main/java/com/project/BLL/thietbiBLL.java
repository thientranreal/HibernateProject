package com.project.BLL;

import com.project.DAL.thietbiDAL;
import com.project.models.thietbi;

import java.util.ArrayList;
import java.util.List;

public class thietbiBLL {
    private final List<thietbi> devices = new ArrayList<>();
    private static thietbiBLL instance;

    private thietbiBLL() {
        devices.addAll(thietbiDAL.getInstance().getDeviceList());
    }

    public static thietbiBLL getInstance() {
        if (instance == null) {
            instance = new thietbiBLL();
        }
        return instance;
    }

    public List<thietbi> getAllModels() {
        return devices;
    }

    public void refresh() {
        devices.clear();
        devices.addAll(thietbiDAL.getInstance().getDeviceList());
    }

    public thietbi getModelById(int id) {
        return thietbiDAL.getInstance().getById(id);
    }

    public List<thietbi> getModelsByType(String type) {
        List<thietbi> result = new ArrayList<>();
        for (thietbi thietbi : devices) {
            if (thietbi.getTenTB().equals(type)) {
                result.add(thietbi);
            }
        }
        return result;
    }

    public int addModel(thietbi device) {
        if (device.getTenTB().isEmpty() || device.getTenTB().length() > 50) {
            return -1;
        }
        if (device.getMoTaTB().isEmpty() || device.getMoTaTB().trim() == null) {
            return -1;
        }

        if (thietbiDAL.getInstance().create(device)) {
            devices.add(device);
            return device.getMaTB();
        }
        return -1;
    }

    public boolean updateModel(thietbi device) {
        if (device.getTenTB().isEmpty() || device.getTenTB().length() > 50) {
            return false;
        }
        if (device.getMoTaTB().isEmpty() || device.getMoTaTB().trim() == null) {
            return false;
        }

        boolean result = thietbiDAL.getInstance().update(device);
        if (result) {
            int index = devices.indexOf(device);
            if (index != -1) {
                devices.set(index, device);
            }
        }
        return result;
    }

    public boolean deleteModel(int id) {
        thietbi device = getModelById(id);
        boolean result = thietbiDAL.getInstance().delete(id);
        if (result) {
            devices.remove(device);
        } else {
            throw new IllegalArgumentException("Device not found");
        }
        return result;
    }

    public List<thietbi> searchListThietBi(String keyword) {
        return thietbiDAL.getInstance().search(keyword);
    }
}
