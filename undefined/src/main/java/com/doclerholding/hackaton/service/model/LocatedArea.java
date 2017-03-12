package com.doclerholding.hackaton.service.model;

public class LocatedArea {
	private double lat;
	private double lon;
	private double radiusDeg;
	
	public LocatedArea() {
		super();
	}
	
	public LocatedArea(double lat, double lon, double radiusDeg) {
		super();
		this.lat = lat;
		this.lon = lon;
		this.radiusDeg = radiusDeg;
	}

	public double getLat() {
		return lat;
	}
	
	public void setLat(double lat) {
		this.lat = lat;
	}
	
	public double getLon() {
		return lon;
	}
	
	public void setLon(double lon) {
		this.lon = lon;
	}
	
	public double getRadiusDeg() {
		return radiusDeg;
	}
	
	public void setRadiusDeg(double radiusDeg) {
		this.radiusDeg = radiusDeg;
	}
}
