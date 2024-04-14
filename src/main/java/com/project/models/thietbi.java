package com.project.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "thietbi")
@Getter
@Setter
public class thietbi {
    @Id
    @Column(name = "MaTB")
    private int MaTB;

    @Column(name = "TenTB")
    private String TenTB;

    @Column(name = "MoTaTB")
    private String MoTaTB;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "thietbi")
    private List<thongtinsd> thongtinsd;

    public thietbi() {
    }

    public thietbi(int maTB, String TenTB, String MoTaTB) {
        this.MaTB = maTB;
        this.TenTB = TenTB;
        this.MoTaTB = MoTaTB;
    }

    public thietbi(String TenTB, String MoTaTB) {
        this.TenTB = TenTB;
        this.MoTaTB = MoTaTB;
    }

    public int getMaTB() {
        return MaTB;
    }

    public void setMaTB(int maTB) {
        MaTB = maTB;
    }

    public String getTenTB() {
        return TenTB;
    }

    public void setTenTB(String tenTB) {
        TenTB = tenTB;
    }

    public String getMoTaTB() {
        return MoTaTB;
    }

    public void setMoTaTB(String moTaTB) {
        MoTaTB = moTaTB;
    }

    @Override
    public String toString() {
        return "thietbi{" +
                "MaTB=" + MaTB +
                ", TenTB='" + TenTB + '\'' +
                ", MoTaTB='" + MoTaTB + '\'' +
                '}';
    }
}