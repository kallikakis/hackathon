package com.doclerholding.hackaton.controller;

import com.doclerholding.hackaton.data.model.parking.ParkSpotDetail;
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
@RequestMapping("/parking/detail")
public class ParkingSpotsController {

	@Autowired
	DetailsService detailsService;

	@RequestMapping("/{id}")
	protected ParkSpotDetail getDetailsById(@PathVariable String id) throws JsonProcessingException {
		return detailsService.getParkingDetail(id);
	}
}
