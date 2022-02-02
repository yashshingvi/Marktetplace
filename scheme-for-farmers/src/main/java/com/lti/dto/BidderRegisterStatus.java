package com.lti.dto;

public class BidderRegisterStatus extends Status {

	private int BidderId;
	private String BidderName;

	public int getBidderId() {
		return BidderId;
	}

	public void setBidderId(int bidderId) {
		BidderId = bidderId;
	}

	public String getBidderName() {
		return BidderName;
	}

	public void setBidderName(String bidderName) {
		BidderName = bidderName;
	}

}
