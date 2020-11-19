package crud.dao;

import crud.model.Role;
import crud.model.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        return (List<User>) entityManager.createQuery("FROM User").getResultList();
    }

    @Override
    public void update(User user) {
        user.getRoles().add(new Role("USER"));
        entityManager.merge(user);
    }

    @Override
    public void delete(Integer id) {
        User user = get(id);
        entityManager.remove(user);
    }

    @Override
    public User get(Integer id) {
        return entityManager.find(User.class, id);
    }
}
