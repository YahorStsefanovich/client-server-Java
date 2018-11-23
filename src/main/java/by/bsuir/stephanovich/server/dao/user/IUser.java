package by.bsuir.stephanovich.server.dao.user;

import by.bsuir.stephanovich.model.User;

public interface IUser {
    User authirize(String login, String password);
    User registrate(String login, String password, boolean isAdmin);
}
