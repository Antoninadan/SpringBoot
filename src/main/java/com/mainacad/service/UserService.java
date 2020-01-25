package com.mainacad.service;

import com.mainacad.dao.UserDAO;
import com.mainacad.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserDAO userDAO;

    public User getByLoginAndPassword(String login, String password) {
//       // version without DAO
//        if (login.equals(login) && password.equals("password")) {
//            return new User(1, "login", "password", "first-name", "last-name");
//        }
//        return null;
        return userDAO.getFirstByLoginAndPassword(login, password);
    }

    public User getById(Integer id) {
//        // version without DAO
//        if (id.equals(1)) {
//            return new User(1, "login", "password", "first-name", "last-name");
//        }
//        return null;

        Optional<User> user = userDAO.findById(id);
        if (user.isEmpty()) {
            return null;
        }
        return user.get();
    }

    public User save(User user) {
        if (user.getId() == null && userDAO.getFirstByLogin(user.getLogin()) == null) {
            return userDAO.save(user);
        }
        return null;
    }

    public User update(User user) {
        if (user.getId() != null && userDAO.getOne(user.getId()) != null) {
            return userDAO.save(user);
        }
        return null;
    }

    public List<User> getAll() {
        return userDAO.findAll();
    }

    public void delete(User user) {
        userDAO.delete(user);
    }

    public void deleteById(Integer id) throws RuntimeException {
        userDAO.deleteById(id);
    }
}
