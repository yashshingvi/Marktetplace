package com.lti.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.CertificateDetails;
import com.lti.dto.HistoryDto;
import com.lti.dto.Status;
import com.lti.dto.Status.StatusType;
import com.lti.entity.Farmer;
import com.lti.entity.LoginCredentials;
import com.lti.entity.SellRequest;
import com.lti.exception.FarmerException;
import com.lti.repository.SellRequestRepository;
import com.lti.service.FarmerService;

@RestController
@CrossOrigin
public class FarmerController {
	
	@Autowired
	private FarmerService farmerService;
	
	@GetMapping("/view-profile-farmer")
	public Farmer getFarmerDetails(@RequestParam("id") int id) {
		 return farmerService.getFarmerDetails(id);
	}
	
	@PostMapping("/register-farmer")
	//@PostMapping(path="/register-farmer",consumes="application/json" , produces="text/plain")
	public @ResponseBody Status registration(@RequestBody Farmer fm) {
		try {
			int id=farmerService.registerFarmer(fm);
			//return "Registration successful your customer id is "+id;
			//the above return statement is bad, rather we should return it in JSON format
			
			Status status=new Status();
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Registration Successful");
			
			
			return status;
		}
		catch(FarmerException e) {
			Status status=new Status();
			status.setStatus(StatusType.FAILED);
			status.setMessage(e.getMessage());
			return status;
		}
	
	}
	

	@PostMapping("/edit-farmer")
	public Status editFarmerDetails(@RequestBody Farmer f) {
		Status status = new Status();
		try { 
		Farmer fnew = farmerService.editFarmerDetails(f);
		 status.setMessage("Updated Successfully !");
		 status.setStatus(StatusType.SUCCESS);
		 return status;
		 
		} 
		 catch(FarmerException e) {
				//Status status=new Status();
				status.setStatus(StatusType.FAILED);
				status.setMessage(e.getMessage());
				return status;
			}
	}
	
	
	@GetMapping("/view-marketplace")
	public List<SellRequest> getMarketPlaceDetails(@RequestParam("id") int id) {
		 return null;
	}
	
	@GetMapping("/sellhistory")
	//closed bids and farmerId = ?
	public List<HistoryDto> getHistory(@RequestParam("id") int id){
		List<HistoryDto> list = farmerService.getSellHistory(id);
		return list;
	}
	
	@PostMapping("/certificate-upload")
	public Status upload( CertificateDetails certificateDetails) {
		int id = certificateDetails.getId();
		System.out.println(id);
		String imgUploadLocation = "E:/uploads/";
		System.out.println("aaa");
		String uploadedFileName = certificateDetails.getCertificate().getOriginalFilename();
		System.out.println("dd");
		String newFileName = id + "-" + uploadedFileName;
		String targetFileName = imgUploadLocation + newFileName;
		try {
			FileCopyUtils.copy(certificateDetails.getCertificate().getInputStream(), new FileOutputStream(targetFileName));
		} catch(IOException e) {
			//e.printStackTrace(); //hoping no error would occur
			Status status = new Status();
			status.setStatus(StatusType.FAILED);
			status.setMessage("File upload failed!");
			return status;
		}
		
		farmerService.updateCertificate(id, newFileName);
		Status status = new Status();
		status.setStatus(StatusType.SUCCESS);
		status.setMessage("Certificate updated!");
		return status;
	}
	
	@GetMapping("/profile")
	public Farmer profile(@RequestParam("id") int id, HttpServletRequest request) {
		Farmer farmer = farmerService.get(id);
	
		//the problem is that the image is in some another folder outside this project
		//because of this, on the client we will not be able to access it by default
		//we need to write the code to copy the image from d:/uploads folder temporarily into this project of ours
	
		//reading the project's deployed location
		String projPath = request.getServletContext().getRealPath("/");
		String tempDownloadPath = projPath + "/downloads/";
		//creating this downloads folder in case if it doesn't exist
		File f = new File(tempDownloadPath);
		if(!f.exists())
			f.mkdir();
		
		//the target location where we will save the profile pic of the customer
		String targetFile = tempDownloadPath + farmer.getCertificate();
		
		//reading the original location where the image is present
		String uploadedImagesPath = "d:/uploads/";
		String sourceFile = uploadedImagesPath + farmer.getCertificate();
		
		try {
			FileCopyUtils.copy(new File(sourceFile), new File(targetFile));
		} catch(IOException e) {
			e.printStackTrace(); //hoping for no error will occur
		}
		
		return farmer;
	}


	
	

}
