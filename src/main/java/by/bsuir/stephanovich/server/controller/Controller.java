package by.bsuir.stephanovich.server.controller;

import by.bsuir.stephanovich.model.Student;
import by.bsuir.stephanovich.model.User;
import by.bsuir.stephanovich.model.XmlCollection;
import by.bsuir.stephanovich.server.service.StudentService;
import by.bsuir.stephanovich.server.service.UserService;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    private StudentService studentService;
    private UserService userService;

    public Controller(){
        studentService = new StudentService();
        userService = new UserService();
    }

    public XmlCollection performCommand(XmlCollection command){
        String answer = "";
        User user;
        List<Object> list = new ArrayList<>();
        list.add(command.list.get(0));
        list.add(answer);
        switch ((String) command.list.get(0)){
            case "\\setname":
                answer = (studentService.setStudentName((String) command.list.get(1),(String) command.list.get(2)))?
                        "Операция прошла успешно" :
                        "Ошибка выполнения операции";
                break;

            case "\\setlname":
                answer = (studentService.setStudentLastName((String) command.list.get(1), (String) command.list.get(2)))?
                        "Операция прошла успешно" :
                        "Ошибка выполнения операции";
                break;

            case "\\setgroup":
                answer =  (studentService.setStudentGroup((String) command.list.get(1), (String) command.list.get(2)))?
                        "Операция прошла успешно" :
                        "Ошибка выполнения операции";
                break;

            case "\\setrole":
                answer = (studentService.setRole((String) command.list.get(1), (Integer) command.list.get(2)))?
                        "Операция прошла успешно" :
                        "Ошибка выполнения операции";
                break;

            case "\\addst":
                answer = studentService.addStudent((Student) command.list.get(1))?
                        "Операция прошла успешно" :
                        "Ошибка выполнения операции";
                break;

            case "\\getst":
                Student st = studentService.getStudent((String) command.list.get(1));
                answer = (st != null)?
                        String.format("Фамилия: %15s, Имя: %10s, Группа: %8s, Номер зачетной книжки: %10s",
                            st.getLastName(), st.getName(), st.getGroup(), st.getId()):
                        "Дела с данным Id не найдено";
                list.add(st);
                break;

            case "\\login":
                user = userService.authorize((String) command.list.get(1), (String) command.list.get(2));
                answer = (user == null)?
                    "Ошибка входа":
                    "Авторизация прошла успешно";
                list.add(user);
                break;

            case "\\reg":
                user = userService.registrate((String) command.list.get(1), (String) command.list.get(2), false);
                answer = (user == null)?
                     "Ошибка регистрации":
                     "Регистрация прошла успешно";
                list.add(user);
                break;

            case "\\logout":
                break;

            case "\\exit":
                break;

            default:
                answer = "Такой команды не существует";
                break;
        }
        list.set(1, answer);
        XmlCollection xmlCollection = new XmlCollection();
        xmlCollection.list = list;
        return xmlCollection;
    }
}
