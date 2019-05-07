package com.bl.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.bl.app.model.Studentinfo;

@RepositoryRestResource
public interface StudentRepository extends CrudRepository<Studentinfo, Long> {

}
