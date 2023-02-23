package by.ageenko.web.command.impl;

import by.ageenko.web.command.Command;
import by.ageenko.web.exception.CommandException;
import by.ageenko.web.exception.ServiceException;
import by.ageenko.web.service.impl.UserService;
import by.ageenko.web.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginCommand implements Command {
    static Logger logger = LogManager.getLogger();
    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        UserService userService = UserServiceImpl.getInstance();
        String page;
        try {
            if (userService.authenticate(login,password)){
                request.setAttribute("user", login);
                page = "pages/main.jsp";
            }else {
                request.setAttribute("login_msg","Incorrect login or pass");
                page = "index.jsp";
            }
        } catch (ServiceException e) {
            throw new ServiceException(e);
        }
        return page;
    }
}
