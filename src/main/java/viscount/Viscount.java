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

    public static void addTask(TaskList taskList, String... taskStrings) {
        if (taskStrings.length == 1) {
            taskList.addTask(new ToDo(taskStrings[0]));
        }else if (taskStrings.length == 2){
            taskList.addTask(new Deadline(taskStrings[0], taskStrings[1]));
        }else if (taskStrings.length == 3){
            taskList.addTask(new Event(taskStrings[0], taskStrings[1], taskStrings[2]));
        }
        displayViscountText("\"" + taskStrings[0] + "\" has been added!");
    }

    public static void toggleTask(String indexStr, TaskList taskList) throws ViscountException{
        try {
            int index = Integer.parseInt(indexStr);
            String outcome = taskList.toggleTask(index)
                    .map(s -> "\"" + s.getDescription() +
                            "\" marked " +
                            (s.isDone() ? "" : "in") + "complete")
                    .orElse("No task found with that index");
            displayViscountText(outcome);
        } catch (NumberFormatException e) {
            displayViscountText("Invalid index. Please try again.");
        }
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
            ParsedCommand parsedCommand;
            try{
                parsedCommand = ParsedCommand.parse(inputString);
            }catch(ViscountException e){
                displayViscountText(e.getMessage());
                continue;
            }
            switch (parsedCommand.getCommand()) {
                case TODO, DEADLINE, EVENT:
                addTask(taskList, parsedCommand.getArguments());
                break;
            case LIST:
                displayTaskList(taskList.getTasks());
                break;
            case TOGGLE:
                toggleTask(parsedCommand.getArguments()[0], taskList);
                break;
            case BYE:
                sayGoodbye();
                isChatting = false;
                break;
            case UNKNOWN:
                displayViscountText("Sorry I didn't understand that. Please try again.");
            }
        }
    }
}
