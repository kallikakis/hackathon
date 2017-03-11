package com.doclerholding.hackaton.data.loaders;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("dataTypes")
public class SwimmingPoolLoader extends AbstractOverpassLoader {

	public SwimmingPoolLoader() {
		super("leisure", "swimming_pool");
	}

	@Override
	public String dataType() {
		return "swimming";
	}

	public static void main(String[] args) {
		SwimmingPoolLoader loader = new SwimmingPoolLoader();
		loader.load(true);
	}
}
