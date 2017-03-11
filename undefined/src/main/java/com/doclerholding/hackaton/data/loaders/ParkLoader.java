package com.doclerholding.hackaton.data.loaders;

public class ParkLoader extends AbstractOverpassLoader {

	public ParkLoader() {
		super("leisure", "park");
	}

	@Override
	public String filterName() {
		return "Park";
	}

	public static void main(String[] args) {
		ParkLoader loader = new ParkLoader();
		loader.load(true);
	}
}
