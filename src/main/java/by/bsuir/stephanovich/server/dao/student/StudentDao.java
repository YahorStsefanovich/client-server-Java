package by.bsuir.stephanovich.server.dao.student;

import by.bsuir.stephanovich.model.Student;
import by.bsuir.stephanovich.serializer.Serializer;

import java.util.List;

public class StudentDao implements IStudent {

    private List<Student> students;
    private Serializer serializer;
    private final static String FILE_NAME = "data.xml";

    public StudentDao(){
        serializer = new Serializer(FILE_NAME, Student.class);
    }

    @Override
    public Student getStudent(String id){
        int index;
        if ((index = findById(id)) == -1)
            return null;

        return students.get(index);
    }

    @Override
    public boolean setName(String id, String name){
        students = (List<Student>) serializer.deserialize();
        int index;

        if ((index = findById(id)) == -1)
            return false;

        Student student = students.get(index);
        student.setName(name);
        students.set(index, student);
        serializer.serialize(students);
        return true;
    }

    @Override
    public boolean setLastName(String id, String lastName){
        students = (List<Student>) serializer.deserialize();
        int index;

        if ((index = findById(id)) == -1)
            return false;

        Student student = students.get(index);
        student.setLastName(lastName);
        students.set(index, student);
        serializer.serialize(students);
        return true;
    }

    @Override
    public boolean setGroup(String id, String group){
        students = (List<Student>) serializer.deserialize();
        int index;

        if ((index = findById(id)) == -1)
            return false;

        Student student = students.get(index);
        student.setGroup(group);
        students.set(index, student);
        serializer.serialize(students);
        return true;
    }

    @Override
    public boolean setRole(String id, int role){
        students = (List<Student>) serializer.deserialize();
        int index;

        if ((index = findById(id)) == -1)
            return false;

        Student student = students.get(index);
        student.setRole(role);
        students.set(index, student);
        serializer.serialize(students);
        return true;
    }

    @Override
    public boolean addStudent(Student student){
        students = (List<Student>) serializer.deserialize();
        if(findById(student.getId()) != -1)
            return false;
        students.add(student);
        serializer.serialize(students);
        return true;
    }

    @Override
    public void saveChanges() {
        serializer.serialize(students);
    }

    private int findById(String id){
        students = (List<Student>) serializer.deserialize();
        for (Student st : students){
            if (id.equals(st.getId())){
                return students.indexOf(st);
            }
        }
        return -1;
    }
}
