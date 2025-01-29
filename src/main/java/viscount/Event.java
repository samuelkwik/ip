package viscount;

public class Event extends Task{
    private static String fromDate;
    private static String toDate;
    public Event(String description, String fromDate, String toDate) {
        super(description);
        this.fromDate = fromDate.trim();
        this.toDate = toDate.trim();
    }
    public Event(String description, Boolean isDone, String fromDate, String toDate) {
        super(description, isDone);
        this.fromDate = fromDate.trim();
        this.toDate = toDate.trim();
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
