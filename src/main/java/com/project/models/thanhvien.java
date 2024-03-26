package com.project.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "thanhvien")
public class thanhvien {
    @Id
    @Column(name = "MaTV")
    private int maTV;

    @Column(name = "HoTen")
    private String hoTen;

    @Column(name = "Khoa")
    private String khoa;

    @Column(name = "Nganh")
    private String nganh;

    @Column(name = "SDT")
    private int sdt;

    public thanhvien() {
    }

    public thanhvien(int maTV, String hoTen, String khoa, String nganh, int sdt) {
        this.maTV = maTV;
        this.hoTen = hoTen;
        this.khoa = khoa;
        this.nganh = nganh;
        this.sdt = sdt;
    }

    public int getMaTV() {
        return this.maTV;
    }

    public void setMaTV(int maTV) {
        this.maTV = maTV;
    }

    public String getHoTen() {
        return this.hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getKhoa() {
        return this.khoa;
    }

    public void setKhoa(String khoa) {
        this.khoa = khoa;
    }

    public String getNganh() {
        return this.nganh;
    }

    public void setNganh(String nganh) {
        this.nganh = nganh;
    }

    public int getSdt() {
        return this.sdt;
    }

    public void setSdt(int sdt) {
        this.sdt = sdt;
    }

    public thanhvien maTV(int maTV) {
        setMaTV(maTV);
        return this;
    }

    public thanhvien hoTen(String hoTen) {
        setHoTen(hoTen);
        return this;
    }

    public thanhvien khoa(String khoa) {
        setKhoa(khoa);
        return this;
    }

    public thanhvien nganh(String nganh) {
        setNganh(nganh);
        return this;
    }

    public thanhvien sdt(int sdt) {
        setSdt(sdt);
        return this;
    }

}
