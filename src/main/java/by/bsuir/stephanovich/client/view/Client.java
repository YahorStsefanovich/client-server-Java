package by.bsuir.stephanovich.client.view;

import by.bsuir.stephanovich.client.controller.Controller;
import by.bsuir.stephanovich.model.XmlCollection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    private static final int PORT = 8080;

    public static void main(String[] args) throws IOException {
        InetAddress addr = InetAddress.getByName("localhost");
        Controller controller = new Controller();

        System.out.println(addr);

        // Помещаем все в блок try-finally, чтобы
        // быть уверенным, что сокет закроется:
        try (Socket socket = new Socket(addr, PORT)) {
            System.out.println("SOCKET : " + socket);

            ObjectOutputStream  out = new ObjectOutputStream (socket.getOutputStream());
            ObjectInputStream  in = new ObjectInputStream (socket.getInputStream());

            while (true){
                //ввод команды пользователем
                String command = controller.waitCommand();

                //отперавка команды на сервер
                out.reset();
                out.writeObject(controller.performCommand(command));
                out.flush();

                //вывод результата
                XmlCollection result = (XmlCollection) in.readObject();

                System.out.println(controller.getAnswer(result));
            }

        } catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println("closing...");
        }
    }
}
