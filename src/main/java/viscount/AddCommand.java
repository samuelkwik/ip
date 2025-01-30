package viscount;

public class AddCommand extends Command {
    private final String description;
    private final String[] additionalArgs;

    public AddCommand(String description, String... additionalArgs) {
        this.description = description;
        this.additionalArgs = additionalArgs;
    }

    @Override
    public void execute(TaskList taskList, TextUi textUi, Storage storage) throws ViscountException{
            if (additionalArgs.length == 0) {
                taskList.addTask(new ToDo(description), storage);
            } else if (additionalArgs.length == 1) {
                taskList.addTask(new Deadline(description, additionalArgs[0]), storage);
            } else if (additionalArgs.length == 2) {
                taskList.addTask(new Event(description, additionalArgs[0], additionalArgs[1]), storage);
            }
            textUi.displayViscountText("\"" + description + "\" has been added!");
    }
}