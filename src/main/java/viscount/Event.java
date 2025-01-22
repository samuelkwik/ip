package viscount;

public class Event extends Task{
    private static String fromDate;
    private static String toDate;
    public Event(String description, String fromDate, String toDate) {
        super(description);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }
    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from " + fromDate + " to " + toDate + ")";
    }
}
