package com.lti.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tbl_bidder")
public class Bidder {
	@Id
	private int id;
	private String name;
	private String address;
	private String city;
	private String state;
	private String pincode;
	private String mobileNo;
	private String adhaarNo;
	private String accountNo;
	private String traderLicense;
	private String ifscCode;
	private Status status;
	private String email;
//	private String Login_id;
	@JoinColumn(name="loginId")
	@OneToOne
	private LoginCredentials login;
	
	@JoinColumn(name="adminId")
	@OneToOne
	private AdminRequest adminRequest;
	
	@OneToMany(mappedBy = "bidder", cascade = CascadeType.MERGE)
	private List<BidInfo> bidInfos;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getAdhaarNo() {
		return adhaarNo;
	}
	public void setAdhaarNo(String adhaarNo) {
		this.adhaarNo = adhaarNo;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getTraderLicense() {
		return traderLicense;
	}
	public void setTraderLicense(String traderLicense) {
		this.traderLicense = traderLicense;
	}
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LoginCredentials getLogin() {
		return login;
	}
	public void setLogin(LoginCredentials login) {
		this.login = login;
	}
	public AdminRequest getAdminRequest() {
		return adminRequest;
	}
	public void setAdminRequest(AdminRequest adminRequest) {
		this.adminRequest = adminRequest;
	}
	public List<BidInfo> getBidInfos() {
		return bidInfos;
	}
	public void setBidInfos(List<BidInfo> bidInfos) {
		this.bidInfos = bidInfos;
	}
	
}
