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
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "cp_travel_search")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "CpTravelSearch.findAll", query = "SELECT c FROM CpTravelSearch c")})
public class CpTravelSearch implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "travel_search_id")
    private Integer travelSearchId;

    @Column(name = "id_person")
    private int idPerson;

    @Size(min = 1, max = 200)
    @Column(name = "place_start")
    private String placeStart;

    @Size(min = 1, max = 200)
    @Column(name = "place_finish")
    private String placeFinish;

    @Size(min = 1, max = 100)
    @Column(name = "departure_time")
    private String departureTime;

    public CpTravelSearch() {
    }

    public CpTravelSearch(Integer travelSearchId) {
        this.travelSearchId = travelSearchId;
    }

    public CpTravelSearch(int idPerson, @Size(min = 1, max = 200) String placeStart, @Size(min = 1, max = 200) String placeFinish, @Size(min = 1, max = 100) String departureTime) {
        this.idPerson = idPerson;
        this.placeStart = placeStart;
        this.placeFinish = placeFinish;
        this.departureTime = departureTime;
    }

    public Integer getTravelSearchId() {
        return travelSearchId;
    }

    public void setTravelSearchId(Integer travelSearchId) {
        this.travelSearchId = travelSearchId;
    }

    public int getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(int idPerson) {
        this.idPerson = idPerson;
    }

    public String getPlaceStart() {
        return placeStart;
    }

    public void setPlaceStart(String placeStart) {
        this.placeStart = placeStart;
    }

    public String getPlaceFinish() {
        return placeFinish;
    }

    public void setPlaceFinish(String placeFinish) {
        this.placeFinish = placeFinish;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (travelSearchId != null ? travelSearchId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CpTravelSearch)) {
            return false;
        }
        CpTravelSearch other = (CpTravelSearch) object;
        if ((this.travelSearchId == null && other.travelSearchId != null) || (this.travelSearchId != null && !this.travelSearchId.equals(other.travelSearchId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.carpooling.bot.domain.CpTravelSearch[ travelSearchId=" + travelSearchId + " ]";
    }

}
