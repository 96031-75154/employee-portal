package com.emp.main.service;

import java.io.FileNotFoundException;

import com.itextpdf.text.DocumentException;

public interface PDFService {
	public byte[] generateEmployeePdfReport() throws FileNotFoundException, DocumentException;
}
