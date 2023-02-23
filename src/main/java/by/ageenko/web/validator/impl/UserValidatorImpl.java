package by.ageenko.web.validator.impl;

import by.ageenko.web.validator.UserValidator;

public class UserValidatorImpl implements UserValidator {
    private static final String LOGIN_REGEX = "^[A-Za-z]([.A-Za-z0-9-]{1,18})([A-Za-z0-9])$";
    private static final String PASSWORD_REGEX = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{4,8}$";
    private static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\\\+]+(\\\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]" +
                                                 "+(\\\\.[A-Za-z0-9]+)*(\\\\.[A-Za-z]{2,})$";
    @Override
    public boolean loginValidate(String login) {
        boolean match = login.matches(LOGIN_REGEX);
        return match;
    }

    @Override
    public boolean passwordValidate(String login) {
        boolean match = login.matches(PASSWORD_REGEX);
        return match;
    }

    @Override
    public boolean emailValidate(String login) {
        boolean match = login.matches(EMAIL_REGEX);
        return match;
    }
}
