package viscount.task;

import viscount.Storage;
import viscount.ViscountException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * The TaskList class is responsible for managing a list of tasks in memory,
 * providing functionality to add, remove, toggle, or retrieve tasks, and
 * ensuring synchronization with the provided storage file.
 * <p>
 * This class represents various task types and facilitates operations for
 * task persistence, modification, and retrieval.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private final String filePath;
    private static String SEPERATOR = " | ";

    private Optional<ArrayList<Task>> previousTasks = Optional.empty();

    /**
     * Constructs a TaskList object that initializes an empty task list
     * and sets the file path for storage.
     *
     * @param filePath The file path used for task storage and retrieval.
     */
    public TaskList(String filePath) {
        tasks = new ArrayList<>();
        this.filePath = filePath;
    }

    /**
     * Initializes the task list by reading stored task data from the provided storage and
     * populating the task list. If the storage does not contain a valid saved task file,
     * it handles the scenario by attempting file creation via the storage's handling mechanism.
     *
     * @param storage The storage object used to read tasks data from and handle file-related operations.
     * @throws ViscountException If no valid saved task file is found or if an error occurs while
     *                           processing the stored task data.
     */
    public void initialize(Storage storage) throws ViscountException {
        ArrayList<String> tempList = new ArrayList<>();
        try {
            tempList = storage.readFromStorage();
            addFromList(tempList);
        } catch (FileNotFoundException e) {
            storage.handleFileNotFound();
            throw new ViscountException("No existing saved task file found");
        } catch (ViscountException e) {
            throw new ViscountException("Saved tasks file corrupted: " + e.getMessage());
        }
    }

    /**
     * Adds a task to the task list and updates the storage with
     * the updated task list. If an error occurs during the
     * writing process, the task list is restored to its initial
     * state and an exception is thrown.
     *
     * @param task    The task to be added to the task list.
     * @param storage The storage object used to write the updated task list.
     * @throws ViscountException If there is an issue writing to the storage.
     */
    public void addTask(Task task, Storage storage) throws ViscountException {
        ArrayList<Task> tempList = new ArrayList<>(tasks);
        Optional<ArrayList<Task>> tempPreviousTasks = previousTasks.map(a -> new ArrayList<>(a));
        previousTasks = Optional.of(new ArrayList<>(this.tasks));
        try {
            tasks.add(task);
            storage.writeToStorage(getTasksFileRepresentation().orElse(""));
        } catch (IOException e) {
            previousTasks = tempPreviousTasks;
            tasks = tempList;
            throw new ViscountException("Add task FAILED: file is busy");
        }
        assert tasks.size() == tempList.size() + 1 : "Invalid State: "
                + "Add failed without exception";
    }

    private void addFromList(ArrayList<String> list) throws ViscountException {
        if (list.isEmpty()) {
            return;
        }
        for (String s : list) {
            addTaskFromFileRepresentation(s);
        }
        assert tasks.size() == list.size() : "Invalid state: "
                + " task list size mismatch during loading from storage";
    }

    private void addTaskFromFileRepresentation(String taskString) throws ViscountException {
        String[] taskParts = taskString.split(Pattern.quote(SEPERATOR));

        if (taskParts.length == 3 && taskParts[0].equals("T")) {
            tasks.add(new ToDo(taskParts[2], taskParts[1].contains("X")));
        } else if (taskParts.length == 4 && taskParts[0].equals("D")) {
            tasks.add(new Deadline(taskParts[2], taskParts[1].contains("X"), taskParts[3]));
        } else if (taskParts.length == 5 && taskParts[0].equals("E")) {
            tasks.add(new Event(taskParts[2],
                    taskParts[1].contains("X"), taskParts[3], taskParts[4]));
        } else {
            throw new ViscountException("Invalid task string format: " + taskString);
        }
    }

    /**
     * Toggles the completion status of a task at a specified index in the task list
     * and updates the storage to reflect the changes. If an error occurs while
     * updating the storage, the task's state is reverted to its original state.
     *
     * @param index   The 1-based index of the task to toggle in the task list.
     * @param storage The storage object used to write the updated task list.
     * @return An {@code Optional} containing the modified task if the index is
     * valid and the operation is successful. Otherwise, returns an empty
     * {@code Optional}.
     * @throws ViscountException If the operation fails due to storage write issues.
     */
    public Optional<Task> toggleTask(int index, Storage storage) throws ViscountException {
        if (index > tasks.size() || index < 1) {
            return Optional.empty();
        }
        Optional<ArrayList<Task>> tempPreviousTasks = previousTasks.map(a -> new ArrayList<>(a));
        previousTasks = Optional.of(new ArrayList<>(this.tasks));
        Task toggledTask = tasks.get(index - 1).toggleDone();
        tasks.set(index - 1, toggledTask);
        try {
            storage.writeToStorage(getTasksFileRepresentation().orElse(""));
            return Optional.of(tasks.get(index - 1));
        } catch (IOException e) {
            previousTasks = tempPreviousTasks;
            tasks.get(index - 1).toggleDone();
            throw new ViscountException("Toggle task failed: File is busy");
        }
    }

    /**
     * Retrieves a task from the task list at the specified 1-based index.
     * If the index is out of range, an empty {@code Optional} is returned.
     *
     * @param index The 1-based index of the task to retrieve from the task list.
     * @return An {@code Optional} containing the task if the index is valid.
     * Otherwise, returns an empty {@code Optional}.
     */
    public Optional<Task> getTask(int index) {
        if (index > tasks.size() || index < 1) {
            return Optional.empty();
        }
        return Optional.of(tasks.get(index - 1));
    }

    /**
     * Generates a string representation of the task list with each task indexed and formatted.
     * If the task list is empty, returns an empty {@code Optional}.
     *
     * @return An {@code Optional<String>} containing the formatted and indexed task list.
     * Returns an empty {@code Optional} if the task list is empty.
     */
    public Optional<String> getTasksString() {
        if (tasks.isEmpty()) {
            return Optional.empty();
        } else {
            Optional<String> taskListString = getTasksStreamWithIndex()
                    .reduce((s1, s2) -> s1 + "\n" + s2);
            return taskListString;
        }
    }

    /**
     * Generates a stream of strings representing each task in the task list, including its 1-based index
     * and formatted display information. Each task is prefixed with a tab and its index, followed by
     * its string representation.
     *
     * @return A {@code Stream<String>} where each string represents a task, formatted with an index
     * and its string representation, or an empty stream if the task list is empty.
     */
    public Stream<String> getTasksStreamWithIndex() {
        Stream<String> tasksStream = IntStream.range(0, tasks.size())
                .mapToObj(i -> "  " + (i + 1) + ". " + tasks.get(i).toString());
        return tasksStream;
    }

    /**
     * Generates a string representation of all tasks in the task list suitable for file storage,
     * with each task's file representation separated by newlines. The file representation
     * of each task is generated using a specified separator.
     *
     * @return An {@code Optional<String>} containing the file representation of the task list.
     * If the task list is empty, an empty {@code Optional} is returned.
     */
    public Optional<String> getTasksFileRepresentation() {
        if (tasks.isEmpty()) {
            return Optional.empty();
        } else {
            return tasks.stream()
                    .map(task -> task.getFileRepresentation(SEPERATOR))
                    .reduce((s1, s2) -> s1 + "\n" + s2);
        }
    }

    /**
     * Deletes a task at the specified index from the task list and updates the storage
     * to reflect the changes. If the storage update fails, the task list reverts to
     * its initial state before the delete operation. If the specified index is invalid,
     * an exception is thrown.
     *
     * @param index   The 1-based index of the task to delete from the list.
     * @param storage The storage object used to update the task list after deletion.
     * @return An {@code Optional} containing the deleted task if the operation is successful.
     * Otherwise, returns an empty {@code Optional}.
     * @throws ViscountException If the operation fails due to an invalid index or storage write issue.
     */
    public Optional<Task> deleteTask(int index, Storage storage) throws ViscountException {
        Optional<Task> deletedTask = getTask(index);
        ArrayList<Task> tempList = new ArrayList<>(tasks);

        Optional<ArrayList<Task>> tempPreviousTasks = previousTasks.map(a -> new ArrayList<>(a));
        previousTasks = Optional.of(new ArrayList<>(this.tasks));
        try {
            tasks.remove(index - 1);
            storage.writeToStorage(getTasksFileRepresentation().orElse(""));
        } catch (IOException e) {
            tasks = tempList;
            previousTasks = tempPreviousTasks;
            throw new ViscountException("Delete task FAILED: file is busy");
        } catch (IndexOutOfBoundsException e) {
            tasks = tempList;
            previousTasks = tempPreviousTasks;
            throw new ViscountException("DELETE: Invalid task index: " + index);
        }
        assert tasks.size() == tempList.size() - 1 : "Delete failed without exception";
        return deletedTask;
    }

    public String undoTask(Storage storage) throws ViscountException {
        return previousTasks.map(previous -> {
            ArrayList<Task> temp = new ArrayList<>(tasks);
            tasks = previous;
            try {
                storage.writeToStorage(getTasksFileRepresentation().orElse(""));
            } catch (IOException e) {
                tasks = temp;
                throw new ViscountException("Undo task FAILED: file is busy");
            }
            previousTasks = Optional.empty();
            return "Undo task SUCCESSFUL\nHere are your tasks:\n" + getTasksString()
                    .orElse("No tasks found");
        }).orElseThrow(() -> new ViscountException("Undo task FAILED: no previous tasks"));
    }
}
