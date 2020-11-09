package crud.serivce;


import crud.dao.UserDAO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return (UserDetails) entityManager.createQuery("FROM User  where username = :Username")
                .setParameter("Username", s).getSingleResult();
    }
}
