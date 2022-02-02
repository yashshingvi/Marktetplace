package com.lti.dto;

public class HistoryDto {
	private String cropName;
	private double basePrice;
	private float maxBidAmount;
	public boolean status;

	public String getCropName() {
		return cropName;
	}

	public void setCropName(String cropName) {
		this.cropName = cropName;
	}

	public double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}

	public float getMaxBidAmount() {
		return maxBidAmount;
	}

	public void setMaxBidAmount(float maxBidAmount) {
		this.maxBidAmount = maxBidAmount;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	
}
