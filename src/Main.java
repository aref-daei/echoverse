import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.printf(
                "%s\n%s\n\n%s ",
                "...::..::: Welcome to EchoVerse :::..::...",
                "    © 2026 - Aref Daei, Shakiba Ahrari    ",
                "Do you want the menu to open? [y/N]"
        );

        boolean isMenu = input.nextLine().equalsIgnoreCase("y");
        if (isMenu) {
            menu(input);
        } else {
            command(input);
        }
    }

    static void menu(Scanner input) {
        System.out.println("Menu");
    }

    static void command(Scanner input) {
        System.out.println("Command");
    }
}
