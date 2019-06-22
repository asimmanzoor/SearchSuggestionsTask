package com.searchsuggestions.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.searchsuggestions.utils.FileDataUploadUtils;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/city_directory")
@Slf4j
public class CityDirectoryCSVUploderController {

	@Autowired
	private FileDataUploadUtils fileUploadService;

	@PostMapping(value = "/api/uploadfile", consumes= MediaType.MULTIPART_FORM_DATA_VALUE ,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile uploadfile) throws HttpMediaTypeNotSupportedException {

		if (uploadfile.isEmpty()) {
			return new ResponseEntity<>("Please select a file !", HttpStatus.BAD_REQUEST);
		}

		saveUploadedFiles(Arrays.asList(uploadfile));
		return new ResponseEntity<>("File Uploaded Successfully !", HttpStatus.OK);

	}

	/*
	 * Save uploaded file in db.
	 */
	private void saveUploadedFiles(List<MultipartFile> files) throws HttpMediaTypeNotSupportedException {

		for (MultipartFile file : files) {
			if (file.isEmpty()) {
				continue;
			}
			List<MediaType> mediaTypeList= Stream.of(new MediaType(".csv")).collect(Collectors.toList());
			MediaType mediaType = MediaType.valueOf(file.getContentType());
			try {
				if (!file.getOriginalFilename().endsWith(".csv")) {
					throw new HttpMediaTypeNotSupportedException( 
							mediaType,
							mediaTypeList, "Please upload only CSV format !");
				}
				fileUploadService.uploadFile(file.getInputStream(), file.getOriginalFilename());
			} catch (IOException e) {
				log.error(e.getMessage());
			}
			// doing for single file
			break;
		}
	}

}
