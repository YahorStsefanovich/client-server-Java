package by.bsuir.stephanovich.serializer;

import java.util.List;

public interface ISerializable {
    void serialize(List<?> list);
    List<?> deserialize();
}
