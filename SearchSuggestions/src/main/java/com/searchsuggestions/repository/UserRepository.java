package com.searchsuggestions.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.searchsuggestions.hibernate.mapping.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	
	
	User findFirstByAddressCity(String city);
	
	@Query("select u from User u where u.address.city = :city")
	List<User> findUsersByCity(@Param("city") String city);

}
