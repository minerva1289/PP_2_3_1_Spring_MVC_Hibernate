package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.User;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    @Transactional
    public User getUserByID (long id) {
        return userDao.getUserByID(id);
    }

    @Override
    @Transactional
    public List <User> getAllUsers () {
        return userDao.getAllUsers();
    }

    @Override
    @Transactional
    public void addUser (User user) {
        userDao.addUser(user);
    }

    @Override
    @Transactional
    public User updateUser (User user) {
        return userDao.updateUser(user);
    }

    @Override
    @Transactional
    public void deleteUser (long id) {
        userDao.deleteUser(id);
    }

    @Override
    @Transactional
    public boolean existsEmail (String email) {
        return userDao.existsEmail(email);
    }
}
