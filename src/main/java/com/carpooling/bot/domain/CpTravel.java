/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carpooling.bot.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "cp_travel")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CpTravel.findAll", query = "SELECT c FROM CpTravel c"),
    @NamedQuery(name = "CpTravel.findByTravelId", query = "SELECT c FROM CpTravel c WHERE c.travelId = :travelId"),
    @NamedQuery(name = "CpTravel.findByDepartureTime", query = "SELECT c FROM CpTravel c WHERE c.departureTime = :departureTime"),
    @NamedQuery(name = "CpTravel.findByCost", query = "SELECT c FROM CpTravel c WHERE c.cost = :cost"),
    @NamedQuery(name = "CpTravel.findByNumberPassengers", query = "SELECT c FROM CpTravel c WHERE c.numberPassengers = :numberPassengers"),
    @NamedQuery(name = "CpTravel.findByPetFriendly", query = "SELECT c FROM CpTravel c WHERE c.petFriendly = :petFriendly"),
    @NamedQuery(name = "CpTravel.findByDescription", query = "SELECT c FROM CpTravel c WHERE c.description = :description"),
    @NamedQuery(name = "CpTravel.findByStatus", query = "SELECT c FROM CpTravel c WHERE c.status = :status"),
    @NamedQuery(name = "CpTravel.findByTxUser", query = "SELECT c FROM CpTravel c WHERE c.txUser = :txUser"),
    @NamedQuery(name = "CpTravel.findByTxHost", query = "SELECT c FROM CpTravel c WHERE c.txHost = :txHost"),
    @NamedQuery(name = "CpTravel.findByTxDate", query = "SELECT c FROM CpTravel c WHERE c.txDate = :txDate")})
public class CpTravel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "travel_id")
    private Integer travelId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "departure_time")
    private String departureTime;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "cost")
    private BigDecimal cost;
    @Basic(optional = false)
    @NotNull
    @Column(name = "number_passengers")
    private int numberPassengers;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pet_friendly")
    private int petFriendly;
    @Size(max = 255)
    @Column(name = "description")
    private String description;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "travelId", fetch = FetchType.LAZY)
    private List<CpTravelRider> cpTravelRiderList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "travelId", fetch = FetchType.LAZY)
    private List<CpTravelPlace> cpTravelPlaceList;
    @JoinColumn(name = "car_id", referencedColumnName = "car_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CpCar carId;

    public CpTravel() {
    }

    public CpTravel(Integer travelId) {
        this.travelId = travelId;
    }

    public CpTravel(Integer travelId, String departureTime, BigDecimal cost, int numberPassengers, int petFriendly, int status, String txUser, String txHost, Date txDate) {
        this.travelId = travelId;
        this.departureTime = departureTime;
        this.cost = cost;
        this.numberPassengers = numberPassengers;
        this.petFriendly = petFriendly;
        this.status = status;
        this.txUser = txUser;
        this.txHost = txHost;
        this.txDate = txDate;
    }

    public Integer getTravelId() {
        return travelId;
    }

    public void setTravelId(Integer travelId) {
        this.travelId = travelId;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public int getNumberPassengers() {
        return numberPassengers;
    }

    public void setNumberPassengers(int numberPassengers) {
        this.numberPassengers = numberPassengers;
    }

    public int getPetFriendly() {
        return petFriendly;
    }

    public void setPetFriendly(int petFriendly) {
        this.petFriendly = petFriendly;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @XmlTransient
    public List<CpTravelRider> getCpTravelRiderList() {
        return cpTravelRiderList;
    }

    public void setCpTravelRiderList(List<CpTravelRider> cpTravelRiderList) {
        this.cpTravelRiderList = cpTravelRiderList;
    }

    @XmlTransient
    public List<CpTravelPlace> getCpTravelPlaceList() {
        return cpTravelPlaceList;
    }

    public void setCpTravelPlaceList(List<CpTravelPlace> cpTravelPlaceList) {
        this.cpTravelPlaceList = cpTravelPlaceList;
    }

    public CpCar getCarId() {
        return carId;
    }

    public void setCarId(CpCar carId) {
        this.carId = carId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (travelId != null ? travelId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CpTravel)) {
            return false;
        }
        CpTravel other = (CpTravel) object;
        if ((this.travelId == null && other.travelId != null) || (this.travelId != null && !this.travelId.equals(other.travelId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.carpooling.bot.domain.CpTravel[ travelId=" + travelId + " ]";
    }
    
}
