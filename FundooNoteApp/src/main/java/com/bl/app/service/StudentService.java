package com.bl.app.service;

import java.util.List;
import java.util.Optional;

import com.bl.app.model.Studentinfo;

public interface StudentService {

	Studentinfo saveStudents(Studentinfo s);

    Optional<Studentinfo> findById(long id);

	List<Studentinfo> findAll();

	Studentinfo updateStudent(Studentinfo student);

	void deleteById(long id);
}
