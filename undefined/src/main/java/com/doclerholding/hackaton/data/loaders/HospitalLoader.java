package com.doclerholding.hackaton.data.loaders;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("dataTypes")
public class HospitalLoader extends AbstractOverpassLoader {

	public HospitalLoader() {
		super("amenity", "hospital");
	}

	@Override
	public String dataType() {
		return "hospital";
	}
}
