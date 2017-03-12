package com.doclerholding.hackaton.data.model.parking;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by claudiu.arba on 11/03/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class Meta{
	private Boolean open;
	private Boolean elevator;
	private String link;
	private Address address;
	private String phone;
	@JsonProperty("reserved_for_disabled")
	private Integer reservedForDisabled;
	@JsonProperty("reserved_for_women")
	private Integer reservedForWomen;
	@JsonProperty("motorbike_lots")
	private Integer motorbikeLots;
	@JsonProperty("bus_lots")
	private Integer busLots;
	@JsonProperty("bicycle_docks")
	private Integer bicycleDocks;
	@JsonProperty("payment_methods")
	private PaymentMethods paymentMethods;
	private Restrictions restrictions;

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}

	public Boolean getElevator() {
		return elevator;
	}

	public void setElevator(Boolean elevator) {
		this.elevator = elevator;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getReservedForDisabled() {
		return reservedForDisabled;
	}

	public void setReservedForDisabled(Integer reservedForDisabled) {
		this.reservedForDisabled = reservedForDisabled;
	}

	public Integer getReservedForWomen() {
		return reservedForWomen;
	}

	public void setReservedForWomen(Integer reservedForWomen) {
		this.reservedForWomen = reservedForWomen;
	}

	public Integer getMotorbikeLots() {
		return motorbikeLots;
	}

	public void setMotorbikeLots(Integer motorbikeLots) {
		this.motorbikeLots = motorbikeLots;
	}

	public Integer getBusLots() {
		return busLots;
	}

	public void setBusLots(Integer busLots) {
		this.busLots = busLots;
	}

	public Integer getBicycleDocks() {
		return bicycleDocks;
	}

	public void setBicycleDocks(Integer bicycleDocks) {
		this.bicycleDocks = bicycleDocks;
	}

	public PaymentMethods getPaymentMethods() {
		return paymentMethods;
	}

	public void setPaymentMethods(PaymentMethods paymentMethods) {
		this.paymentMethods = paymentMethods;
	}

	public Restrictions getRestrictions() {
		return restrictions;
	}

	public void setRestrictions(Restrictions restrictions) {
		this.restrictions = restrictions;
	}
}
