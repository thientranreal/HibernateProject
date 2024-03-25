package com.project.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "thanhvien")
@Getter
@Setter
public class Member {
    @Id
    @Column (name = "MaTV")
    private int maTV;
    @Column (name = "HoTen")
    private String hoTen;
    @Column (name = "Khoa")
    private String khoa;
    @Column (name = "Nganh")
    private String nganh;
    @Column (name = "SDT")
    private int sdt;
}
