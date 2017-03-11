package com.doclerholding.hackaton.data.model.parking;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by claudiu.arba on 11/03/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ParkSpotDetail {

	private String id;
	private String name;
	private Integer total;
	private Integer free;
	private String trend;
	private Meta meta;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getFree() {
		return free;
	}

	public void setFree(Integer free) {
		this.free = free;
	}

	public String getTrend() {
		return trend;
	}

	public void setTrend(String trend) {
		this.trend = trend;
	}

	public Meta getMeta() {
		return meta;
	}

	public void setMeta(Meta meta) {
		this.meta = meta;
	}


}
