package com.doclerholding.hackaton.service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.doclerholding.hackaton.data.loaders.IDataType;

@Service
public class DataLoaderService implements IDataLoaderService {
	@Autowired
	private List<IDataType> dataTypes;

	@Override
	@Async
	public void loadDataAsync(boolean forceDownload) {
		for(IDataType dt: this.dataTypes) {
			System.out.println("Loading: "+dt.dataType());
			Instant start = Instant.now();
			long cnt = -1;
			try {
				cnt = dt.load(forceDownload);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			Duration duration = Duration.between(start, Instant.now());
			if (cnt >= 0) {
				System.out.println("    DONE "+cnt+" records in "+duration.getSeconds()+"sec");
			} else {
				System.out.println("    FAIL in "+duration.getSeconds()+"sec");
			}
		}
		System.out.println("All datasets loaded");
	}


}
