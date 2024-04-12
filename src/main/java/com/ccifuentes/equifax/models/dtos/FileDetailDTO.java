package com.ccifuentes.equifax.models.dtos;

import java.math.BigDecimal;

public class FileDetailDTO {

	private Long id;
	private String name;
	private String rut;
	private BigDecimal amount;
	private String description;
	private Long id_document;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getRut() {
		return rut;
	}
	
	public void setRut(String rut) {
		this.rut = rut;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}
	
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Long getId_document() {
		return id_document;
	}
	
	public void setId_document(Long id_document) {
		this.id_document = id_document;
	}
	
	
	

}
