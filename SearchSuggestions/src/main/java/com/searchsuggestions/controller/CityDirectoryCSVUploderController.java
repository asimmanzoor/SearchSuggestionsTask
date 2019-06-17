package com.searchsuggestions.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.searchsuggestions.utils.FileDataUploadUtils;

@RestController
@RequestMapping("/city_directory")
public class CityDirectoryCSVUploderController {
	
	@Autowired
	private FileDataUploadUtils fileUploadService;
	
	@PostMapping(value = "/api/uploadfile", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile uploadfile) {
		
		if (uploadfile.isEmpty()) {
			return new ResponseEntity<>("Please select a file !" , HttpStatus.BAD_REQUEST);
		}

		try {
			 saveUploadedFiles(Arrays.asList(uploadfile));

		} catch (IOException e) {
			return new ResponseEntity<>("Error occured while saving file(s)", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>("File Uploaded Successfully !" , HttpStatus.OK);
		
	}




/*
 * Save uploaded file in db.
 */
private void saveUploadedFiles(List<MultipartFile> files) throws IOException {

	for (MultipartFile file : files) {
		if (file.isEmpty()) {
			continue;
		}
		try {
			fileUploadService.uploadFile(file.getInputStream(), file.getOriginalFilename());
		} catch (IOException e) {
			e.printStackTrace();
		}
		//doing for single file 
		break;
	}
}



}
