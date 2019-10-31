package com.carpooling.bot.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "cp_carpooler", schema = "carpooling_db", catalog = "")
public class CpCarpoolerEntity {
    private long carpoolerId;
    private String firstName;
    private String lastName;
    private String ciNumber;
    private String cellphoneNumber;
    private BigDecimal reputation;
    private int status;
    private String txUser;
    private String txHost;
    private Date txDate;
    private Collection<CpCarEntity> cpCarsByCarpoolerId;
    private Collection<CpTravelEntity> cpTravelsByCarpoolerId;

    @Id
    @Column(name = "carpooler_id")
    public long getCarpoolerId() {
        return carpoolerId;
    }

    public void setCarpoolerId(long carpoolerId) {
        this.carpoolerId = carpoolerId;
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
    @Column(name = "ci_number")
    public String getCiNumber() {
        return ciNumber;
    }

    public void setCiNumber(String ciNumber) {
        this.ciNumber = ciNumber;
    }

    @Basic
    @Column(name = "cellphone_number")
    public String getCellphoneNumber() {
        return cellphoneNumber;
    }

    public void setCellphoneNumber(String cellphoneNumber) {
        this.cellphoneNumber = cellphoneNumber;
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
        CpCarpoolerEntity that = (CpCarpoolerEntity) o;
        return carpoolerId == that.carpoolerId &&
                status == that.status &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(ciNumber, that.ciNumber) &&
                Objects.equals(cellphoneNumber, that.cellphoneNumber) &&
                Objects.equals(reputation, that.reputation) &&
                Objects.equals(txUser, that.txUser) &&
                Objects.equals(txHost, that.txHost) &&
                Objects.equals(txDate, that.txDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carpoolerId, firstName, lastName, ciNumber, cellphoneNumber, reputation, status, txUser, txHost, txDate);
    }

    @OneToMany(mappedBy = "cpCarpoolerByCarpoolerId")
    public Collection<CpCarEntity> getCpCarsByCarpoolerId() {
        return cpCarsByCarpoolerId;
    }

    public void setCpCarsByCarpoolerId(Collection<CpCarEntity> cpCarsByCarpoolerId) {
        this.cpCarsByCarpoolerId = cpCarsByCarpoolerId;
    }

    @OneToMany(mappedBy = "cpCarpoolerByCarpoolerId")
    public Collection<CpTravelEntity> getCpTravelsByCarpoolerId() {
        return cpTravelsByCarpoolerId;
    }

    public void setCpTravelsByCarpoolerId(Collection<CpTravelEntity> cpTravelsByCarpoolerId) {
        this.cpTravelsByCarpoolerId = cpTravelsByCarpoolerId;
    }
}
