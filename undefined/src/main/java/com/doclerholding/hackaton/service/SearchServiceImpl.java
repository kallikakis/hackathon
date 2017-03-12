package com.doclerholding.hackaton.service;

import com.doclerholding.hackaton.data.loaders.IDataType;
import com.doclerholding.hackaton.data.model.Poi;
import com.doclerholding.hackaton.service.model.FilterCriteria;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.geo.GeoBox;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.stereotype.Component;

import com.doclerholding.hackaton.data.loaders.IDataType;
import com.doclerholding.hackaton.data.model.Poi;
import com.doclerholding.hackaton.service.model.FilterCriteria;

/**
 * Created by nikolaos.kallikakis on 11/03/17.
 */
@Component
public class SearchServiceImpl implements SearchService {
	private final static int MAX_RESULT = 500;

	@Autowired
	private ElasticsearchTemplate template;

	@Autowired
	ReverseGeocodeService reverseGeocodeService;
	
	@Autowired
	private List<IDataType> dataTypes;

	@Override
	public List<FilterCriteria> getFilterCriteria() {
		return this.dataTypes.stream().filter(x -> x.distanceFilter()).map(x -> new FilterCriteria(x.dataType())).collect(Collectors.toList());
	}

	private List<Poi> getResult(Criteria criteria, List<String> filters) {
		CriteriaQuery query = new CriteriaQuery(criteria);
		criteria.and(new Criteria("type").in(filters));
		query.setPageable(new PageRequest(0, MAX_RESULT));
		return template.queryForList(query, Poi.class);
	}

	@Override
	public List<Poi> getPois(GeoBox box, List<String> filters) {
		Criteria criteria = new Criteria("location").boundedBy(box);
		return getResult(criteria, filters);
	}

	@Override
	public List<Poi> getPois(GeoPoint origo, double radiusKm, List<String> filters){
		Criteria criteria = new Criteria("location").within(origo, String.valueOf(radiusKm)+"km");
		return getResult(criteria, filters);
	}


}
