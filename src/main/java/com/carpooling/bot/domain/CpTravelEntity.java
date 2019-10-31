package com.carpooling.bot.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

@Entity
@Table(name = "cp_travel", schema = "carpooling_db", catalog = "")
public class CpTravelEntity {
    private long travelId;
    private Time departureTime;
    private BigDecimal cost;
    private int numberPassengers;
    private int petFriendly;
    private int status;
    private String txUser;
    private String txHost;
    private Date txDate;

    @Id
    @Column(name = "travel_id")
    public long getTravelId() {
        return travelId;
    }

    public void setTravelId(long travelId) {
        this.travelId = travelId;
    }

    @Basic
    @Column(name = "departure_time")
    public Time getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Time departureTime) {
        this.departureTime = departureTime;
    }

    @Basic
    @Column(name = "cost")
    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    @Basic
    @Column(name = "number_passengers")
    public int getNumberPassengers() {
        return numberPassengers;
    }

    public void setNumberPassengers(int numberPassengers) {
        this.numberPassengers = numberPassengers;
    }

    @Basic
    @Column(name = "pet_friendly")
    public int getPetFriendly() {
        return petFriendly;
    }

    public void setPetFriendly(int petFriendly) {
        this.petFriendly = petFriendly;
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
        CpTravelEntity that = (CpTravelEntity) o;
        return travelId == that.travelId &&
                numberPassengers == that.numberPassengers &&
                petFriendly == that.petFriendly &&
                status == that.status &&
                Objects.equals(departureTime, that.departureTime) &&
                Objects.equals(cost, that.cost) &&
                Objects.equals(txUser, that.txUser) &&
                Objects.equals(txHost, that.txHost) &&
                Objects.equals(txDate, that.txDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(travelId, departureTime, cost, numberPassengers, petFriendly, status, txUser, txHost, txDate);
    }
}
