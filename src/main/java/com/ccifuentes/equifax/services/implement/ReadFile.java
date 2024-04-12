package com.ccifuentes.equifax.services.implement;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ccifuentes.equifax.models.entity.FileDetail;
import com.ccifuentes.equifax.services.interfaces.IReadFile;

@Service
public class ReadFile implements IReadFile {

	@Override
	public List<FileDetail> readFile(MultipartFile file) throws IOException {
		try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
			Sheet sheet = workbook.getSheetAt(0); // Suponiendo que el archivo tiene solo una hoja
			List<FileDetail> fileDetails = new ArrayList<FileDetail>();
			boolean firstRow = true; // bandera que nos ayudara a saltar la primera fila de encabezados

			for (Row row : sheet) {
				// Saltamos la primera fila que contiene los encabezados
				if (firstRow) {
					firstRow = false;
					continue;
				}
				FileDetail fileDetail = new FileDetail();
				if (cellIsNotnull(row.getCell(0))) fileDetail.setName(row.getCell(0).getStringCellValue());
				if (cellIsNotnull(row.getCell(1))) fileDetail.setRut(row.getCell(1).getStringCellValue());
				if (cellIsNotnull(row.getCell(2))) fileDetail.setAmount(BigDecimal.valueOf(row.getCell(2).getNumericCellValue()));
				if (cellIsNotnull(row.getCell(3))) fileDetail.setDescription(row.getCell(3).getStringCellValue());
				if (isNotEmptyRow(fileDetail)) fileDetails.add(fileDetail);

			}
			return fileDetails;
		}

	}

	private boolean cellIsNotnull(Cell cell) {
		if (cell != null)	return true;
		else return false;
	}
	
	private boolean isNotEmptyRow(FileDetail fileDetail) {
		if (fileDetail.getName() == null) return false;
		if (fileDetail.getRut() == null) return false;
		if (fileDetail.getDescription() == null) return false;
		return true;
	}
}
