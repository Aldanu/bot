/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carpooling.bot.domain;

import java.io.Serializable;
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
@Table(name = "cp_car")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CpCar.findAll", query = "SELECT c FROM CpCar c"),
    @NamedQuery(name = "CpCar.findByCarId", query = "SELECT c FROM CpCar c WHERE c.carId = :carId"),
    @NamedQuery(name = "CpCar.findByEnrollmentNumber", query = "SELECT c FROM CpCar c WHERE c.enrollmentNumber = :enrollmentNumber"),
    @NamedQuery(name = "CpCar.findByBrand", query = "SELECT c FROM CpCar c WHERE c.brand = :brand"),
    @NamedQuery(name = "CpCar.findByModel", query = "SELECT c FROM CpCar c WHERE c.model = :model"),
    @NamedQuery(name = "CpCar.findByCapacity", query = "SELECT c FROM CpCar c WHERE c.capacity = :capacity"),
    @NamedQuery(name = "CpCar.findByStatus", query = "SELECT c FROM CpCar c WHERE c.status = :status"),
    @NamedQuery(name = "CpCar.findByTxUser", query = "SELECT c FROM CpCar c WHERE c.txUser = :txUser"),
    @NamedQuery(name = "CpCar.findByTxHost", query = "SELECT c FROM CpCar c WHERE c.txHost = :txHost"),
    @NamedQuery(name = "CpCar.findByTxDate", query = "SELECT c FROM CpCar c WHERE c.txDate = :txDate")})
public class CpCar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "car_id")
    private Integer carId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "enrollment_number")
    private String enrollmentNumber;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "brand")
    private String brand;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "model")
    private String model;
    @Basic(optional = false)
    @NotNull
    @Column(name = "capacity")
    private int capacity;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "carId", fetch = FetchType.LAZY)
    private List<CpTravel> cpTravelList;

    public CpCar() {
    }

    public CpCar(Integer carId) {
        this.carId = carId;
    }

    public CpCar(Integer carId, String enrollmentNumber, String brand, String model, int capacity, int status, String txUser, String txHost, Date txDate) {
        this.carId = carId;
        this.enrollmentNumber = enrollmentNumber;
        this.brand = brand;
        this.model = model;
        this.capacity = capacity;
        this.status = status;
        this.txUser = txUser;
        this.txHost = txHost;
        this.txDate = txDate;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getEnrollmentNumber() {
        return enrollmentNumber;
    }

    public void setEnrollmentNumber(String enrollmentNumber) {
        this.enrollmentNumber = enrollmentNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
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

    @XmlTransient
    public List<CpTravel> getCpTravelList() {
        return cpTravelList;
    }

    public void setCpTravelList(List<CpTravel> cpTravelList) {
        this.cpTravelList = cpTravelList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (carId != null ? carId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CpCar)) {
            return false;
        }
        CpCar other = (CpCar) object;
        if ((this.carId == null && other.carId != null) || (this.carId != null && !this.carId.equals(other.carId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Placa = " + enrollmentNumber + '\n' +
                "Marca =" + brand + '\n' +
                " Model =" + model + '\n' +
                ", Capacidad =" + capacity ;
    }
}
