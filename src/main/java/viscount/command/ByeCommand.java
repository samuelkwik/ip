package viscount.command;

import viscount.Storage;
import viscount.task.TaskList;
import viscount.TextUi;

/**
 * Represents a command that terminates the program and signals an exit.
 */
public class ByeCommand extends Command {
    /**
     * Executes a command to display the <code>textUi.sayGoodbye()</code> message.
     *
     * @param taskList The TaskList containing all current tasks. (not used here)
     * @param textUi   The TextUi for user interaction and displaying messages.
     * @param storage  The Storage handler for reading and writing tasks
     *                 to the storage file. (not used here)
     */
    @Override
    public void execute(TaskList taskList, TextUi textUi, Storage storage) {
        textUi.sayGoodbye();
    }
    public String execute(TaskList taskList, Storage storage) {
        return "Close the application by clicking the X button";
    }
    /**
     * Indicates this command is an exit command that terminates the program.
     *
     * @return true as this command represents an exit operation.
     */
    @Override
    public boolean isExitCommand() {
        return true;
    }
}