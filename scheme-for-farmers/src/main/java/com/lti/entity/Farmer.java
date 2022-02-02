package com.lti.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_farmer")
public class Farmer {

	@Id
//	@GeneratedValue
	private int id;

	private String name;
	private String address;
	private String area;
	private String city;
	private String pincode;
	private String email;
	private String mobileNo;
	private String aadharNo;
	private String ifscCode;
	private String certificate;
	private Status status;
	private String state;
	private String accountNo;
	
	@OneToMany(mappedBy = "farmer",fetch = FetchType.EAGER)
	private List<SellRequest> sellRequests;

	@JoinColumn(name="loginId")
	@OneToOne
	private LoginCredentials login;
	
	@JoinColumn(name="adminId")
	@OneToOne
	private AdminRequest adminRequest;
	
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

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getAadharNo() {
		return aadharNo;
	}

	public void setAadharNo(String aadharNo) {
		this.aadharNo = aadharNo;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	

	

	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<SellRequest> getSellRequests() {
		return sellRequests;
	}

	public void setSellRequests(List<SellRequest> sellRequests) {
		this.sellRequests = sellRequests;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	
	
}
