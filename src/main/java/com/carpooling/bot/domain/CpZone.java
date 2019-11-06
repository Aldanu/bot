/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carpooling.bot.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "cp_zone")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CpZone.findAll", query = "SELECT c FROM CpZone c"),
    @NamedQuery(name = "CpZone.findByZoneId", query = "SELECT c FROM CpZone c WHERE c.zoneId = :zoneId"),
    @NamedQuery(name = "CpZone.findByName", query = "SELECT c FROM CpZone c WHERE c.name = :name"),
    @NamedQuery(name = "CpZone.findByStatus", query = "SELECT c FROM CpZone c WHERE c.status = :status")})
public class CpZone implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "zone_id")
    private Integer zoneId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "zoneId", fetch = FetchType.LAZY)
    private List<CpPlace> cpPlaceList;

    public CpZone() {
    }

    public CpZone(Integer zoneId) {
        this.zoneId = zoneId;
    }

    public CpZone(Integer zoneId, String name, int status) {
        this.zoneId = zoneId;
        this.name = name;
        this.status = status;
    }

    public Integer getZoneId() {
        return zoneId;
    }

    public void setZoneId(Integer zoneId) {
        this.zoneId = zoneId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @XmlTransient
    public List<CpPlace> getCpPlaceList() {
        return cpPlaceList;
    }

    public void setCpPlaceList(List<CpPlace> cpPlaceList) {
        this.cpPlaceList = cpPlaceList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (zoneId != null ? zoneId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CpZone)) {
            return false;
        }
        CpZone other = (CpZone) object;
        if ((this.zoneId == null && other.zoneId != null) || (this.zoneId != null && !this.zoneId.equals(other.zoneId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.carpooling.bot.domain.CpZone[ zoneId=" + zoneId + " ]";
    }
    
}
