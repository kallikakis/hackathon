package com.doclerholding.hackaton.data.loaders;

public class HospitalLoader extends AbstractOverpassLoader {

	public HospitalLoader() {
		super("amenity", "hospital");
	}

	@Override
	public String filterName() {
		return "Hospital";
	}

	public static void main(String[] args) {
		HospitalLoader loader = new HospitalLoader();
		loader.load(true);
	}
}
