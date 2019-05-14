package com.bl.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bl.app.model.Studentinfo;
import com.bl.app.service.StudentService;


@RestController
public class StudentController {

	@Autowired
	StudentService studentService;

	// Create
	@RequestMapping(value = "/student", method = RequestMethod.POST)
	public Studentinfo createStudent(@RequestBody Studentinfo student) {

		return studentService.saveStudents(student);
	}

	// Read
	@RequestMapping(value = "/student/{id}", method = RequestMethod.GET)
	public Optional<Studentinfo> getStudentById(@PathVariable long id) {

		return studentService.findById(id);
	}

	@RequestMapping(value = "/students", method = RequestMethod.GET)
	public List<Studentinfo> getAllStudents() {
		System.out.println("### I am at Controller:");

		return (List<Studentinfo>) studentService.findAll();
	}

	// Update
	@RequestMapping(value = "/student", method = RequestMethod.PUT)
	public Studentinfo updateStudent(@RequestBody Studentinfo student) {

		return studentService.updateStudent(student);
	}

	// Delete
	@RequestMapping(value = "/student/{id}", method = RequestMethod.DELETE)
	public void deleteStudent(@PathVariable long id) {

		studentService.deleteById(id);

	}
}
