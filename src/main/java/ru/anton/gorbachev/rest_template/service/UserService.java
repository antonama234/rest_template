package ru.anton.gorbachev.rest_template.service;

import ru.anton.gorbachev.rest_template.models.User;

import java.util.List;

public interface UserService extends Service<User> {
    List<User> allUsers();
    void addUser(User object);
    void deleteUser(User object);
    void editUser(User object);
    User findUserByLogin(String name);
    User findUserById(Long id);
    boolean isExist(String login);
}
