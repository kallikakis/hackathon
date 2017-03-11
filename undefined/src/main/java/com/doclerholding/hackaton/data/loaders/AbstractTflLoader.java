package com.doclerholding.hackaton.data.loaders;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import com.doclerholding.hackaton.data.model.Poi;
import com.doclerholding.hackaton.data.repository.PoiRepository;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * Created by maxim on 3/11/2017.
 */
public abstract class AbstractTflLoader implements IDataType {

	@Autowired
	private PoiRepository poiRepository;

	protected void addPoint(JsonNode node, String pointType) {
		final List<Poi> data = new ArrayList<>();
		JsonNode features = node.path("features");
		for (JsonNode f : features) {
			Poi poi = new Poi();
			poi.setType(pointType);
			JsonNode geometry = f.path("geometry");
			if (geometry.path("type").asText().equals("Point")) {
				JsonNode coordinates = geometry.path("coordinates");
				poi.setLocation(new GeoPoint(coordinates.path(0).asDouble(), coordinates.path(1).asDouble()));
			}
			JsonNode props = f.path("properties");
			poi.setId(props.path("id").asText());
			poi.setName(props.path("name").asText());
			data.add(poi);
			this.poiRepository.index(poi);
		}
	}
}
