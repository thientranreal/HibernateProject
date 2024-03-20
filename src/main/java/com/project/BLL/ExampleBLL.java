package com.project.BLL;
import com.project.DAL.ExampleDAL;
import com.project.Entity.Example;
public class ExampleBLL {
    private final ExampleDAL exampleDAL;

    public ExampleBLL() {
        exampleDAL = new ExampleDAL();
    }

    // Insert into
    public void createExample(Example example) {
        // Validate the example object here
        // Apply business rules here
        exampleDAL.createDepartment(example);
    }

    // Update
    public void updateExample(Example example) {
        // Validate the example object here
        // Apply business rules here
        exampleDAL.updateDepartment(example);
    }

    // Delete
    public void deleteExample(int id) {
        // Validate the id here
        // Apply business rules here
        exampleDAL.deleteDepartment(id);
    }

    // Read
    public Example getExample(int id) {
        // Validate the id here
        // Apply business rules here
        return exampleDAL.getDepartment(id);
    }
}
