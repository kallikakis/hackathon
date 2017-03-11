package com.doclerholding.hackaton.data.loaders;

import com.doclerholding.hackaton.data.model.Poi;
import com.doclerholding.hackaton.data.repository.PoiRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by claudiu.arba on 11/03/17.
 */
@Component
public class LineLoader {

    @Autowired
    PoiRepository repository;

    @PostConstruct
    protected void loadData() throws URISyntaxException, IOException {
        URL dirURL = Thread.currentThread().getContextClassLoader().getResource("stops.json");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(new File(dirURL.toURI()));

        List<Poi> data = new ArrayList<>();

        JsonNode features = rootNode.path("features");
        for(JsonNode f: features){
            Poi model = new Poi();
            JsonNode geometry = f.path("geometry");
            if(geometry.path("type").asText().equals("Point")){
                JsonNode coordinates = geometry.path("coordinates");
                JsonNode latNode = coordinates.path(0);
                JsonNode lonNode = coordinates.path(1);
                double lat = latNode.asDouble();
                double lon = lonNode.asDouble();
                GeoPoint g = new GeoPoint(lat, lon);
                model.setLocation(g);
            }
            JsonNode props = f.path("properties");
            //Model props
            JsonNode id = props.path("id");
            JsonNode name = props.path("name");
            model.setId(id.asText());
            model.setName(name.asText());
            model.setType("stop");

            //repository.index(model);
            data.add(model);
        }

      
    }
}
