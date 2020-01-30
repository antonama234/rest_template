package ru.anton.gorbachev.rest_template.service;

import java.util.List;

public interface Service<T> {
    List<T> allUsers();
    void addUser(T object);
    void deleteUser(T object);
    void editUser(T object);
    T findUserById(Long id);
    T findUserByLogin(String name);
    boolean isExist(String login);
}
