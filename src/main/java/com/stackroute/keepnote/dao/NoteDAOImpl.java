package com.stackroute.keepnote.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.stackroute.keepnote.model.Note;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/*
 * This class is implementing the NoteDAO interface. This class has to be annotated with @Repository
 * annotation.
 * @Repository - is an annotation that marks the specific class as a Data Access Object, thus 
 * 				 clarifying it's role.
 * @Transactional - The transactional annotation itself defines the scope of a single database 
 * 					transaction. The database transaction happens inside the scope of a persistence 
 * 					context.  
 * */


@Repository
public class NoteDAOImpl implements NoteDAO {

	 /*
	 * Autowiring should be implemented for the SessionFactory.
	 */
	 @Autowired
	 SessionFactory sessionFactory;

	public NoteDAOImpl(SessionFactory sessionFactory) {

		this.sessionFactory=sessionFactory;

	}

	/*
	 * Save the note in the database(note) table.
	 */

	public boolean saveNote(Note note) {

		sessionFactory.getCurrentSession().save(note);
		return true;

	}

	/*
	 * Remove the note from the database(note) table.
	 */

	public boolean deleteNote(int noteId) {

		sessionFactory.getCurrentSession().delete(getNoteById(noteId));
		return true;

	}

	/*
	 * retrieve all existing notes sorted by created Date in descending
	 * order(showing latest note first)
	 */
	public List<Note> getAllNotes() {

		return sessionFactory.getCurrentSession().createQuery("from Note").list();

	}

	/*
	 * retrieve specific note from the database(note) table
	 */
	public Note getNoteById(int noteId) {

		return sessionFactory.getCurrentSession().get(Note.class,noteId);
	}

	/* Update existing note */

	public boolean UpdateNote(Note note) {
		sessionFactory.getCurrentSession().update(note);
		return true;

	}

}
