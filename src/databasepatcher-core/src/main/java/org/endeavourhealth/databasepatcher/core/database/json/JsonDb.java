package org.endeavourhealth.databasepatcher.core.database.json;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public final class JsonDb {

    private String id = null;
    private String name = null;
    private String hostname = null;
    private String port = null;
    private String username = null;
    private String password = null;
    private String lastPatchTimestamp = null;
    private String lastStatus = null;
    private String currentVersion = null;
    private String nextPatchTimestamp = null;
    private String targetVersion = null;
    private String region = null;
    private String zone = null;
    private String type = null;
    private String regionId = null;
    private String availabilityZoneId = null;
    private String dataTypeId = null;
    private String patchId = null;
    private String runNow = null;

    public JsonDb() {
    }

    /**
     * gets/sets
     */

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastPatchTimestamp() {
        return lastPatchTimestamp;
    }

    public void setLastPatchTimestamp(String lastPatchTimestamp) {
        this.lastPatchTimestamp = lastPatchTimestamp;
    }

    public String getLastStatus() {
        return lastStatus;
    }

    public void setLastStatus(String lastStatus) {
        this.lastStatus = lastStatus;
    }

    public String getCurrentVersion() {
        return currentVersion;
    }

    public void setCurrentVersion(String currentVersion) {
        this.currentVersion = currentVersion;
    }

    public String getNextPatchTimestamp() {
        return nextPatchTimestamp;
    }

    public void setNextPatchTimestamp(String nextPatchTimestamp) {
        this.nextPatchTimestamp = nextPatchTimestamp;
    }

    public String getTargetVersion() {
        return targetVersion;
    }

    public void setTargetVersion(String targetVersion) {
        this.targetVersion = targetVersion;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getAvailabilityZoneId() {
        return availabilityZoneId;
    }

    public void setAvailabilityZoneId(String availabilityZoneId) {
        this.availabilityZoneId = availabilityZoneId;
    }

    public String getDataTypeId() {
        return dataTypeId;
    }

    public void setDataTypeId(String dataTypeId) {
        this.dataTypeId = dataTypeId;
    }

    public String getPatchId() {
        return patchId;
    }

    public void setPatchId(String patchId) {
        this.patchId = patchId;
    }

    public String getRunNow() {
        return runNow;
    }

    public void setRunNow(String runNow) {
        this.runNow = runNow;
    }


}
