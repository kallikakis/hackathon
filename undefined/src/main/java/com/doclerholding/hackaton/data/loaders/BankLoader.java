package com.doclerholding.hackaton.data.loaders;

public class BankLoader extends AbstractOverpassLoader {

	public BankLoader() {
		super("amenity", "bank");
	}

	@Override
	public String filterName() {
		return "Bank";
	}

	public static void main(String[] args) {
		BankLoader loader = new BankLoader();
		loader.load(true);
	}
}
