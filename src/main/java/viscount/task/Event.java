package viscount.task;

import viscount.ViscountException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a specific start and end date.
 * An Event task extends the functionality of the Task class by including
 * additional fields to store the commencement and conclusion dates of an Event.
 */
public class Event extends Task {
    private final LocalDate fromDate;
    private final LocalDate toDate;

    /**
     * Constructs an Evenet object with a description and from and to dates.
     * The Event dates are parsed from the provided string representations.
     *
     * @param description    The description of the task.
     * @param fromDateString The string representation of the date by which the Event starts.
     *                       This date is parsed into a LocalDate object.
     * @param toDateString   The string reprsentation of the date by which the Event ends.
     *                       This date is parsed into a LocalDate object
     * @throws DateTimeParseException If the provided date strings cannot be
     *                                parsed to a valid LocalDate
     *                                or if the end date is before the start date.
     */
    public Event(String description, String fromDateString,
                 String toDateString) throws DateTimeParseException {
        super(description);
        fromDate = LocalDate.parse(fromDateString.trim());
        toDate = LocalDate.parse(toDateString.trim());
        if (fromDate.isAfter(toDate)) {
            throw new ViscountException("Event Creation Failed: From Date is after To Date");
        }
    }

    /**
     * Constructs an Event object with the specified description, completion status, and start/end dates.
     * The Event dates are parsed from the provided string representations.
     *
     * @param description    The description of the task.
     * @param isDone         The initial completion status of the task.
     * @param fromDateString The string representation of the date by which the Event starts.
     *                       This date is parsed into a LocalDate object.
     * @param toDateString   The string reprsentation of the date by which the Event ends.
     *                       This date is parsed into a LocalDate ob ject
     * @throws DateTimeParseException If the provided date strings cannot be parsed to a valid LocalDate
     *                                or if the end date is before the start date.
     */
    public Event(String description, Boolean isDone,
                 String fromDateString, String toDateString) throws DateTimeParseException {
        super(description, isDone);
        fromDate = LocalDate.parse(fromDateString.trim());
        toDate = LocalDate.parse(toDateString.trim());
        if (fromDate.isAfter(toDate)) {
            throw new ViscountException("Event Creation Failed: From Date is after To Date");
        }
    }

    /**
     * Generates a string representation of the Event task in a file-specific format.
     *
     * @param seperator The string to use as a delimiter between the components of the representation.
     * @return A string representing the Event task in the required file representation.
     */
    @Override
    public String getFileRepresentation(String seperator) {
        return "E" + seperator + super.getFileRepresentation(seperator)
                + seperator + fromDate + seperator + toDate;
    }

    /**
     * Returns a string representation of the Event task.
     *
     * @return A string representing the Event task, including its type, completion status,
     * description, and start/end dates.
     */
    @Override
    public String toString() {

        return "[E] " + super.toString() + " (from " + fromDate + " to " + toDate + ")";
    }
}
