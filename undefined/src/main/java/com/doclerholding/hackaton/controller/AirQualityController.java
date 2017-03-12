package com.doclerholding.hackaton.controller;

import com.doclerholding.hackaton.data.model.airquality.AirQualityDetails;
import com.doclerholding.hackaton.service.DetailsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by claudiu.arba on 11/03/17.
 */
@Controller
@RequestMapping("/airquality")
public class AirQualityController {

	@Autowired
	private DetailsService detailsService;

	@RequestMapping("/details/{id}")
	public AirQualityDetails getDetailsById(@PathVariable String id) throws JsonProcessingException {
		return this.detailsService.getAirQualityDetail(id);
	}

	@RequestMapping(value = "/zones", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<String> getZones() throws JsonProcessingException {
		return this.detailsService.getAirQualityZones();
	}
}
