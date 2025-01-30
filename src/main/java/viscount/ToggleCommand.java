package viscount;

public class ToggleCommand extends Command {
    private final String indexStr;

    public ToggleCommand(String indexStr) {
        this.indexStr = indexStr;
    }

    @Override
    public void execute(TaskList taskList, TextUi textUi, Storage storage) throws ViscountException{
        try {
            int index = Integer.parseInt(indexStr);
            String outcome = taskList.toggleTask(index, storage)
                    .map(task -> "\"" + task.getDescription() +
                            "\" marked " +
                            (task.isDone() ? "" : "in") + "complete")
                    .orElse("TOGGLE: No task found with that index");
            textUi.displayViscountText(outcome);
        } catch (NumberFormatException e) {
            textUi.displayViscountText("TOGGLE: Please enter a valid numerical index");
        }
    }
}