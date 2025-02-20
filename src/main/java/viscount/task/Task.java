package viscount.task;

/**
 * Represents a task with a description and a completion status.
 * A task can be marked as done or undone, and its description is immutable.
 * This class serves as the base class for more specialized task types.
 */
public class Task {
    private final String description;
    private Boolean isDone;

    /**
     * Creates a new Task with the specified description.
     * The description is trimmed to remove leading and trailing whitespace,
     * and the task is initialized to an undone state by default.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description.trim();
        this.isDone = false;
    }

    /**
     * Constructs a Task object with the specified description and completion status.
     * The description is automatically trimmed of leading and trailing whitespace.
     *
     * @param description The description of the task.
     * @param isDone      The initial completion status of the task, where true signifies the task is done.
     */
    public Task(String description, Boolean isDone) {
        this.description = description.trim();
        this.isDone = isDone;
    }

    /**
     * Toggles the completion status of the task.
     * If the task is currently marked as done, it will be marked as not done.
     * If the task is currently marked as not done, it will be marked as done.
     */
    public Task toggleDone() {
        this.isDone = !this.isDone;
        return new Task(this.description, this.isDone);
    }

    /**
     * Checks the completion status of the task.
     *
     * @return true if the task is marked as done, false otherwise.
     */
    public Boolean isDone() {
        return this.isDone;
    }

    /**
     * Retrieves the description of the task.
     *
     * @return The description of the task as a string.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Generates a file representation of the task object in a specified format using a separator.
     * The representation includes the completion status of the task and its description, separated by the provided
     * delimiter.
     *
     * @param separator The string to use as the delimiter between the completion status and the task description.
     * @return A string representing the task in file format, formatted as "completion_status separator description".
     */
    public String getFileRepresentation(String separator) {
        return (this.isDone ? "X" : " ") + separator + this.description;
    }

    /**
     * Returns a string representation of the task, including its completion status
     * and description. The completion status is denoted by "[x]" if the task is done,
     * and "[ ]" if it is not done, followed by the task description.
     *
     * @return A string representing the task, with its completion status and description.
     */
    public String toString() {
        return (this.isDone ? "[x]" : "[ ]") + " " + this.description;
    }
}
