package com.ccifuentes.equifax.controllers;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ccifuentes.equifax.models.dtos.DocumentDTO;
import com.ccifuentes.equifax.services.interfaces.IFileProcessor;

@RestController
@RequestMapping("file")
public class FileReceiverController {

	private final IFileProcessor fileProcessor;

	public FileReceiverController(IFileProcessor fileProcessor) {
		this.fileProcessor = fileProcessor;
	}

	@PostMapping
	public ResponseEntity<DocumentDTO> receiveFile(@RequestParam("file") final MultipartFile file) {
		try {
			DocumentDTO documentGenerated = fileProcessor.processFile(file);
			return ResponseEntity.ok(documentGenerated);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatusCode.valueOf(500)).body(null);

		}

	}

}
