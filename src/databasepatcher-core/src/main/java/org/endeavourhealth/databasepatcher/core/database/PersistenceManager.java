package org.endeavourhealth.databasepatcher.core.database;

import com.fasterxml.jackson.databind.JsonNode;
import org.endeavourhealth.common.config.ConfigManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public enum PersistenceManager {
    INSTANCE;
	private static final Logger LOG = LoggerFactory.getLogger(PersistenceManager.class);

    private EntityManagerFactory emPatcher;

    private PersistenceManager() {

			Map<String, Object> override = getHibernateOverridesFromConfig("patch_database");
			emPatcher = Persistence.createEntityManagerFactory("patch_database", override);

		}

	public EntityManager getemPatcher() {
        return emPatcher.createEntityManager();
    }

    public void close() {
        emPatcher.close();

    }

	private Map<String, Object> getHibernateOverridesFromConfig(String configId) {
		Map<String, Object> override = new HashMap<>();

		try {
			JsonNode config = ConfigManager.getConfigurationAsJson(configId);

			if (config.has("driverClass"))
				override.put("hibernate.connection.driver_class", config.get("driverClass").asText());

			if (config.has("patcher_url"))
				override.put("hibernate.connection.url", config.get("patcher_url").asText());

			if (config.has("patcher_username"))
				override.put("hibernate.connection.username", config.get("patcher_username").asText());

			if (config.has("patcher_password"))
				override.put("hibernate.connection.password", config.get("patcher_password").asText());

		} catch (Exception e) {
			LOG.warn("Error loading config ["+configId+"]", e);
		}
		return override;
	}
}
