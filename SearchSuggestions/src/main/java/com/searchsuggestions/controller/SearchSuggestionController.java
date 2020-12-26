package com.searchsuggestions.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.searchsuggestions.hibernate.mapping.Cart;
import com.searchsuggestions.hibernate.mapping.Items;
import com.searchsuggestions.service.CartService;
import com.searchsuggestions.service.CityDirectoryService;

@RestController
@RequestMapping("/search_directory")
public class SearchSuggestionController {

	@Autowired
	private CityDirectoryService cityDirectoryService;
	
	@Autowired
	private CartService cartService;
	

	@GetMapping(value = "/suggest_cities", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> searchSuggestedCities(@Validated  @RequestParam(value = "start") String start,
			@RequestParam(value = "atmost", required = true, defaultValue = "1") 	int atmost) {
		/*
		 * List<String> result = cityDirectoryService.findByDistrictStartWith(atmost,
		 * start.toLowerCase()); if (CollectionUtils.isEmpty(result)) { return new
		 * ResponseEntity<>("No Result Found !", HttpStatus.NOT_FOUND); }
		 */
		Cart cart = Cart.builder().cartName("Mycart").build();
		Set<Items> items = new HashSet<>();
		items.add(Items.builder().itemName("abc1").cart(cart).build());
		items.add(Items.builder().itemName("abc2").cart(cart).build());
		items.add(Items.builder().itemName("abc3").cart(cart).build());
		items.add(Items.builder().itemName("abc4").cart(cart).build());

		cart.setItems(items);
		cartService.saveCart(cart);
		//return new ResponseEntity<>(result.stream().collect(Collectors.joining(System.lineSeparator())), HttpStatus.FOUND);
	    return null;
	}

}
