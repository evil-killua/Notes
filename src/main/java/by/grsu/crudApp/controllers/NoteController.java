package by.grsu.crudApp.controllers;


import by.grsu.crudApp.Forms.NoteForm;
import by.grsu.crudApp.dao.NoteDAO;
import by.grsu.crudApp.dao.RoleDAO;
import by.grsu.crudApp.entity.Note;
import by.grsu.crudApp.entity.User;
import by.grsu.crudApp.services.NoteService;
import by.grsu.crudApp.services.SignUpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
;

import java.nio.file.AccessDeniedException;
import java.security.Principal;

import java.util.*;

@Controller
public class NoteController {

    @Autowired
    private NoteService noteService;

    @Autowired
    private SignUpService userService;

    @Autowired
    private NoteDAO noteDAO;

    @Autowired
    private RoleDAO roleDAO;

    private static final Logger logger = LoggerFactory.getLogger(NoteController.class);

    @GetMapping("/addNewNote")
    public String showAddNewNoteForm() {

        return "notePage/newNote";
    }

    @PostMapping("/newNote")
    public String newNote(NoteForm noteForm, Principal principal, Model model) {

        try {
            noteService.createNote(noteForm, principal.getName());

            return "redirect:/note";
        } catch (Exception e) {
            logger.error("note name or note text is empty!!!");
            model.addAttribute("error", "note name or note text is empty!!!");

            return "notePage/newNote";
        }

    }

    @GetMapping("/note/{id}")
    public String viewNoteText(@PathVariable("id") long id, Model model) {

        Note note = noteService.getOneNoteById(id);
        model.addAttribute("note", note);
        logger.info("view one note,get note by id note");

        return "notePage/viewNote";
    }

    @GetMapping("/allNotes")
    public String allNotes(Principal principal, Model model) throws AccessDeniedException {

        List<Note> notes = noteService.getAllNote();

        User user = userService.getUserByUserName(principal.getName());

        String role = roleDAO.getRoleNames(user.getId()).get(0);

        model.addAttribute("role", role);
        model.addAttribute("notes", notes);

        logger.info("view all notes");

        return "notePage/notePage";
    }

    @GetMapping("/allNotes/date")
    public String allNotesSortByDate(Principal principal, Model model) {

        List<Note> notes = new ArrayList<>(noteService.getAllNote());

        notes.sort(Comparator.comparing(Note::getDateOfCreation));

        User user = userService.getUserByUserName(principal.getName());

        String role = roleDAO.getRoleNames(user.getId()).get(0);

        model.addAttribute("role", role);
        model.addAttribute("notes", notes);

        logger.info("view notes,sort by date Of Creation");

        return "notePage/notePage";
    }

    @GetMapping("/allNotes/name")
    public String allNotesSortNoteName(Principal principal, Model model) throws AccessDeniedException {

        List<Note> notes = new ArrayList<>(noteService.getAllNote());
        notes.sort(Comparator.comparing(Note::getNoteName));

        User user = userService.getUserByUserName(principal.getName());

        String role = roleDAO.getRoleNames(user.getId()).get(0);

        model.addAttribute("role", role);
        model.addAttribute("notes", notes);

        logger.info("view notes,sort by note name");

        return "notePage/notePage";
    }

    @GetMapping("/note")
    public String userNote(Principal principal, Model model) {

        User user = userService.getUserByUserName(principal.getName());

        List<Long> notesId = noteDAO.getId(user.getId());

        List<Note> notes = new ArrayList<>();

        for (Long id : notesId) {
            notes.add(noteService.getOneNoteById(id));
        }

        String role = roleDAO.getRoleNames(user.getId()).get(0);

        model.addAttribute("role", role);


        logger.info("view user notes");
        model.addAttribute("notes", notes);

        return "notePage/notePage";
    }


    @GetMapping("/deleteNote/{id}")
    public String deleteUser(@PathVariable("id") long id) {

        noteService.deleteNote(id);

        logger.info("delete note by note id");
        return "redirect:/note";
    }

    @GetMapping("/editNoteText/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {

        Note note = noteService.getOneNoteById(id);
        model.addAttribute("note", note);

        return "notePage/update-note";
    }

    @PostMapping("/updateNoteText/{id}")
    public String updateUser(@PathVariable("id") Long id, Note note, Model model) {

        if (!note.getNoteText().isEmpty()) {
            noteService.updateNote(id, note);
            logger.info("update note");

            return "redirect:/note";
        } else {
            model.addAttribute("error", "note text is empty");
            logger.error("note text is empty");

            return "notePage/update-note";
        }

    }

}
