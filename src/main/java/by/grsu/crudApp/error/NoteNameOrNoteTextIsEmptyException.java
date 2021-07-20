package by.grsu.crudApp.error;

public class NoteNameOrNoteTextIsEmptyException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NoteNameOrNoteTextIsEmptyException() {
    }

    public NoteNameOrNoteTextIsEmptyException(String message) {
        super(message);
    }

    public NoteNameOrNoteTextIsEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoteNameOrNoteTextIsEmptyException(Throwable cause) {
        super(cause);
    }
}
