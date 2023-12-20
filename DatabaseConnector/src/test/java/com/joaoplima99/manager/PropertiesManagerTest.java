package com.joaoplima99.manager;

import com.joaoplima99.exception.PropertyNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PropertiesManagerTest {

    @Spy
    PropertiesManager propertiesManager;

    private static final Map<String, String> TESTING_PROPERTIES = Map.of(
            "testing.property.one",   "property-one",
            "testing.property.two",   "property-two",
            "testing.property.three", "property-three"
    );

    @Test
    public void assert_invalid_resource_path_on_loadPropertiesFromResourcePath() throws URISyntaxException, IOException {
        assertFalse(propertiesManager.loadPropertiesFromResourcePath("/invalid-resource-path.dot").isPresent());
    }

    @Test
    public void assert_valid_resource_path_on_loadPropertiesFromResourcePath() throws URISyntaxException, IOException {
        Optional<Properties> opProp = propertiesManager.loadPropertiesFromResourcePath("/testing/testing-supporting-files/testing.properties");
        assertTrue(opProp.isPresent());
        Properties properties = opProp.get();
        verify(propertiesManager, times(1))
                .loadProperties(ResourceManager.getInstance().getResource("/testing/testing-supporting-files/testing.properties").toURI());
        properties.keySet().forEach(key -> assertTrue(TESTING_PROPERTIES.containsKey(key)));
        properties.entrySet().forEach(entry -> assertTrue(TESTING_PROPERTIES.entrySet().contains(entry)));
    }

    @Test
    public void assert_throws_PropertyNotFoundException_on_getProperty() {
        assertThrows(PropertyNotFoundException.class, () -> {
            propertiesManager.getProperty(getActualTestingProperties(), "testing.property.N/A");
        });
    }

    @Test
    public void assert_getting_all_testing_properties_on_getProperty() {
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
        TESTING_PROPERTIES.forEach(properties::setProperty);
        return properties;
    }
}