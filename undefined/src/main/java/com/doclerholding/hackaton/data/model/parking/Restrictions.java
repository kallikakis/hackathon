package com.doclerholding.hackaton.data.model.parking;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by claudiu.arba on 11/03/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class Restrictions{
	@JsonProperty("allowed_gpl")
	private Boolean allowedGpl;
	@JsonProperty("allowed_trailor")
	private Boolean allowedTrailor;
	@JsonProperty("allowed_truck")
	private Boolean allowedTruck;
	@JsonProperty("max_height")
	private Integer maxHeight;

	public Boolean getAllowedGpl() {
		return allowedGpl;
	}

	public void setAllowedGpl(Boolean allowedGpl) {
		this.allowedGpl = allowedGpl;
	}

	public Boolean getAllowedTrailor() {
		return allowedTrailor;
	}

	public void setAllowedTrailor(Boolean allowedTrailor) {
		this.allowedTrailor = allowedTrailor;
	}

	public Boolean getAllowedTruck() {
		return allowedTruck;
	}

	public void setAllowedTruck(Boolean allowedTruck) {
		this.allowedTruck = allowedTruck;
	}

	public Integer getMaxHeight() {
		return maxHeight;
	}

	public void setMaxHeight(Integer maxHeight) {
		this.maxHeight = maxHeight;
	}
}
