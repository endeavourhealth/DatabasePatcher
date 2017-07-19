package org.endeavourhealth.databasepatcher.core.database.json;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public final class JsonPatch {

    private String id = null;
    private String patchDescription = null;
    private String createdTimestamp = null;
    private String patchScript = null;

    public JsonPatch() {
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

    public String getPatchDescription() {
        return patchDescription;
    }

    public void setPatchDescription(String patchDescription) {
        this.patchDescription = patchDescription;
    }

    public String getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(String createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    public String getPatchScript() {
        return patchScript;
    }

    public void setPatchScript(String patchScript) {
        this.patchScript = patchScript;
    }


}
