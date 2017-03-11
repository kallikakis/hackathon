package com.doclerholding.hackaton.data.model.airquality;

/**
 * Created by maxim on 3/11/2017.
 */
public class AirQualityDetails {
	private String id; //id: "aev:Beidweiler",
	private String name; //name: "Beidweiler",
	private Double temp; //temp: 12.3,
	private Double pm10; //pm10: 7,
	private Double no2; //no2: 9.1,
	private Double o3; //o3: 76,
	private Double so2; //so2: 1.3,
	private Double co; //co: null,

	public AirQualityDetails() {
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

	public Double getTemp() {
		return temp;
	}

	public void setTemp(Double temp) {
		this.temp = temp;
	}

	public Double getPm10() {
		return pm10;
	}

	public void setPm10(Double pm10) {
		this.pm10 = pm10;
	}

	public Double getNo2() {
		return no2;
	}

	public void setNo2(Double no2) {
		this.no2 = no2;
	}

	public Double getO3() {
		return o3;
	}

	public void setO3(Double o3) {
		this.o3 = o3;
	}

	public Double getSo2() {
		return so2;
	}

	public void setSo2(Double so2) {
		this.so2 = so2;
	}

	public Double getCo() {
		return co;
	}

	public void setCo(Double co) {
		this.co = co;
	}
}
