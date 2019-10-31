package com.carpooling.bot.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "cp_place", schema = "carpooling_db", catalog = "")
public class CpPlaceEntity {
    private long placeId;
    private String name;
    private long zoneId;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private int status;
    private CpZoneEntity cpZoneByZoneId;
    private List<CpTravelPlaceEntity> cpTravelPlacesByPlaceId;

    @Id
    @Column(name = "place_id")
    public long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(long placeId) {
        this.placeId = placeId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "zone_id")
    public long getZoneId() {
        return zoneId;
    }

    public void setZoneId(long zoneId) {
        this.zoneId = zoneId;
    }

    @Basic
    @Column(name = "latitude")
    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    @Basic
    @Column(name = "longitude")
    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    @Basic
    @Column(name = "status")
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CpPlaceEntity that = (CpPlaceEntity) o;
        return placeId == that.placeId &&
                zoneId == that.zoneId &&
                status == that.status &&
                Objects.equals(name, that.name) &&
                Objects.equals(latitude, that.latitude) &&
                Objects.equals(longitude, that.longitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(placeId, name, zoneId, latitude, longitude, status);
    }

    @ManyToOne
    @JoinColumn(name = "zone_id", referencedColumnName = "zone_id", nullable = false)
    public CpZoneEntity getCpZoneByZoneId() {
        return cpZoneByZoneId;
    }

    public void setCpZoneByZoneId(CpZoneEntity cpZoneByZoneId) {
        this.cpZoneByZoneId = cpZoneByZoneId;
    }

    @OneToMany(mappedBy = "cpPlaceByPlaceId")
    public List<CpTravelPlaceEntity> getCpTravelPlacesByPlaceId() {
        return cpTravelPlacesByPlaceId;
    }

    public void setCpTravelPlacesByPlaceId(List<CpTravelPlaceEntity> cpTravelPlacesByPlaceId) {
        this.cpTravelPlacesByPlaceId = cpTravelPlacesByPlaceId;
    }
}