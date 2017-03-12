package com.doclerholding.hackaton.data.model.airquality;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by maxim on 3/11/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AirQualityDetails {
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private double lat;
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private double lng;
	private String id;
	private String name;
	private double temp;
	private double pm10;
	private double no2;
	private double o3;
	private double so2;
	private double co;
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private AirQualityIndexEnum index;

	public AirQualityDetails() {
	}

	public void generateIndex(){
		index = AirQualityIndexEnum.valueOf(this.pm10, this.no2, this.o3, this.so2, this.co);
	}

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

	public double getTemp() {
		return temp;
	}

	public void setTemp(double temp) {
		this.temp = temp;
	}

	public double getPm10() {
		return pm10;
	}

	public void setPm10(double pm10) {
		this.pm10 = pm10;
	}

	public double getNo2() {
		return no2;
	}

	public void setNo2(double no2) {
		this.no2 = no2;
	}

	public double getO3() {
		return o3;
	}

	public void setO3(double o3) {
		this.o3 = o3;
	}

	public double getSo2() {
		return so2;
	}

	public void setSo2(double so2) {
		this.so2 = so2;
	}

	public double getCo() {
		return co;
	}

	public void setCo(double co) {
		this.co = co;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public AirQualityIndexEnum getIndex() {
		return this.index;
	}

}
