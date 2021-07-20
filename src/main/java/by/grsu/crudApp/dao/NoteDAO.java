package by.grsu.crudApp.dao;


import by.grsu.crudApp.entity.UserNote;
import by.grsu.crudApp.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class NoteDAO {

    @Autowired
    private EntityManager entityManager;

    public List<String> getNoteNames(Long userId) {
        String sql = "Select ur.note.noteName from " + UserNote.class.getName() + " ur " //
                + " where ur.user.id = :userId ";

        Query query = this.entityManager.createQuery(sql, String.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    public List<Long> getId(long userId){

        //language=SQL
        String sql = "Select ur.note.id from " + UserNote.class.getName() + " ur " //
                + " where ur.user.id = :userId ";

        Query query = this.entityManager.createQuery(sql, Long.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    public List<Long> getIdNote(long noteId){

        //language=SQL
        String sql = "Select ur.id from " + UserNote.class.getName() + " ur " //
                + " where ur.note.id = :noteId ";

        Query query = this.entityManager.createQuery(sql, Long.class);
        query.setParameter("noteId", noteId);
        return query.getResultList();
    }

}
