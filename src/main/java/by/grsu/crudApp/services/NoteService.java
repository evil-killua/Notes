package by.grsu.crudApp.services;

import by.grsu.crudApp.Forms.NoteForm;

public interface NoteService {
    void createNote(NoteForm noteForm,String username);

}
