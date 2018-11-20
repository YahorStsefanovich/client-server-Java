package by.bsuir.stephanovich.server.service;

public class ServiceFactory {
    private static StudentService studentService;

    private ServiceFactory(){

    }

    public static StudentService getStudentService(){
        if (studentService == null)
            studentService = new StudentService();
        return studentService;
    }
}
