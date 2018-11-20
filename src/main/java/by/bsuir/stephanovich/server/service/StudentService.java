package by.bsuir.stephanovich.server.service;

import by.bsuir.stephanovich.server.dao.DaoFactory;
import by.bsuir.stephanovich.server.dao.StudentDao;
import by.bsuir.stephanovich.model.Student;

public class StudentService {
    private static StudentDao studentDao;

    public StudentService(){
        studentDao  = DaoFactory.getStudentDao();
    }

    public static void main(String ... args){
        studentDao.addStudent(new Student("Yegor", "Stephanovich", "651006", "6510151", 1));
        studentDao.saveChanges();
    }

    public void save(){
        studentDao.saveChanges();
    }

    public boolean setStudentName(String id, String name){
        return studentDao.setName(id, name);
    }

    public boolean setStudentLastName(String id, String lastName){
        return studentDao.setLastName(id, lastName);
    }

    public boolean setStudentGroup(String id, String group){
        return studentDao.setGroup(id, group);
    }

    public boolean setRole(String id, int lastName){
        return studentDao.setRole(id, lastName);
    }

    public void addStudent(Student student){
        studentDao.addStudent(student);
    }

    public Student getStudent(String id){
        return studentDao.getStudent(id);
    }

}
