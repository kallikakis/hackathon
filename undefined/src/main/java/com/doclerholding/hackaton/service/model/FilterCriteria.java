package com.doclerholding.hackaton.service.model;

import java.io.Serializable;

/**
 * Created by nikolaos.kallikakis on 11/03/17.
 */
public class FilterCriteria implements Serializable {
	private static final long serialVersionUID = 1L;

	private String filterType;
	private boolean distanceFilterSupported;

	public FilterCriteria() {
		super();
	}

	public FilterCriteria(String filterType, boolean distanceFilterSupported) {
		super();
		this.filterType = filterType;
		this.distanceFilterSupported = distanceFilterSupported;
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
