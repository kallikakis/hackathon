package com.doclerholding.hackaton.data.loaders;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("dataTypes")
public class AtmLoader extends AbstractOverpassLoader {

	public AtmLoader() {
		super("amenity", "atm");
	}

	@Override
	public String dataType() {
		return "atm";
	}
}
