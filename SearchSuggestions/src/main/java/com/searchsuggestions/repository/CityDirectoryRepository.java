package com.searchsuggestions.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.searchsuggestions.models.CityDirectory;

@Repository
public interface CityDirectoryRepository extends CrudRepository<CityDirectory, Long> {

}
