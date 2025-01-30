package viscount;

import viscount.command.Command;
import viscount.command.Parser;
import viscount.task.TaskList;

import java.util.Scanner;

public class Viscount {
    private static final String DEFAULT_FILEPATH = ".data/saved_tasks.txt";
    private TaskList taskList;
    private final TextUi textUi = new TextUi();
    private final Storage storage;

    public Viscount(String filePath) {
        taskList = new TaskList(filePath);
        storage = new Storage(filePath);
    }

    public Viscount() {
        taskList = new TaskList(DEFAULT_FILEPATH);
        storage = new Storage(DEFAULT_FILEPATH);
    }

    public void run(){
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
            try{
                parsedCommand = Parser.parse(inputString);
                parsedCommand.execute(taskList, textUi, storage);
                isChatting = !(parsedCommand.isExitCommand());
            }catch(ViscountException e){
                textUi.displayViscountText(e.getMessage());
                continue;
            }
        }
    }

    public static void main(String[] args) {
        Viscount viscount = new Viscount();
        viscount.run();
    }
}
