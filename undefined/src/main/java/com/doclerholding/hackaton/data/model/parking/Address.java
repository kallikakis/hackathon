package com.doclerholding.hackaton.data.model.parking;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by claudiu.arba on 11/03/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class Address{
	private String street;
	private String exit;

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getExit() {
		return exit;
	}

	public void setExit(String exit) {
		this.exit = exit;
	}
}
