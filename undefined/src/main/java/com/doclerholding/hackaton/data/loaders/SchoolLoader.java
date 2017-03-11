package com.doclerholding.hackaton.data.loaders;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("dataTypes")
public class SchoolLoader extends AbstractOverpassLoader {

	public SchoolLoader() {
		super("amenity", "school");
	}

	@Override
	public String filterType() {
		return "school";
	}

	public static void main(String[] args) {
		SchoolLoader loader = new SchoolLoader();
		loader.load(true);
	}
}
