package com.project.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.math.BigInteger;
import java.sql.Timestamp;

@Entity
@Table(name = "thongtinsd")
@Getter
@Setter
public class thongtinsd {
    @Id
    @Column(name = "MaTT")
    private int MaTT;

    @Column(name = "MaTV")
    private BigInteger thanhvien;

    @Column(name = "MaTB")
    private Integer thietbi;

    @Column(name = "TGVao")
    private Timestamp TGVao;

    @Column(name = "TGMuon")
    private Timestamp TGMuon;

    @Column(name = "TGTra")
    private Timestamp TGTra;

    public thongtinsd(){}

    public thongtinsd(BigInteger thanhvien, Integer thietbi, Timestamp TGVao, Timestamp TGMuon, Timestamp TGTra) {
        this.thanhvien = thanhvien;
        this.thietbi = thietbi;
        this.TGVao = TGVao;
        this.TGMuon = TGMuon;
        this.TGTra = TGTra;
    }


    public thongtinsd(int MaTT, BigInteger thanhvien, Integer thietbi, Timestamp TGVao, Timestamp TGMuon, Timestamp TGTra) {
        this.MaTT = MaTT;
        this.thanhvien = thanhvien;
        this.thietbi = thietbi;
        this.TGVao = TGVao;
        this.TGMuon = TGMuon;
        this.TGTra = TGTra;
    }

    public int getMaTT() {
        return MaTT;
    }

    public void setMaTT(int maTT) {
        MaTT = maTT;
    }

    public BigInteger getThanhvien() {
        return thanhvien;
    }

    public void setThanhvien(BigInteger thanhvien) {
        this.thanhvien = thanhvien;
    }

    public Integer getThietbi() {
        return thietbi;
    }

    public void setThietbi(Integer thietbi) {
        this.thietbi = thietbi;
    }

    public Timestamp getTGVao() {
        return TGVao;
    }

    public void setTGVao(Timestamp TGVao) {
        this.TGVao = TGVao;
    }

    public Timestamp getTGMuon() {
        return TGMuon;
    }

    public void setTGMuon(Timestamp TGMuon) {
        this.TGMuon = TGMuon;
    }

    public Timestamp getTGTra() {
        return TGTra;
    }

    public void setTGTra(Timestamp TGTra) {
        this.TGTra = TGTra;
    }

    @Override
    public String toString() {
        return "thongtinsd{" +
                "MaTT=" + MaTT +
                ", thanhvien=" + thanhvien +
                ", thietbi=" + thietbi +
                ", TGVao=" + TGVao +
                ", TGMuon=" + TGMuon +
                ", TGTra=" + TGTra +
                '}';
    }

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "MaTB", insertable = false, updatable = false,foreignKey = @ForeignKey(name = "fk_thongtinsd_device"))
    private thietbi device;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaTV", referencedColumnName = "MaTV", insertable = false, updatable = false,foreignKey = @ForeignKey(name = "fk_thanhvien_thongtinsd"))
    private thanhvien member;
}