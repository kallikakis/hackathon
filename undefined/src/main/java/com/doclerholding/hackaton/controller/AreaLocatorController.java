package com.doclerholding.hackaton.controller;

import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.doclerholding.hackaton.data.loaders.IDataType;
import com.doclerholding.hackaton.exception.RestBadRequestException;
import com.doclerholding.hackaton.service.IAreaLocator;
import com.doclerholding.hackaton.service.model.AreaFilterCriteria;
import com.doclerholding.hackaton.service.model.LocatedArea;

@RestController
public class AreaLocatorController {
	@Autowired
	private IAreaLocator areaLocator;
	
	@Autowired
	private Map<String,IDataType> dataTypeMap;
	
	@RequestMapping(path="/areas", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<LocatedArea> locate(@RequestParam("types") String[] filterArray) throws RestBadRequestException {
		SortedSet<AreaFilterCriteria> filters = new TreeSet<>();
		for(int i = 0; i < filterArray.length; i++) {
			String[] f = filterArray[i].split(":");
			if (f.length != 2 || !this.dataTypeMap.containsKey(f[0]) || !this.dataTypeMap.get(f[0]).distanceFilter() || !StringUtils.isNumeric(f[1])) {
				throw new RestBadRequestException("Wrong input data: "+String.join("; ", filterArray));
			}
			filters.add(new AreaFilterCriteria(f[0], Math.abs(Double.valueOf(f[1]).doubleValue()), i));
		}
		return this.areaLocator.getAreas(filters);
	}
}
