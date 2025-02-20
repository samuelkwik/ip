package viscount.command;

import viscount.Storage;
import viscount.ViscountException;
import viscount.task.TaskList;
import viscount.TextUi;

/**
 * Represents a command that undoes the last Add/Toggle/Delete command.
 */
public class UndoCommand extends Command {
    /**
     * Executes a command to undo the last Add/Toggle/Delete command.
     *
     * @param taskList The TaskList containing all current tasks.
     * @param textUi   The TextUi for user interaction and displaying messages.
     * @param storage  The Storage handler for reading and writing tasks
     *                 to the storage file.
     * @throws ViscountException If there is an issue with restoring the old task list.
     */
    @Override
    public void execute(TaskList taskList, TextUi textUi, Storage storage) {
        textUi.displayViscountText(execute(taskList, storage));
    }

    /**
     * Executes a command to undo the last Add/Toggle/Delete command.
     *
     * @param taskList The TaskList containing all current tasks.
     * @param storage  The Storage handler for reading and writing tasks
     *                 to the storage file.
     * @return A String confirming the undo and the updated task list.
     * @throws ViscountException If there is an issue with restoring the old task list.
     */
    public String execute(TaskList taskList, Storage storage) throws ViscountException {
        return taskList.undoTask(storage);
    }
}