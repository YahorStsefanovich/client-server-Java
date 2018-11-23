package by.bsuir.stephanovich.client.controller;

import by.bsuir.stephanovich.consolereader.Reader;
import by.bsuir.stephanovich.model.Student;
import by.bsuir.stephanovich.model.XmlCollection;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Controller {

    //private Serializer serializer;

    public Controller(){
        //serializer = new Serializer();
    }

    private Object getValue(String text){
        System.out.print(text);
        return Reader.readValue();
    }

    public XmlCollection performCommand(String command){

        List<Object> list = new ArrayList<>();
        switch (command){
            case "\\setname":
                list = new ArrayList<>(Arrays.asList(
                        command,
                        getValue("Введите Id студента: "),
                        getValue("Введите имя студента: "))
                );
                break;
            case "\\setlname":
                list  = new ArrayList<>(Arrays.asList(
                        command,
                        getValue("Введите Id студента: "),
                        getValue("Введите Фамилию студента: "))
                );
                break;
            case "\\setgroup":
                list  = new ArrayList<>(Arrays.asList(
                        command,
                        getValue("Введите Id студента: "),
                        getValue("Введите Группу студента:"))
                );
                break;
            case "\\setrole":
                list  = new ArrayList<>(Arrays.asList(
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
                list  = new ArrayList<>(Arrays.asList(command, (Object)st));
                break;
            case "\\getst":
                list  = new ArrayList<>(Arrays.asList(
                        command,
                        getValue("Введите Id студента: "))
                );
                break;
            case "\\login":
                list  = new ArrayList<>(Arrays.asList(
                        command,
                        getValue("Введите логин: "),
                        DigestUtils.md5Hex((String)getValue("Введите пароль: ")))
                );
            case "\\reg":
                list  = new ArrayList<>(Arrays.asList(
                        command,
                        getValue("Введите логин: "),
                        DigestUtils.md5Hex((String)getValue("Введите пароль: ")))
                );
            case "\\end":
                //exit(0);
                break;
            default:
                break;
        }
        XmlCollection xmlCollection = new XmlCollection();
        xmlCollection.list = list;
        return xmlCollection;
    }
}
