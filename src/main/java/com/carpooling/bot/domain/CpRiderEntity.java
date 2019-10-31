package com.carpooling.bot.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "cp_rider", schema = "carpooling_db", catalog = "")
public class CpRiderEntity {
    private long riderId;
    private String firstName;
    private String lastName;
    private BigDecimal reputation;
    private int status;
    private String txUser;
    private String txHost;
    private Date txDate;
    private List<CpTravelRiderEntity> cpTravelRidersByRiderId;

    @Id
    @Column(name = "rider_id")
    public long getRiderId() {
        return riderId;
    }

    public void setRiderId(long riderId) {
        this.riderId = riderId;
    }

    @Basic
    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "reputation")
    public BigDecimal getReputation() {
        return reputation;
    }

    public void setReputation(BigDecimal reputation) {
        this.reputation = reputation;
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
        CpRiderEntity that = (CpRiderEntity) o;
        return riderId == that.riderId &&
                status == that.status &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(reputation, that.reputation) &&
                Objects.equals(txUser, that.txUser) &&
                Objects.equals(txHost, that.txHost) &&
                Objects.equals(txDate, that.txDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(riderId, firstName, lastName, reputation, status, txUser, txHost, txDate);
    }

    @OneToMany(mappedBy = "cpRiderByRiderId")
    public List<CpTravelRiderEntity> getCpTravelRidersByRiderId() {
        return cpTravelRidersByRiderId;
    }

    public void setCpTravelRidersByRiderId(List<CpTravelRiderEntity> cpTravelRidersByRiderId) {
        this.cpTravelRidersByRiderId = cpTravelRidersByRiderId;
    }
}
