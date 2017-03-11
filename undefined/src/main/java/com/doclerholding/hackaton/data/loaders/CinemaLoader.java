package com.doclerholding.hackaton.data.loaders;

public class CinemaLoader extends AbstractOverpassLoader {

	public CinemaLoader() {
		super("amenity", "cinema");
	}

	@Override
	public String filterName() {
		return "Cinema";
	}

	public static void main(String[] args) {
		CinemaLoader loader = new CinemaLoader();
		loader.load(true);
	}
}
