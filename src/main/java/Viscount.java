import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Viscount {
    enum Command {
        ADD,
        LIST,
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
        if (input.toLowerCase().equals("bye")) {
            return Command.BYE;
        } else if (input.toLowerCase().equals("list")) {
            return Command.LIST;
        }
        return Command.ADD;
    }

    public static void addTask(String task, ArrayList<String> taskList) {
        taskList.add(task);
        displayViscountText("Added \"" + task + "\" to your list!");
    }

    public static void displayTaskList(ArrayList<String> taskList) {
        if (taskList.isEmpty()) {
            displayViscountText("You have no tasks!");
        } else {
            String taskListString = IntStream.range(0, taskList.size())
                    .mapToObj(i -> "\t" + (i + 1) + ". " + taskList.get(i))
                    .reduce("Here is your list of tasks: ", (s1, s2) -> s1 + "\n" + s2);
            displayViscountText(taskListString);
        }
    }

    public static void main(String[] args) {
        startChat();
        sayHello();

        Scanner scanner = new Scanner(System.in);
        String inputString = "";
        Boolean isChatting = true;
        ArrayList<String> taskList = new ArrayList<>();

        while (isChatting) {
            showUserPrompt();
            inputString = scanner.nextLine();
            switch (parseInput(inputString)) {
                case ADD:
                    addTask(inputString, taskList);
                    break;
                case LIST:
                    displayTaskList(taskList);
                    break;
                case BYE:
                    sayGoodbye();
                    isChatting = false;
                    break;
            }
        }
    }
}
