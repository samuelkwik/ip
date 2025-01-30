package viscount;

public class UnknownCommand extends Command {
    @ Override
    public void execute(TaskList taskList, TextUi textUi, Storage storage) {
        textUi.displayViscountText("Sorry I didn't understand that. Please try again.");
    }
}
