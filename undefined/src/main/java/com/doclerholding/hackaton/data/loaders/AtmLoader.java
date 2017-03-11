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
		return "ATM";
	}

	public static void main(String[] args) {
		AtmLoader loader = new AtmLoader();
		loader.load(true);
	}
}
