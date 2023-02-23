package by.ageenko.web.dao;

import by.ageenko.web.entity.AbstractEntity;
import by.ageenko.web.exception.DaoException;

import java.util.List;

public abstract class BaseDao<T extends AbstractEntity> {
    public abstract boolean insert(T t) throws DaoException;
    public abstract boolean delete(T t);
    public abstract List<T> findAll();
    public abstract T update(T t);
}
