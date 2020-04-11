package web.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import web.model.Role;

import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.Set;

public class RoleDaoImpl implements RoleDao {
    @Autowired
    private SessionFactory sessionFactory;

    public void addRole(Role role) {
        sessionFactory.getCurrentSession().save(role);
    }

    public Role getRole(String role) {
        TypedQuery<Role> r = sessionFactory.getCurrentSession()
                .createQuery("from Role r where r.authority = :role");
        r.setParameter("role", role);

        return r.getSingleResult();
    }

    public Role getRoleById(Long id) {
        return sessionFactory.getCurrentSession().get(Role.class, id);
    }

    public Set getAuthorityById(Long id) {
        Set<Role> userRole = new HashSet<>();
        Role role = getRoleById(id);
        userRole.add(role);

        return userRole;
    }

    public void deleteRole(Long id) {
        Role role = getRoleById(id);

        if (null != role) {
            sessionFactory.getCurrentSession().delete(role);
        }
    }
}
