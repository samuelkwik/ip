import java.util.Scanner;

public class Viscount {
    enum Command {
        ECHO,
        BYE
    }

    private static void displayViscountText(String text) {
        System.out.println("\t_____________________________________________________________");
        System.out.println("\t" + text.replaceAll("\n", "\n\t"));
        System.out.println("\t_____________________________________________________________");
    }
    public static void startChat() {
        String logo = "    __     ___                           _  \n" +
                "    \\ \\   / (_)___  ___ ___  _   _ _ __ | |_ \n" +
                "     \\ \\ / /| / __|/ __/ _ \\| | | | '_ \\| __|\n" +
                "      \\ V / | \\__ \\ (_| (_) | |_| | | | | |_ \n" +
                "       \\_/  |_|___/\\___\\___/ \\__,_|_| |_|\\__|";

        System.out.println(logo + " the okayish chatbot");
    }

    public static void sayHello() {
        displayViscountText("Hello! I'm Viscount! \nWhat can I do for you?");
    }

    public static void sayGoodbye() {
        displayViscountText("Bye. Hope to see you again soon!");
    }

    public static void showUserPrompt() {
        System.out.print("You >> ");
    }

    public static Command parseInput(String input) {
        if(input.toLowerCase().contains("bye")){
            return Command.BYE;
        }
        return Command.ECHO;
    }

    public static void main(String[] args) {
        startChat();
        sayHello();

        Scanner scanner = new Scanner(System.in);
        String inputString = "";
        Boolean isChatting = true;

        while(isChatting){
            showUserPrompt();
            inputString = scanner.nextLine();
            switch (parseInput(inputString)) {
            case ECHO:
                displayViscountText(inputString);
                break;
            case BYE:
                sayGoodbye();
                isChatting = false;
                break;
            }
        }
    }
}
