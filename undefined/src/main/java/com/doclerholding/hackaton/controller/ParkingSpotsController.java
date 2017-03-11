package com.doclerholding.hackaton.controller;

import com.doclerholding.hackaton.data.model.parking.ParkSpotDetail;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

/**
 * Created by claudiu.arba on 11/03/17.
 */
@Controller
@RequestMapping("/parking/detail")
public class ParkingSpotsController {

	@Autowired
	RestTemplate restTemplate;

	@RequestMapping("/{id}")
	public ParkSpotDetail parkingSpotDetail(@PathVariable String id) throws JsonProcessingException {

		JsonNode node = restTemplate.getForObject("https://api.tfl.lu/v1/Occupancy/CarPark/{id}", JsonNode.class, id);
		JsonNode props = node.path("properties");

		ObjectMapper mapper = new ObjectMapper();
		ParkSpotDetail spotDetail = mapper.treeToValue(props, ParkSpotDetail.class);

		return spotDetail;
	}
}
