package com.ccifuentes.equifax.models.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

@Entity
public class Document {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date date;
	private int quantityRecords;
	
	@Transient
    private List<FileDetail> fileDetails;

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

	public List<FileDetail> getFileDetails() {
		return fileDetails;
	}

	public void setFileDetails(List<FileDetail> fileDetails) {
		this.fileDetails = fileDetails;
	}
	
	

}
