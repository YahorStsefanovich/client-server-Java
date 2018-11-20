package by.bsuir.stephanovich.consolereader;

import java.util.Scanner;

public class Reader {
    public static Scanner scanner = new Scanner(System.in);

    public static String readValue(){
        return scanner.nextLine();
    }
}
