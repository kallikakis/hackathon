package com.doclerholding.hackaton;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;

import com.doclerholding.hackaton.data.loaders.IDataType;

@SpringBootApplication
@EnableAsync
public class UndefinedApplication {

	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

	@Bean
	public Map<String,IDataType> dataTypeMap(@Autowired List<IDataType> dataTypes) {
		return dataTypes.stream().collect(Collectors.toMap(IDataType::dataType, x -> x));
	}

	public static void main(String[] args) {
		SpringApplication.run(UndefinedApplication.class, args);
	}
}
