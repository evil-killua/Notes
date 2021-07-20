package by.grsu.crudApp.repositories;

import by.grsu.crudApp.entity.UserNote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserNoteRepository extends JpaRepository<UserNote,Long> {
}
