package com.doclerholding.hackaton.data.loaders;

import com.doclerholding.hackaton.data.model.AirQualityModel;
import com.doclerholding.hackaton.data.model.Poi;
import com.doclerholding.hackaton.data.repository.PoiRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by maxim on 3/11/2017.
 */
@Component
public class AirQualityLineLoader {

	private static Logger logger = LoggerFactory.getLogger(AirQualityLineLoader.class);

	@Autowired
	private PoiRepository poiRepository;

	@PostConstruct
	public void loadAirQualityLine() throws URISyntaxException, IOException {
		final ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode = mapper.readTree(new File(Thread.currentThread().getContextClassLoader().getResource("air_quality.json").toURI()));
		List<Poi> data = new ArrayList<>();
		JsonNode features = rootNode.path("features");
		for (JsonNode f : features) {
			AirQualityModel airQualityModel = new AirQualityModel();
			airQualityModel.setType("air_quality");
			JsonNode geometry = f.path("geometry");
			if (geometry.path("type").asText().equals("Point")) {
				JsonNode coordinates = geometry.path("coordinates");
				airQualityModel.setLocation(new GeoPoint(coordinates.path(0).asDouble(), coordinates.path(1).asDouble()));
			}
			JsonNode props = f.path("properties");
			airQualityModel.setId(props.path("id").asText());
			airQualityModel.setName(props.path("name").asText());
			data.add(airQualityModel);
		}
	}

}
