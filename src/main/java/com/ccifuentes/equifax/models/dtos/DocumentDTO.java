package com.ccifuentes.equifax.models.dtos;

import java.util.Date;
import java.util.List;

public class DocumentDTO {

	private Long id;
	private Date date;
	private int quantityRecords;
	private List<FileDetailDTO> fileDetails;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getQuantityRecords() {
		return quantityRecords;
	}

	public void setQuantityRecords(int quantityRecords) {
		this.quantityRecords = quantityRecords;
	}

	public List<FileDetailDTO> getFileDetails() {
		return fileDetails;
	}

	public void setFileDetails(List<FileDetailDTO> fileDetails) {
		this.fileDetails = fileDetails;
	}
	
	

}
