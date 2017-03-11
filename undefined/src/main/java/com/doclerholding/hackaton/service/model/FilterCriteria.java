package com.doclerholding.hackaton.service.model;

import java.io.Serializable;

/**
 * Created by nikolaos.kallikakis on 11/03/17.
 */
public class FilterCriteria implements Serializable {
	private static final long serialVersionUID = 1L;

	private String filterType;

	public FilterCriteria() {
		super();
	}

	public FilterCriteria(String filterType) {
		super();
		this.filterType = filterType;
	}

	public String getFilterType() {
		return filterType;
	}

	public void setFilterType(String filterType) {
		this.filterType = filterType;
	}
}
