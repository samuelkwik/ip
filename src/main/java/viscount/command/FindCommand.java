package viscount.command;

import viscount.Storage;
import viscount.TextUi;
import viscount.ViscountException;
import viscount.task.TaskList;

public class FindCommand extends Command {
    private final String searchTerm;

    public FindCommand(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    @Override
    public void execute(TaskList taskList, TextUi textUi, Storage storage) throws ViscountException {
        String findResults = taskList.getTasksStreamWithIndex()
                .filter(s -> s.contains(searchTerm))
                .reduce((s1, s2) -> s1 + "\n" + s2)
                .orElse("Find: [" + searchTerm + "] : No results found");
        textUi.displayViscountText("Find: [" + searchTerm + "]\n" + findResults);
    }
}
