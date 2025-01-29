package viscount;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class TaskList {
    private ArrayList<Task> tasks;
    private final String filePath;

    private static String SEPERATOR = " | ";

    public TaskList(String filePath) {
        tasks = new ArrayList<>();
        this.filePath = filePath;
    }

    public void initialize() throws ViscountException {
        ArrayList<String> tempList = new ArrayList<>();
        try {
            tempList = readFileContents();
            addFromList(tempList);
        } catch ( FileNotFoundException e ) {
            File file = new File(filePath);
            file.getParentFile().mkdirs();
            throw new ViscountException("No existing tasks found");
        }
        catch (ViscountException e) {
            throw new ViscountException("Saved tasks file: " + e.getMessage());
        }
    }

    public void addTask(Task task) throws ViscountException {
        try {
            appendToFileContents(getTaskFileRepresentation(task));
            tasks.add(task);
        }
        catch ( IOException e ) {
            throw new ViscountException("Add task FAILED: file is busy");
        }
    }

    private void addTask(String taskString) throws ViscountException{
        String[] taskParts = taskString.split(Pattern.quote(SEPERATOR));

        if (taskParts.length == 3 && taskParts[0].equals("T")) {
            tasks.add(new ToDo(taskParts[2], taskParts[1].contains("X")));
        } else if (taskParts.length == 4 && taskParts[0].equals("D")) {
            tasks.add(new Deadline(taskParts[2], taskParts[1].contains("X"), taskParts[3]));
        } else if (taskParts.length == 5 && taskParts[0].equals("E")) {
            tasks.add(new Event(taskParts[2], taskParts[1].contains("X"), taskParts[3], taskParts[4]));
        } else {
            throw new ViscountException("Invalid task string format: " + taskString);
        }
    }

    public Optional<Task> toggleTask(int index) {

            if (index > tasks.size() || index < 1) {
                return Optional.empty();
            }
            tasks.get(index - 1).toggleDone();
            try {
                writeFileContents(getTasksFileRepresentation().orElse(""));
                return Optional.of(tasks.get(index - 1));
            } catch (IOException e) {
                tasks.get(index - 1).toggleDone();
                throw new ViscountException("Toggle task failed: File is busy");
            }
        }


    public Optional<Task> getTask(int index) {
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
                    .mapToObj(i -> "\t" + (i + 1) + ". " + tasks.get(i).toString())
                    .reduce((s1, s2) -> s1 + "\n" + s2);
            return taskListString;
        }
    }

    public Optional<String> getTaskFileRepresentation(int index) {
        return getTask(index).map(s -> s.getFileRepresentation(SEPERATOR));
    }

    public String getTaskFileRepresentation(Task task) {
        return task.getFileRepresentation(SEPERATOR);
    }

    public Optional<String> getTasksFileRepresentation() {
        if (tasks.isEmpty()) {
            return Optional.empty();
        } else {
            return tasks.stream().map(task -> task.getFileRepresentation(SEPERATOR)).reduce((s1, s2) -> s1 + "\n" + s2);
        }
    }

    public Optional<Task> deleteTask(int index) throws ViscountException {
        Optional<Task> deletedTask = getTask(index);
        ArrayList<Task> tempList = tasks;
        try {
            tasks.remove(index-1);
            writeFileContents(getTasksFileRepresentation().orElse(""));
        } catch (IOException e) {
            tasks = tempList;
            throw new ViscountException("Delete task FAILED: file is busy");
        } catch (IndexOutOfBoundsException e) {
            throw new ViscountException("Invalid task index: " + index);
        }
        return deletedTask;
    }

    private ArrayList<String> readFileContents() throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        ArrayList<String> list = new ArrayList<>();
        while (s.hasNextLine()) {
            list.add(s.nextLine());
        }
        return list;
    }

    private void writeFileContents(String toFile) throws IOException {
        File f = new File(filePath);
        FileWriter fw = new FileWriter(f);
        fw.write(toFile + "\n");
        fw.close();
    }

    private void appendToFileContents(String toFile) throws IOException {
        File f = new File(filePath);
        FileWriter fw = new FileWriter(f, true);
        fw.write(toFile + "\n");
        fw.close();
    }

    private void addFromList(ArrayList<String> list) throws ViscountException{
        for (String s : list) {
            addTask(s);
        }
    }
}
