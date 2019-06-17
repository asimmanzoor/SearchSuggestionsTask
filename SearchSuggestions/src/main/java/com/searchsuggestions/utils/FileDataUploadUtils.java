package com.searchsuggestions.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opencsv.CSVReader;
import com.searchsuggestions.models.CityDirectory;
import com.searchsuggestions.service.CityDirectoryService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FileDataUploadUtils {

	@Autowired
	private CityDirectoryService cityDirectoryService;

	public void uploadFile(InputStream inputStream, String string) throws IOException {
		log.info("Uploading file in progress: " + string);
		CSVReader reader = null;
		try {
			List<CityDirectory> cityDirectoryList = new ArrayList<>();

			reader = new CSVReader(new InputStreamReader(inputStream));
			String[] nextLine;
			reader.readNext(); // to avoid headers

			while ((nextLine = reader.readNext()) != null) {

				CityDirectory cityDirectory = CityDirectory.builder().circleName(nextLine[0]).regionName(nextLine[1])
						.divisionName(nextLine[2]).officeName(nextLine[3]).pinCode(nextLine[4]).officeType(nextLine[5])
						.delivery(nextLine[6]).district(nextLine[7]).stateName(nextLine[8]).build();

				cityDirectoryList.add(cityDirectory);
			}
			// persist all records
			cityDirectoryService.saveAll(cityDirectoryList);

			log.info("Uploading file is Done : " + string);

		} catch (FileNotFoundException ex) {
			log.error(ex.getMessage());
		} catch (IOException ex1) {
			log.error(ex1.getMessage());

		} finally {
			if (reader != null)
				reader.close();
		}

	}
}
