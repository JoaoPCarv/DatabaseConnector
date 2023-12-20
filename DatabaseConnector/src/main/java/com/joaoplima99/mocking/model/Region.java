package com.joaoplima99.mocking.model;

import com.google.common.base.MoreObjects;

import java.io.Serializable;
import java.util.Arrays;

public enum Region implements Serializable {

    BRAZIL("Brazil", 1, StatusOfRegion.ACTIVATED),
    USA("USA", 5, StatusOfRegion.ON_HOLD),
    CHINA("China", 4, StatusOfRegion.ACTIVATED);

    private final String name;
    private final int numOfClusters;
    private final StatusOfRegion status;

    Region(String name, int numOfClusters, StatusOfRegion status){
        this.name = name;
        this.numOfClusters = numOfClusters;
        this.status = status;
    }

    public static Region of(String regionName) {
        return Arrays.stream(Region.values()).filter(region -> region.getName().equals(regionName))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Region '" + regionName + "' is not mapped."));
    }

    public String getName() {
        return name;
    }

    public int getNumOfClusters() {
        return numOfClusters;
    }

    public StatusOfRegion getStatusOfRegion() {
        return status;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("Region name", name)
                .add("Number of clusters", numOfClusters)
                .add("Status of this region", status)
                .toString();
    }

    public enum StatusOfRegion {

        ACTIVATED("Activated"),
        DEACTIVATED("Deactivated"),
        ON_HOLD("On hold"),
        DEPRECATED("Deprecated"),
        TO_BE_BUILT("To be built");

        private final String statusStr;
        StatusOfRegion(String status) {
            this.statusStr = status;
        }

        public String getStatus() {
            return statusStr;
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                    .add("Value", this.getStatus())
                    .toString();
        }
    }
}