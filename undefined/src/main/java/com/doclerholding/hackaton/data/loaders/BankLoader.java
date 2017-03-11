package com.doclerholding.hackaton.data.loaders;

public class BankLoader extends AbstractOverpassLoader {

	public BankLoader() {
		super("leisure", "swimming_pool");
	}

	@Override
	public String filterName() {
		return "Swimming pool";
	}

	public static void main(String[] args) {
		BankLoader loader = new BankLoader();
		loader.load(true);
	}
}
