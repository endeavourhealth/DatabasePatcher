package org.endeavourhealth.databasepatcher.core.database.models;

import org.endeavourhealth.databasepatcher.core.database.PersistenceManager;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by darren on 23/06/2017.
 */
@Entity
@Table(name = "region", schema = "database_patcher", catalog = "")
public class RegionEntity {
    private int id;
    private String name;


    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RegionEntity that = (RegionEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);

        return result;
    }

    public static List<RegionEntity> getRegions() throws Exception {
        String where = "select r from RegionEntity r";

        EntityManager entityManager = PersistenceManager.INSTANCE.getemPatcher();

        List<RegionEntity> ent = entityManager.createQuery(where, RegionEntity.class)
                .getResultList();

        entityManager.close();

        return ent;

    }
}
