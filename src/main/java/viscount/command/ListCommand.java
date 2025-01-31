package viscount.command;

import viscount.Storage;
import viscount.task.TaskList;
import viscount.TextUi;

/**
 * Represents a command to list all tasks in the task list.
 * <p>
 * The {@code ListCommand} retrieves all tasks from the provided {@link TaskList}
 * and displays them using the {@link TextUi}. If no tasks are found, an appropriate
 * message is displayed instead.
 */
public class ListCommand extends Command {

    /**
     * Executes the command to display the list of tasks.
     * <p>
     * This method interacts with the following components:
     * <ul>
     *     <li>{@link TaskList}: Retrieves the list of tasks.</li>
     *     <li>{@link TextUi}: Displays the task list or a message indicating that no tasks are present.</li>
     *     <li>{@link Storage}: Although provided, it is not utilized by this command.</li>
     * </ul>
     *
     * @param taskList The task list containing the tasks to be displayed.
     * @param textUi   The user interface used to display the tasks or messages.
     * @param storage  The storage handler used to manage persistent data (not used in this command).
     */
    @Override
    public void execute(TaskList taskList, TextUi textUi, Storage storage) {
        textUi.displayViscountText(taskList.getTasks().map(s -> "Here is your list of tasks: \n" + s)
                .orElse("You have no tasks"));
    }
}