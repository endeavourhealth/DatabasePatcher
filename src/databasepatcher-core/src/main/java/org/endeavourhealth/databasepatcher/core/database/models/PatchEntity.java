package org.endeavourhealth.databasepatcher.core.database.models;

import org.endeavourhealth.databasepatcher.core.database.PersistenceManager;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by darren on 11/07/2017.
 */
@Entity
@Table(name = "patch", schema = "database_patcher", catalog = "")
public class PatchEntity {
    private int id;
    private String patchDescription;
    private Timestamp createdTimestamp;
    private String patchScript;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "patch_description", nullable = false, length = 1000)
    public String getPatchDescription() {
        return patchDescription;
    }

    public void setPatchDescription(String patchDescription) {
        this.patchDescription = patchDescription;
    }

    @Basic
    @Column(name = "created_timestamp", nullable = false)
    public Timestamp getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(Timestamp createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    @Basic
    @Column(name = "patch_script", nullable = false, length = -1)
    public String getPatchScript() {
        return patchScript;
    }

    public void setPatchScript(String patchScript) {
        this.patchScript = patchScript;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PatchEntity that = (PatchEntity) o;

        if (id != that.id) return false;
        if (patchDescription != null ? !patchDescription.equals(that.patchDescription) : that.patchDescription != null)
            return false;
        if (createdTimestamp != null ? !createdTimestamp.equals(that.createdTimestamp) : that.createdTimestamp != null)
            return false;
        if (patchScript != null ? !patchScript.equals(that.patchScript) : that.patchScript != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (patchDescription != null ? patchDescription.hashCode() : 0);
        result = 31 * result + (createdTimestamp != null ? createdTimestamp.hashCode() : 0);
        result = 31 * result + (patchScript != null ? patchScript.hashCode() : 0);
        return result;
    }

    public static void savePatch(PatchEntity patchEntity) throws Exception {
        EntityManager entityManager = PersistenceManager.INSTANCE.getemPatcher();

        entityManager.getTransaction().begin();

        entityManager.merge(patchEntity);

        entityManager.getTransaction().commit();

        entityManager.close();
    }

    public static List<Object[]> getPatches() throws Exception {

        String where = "select p.id,p.patchDescription,p.createdTimestamp,p.patchScript from PatchEntity p";

        EntityManager entityManager = PersistenceManager.INSTANCE.getemPatcher();

        List<Object[]> ent = null;

        ent = entityManager.createQuery(where)
                .getResultList();

        entityManager.close();

        return ent;

    }

    public static List<Object[]> getPatch(Integer patchId) throws Exception {

        String where = "select p.id,p.patchDescription,p.createdTimestamp,p.patchScript from PatchEntity p where id = :id";

        EntityManager entityManager = PersistenceManager.INSTANCE.getemPatcher();

        List<Object[]> ent = null;

        ent = entityManager.createQuery(where)
                .setParameter("id", patchId)
                .getResultList();

        entityManager.close();

        return ent;

    }
}
