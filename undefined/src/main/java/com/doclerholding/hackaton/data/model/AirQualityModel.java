package com.doclerholding.hackaton.data.model;

import org.springframework.data.elasticsearch.core.geo.GeoPoint;

/**
 * Created by maxim on 3/11/2017.
 */

public class AirQualityModel extends Poi {
    public AirQualityModel() {
    }

    public AirQualityModel(String id, String name, String type, GeoPoint location) {
        super(id, name, type, location);
    }
}
