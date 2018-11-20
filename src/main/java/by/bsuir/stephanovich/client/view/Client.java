package by.bsuir.stephanovich.client.view;

import by.bsuir.stephanovich.client.controller.Controller;
import by.bsuir.stephanovich.consolereader.Reader;


import java.net.*;

import java.io.*;

public class Client {

    private static final int PORT = 8080;

    public static void main(String[] args) throws IOException {
        InetAddress addr = InetAddress.getByName("localhost");
        Controller controller = new Controller();

        System.out.println(addr);

        // Помещаем все в блок try-finally, чтобы
        // быть уверенным, что сокет закроется:
        try (Socket socket = new Socket(addr, PORT)) {
            System.out.println("SOCKET = " + socket);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Вывод автоматически Output выталкивается PrintWriter'ом.
            PrintWriter out = new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream())), false);

            //ввод команды пользователем
            String command = waitCommand();

            //отперавка команды на сервер
            out.println(controller.performCommand(command));
            out.flush();

            //вывод результата
            String result = in.readLine();
            System.out.println(result);
            out.println("\\end");
        } finally {
            System.out.println("closing...");
        }
    }

    private static String waitCommand(){
        System.out.println("\\setname - set Student Name");
        System.out.println("\\setlname - set Student Last Name");
        System.out.println("\\setgroup - set Student Group");
        System.out.println("\\setrole - set role");
        System.out.println("\\addst - add Student Info");
        System.out.println("\\getst - add Student Info");

        System.out.print("Input command: ");
        return Reader.readValue();
    }
}
