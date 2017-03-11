package com.doclerholding.hackaton.data.model.parking;

/**
 * Created by claudiu.arba on 11/03/17.
 */
class PaymentMethods{
	private Boolean cash;
	private Boolean vpay;
	private Boolean visa;
	private Boolean mastercard;
	private Boolean eurocard;
	private Boolean amex;
	private Boolean call2park;

	public Boolean getCash() {
		return cash;
	}

	public void setCash(Boolean cash) {
		this.cash = cash;
	}

	public Boolean getVpay() {
		return vpay;
	}

	public void setVpay(Boolean vpay) {
		this.vpay = vpay;
	}

	public Boolean getVisa() {
		return visa;
	}

	public void setVisa(Boolean visa) {
		this.visa = visa;
	}

	public Boolean getMastercard() {
		return mastercard;
	}

	public void setMastercard(Boolean mastercard) {
		this.mastercard = mastercard;
	}

	public Boolean getEurocard() {
		return eurocard;
	}

	public void setEurocard(Boolean eurocard) {
		this.eurocard = eurocard;
	}

	public Boolean getAmex() {
		return amex;
	}

	public void setAmex(Boolean amex) {
		this.amex = amex;
	}

	public Boolean getCall2park() {
		return call2park;
	}

	public void setCall2park(Boolean call2park) {
		this.call2park = call2park;
	}
}
