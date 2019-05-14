package com.bl.app.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bl.app.model.Note;
import com.bl.app.repository.NoteRepository;
import com.bl.app.service.NoteService;
import com.bl.app.utility.JsonToken;
@Service
public class NoteServiceImpl implements NoteService {
    @Autowired
    private NoteRepository noteRepository;
   
    @Autowired
    private JsonToken jsontoken;
   
	@Override
	public Note createNote(Note note, String token ) {
		System.out.println("####->"+token);
		
		int id=jsontoken.tokenVerification(token);
		note.setId(id);
		return noteRepository.save(note);
	}
}
