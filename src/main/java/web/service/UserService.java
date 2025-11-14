package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    User getUserByID (long id);

    List <User> getAllUsers ();

    void addUser (User user);

    User updateUser (User user);

    void deleteUser (long id);

    boolean existsEmail (String email);
}
