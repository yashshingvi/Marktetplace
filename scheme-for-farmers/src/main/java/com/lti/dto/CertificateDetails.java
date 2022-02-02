package com.lti.dto;

import org.springframework.web.multipart.MultipartFile;

public class CertificateDetails {
	private int id;
	private MultipartFile certificate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public MultipartFile getCertificate() {
		return certificate;
	}

	public void setCertificate(MultipartFile certificate) {
		this.certificate = certificate;
	}

}
