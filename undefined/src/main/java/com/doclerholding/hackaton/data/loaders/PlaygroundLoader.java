package com.doclerholding.hackaton.data.loaders;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("dataTypes")
public class PlaygroundLoader extends AbstractOverpassLoader {

	public PlaygroundLoader() {
		super("leisure", "playground");
	}

	@Override
	public String dataType() {
		return "playgound";
	}
}
