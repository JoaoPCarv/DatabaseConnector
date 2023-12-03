package com.joaoplima99.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockedStatic;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Map;

import static com.joaoplima99.service.RepositoryService.startEntityManager;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RepositoryServiceTest {

    private final String PERSISTENCE_UNIT_NAME = "DatabaseConnector";

    private MockedStatic<Persistence> persistence;

    @Test
    public void assert_NPE_on_startEntityManager_for_fail_on_createEntityManagerFactory() {
        MockedStatic<Persistence> persistence = mockStatic(Persistence.class);
        persistence.when(() -> Persistence.createEntityManagerFactory(anyString(), any(Map.class)))
                .thenReturn(nullable(EntityManagerFactory.class));
       assertThrows(NullPointerException.class, () -> startEntityManager(eq("persistenceUnitName")));
       persistence.clearInvocations();
       persistence.close();
    }

    @Test
    public void assert_null_on_startEntityManager_for_fail_on_createEntityManager() {
        MockedStatic<Persistence> persistence = mockStatic(Persistence.class);
        EntityManagerFactory emf = mock(EntityManagerFactory.class);
        when(emf.createEntityManager()).thenReturn(null);
        persistence.when(() -> Persistence.createEntityManagerFactory(anyString(), any(Map.class)))
                .thenReturn(emf);
        assertNull(startEntityManager("persistenceUnitName"));
        persistence.clearInvocations();
        persistence.close();
    }

    @Test
    public void assert_PASS_for_valid_persistenceUnitName_on_startEntityManager() {
        EntityManager entityManager = startEntityManager(PERSISTENCE_UNIT_NAME);
        assertNotNull(entityManager);
        assertEquals(Boolean.TRUE, entityManager.isOpen());
        entityManager.clear();
        entityManager.close();
    }
}