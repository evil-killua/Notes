package by.grsu.crudApp.services;

import by.grsu.crudApp.Forms.NoteForm;
import by.grsu.crudApp.entity.Note;

import java.util.List;

public interface NoteService {
    void createNote(NoteForm noteForm,String username);
    Note getOneNoteById(long id);
    List<Note> getAllNote();
    void deleteNote(long id);
    void updateNote(long id, Note note);

}
