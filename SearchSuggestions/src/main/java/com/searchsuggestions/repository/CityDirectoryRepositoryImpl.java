package com.searchsuggestions.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

@Repository
public class CityDirectoryRepositoryImpl {

	@PersistenceContext
	private EntityManager entityManager;

	public List<String> findByDistrict(int limit, String district) {
		TypedQuery<String> q = entityManager.createQuery(
				"select distinct LOWER(cd.district) from CityDirectory cd where LOWER(district) like :district order by LOWER(cd.district)", String.class);
		q.setParameter("district", district + "%");
		q.setFirstResult(0);
		q.setMaxResults(limit);
		return q.getResultList();
	}
}