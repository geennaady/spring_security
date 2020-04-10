package web.dao;

import java.util.List;


public interface UserDao<T> {
    List<T> listUsers();
    void addUser(T user);
    void updateUser(T user);
    T getUserById(Long id);
    void deleteUser(Long id);
    T getUserByName(String username);
}
