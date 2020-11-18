package crud.serivce;

import crud.config.repo.UserRepository;
import crud.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

//    @PersistenceContext
//    EntityManager entityManager;
//
//    @Override
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        return entityManager.createQuery("from User u  where u.username = :username", User.class)
//                .setParameter("username", s).getSingleResult();
//    }

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s);
    }
}
