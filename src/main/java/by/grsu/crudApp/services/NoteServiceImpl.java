package by.grsu.crudApp.services;

import by.grsu.crudApp.Forms.NoteForm;
import by.grsu.crudApp.dao.NoteDAO;
import by.grsu.crudApp.entity.Note;
import by.grsu.crudApp.entity.User;
import by.grsu.crudApp.entity.UserNote;
import by.grsu.crudApp.error.NoteNameOrNoteTextIsEmptyException;
import by.grsu.crudApp.repositories.NoteRepository;
import by.grsu.crudApp.repositories.UserNoteRepository;
import by.grsu.crudApp.repositories.UserRoleRepository;
import by.grsu.crudApp.repositories.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private UserNoteRepository userNoteRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private NoteDAO noteDAO;

    private static final Logger logger = LoggerFactory.getLogger(NoteServiceImpl.class);

    @Override
    public void createNote(NoteForm noteForm, String username) {
        Note note = Note.builder()
                .noteName(noteForm.getNoteName())
                .noteText(noteForm.getNoteText())
                .dateOfCreation(LocalDateTime.now(ZoneId.systemDefault()).withNano(0))
                .lastChange(LocalDateTime.now(ZoneId.systemDefault()).withNano(0))
                .build();

        if (note.getNoteName().isEmpty() || note.getNoteText().isEmpty()) {
            throw new NoteNameOrNoteTextIsEmptyException("note name or note text is null");
        }

        System.out.println("note: " + note.toString());
        noteRepository.save(note);

        User user = usersRepository.findOneByUserName(username);


        UserNote userNote = UserNote.builder()
                .note(note)
                .user(user)
                .build();

        userNoteRepository.save(userNote);
        logger.info("create new note");
    }

    @Override
    public Note getOneNoteById(long id) {

        Note note = noteRepository.findOneById(id);

        return note;
    }

    @Override
    public List<Note> getAllNote() {

        List<Note> notes = noteRepository.findAll();

        return notes;
    }

    @Override
    public void deleteNote(long id) {

        Note note = noteRepository.findOneById(id);

        List<Long> listId = noteDAO.getIdNote(id);

        for (Long aLong : listId) {
            userNoteRepository.delete(aLong);
        }

        noteRepository.delete(note.getId());

    }

    @Override
    public void updateNote(long id, Note note) {

        Note note1 = noteRepository.findOneById(id);

        Note newNote = Note.builder()
                .noteName(note1.getNoteName())
                .noteText(note.getNoteText())
                .dateOfCreation(note1.getDateOfCreation())
                .lastChange(LocalDateTime.now(ZoneId.systemDefault()).withNano(0))
                .id(note.getId())
                .build();

        noteRepository.save(newNote);

    }

}
