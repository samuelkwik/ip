package viscount.command;

import viscount.Storage;
import viscount.task.TaskList;
import viscount.TextUi;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, TextUi textUi, Storage storage) {
        textUi.displayViscountText(taskList.getTasks().map(s -> "Here is your list of tasks: \n" + s)
                .orElse("You have no tasks"));
    }
}