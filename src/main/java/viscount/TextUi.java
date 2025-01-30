package viscount;

import java.util.Scanner;

public class TextUi {
    public void printLine() {
        System.out.println("\t_____________________________________________________________");
    }

    public void displayViscountText(String text) {
        printLine();
        System.out.println("\t" + text.replaceAll("\n", "\n\t"));
        printLine();
    }
    public void sayHello() {
        displayViscountText("Hello! I'm Viscount! \nWhat can I do for you?");
    }

    public void sayGoodbye() {
        displayViscountText("Bye. Hope to see you again soon!");
    }

    public void showUserPrompt() {
        System.out.print("You >> ");
    }

    public String promptUserForInput() {
        Scanner scanner = new Scanner(System.in);
        showUserPrompt();
        return scanner.nextLine();
    }

    public void startChat() {
        String logo = "    __     ___                           _  \n" +
                "    \\ \\   / (_)___  ___ ___  _   _ _ __ | |_ \n" +
                "     \\ \\ / /| / __|/ __/ _ \\| | | | '_ \\| __|\n" +
                "      \\ V / | \\__ \\ (_| (_) | |_| | | | | |_ \n" +
                "       \\_/  |_|___/\\___\\___/ \\__,_|_| |_|\\__|";
        printLine();
        System.out.println(logo + " the okayish chatbot");
    }
}
