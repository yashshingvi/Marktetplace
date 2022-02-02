package com.lti.dto;

public class BidSupport {
	
	private float bidAmount;
	private int bidderId;
	private int sellRequestId;
	
	public float getBidAmount() {
		return bidAmount;
	}
	public void setBidAmount(float bidAmount) {
		this.bidAmount = bidAmount;
	}
	public int getBidderId() {
		return bidderId;
	}
	public void setBidderId(int bidderId) {
		this.bidderId = bidderId;
	}
	public int getSellRequestId() {
		return sellRequestId;
	}
	public void setSellRequestId(int sellRequestId) {
		this.sellRequestId = sellRequestId;
	}
	
}
