package com.cjc.in;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cjc.in.model.Document;
import com.cjc.in.service.DocumentService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/docApi")
public class DocumentController {

	@Autowired
	 private DocumentService service;
	 
	
	@GetMapping("/check")
	public String check() {
		return "Ruuning OK";
	}
	
	@RequestMapping(value = "/uploadDocument",
			method = RequestMethod.POST,
			
			consumes = MediaType.MULTIPART_FORM_DATA_VALUE
			)
	public ResponseEntity<String> uploadDocuments(
			@RequestPart(required = true , value = "photo") MultipartFile f1,
			@RequestPart(required = true,value = "signature") MultipartFile f2,
			@RequestPart(required = true,value = "pan") MultipartFile f3
		
			) throws IOException {
		
		   Document dc = new Document();
		   dc.setPhoto(f1.getBytes());
		   dc.setSignature(f2.getBytes());
		   dc.setPan(f3.getBytes());
   
		   service.insertDocuments(dc);
		   
		return new ResponseEntity<String>("Documents Uploded ",HttpStatus.CREATED);
	}
}
