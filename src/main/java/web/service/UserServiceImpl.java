package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService<User> {
    @Autowired
    private UserDao userDao;

    @Transactional(readOnly = true)
    @Override
    public List<User> listUsers() { return userDao.listUsers(); }

    @Transactional
    @Override
    public void addUser(User user) { userDao.addUser(user); }

    @Transactional
    @Override
    public void updateUser(User user) { userDao.updateUser(user); }

    @Transactional(readOnly = true)
    @Override
    public User getUserById(Long id) { return (User) userDao.getUserById(id); }

    @Transactional
    @Override
    public void deleteUser(Long id) { userDao.deleteUser(id); }

    @Transactional(readOnly = true)
    @Override
    public User getUserByName(String username) { return (User) userDao.getUserByName(username); }
}
