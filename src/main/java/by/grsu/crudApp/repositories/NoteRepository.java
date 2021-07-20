package by.grsu.crudApp.repositories;

import by.grsu.crudApp.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

public interface NoteRepository extends JpaRepository<Note,Long> {
    Note findOneById(Long id);
}
