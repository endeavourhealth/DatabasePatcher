package org.endeavourhealth.databasepatcher.core.database.models;

import org.endeavourhealth.databasepatcher.core.database.PersistenceManager;
import org.endeavourhealth.databasepatcher.core.database.json.JsonDb;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by darren on 23/06/2017.
 */
@Entity
@Table(name = "db", schema = "database_patcher", catalog = "")
public class DbEntity {
    private int id;
    private String name;
    private Integer regionId;
    private String hostname;
    private String port;
    private String username;
    private String password;
    private Timestamp lastPatchTimestamp;
    private Byte lastStatus;
    private String currentVersion;
    private Timestamp nextPatchTimestamp;
    private String targetVersion;
    private Integer dataTypeId;
    private Integer availabilityZoneId;
    private Integer patchId;

    public static List<Object[]> getDatabases(String regionId, String availabilityZoneId, String dataTypeId) throws Exception {

        String filter = "";

        if (!regionId.equals("0"))
            filter += " and d.regionId = :regionId";
        else
            filter += " and d.regionId > :regionId";

        if (!availabilityZoneId.equals("0"))
            filter += " and d.availabilityZoneId = :availabilityZoneId";
        else
            filter += " and d.availabilityZoneId > :availabilityZoneId";

        if (!dataTypeId.equals("0"))
            filter += " and d.dataTypeId = :dataTypeId";
        else
            filter += " and d.dataTypeId > :dataTypeId";

        String where = "select d.name,d.hostname,d.port,d.username,d.password,d.lastPatchTimestamp,d.lastStatus,d.currentVersion," +
                "d.nextPatchTimestamp,d.targetVersion,r.name as region,a.zone,dt.type,d.regionId,d.availabilityZoneId,d.dataTypeId,d.id,d.patchId from DbEntity d "+
                "join RegionEntity r on r.id = d.regionId "+
                "join AvailabilityZoneEntity a on a.id = d.availabilityZoneId "+
                "join DataTypesEntity dt on dt.id = d.dataTypeId "+
                "where 1=1 "+filter+" order by d.regionId,d.availabilityZoneId,d.dataTypeId,d.name";

        EntityManager entityManager = PersistenceManager.INSTANCE.getemPatcher();

        List<Object[]> ent = null;

        ent = entityManager.createQuery(where)
                .setParameter("regionId", Integer.parseInt(regionId))
                .setParameter("availabilityZoneId", Integer.parseInt(availabilityZoneId))
                .setParameter("dataTypeId", Integer.parseInt(dataTypeId))
                .getResultList();

        entityManager.close();

        return ent;

    }

    public static void saveDatabase(DbEntity dbEntity) throws Exception {
        EntityManager entityManager = PersistenceManager.INSTANCE.getemPatcher();

        entityManager.getTransaction().begin();

        entityManager.merge(dbEntity);

        entityManager.getTransaction().commit();

        entityManager.close();
    }

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "region_id", nullable = true)
    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    @Basic
    @Column(name = "hostname", nullable = true, length = 45)
    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    @Basic
    @Column(name = "port", nullable = true, length = 5)
    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    @Basic
    @Column(name = "username", nullable = true, length = 20)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password", nullable = true, length = 20)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "last_patch_timestamp", nullable = true)
    public Timestamp getLastPatchTimestamp() {
        return lastPatchTimestamp;
    }

    public void setLastPatchTimestamp(Timestamp lastPatchTimestamp) {
        this.lastPatchTimestamp = lastPatchTimestamp;
    }

    @Basic
    @Column(name = "last_status", nullable = true)
    public Byte getLastStatus() {
        return lastStatus;
    }

    public void setLastStatus(Byte lastStatus) {
        this.lastStatus = lastStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DbEntity dbEntity = (DbEntity) o;

        if (id != dbEntity.id) return false;
        if (name != null ? !name.equals(dbEntity.name) : dbEntity.name != null) return false;
        if (regionId != null ? !regionId.equals(dbEntity.regionId) : dbEntity.regionId != null) return false;
        if (hostname != null ? !hostname.equals(dbEntity.hostname) : dbEntity.hostname != null) return false;
        if (port != null ? !port.equals(dbEntity.port) : dbEntity.port != null) return false;
        if (username != null ? !username.equals(dbEntity.username) : dbEntity.username != null) return false;
        if (password != null ? !password.equals(dbEntity.password) : dbEntity.password != null) return false;
        if (lastPatchTimestamp != null ? !lastPatchTimestamp.equals(dbEntity.lastPatchTimestamp) : dbEntity.lastPatchTimestamp != null)
            return false;
        if (lastStatus != null ? !lastStatus.equals(dbEntity.lastStatus) : dbEntity.lastStatus != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (regionId != null ? regionId.hashCode() : 0);
        result = 31 * result + (hostname != null ? hostname.hashCode() : 0);
        result = 31 * result + (port != null ? port.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (lastPatchTimestamp != null ? lastPatchTimestamp.hashCode() : 0);
        result = 31 * result + (lastStatus != null ? lastStatus.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "current_version", nullable = true, length = 25)
    public String getCurrentVersion() {
        return currentVersion;
    }

    public void setCurrentVersion(String currentVersion) {
        this.currentVersion = currentVersion;
    }

    @Basic
    @Column(name = "next_patch_timestamp", nullable = true)
    public Timestamp getNextPatchTimestamp() {
        return nextPatchTimestamp;
    }

    public void setNextPatchTimestamp(Timestamp nextPatchTimestamp) {
        this.nextPatchTimestamp = nextPatchTimestamp;
    }

    @Basic
    @Column(name = "target_version", nullable = true, length = 25)
    public String getTargetVersion() {
        return targetVersion;
    }

    public void setTargetVersion(String targetVersion) {
        this.targetVersion = targetVersion;
    }

    @Basic
    @Column(name = "data_type_id", nullable = true)
    public Integer getDataTypeId() {
        return dataTypeId;
    }

    public void setDataTypeId(Integer dataTypeId) {
        this.dataTypeId = dataTypeId;
    }

    @Basic
    @Column(name = "availability_zone_id", nullable = true)
    public Integer getAvailabilityZoneId() {
        return availabilityZoneId;
    }

    public void setAvailabilityZoneId(Integer availabilityZoneId) {
        this.availabilityZoneId = availabilityZoneId;
    }

    @Basic
    @Column(name = "patch_id", nullable = true)
    public Integer getPatchId() {
        return patchId;
    }

    public void setPatchId(Integer patchId) {
        this.patchId = patchId;
    }
}
