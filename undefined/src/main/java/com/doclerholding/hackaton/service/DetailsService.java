package com.doclerholding.hackaton.service;

import com.doclerholding.hackaton.data.model.airquality.AirQualityDetails;
import com.doclerholding.hackaton.data.model.parking.ParkSpotDetail;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by claudiu.arba on 11/03/17.
 */
@Component
public class DetailsService {
	private final String PUBLIC_DATA_BASE_URL = "https://api.tfl.lu/v1/";

	@Autowired
	private RestTemplate restTemplate;

	public ParkSpotDetail getParkingDetail(String id) {
		JsonNode node = restTemplate.getForObject(PUBLIC_DATA_BASE_URL + "Occupancy/CarPark/{id}", JsonNode.class, id);
		JsonNode props = node.path("properties");
		ObjectMapper mapper = new ObjectMapper();
		ParkSpotDetail result = null;
		try {
			result = mapper.treeToValue(props, ParkSpotDetail.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return result;
	}

	public AirQualityDetails getAirQualityDetail(String id) {
		JsonNode node = restTemplate.getForObject(PUBLIC_DATA_BASE_URL + "Weather/Airquality/{id}", JsonNode.class, id);
		JsonNode props = node.path("properties");
		ObjectMapper mapper = new ObjectMapper();
		AirQualityDetails result = null;
		try {
			result = mapper.treeToValue(props, AirQualityDetails.class);
			result.generateIndex();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return result;
	}
}
