package com.searchsuggestions.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.searchsuggestions.models.CityDirectory;
import com.searchsuggestions.repository.CityDirectoryRepository;
import com.searchsuggestions.repository.CityDirectoryRepositoryImpl;

@Service
public class CityDirectoryService {
	
	@Autowired
	private CityDirectoryRepository cityDirectoryRepository;
	
	@Autowired
	private CityDirectoryRepositoryImpl cityDirectoryRepositoryImpl;
	
	public void saveAll (List<CityDirectory> list) {
		cityDirectoryRepository.saveAll(list);
	}
	
	/*
	 * public List<String> findByDistrictStartWith(String district) { return
	 * cityDirectoryRepository.findByDistrictStartWith(district); }
	 */
	
	/*
	 * public List<String> findByDistrictStartWith(int topMost, String district) {
	 * return cityDirectoryRepository.findTopByDistrictStartingWith(district); }
	 */
	
	public List<String> findByDistrictStartWith(int topMost, String district) {
		return cityDirectoryRepositoryImpl.findByDistrict(topMost, district);
	}
	
	

}
