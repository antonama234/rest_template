package ru.anton.gorbachev.rest_template.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.anton.gorbachev.rest_template.dao.UserDAO;
import ru.anton.gorbachev.rest_template.models.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserDAO dao;

    @Autowired
    public UserServiceImpl(UserDAO dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public List<User> allUsers() {
        return dao.findAll();
    }

    @Override
    @Transactional
    public void addUser(User user) {
        dao.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(User user) {
        dao.delete(user);
    }

    @Override
    @Transactional
    public void editUser(User user) {
        User u = dao.findByLogin(user.getLogin());
        u.setLogin(user.getLogin());
        u.setPassword(user.getPassword());
        u.setName(user.getName());
        u.setSurname(user.getSurname());
        u.setAge(user.getAge());
    }

    @Override
    public User findUserByLogin(String name) {
        return dao.findByLogin(name);
    }

    @Override
    @Transactional
    public User findUserById(Long id) {
        return dao.findUserById(id);
    }

    @Override
    @Transactional
    public boolean isExist(String login) {
        return dao.findByLogin(login) != null;
    }
}
