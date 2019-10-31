package com.carpooling.bot.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "cp_travel_rider", schema = "carpooling_db", catalog = "")
public class CpTravelRiderEntity {
    private long travelRiderId;
    private BigDecimal experience;
    private int status;
    private String txUser;
    private String txHost;
    private Date txDate;

    @Id
    @Column(name = "travel_rider_id")
    public long getTravelRiderId() {
        return travelRiderId;
    }

    public void setTravelRiderId(long travelRiderId) {
        this.travelRiderId = travelRiderId;
    }

    @Basic
    @Column(name = "experience")
    public BigDecimal getExperience() {
        return experience;
    }

    public void setExperience(BigDecimal experience) {
        this.experience = experience;
    }

    @Basic
    @Column(name = "status")
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Basic
    @Column(name = "tx_user")
    public String getTxUser() {
        return txUser;
    }

    public void setTxUser(String txUser) {
        this.txUser = txUser;
    }

    @Basic
    @Column(name = "tx_host")
    public String getTxHost() {
        return txHost;
    }

    public void setTxHost(String txHost) {
        this.txHost = txHost;
    }

    @Basic
    @Column(name = "tx_date")
    public Date getTxDate() {
        return txDate;
    }

    public void setTxDate(Date txDate) {
        this.txDate = txDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CpTravelRiderEntity that = (CpTravelRiderEntity) o;
        return travelRiderId == that.travelRiderId &&
                status == that.status &&
                Objects.equals(experience, that.experience) &&
                Objects.equals(txUser, that.txUser) &&
                Objects.equals(txHost, that.txHost) &&
                Objects.equals(txDate, that.txDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(travelRiderId, experience, status, txUser, txHost, txDate);
    }
}
