/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carpooling.bot.domain;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "cp_place")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CpPlace.findAll", query = "SELECT c FROM CpPlace c"),
    @NamedQuery(name = "CpPlace.findByPlaceId", query = "SELECT c FROM CpPlace c WHERE c.placeId = :placeId"),
    @NamedQuery(name = "CpPlace.findByName", query = "SELECT c FROM CpPlace c WHERE c.name = :name"),
    @NamedQuery(name = "CpPlace.findByLatitude", query = "SELECT c FROM CpPlace c WHERE c.latitude = :latitude"),
    @NamedQuery(name = "CpPlace.findByLongitude", query = "SELECT c FROM CpPlace c WHERE c.longitude = :longitude"),
    @NamedQuery(name = "CpPlace.findByStatus", query = "SELECT c FROM CpPlace c WHERE c.status = :status")})
public class CpPlace implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "place_id")
    private Integer placeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "name")
    private String name;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "latitude")
    private BigDecimal latitude;
    @Basic(optional = false)
    @NotNull
    @Column(name = "longitude")
    private BigDecimal longitude;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;
    @JoinColumn(name = "zone_id", referencedColumnName = "zone_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CpZone zoneId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "placeId", fetch = FetchType.LAZY)
    private List<CpTravelPlace> cpTravelPlaceList;

    public CpPlace() {
    }

    public CpPlace(Integer placeId) {
        this.placeId = placeId;
    }

    public CpPlace(Integer placeId, String name, BigDecimal latitude, BigDecimal longitude, int status) {
        this.placeId = placeId;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.status = status;
    }

    public Integer getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Integer placeId) {
        this.placeId = placeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public CpZone getZoneId() {
        return zoneId;
    }

    public void setZoneId(CpZone zoneId) {
        this.zoneId = zoneId;
    }

    @XmlTransient
    public List<CpTravelPlace> getCpTravelPlaceList() {
        return cpTravelPlaceList;
    }

    public void setCpTravelPlaceList(List<CpTravelPlace> cpTravelPlaceList) {
        this.cpTravelPlaceList = cpTravelPlaceList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (placeId != null ? placeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CpPlace)) {
            return false;
        }
        CpPlace other = (CpPlace) object;
        if ((this.placeId == null && other.placeId != null) || (this.placeId != null && !this.placeId.equals(other.placeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.carpooling.bot.domain.CpPlace[ placeId=" + placeId + " ]";
    }
    public String toStringOption(){
        return name;
    }
    
}
