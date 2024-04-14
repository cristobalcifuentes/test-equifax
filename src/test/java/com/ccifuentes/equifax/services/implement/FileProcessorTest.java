package com.ccifuentes.equifax.services.implement;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import com.ccifuentes.equifax.models.dtos.DocumentDTO;
import com.ccifuentes.equifax.models.entity.Document;
import com.ccifuentes.equifax.models.entity.FileDetail;
import com.ccifuentes.equifax.services.interfaces.IDocumentService;
import com.ccifuentes.equifax.services.interfaces.IReadFile;

class FileProcessorTest {

    private FileProcessor fileProcessor;
    private IReadFile readFile;
    private IDocumentService documentService;

    @BeforeEach
    public void setUp() {
        readFile = mock(ReadFile.class); // Mock de ReadFile
        documentService = mock(IDocumentService.class);
        fileProcessor = new FileProcessor(readFile, documentService);
    }

    @Test
    public void testProcessFile() throws IOException {

    	MockMultipartFile mockFile = new MockMultipartFile("file", "test.xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "mock content".getBytes());
        List<FileDetail> fileDetails = new ArrayList<>();

        FileDetail fileDetail1 = new FileDetail();
        fileDetail1.setName("John Doe");
        fileDetail1.setRut("123456789");
        fileDetail1.setAmount(BigDecimal.valueOf(1000));
        fileDetail1.setDescription("Description 1");
        fileDetails.add(fileDetail1);

        FileDetail fileDetail2 = new FileDetail();
        fileDetail2.setName("Jane Smith");
        fileDetail2.setRut("987654321");
        fileDetail2.setAmount(BigDecimal.valueOf(2000));
        fileDetail2.setDescription("Description 2");
        fileDetails.add(fileDetail2);

        when(readFile.readFile(mockFile)).thenReturn(fileDetails);


        Document document = new Document();
        document.setId(1L);
        document.setDate(new Date());
        document.setQuantityRecords(fileDetails.size());
        document.setFileDetails(fileDetails);
        when(documentService.saveDocumentwithFileDetails(any(Document.class))).thenReturn(document);

        DocumentDTO result = fileProcessor.processFile(mockFile);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(fileDetails.size(), result.getQuantityRecords());
        
    }

}
