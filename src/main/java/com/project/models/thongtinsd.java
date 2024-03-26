package com.project.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "thongtinsd")
@Getter
@Setter
public class thongtinsd {
    @Id
    @Column(name = "MaTT")
    private int MaTT;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaTV", foreignKey = @ForeignKey(name = "thongtinsd_thanhvien"))
    private thanhvien thanhvien;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaTB", foreignKey = @ForeignKey(name = "thongtinsd_thietbi"))
    private thietbi thietbi;

    @Column(name = "TGVao")
    private Date TGVao;

    @Column(name = "TGMuon")
    private Date TGMuon;

    @Column(name = "TGTra")
    private Date TGTra;

    public thongtinsd() {
    }

    public thongtinsd(int maTT, thanhvien thanhvien, thietbi thietbi, Date TGVao, Date TGMuon, Date TGTra) {
        MaTT = maTT;
        this.thanhvien = thanhvien;
        this.thietbi = thietbi;
        this.TGVao = TGVao;
        this.TGMuon = TGMuon;
        this.TGTra = TGTra;
    }
}