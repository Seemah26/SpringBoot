package com.bl.app.service;

import javax.servlet.http.HttpServletRequest;

import com.bl.app.model.Note;

public interface NoteService {
	
	public Note createNote(Note user, String token);

}
