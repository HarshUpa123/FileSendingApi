package com.example.file.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.file.helper.FileHelper;

@RestController
public class FileUploadController {
	
	@Autowired
	FileHelper fileHelper;

	@PostMapping("/upload-file")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {

		// validation
		if (file.isEmpty()) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Request must contain file");
		}
		if (file.getContentType().equals("image/jpg")) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Only jpg files are allowed to upload");
		}
		
		boolean f = fileHelper.uploadFile(file);
		System.out.println("in th file helper");
		if(f) {
			return ResponseEntity.ok("File uploaded successfully.....!");
		}


		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong...!");
	}
}
