package com.project.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "department")
public class Example {
    @Id
    @Column(name = "DepartmentID")
    private int departmentID;
    @Column(name = "Name")
    private String name;
    @Column(name = "Budget")
    private double budget;
    @Column(name = "StartDate")
    private String startDate;
    @Column(name = "Administrator")
    private int administrator;

    public int getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public int getAdministrator() {
        return administrator;
    }

    public void setAdministrator(int administrator) {
        this.administrator = administrator;
    }

    @Override
    public String toString() {
        return "Example{" +
                "departmentID=" + departmentID +
                ", name='" + name + '\'' +
                ", budget=" + budget +
                ", startDate='" + startDate + '\'' +
                ", administrator=" + administrator +
                '}';
    }
}
