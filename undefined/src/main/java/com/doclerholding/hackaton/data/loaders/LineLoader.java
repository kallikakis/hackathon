package com.doclerholding.hackaton.data.loaders;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	protected String getApiURL() {
		return "https://api.tfl.lu/v1/StopPoint";
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
		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode;
		try {
			rootNode = mapper.readTree(dataFile);
			return addPoint(rootNode, "stop");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

}
