package com.doclerholding.hackaton.controller;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.doclerholding.hackaton.service.IAreaLocator;
import com.doclerholding.hackaton.service.model.AreaFilterCriteria;
import com.doclerholding.hackaton.service.model.LocatedArea;

@RestController
public class AreaLocatorController {
	@Autowired
	private IAreaLocator areaLocator;
	
	@RequestMapping(path="/areas", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<LocatedArea> locate(@RequestParam("types") String[] filterArray) {
		SortedSet<AreaFilterCriteria> filters = new TreeSet<>();
		for(int i = 0; i < filterArray.length; i++) {
			String[] f = filterArray[i].split(":");
			// TODO validate field name f[0] is existsing dataType
			// TODO validate f[1] is a valid numeric string
			filters.add(new AreaFilterCriteria(f[0], Double.valueOf(f[1]).doubleValue(), i));
		}
		return this.areaLocator.getAreas(filters);
	}
}
