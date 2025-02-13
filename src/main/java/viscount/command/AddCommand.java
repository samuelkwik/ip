package viscount.command;

import viscount.task.TaskList;
import viscount.Storage;
import viscount.TextUi;
import viscount.ViscountException;
import viscount.task.ToDo;
import viscount.task.Deadline;
import viscount.task.Event;

/**
 * Represents a command to add a task to the task list.
 * A task can be of type ToDo, Deadline, or Event based on the provided arguments.
 */
public class AddCommand extends Command {
    private final String description;
    private final String[] additionalArgs;

    /**
     * Constructs an AddCommand object to add a task to the task list.
     * The type task is determined based on the provided description
     * and additional arguments.
     *
     * @param description    The description of the task to be added.
     * @param additionalArgs Additional arguments for the task.
     *                       If no arguments are provided, a ToDo task is created.
     *                       If one argument is provided, a Deadline task is created with the argument as the
     *                       deadline date.
     *                       If two arguments are provided, an Event task is created with the arguments as the start
     *                       and end dates.
     */
    public AddCommand(String description, String... additionalArgs) {
        this.description = description;
        this.additionalArgs = additionalArgs;
    }

    /**
     * Executes the command to add a task to the task list.
     * The type of task is determined based on the provided arguments.
     *
     * @param taskList The TaskList containing all current tasks.
     * @param textUi   The TextUi for user interaction and displaying messages.
     * @param storage  The Storage handler for reading and writing tasks to the storage file.
     * @throws ViscountException If there is an error adding the task due to incorrect formatting
     *                           or storing it in persistent storage.
     */
    @Override
    public void execute(TaskList taskList,
                        TextUi textUi, Storage storage) throws ViscountException {
        textUi.displayViscountText(execute(taskList, storage));
    }

    @Override
    public String execute(TaskList taskList, Storage storage) throws ViscountException {
        if (additionalArgs.length == 0) {
            taskList.addTask(new ToDo(description), storage);
        } else if (additionalArgs.length == 1) {
            taskList.addTask(new Deadline(description, additionalArgs[0]), storage);
        } else if (additionalArgs.length == 2) {
            taskList.addTask(new Event(description, additionalArgs[0],
                    additionalArgs[1]), storage);
        } else {
            assert additionalArgs.length > 2 : "Invalid add command state:"
                    + " Parser failed to parse arguments correctly";
        }
        return "\"" + description + "\" has been added!";
    }
}