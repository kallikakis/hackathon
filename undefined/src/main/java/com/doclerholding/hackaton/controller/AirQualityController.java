package com.doclerholding.hackaton.controller;

import com.doclerholding.hackaton.data.model.airquality.AirQualityDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by claudiu.arba on 11/03/17.
 */
@Controller
@RequestMapping("/airquality/details")
public class AirQualityController extends PublicDataDetailedController<AirQualityDetails> {

	public AirQualityController() {
		super(AirQualityDetails.class);
	}

	@Override
	protected AirQualityDetails getDetailsById(@PathVariable String id) throws JsonProcessingException {
		return getDetailedData(id, "Weather/Airquality/{id}");
	}
}
