package com.doclerholding.hackaton.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by nikolaos.kallikakis on 11/03/17.
 */
@Controller
public class IndexController {

	@RequestMapping("/")
	public String index(){
		return "index";
	}
}
