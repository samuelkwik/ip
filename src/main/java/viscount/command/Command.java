package viscount.command;

import viscount.Storage;
import viscount.task.TaskList;
import viscount.TextUi;
import viscount.ViscountException;

/**
 * Represents an abstract base class for all commands in the application.
 * A Command encapsulates the behavior to be executed when invoked.
 * Subclasses define specific command behaviors by implementing the `execute` method.
 */
public abstract class Command {
    public abstract void execute(TaskList taskList,
                                 TextUi textUi, Storage storage) throws ViscountException;

    /**
     * Indicates whether this command is an exit command.
     * By default, commands do not signal an exit unless overridden in a subclass.
     *
     * @return false as the default implementation does not represent an exit command.
     */
    public boolean isExitCommand() {
        return false;
    }
}