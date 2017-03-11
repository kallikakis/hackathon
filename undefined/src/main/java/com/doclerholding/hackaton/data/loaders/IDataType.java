package com.doclerholding.hackaton.data.loaders;

public interface IDataType {
	String dataType();
	boolean distanceFilter();
	void load(boolean forceDownload);
}
