package com.bl.app.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Note")
public class Note {
	@Id
	@Column(name="noteId")
    private int noteId;
	
	@Column(name="")
	private String title;
	
	@Column(name="description")
	private String description;
	
	@Column(name="createTable")
	private String createTable;
	

	@Column(name="updateTime")
	private Timestamp updateTime;
     
	@Column(name="isArchieve")
	private boolean isArchieve;
	
	@Column(name="inTrash")
	private boolean inTrash;
	
	@Column(name="isPinned")
	private boolean isPinned;
	
	@Column(name="id")
	private int id;

	public int getNoteId() {
		return noteId;
	}

	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreateTable() {
		return createTable;
	}

	public void setCreateTable(String createTable) {
		this.createTable = createTable;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public boolean isArchieve() {
		return isArchieve;
	}

	public void setArchieve(boolean isArchieve) {
		this.isArchieve = isArchieve;
	}

	public boolean isInTrash() {
		return inTrash;
	}

	public void setInTrash(boolean inTrash) {
		this.inTrash = inTrash;
	}

	public boolean isPinned() {
		return isPinned;
	}

	public void setPinned(boolean isPinned) {
		this.isPinned = isPinned;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
