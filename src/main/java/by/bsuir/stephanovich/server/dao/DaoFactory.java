package by.bsuir.stephanovich.server.dao;

import by.bsuir.stephanovich.server.dao.student.StudentDao;
import by.bsuir.stephanovich.server.dao.user.UserDao;

public class DaoFactory {

    private static StudentDao studentDao;
    private static UserDao userDao;

    private DaoFactory(){

    }

    public static StudentDao getStudentDao(){
        if (studentDao == null)
            studentDao = new StudentDao();
        return studentDao;
    }

    public static UserDao getUserDao(){
        if (userDao == null)
            userDao = new UserDao();
        return userDao;
    }
}
