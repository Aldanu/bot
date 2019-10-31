package com.carpooling.bot.domain;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "cp_car", schema = "carpooling_db", catalog = "")
public class CpCarEntity {
    private long carId;
    private long carpoolerId;
    private String enrollmentNumber;
    private String brand;
    private String model;
    private int status;
    private String txUser;
    private String txHost;
    private Date txDate;
    private CpCarpoolerEntity cpCarpoolerByCarpoolerId;
    private List<CpTravelEntity> cpTravelsByCarId;

    @Id
    @Column(name = "car_id")
    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    @Basic
    @Column(name = "carpooler_id")
    public long getCarpoolerId() {
        return carpoolerId;
    }

    public void setCarpoolerId(long carpoolerId) {
        this.carpoolerId = carpoolerId;
    }

    @Basic
    @Column(name = "enrollment_number")
    public String getEnrollmentNumber() {
        return enrollmentNumber;
    }

    public void setEnrollmentNumber(String enrollmentNumber) {
        this.enrollmentNumber = enrollmentNumber;
    }

    @Basic
    @Column(name = "brand")
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Basic
    @Column(name = "model")
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
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
        CpCarEntity that = (CpCarEntity) o;
        return carId == that.carId &&
                carpoolerId == that.carpoolerId &&
                status == that.status &&
                Objects.equals(enrollmentNumber, that.enrollmentNumber) &&
                Objects.equals(brand, that.brand) &&
                Objects.equals(model, that.model) &&
                Objects.equals(txUser, that.txUser) &&
                Objects.equals(txHost, that.txHost) &&
                Objects.equals(txDate, that.txDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carId, carpoolerId, enrollmentNumber, brand, model, status, txUser, txHost, txDate);
    }

    @ManyToOne
    @JoinColumn(name = "carpooler_id", referencedColumnName = "carpooler_id", nullable = false)
    public CpCarpoolerEntity getCpCarpoolerByCarpoolerId() {
        return cpCarpoolerByCarpoolerId;
    }

    public void setCpCarpoolerByCarpoolerId(CpCarpoolerEntity cpCarpoolerByCarpoolerId) {
        this.cpCarpoolerByCarpoolerId = cpCarpoolerByCarpoolerId;
    }

    @OneToMany(mappedBy = "cpCarByCarId")
    public List<CpTravelEntity> getCpTravelsByCarId() {
        return cpTravelsByCarId;
    }

    public void setCpTravelsByCarId(List<CpTravelEntity> cpTravelsByCarId) {
        this.cpTravelsByCarId = cpTravelsByCarId;
    }
}
