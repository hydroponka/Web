package by.ageenko.web;

import by.ageenko.web.exception.ServiceException;
import by.ageenko.web.service.impl.UserServiceImpl;

public class main {
    public static void main(String[] args) throws ServiceException {
        boolean p = false;
        UserServiceImpl userService = UserServiceImpl.getInstance();
        p = userService.authenticate("Avc123","Abcrge123");
        System.out.println(p);
    }
}
