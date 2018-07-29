package org.mashup.takoyaki.common.type;

public enum RegionType {
    SEOUL(1, "seoul");

    private int regionCode;
    private String regionName;

    RegionType(int regionCode, String regionName) {
        this.regionCode = regionCode;
        this.regionName = regionName;
    }

    public int getRegionCode() {
        return this.regionCode;
    }

    public String getRegionName() {
        return this.regionName;
    }

}
