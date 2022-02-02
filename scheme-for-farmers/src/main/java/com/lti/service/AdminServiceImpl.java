package com.lti.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lti.entity.AdminRequest;
import com.lti.entity.Bidder;
import com.lti.entity.Farmer;
import com.lti.entity.SellRequest;
import com.lti.entity.Status;
import com.lti.entity.UserType;
import com.lti.exception.AdminRequestException;
import com.lti.repository.AdminRequestRepository;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminRequestRepository adminRequestRepository;

	public AdminRequest approveStatus(AdminRequest admin) {
		
		AdminRequest a = (AdminRequest) adminRequestRepository.save(admin);
		
		if(admin.getType() == UserType.FARMER)
		{
			Farmer fm = (Farmer)adminRequestRepository.fetch(Farmer.class, admin.getId());
		    Status status = admin.isStatus() ? Status.APPROVED : Status.REJECTED ; 
			fm.setStatus(status);
			adminRequestRepository.save(fm);
			return a;
		}
		else if(admin.getType() == UserType.BIDDER)
		{
			Bidder b = (Bidder)adminRequestRepository.fetch(Bidder.class, admin.getId());
		    Status status = admin.isStatus() ? Status.APPROVED : Status.REJECTED ; 
			b.setStatus(status);
			adminRequestRepository.save(b);
			return a;
		}
		else if(admin.getType() == UserType.SELLREQUEST)
		{
			int id = admin.getId();
			SellRequest fm = (SellRequest)adminRequestRepository.fetch(SellRequest.class, admin.getId());
		    Status status = admin.isStatus() ? Status.APPROVED : Status.REJECTED ; 
			fm.setStatus(status);
			adminRequestRepository.save(fm);
			return a;
		}
		else
			throw new AdminRequestException("Can't Process Request");		
		
		
	}

	public int getRegistrationId(AdminRequest obj) {
		AdminRequest updatedObj = (AdminRequest) adminRequestRepository.save(obj);	
		return updatedObj.getId();
	}
	
	public List<AdminRequest> getAllPendingRequests()
	{
		List<AdminRequest> list = adminRequestRepository.fetchAllPendingRequest();
		if(list == null)
			throw new AdminRequestException("No Pending Requests Available");
		return list;
	}

	@Override
	public void UpdateAdminStatus(AdminRequest admin) {
		
		adminRequestRepository.save(admin);
	}

	@Override
	public List getRecentExpiredSellRequests(LocalDate yest) {
		List<AdminRequest> list = adminRequestRepository.getExpiredSellRequests(yest);
		if(list == null)
			throw new AdminRequestException("No Pending Requests Available");
		return list;
	}

	@Override
	public  String getEmailId(int i) {
		// TODO Auto-generated method stub
		try {
		return adminRequestRepository.getE(i);
		}
		catch(RuntimeException e) {
//			List <String> a = new ArrayList<>();
			return "";
		}
		
	}

}
