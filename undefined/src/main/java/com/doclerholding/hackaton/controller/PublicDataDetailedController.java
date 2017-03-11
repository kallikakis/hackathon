package com.doclerholding.hackaton.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

/**
 * Created by maxim on 3/11/2017.
 */
public abstract class PublicDataDetailedController<T> {
	private final String PUBLIC_DATA_BASE_URL = "https://api.tfl.lu/v1/";

	private Class<T> typeParameterClass;

	@Autowired
	private RestTemplate restTemplate;

	public PublicDataDetailedController(Class<T> typeParameterClass) {
		this.typeParameterClass = typeParameterClass;
	}

	protected T getDetailedData(String id, String detailsEndPointPath) throws JsonProcessingException {
		JsonNode node = restTemplate.getForObject(PUBLIC_DATA_BASE_URL + detailsEndPointPath, JsonNode.class, id);
		JsonNode props = node.path("properties");
		ObjectMapper mapper = new ObjectMapper();
		T result = mapper.treeToValue(props, typeParameterClass);
		return result;
	}
}
