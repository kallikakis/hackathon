package com.doclerholding.hackaton.data.loaders;

public class PlaygroundLoader extends AbstractOverpassLoader {

	public PlaygroundLoader() {
		super("leisure", "playground");
	}

	@Override
	public String filterName() {
		return "Playgound";
	}

	public static void main(String[] args) {
		PlaygroundLoader loader = new PlaygroundLoader();
		loader.load(true);
	}
}
