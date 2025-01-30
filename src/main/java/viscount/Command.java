package viscount;

public abstract class Command {
    public abstract void execute(TaskList taskList, TextUi textUi, Storage storage) throws ViscountException;

    public boolean isExitCommand() {
        return false;
    }
}