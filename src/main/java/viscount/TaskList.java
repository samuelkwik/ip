package viscount;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.IntStream;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Optional<Task> toggleTask(int index) {
        if (index > tasks.size() || index < 1) {
            return Optional.empty();
        }
        tasks.get(index-1).toggleDone();
        return Optional.of(tasks.get(index-1));
    }

    public Optional<Task> getTask(int index) {
        if (index > tasks.size() || index < 1) {
            return Optional.empty();
        }
        return Optional.of(tasks.get(index-1));
    }

    public Optional<Task> deleteTask(int index) {
        Optional<Task> deletedTask = getTask(index);
        return deletedTask.map(task -> {
            tasks.remove(index-1);
            return task;
        });
    }

    public Optional<String> getTasks() {
        if (tasks.isEmpty()) {
            return Optional.empty();
        } else {
            Optional<String> taskListString = IntStream.range(0, tasks.size())
                    .mapToObj(i -> "\t" + (i + 1) + ". " + tasks.get(i).toString())
                    .reduce((s1, s2) -> s1 + "\n" + s2);
            return taskListString;
        }
    }
}
