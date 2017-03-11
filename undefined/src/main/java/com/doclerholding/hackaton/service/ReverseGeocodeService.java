package com.doclerholding.hackaton.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by claudiu.arba on 11/03/17.
 */
@Component
public class ReverseGeocodeService {

	@Autowired
	RestTemplate restTemplate;
	
	public GeoPoint pointFromAddress(String address){
		JsonNode node = restTemplate.getForObject("http://nominatim.openstreetmap.org/search?format=json&q={query}", JsonNode.class, address);

		int i=0;
		double max = 0;
		int pos = 0;
		for(JsonNode n: node){
			double importance = n.path("importance").asDouble();
			if(importance > max){
				max = importance;
				pos = i;
			}
			i++;
		}
		JsonNode addr = node.path(pos);

		double lon = addr.path("lon").asDouble();
		double lat = addr.path("lat").asDouble();

		return new GeoPoint(lat, lon);
	}
}
