package com.doclerholding.hackaton.data.loaders;

public class SwimmingPoolLoader extends AbstractOverpassLoader {

	public SwimmingPoolLoader() {
		super("amenity", "theatre");
	}

	@Override
	public String filterName() {
		return "Theatre";
	}

	public static void main(String[] args) {
		SwimmingPoolLoader loader = new SwimmingPoolLoader();
		loader.load(true);
	}
}
