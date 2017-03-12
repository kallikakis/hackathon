package com.doclerholding.hackaton.data.loaders;

public interface IDataType {
	String dataType();
	boolean distanceFilter();
	long load(boolean forceDownload);
}
