package com.doclerholding.hackaton.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.doclerholding.hackaton.service.IDataLoaderService;

@Controller
public class DataController {
	
	@Autowired
	private IDataLoaderService dataLoaderService;
	
	@RequestMapping(path="/data/load", method = RequestMethod.GET)
	@ResponseBody
	public String loadData(@RequestParam(name="forceDownload", required=false) Boolean forceDownload) {
		this.dataLoaderService.loadDataAsync(forceDownload != null ? forceDownload.booleanValue() : false);
		return "Started";
	}
}
