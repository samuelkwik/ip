package viscount.command;

import viscount.Storage;
import viscount.task.TaskList;
import viscount.TextUi;

public class UnknownCommand extends Command {
    @ Override
    public void execute(TaskList taskList, TextUi textUi, Storage storage) {
        textUi.displayViscountText("Sorry I didn't understand that. Please try again.");
    }
}
