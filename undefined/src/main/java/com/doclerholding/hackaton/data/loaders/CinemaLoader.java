package com.doclerholding.hackaton.data.loaders;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("dataTypes")
public class CinemaLoader extends AbstractOverpassLoader {

	public CinemaLoader() {
		super("amenity", "cinema");
	}

	@Override
	public String dataType() {
		return "cinema";
	}
}
