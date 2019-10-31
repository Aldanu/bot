package com.carpooling.bot.domain;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "cp_travel_place", schema = "carpooling_db", catalog = "")
public class CpTravelPlaceEntity {
    private long travelPlaceId;
    private int order;
    private int status;
    private String txUser;
    private String txHost;
    private Date txDate;

    @Id
    @Column(name = "travel_place_id")
    public long getTravelPlaceId() {
        return travelPlaceId;
    }

    public void setTravelPlaceId(long travelPlaceId) {
        this.travelPlaceId = travelPlaceId;
    }

    @Basic
    @Column(name = "order")
    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
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
        CpTravelPlaceEntity that = (CpTravelPlaceEntity) o;
        return travelPlaceId == that.travelPlaceId &&
                order == that.order &&
                status == that.status &&
                Objects.equals(txUser, that.txUser) &&
                Objects.equals(txHost, that.txHost) &&
                Objects.equals(txDate, that.txDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(travelPlaceId, order, status, txUser, txHost, txDate);
    }
}
