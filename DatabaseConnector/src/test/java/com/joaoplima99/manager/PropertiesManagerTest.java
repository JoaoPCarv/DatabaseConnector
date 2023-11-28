package com.joaoplima99.manager;

import com.joaoplima99.exception.PropertyNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PropertiesManagerTest {

    @Spy
    PropertiesManager propertiesManager;

    private static Map<String, String> TESTING_PROPERTIES = Map.of(
            "testing.property.one",   "property-one",
            "testing.property.two",   "property-two",
            "testing.property.three", "property-three"
    );

    @Test
    public void validateInvalidResourcePath() throws URISyntaxException, IOException {
        assertNull(propertiesManager.loadPropertiesFromResourcePath("/invalid-resource-path.dot"));
    }

    @Test
    public void validateValidResourcePath() throws URISyntaxException, IOException {
        Properties properties = propertiesManager.loadPropertiesFromResourcePath("/testing.properties");
        verify(propertiesManager, times(1))
                .loadProperties(getClass().getResource("/testing.properties").toURI());
        properties.keySet().forEach(key -> assertTrue(TESTING_PROPERTIES.keySet().contains(key)));
        properties.entrySet().forEach(entry -> assertTrue(TESTING_PROPERTIES.entrySet().contains(entry)));
    }

    @Test
    public void validateThrowsPropertyNotFoundException() {
        assertThrows(PropertyNotFoundException.class, () -> {
            propertiesManager.getProperty(getActualTestingProperties(), "testing.property.N/A");
        });
    }

    @Test
    public void validateGetAllTestingProperties() {
        Properties properties = getActualTestingProperties();
        properties.keySet().forEach(key -> {
            try {
                assertEquals(properties.getProperty(String.valueOf(key)),
                        propertiesManager.getProperty(properties, String.valueOf(key)));
            } catch (PropertyNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private Properties getActualTestingProperties() {
        Properties properties = new Properties();
        TESTING_PROPERTIES.forEach((key, value) -> properties.setProperty(key, value));
        return properties;
    }
}