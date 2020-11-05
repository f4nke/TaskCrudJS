package crud.serivce;

import crud.dao.UserDAO;
import crud.dao.UserDAOImpl;
import crud.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDAO userDAO;

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

    public void delete(int id) {
        userDAO.delete(id);
    }

    @Override
    @Transactional
    public User get(int id) {
        return userDAO.get(id);
    }
}
