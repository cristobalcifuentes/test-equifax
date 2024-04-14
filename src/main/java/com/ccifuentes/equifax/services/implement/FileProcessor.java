package com.ccifuentes.equifax.services.implement;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ccifuentes.equifax.models.dtos.DocumentDTO;
import com.ccifuentes.equifax.models.dtos.FileDetailDTO;
import com.ccifuentes.equifax.models.entity.Document;
import com.ccifuentes.equifax.models.entity.FileDetail;
import com.ccifuentes.equifax.services.interfaces.IDocumentService;
import com.ccifuentes.equifax.services.interfaces.IFileProcessor;
import com.ccifuentes.equifax.services.interfaces.IReadFile;

@Service
public class FileProcessor implements IFileProcessor {

	private final IReadFile readFile;
	private final IDocumentService documentService;

	public FileProcessor(IReadFile readFile, IDocumentService documentService) {
		super();
		this.readFile = readFile;
		this.documentService = documentService;
	}

	@Override
	public DocumentDTO processFile(MultipartFile file) throws IOException {
		List<FileDetail> fileDetails = readFile.readFile(file);
		Document document = generateEntitiesToSave(fileDetails);
		Document documentGenerated = documentService.saveDocumentwithFileDetails(document);
		return parseDocumentEntityToDto(documentGenerated);
	}

	private Document generateEntitiesToSave(List<FileDetail> fileDetails) {
		Document document = new Document();
		document.setDate(new Date());
		document.setQuantityRecords(fileDetails.size());
		document.setFileDetails(fileDetails);
		return document;
	}

	private DocumentDTO parseDocumentEntityToDto(Document document) {
	    DocumentDTO documentDTO = new DocumentDTO();
	    documentDTO.setId(document.getId());
	    documentDTO.setDate(document.getDate());
	    documentDTO.setQuantityRecords(document.getQuantityRecords());
	    documentDTO.setFileDetails(parseListFileDetailEntityToDto(document.getFileDetails()));
	    return documentDTO;
	}

	private List<FileDetailDTO> parseListFileDetailEntityToDto(List<FileDetail> fileDetails) {
	    return fileDetails.stream()
	            .map(this::parseFileDetailEntityToDto) 
	            .collect(Collectors.toList());
	}

	private FileDetailDTO parseFileDetailEntityToDto(FileDetail fileDetail) {
	    FileDetailDTO fileDetailDTO = new FileDetailDTO();
	    fileDetailDTO.setId(fileDetail.getId());
	    fileDetailDTO.setName(fileDetail.getName());
	    fileDetailDTO.setRut(fileDetail.getRut());
	    fileDetailDTO.setAmount(fileDetail.getAmount());
	    fileDetailDTO.setDescription(fileDetail.getDescription());
	    fileDetailDTO.setId_document(fileDetail.getDocument().getId()); 
	    return fileDetailDTO;
	}

}
