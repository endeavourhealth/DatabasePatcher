package org.endeavourhealth.databasepatcher.core.database.models;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by darren on 19/07/2017.
 */
@Entity
@Table(name = "db_patch_schedule", schema = "database_patcher", catalog = "")
public class DbPatchScheduleEntity {
    private int id;
    private int dbId;
    private int patchId;
    private Timestamp patchTimestamp;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "db_id", nullable = false)
    public int getDbId() {
        return dbId;
    }

    public void setDbId(int dbId) {
        this.dbId = dbId;
    }

    @Basic
    @Column(name = "patch_id", nullable = false)
    public int getPatchId() {
        return patchId;
    }

    public void setPatchId(int patchId) {
        this.patchId = patchId;
    }

    @Basic
    @Column(name = "patch_timestamp", nullable = false)
    public Timestamp getPatchTimestamp() {
        return patchTimestamp;
    }

    public void setPatchTimestamp(Timestamp patchTimestamp) {
        this.patchTimestamp = patchTimestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DbPatchScheduleEntity that = (DbPatchScheduleEntity) o;

        if (id != that.id) return false;
        if (dbId != that.dbId) return false;
        if (patchId != that.patchId) return false;
        if (patchTimestamp != null ? !patchTimestamp.equals(that.patchTimestamp) : that.patchTimestamp != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + dbId;
        result = 31 * result + patchId;
        result = 31 * result + (patchTimestamp != null ? patchTimestamp.hashCode() : 0);
        return result;
    }
}
