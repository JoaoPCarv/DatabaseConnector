package com.joaoplima99.service;

import com.joaoplima99.manager.PropertiesManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

public final class HibernateService {

    /**
     * @deprecated (This class should not be instantiated)
     */
    @Deprecated(since = "1.0")
    private HibernateService() {}

    public static final Logger LOG = LoggerFactory.getLogger(HibernateService.class);

    public static Optional<Properties> getHibernateProperties() {
        try {
            return PropertiesManager.getInstance()
                    .loadOptionalPropertiesFromResourcePath("/persistence.properties");
        } catch (URISyntaxException | IOException e) {
            LOG.error("Error while loading Hibernate properties from resource file." +
                    "Exception thrown: {}.", e.getMessage());
            return Optional.empty();
        }
    }

    public static Map<String, String> getHibernatePropertiesMap() {
        Optional<Properties> optProp = getHibernateProperties();
        if (optProp.isPresent()) {
            return PropertiesManager.getInstance().getPropertiesMap(optProp.get());
        }
        return Collections.emptyMap();
    }
}