package viscount.command;

import viscount.Storage;
import viscount.task.TaskList;
import viscount.TextUi;

public class ByeCommand extends Command {
    @Override
    public void execute(TaskList taskList, TextUi textUi, Storage storage) {
        textUi.sayGoodbye();
    }

    @Override
    public boolean isExitCommand() {
        return true;
    }
}