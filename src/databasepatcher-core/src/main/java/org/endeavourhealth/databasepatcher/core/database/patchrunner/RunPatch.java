package org.endeavourhealth.databasepatcher.core.database.patchrunner;

import com.fasterxml.jackson.databind.JsonNode;
import org.endeavourhealth.common.config.ConfigManager;
import org.endeavourhealth.databasepatcher.core.database.json.JsonDb;

import java.sql.DriverManager;
import java.util.Map;


public class RunPatch {

    public void runPatch(JsonDb database, String patchScript) {

        try {
            String className = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://"+database.getHostname()+":"+database.getPort()+"/"+database.getName();
            String username = database.getUsername();
            String password = database.getPassword();
            Class.forName(className);
            new ScriptRunner(DriverManager.getConnection(
                    url, username, password))
                    .runScript(patchScript);
        } catch (Exception e) {
            System.err.println(e);
        }
    }


}
