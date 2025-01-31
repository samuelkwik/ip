package viscount.task;

import viscount.ViscountException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private final LocalDate fromDate;
    private final LocalDate toDate;

    public Event(String description, String fromDateString,
                 String toDateString) throws DateTimeParseException {
        super(description);
        fromDate = LocalDate.parse(fromDateString.trim());
        toDate = LocalDate.parse(toDateString.trim());
        if (fromDate.isAfter(toDate)) {
            throw new ViscountException("Event Creation Failed: From Date is after To Date");
        }
    }

    public Event(String description, Boolean isDone,
                 String fromDateString, String toDateString) throws DateTimeParseException {
        super(description, isDone);
        fromDate = LocalDate.parse(fromDateString.trim());
        toDate = LocalDate.parse(toDateString.trim());
        if (fromDate.isAfter(toDate)) {
            throw new ViscountException("Event Creation Failed: From Date is after To Date");
        }
    }

    @Override
    public String getFileRepresentation(String seperator) {
        return "E" + seperator + super.getFileRepresentation(seperator)
                + seperator + fromDate + seperator + toDate;
    }

    @Override
    public String toString() {

        return "[E] " + super.toString() + " (from " + fromDate + " to " + toDate + ")";
    }
}
