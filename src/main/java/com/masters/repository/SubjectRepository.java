package com.masters.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.masters.model.Subject;


@Repository
public interface SubjectRepository extends CrudRepository<Subject , Long> {
	
	public Subject findById(long id);
	public boolean existsByName(String name);
}
