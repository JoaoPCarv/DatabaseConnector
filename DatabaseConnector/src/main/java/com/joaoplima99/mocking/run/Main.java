package com.joaoplima99.mocking.run;

import com.joaoplima99.mocking.model.MockingBean;
import com.joaoplima99.mocking.model.Region;
import com.joaoplima99.service.RepositoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;

public class Main {

    public static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args)  {
        MockingBean mockingBean = MockingBean.newMockingBean().withName("Joao").withRegion(Region.BRAZIL);
        EntityManager entityManager = RepositoryService.startEntityManager("DatabaseConnector");
        entityManager.getTransaction().begin();
        entityManager.persist(mockingBean);
        entityManager.getTransaction().commit();
        entityManager.close();


    }
}