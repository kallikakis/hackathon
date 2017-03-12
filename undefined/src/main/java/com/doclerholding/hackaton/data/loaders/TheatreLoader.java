package com.doclerholding.hackaton.data.loaders;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("dataTypes")
public class TheatreLoader extends AbstractOverpassLoader {

	public TheatreLoader() {
		super("amenity", "theatre");
	}

	@Override
	public String dataType() {
		return "theatre";
	}
}
