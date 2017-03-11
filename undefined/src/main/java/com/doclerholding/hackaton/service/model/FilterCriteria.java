package com.doclerholding.hackaton.service.model;

/**
 * Created by nikolaos.kallikakis on 11/03/17.
 */
public class FilterCriteria {

	private String filterName;
	private String filterType;
	private boolean distanceFilterSupported;

	public String getFilterName() {
		return filterName;
	}

	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}

	public String getFilterType() {
		return filterType;
	}

	public void setFilterType(String filterType) {
		this.filterType = filterType;
	}

	public boolean isDistanceFilterSupported() {
		return distanceFilterSupported;
	}

	public void setDistanceFilterSupported(boolean distanceFilterSupported) {
		this.distanceFilterSupported = distanceFilterSupported;
	}
}
