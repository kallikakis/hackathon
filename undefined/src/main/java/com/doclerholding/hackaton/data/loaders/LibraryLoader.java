package com.doclerholding.hackaton.data.loaders;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("dataTypes")
public class LibraryLoader extends AbstractOverpassLoader {

	public LibraryLoader() {
		super("amenity", "library");
	}

	@Override
	public String dataType() {
		return "library";
	}

	public static void main(String[] args) {
		LibraryLoader loader = new LibraryLoader();
		loader.load(true);
	}
}
