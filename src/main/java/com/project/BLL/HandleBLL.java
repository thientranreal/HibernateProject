package com.project.BLL;

import com.project.DAL.HandleDAL;
import com.project.Entity.Device;
import com.project.Entity.Handle;

import java.util.List;

public class HandleBLL {
    private final HandleDAL HandleDAL;

    public HandleBLL() {
        HandleDAL = new HandleDAL();
    }


    // Insert into
    public void createHandle(Handle Handle) {
        // Validate the Handle object here
        // Apply business rules here
//        HandleDAL.createHandle(Handle);
    }

    // Update
    public void updateHandle(Handle Handle) {
        // Validate the Handle object here
        // Apply business rules here
//        HandleDAL.updateHandle(Handle);
    }

    // Delete
    public void deleteHandle(int id) {
        // Validate the id here
        // Apply business rules here
//        HandleDAL.deleteHandle(id);
    }

    // Read
    public Handle getHandleByID(int id) {
        // Validate the id here
        // Apply business rules here
        return HandleDAL.getHandle(id);
    }

    public List<Handle> getAllDevices() {
        return HandleDAL.listHandle();
    }
}
