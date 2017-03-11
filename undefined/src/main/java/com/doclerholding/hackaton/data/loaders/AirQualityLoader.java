package com.doclerholding.hackaton.data.loaders;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by maxim on 3/11/2017.
 */
@Component
@Qualifier("dataTypes")
public class AirQualityLoader extends AbstractTflLoader {

	private static Logger logger = LoggerFactory.getLogger(AirQualityLoader.class);

	@Override
	public String dataType() {
		return "airquality";
	}

	@Override
	public boolean distanceFilter() {
		return false;
	}

	@Override
	public void load(boolean forceDownload) {
		final ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode;
		try {
			rootNode = mapper.readTree(new File(Thread.currentThread().getContextClassLoader().getResource("air_quality.json").toURI()));
			addPoint(rootNode, "air_quality");
		} catch (IOException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
