package by.ageenko.web.command.impl;

import by.ageenko.web.command.Command;
import by.ageenko.web.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().invalidate();
        return "/index.jsp";
    }
}
