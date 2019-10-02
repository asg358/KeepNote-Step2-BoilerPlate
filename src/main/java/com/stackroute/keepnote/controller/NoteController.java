package com.stackroute.keepnote.controller;

/*
 * Annotate the class with @Controller annotation.@Controller annotation is used to mark 
 * any POJO class as a controller so that Spring can recognize this class as a Controller
 */

import com.stackroute.keepnote.dao.NoteDAO;
import com.stackroute.keepnote.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class NoteController {
	/*
	 * From the problem statement, we can understand that the application requires
	 * us to implement the following functionalities.
	 * 
	 * 1. display the list of existing notes from the persistence data. Each note
	 * should contain Note Id, title, content, status and created date. 
	 * 2. Add a new note which should contain the note id, title, content and status. 
	 * 3. Delete an existing note 
	 * 4. Update an existing note
	 * 
	 */

	// Autowiring should be implemented for the NoteDAO.
	 @Autowired
	 NoteDAO noteDAO;

	 // Create a Note object.
	 Note note=new Note();

	public NoteController(NoteDAO noteDAO) {
		this.noteDAO = noteDAO;
	}

	/*
	 * Define a handler method to read the existing notes from the database and add
	 * it to the ModelMap which is an implementation of Map, used when building
	 * model data for use with views. it should map to the default URL i.e. "/index"
	 */

	@RequestMapping("/")
	public ModelAndView getNotes(){

		ModelAndView modelAndView=new ModelAndView();

		List<Note> notes=noteDAO.getAllNotes();

		modelAndView.addObject("notes",notes);
		modelAndView.setViewName("index");

		return modelAndView;
	}

	/*
	 * Define a handler method which will read the NoteTitle, NoteContent,
	 * NoteStatus from request parameters and save the note in note table in
	 * database. Please note that the CreatedAt should always be auto populated with
	 * system time and should not be accepted from the user. Also, after saving the
	 * note, it should show the same along with existing messages. Hence, reading
	 * note has to be done here again and the retrieved notes object should be sent
	 * back to the view using ModelMap This handler method should map to the URL
	 * "/add".
	 */

	@RequestMapping("/add")
	public ModelAndView add(@RequestParam String noteTitle, @RequestParam String noteStatus, @RequestParam String noteContent){
		ModelAndView modelAndView=new ModelAndView();
		note.setNoteId(10);
		note.setNoteTitle(noteTitle);
		note.setNoteStatus(noteStatus);
		note.setNoteContent(noteContent);
		note.setCreatedAt(LocalDateTime.now());

		noteDAO.saveNote(note);
		List<Note> notes=noteDAO.getAllNotes();

		System.out.println("Size of list " + notes.size() + " First element ");

		modelAndView.addObject("notes",notes);
		modelAndView.setViewName("redirect:/");

		return modelAndView;
	}


	/*
	 * Define a handler method which will read the NoteId from request parameters
	 * and remove an existing note by calling the deleteNote() method of the
	 * NoteRepository class.This handler method should map to the URL "/delete".
	 */
	@RequestMapping("/delete")
	public ModelAndView delete(@RequestParam int noteId){

		ModelAndView modelAndView=new ModelAndView();

		//System.out.println(noteDAO.hashCode());

		List<Note> notes1=noteDAO.getAllNotes();
		System.out.println("Size of list before deletion " + notes1.size());

		noteDAO.deleteNote(noteId);

		List<Note> notes=noteDAO.getAllNotes();
		System.out.println("Size of list after deletion" + notes.size() );

		modelAndView.addObject("notes",notes);
		modelAndView.setViewName("redirect:/");

		return modelAndView;
	}
	/*
	 * Define a handler method which will update the existing note. This handler
	 * method should map to the URL "/update".
	 */
	@RequestMapping("/update")
	public ModelAndView update(@RequestParam int noteId, @RequestParam String noteTitle, @RequestParam String noteStatus, @RequestParam String noteContent){
		ModelAndView modelAndView=new ModelAndView();

		note.setNoteId(noteId);
		note.setNoteTitle(noteTitle);
		note.setNoteStatus(noteStatus);
		note.setNoteContent(noteContent);
		note.setCreatedAt(LocalDateTime.now());

		noteDAO.saveNote(note);
		List<Note> notes=noteDAO.getAllNotes();

		System.out.println("Size of list " + notes.size() + " First element ");

		modelAndView.addObject("notes",notes);
		modelAndView.setViewName("redirect:/");

		return modelAndView;
	}




}
