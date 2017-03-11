package com.doclerholding.hackaton.data.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

/**
 * Created by claudiu.arba on 11/03/17.
 */
@Document(indexName = "pois", type = "poi", shards = 1, replicas = 0, refreshInterval = "-1")
public class Poi {

    @Id
    private String id;

    private String name;

    private String type;

    private GeoPoint location;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public GeoPoint getLocation() {
        return location;
    }

    public void setLocation(GeoPoint location) {
        this.location = location;
    }
}
