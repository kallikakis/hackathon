package com.doclerholding.hackaton.controller;

import com.doclerholding.hackaton.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by nikolaos.kallikakis on 11/03/17.
 */
@Controller
public class SearchController {

	@Autowired
	private SearchService searchService;


}
