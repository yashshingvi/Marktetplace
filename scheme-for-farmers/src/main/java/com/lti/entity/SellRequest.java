package com.lti.entity;

import java.time.LocalDate;
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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tbl_sellrequest")
public class SellRequest {

	@Id
//	@GeneratedValue
	private int id;
	
//	private int farmerId;
	private String cropName;
	private String cropType;
	private double basePrice;
	private int quantity;
	private int phValue;
	private String fertlizerType;
	private Status status;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate startDate;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate endDate;

	@OneToMany(mappedBy = "sellRequest")
	private List<BidInfo> bidInfos;
	
	@JoinColumn(name="adminId")
	@OneToOne
	private AdminRequest adminRequest;
	
	@JoinColumn(name="farmerId")
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnore
	private Farmer farmer;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCropName() {
		return cropName;
	}

	public void setCropName(String cropName) {
		this.cropName = cropName;
	}

	public String getCropType() {
		return cropType;
	}

	public void setCropType(String cropType) {
		this.cropType = cropType;
	}

	public double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPhValue() {
		return phValue;
	}

	public void setPhValue(int phValue) {
		this.phValue = phValue;
	}

	public String getFertlizerType() {
		return fertlizerType;
	}

	public void setFertlizerType(String fertlizerType) {
		this.fertlizerType = fertlizerType;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}


	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public List<BidInfo> getBidInfos() {
		return bidInfos;
	}

	public void setBidInfos(List<BidInfo> bidInfos) {
		this.bidInfos = bidInfos;
	}

	public AdminRequest getAdminRequest() {
		return adminRequest;
	}

	public void setAdminRequest(AdminRequest adminRequest) {
		this.adminRequest = adminRequest;
	}

	public Farmer getFarmer() {
		return farmer;
	}

	public void setFarmer(Farmer farmer) {
		this.farmer = farmer;
	}
	
	
}
