package com.project.BLL;

import com.project.DAL.UsageInformationDAL;
import com.project.Entity.Device;
import com.project.Entity.Handle;
import com.project.Entity.UsageInformation;

import java.util.List;

public class UsageInformationBLL {
    private final UsageInformationDAL UsageInformationDAL;

    public UsageInformationBLL() {
        UsageInformationDAL = new UsageInformationDAL();
    }


    // Insert into
    public void createUsageInformation(UsageInformation info) {
        // Validate the Handle object here
        // Apply business rules here
//        UsageInformationDAL.createUsageInformation(info);
    }

    // Update
    public void updateUsageInformation(UsageInformation info) {
        // Validate the Handle object here
        // Apply business rules here
//        UsageInformationDAL.updateUsageInformation(info);
    }

    // Delete
    public void deleteUsageInformation(int id) {
        // Validate the id here
        // Apply business rules here
//        UsageInformationDAL.deleteUsageInformation(id);
    }

    // Read
    public UsageInformation getUsageInformation(int id) {
        // Validate the id here
        // Apply business rules here
        return UsageInformationDAL.getUsageInformation(id);
    }

    public List<UsageInformation> getAllUsageInformation() {
       return UsageInformationDAL.listUsageInformation();
    }
}
