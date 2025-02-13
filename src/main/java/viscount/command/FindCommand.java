package viscount.command;

import viscount.Storage;
import viscount.TextUi;
import viscount.ViscountException;
import viscount.task.Task;
import viscount.task.TaskList;

/**
 * Represents a command that facilitates searching for tasks within
 * the task list matching a specific search term provided by the user.
 * This command filters the tasks to find any that contain the specified search term
 * and displays the results, or a message indicating no results were found.
 */
public class FindCommand extends Command {
    private final String searchTerm;

    /**
     * Constructs a FindCommand object to search for tasks in the task list
     * that match the specified search term.
     *
     * @param searchTerm The search term or keyword to filter tasks by.
     *                   Tasks containing this term in their descriptions
     *                   will be included in the search results.
     */
    public FindCommand(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    /**
     * Executes the FindCommand by searching for tasks within the provided task list
     * that match the specified search term. The search is case-sensitive and looks for
     * the presence of the term within the task descriptions. The results are displayed
     * using the provided text UI. If no tasks match the search term, an appropriate
     * "no results found" message is shown.
     *
     * @param taskList The TaskList containing all current tasks.
     * @param textUi   The TextUi for user interaction and displaying messages.
     * @param storage  The Storage handler for reading and writing tasks
     *                 to the storage file. (not used here)
     * @throws ViscountException If any unexpected error occurs during execution.
     */
    @Override
    public void execute(TaskList taskList,
                        TextUi textUi, Storage storage) {
        textUi.displayViscountText(execute(taskList, storage));
    }

    public String execute(TaskList taskList, Storage storage) {
        String findResults =
                taskList.getTasksStreamWithIndex()
                        .filter(s -> s.contains(searchTerm))
                        .reduce((s1, s2) -> s1 + "\n" + s2)
                        .orElse("Find: [" + searchTerm + "] : No results found");
        return "Find: [" + searchTerm + "]\n" + findResults;
    }
}
