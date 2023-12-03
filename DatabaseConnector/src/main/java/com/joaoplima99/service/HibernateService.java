package com.joaoplima99.service;

import com.joaoplima99.manager.PropertiesManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Properties;

public final class HibernateService {

    //This class should not be instantiated.
    @Deprecated(since = "1.0")
    private HibernateService() {}

    public static Logger LOG = LoggerFactory.getLogger(HibernateService.class);

    public static Properties getHibernateProperties() {
        try {
            return PropertiesManager.getInstance().loadPropertiesFromResourcePath("/persistence.properties");
        } catch (URISyntaxException | IOException e) {
            LOG.error("Error while loading Hibernate properties from resource file." +
                    "Exception thrown: {}.", e.getMessage());
            return null;
        }
    }

    public static Map<String, String> getHibernatePropertiesMap() {
        return PropertiesManager.getInstance().getPropertiesMap(getHibernateProperties());
    }
}