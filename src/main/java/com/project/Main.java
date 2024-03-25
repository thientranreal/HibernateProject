package com.project;

import com.project.BLL.DeviceBLL;
import com.project.Entity.Device;

public class Main {
    public static void main(String[] args) {
        for (Device device : new DeviceBLL().getAllDevices()) {
            System.out.println(device);
        }
    }
}
