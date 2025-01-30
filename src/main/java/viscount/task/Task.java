package viscount.task;

public class Task {
    private final String description;
    private Boolean isDone;

    public Task(String description) {
        this.description = description.trim();
        this.isDone = false;
    }

    public Task(String description, Boolean isDone) {
        this.description = description.trim();
        this.isDone = isDone;
    }

    public void toggleDone() {
        this.isDone = !this.isDone;
    }

    public Boolean isDone() {
        return this.isDone;
    }

    public String getDescription() {
        return this.description;
    }

    public String getFileRepresentation(String seperator) {
        return (this.isDone? "X" : " ") + seperator + this.description;
    }

    public String toString() {
        return (this.isDone ? "[x]" : "[ ]") + " " + this.description;
    }
}
