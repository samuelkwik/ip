package viscount;

import viscount.command.Command;
import viscount.command.Parser;
import viscount.task.TaskList;

import java.util.Scanner;

/**
 * The Viscount class serves as the entry point to the chatbot application.
 * It is responsible for initializing the task management components,
 * handling user input, and facilitating the interaction between the user and the chatbot.
 */
public class Viscount {
    private static final String DEFAULT_FILEPATH = ".data/saved_tasks.txt";
    private TaskList taskList;
    private final TextUi textUi = new TextUi();
    private final Storage storage;

    /**
     * Initializes a Viscount instance with the specified file path.
     * This constructor sets up the task management and storage components
     * using the provided file path.
     *
     * @param filePath The file path for loading and saving task data.
     */
    public Viscount(String filePath) {
        taskList = new TaskList(filePath);
        storage = new Storage(filePath);
    }

    /**
     * Initializes a default instance of the Viscount chatbot.
     * This constructor sets up the task management and storage components
     * using a predefined default file path.
     * The default file path is used to load and save task data.
     */
    public Viscount() {
        taskList = new TaskList(DEFAULT_FILEPATH);
        storage = new Storage(DEFAULT_FILEPATH);
    }

    /**
     * Starts and runs the main loop for the Viscount chatbot application.
     * This method initializes necessary components, welcomes the user,
     * and continuously processes user inputs until an exit command is given.
     */
    public void run() {
        textUi.startChat();

        Scanner scanner = new Scanner(System.in);
        String inputString;
        boolean isChatting = true;

        try {
            this.taskList.initialize(storage);
        } catch (ViscountException e) {
            textUi.displayViscountText(e.getMessage());
        }
        textUi.sayHello();

        while (isChatting) {
            textUi.showUserPrompt();
            inputString = scanner.nextLine();
            Command parsedCommand;
            try {
                parsedCommand = Parser.parse(inputString);
                parsedCommand.execute(taskList, textUi, storage);
                isChatting = !(parsedCommand.isExitCommand());
            } catch (ViscountException e) {
                textUi.displayViscountText(e.getMessage());
                continue;
            }
        }
    }

    /**
     * The main method serves as the entry point to the Viscount chatbot application.
     * It initializes a default instance of the Viscount chatbot and starts its execution.
     *
     * @param args Command-line arguments passed to the application. These are
     *             not used in the current implementation.
     */
    public static void main(String[] args) {
        Viscount viscount = new Viscount();
        viscount.run();
    }
}
