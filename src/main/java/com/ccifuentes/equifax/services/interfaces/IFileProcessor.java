package com.ccifuentes.equifax.services.interfaces;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.ccifuentes.equifax.models.dtos.DocumentDTO;

public interface IFileProcessor {
	
	public DocumentDTO processFile(MultipartFile file) throws IOException;

}
