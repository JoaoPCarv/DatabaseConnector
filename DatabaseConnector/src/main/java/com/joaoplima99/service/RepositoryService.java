package com.joaoplima99.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public final class RepositoryService {

    //This class should not be instantiated.
    @Deprecated
    private RepositoryService() {}

    public static Logger LOG = LoggerFactory.getLogger(RepositoryService.class);

    public static EntityManager startEntityManager(String persistenceUnitName) {
        EntityManager entityManager = Persistence.createEntityManagerFactory(persistenceUnitName,
                        HibernateService.getHibernatePropertiesMap()).createEntityManager();
        if (entityManager == null) {
            LOG.error("Error creating entity manager for the persistence unit {}.", persistenceUnitName);
            return null;
        }
        return entityManager;
    }

    public static void main(String[] args) {}
}