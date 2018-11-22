package by.bsuir.stephanovich.server.view;

import by.bsuir.stephanovich.model.XmlCollection;
import by.bsuir.stephanovich.server.controller.Controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

class ServerOneWorker extends Thread {
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private static Controller controller = new Controller();

    ServerOneWorker(Socket s) throws IOException {
        socket = s;
        in = new ObjectInputStream (socket.getInputStream());
        out = new ObjectOutputStream (socket.getOutputStream());

        // Если любой из вышеприведенных вызовов приведет к
        // возникновению исключения, то вызывающий отвечает за
        // закрытие сокета. В противном случае, нить
        // закроет его.
        start(); // вызываем run()
    }

    public void run() {
        try {
            while (true) {
                try {
                    Object command = in.readObject();
                    String result;
                    if (!(result = controller.performCommand((XmlCollection) command)).equals("\\end"))
                    {
                        out.writeObject(result);
                        out.flush();
                    }
                    else
                        break;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            }
            System.out.println("closing...");
        }
        catch (IOException e) {
            System.err.println("IO Exception");
        }
        finally {
            try {
                socket.close();
            }
            catch (IOException e) {
                System.err.println("Socket not closed");
            }
        }
    }
}
