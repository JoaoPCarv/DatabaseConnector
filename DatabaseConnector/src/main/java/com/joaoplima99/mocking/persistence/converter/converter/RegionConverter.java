package com.joaoplima99.mocking.persistence.converter.converter;

import com.joaoplima99.mocking.model.Region;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class RegionConverter implements AttributeConverter<Region, String> {

    public static final String TAG = "REGION:";

    public static final Logger LOG = LoggerFactory.getLogger(RegionConverter.class);

    @Override
    public String convertToDatabaseColumn(Region region) {
        if (region == null) {
            LOG.error("The region passed to the method RegionConverter.convertToDatabaseColumn is null.");
            return null;
        }
        return TAG + region.getName();
    }

    @Override
    public Region convertToEntityAttribute(String s) {
        if (s == null || s.isEmpty()) {
            String msg = (s == null) ? "null" : "empty";
            LOG.error("The string passed to the method RegionConverter.convertToEntityAttribute is {}.", msg);
            return null;
        }
        int indexOf = s.indexOf(TAG);
        if (indexOf == -1) {
            LOG.error("The string passed is not tagged according to the pattern specified.");
            return null;
        }
        return Region.of(s.substring(indexOf + TAG.length()));
    }
}