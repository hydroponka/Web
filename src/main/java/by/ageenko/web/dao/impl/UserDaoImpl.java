package by.ageenko.web.dao.impl;

import by.ageenko.web.dao.BaseDao;
import by.ageenko.web.dao.UserDao;
import by.ageenko.web.entity.User;
import by.ageenko.web.exception.DaoException;
import by.ageenko.web.pool.ConnectionPool;

import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl extends BaseDao<User> implements UserDao {
    private static final String SELECT_LOGIN_PASSWORD = "SELECT password FROM users WHERE login = ?";
    private static final String SELECT_LOGIN = "SELECT login FROM users";
    private static final String INSERT_LOGIN_PASSWORD_EMAIL = "INSERT INTO users(login,password,email) value (?,?,?)";
    private static final String DELETE_USER = "DELETE FROM users WHERE login = ?";
    private static final String UPDATE_PASSWORD = "UPDATE password FROM users WHERE email = ?";
    private static UserDaoImpl instance = new UserDaoImpl();

    private UserDaoImpl() {
    }

    public static UserDaoImpl getInstance() {
        return instance;
    }

    @Override
    public boolean authenticate(String login, String password) throws DaoException {
        var match = false;

        try (var connection = ConnectionPool.getInstance().getConnection();
             var statement = connection.prepareStatement(SELECT_LOGIN_PASSWORD)) {
            statement.setString(1, login);
            try (var resultSet = statement.executeQuery()) {
                String passFromDb;
                if (resultSet.next()) {
                    passFromDb = resultSet.getString(1);
                    match = password.equals(passFromDb);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return match;
    }

    @Override
    public boolean insert(User user) throws DaoException {
        var match = false;

        try (var connection = ConnectionPool.getInstance().getConnection();
             var statement = connection.prepareStatement(SELECT_LOGIN);
             var statemantInsert = connection.prepareStatement(INSERT_LOGIN_PASSWORD_EMAIL)) {
            try (var resultSet = statement.executeQuery()) {
                String loginFromDb;
                while (resultSet.next()) {
                    loginFromDb = resultSet.getString("login");
                    match = user.getLogin().equals(loginFromDb);
                    if (match) {
                        return false;
                    }
                }
            }
            statemantInsert.setString(1, user.getLogin());
            statemantInsert.setString(2, user.getPassword());
            statemantInsert.setString(3, user.getEmail());
            statemantInsert.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return true;
    }

    @Override
    public boolean delete(User user) {
        return false;
    }

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }

}
