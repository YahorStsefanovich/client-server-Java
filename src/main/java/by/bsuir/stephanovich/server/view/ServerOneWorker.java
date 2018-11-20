package by.bsuir.stephanovich.server.view;

import by.bsuir.stephanovich.server.controller.Controller;

import java.io.*;
import java.net.Socket;

class ServerOneWorker extends Thread {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private static Controller controller = new Controller();

    public ServerOneWorker(Socket s) throws IOException {
        socket = s;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        // Включаем автоматическое выталкивание:
        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket
                .getOutputStream())), false);
        // Если любой из вышеприведенных вызовов приведет к
        // возникновению исключения, то вызывающий отвечает за
        // закрытие сокета. В противном случае, нить
        // закроет его.
        start(); // вызываем run()
    }

    public void run() {
        try {
            while (true) {
                //String command = this.in.readLine().replaceAll("&", "\n");
                String command = this.in.readLine();
                String result;
                if ((result = controller.performCommand(command.replaceAll("&", "\n"))) != "\\end")
                {
                    out.println(result);
                    out.flush();
                }
                else
                    break;
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
