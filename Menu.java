package traffic;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    private static ArrayList<String> options = new ArrayList<>();

    private static Menu instance;

    private static Scanner scanner = new Scanner(System.in);

    static Menu getInstance() {
        if (instance == null) {
            instance = new Menu();
        }
        return instance;
    }

    private Menu() {
        options.add("1. Add road");
        options.add("2. Delete road");
        options.add("3. Open system");
        options.add("0. Quit");
    }

    void display() {
        System.out.println("Menu:");
        options.forEach(System.out::println);
    }

    int getCommand() {
        int result = options.size();
        try {
            result = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {}
        return result;
    }

    int menuLength() {
        return options.size();
    }
}
