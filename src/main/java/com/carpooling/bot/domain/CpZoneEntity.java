package com.carpooling.bot.domain;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "cp_zone", schema = "carpooling_db", catalog = "")
public class CpZoneEntity {
    private long zoneId;
    private String name;
    private int status;
    private Collection<CpPlaceEntity> cpPlacesByZoneId;

    @Id
    @Column(name = "zone_id")
    public long getZoneId() {
        return zoneId;
    }

    public void setZoneId(long zoneId) {
        this.zoneId = zoneId;
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
        CpZoneEntity that = (CpZoneEntity) o;
        return zoneId == that.zoneId &&
                status == that.status &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zoneId, name, status);
    }

    @OneToMany(mappedBy = "cpZoneByZoneId")
    public Collection<CpPlaceEntity> getCpPlacesByZoneId() {
        return cpPlacesByZoneId;
    }

    public void setCpPlacesByZoneId(Collection<CpPlaceEntity> cpPlacesByZoneId) {
        this.cpPlacesByZoneId = cpPlacesByZoneId;
    }
}
