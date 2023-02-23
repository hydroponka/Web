package by.ageenko.web.validator;

public interface UserValidator {
    boolean loginValidate(String login);
    boolean passwordValidate(String login);
    boolean emailValidate(String login);
}
