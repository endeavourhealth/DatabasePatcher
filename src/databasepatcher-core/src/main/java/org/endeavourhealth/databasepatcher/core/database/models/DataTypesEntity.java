package org.endeavourhealth.databasepatcher.core.database.models;

import org.endeavourhealth.databasepatcher.core.database.PersistenceManager;

import javax.persistence.*;
import java.util.List;

/**
 * Created by darren on 24/06/2017.
 */
@Entity
@Table(name = "data_types", schema = "database_patcher", catalog = "")
public class DataTypesEntity {
    private int id;
    private String type;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "type", nullable = false, length = 45)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DataTypesEntity that = (DataTypesEntity) o;

        if (id != that.id) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    public static List<DataTypesEntity> getDataTypes() throws Exception {
        String where = "select d from DataTypesEntity d";

        EntityManager entityManager = PersistenceManager.INSTANCE.getemPatcher();

        List<DataTypesEntity> ent = entityManager.createQuery(where, DataTypesEntity.class)
                .getResultList();

        entityManager.close();

        return ent;

    }
}
