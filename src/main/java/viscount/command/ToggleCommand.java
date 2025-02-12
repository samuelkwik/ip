package viscount.command;

import viscount.Storage;
import viscount.task.TaskList;
import viscount.TextUi;
import viscount.ViscountException;

/**
 * Represents a command to toggle the completion status of a task at a specified index.
 */
public class ToggleCommand extends Command {
    final String indexStr;

    /**
     * Constructs a ToggleCommand with the specified index string.
     * This command is used to toggle the completion status of a task
     * in the task list at the given index.
     *
     * @param indexStr The string representing the index of the task to toggle.
     */
    public ToggleCommand(String indexStr) {
        this.indexStr = indexStr;
    }

    /**
     * Executes the toggle command to change the completion status of a task.
     * Parses the index of the task from the provided input, retrieves the task,
     * toggles its status, and writes the updated task list to storage.
     * Displays the outcome to the user via the text UI.
     *
     * @param taskList The TaskList containing all current tasks.
     * @param textUi   The TextUi for user interaction and displaying messages.
     * @param storage  The Storage handler for reading and writing tasks to the storage file.
     * @throws ViscountException If there is an issue accessing the storage or toggling the task status.
     */
    @Override
    public void execute(TaskList taskList,
                        TextUi textUi, Storage storage) throws ViscountException {
        textUi.displayViscountText(execute(taskList, storage));
    }

    public String execute(TaskList taskList, Storage storage) throws ViscountException {
        try {
            int index = Integer.parseInt(indexStr);
            String outcome = taskList.toggleTask(index, storage)
                    .map(task -> "\"" + task.getDescription()
                            + "\" marked " + (task.isDone() ? "" : "in") + "complete")
                    .orElse("TOGGLE: No task found with that index");
            return outcome;
        } catch (NumberFormatException e) {
            throw new ViscountException("TOGGLE: Please enter a valid numerical index");
        }
    }
}