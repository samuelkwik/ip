package viscount.command;

import viscount.Storage;
import viscount.task.TaskList;
import viscount.TextUi;
import viscount.ViscountException;

public abstract class Command {
    public abstract void execute(TaskList taskList, TextUi textUi, Storage storage) throws ViscountException;

    public boolean isExitCommand() {
        return false;
    }
}