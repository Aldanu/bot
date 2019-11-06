/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carpooling.bot.domain;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "cp_travel_rider")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CpTravelRider.findAll", query = "SELECT c FROM CpTravelRider c"),
    @NamedQuery(name = "CpTravelRider.findByTravelRiderId", query = "SELECT c FROM CpTravelRider c WHERE c.travelRiderId = :travelRiderId"),
    @NamedQuery(name = "CpTravelRider.findByExperience", query = "SELECT c FROM CpTravelRider c WHERE c.experience = :experience"),
    @NamedQuery(name = "CpTravelRider.findByStatus", query = "SELECT c FROM CpTravelRider c WHERE c.status = :status"),
    @NamedQuery(name = "CpTravelRider.findByTxUser", query = "SELECT c FROM CpTravelRider c WHERE c.txUser = :txUser"),
    @NamedQuery(name = "CpTravelRider.findByTxHost", query = "SELECT c FROM CpTravelRider c WHERE c.txHost = :txHost"),
    @NamedQuery(name = "CpTravelRider.findByTxDate", query = "SELECT c FROM CpTravelRider c WHERE c.txDate = :txDate")})
public class CpTravelRider implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "travel_rider_id")
    private Integer travelRiderId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "experience")
    private BigDecimal experience;
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
    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CpPerson personId;
    @JoinColumn(name = "travel_id", referencedColumnName = "travel_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CpTravel travelId;

    public CpTravelRider() {
    }

    public CpTravelRider(Integer travelRiderId) {
        this.travelRiderId = travelRiderId;
    }

    public CpTravelRider(Integer travelRiderId, int status, String txUser, String txHost, Date txDate) {
        this.travelRiderId = travelRiderId;
        this.status = status;
        this.txUser = txUser;
        this.txHost = txHost;
        this.txDate = txDate;
    }

    public Integer getTravelRiderId() {
        return travelRiderId;
    }

    public void setTravelRiderId(Integer travelRiderId) {
        this.travelRiderId = travelRiderId;
    }

    public BigDecimal getExperience() {
        return experience;
    }

    public void setExperience(BigDecimal experience) {
        this.experience = experience;
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

    public CpPerson getPersonId() {
        return personId;
    }

    public void setPersonId(CpPerson personId) {
        this.personId = personId;
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
        hash += (travelRiderId != null ? travelRiderId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CpTravelRider)) {
            return false;
        }
        CpTravelRider other = (CpTravelRider) object;
        if ((this.travelRiderId == null && other.travelRiderId != null) || (this.travelRiderId != null && !this.travelRiderId.equals(other.travelRiderId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.carpooling.bot.domain.CpTravelRider[ travelRiderId=" + travelRiderId + " ]";
    }
    
}
