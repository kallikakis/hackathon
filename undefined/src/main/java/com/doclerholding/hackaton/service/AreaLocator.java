package com.doclerholding.hackaton.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.stereotype.Service;

import com.doclerholding.hackaton.data.model.Poi;
import com.doclerholding.hackaton.service.model.AreaFilterCriteria;
import com.doclerholding.hackaton.service.model.LocatedArea;
import com.spatial4j.core.context.SpatialContext;
import com.spatial4j.core.distance.DistanceUtils;
import com.spatial4j.core.shape.Circle;
import com.spatial4j.core.shape.Point;
import com.spatial4j.core.shape.impl.GeoCircle;
import com.spatial4j.core.shape.impl.PointImpl;

@Service
public class AreaLocator implements IAreaLocator {
	@Autowired
	private ElasticsearchTemplate esTemplate;

	private static Point midPoint(Point p1, Point p2){
		double dLon = Math.toRadians(p2.getX() - p1.getX());

		double lat1 = Math.toRadians(p1.getY());
		double lat2 = Math.toRadians(p2.getY());
		double lon1 = Math.toRadians(p1.getX());

		double Bx = Math.cos(lat2) * Math.cos(dLon);
		double By = Math.cos(lat2) * Math.sin(dLon);
		double midLat = Math.atan2(Math.sin(lat1) + Math.sin(lat2), Math.sqrt((Math.cos(lat1) + Bx) * (Math.cos(lat1) + Bx) + By * By));
		double midLon = lon1 + Math.atan2(By, Math.cos(lat1) + Bx);

		return new PointImpl(midLon, midLat, SpatialContext.GEO);
	}
	
	private List<LocatedArea> getAreas(List<GeoPoint> startPoints, double radiusDeg, SortedSet<AreaFilterCriteria> filters) {
		List<LocatedArea> retval = new ArrayList<>(startPoints.size());
		double lfr = 0;
		Criteria preFilterCriteria = new Criteria("type");
		Map<String,Double> typeRadiusMap = new HashMap<>(filters.size());
		for (AreaFilterCriteria filter: filters) {
			double r = filter.getRadiusKm() * DistanceUtils.KM_TO_DEG;
			if (r > lfr) {
				lfr = r;
			}
			typeRadiusMap.put(filter.getType(), Double.valueOf(filter.getRadiusKm() * DistanceUtils.KM_TO_DEG));
		}
		preFilterCriteria.in(typeRadiusMap.keySet());
		for (GeoPoint startPoint: startPoints) {			
			double distance = radiusDeg + lfr;
			Criteria criteria = new Criteria("location").within(startPoint, String.valueOf(distance * DistanceUtils.DEG_TO_KM)+"km");
			List<Poi> result = this.esTemplate.queryForList(new CriteriaQuery(criteria), Poi.class);
			Set<String> requireTypes = new HashSet<>(typeRadiusMap.keySet());

			// magic on
			double baseRadius = radiusDeg;
			Point basePoint = new PointImpl(startPoint.getLon(), startPoint.getLat(), SpatialContext.GEO);
			Circle baseCircle = new GeoCircle(basePoint, baseRadius, SpatialContext.GEO);
			for(Poi item: result) {
				if (requireTypes.contains(item.getType())) {
					Point filterPoint = new PointImpl(item.getLocation().getLon(), item.getLocation().getLat(), SpatialContext.GEO);
					double filterRadius = typeRadiusMap.get(item.getType()).doubleValue();
					Circle filterCircle = new GeoCircle(filterPoint, filterRadius, SpatialContext.GEO);
					switch (filterCircle.relate(baseCircle)) {
						case DISJOINT:
							continue;
						case CONTAINS:
						case WITHIN:
							// best case, filter has larger area then base and it contains all base area
							break;
						case INTERSECTS:
							switch (baseCircle.relate(filterCircle)) {
								case DISJOINT:
									// should not happen
									continue;
								case CONTAINS:
								case WITHIN:
									// replace the base to the filter one - filter has a smaller area then base
									basePoint = filterCircle.getCenter();
									baseRadius = filterCircle.getRadius();
									baseCircle = filterCircle;
									break;
								case INTERSECTS:
									// intersect we calculate a approximately base circle between the two circle
									basePoint = midPoint(basePoint, filterPoint);
									baseCircle = new GeoCircle(basePoint, Math.abs(filterRadius-baseRadius), SpatialContext.GEO);
									break;
							}
					}
					requireTypes.remove(item.getType());
					if (requireTypes.isEmpty()) {
						break;
					}
				}
			}
			// magic off
			if (requireTypes.isEmpty()) {
				// we found all types in intersection;
				retval.add(new LocatedArea(baseCircle.getCenter().getY(), baseCircle.getCenter().getX(), baseCircle.getRadius()));
			}
		}
		return retval;
	}

	@Override
	public List<LocatedArea> getAreas(SortedSet<AreaFilterCriteria> filters) {
		if (filters == null || filters.isEmpty()) {
			return Collections.emptyList();
		}
		AreaFilterCriteria primaryFilter = filters.first();
		Criteria criteria = new Criteria("type").is(primaryFilter.getType());
		List<Poi> result = this.esTemplate.queryForList(new CriteriaQuery(criteria), Poi.class);
		if (filters.size() > 1) {
			List<GeoPoint> startPoints = result.stream().map(x -> x.getLocation()).collect(Collectors.toList());
			SortedSet<AreaFilterCriteria> reaminFilters = new TreeSet<>(filters);
			reaminFilters.remove(primaryFilter);
			return this.getAreas(startPoints, primaryFilter.getRadiusKm() * DistanceUtils.KM_TO_DEG, reaminFilters);
		}
		return result.stream().map(x -> new LocatedArea(x.getLocation().getLat(), x.getLocation().getLon(), primaryFilter.getRadiusKm() * DistanceUtils.KM_TO_DEG)).collect(Collectors.toList());
	}

}
