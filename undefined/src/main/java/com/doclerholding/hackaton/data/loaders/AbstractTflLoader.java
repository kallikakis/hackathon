package com.doclerholding.hackaton.data.loaders;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import com.doclerholding.hackaton.data.model.Poi;
import com.doclerholding.hackaton.data.repository.PoiRepository;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * Created by maxim on 3/11/2017.
 */
public abstract class AbstractTflLoader implements IDataType {

	@Autowired
	private PoiRepository poiRepository;
	
	abstract protected String getApiURL();

	protected File downloadData(boolean forceDownload) throws IOException {
		File file = new File("data/download/"+this.getClass().getSimpleName()+".json");
		if (forceDownload || !file.exists()) {
			file.getParentFile().mkdirs();
			URL apiURL = new URL(this.getApiURL());
			
			HttpURLConnection connection = (HttpURLConnection) apiURL.openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(false);
			connection.setRequestMethod("GET");
			
			try (
					FileOutputStream fos = new FileOutputStream(file);
					BufferedInputStream bis = new BufferedInputStream(connection.getInputStream());
			) {
				int read = 0;
				byte[] bytes = new byte[1024];
				
				while((read = bis.read(bytes)) != -1) {
					fos.write(bytes, 0, read);
				}
			}
		}
		return file;
	}

	protected long addPoint(JsonNode node, String pointType) {
		long cnt = 0;
		final List<Poi> data = new ArrayList<>();
		JsonNode features = node.path("features");
		for (JsonNode f : features) {
			Poi poi = new Poi();
			poi.setType(pointType);
			JsonNode geometry = f.path("geometry");
			if (geometry.path("type").asText().equals("Point")) {
				JsonNode coordinates = geometry.path("coordinates");
				poi.setLocation(new GeoPoint(coordinates.path(1).asDouble(), coordinates.path(0).asDouble()));
			}
			JsonNode props = f.path("properties");
			poi.setId(props.path("id").asText());
			poi.setName(props.path("name").asText());
			data.add(poi);
			this.poiRepository.index(poi);
			cnt++;
		}
		return cnt;
	}
}
