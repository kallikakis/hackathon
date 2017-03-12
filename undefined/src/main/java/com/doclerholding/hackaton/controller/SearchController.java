package com.doclerholding.hackaton.controller;

import java.util.Arrays;
import java.util.List;

import com.doclerholding.hackaton.data.model.Poi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.doclerholding.hackaton.service.SearchService;

/**
 * Created by nikolaos.kallikakis on 11/03/17.
 */
@RestController
public class SearchController {

	@Autowired
	private SearchService searchService;

	@RequestMapping(path="/search/pois", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	List<Poi> getPois(@RequestParam(required = false) Integer distance, @RequestParam(required = false) String[] filters) {
		return this.searchService.getPois(Arrays.asList(filters));
	}
}
