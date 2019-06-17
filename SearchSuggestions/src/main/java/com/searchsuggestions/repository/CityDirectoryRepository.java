package com.searchsuggestions.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.searchsuggestions.models.CityDirectory;

@Repository
public interface CityDirectoryRepository extends CrudRepository<CityDirectory, Long> {
	
	@Query("select distinct cd.district from CityDirectory cd where LOWER(district) like :district%  order by district")
	List<String> findByDistrictStartWith(@Param(value = "district") String district);

}
