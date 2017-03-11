package com.doclerholding.hackaton.data.loaders;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by claudiu.arba on 11/03/17.
 */
public class LineLoader extends AbstractLineLoader {

	@PostConstruct
	protected void loadData() throws URISyntaxException, IOException {
		URL dirURL = Thread.currentThread().getContextClassLoader().getResource("stops.json");
		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode = mapper.readTree(new File(dirURL.toURI()));
		addPoint(rootNode, "stop");
	}

}
