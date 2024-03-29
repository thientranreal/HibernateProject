package com.project;

import com.project.BLL.thanhvienBLL;
import com.project.BLL.thietbiBLL;
import com.project.BLL.thongtinsdBLL;
import com.project.BLL.xulyBLL;
import com.project.models.thanhvien;
import com.project.models.thietbi;
import com.project.models.thongtinsd;
import com.project.models.xuly;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Main {
    public static void main(String[] args) {
        thietbi device = new thietbi("lalala","rat la xin xo");
        int result = thietbiBLL.getInstance().addModel(device);

        if(result > 0) {
            System.out.println("OK");
        }else {
            System.out.println("KO OK");
        }

//        String dateString = "2023-09-12 08:00:00";
//
//        Date dateSql = null;
//        try {
//            // Định dạng của chuỗi ngày tháng
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//            // Phân tích chuỗi thành đối tượng Date
//            java.util.Date dateUtil = sdf.parse(dateString);
//
//            // Chuyển đổi thành java.sql.Date
//            dateSql = new Date(dateUtil.getTime());
//
//            // In ra để kiểm tra
//            System.out.println("Date in java.sql.Date format: " + dateSql);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        xuly device = new xuly(1121530087,"Dep trai",100000,dateSql,0);
//        int result = xulyBLL.getInstance().addModel(device);
//
//        if(result > 0) {
//            System.out.println("OK");
//        }else {
//            System.out.println("KO OK");
//        }


    }
}
