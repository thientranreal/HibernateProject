package com.project.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "thanhvien")
@Getter
@Setter
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "thanhvien")
    private List<thongtinsd> thongtinsd;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "thanhvien")
    private List<xuly> xuly;

    public thanhvien() {
    }

    public thanhvien(int maTV, String hoTen, String khoa, String nganh, int sdt) {
        this.maTV = maTV;
        this.hoTen = hoTen;
        this.khoa = khoa;
        this.nganh = nganh;
        this.sdt = sdt;
    }
}
