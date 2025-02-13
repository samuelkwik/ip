package viscount.command;

import viscount.Storage;
import viscount.task.TaskList;
import viscount.TextUi;
import viscount.ViscountException;

/**
 * Represents a command to delete a task from the task list.
 * The task to be deleted is identified by the index provided as a string
 * during the creation of the command.
 */
public class DeleteCommand extends Command {
    private final String indexStr;

    /**
     * Constructs a DeleteCommand object with a string representing the index
     * of the task to be deleted.
     *
     * @param indexStr The index of the task to be deleted, provided as a string.
     */
    public DeleteCommand(String indexStr) {
        this.indexStr = indexStr + "";
    }

    /**
     * Executes the delete operation by removing a task from the given task list
     * based on the index provided as a string during the command creation.
     * It displays an appropriate success message if the task is successfully
     * deleted or an error message if failed.
     *
     * @param taskList The TaskList containing all current tasks.
     * @param textUi   The TextUi for user interaction and displaying messages.
     * @param storage  The Storage handler for reading and writing tasks to the storage file.
     * @throws ViscountException If there is an error during writing to the storage or
     *                           an invalid index is provided.
     */
    @Override
    public void execute(TaskList taskList, TextUi textUi,
                        Storage storage) throws ViscountException {
        textUi.displayViscountText(execute(taskList, storage));
    }

    public String execute(TaskList taskList, Storage storage) throws ViscountException {
        try {
            int index = Integer.parseInt(indexStr);
            String outcome = taskList.deleteTask(index, storage)
                    .map(task -> "\"" + task.getDescription() + "\" deleted")
                    .orElse("DELETE: No task found with that index");
            return outcome;
        } catch (NumberFormatException e) {
            throw new ViscountException("DELETE: Please enter a numerical index");
        }
    }
}