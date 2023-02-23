package by.ageenko.web.command;

import by.ageenko.web.exception.CommandException;
import by.ageenko.web.exception.DaoException;
import by.ageenko.web.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;

public interface Command {
    String execute(HttpServletRequest request) throws CommandException, ServiceException, DaoException;
    default void refresh(){}
}
