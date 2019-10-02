package com.stackroute.keepnote.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

/*
 * The class "Note" will be acting as the data model for the note Table in the database. Please
 * note that this class is annotated with @Entity annotation. Hibernate will scan all package for 
 * any Java objects annotated with the @Entity annotation. If it finds any, then it will begin the 
 * process of looking through that particular Java object to recreate it as a table in your database.
 */

@Entity
public class Note {

	@Id
	@Column
	int noteId;
	@Column
	String noteTitle;
	@Column
	String noteStatus;
	@Column
	String noteContent;
	@Column
	LocalDateTime createdAt;

	public Note() {

	}

	public Note(int noteId, String noteTitle, String noteStatus, String noteContent, LocalDateTime createdAt) {
		this.noteId = noteId;
		this.noteTitle = noteTitle;
		this.noteStatus = noteStatus;
		this.noteContent = noteContent;
		this.createdAt = createdAt;
	}

	public int getNoteId() {

		return this.noteId;
	}

	public String getNoteTitle() {

		return this.noteTitle;
	}

	public String getNoteContent() {

		return this.noteContent;
	}

	public String getNoteStatus() {

		return this.noteStatus;
	}

	public void setNoteId(int parseInt) {
		this.noteId=parseInt;

	}

	public void setNoteTitle(String parameter) {
		this.noteTitle=parameter;
	}

	public void setNoteContent(String parameter) {
		this.noteContent=parameter;
	}

	public void setNoteStatus(String parameter) {
		this.noteStatus=parameter;
	}

	public void setCreatedAt(LocalDateTime now) {
		this.createdAt=now;
	}

}
