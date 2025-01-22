package viscount;

public class Task {
    private final String description;
    private Boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void toggleDone() {
        this.isDone = !this.isDone;
    }

    public Boolean isDone() {
        return this.isDone;
    }

    public String toString() {
        return (this.isDone ? "[x]" : "[ ]") + " " + this.description;
    }
}
