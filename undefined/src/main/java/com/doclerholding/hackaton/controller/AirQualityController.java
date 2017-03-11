package com.doclerholding.hackaton.controller;

import com.doclerholding.hackaton.data.model.airquality.AirQualityDetails;
import com.doclerholding.hackaton.service.DetailsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by claudiu.arba on 11/03/17.
 */
@Controller
@RequestMapping("/airquality/details")
public class AirQualityController {

	@Autowired
	DetailsService detailsService;

	@RequestMapping("/{id}")
	public AirQualityDetails getDetailsById(@PathVariable String id) throws JsonProcessingException {
		return detailsService.getAirQualityDetail(id);
	}
}
