package by.bsuir.stephanovich.serializer;

import by.bsuir.stephanovich.model.Student;
import by.bsuir.stephanovich.model.XmlCollection;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.Annotations;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class Serializer implements ISerializable{

    private final static String FILE_NAME = "data.xml";
    public XStream xstream;

    public Serializer(){
        xstream = new XStream();
    }

    public String serializeToString(XmlCollection xmlCollection){
        return xstream.toXML(xmlCollection);
    }

    public XmlCollection deserializeFromString(String xml){
        return (XmlCollection)xstream.fromXML(xml);
    }

    @Override
    public void serialize(List<Student> list) {
        Annotations.configureAliases(xstream, Student.class);
        setXmlToFile(xstream.toXML(list));
    }

    @Override
    public List<Student> deserialize() {
        Annotations.configureAliases(xstream, Student.class);
        List<Student> list = new ArrayList<>();

        try {
            for (Student student: (List<Student>)xstream.fromXML(getXmlFromFile())) {
                list.add(student);
            }
        }catch (Exception e){

        }

        return list;
    }

    private String getXmlFromFile(){
        StringBuilder xml = new StringBuilder();
        try {
            if (Files.notExists(Paths.get(FILE_NAME), LinkOption.NOFOLLOW_LINKS))
                Files.createFile(Paths.get(FILE_NAME));
            List<String> lines = Files.readAllLines(Paths.get(FILE_NAME), StandardCharsets.UTF_8);
            for(String line: lines){
                xml.append(line);
            }
        } catch (IOException e){
            e.printStackTrace();
        }

        return xml.toString();
    }

    private void setXmlToFile(String xml){
        try {
            if (Files.notExists(Paths.get(FILE_NAME), LinkOption.NOFOLLOW_LINKS))
                Files.createFile(Paths.get(FILE_NAME));
            Files.write(Paths.get(FILE_NAME), xml.getBytes(), StandardOpenOption.WRITE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
