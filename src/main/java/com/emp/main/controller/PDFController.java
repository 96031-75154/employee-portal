package com.emp.main.controller;

import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emp.main.service.PDFService;
import com.itextpdf.text.DocumentException;

@RestController
public class PDFController {
	
	@Autowired
	private PDFService pdfService;
	
	/*
	 * API for generate Employee Invoice
	 */
	@PostMapping("/generateEmployeePdfReport")
	public ResponseEntity<byte[]> generateEmployeePdfReport() throws FileNotFoundException, DocumentException {
		byte[] pdfBytes = pdfService.generateEmployeePdfReport();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=employeeinvoice.pdf");
		return ResponseEntity
				.ok()
				.headers(headers)
				.contentType(MediaType.APPLICATION_PDF)
				.body(pdfBytes);
	}
}
