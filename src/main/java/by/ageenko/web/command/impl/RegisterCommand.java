package by.ageenko.web.command.impl;

import by.ageenko.web.command.Command;
import by.ageenko.web.entity.User;
import by.ageenko.web.exception.CommandException;
import by.ageenko.web.exception.DaoException;
import by.ageenko.web.exception.ServiceException;
import by.ageenko.web.service.impl.UserService;
import by.ageenko.web.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RegisterCommand implements Command {
    static Logger logger = LogManager.getLogger();
    @Override
    public String execute(HttpServletRequest request) throws DaoException, ServiceException {
        String login = request.getParameter("login");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = new User(login,email,password);
        UserService userService = UserServiceImpl.getInstance();
        String page;
        try {
            if (userService.insert(user)){
                request.setAttribute("login_msg","Your account has been successfully registered. Return to home page");
                page = "register.jsp";
            }else {
                request.setAttribute("login_msg", "This username is already taken");
                page = "register.jsp";
            }
        } catch (ServiceException e) {
            throw new ServiceException(e);
        } catch (DaoException e) {
            throw new DaoException(e);
        }
        return page;
    }
}
