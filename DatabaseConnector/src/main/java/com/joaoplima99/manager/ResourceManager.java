package com.joaoplima99.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.Objects;

public class ResourceManager {

    public static final Logger LOG = LoggerFactory.getLogger(ResourceManager.class);

    private static ResourceManager instance = null;

    private ResourceManager() {}

    public static ResourceManager getInstance() {
        if (instance == null) {
            instance = new ResourceManager();
        }
        return instance;
    }

    public URL getResource(String path) {
        return Objects.requireNonNull(ResourceManager.getInstance().getClass().getResource(path));
    }
}