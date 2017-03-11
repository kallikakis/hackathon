package com.doclerholding.hackaton.data.loaders;

public class SwimmingPoolLoader extends AbstractOverpassLoader {

	public SwimmingPoolLoader() {
		super("leisure", "swimming_pool");
	}

	@Override
	public String filterName() {
		return "Swimming Pool";
	}

	public static void main(String[] args) {
		SwimmingPoolLoader loader = new SwimmingPoolLoader();
		loader.load(true);
	}
}
