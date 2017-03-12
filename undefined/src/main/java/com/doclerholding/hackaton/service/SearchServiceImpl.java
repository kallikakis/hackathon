package com.doclerholding.hackaton.service;

import com.doclerholding.hackaton.data.loaders.IDataType;
import com.doclerholding.hackaton.data.model.Poi;
import com.doclerholding.hackaton.service.model.FilterCriteria;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.doclerholding.hackaton.util.CoordsUtil;
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

	public String getPoiDescription(String address){
		GeoPoint point = reverseGeocodeService.pointFromAddress(address);
		List<Poi> list= getPoisWithin(point.getLat(), point.getLon(), "5km");

		Map<String, Double> distanceMap = new HashMap<>();
		Map<String, String> nameMap = new HashMap<>();
		int parkingCount = 0;
		for(Poi p:list){
			if(p.getType().equals("school")){
				if(p.getLocation() != null){
					double dist = CoordsUtil.distFrom(point.getLat(), point.getLon(), p.getLocation().getLat(), p.getLocation().getLon());
					dist(dist, "school",p.getName() , distanceMap, nameMap);
				}
			}
			if(p.getType().equals("hospital")){
				if(p.getLocation() != null){
					double dist = CoordsUtil.distFrom(point.getLat(), point.getLon(), p.getLocation().getLat(), p.getLocation().getLon());
					dist(dist, "hospital",p.getName(), distanceMap, nameMap);
				}
			}
			if(p.getType().equals("parking")){
				parkingCount++;
				if(p.getLocation() != null){
					double dist = CoordsUtil.distFrom(point.getLat(), point.getLon(), p.getLocation().getLat(), p.getLocation().getLon());
					dist(dist, "parking",p.getName(), distanceMap, nameMap);
				}
			}
			if(p.getType().equals("playgound")){
				if(p.getLocation() != null){
					double dist = CoordsUtil.distFrom(point.getLat(), point.getLon(), p.getLocation().getLat(), p.getLocation().getLon());
					dist(dist, "playgound",p.getName(), distanceMap, nameMap);
				}
			}
			if(p.getType().equals("park")){
				if(p.getLocation() != null){
					double dist = CoordsUtil.distFrom(point.getLat(), point.getLon(), p.getLocation().getLat(), p.getLocation().getLon());
					dist(dist, "park",p.getName(), distanceMap, nameMap);
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		if(nameMap.containsKey("park")){
			sb.append("The closest public park is \""+nameMap.get("park")+"\" at a distance of "+distanceMap.get("park")+"m<br>");
		}
		if(nameMap.containsKey("school")){
			sb.append("The closest school is \""+nameMap.get("school")+"\" at a distance of "+distanceMap.get("school")+"m<br>");
		}
		if(nameMap.containsKey("playgound")){
			sb.append("The closest playgound is \""+nameMap.get("playgound")+"\" at a distance of "+distanceMap.get("playgound")+"m<br>");
		}
		if(nameMap.containsKey("hospital")){
			sb.append("The closest hospital is \""+nameMap.get("hospital")+"\" at a distance of "+distanceMap.get("hospital")+"m<br>");
		}
		if(nameMap.containsKey("parking")){
			sb.append("The closest parking spot is \""+nameMap.get("parking")+"\" at a distance of "+distanceMap.get("parking")+"m<br>");
		}
		sb.append("There are "+parkingCount+" parking spots in 5km.<br>");

		return sb.toString();
	}

	public List<Poi> getPoisWithin(double latitude, double longitude, String distance) {
		CriteriaQuery searchQuery = new CriteriaQuery(new Criteria("location").within(new GeoPoint(latitude, longitude), distance));
		searchQuery.setPageable(new PageRequest(0,MAX_RESULT));
		return template.queryForList(searchQuery, Poi.class);
	}

	public void dist(double dist, String type, String name, Map<String, Double> distanceMap, Map<String, String> nameMap){
		if(distanceMap.containsKey(type)){
			if(distanceMap.get(type) > dist){
				distanceMap.put(type, dist);
				nameMap.put(type, name);
			}
		}else{
			distanceMap.put(type, dist);
			nameMap.put(type, name);
		}
	}

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
