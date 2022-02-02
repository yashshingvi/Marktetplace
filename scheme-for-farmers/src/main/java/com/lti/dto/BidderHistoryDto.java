package com.lti.dto;

public class BidderHistoryDto {

	private String cropName;
	private float hisBidAmount;
	private double basePrice;
	private float MaxBidAmount;
	private boolean status;

	public String getCropName() {
		return cropName;
	}

	public void setCropName(String cropName) {
		this.cropName = cropName;
	}

	public float getHisBidAmount() {
		return hisBidAmount;
	}

	public void setHisBidAmount(float hisBidAmount) {
		this.hisBidAmount = hisBidAmount;
	}

	public double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}

	public float getMaxBidAmount() {
		return MaxBidAmount;
	}

	public void setMaxBidAmount(float maxBidAmount) {
		MaxBidAmount = maxBidAmount;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
