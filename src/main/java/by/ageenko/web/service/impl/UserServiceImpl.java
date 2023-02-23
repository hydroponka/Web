package by.ageenko.web.service.impl;

import by.ageenko.web.dao.UserDao;
import by.ageenko.web.dao.impl.UserDaoImpl;
import by.ageenko.web.entity.User;
import by.ageenko.web.exception.DaoException;
import by.ageenko.web.exception.ServiceException;
import by.ageenko.web.validator.UserValidator;
import by.ageenko.web.validator.impl.UserValidatorImpl;

import java.util.List;

public class UserServiceImpl implements UserService{
    private static UserServiceImpl instance = new UserServiceImpl();

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        return instance;
    }

    @Override
    public boolean authenticate(String login, String password) throws ServiceException {
        UserValidator userValidator = new UserValidatorImpl();
        boolean loginMatch = userValidator.loginValidate(login);
        boolean passwordMatch = userValidator.passwordValidate(login);
        boolean match = false;
        if (loginMatch && passwordMatch) {
            UserDaoImpl userDao = UserDaoImpl.getInstance();
            try {
                match = userDao.authenticate(login, password);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return match;
    }

    @Override
    public boolean insert(User user) throws ServiceException {
        UserValidator userValidator = new UserValidatorImpl();
        boolean loginMatch = userValidator.loginValidate(user.getLogin());
        boolean passwordMatch = userValidator.passwordValidate(user.getPassword());
        boolean emailMatch = userValidator.emailValidate(user.getEmail());
        boolean match = false;
        if (loginMatch && passwordMatch && emailMatch) {
            UserDaoImpl userDao = UserDaoImpl.getInstance();
            try {
                match = userDao.insert(user);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return match;
    }

    @Override
    public boolean delete(User user) {
        return false;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }
}
