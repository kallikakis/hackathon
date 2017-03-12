package com.doclerholding.hackaton.service.model;

import java.io.Serializable;

public class AreaFilterCriteria implements Serializable, Comparable<AreaFilterCriteria> {
	private static final long serialVersionUID = 1L;

	private String type;
	private double radiusKm;
	private int priority;

	public AreaFilterCriteria() {
		super();
	}

	public AreaFilterCriteria(String type, double radiusKm, int priority) {
		super();
		this.type = type;
		this.radiusKm = radiusKm;
		this.priority = priority;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getRadiusKm() {
		return radiusKm;
	}
	public void setRadiusKm(double radius) {
		this.radiusKm = radius;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}
	
	// Filters unique by type (no other fields required then type)
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AreaFilterCriteria other = (AreaFilterCriteria) obj;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	
	// Filters unique by type (no other fields required then type)
	@Override
	public int compareTo(AreaFilterCriteria o) {
		return Integer.compare(this.priority, o.priority);
	}
}
