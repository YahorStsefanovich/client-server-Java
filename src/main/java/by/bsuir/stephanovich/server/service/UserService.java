package by.bsuir.stephanovich.server.service;

import by.bsuir.stephanovich.model.User;
import by.bsuir.stephanovich.server.dao.DaoFactory;
import by.bsuir.stephanovich.server.dao.user.UserDao;

public class UserService {
    private static UserDao userDao;

    public UserService(){
        userDao = DaoFactory.getUserDao();
    }

    public User authorize(String login, String password){
         return userDao.authirize(login, password);
    }

    public User registrate(String login, String password, boolean isAdmin){
        if (login.length() < 4 || password.length() < 4)
            return null;
        return userDao.registrate(login, password, isAdmin);
    }
}
