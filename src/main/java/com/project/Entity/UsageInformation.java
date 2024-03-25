package com.project.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name="thongtinsd")
public class UsageInformation {
    @Id
    @Column(name = "MaTT")
    private int id;

    @Column(name="MaTV")
    private int memberID;
    @Column(name="MaTB")

    private int deviceID;
    @Column(name="TGVao")

    private Timestamp enterTime;
    @Column(name="TGMuon")

    private Timestamp borrowTime;
    @Column(name="TGTra")

    private Timestamp paybackTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMemberID() {
        return memberID;
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }

    public int getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(int deviceID) {
        this.deviceID = deviceID;
    }

    public Timestamp getEnterTime() {
        return enterTime;
    }

    public void setEnterTime(Timestamp enterTime) {
        this.enterTime = enterTime;
    }

    public Timestamp getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(Timestamp borrowTime) {
        this.borrowTime = borrowTime;
    }

    public Timestamp getPaybackTime() {
        return paybackTime;
    }

    public void setPaybackTime(Timestamp paybackTime) {
        this.paybackTime = paybackTime;
    }

    @Override
    public String toString() {
        return "usageInfomation{" +
                "id=" + id +
                ", memberID=" + memberID +
                ", deviceID=" + deviceID +
                ", enterTime=" + enterTime +
                ", borrowTime=" + borrowTime +
                ", paybackTime=" + paybackTime +
                '}';
    }
}
