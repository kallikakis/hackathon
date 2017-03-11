package com.doclerholding.hackaton.data.loaders;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("dataTypes")
public class ParkLoader extends AbstractOverpassLoader {

	public ParkLoader() {
		super("leisure", "park");
	}

	@Override
	public String dataType() {
		return "park";
	}

	public static void main(String[] args) {
		ParkLoader loader = new ParkLoader();
		loader.load(true);
	}
}
