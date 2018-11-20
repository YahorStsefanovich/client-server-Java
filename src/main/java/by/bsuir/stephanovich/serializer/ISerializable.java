package by.bsuir.stephanovich.serializer;

import by.bsuir.stephanovich.model.Student;

import java.util.List;

public interface ISerializable {
    void serialize(List<Student> list);
    List<Student> deserialize();
}
