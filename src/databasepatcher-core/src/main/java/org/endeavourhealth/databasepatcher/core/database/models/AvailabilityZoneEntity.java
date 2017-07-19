package org.endeavourhealth.databasepatcher.core.database.models;

import org.endeavourhealth.databasepatcher.core.database.PersistenceManager;

import javax.persistence.*;
import java.util.List;

/**
 * Created by darren on 24/06/2017.
 */
@Entity
@Table(name = "availability_zone", schema = "database_patcher", catalog = "")
public class AvailabilityZoneEntity {
    private int id;
    private String zone;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "zone", nullable = true, length = 45)
    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AvailabilityZoneEntity that = (AvailabilityZoneEntity) o;

        if (id != that.id) return false;
        if (zone != null ? !zone.equals(that.zone) : that.zone != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (zone != null ? zone.hashCode() : 0);
        return result;
    }

    public static List<AvailabilityZoneEntity> getZones() throws Exception {
        String where = "select a from AvailabilityZoneEntity a";

        EntityManager entityManager = PersistenceManager.INSTANCE.getemPatcher();

        List<AvailabilityZoneEntity> ent = entityManager.createQuery(where, AvailabilityZoneEntity.class)
                .getResultList();

        entityManager.close();

        return ent;

    }
}
