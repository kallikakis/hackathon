package com.doclerholding.hackaton.data.loaders;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by claudiu.arba on 11/03/17.
 */
@Component
@Qualifier("dataTypes")
public class LineLoader extends AbstractTflLoader {

	@Override
	public String dataType() {
		return "stop";
	}

	@Override
	public boolean distanceFilter() {
		return true;
	}

	@Override
	public void load(boolean forceDownload) {
		URL dirURL = Thread.currentThread().getContextClassLoader().getResource("stops.json");
		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode;
		try {
			rootNode = mapper.readTree(new File(dirURL.toURI()));
			addPoint(rootNode, "stop");
		} catch (IOException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
