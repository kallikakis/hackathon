package com.doclerholding.hackaton.service;

import com.doclerholding.hackaton.data.model.Poi;
import com.doclerholding.hackaton.service.model.FilterCriteria;
import java.util.List;

import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.stereotype.Component;

/**
 * Created by nikolaos.kallikakis on 11/03/17.
 */
@Component
public class SearchServiceImpl implements SearchService {

	@Autowired
	private ElasticsearchTemplate template;

	@Override
	public List<FilterCriteria> getFilterCriteria() {
		// TODO: This needs to come from somewhere, DB??

		return null;
	}

	public List<Poi> getPoisWithin(double latitude, double longitude, String distance){

		CriteriaQuery searchQuery = new CriteriaQuery(new Criteria("location").within(new GeoPoint(latitude, longitude), distance));
		return template.queryForList(searchQuery, Poi.class);
	}

	public List<Poi> getPoisWithinWithFilters(double latitude, double longitude, String distance, List<FilterCriteria> filters){

		Criteria criteria = null;

		for(FilterCriteria filterCriteria: filters){
			if(criteria == null){
				criteria = new Criteria("type").is(filterCriteria.getFilterName());
			}else{
				criteria.and("type").is(filterCriteria.getFilterName());
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
