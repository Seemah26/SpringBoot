package com.bl.app.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bl.app.model.Studentinfo;
import com.bl.app.repository.StudentRepository;
import com.bl.app.service.StudentService;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentRepository studentRepository;

	@Override
	public Studentinfo saveStudents(Studentinfo student) {
		return studentRepository.save(student);

	}

	@Override
	public Optional<Studentinfo> findById(long id) {
		return studentRepository.findById(id);
	}

	@Override
	public List<Studentinfo> findAll() {
		return (List<Studentinfo>) studentRepository.findAll();
	}

	@Override
	public Studentinfo updateStudent(Studentinfo student) {
		return studentRepository.save(student);
	}

	@Override
	public void deleteById(long id) {

		studentRepository.deleteById(id);

	}

}
