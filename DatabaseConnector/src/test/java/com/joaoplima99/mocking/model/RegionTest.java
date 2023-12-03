package com.joaoplima99.mocking.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

import static com.joaoplima99.mocking.model.Region.of;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@RunWith(MockitoJUnitRunner.class)
public class RegionTest {

    @Test
    public void assert_each_Region_as_right_given_right_name_on_of() {
        Arrays.stream(Region.values()).forEach(region -> {
            Region tested = of(region.getName());
            assertEquals(region, tested);
        });
    }

    @Test
    public void assert_equals_BRAZIL_on_of() {
        setup_of("Brazil", Region.BRAZIL);
    }

    @Test
    public void assert_equals_USA_on_of() {
        setup_of("USA", Region.USA);
    }

    @Test
    public void assert_equals_CHINA_on_of() {
        setup_of("China", Region.CHINA);
    }

    @Test
    public void assert_throws_IllegalArgumentException_on_of() {
        String illegal = "N/A";
        String message = String.format("Region '%s' is not mapped.", illegal);
        assertThrows(message, IllegalArgumentException.class, () -> of(illegal));
    }

   private void setup_of(String regionName, Region region) {
        Region tested = of(regionName);
        assertEquals(region, tested);
   }
}