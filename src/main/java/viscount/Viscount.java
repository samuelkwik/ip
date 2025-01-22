package viscount;

import java.util.Optional;
import java.util.Scanner;

public class Viscount {

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

    public static void displayTaskList(Optional<String> taskListString) {
        displayViscountText(taskListString.map(s -> "Here is your list of tasks: \n" + s)
                .orElse("You have no tasks"));
    }

    public static void addTask(String taskString, TaskList taskList) {
        taskList.addTask(new Task(taskString));
        displayViscountText("\"" + taskString + "\" has been added!");
    }

    public static void toggleTask(int index, TaskList taskList) {
        String outcome = taskList.toggleTask(index)
                .map(s -> "\"" + s.getDescription() +
                        "\" marked " +
                        (s.isDone() ? "" : "in") + "complete")
                .orElse("No task found with that index");
        displayViscountText(outcome);
    }

    public static void main(String[] args) {
        startChat();
        sayHello();

        Scanner scanner = new Scanner(System.in);
        String inputString;
        boolean isChatting = true;
        TaskList taskList = new TaskList();

        while (isChatting) {
            showUserPrompt();
            inputString = scanner.nextLine();
            ParsedCommand parsedCommand = ParsedCommand.parse(inputString);
            switch (parsedCommand.getCommand()) {
            case ADD:
                addTask(inputString, taskList);
                break;
            case LIST:
                displayTaskList(taskList.getTasks());
                break;
            case TOGGLE:
                int index = Integer.parseInt(parsedCommand.getArguments()[0]);
                toggleTask(index, taskList);
                break;
            case BYE:
                sayGoodbye();
                isChatting = false;
                break;
            }
        }
    }
}
