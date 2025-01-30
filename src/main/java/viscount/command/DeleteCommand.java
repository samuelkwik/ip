package viscount.command;

import viscount.Storage;
import viscount.task.TaskList;
import viscount.TextUi;
import viscount.ViscountException;

public class DeleteCommand extends Command {
    private final String indexStr;

    public DeleteCommand(String indexStr) {
        this.indexStr = indexStr + "";
    }

    @Override
    public void execute(TaskList taskList, TextUi textUi, Storage storage) throws ViscountException {
        try {
            int index = Integer.parseInt(indexStr);
            String outcome = taskList.deleteTask(index, storage)
                    .map(task -> "\"" + task.getDescription() + "\" deleted")
                    .orElse("DELETE: No task found with that index");
            textUi.displayViscountText(outcome);
        } catch (NumberFormatException e) {
            textUi.displayViscountText("DELETE: Please enter a numerical index");
        }
    }
}