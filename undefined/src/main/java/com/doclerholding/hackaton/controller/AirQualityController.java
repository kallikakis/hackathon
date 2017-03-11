package com.doclerholding.hackaton.controller;

import com.doclerholding.hackaton.data.model.airquality.AirQualityDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

/**
 * Created by claudiu.arba on 11/03/17.
 */
@Controller
@RequestMapping("/airquality/details")
public class AirQualityController extends PublicDataDetailedController<AirQualityDetails> {

	@Autowired
	private RestTemplate restTemplate;

	public AirQualityController() {
		super(AirQualityDetails.class);
	}

	@RequestMapping("/{id}")
	public AirQualityDetails parkingSpotDetail(@PathVariable String id) throws JsonProcessingException {
		return getDetailedData(id, "Weather/Airquality/{id}");
	}
}
