package com.doclerholding.hackaton.controller;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.geo.GeoBox;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.doclerholding.hackaton.data.model.Poi;
import com.doclerholding.hackaton.exception.RestNotFoundException;
import com.doclerholding.hackaton.service.ReverseGeocodeService;
import com.doclerholding.hackaton.service.SearchService;

/**
 * Created by nikolaos.kallikakis on 11/03/17.
 */
@RestController
public class SearchController {

	@Autowired
	private SearchService searchService;

	@Autowired
	private ReverseGeocodeService reverseGeocodeService;

	@RequestMapping(path="/search/pois/boundedbox", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	List<Poi> getPois(@RequestParam double tlLat, @RequestParam double tlLon, @RequestParam double brLat, @RequestParam double brLon, @RequestParam String[] filters) {
		GeoBox box = new GeoBox(new GeoPoint(tlLat, tlLon), new GeoPoint(brLat, brLon));
		return this.searchService.getPois(box, Arrays.asList(filters));
	}

	@RequestMapping(path="/search/description/address", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public @ResponseBody
	String getAddressShortDescription(@RequestParam String address) throws RestNotFoundException{
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode node = mapper.createObjectNode();

		GeoPoint point = reverseGeocodeService.pointFromAddress(address);
		if (point == null) {
			throw new RestNotFoundException("Address not found");
		}
		String description = this.searchService.getPoiDescription(point);
		node.put("description", description);
		JsonNode coords = mapper.valueToTree(point);
		node.put("coords" ,coords);
		try {
			return mapper.writeValueAsString(node);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return "";
	}

	@RequestMapping(path="/search/pois/address", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	List<Poi> getPois(@RequestParam String address, @RequestParam double distanceKm, @RequestParam String[] filters) throws RestNotFoundException {
		GeoPoint addressPoint = reverseGeocodeService.pointFromAddress(address);
		if (addressPoint == null) {
			throw new RestNotFoundException("Address not found");
		}
		return this.searchService.getPois(addressPoint, distanceKm, Arrays.asList(filters));
	}

	@RequestMapping(path="/search/pois/pointinmap", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	List<Poi> getPois(@RequestParam double lat, @RequestParam double lon, @RequestParam double distanceKm, @RequestParam String[] filters) {
		GeoPoint addressPoint = new GeoPoint(lat, lon);
		return this.searchService.getPois(addressPoint, distanceKm, Arrays.asList(filters));
	}
}
