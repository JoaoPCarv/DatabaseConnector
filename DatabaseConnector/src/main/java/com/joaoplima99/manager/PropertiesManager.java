package com.joaoplima99.manager;

import com.joaoplima99.exception.PropertyNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class PropertiesManager {

    public static final Logger LOG = LoggerFactory.getLogger(PropertiesManager.class);

    private static PropertiesManager instance = null;

    private PropertiesManager() {}

    public static PropertiesManager getInstance() {
        if (instance == null) {
            instance = new PropertiesManager();
        }
        return instance;
    }

    public Properties loadProperties(URI uri) throws IOException {
        File file = new File(uri);
        Properties properties = new Properties();
        try (BufferedReader bf = new BufferedReader(new FileReader(file))){
            properties.load(bf);
        }
        return properties;
    }

    public Properties loadProperties(URL url) throws URISyntaxException, IOException {
        return loadProperties(url.toURI());
    }

    public Optional<Properties> loadPropertiesFromResourcePath(String resourcePath) throws URISyntaxException, IOException {
        if (this.getClass().getResource(resourcePath) == null) {
            LOG.warn("Invalid resource path name.");
            return Optional.empty();
        }
        return Optional.of(loadProperties(Objects.requireNonNull(this.getClass().getResource(resourcePath)).toURI()));
    }

    public Optional<Properties> loadOptionalPropertiesFromResourcePath(String resourcePath) throws URISyntaxException, IOException {
        if (this.getClass().getResource(resourcePath) == null) {
            LOG.warn("Invalid resource path name.");
            return Optional.empty();
        }
        return Optional.ofNullable(loadProperties(Objects.requireNonNull(this.getClass().getResource(resourcePath)).toURI()));
    }

    public String getProperty(Properties properties, String key) throws PropertyNotFoundException {
        String value = properties.getProperty(key);
        if(value == null) throw new PropertyNotFoundException(key);
        return value;
    }

    public void setProperty(Properties properties, String key, String value, String path) throws IOException {
        Files.write(Paths.get(path), (key + " = " + value).getBytes(), StandardOpenOption.APPEND);
        properties.setProperty(key, value);
    }

    public Map<String, String> getPropertiesMap(Properties properties) {
        Map<String, String> propMap = new LinkedHashMap<>();

        Enumeration enProp = properties.propertyNames();
        String key;

        do {
            key = (String) enProp.nextElement();
            propMap.put(key, properties.getProperty(key));
        } while(enProp.hasMoreElements());

        return propMap;
    }

    public static void main(String[] args) throws URISyntaxException, IOException {
        PropertiesManager.getInstance().loadOptionalPropertiesFromResourcePath("/testing/testing-supporting-files/testing.properties")
                .orElseThrow(IllegalArgumentException::new).forEach((key, value) -> {
                    LOG.info("key: " + key + ", value: " + value);
                });
    }
}