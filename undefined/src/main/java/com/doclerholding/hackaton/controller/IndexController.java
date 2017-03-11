package com.doclerholding.hackaton.controller;

import com.doclerholding.hackaton.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by nikolaos.kallikakis on 11/03/17.
 */
@Controller
public class IndexController {

	@Autowired
	private SearchService searchService;

	@RequestMapping("/")
	public String index(ModelMap modelMap) {
		modelMap.addAttribute("filteringCriteria", searchService.getFilterCriteria());

		return "index";
	}
}
