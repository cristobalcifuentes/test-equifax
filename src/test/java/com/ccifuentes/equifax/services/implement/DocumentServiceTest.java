package com.ccifuentes.equifax.services.implement;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ccifuentes.equifax.models.entity.Document;
import com.ccifuentes.equifax.models.entity.FileDetail;
import com.ccifuentes.equifax.repositorys.IDocumentRepository;
import com.ccifuentes.equifax.repositorys.IFileDetailRepository;

class DocumentServiceTest {

	private DocumentService documentService;
	private IDocumentRepository documentRepository;
	private IFileDetailRepository fileDetailRepository;

	@BeforeEach
	public void setUp() {
		documentRepository = mock(IDocumentRepository.class);
		fileDetailRepository = mock(IFileDetailRepository.class);
		documentService = new DocumentService(documentRepository, fileDetailRepository);
	}

	@Test
	public void testSaveDocumentWithFileDetails() {
		// Given
		Document document = new Document();
		List<FileDetail> fileDetails = new ArrayList<>();
		FileDetail fileDetail = new FileDetail();
		fileDetails.add(fileDetail);
		document.setFileDetails(fileDetails);

		when(documentRepository.save(any(Document.class))).thenReturn(document);
		when(fileDetailRepository.saveAll(anyList())).thenReturn(fileDetails);

		// When
		Document savedDocument = documentService.saveDocumentwithFileDetails(document);

		// Then
		assertNotNull(savedDocument);
		assertEquals(fileDetails, savedDocument.getFileDetails());
		verify(documentRepository, times(1)).save(any(Document.class));
		verify(fileDetailRepository, times(1)).saveAll(anyList());
	}

	@Test
	public void testSaveDocumentWithNoFileDetails() {
		// Given
		Document document = new Document();

		when(documentRepository.save(any(Document.class))).thenReturn(document);

		// When
		Document savedDocument = documentService.saveDocumentwithFileDetails(document);

		// Then
		assertNotNull(savedDocument);
		verify(documentRepository, times(1)).save(any(Document.class));
	}

	@Test
	public void testSetDocumentInFileDetails() {
		// Given
		Document document = new Document();
		FileDetail fileDetail = new FileDetail();
		List<FileDetail> fileDetails = new ArrayList<>();
		fileDetails.add(fileDetail);
		document.setFileDetails(fileDetails);

		// When
		List<FileDetail> result = documentService.setDocumentinFileDetails(document);

		// Then
		assertNotNull(result);
		assertEquals(fileDetails, result);
		assertEquals(document, fileDetail.getDocument());
	}

	@Test
	public void testSetDocumentInFileDetailsWithNullFileDetails() {
		// Given
		Document document = new Document();

		// When
		List<FileDetail> result = documentService.setDocumentinFileDetails(document);

		// Then
		assertNotNull(result);
		assertTrue(result.isEmpty());
	}

}
