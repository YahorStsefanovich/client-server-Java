package by.bsuir.stephanovich.server.dao;

public class DaoFactory {

    private static StudentDao studentDao;

    private DaoFactory(){

    }

    public static StudentDao getStudentDao(){
        if (studentDao == null)
            studentDao = new StudentDao();
        return studentDao;
    }
}
