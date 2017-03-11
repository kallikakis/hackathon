package com.doclerholding.hackaton.data.loaders;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("dataTypes")
public class BankLoader extends AbstractOverpassLoader {

	public BankLoader() {
		super("amenity", "bank");
	}

	@Override
	public String filterType() {
		return "bank";
	}

	public static void main(String[] args) {
		BankLoader loader = new BankLoader();
		loader.load(true);
	}
}
