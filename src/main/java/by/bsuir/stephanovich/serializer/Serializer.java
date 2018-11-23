package by.bsuir.stephanovich.serializer;

import by.bsuir.stephanovich.model.Student;
import by.bsuir.stephanovich.model.XmlCollection;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.Annotations;

import java.io.EOFException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class Serializer implements ISerializable{

    private String fileName;
    private XStream xstream;

    public Serializer(String fileName, Class cl){
        this.fileName = fileName;
        xstream = new XStream();
        Annotations.configureAliases(xstream, cl);
    }

    @Override
    public void serialize(List<?> list) {
        setXmlToFile(xstream.toXML(list));
    }

    @Override
    public List<?> deserialize() {
        List<?> list = null;
        try{
            list = (List<?>) xstream.fromXML(getXmlFromFile());
        }catch (Exception ignored){

        }
        if (list == null)
            list = new ArrayList<>();
        return list;
    }

    private String getXmlFromFile(){
        StringBuilder xml = new StringBuilder();
        try {
            if (Files.notExists(Paths.get(fileName), LinkOption.NOFOLLOW_LINKS))
                Files.createFile(Paths.get(fileName));
            List<String> lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
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
            if (Files.notExists(Paths.get(fileName), LinkOption.NOFOLLOW_LINKS))
                Files.createFile(Paths.get(fileName));
            Files.write(Paths.get(fileName), xml.getBytes(), StandardOpenOption.WRITE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
