package com.doclerholding.hackaton.service;

import com.doclerholding.hackaton.data.loaders.IDataType;
import com.doclerholding.hackaton.data.model.Poi;
import com.doclerholding.hackaton.service.model.FilterCriteria;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.stereotype.Component;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;

/**
 * Created by nikolaos.kallikakis on 11/03/17.
 */
@Component
public class SearchServiceImpl implements SearchService {

	@Autowired
	private ElasticsearchTemplate template;
	
	@Autowired
	private List<IDataType> dataTypes;

	@Override
	public List<FilterCriteria> getFilterCriteria() {
		return this.dataTypes.stream().filter(x -> x.distanceFilter()).map(x -> new FilterCriteria(x.dataType())).collect(Collectors.toList());
	}

	@Override
	public List<Poi> getPois(List<String> filters) {
		Criteria criteria = new Criteria("type").in(filters);

		CriteriaQuery query = new CriteriaQuery(criteria);
		query.setPageable(new PageRequest(0, 500));
		return template.queryForList(query, Poi.class);

	}

	public List<Poi> getPoisWithin(double latitude, double longitude, String distance){

		CriteriaQuery searchQuery = new CriteriaQuery(new Criteria("location").within(new GeoPoint(latitude, longitude), distance));
		return template.queryForList(searchQuery, Poi.class);
	}

	public List<Poi> getPoisWithinWithFilters(double latitude, double longitude, String distance, List<String> filters){

		Criteria criteria = null;

		for(String filterName: filters){
			if(criteria == null){
				criteria = new Criteria("type").is(filterName);
			}else{
				criteria.and("type").is(filterName);
			}
		}

		if(criteria == null){
			criteria = new Criteria("location").within(new GeoPoint(latitude, longitude), distance);
		}else{
			criteria.and("location").within(new GeoPoint(latitude, longitude), distance);
		}

		return template.queryForList(new CriteriaQuery(criteria), Poi.class);
	}


}
