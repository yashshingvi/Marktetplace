package com.lti.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tbl_bidinfo")
public class BidInfo {

	@Id
	@GeneratedValue
	private int id;

//	private int Bidder_Id;
//	private int SellRequest_Id;
	private float bidAmount;
	
	@JoinColumn(name="bidderId")
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnore
	private Bidder bidder;
	
	@JoinColumn(name="sellRequestId")
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnore
	private SellRequest sellRequest;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getBidAmount() {
		return bidAmount;
	}
	public void setBidAmount(float bidAmount) {
		this.bidAmount = bidAmount;
	}
	public Bidder getBidder() {
		return bidder;
	}
	public void setBidder(Bidder bidder) {
		this.bidder = bidder;
	}
	public SellRequest getSellRequest() {
		return sellRequest;
	}
	public void setSellRequest(SellRequest sellRequest) {
		this.sellRequest = sellRequest;
	}

}
