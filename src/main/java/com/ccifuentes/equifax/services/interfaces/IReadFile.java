package com.ccifuentes.equifax.services.interfaces;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ccifuentes.equifax.models.entity.FileDetail;

public interface IReadFile {
	
	 public  List<FileDetail> readFile(MultipartFile file) throws IOException;

}
