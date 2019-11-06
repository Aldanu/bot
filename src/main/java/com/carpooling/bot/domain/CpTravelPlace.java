/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carpooling.bot.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "cp_travel_place")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CpTravelPlace.findAll", query = "SELECT c FROM CpTravelPlace c"),
    @NamedQuery(name = "CpTravelPlace.findByTravelPlaceId", query = "SELECT c FROM CpTravelPlace c WHERE c.travelPlaceId = :travelPlaceId"),
    @NamedQuery(name = "CpTravelPlace.findByOrder", query = "SELECT c FROM CpTravelPlace c WHERE c.order = :order"),
    @NamedQuery(name = "CpTravelPlace.findByStatus", query = "SELECT c FROM CpTravelPlace c WHERE c.status = :status"),
    @NamedQuery(name = "CpTravelPlace.findByTxUser", query = "SELECT c FROM CpTravelPlace c WHERE c.txUser = :txUser"),
    @NamedQuery(name = "CpTravelPlace.findByTxHost", query = "SELECT c FROM CpTravelPlace c WHERE c.txHost = :txHost"),
    @NamedQuery(name = "CpTravelPlace.findByTxDate", query = "SELECT c FROM CpTravelPlace c WHERE c.txDate = :txDate")})
public class CpTravelPlace implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "travel_place_id")
    private Integer travelPlaceId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "order")
    private int order;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "tx_user")
    private String txUser;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "tx_host")
    private String txHost;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tx_date")
    @Temporal(TemporalType.DATE)
    private Date txDate;
    @JoinColumn(name = "place_id", referencedColumnName = "place_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CpPlace placeId;
    @JoinColumn(name = "travel_id", referencedColumnName = "travel_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CpTravel travelId;

    public CpTravelPlace() {
    }

    public CpTravelPlace(Integer travelPlaceId) {
        this.travelPlaceId = travelPlaceId;
    }

    public CpTravelPlace(Integer travelPlaceId, int order, int status, String txUser, String txHost, Date txDate) {
        this.travelPlaceId = travelPlaceId;
        this.order = order;
        this.status = status;
        this.txUser = txUser;
        this.txHost = txHost;
        this.txDate = txDate;
    }

    public Integer getTravelPlaceId() {
        return travelPlaceId;
    }

    public void setTravelPlaceId(Integer travelPlaceId) {
        this.travelPlaceId = travelPlaceId;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTxUser() {
        return txUser;
    }

    public void setTxUser(String txUser) {
        this.txUser = txUser;
    }

    public String getTxHost() {
        return txHost;
    }

    public void setTxHost(String txHost) {
        this.txHost = txHost;
    }

    public Date getTxDate() {
        return txDate;
    }

    public void setTxDate(Date txDate) {
        this.txDate = txDate;
    }

    public CpPlace getPlaceId() {
        return placeId;
    }

    public void setPlaceId(CpPlace placeId) {
        this.placeId = placeId;
    }

    public CpTravel getTravelId() {
        return travelId;
    }

    public void setTravelId(CpTravel travelId) {
        this.travelId = travelId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (travelPlaceId != null ? travelPlaceId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CpTravelPlace)) {
            return false;
        }
        CpTravelPlace other = (CpTravelPlace) object;
        if ((this.travelPlaceId == null && other.travelPlaceId != null) || (this.travelPlaceId != null && !this.travelPlaceId.equals(other.travelPlaceId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.carpooling.bot.domain.CpTravelPlace[ travelPlaceId=" + travelPlaceId + " ]";
    }
    
}
