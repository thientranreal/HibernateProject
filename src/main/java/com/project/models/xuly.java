package com.project.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import java.sql.Date;

@Entity
@Table(name = "XuLy")
public class xuly {

    @Id
    @Column(name = "MaXL")
    private int MaXL;

    @ManyToOne
    @JoinColumn(name = "MaTV")
    private thanhvien MaTV;

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

    public xuly(int MaXL, thanhvien MaTV, String HinhThucXL, int SoTien, Date NgayXL, int TrangThaiXL) {
        this.MaXL = MaXL;
        this.MaTV = MaTV;
        this.HinhThucXL = HinhThucXL;
        this.SoTien = SoTien;
        this.NgayXL = NgayXL;
        this.TrangThaiXL = TrangThaiXL;
    }

    public int getMaXL() {
        return this.MaXL;
    }

    public void setMaXL(int MaXL) {
        this.MaXL = MaXL;
    }

    public thanhvien getMaTV() {
        return this.MaTV;
    }

    public void setMaTV(thanhvien MaTV) {
        this.MaTV = MaTV;
    }

    public String getHinhThucXL() {
        return this.HinhThucXL;
    }

    public void setHinhThucXL(String HinhThucXL) {
        this.HinhThucXL = HinhThucXL;
    }

    public int getSoTien() {
        return this.SoTien;
    }

    public void setSoTien(int SoTien) {
        this.SoTien = SoTien;
    }

    public Date getNgayXL() {
        return this.NgayXL;
    }

    public void setNgayXL(Date NgayXL) {
        this.NgayXL = NgayXL;
    }

    public int getTrangThaiXL() {
        return this.TrangThaiXL;
    }

    public void setTrangThaiXL(int TrangThaiXL) {
        this.TrangThaiXL = TrangThaiXL;
    }

    public xuly MaXL(int MaXL) {
        setMaXL(MaXL);
        return this;
    }

    public xuly MaTV(thanhvien MaTV) {
        setMaTV(MaTV);
        return this;
    }

    public xuly HinhThucXL(String HinhThucXL) {
        setHinhThucXL(HinhThucXL);
        return this;
    }

    public xuly SoTien(int SoTien) {
        setSoTien(SoTien);
        return this;
    }

    public xuly NgayXL(Date NgayXL) {
        setNgayXL(NgayXL);
        return this;
    }

    public xuly TrangThaiXL(int TrangThaiXL) {
        setTrangThaiXL(TrangThaiXL);
        return this;
    }
}