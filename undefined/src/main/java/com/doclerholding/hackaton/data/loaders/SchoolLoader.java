package com.doclerholding.hackaton.data.loaders;

public class SchoolLoader extends AbstractOverpassLoader {

	public SchoolLoader() {
		super("amenity", "school");
	}

	@Override
	public String filterName() {
		return "School";
	}

	public static void main(String[] args) {
		SchoolLoader loader = new SchoolLoader();
		loader.load(true);
	}
}
