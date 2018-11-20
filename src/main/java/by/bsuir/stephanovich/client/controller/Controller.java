package by.bsuir.stephanovich.client.controller;

import by.bsuir.stephanovich.consolereader.Reader;
import by.bsuir.stephanovich.model.Student;
import by.bsuir.stephanovich.model.XmlCollection;
import by.bsuir.stephanovich.serializer.Serializer;

import java.util.ArrayList;
import java.util.Arrays;

public class Controller {

    private Serializer serializer;

    public Controller(){
        serializer = new Serializer();
    }

    private Object getValue(String text){
        System.out.print(text);
        return Reader.readValue();
    }

    public String performCommand(String command){

        XmlCollection xmlCollection = new XmlCollection();
        switch (command){
            case "\\setname":
                xmlCollection.list = new ArrayList<>(Arrays.asList(
                        command,
                        getValue("Введите Id студента: "),
                        getValue("Введите имя студента: "))
                );
                break;
            case "\\setlname":
                xmlCollection.list  = new ArrayList<>(Arrays.asList(
                        command,
                        getValue("Введите Id студента: "),
                        getValue("Введите Фамилию студента: "))
                );
                break;
            case "\\setgroup":
                xmlCollection.list  = new ArrayList<>(Arrays.asList(
                        command,
                        getValue("Введите Id студента: "),
                        getValue("Введите Группу студента:"))
                );
                break;
            case "\\setrole":
                xmlCollection.list  = new ArrayList<>(Arrays.asList(
                        command,
                        getValue("Введите Id студента: "),
                        getValue("Введите роль студента: "))
                );
                break;
            case "\\addst":
                Student st = new Student(
                        (String) getValue("Введите Имя студента: "),
                        (String) getValue("Введите Фамилию студента: "),
                        (String) getValue("Введите группу студента: "),
                        (String) getValue("Введите Номер зачетной книги студента: "),
                        0
                );
                xmlCollection.list  = new ArrayList<>(Arrays.asList(command, (Object)st));
                break;
            case "\\getst":
                xmlCollection.list  = new ArrayList<>(Arrays.asList(
                        command,
                        getValue("Введите Id студента: "))
                );
                break;
            default:
                break;
        }
        serializer.xstream.addImplicitCollection(XmlCollection.class, "list ofObjects");

        String result = serializer.serializeToString(xmlCollection).replaceAll("\n", "&");
        return result;
    }
}
