package by.grsu.crudApp.dao;

import by.grsu.crudApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

@Repository
@Transactional
public class UserDAO {

    @Autowired
    private EntityManager entityManager;

    public User findUserAccount(String username){
        try {
            String sql = "Select e from " + User.class.getName() + " e " //
                    + " Where e.userName = :userName ";

            Query query = entityManager.createQuery(sql, User.class);
            query.setParameter("userName", username);

            return (User) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
