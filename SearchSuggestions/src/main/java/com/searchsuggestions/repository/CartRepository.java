package com.searchsuggestions.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.searchsuggestions.hibernate.mapping.Cart;

@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {

}
