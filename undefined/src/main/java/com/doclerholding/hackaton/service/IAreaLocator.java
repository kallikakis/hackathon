package com.doclerholding.hackaton.service;

import java.util.List;
import java.util.SortedSet;

import com.doclerholding.hackaton.service.model.AreaFilterCriteria;
import com.doclerholding.hackaton.service.model.LocatedArea;

public interface IAreaLocator {
	List<LocatedArea> getAreas(SortedSet<AreaFilterCriteria> filters);
}
