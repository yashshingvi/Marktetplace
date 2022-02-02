package com.lti.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="tbl_adminRequest")
public class AdminRequest {
	
	@Id
	//@GeneratedValue
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_admin_request_seq")
	@SequenceGenerator(sequenceName = "my_admin_request_seq", allocationSize = 1, name = "my_admin_request_seq")
	private int id;
	private UserType type;
	private boolean status;
	
	@OneToOne(mappedBy = "adminRequest")
	private Bidder bidder;
	
	@OneToOne(mappedBy = "adminRequest")
	private SellRequest sellRequest;
	
	@OneToOne(mappedBy = "adminRequest")
	private Farmer farmer;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public UserType getType() {
		return type;
	}
	public void setType(UserType type) {
		this.type = type;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
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
	public Farmer getFarmer() {
		return farmer;
	}
	public void setFarmer(Farmer farmer) {
		this.farmer = farmer;
	}
	
}
