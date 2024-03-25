package com.project.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "XuLy")
@Getter
@Setter
public class Handle {
    @Id
    @Column(name = "MaXL")
    private int handleID;
    @Column(name = "MaTV")

    private int memberID;
    @Column(name = "HinhThucXL")

    private String actionHandle;
    @Column(name = "SoTien")

    private int total;
    @Column(name = "NgayXL")

    private Timestamp handleDate;
    @Column(name = "TrangThaiXL")

    private int handleStatus;
}
