package com.doclerholding.hackaton.exception;

abstract public class AbstractRestException extends Exception {
	private static final long serialVersionUID = 1L;
	
	private String errMsg;

	public AbstractRestException(String errMsg) {
		this.errMsg = errMsg;
	}

	public String getResponse() {
		return errMsg;
	}
}
