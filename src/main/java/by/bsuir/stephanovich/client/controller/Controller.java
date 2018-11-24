package by.bsuir.stephanovich.client.controller;

import by.bsuir.stephanovich.consolereader.Reader;
import by.bsuir.stephanovich.model.Student;
import by.bsuir.stephanovich.model.User;
import by.bsuir.stephanovich.model.XmlCollection;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.System.exit;

public class Controller {
    private static boolean isLogin;
    private static boolean isAdmin;
    private String name;

    public Controller(){
        isLogin = false;
        isAdmin = false;
    }

    public String waitCommand(){
        if (!isLogin){
            System.out.println("\\reg - registrate");
            System.out.println("\\login - login");
        }
        else{
            System.out.println("\\getst - add Student Info");

            if (isAdmin){
                System.out.println("\\setname - set Student Name");
                System.out.println("\\setlname - set Student Last Name");
                System.out.println("\\setgroup - set Student Group");
                System.out.println("\\setrole - set role");
                System.out.println("\\addst - add Student Info");
            }

            System.out.println("\\logout - logout");
        }
        System.out.println("\\exit - exit program");

        System.out.print("Input command: ");
        return Reader.readValue();
    }


    private Object getValue(String text){
        System.out.print(text);
        return Reader.readValue();
    }

    public String getAnswer(XmlCollection collection){
        switch ((String)collection.list.get(0)){
            case "\\setname":
                break;
            case "\\setlname":
                break;
            case "\\setgroup":
                break;
            case "\\setrole":
                break;
            case "\\addst":
                break;
            case "\\getst":
                break;
            case "\\login":
            case "\\reg":
                User user = (User) collection.list.get(2);
                if (user != null){
                    isLogin = true;
                    isAdmin = user.isAdmin();
                    name = user.getLogin();
                }
                break;
            case "\\logout":
                isLogin = false;
                isAdmin = false;
                break;
            case "\\exit":
                exit(0);
                break;
            default:
                break;
        }
        return (String) collection.list.get(1);
    }

    public XmlCollection performCommand(String command){

        List<Object> list = new ArrayList<>();
        list.add(command);
        switch (command){
            case "\\setname":
                list.add(getValue("Введите Id студента: "));
                list.add(getValue("Введите имя студента: "));
                break;
            case "\\setlname":
                list.add(getValue("Введите Id студента: "));
                list.add(getValue("Введите Фамилию студента: "));
                break;
            case "\\setgroup":
                list.add(getValue("Введите Id студента: "));
                list.add(getValue("Введите Группу студента: "));
                break;
            case "\\setrole":
                list.add(getValue("Введите Id студента: "));
                list.add(getValue("Введите роль студента: "));
                break;
            case "\\addst":
                Student st = new Student(
                        (String) getValue("Введите Имя студента: "),
                        (String) getValue("Введите Фамилию студента: "),
                        (String) getValue("Введите группу студента: "),
                        (String) getValue("Введите Номер зачетной книги студента: "),
                        0
                );
                list.add(st);
                break;
            case "\\getst":
                list.add(getValue("Введите Id студента: "));
                break;
            case "\\login":
            case "\\reg":
                list.add(getValue("Введите логин: "));
                list.add(DigestUtils.md5Hex((String)getValue("Введите пароль: ")));
                break;
            case "\\logout":
                isLogin = false;
                isAdmin = false;
                break;
            case "\\exit":
                exit(0);
                break;
            default:
                break;
        }
        XmlCollection xmlCollection = new XmlCollection();
        xmlCollection.list = list;
        return xmlCollection;
    }
}
