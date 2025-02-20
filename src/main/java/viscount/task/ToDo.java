package viscount.task;

/**
 * Represents a general task
 * A ToDo task extends the functionality of the Task class.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo object with a description.
     *
     * @param description The description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructs a ToDo object with a description.
     *
     * @param description The description of the task.
     * @param isDone      The initial completion status of the ToDo
     */
    public ToDo(String description, Boolean isDone) {
        super(description, isDone);
    }

    @Override
    public Task toggleDone() {
        return new ToDo(getDescription(), !isDone());
    }

    /**
     * Generates a string representation of the ToDo task in a file-specific format.
     *
     * @param separator The string to use as a delimiter between
     *                  the components of the representation.
     * @return A string representing the ToDo task in the
     * required file representation.
     */
    @Override
    public String getFileRepresentation(String separator) {
        return "T" + separator + super.getFileRepresentation(separator);
    }


    /**
     * Returns a string representation of the ToDo task.
     *
     * @return A string representing the ToDo task,
     * including its type, completion status and description
     */
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
