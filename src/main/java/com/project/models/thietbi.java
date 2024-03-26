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

    public thietbi(int MaTB, String TenTB, String MoTaTB) {
        this.MaTB = MaTB;
        this.TenTB = TenTB;
        this.MoTaTB = MoTaTB;
    }
}