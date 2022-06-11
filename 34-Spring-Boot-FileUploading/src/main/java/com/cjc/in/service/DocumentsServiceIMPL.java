package com.cjc.in.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjc.in.model.Document;
import com.cjc.in.repository.DocumentsRepository;

@Service
public class DocumentsServiceIMPL implements DocumentService{

	@Autowired
	  private DocumentsRepository repository;
	  
	
	@Override
	public void insertDocuments(Document document) {
		
		repository.save(document);
		
	}

}
