package viscount.command;

import viscount.Storage;
import viscount.task.Task;
import viscount.task.TaskList;
import viscount.TextUi;

/**
 * Represents a command to display all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the command to display the list of tasks.
     *
     * @param taskList The TaskList containing all current tasks.
     * @param textUi   The TextUi for user interaction and displaying messages.
     * @param storage  The Storage handler for reading and writing tasks to
     *                 the storage file. (not used here)
     */
    @Override
    public void execute(TaskList taskList, TextUi textUi, Storage storage) {
        textUi.displayViscountText(execute(taskList, storage));
    }

    /**
     * Executes the command to display the list of tasks.
     *
     * @param taskList The TaskList containing all current tasks.
     * @param storage  The Storage handler for reading and writing tasks to
     *                 the storage file. (not used here)
     * @return A String of the task list if there are tasks.
     */
    public String execute(TaskList taskList, Storage storage) {
        return taskList.getTasksString()
                .map(s -> "Here is your list of tasks: \n" + s)
                .orElse("You have no tasks");
    }
}