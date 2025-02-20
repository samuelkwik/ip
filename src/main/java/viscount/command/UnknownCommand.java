package viscount.command;

import viscount.Storage;
import viscount.task.TaskList;
import viscount.TextUi;

/**
 * Represents a command that handles invalid or unrecognized user input.
 */
public class UnknownCommand extends Command {
    /**
     * Executes the command for handling invalid or unrecognized user input.
     *
     * @param taskList The TaskList containing all current tasks. (not used here)
     * @param textUi   The TextUi for user interaction and displaying messages.
     * @param storage  The Storage handler for reading and writing tasks
     *                 to the storage file. (not used here)
     */
    @Override
    public void execute(TaskList taskList, TextUi textUi, Storage storage) {
        textUi.displayViscountText(execute(taskList, storage));
    }

    /**
     * Executes the command for handling invalid or unrecognized user input.
     *
     * @param taskList The TaskList containing all current tasks. (not used here)
     * @param storage  The Storage handler for reading and writing tasks
     *                 to the storage file. (not used here)
     * @return A String alerting user that the command entered is not valid.
     */
    public String execute(TaskList taskList, Storage storage) {
        return "Sorry I didn't understand that. Please try again.";
    }
}
