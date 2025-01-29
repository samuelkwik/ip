package viscount;

public class Deadline extends Task{
    private static String date;
    public Deadline(String description, String date) {
        super(description);
        this.date = date.trim();
    }

    public Deadline(String description, Boolean isDone, String date) {
        super(description, isDone);
        this.date = date.trim();
    }

    @Override
    public String getFileRepresentation(String seperator) {
        return "D" + seperator + super.getFileRepresentation(seperator) + seperator + date;
    }
    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by " + date +")";
    }
}
