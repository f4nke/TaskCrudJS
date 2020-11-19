package crud.serivce;

import crud.dao.UserDAO;
import crud.dao.UserDAOImpl;
import crud.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;


    @Override
    @Transactional
    public void add(User user) {
        userDAO.add(user);
    }

    @Override
    public List<User> listUsers() {
       return userDAO.listUsers();
    }

    @Override
    @Transactional
    public void update(User user) {
        userDAO.update(user);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        userDAO.delete(id);
    }

    @Override
    @Transactional
    public User get(Integer id) {
        return userDAO.get(id);
    }
}
