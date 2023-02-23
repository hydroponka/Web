package by.ageenko.web.controller;

import by.ageenko.web.command.Command;
import by.ageenko.web.command.CommandType;
import by.ageenko.web.exception.CommandException;
import by.ageenko.web.exception.DaoException;
import by.ageenko.web.exception.ServiceException;
import by.ageenko.web.pool.ConnectionPool;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
@WebServlet(name = "Controller", urlPatterns = {"/controller"})
public class Controller extends HttpServlet {
    static Logger logger = LogManager.getLogger();
    @Override
    public void init() throws ServletException {
        ConnectionPool.getInstance();
        logger.log(Level.INFO, "----------> Servlet Init :" + this.getServletInfo());
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        String commandStr = req.getParameter("command");
        Command command = CommandType.define(commandStr);
        String page;
        try {
            page = command.execute(req);
            req.getRequestDispatcher(page).forward(req, resp);
        } catch (CommandException e) {
            req.getRequestDispatcher("pages/error/error_500.jsp").forward(req, resp);
        } catch (ServiceException e) {
            throw new ServletException(e);
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroy() {
        //ConnectionPool.destroyPool();
        logger.log(Level.INFO, "----------> Servlet Destroyed :" + this.getServletName());
    }
}
