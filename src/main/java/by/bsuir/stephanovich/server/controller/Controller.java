package by.bsuir.stephanovich.server.controller;

import by.bsuir.stephanovich.model.Student;
import by.bsuir.stephanovich.model.XmlCollection;
import by.bsuir.stephanovich.server.service.ServiceFactory;
import by.bsuir.stephanovich.server.service.StudentService;

import java.util.List;

public class Controller {

    private StudentService studentService;

    public Controller(){
        studentService = ServiceFactory.getStudentService();
    }

    public String performCommand(XmlCollection command){
        String answer = "";
        switch ((String) command.list.get(0)){
            case "\\setname":
                if (studentService.setStudentName((String) command.list.get(1),(String) command.list.get(2)))
                    answer = "Операция прошла успешно";
                else
                    answer = "Ошибка выполнения операции";
                break;

            case "\\setlname":
                if (studentService.setStudentLastName((String) command.list.get(1), (String) command.list.get(2)))
                    answer = "Операция прошла успешно";
                else
                    answer = "Ошибка выполнения операции";
                break;

            case "\\setgroup":
                if (studentService.setStudentGroup((String) command.list.get(1), (String) command.list.get(2)))
                    answer = "Операция прошла успешно";
                else
                    answer = "Ошибка выполнения операции";
                break;

            case "\\setrole":
                if (studentService.setRole((String) command.list.get(1), (int) command.list.get(2)))
                    answer = "Операция прошла успешно";
                else
                    answer = "Ошибка выполнения операции";
                break;

            case "\\addst":
                studentService.addStudent((Student) command.list.get(1));
                break;

            case "\\getst":
                Student st = studentService.getStudent((String) command.list.get(1));
                if (st != null)
                    answer = String.format("Фамилия: %15s, Имя: %10s, Группа: %8s, Номер зачетной книжки: %10s",
                            st.getLastName(), st.getName(), st.getGroup(), st.getId());
                break;

            case "\\end":
                answer = "\\end";
                break;

            default:
                answer = "Такой команды не существует";
                break;
        }
       // ArrayList<Object> result = new ArrayList<>();
        //result.add(answer);
        return answer;
    }

//    private XmlCollection parseXml(String command){
//        Serializer serializer = new Serializer();
//        return  (XmlCollection)serializer.deserializeFromString(command);
//    }
}
