package com.doclerholding.hackaton.service;

import com.doclerholding.hackaton.data.model.Poi;
import com.doclerholding.hackaton.service.model.FilterCriteria;
import java.util.List;

/**
 * Created by nikolaos.kallikakis on 11/03/17.
 */
public interface SearchService {

	List<FilterCriteria> getFilterCriteria();
	List<Poi> getPois(List<String> filters);
	List<Poi> getPoisWithin(double latitude, double longitude, String distance);
	List<Poi> getPoisWithinWithFilters(double latitude, double longitude, String distance, List<String> filters);
}
