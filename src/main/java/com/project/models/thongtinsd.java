package com.project.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import java.sql.Date;

@Entity
@Table(name = "thongtinsd")
public class thongtinsd {
    @Id
    @Column(name = "MaTT")
    private int MaTT;

    @ManyToOne
    @JoinColumn(name = "MaTV")
    private thanhvien MaTV;

    @ManyToOne
    @JoinColumn(name = "MaTB")
    private thietbi MaTB;

    @Column(name = "TGVao")
    private Date TGVao;

    @Column(name = "TGMuon")
    private Date TGMuon;

    @Column(name = "TGTra")
    private Date TGTra;

    public thongtinsd() {
    }

    public thongtinsd(int MaTT, thanhvien MaTV, thietbi MaTB, Date TGVao, Date TGMuon, Date TGTra) {
        this.MaTT = MaTT;
        this.MaTV = MaTV;
        this.MaTB = MaTB;
        this.TGVao = TGVao;
        this.TGMuon = TGMuon;
        this.TGTra = TGTra;
    }

    public int getMaTT() {
        return this.MaTT;
    }

    public void setMaTT(int MaTT) {
        this.MaTT = MaTT;
    }

    public thanhvien getMaTV() {
        return this.MaTV;
    }

    public void setMaTV(thanhvien MaTV) {
        this.MaTV = MaTV;
    }

    public thietbi getMaTB() {
        return this.MaTB;
    }

    public void setMaTB(thietbi MaTB) {
        this.MaTB = MaTB;
    }

    public Date getTGVao() {
        return this.TGVao;
    }

    public void setTGVao(Date TGVao) {
        this.TGVao = TGVao;
    }

    public Date getTGMuon() {
        return this.TGMuon;
    }

    public void setTGMuon(Date TGMuon) {
        this.TGMuon = TGMuon;
    }

    public Date getTGTra() {
        return this.TGTra;
    }

    public void setTGTra(Date TGTra) {
        this.TGTra = TGTra;
    }

    public thongtinsd MaTT(int MaTT) {
        setMaTT(MaTT);
        return this;
    }

    public thongtinsd MaTV(thanhvien MaTV) {
        setMaTV(MaTV);
        return this;
    }

    public thongtinsd MaTB(thietbi MaTB) {
        setMaTB(MaTB);
        return this;
    }

    public thongtinsd TGVao(Date TGVao) {
        setTGVao(TGVao);
        return this;
    }

    public thongtinsd TGMuon(Date TGMuon) {
        setTGMuon(TGMuon);
        return this;
    }

    public thongtinsd TGTra(Date TGTra) {
        setTGTra(TGTra);
        return this;
    }

}