package com.project.BLL;

import com.project.DAL.DeviceDAL;
import com.project.Entity.Device;

import java.util.List;

public class DeviceBLL {
    private final DeviceDAL DeviceDAL;

    public DeviceBLL() {
        DeviceDAL = new DeviceDAL();
    }
    

    // Insert into
    public void createDevice(Device device) {
        // Validate the Device object here
        // Apply business rules here
//        DeviceDAL.createDevice(device);
    }

    // Update
    public void updateDevice(Device device) {
        // Validate the Device object here
        // Apply business rules here
//        DeviceDAL.updateDevice(device);
    }

    // Delete
    public void deleteDevice(int id) {
        // Validate the id here
        // Apply business rules here
//        DeviceDAL.deleteDevice(id);
    }

    // Read
    public Device getDeviceByID(int id) {
        // Validate the id here
        // Apply business rules here
        return DeviceDAL.getDevice(id);
    }

    public List<Device> getAllDevices() {
        return DeviceDAL.listDevice();
    }
}
