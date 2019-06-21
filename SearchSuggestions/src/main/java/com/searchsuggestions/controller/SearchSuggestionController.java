package com.searchsuggestions.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.Digits;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.searchsuggestions.service.CityDirectoryService;

@RestController
@RequestMapping("/search_directory")
public class SearchSuggestionController {

	@Autowired
	private CityDirectoryService cityDirectoryService;

	@GetMapping(value = "/suggest_cities", produces = MediaType.TEXT_PLAIN_VALUE)
	public String searchSuggestedCities(@Validated  @RequestParam(value = "start") String start,
			@RequestParam(value = "atmost") /* @Valid @Digits(integer = 1, fraction = 0) */	int atmost) {
		List<String> result = cityDirectoryService.findByDistrictStartWith(atmost, start.toLowerCase());
		if (CollectionUtils.isEmpty(result)) {
			return "No Result Found !";
		}
		return result.stream().collect(Collectors.joining(System.lineSeparator()));

	}

}
