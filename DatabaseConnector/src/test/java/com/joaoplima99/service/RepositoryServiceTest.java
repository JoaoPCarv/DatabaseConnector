package com.joaoplima99.service;

import com.joaoplima99.manager.ResourceManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockedStatic;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import static com.joaoplima99.service.RepositoryService.getProjectPersistenceUnitName;
import static com.joaoplima99.service.RepositoryService.startEntityManager;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RepositoryServiceTest {

    private final static String ACTUAL_PERSISTENCE_UNIT_NAME = "DatabaseConnector";
    private final static String TESTING_PERSISTENCE_UNIT_NAME = "persistenceUnitName";

    @Test
    public void assert_empty_optional_for_empty_Hibernate_props_map_on_startEntityManager() {
        try (MockedStatic<HibernateService> hibService = mockStatic(HibernateService.class)) {
            hibService.when(HibernateService::getHibernatePropertiesMap).thenReturn(Collections.emptyMap());
            assertEquals(Optional.empty(), startEntityManager(TESTING_PERSISTENCE_UNIT_NAME));
            hibService.clearInvocations();
        }
    }

    @Test
    public void assert_NPE_on_startEntityManager_for_fail_on_createEntityManagerFactory_with_non_empty_map() {
        Map<String, String> map = Collections.singletonMap("key", "value");
        try (MockedStatic<HibernateService> hibService = mockStatic(HibernateService.class);
             MockedStatic<Persistence> persistence = mockStatic(Persistence.class)) {
            hibService.when(HibernateService::getHibernatePropertiesMap).thenReturn(map);
            persistence.when(() -> Persistence.createEntityManagerFactory(anyString(), eq(map)))
                    .thenReturn(null);
            assertThrows(NullPointerException.class, () -> startEntityManager(TESTING_PERSISTENCE_UNIT_NAME));
            persistence.clearInvocations();
            hibService.clearInvocations();
        }
    }

    @Test
    public void assert_empty_optional_on_startEntityManager_for_fail_on_createEntityManager() {
        try (MockedStatic<Persistence> persistence = mockStatic(Persistence.class)) {
            EntityManagerFactory emf = mock(EntityManagerFactory.class);
            when(emf.createEntityManager()).thenReturn(null);
            persistence.when(() -> Persistence.createEntityManagerFactory(anyString(), any(Map.class)))
                    .thenReturn(emf);
            assertEquals(Optional.empty(), startEntityManager(TESTING_PERSISTENCE_UNIT_NAME));
            persistence.clearInvocations();
        }
    }

    @Test
    public void assert_PASS_for_valid_persistenceUnitName_on_startEntityManager() {
        Optional<EntityManager> entManagerOpt = startEntityManager(ACTUAL_PERSISTENCE_UNIT_NAME);
        assertTrue(entManagerOpt.isPresent());
        EntityManager entityManager = entManagerOpt.get();
        assertNotNull(entityManager);
        assertEquals(Boolean.TRUE, entityManager.isOpen());
        entityManager.clear();
        entityManager.close();
    }

    @Test
    public void assert_that_persistence_unit_name_is_retrieved_on_getProjectPersistenceUnitName() {
        assertEquals(ACTUAL_PERSISTENCE_UNIT_NAME, getProjectPersistenceUnitName());
    }

    @Test
    public void assert_NPE_for_unloaded_URL_resource_on_getProjectPersistenceUnitName() {
        ResourceManager resourceManager = mock(ResourceManager.class);
        when(resourceManager.getResource(anyString())).thenReturn(null);
        try (MockedStatic<ResourceManager> rsmMockedStatic = mockStatic(ResourceManager.class)) {
            rsmMockedStatic.when(ResourceManager::getInstance).thenReturn(resourceManager);
            assertThrows(NullPointerException.class, RepositoryService::getProjectPersistenceUnitName);
        }
    }
}