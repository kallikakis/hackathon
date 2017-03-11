package com.doclerholding.hackaton.data.loaders;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.stereotype.Component;

import com.doclerholding.hackaton.data.model.Poi;
import com.doclerholding.hackaton.data.repository.PoiRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
abstract public class AbstractOverpassLoader implements IFilterType {
	private static final long countryId = 3602171347l;
	private static final String ID_PREFIX = "OSM:";
	private static final String OVERPASS_API="http://overpass-api.de/api/interpreter";
	
	private ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	private PoiRepository poiRepository;

	protected final String type;
	protected final String value;
	
	protected AbstractOverpassLoader(String type, String value) {
		this.type = type;
		this.value = value;
	}
	
	private File downloadData(boolean forceDownload) throws IOException {
		File file = new File("data/download/"+this.type+"-"+this.value+".json");
		if (forceDownload || !file.exists()) {
			String queryString = "[out:json][timeout:600];\n"
					+"area("+countryId+")->.searchArea;\n"
					+"(\n"
					+"	node["+type+"="+value+"](area.searchArea);\n"
					+"	way["+type+"="+value+"](area.searchArea);\n"
					+"	relation["+type+"="+value+"](area.searchArea);\n"
					+");\n"
					+"out center meta;\n";
			
			URL apiURL = new URL(OVERPASS_API);
			
			HttpURLConnection connection = (HttpURLConnection) apiURL.openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
			
			try (DataOutputStream request = new DataOutputStream(connection.getOutputStream())) {
				request.writeBytes("data=" + URLEncoder.encode(queryString, "utf-8"));
				request.flush();
			}
			
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
	
	private void loadData(File sourceFile) throws JsonProcessingException, IOException {
		JsonNode rootNode = this.objectMapper.readTree(sourceFile);
		JsonNode elements = rootNode.path("elements");
		for(JsonNode item: elements) {
			String itemType = item.get("type").asText();
			Poi model = new Poi();
			model.setId(ID_PREFIX+item.get("id").asInt());
			model.setType(this.type);
			if ("node".equals(itemType)) {
				model.setLocation(new GeoPoint(item.get("lat").asDouble(), item.get("lon").asDouble()));
				JsonNode tags = item.get("tags");
				if (tags != null) {
					JsonNode nameTag = tags.get("name");
					if (nameTag == null) {
						continue;
					}
					
				}
			}
			try {
				if ("way".equals(itemType)) {
					model.setLocation(new GeoPoint(item.get("center").get("lat").asDouble(), item.get("center").get("lon").asDouble()));
				} else if ("node".equals("itemType")) {
					model.setLocation(new GeoPoint(item.get("lat").asDouble(), item.get("lon").asDouble()));
				}
				model.setName(item.get("tags").get("name").asText());
			} catch (NullPointerException e) {
				// Missing important data
				continue;
			}
			this.poiRepository.index(model);
		}
	}
	
	public void load(boolean forceDownload) {
		try {
			this.loadData(this.downloadData(forceDownload));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
