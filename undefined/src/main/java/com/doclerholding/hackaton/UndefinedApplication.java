package com.doclerholding.hackaton;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;

import com.doclerholding.hackaton.data.loaders.IDataType;

import javax.net.ssl.SSLContext;

@SpringBootApplication
@EnableAsync
public class UndefinedApplication {

//	@Bean
//	public RestTemplate restTemplate(){
//		return new RestTemplate();
//	}

	@Bean
	public RestTemplate restTemplate() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
		TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
		SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
		SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);
		CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setHttpClient(httpClient);
		return new RestTemplate(requestFactory);
	}

	@Bean("dataTypeMap")
	public Map<String,IDataType> dataTypeMap(@Autowired List<IDataType> dataTypes){
		Map<String, IDataType> dataTypeMap = new HashMap<>();

		for(IDataType dataType : dataTypes) {
			dataTypeMap.put(dataType.dataType(), dataType);
		}

		return dataTypeMap;
	}

	public static void main(String[] args) {
		SpringApplication.run(UndefinedApplication.class, args);
	}
}
