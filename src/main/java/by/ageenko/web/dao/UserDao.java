package by.ageenko.web.dao;

import by.ageenko.web.exception.DaoException;

public interface UserDao {
    boolean authenticate(String login, String password) throws DaoException;
}
