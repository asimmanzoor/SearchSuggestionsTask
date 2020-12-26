package com.searchsuggestions.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.searchsuggestions.hibernate.mapping.Cart;
import com.searchsuggestions.repository.CartRepository;

@Service
public class CartService {
	
	@Autowired
	private CartRepository cartRepository;

	public Cart saveCart(Cart cart) {
		return cartRepository.save(cart);
		
	}
}
