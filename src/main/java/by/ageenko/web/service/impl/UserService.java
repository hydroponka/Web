package by.ageenko.web.service.impl;

import by.ageenko.web.entity.User;
import by.ageenko.web.exception.DaoException;
import by.ageenko.web.exception.ServiceException;

import java.util.List;

public interface UserService {
    boolean authenticate(String login, String password) throws ServiceException;
    boolean insert(User user) throws DaoException, ServiceException;
    boolean delete(User user);
    List<User> findAll();
    User update(User user);
}
