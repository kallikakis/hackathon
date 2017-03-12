package com.doclerholding.hackaton.data.loaders;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by maxim on 3/11/2017.
 */
@Component
@Qualifier("dataTypes")
public class AirQualityLoader extends AbstractTflLoader {

	//private static Logger logger = LoggerFactory.getLogger(AirQualityLoader.class);

	@Override
	public String dataType() {
		return "airquality";
	}

	@Override
	public boolean distanceFilter() {
		return false;
	}
	
	@Override
	protected String getApiURL() {
		return "https://api.tfl.lu/v1/Weather/Airquality";
	}

	@Override
	public long load(boolean forceDownload) {
		File dataFile;
		try {
			dataFile = this.downloadData(forceDownload);
		} catch (IOException e1) {
			e1.printStackTrace();
			return -1;
		}
		final ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode;
		try {
			rootNode = mapper.readTree(dataFile);
			return addPoint(rootNode, "air_quality");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

}
