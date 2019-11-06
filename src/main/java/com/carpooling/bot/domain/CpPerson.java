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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "cp_person")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CpPerson.findAll", query = "SELECT c FROM CpPerson c"),
    @NamedQuery(name = "CpPerson.findByPersonId", query = "SELECT c FROM CpPerson c WHERE c.personId = :personId"),
    @NamedQuery(name = "CpPerson.findByFirstName", query = "SELECT c FROM CpPerson c WHERE c.firstName = :firstName"),
    @NamedQuery(name = "CpPerson.findByLastName", query = "SELECT c FROM CpPerson c WHERE c.lastName = :lastName"),
    @NamedQuery(name = "CpPerson.findByCiNumber", query = "SELECT c FROM CpPerson c WHERE c.ciNumber = :ciNumber"),
    @NamedQuery(name = "CpPerson.findByCellphoneNumber", query = "SELECT c FROM CpPerson c WHERE c.cellphoneNumber = :cellphoneNumber"),
    @NamedQuery(name = "CpPerson.findByReputation", query = "SELECT c FROM CpPerson c WHERE c.reputation = :reputation"),
    @NamedQuery(name = "CpPerson.findByCarpool", query = "SELECT c FROM CpPerson c WHERE c.carpool = :carpool"),
    @NamedQuery(name = "CpPerson.findByStatus", query = "SELECT c FROM CpPerson c WHERE c.status = :status"),
    @NamedQuery(name = "CpPerson.findByTxUser", query = "SELECT c FROM CpPerson c WHERE c.txUser = :txUser"),
    @NamedQuery(name = "CpPerson.findByTxHost", query = "SELECT c FROM CpPerson c WHERE c.txHost = :txHost"),
    @NamedQuery(name = "CpPerson.findByTxDate", query = "SELECT c FROM CpPerson c WHERE c.txDate = :txDate")})
public class CpPerson implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "person_id")
    private Integer personId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "first_name")
    private String firstName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "last_name")
    private String lastName;
    @Size(max = 30)
    @Column(name = "ci_number")
    private String ciNumber;
    @Size(max = 30)
    @Column(name = "cellphone_number")
    private String cellphoneNumber;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "reputation")
    private BigDecimal reputation;
    @Basic(optional = false)
    @NotNull
    @Column(name = "carpool")
    private int carpool;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personId", fetch = FetchType.LAZY)
    private List<CpCar> cpCarList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personId", fetch = FetchType.LAZY)
    private List<CpTravelRider> cpTravelRiderList;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "personId", fetch = FetchType.LAZY)
    private CpUser cpUser;

    public CpPerson() {
    }

    public CpPerson(Integer personId) {
        this.personId = personId;
    }

    public CpPerson(Integer personId, String firstName, String lastName, int carpool, int status, String txUser, String txHost, Date txDate) {
        this.personId = personId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.carpool = carpool;
        this.status = status;
        this.txUser = txUser;
        this.txHost = txHost;
        this.txDate = txDate;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCiNumber() {
        return ciNumber;
    }

    public void setCiNumber(String ciNumber) {
        this.ciNumber = ciNumber;
    }

    public String getCellphoneNumber() {
        return cellphoneNumber;
    }

    public void setCellphoneNumber(String cellphoneNumber) {
        this.cellphoneNumber = cellphoneNumber;
    }

    public BigDecimal getReputation() {
        return reputation;
    }

    public void setReputation(BigDecimal reputation) {
        this.reputation = reputation;
    }

    public int getCarpool() {
        return carpool;
    }

    public void setCarpool(int carpool) {
        this.carpool = carpool;
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
    public List<CpCar> getCpCarList() {
        return cpCarList;
    }

    public void setCpCarList(List<CpCar> cpCarList) {
        this.cpCarList = cpCarList;
    }

    @XmlTransient
    public List<CpTravelRider> getCpTravelRiderList() {
        return cpTravelRiderList;
    }

    public void setCpTravelRiderList(List<CpTravelRider> cpTravelRiderList) {
        this.cpTravelRiderList = cpTravelRiderList;
    }

    public CpUser getCpUser() {
        return cpUser;
    }

    public void setCpUser(CpUser cpUser) {
        this.cpUser = cpUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personId != null ? personId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CpPerson)) {
            return false;
        }
        CpPerson other = (CpPerson) object;
        if ((this.personId == null && other.personId != null) || (this.personId != null && !this.personId.equals(other.personId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.carpooling.bot.domain.CpPerson[ personId=" + personId + " ]";
    }
    
}
