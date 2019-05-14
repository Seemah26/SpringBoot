package com.bl.app.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bl.app.model.Note;
import com.bl.app.service.NoteService;

@RestController
public class NoteController {
	@Autowired
	NoteService noteService;
	
	// IN pre-request-script PASS
	/*
	 * var date=new Date();
	 * postman.setEnvironmentVariable("createdOn",date.toISOString());
	 */
    //IN HEADER PASS TOKEN,GENERATED AFTER LOGIN
	
	 @RequestMapping(value = "/createnote", method = RequestMethod.POST)
	    public Note createNote(@RequestBody Note note,HttpServletRequest request) {

	        return noteService.createNote(note,request.getHeader("token"));
	    }

}
