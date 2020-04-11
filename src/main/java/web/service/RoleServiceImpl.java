package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import web.dao.RoleDao;
import web.model.Role;

import java.util.Set;

public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Transactional
    @Override
    public void addRole(Role role) {
        roleDao.addRole(role);
    }

    @Transactional(readOnly = true)
    @Override
    public Role getRole(String role) {
        return roleDao.getRole(role);
    }

    @Transactional(readOnly = true)
    @Override
    public Role getRoleById(Long id) {
        return roleDao.getRoleById(id);
    }

    @Transactional
    @Override
    public void deleteRole(Long id) {
        roleDao.deleteRole(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Set getAuthorityById(Long id) {
        return roleDao.getAuthorityById(id);
    }
}
