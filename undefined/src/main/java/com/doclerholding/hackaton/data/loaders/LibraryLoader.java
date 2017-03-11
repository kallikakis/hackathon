package com.doclerholding.hackaton.data.loaders;

public class LibraryLoader extends AbstractOverpassLoader {

	public LibraryLoader() {
		super("amenity", "library");
	}

	@Override
	public String filterName() {
		return "Library";
	}

	public static void main(String[] args) {
		LibraryLoader loader = new LibraryLoader();
		loader.load(true);
	}
}
