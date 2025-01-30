package viscount;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private final LocalDate byDate;

    public Deadline(String description, String byDateString) throws DateTimeParseException {
        super(description);
        this.byDate = LocalDate.parse(byDateString.trim());
    }

    public Deadline(String description, Boolean isDone, String byDateString) throws DateTimeParseException {
        super(description, isDone);
        this.byDate = LocalDate.parse(byDateString.trim());
    }

    @Override
    public String getFileRepresentation(String seperator) {
        return "D" + seperator + super.getFileRepresentation(seperator) + seperator + byDate;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by " + byDate + ")";
    }
}
