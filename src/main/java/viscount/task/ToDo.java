package viscount.task;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, Boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String getFileRepresentation(String seperator) {
        return "T" + seperator + super.getFileRepresentation(seperator);
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
