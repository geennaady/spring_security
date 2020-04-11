package web.dao;

import web.model.Role;

import java.util.Set;

public interface RoleDao {
    void addRole(Role role);
    Role getRole(String role);
    Role getRoleById(Long id);
    void deleteRole(Long id);
    Set getAuthorityById(Long id);
}
