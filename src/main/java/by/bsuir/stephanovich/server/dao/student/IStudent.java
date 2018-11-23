package by.bsuir.stephanovich.server.dao.student;

import by.bsuir.stephanovich.model.Student;

public interface IStudent {
    Student getStudent(String id);
    boolean setName(String id, String name);
    boolean setLastName(String id, String lastName);
    boolean setGroup(String id, String group);
    boolean setRole(String id, int role);
    void addStudent(Student student);
    void saveChanges();
}
