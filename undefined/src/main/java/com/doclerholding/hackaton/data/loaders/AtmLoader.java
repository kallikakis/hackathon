package com.doclerholding.hackaton.data.loaders;

public class AtmLoader extends AbstractOverpassLoader {

	public AtmLoader() {
		super("amenity", "theatre");
	}

	@Override
	public String filterName() {
		return "Theatre";
	}

	public static void main(String[] args) {
		AtmLoader loader = new AtmLoader();
		loader.load(true);
	}
}
