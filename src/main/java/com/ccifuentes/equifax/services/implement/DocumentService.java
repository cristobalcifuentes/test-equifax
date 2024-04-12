package com.ccifuentes.equifax.services.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ccifuentes.equifax.models.entity.Document;
import com.ccifuentes.equifax.models.entity.FileDetail;
import com.ccifuentes.equifax.repositorys.IDocumentRepository;
import com.ccifuentes.equifax.repositorys.IFileDetailRepository;
import com.ccifuentes.equifax.services.interfaces.IDocumentService;

import jakarta.transaction.Transactional;

@Service
public class DocumentService implements IDocumentService {

	private final IDocumentRepository documentRepository;
	private final IFileDetailRepository fileDetailRepository;

	public DocumentService(IDocumentRepository documentRepository, IFileDetailRepository fileDetailRepository) {
		this.documentRepository = documentRepository;
		this.fileDetailRepository = fileDetailRepository;
	}

	@Override
	@Transactional
	public Document saveDocumentwithFileDetails(Document document) {
		Document documentGenerated = documentRepository.save(document);
		List<FileDetail> fileDetailsWithDocument = setDocumentinFileDetails(documentGenerated);
		List<FileDetail> fileDetailGenerated = fileDetailRepository.saveAll(fileDetailsWithDocument);
		documentGenerated.setFileDetails(fileDetailGenerated);
		return documentGenerated;
	}

	List<FileDetail> setDocumentinFileDetails(Document document) {
		List<FileDetail> fileDetailsWithDocument = new ArrayList<FileDetail>();
		if (document.getFileDetails() != null) {
			for (FileDetail fileDetail : document.getFileDetails()) {
				fileDetail.setDocument(document);
				fileDetailsWithDocument.add(fileDetail);
			}
		}
		return fileDetailsWithDocument;
	}

}
