package com.project.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "xuly")
@Getter
@Setter
public class xuly {
    @Id
    @Column(name = "MaXL")
    private int MaXL;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaTV", foreignKey = @ForeignKey(name = "xuly_thanhvien"))
    private thanhvien thanhvien;

    @Column(name = "HinhThucXL")
    private String HinhThucXL;

    @Column(name = "SoTien")
    private int SoTien;

    @Column(name = "NgayXL")
    private Date NgayXL;

    @Column(name = "TrangThaiXL")
    private int TrangThaiXL;

    public xuly() {
    }

    public xuly(int MaXL, thanhvien thanhvien, String HinhThucXL, int SoTien, Date NgayXL, int TrangThaiXL) {
        this.MaXL = MaXL;
        this.thanhvien = thanhvien;
        this.HinhThucXL = HinhThucXL;
        this.SoTien = SoTien;
        this.NgayXL = NgayXL;
        this.TrangThaiXL = TrangThaiXL;
    }
}