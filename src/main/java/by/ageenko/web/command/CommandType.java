package by.ageenko.web.command;

import by.ageenko.web.command.impl.RegisterCommand;
import by.ageenko.web.command.impl.LoginCommand;
import by.ageenko.web.command.impl.LogoutCommand;

public enum CommandType {
    REGISTER(new RegisterCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand());
    Command command;

    CommandType(Command command) {
        this.command = command;
    }
    public static Command define(String commandStr) {
        CommandType current = CommandType.valueOf(commandStr.toUpperCase());
        return current.command;
    }
}
