package com.doclerholding.hackaton.service.model;

public class LocatedArea {
	private double lat;
	private double lon;
	private double radiusMeter;
	
	public LocatedArea() {
		super();
	}
	
	public LocatedArea(double lat, double lon, double radiusMeter) {
		super();
		this.lat = lat;
		this.lon = lon;
		this.radiusMeter = radiusMeter;
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
	
	public double getRadiusMeter() {
		return radiusMeter;
	}
	
	public void setRadiusMeter(double radiusMeter) {
		this.radiusMeter = radiusMeter;
	}
}
