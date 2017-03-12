package com.doclerholding.hackaton.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.doclerholding.hackaton.data.loaders.IDataType;

@Controller
public class DataController {
	
	static class DataLoaderStreamingResponse implements StreamingResponseBody {

		private final List<IDataType> dataTypes;
		private final boolean forceDownload;
		
		DataLoaderStreamingResponse(List<IDataType> dataTypes, boolean forceDownload) {
			this.dataTypes = dataTypes;
			this.forceDownload = forceDownload;
		}

		@Override
		public void writeTo(OutputStream outputStream) throws IOException {
			for(IDataType dt: this.dataTypes) {
				outputStream.write(("Loading: "+dt.dataType()+"\n").getBytes());
				outputStream.flush();
				Instant start = Instant.now();
				long cnt = dt.load(forceDownload);
				Duration duration = Duration.between(start, Instant.now());
				if (cnt >= 0) {
					outputStream.write(("    DONE "+cnt+" records in "+duration.getSeconds()+"sec\n").getBytes());
				} else {
					outputStream.write(("    FAIL in "+duration.getSeconds()+"sec\n").getBytes());
				}
				outputStream.flush();
			}
			outputStream.write("All data sets loaded".getBytes());
		}
	}
	
	@Autowired
	private List<IDataType> dataTypes;

	@RequestMapping(path="/data/load", method = RequestMethod.GET)
	@ResponseBody
	public StreamingResponseBody loadData(@RequestParam(name="forceDownload", required=false) Boolean forceDownload) {
		return new DataLoaderStreamingResponse(this.dataTypes, forceDownload != null ? forceDownload.booleanValue() : false);
	}
}
