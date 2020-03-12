package com.masters.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.masters.model.Classes;


@Repository
public interface ClassRepository extends CrudRepository<Classes , Long>  {
	
	public Classes findAllById(long id);
	public boolean existsByName(String name);

}
