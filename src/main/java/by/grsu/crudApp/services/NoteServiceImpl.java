package by.grsu.crudApp.services;

import by.grsu.crudApp.Forms.NoteForm;
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

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private UserNoteRepository userNoteRepository;

    @Autowired
    private UsersRepository usersRepository;

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

}
