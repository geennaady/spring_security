package web.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import web.model.Role;
import web.model.User;

import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class UserDaoImpl implements UserDao<User> {
    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> listUsers() {
        return sessionFactory.getCurrentSession().createQuery("from User").list();
    }

    @Override
    public void addUser(User user) {
        Set<Role> userRole = new HashSet<>();
        Role role = new Role(2L, "ROLE_USER");
        userRole.add(role);

        user.setRoles(userRole);

        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public void updateUser(User user) {
        Set<Role> userRole = new HashSet<>();
        Role role = new Role(2L, "ROLE_USER");
        userRole.add(role);
        user.setRoles(userRole);

        sessionFactory.getCurrentSession().update(user);
    }

    @Override
    public User getUserById(Long id) {
        return sessionFactory.getCurrentSession().get(User.class, id);
    }

    @Override
    public User getUserByName(String username) {
        TypedQuery<User> u = sessionFactory.getCurrentSession().createQuery("from User u where u.username = :username");
        u.setParameter("username", username);

        return u.getSingleResult();
    }

    @Override
    public void deleteUser(Long id) {
        User user = getUserById(id);

        if (null != user) {
            sessionFactory.getCurrentSession().delete(user);
        }
    }
}
