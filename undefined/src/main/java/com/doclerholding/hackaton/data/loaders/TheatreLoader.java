package com.doclerholding.hackaton.data.loaders;

public class TheatreLoader extends AbstractOverpassLoader {

	public TheatreLoader() {
		super("amenity", "theatre");
	}

	@Override
	public String filterName() {
		return "Theatre";
	}

	public static void main(String[] args) {
		TheatreLoader loader = new TheatreLoader();
		loader.load(true);
	}
}
