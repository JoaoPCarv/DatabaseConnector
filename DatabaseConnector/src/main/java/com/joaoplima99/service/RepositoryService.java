package com.joaoplima99.service;

import com.joaoplima99.manager.PropertiesManager;
import com.joaoplima99.manager.ResourceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public final class RepositoryService {

    /**
     * @deprecated (This class should not be instantiated)
     */
    @Deprecated(since = "1.0")
    private RepositoryService() {}

    public static final Logger LOG = LoggerFactory.getLogger(RepositoryService.class);

    public static Optional<EntityManager> startEntityManager(String persistenceUnitName) {
        Map<String, String> hibernatePropsMap = HibernateService.getHibernatePropertiesMap();
        if(hibernatePropsMap.isEmpty()) {
            LOG.error("Error setting up entity manager factory: Hibernate properties map was not loaded.");
            return Optional.empty();
        }
        EntityManager entityManager = Persistence.createEntityManagerFactory(persistenceUnitName,
                        hibernatePropsMap).createEntityManager();
        if (entityManager == null) {
            LOG.error("Error creating entity manager for the persistence unit '{}'.", persistenceUnitName);
            return Optional.empty();
        }
        return Optional.of(entityManager);
    }

    public static String getProjectPersistenceUnitName() {
        try {
            return PropertiesManager.getInstance().loadProperties(ResourceManager.getInstance()
                    .getResource("/project.properties"))
                    .getProperty("joaoplima99.project.persistence.unit.name");
        } catch (URISyntaxException | IOException ex) {
            LOG.error("Error loading project properties in which its persistence unit name is located at. " +
                    "Exception thrown: {}", ex.getMessage());
            return null;
        }
    }
}