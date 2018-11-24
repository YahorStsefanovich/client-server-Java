package by.bsuir.stephanovich.server.service;

import by.bsuir.stephanovich.server.dao.DaoFactory;
import by.bsuir.stephanovich.server.dao.student.StudentDao;
import by.bsuir.stephanovich.model.Student;

public class StudentService {
    private static StudentDao studentDao;

    public StudentService(){
        studentDao  = DaoFactory.getStudentDao();
    }

    public void save(){
        studentDao.saveChanges();
    }

    public boolean setStudentName(String id, String name){
        if (name.equals(""))
            return false;
        return studentDao.setName(id, name);
    }

    public boolean setStudentLastName(String id, String lastName){
        if (lastName.equals(""))
            return false;
        return studentDao.setLastName(id, lastName);
    }

    public boolean setStudentGroup(String id, String group){
        if (group.length() < 6)
            return false;
        return studentDao.setGroup(id, group);
    }

    public boolean setRole(String id, int role){
        if (role != 0 && role != 1)
            return false;
        return studentDao.setRole(id, role);
    }

    public boolean addStudent(Student student){
        if (student.getName().equals("") ||
            student.getLastName().equals("") ||
            student.getGroup().equals("") ||
            student.getId().length() < 6)
            return false;
        return studentDao.addStudent(student);
    }

    public Student getStudent(String id){
        return studentDao.getStudent(id);
    }

}
