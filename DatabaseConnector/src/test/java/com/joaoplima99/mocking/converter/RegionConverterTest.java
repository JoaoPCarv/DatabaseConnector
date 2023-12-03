package com.joaoplima99.mocking.converter;

import com.joaoplima99.mocking.model.Region;
import com.joaoplima99.utils.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.joaoplima99.mocking.converter.RegionConverter.TAG;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class RegionConverterTest {

    private static Logger LOG = LoggerFactory.getLogger(RegionConverter.class);

    private RegionConverter regionConverter;
    private static Map<String, String> tags;

    static {
        StringBuilder sBuilder = new StringBuilder();
        tags = new HashMap<>();
        Arrays.stream(Region.values()).forEach(region -> {
            StringUtils.emptyStringBuilder(sBuilder);
            sBuilder.append(TAG);
            sBuilder.append(region.getName());
            tags.put(region.getName(), sBuilder.toString());
        });
    }

    @Before
    public void setup() {
        regionConverter = new RegionConverter();
    }

    @Test
    public void assert_null_on_convertToDatabaseColumn() {
        assertNull(regionConverter.convertToDatabaseColumn(null));
    };

    @Test
    public void assert_PASS_for_each_Region_on_convertToDatabaseColumn() {
        Arrays.stream(Region.values()).forEach(region ->
                assertEquals(tags.get(region.getName()), regionConverter.convertToDatabaseColumn(region)));
    }

    @Test
    public void assert_null_for_null_s_on_convertToEntityAttribute() {
        assertNull(regionConverter.convertToEntityAttribute(null));
    }

    @Test
    public void assert_null_for_empty_s_on_convertToEntityAttribute() {
        assertNull(regionConverter.convertToEntityAttribute(""));
    }

    @Test
    public void assert_null_for_untagged_s_on_convertToEntityAttribute() {
        assertNull(regionConverter.convertToEntityAttribute(Region.BRAZIL.toString()));
    }

    @Test
    public void assert_PASS_for_each_for_each_tagged_Region_on_convertToEntityAttribute() {
        tags.forEach((regionName, tag) -> {
            assertEquals(Region.of(regionName), regionConverter.convertToEntityAttribute(tag));
        });
    }
}