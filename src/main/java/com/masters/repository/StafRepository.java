package com.masters.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.masters.model.Staf;


@Repository
public interface StafRepository extends CrudRepository<Staf , Long>  {
	public Staf findById(long id);
}
