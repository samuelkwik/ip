package viscount;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.IntStream;

public class TaskList {
    private ArrayList<String> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void addTask(String task) {
        tasks.add(task);
    }

    public Optional<String> getTask(int index) {
        if (index > tasks.size() || index < 1) {
            return Optional.empty();
        }
        return Optional.of(tasks.get(index-1));
    }

    public Optional<String> getTasks() {
        if (tasks.isEmpty()) {
            return Optional.empty();
        } else {
            Optional<String> taskListString = IntStream.range(0, tasks.size())
                    .mapToObj(i -> "\t" + (i + 1) + ". " + tasks.get(i))
                    .reduce((s1, s2) -> s1 + "\n" + s2);
            return taskListString;
        }
    }
}
