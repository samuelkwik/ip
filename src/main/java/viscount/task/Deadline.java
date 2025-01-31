package viscount.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a specific deadline.
 * A Deadline task extends the functionality of the Task class by including
 * an additional field to store the date by which the task should be completed.
 */
public class Deadline extends Task {
    private final LocalDate byDate;

    /**
     * Constructs a Deadline object with a description and a deadline date.
     * The deadline date is parsed from the provided string representation.
     *
     * @param description  The description of the task.
     * @param byDateString The string representation of the date by
     *                     which the task needs to be completed.
     *                     This date is parsed into a LocalDate object.
     * @throws DateTimeParseException If the provided date string cannot be parsed
     *                                to a valid LocalDate.
     */
    public Deadline(String description,
                    String byDateString) throws DateTimeParseException {
        super(description);
        this.byDate = LocalDate.parse(byDateString.trim());
    }

    /**
     * Constructs a Deadline object with the specified description,
     * completion status, and deadline date.
     * The deadline date is parsed from the provided string representation.
     *
     * @param description  The description of the task.
     * @param isDone       The initial completion status of the task.
     * @param byDateString The string representation of the date by which
     *                     the task needs to be completed.
     *                     This date is parsed into a LocalDate object.
     * @throws DateTimeParseException If the provided date string cannot be
     *                                parsed to a valid LocalDate.
     */
    public Deadline(String description,
                    Boolean isDone, String byDateString) throws DateTimeParseException {
        super(description, isDone);
        this.byDate = LocalDate.parse(byDateString.trim());
    }

    /**
     * Generates a string representation of the Deadline task in a file-specific format.
     *
     * @param seperator The string to use as a delimiter between
     *                  the components of the representation.
     * @return A string representing the Deadline task in the
     * required file representation.
     */
    @Override
    public String getFileRepresentation(String seperator) {
        return "D" + seperator + super.getFileRepresentation(seperator) + seperator + byDate;
    }

    /**
     * Returns a string representation of the Deadline task.
     *
     * @return A string representing the Deadline task,
     * including its type, completion status, description, and
     * deadline date.
     */
    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by " + byDate + ")";
    }
}
