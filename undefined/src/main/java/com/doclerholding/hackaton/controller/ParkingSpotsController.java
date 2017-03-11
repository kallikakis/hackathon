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
public class ParkingSpotsController extends PublicDataDetailedController<ParkSpotDetail> {

	public ParkingSpotsController(Class<ParkSpotDetail> typeParameterClass) {
		super(typeParameterClass);
	}

	@Override
	protected ParkSpotDetail getDetailsById(@PathVariable String id) throws JsonProcessingException {
		return getDetailedData(id, "Occupancy/CarPark/{id}");
	}
}
