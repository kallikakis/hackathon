package com.doclerholding.hackaton.service;

import com.doclerholding.hackaton.data.model.Poi;
import com.doclerholding.hackaton.service.model.FilterCriteria;
import java.util.List;

import org.springframework.data.elasticsearch.core.geo.GeoBox;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

/**
 * Created by nikolaos.kallikakis on 11/03/17.
 */
public interface SearchService {

	List<FilterCriteria> getFilterCriteria();
	String getPoiDescription(String address);
	List<Poi> getPois(GeoBox box, List<String> filters);
	List<Poi> getPois(GeoPoint origo, double radiusKm, List<String> filters);
}
