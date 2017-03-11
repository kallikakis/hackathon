package com.doclerholding.hackaton.data.loaders;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by maxim on 3/11/2017.
 */
@Component
public class AirQualityLineLoader extends AbstractLineLoader {

	private static Logger logger = LoggerFactory.getLogger(AirQualityLineLoader.class);

	@PostConstruct
	public void loadAirQualityLine() throws URISyntaxException, IOException {
		final ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode = mapper.readTree(new File(Thread.currentThread().getContextClassLoader().getResource("air_quality.json").toURI()));
		addPoint(rootNode, "air_quality");
	}

}
