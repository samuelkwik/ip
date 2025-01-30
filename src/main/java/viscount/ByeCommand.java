package viscount;

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