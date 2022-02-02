package com.lti.service;

import java.time.LocalDate;
import java.util.List;

import com.lti.entity.AdminRequest;

public interface AdminService {

	//show-pending-details i.e bidder, farmer and sellRequest[1.select only the pending-request 2.remove the record after approved]
	
	
	
	//approve bidder, farmer and sellRequest  [ 1. update query to change status using requestid]
	public AdminRequest approveStatus(AdminRequest admin );
	
	public int getRegistrationId(AdminRequest obj);
	
	public List<AdminRequest> getAllPendingRequests();
	
	public void UpdateAdminStatus(AdminRequest admin);

	public List getRecentExpiredSellRequests(LocalDate yest);

	public String getEmailId(int i);
	
}
