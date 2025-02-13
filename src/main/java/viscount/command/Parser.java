package viscount.command;

import viscount.ViscountException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Parser class is responsible for interpreting and parsing user input commands
 * into their respective Command objects. Each command entered by the user is
 * routed to an appropriate handler based on the command type, where it is validated
 * and converted into a specific subclass of the Command class.
 */
public class Parser {

    private static Command handleToggle(String inputString) throws ViscountException {
        String[] splitString = inputString.split(" ");
        if (splitString.length != 2) {
            throw new ViscountException("Invalid toggle command,"
                    + " please only state one index to toggle");
        }
        if (!splitString[1].matches("\\d+")) {
            throw new ViscountException("Invalid toggle command,"
                    + " please enter a numerical index");
        }
        return new ToggleCommand(splitString[1]);
    }

    private static Command handleTodo(String inputString) throws ViscountException {
        if (inputString.length() < 5 || inputString.substring(5).trim().isEmpty()) {
            throw new ViscountException("Invalid todo command, please provide a valid todo"
                    + "\ntodo <description>");
        }
        return new AddCommand(inputString.substring(5).trim());
    }

    private static Command handleDeadline(String inputString) throws ViscountException {
        String pattern = "deadline\\s(.*\\S.*)\\s\\/by\s(.*\\S.*)";
        Pattern deadlinePattern = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = deadlinePattern.matcher(inputString);
        if (!matcher.matches()) {
            throw new ViscountException("Invalid deadline command,"
                    + " please provide a valid deadline\ndeadline <description> /by <date>");
        }
        return new AddCommand(matcher.group(1), matcher.group(2));
    }

    private static Command handleEvent(String inputString) throws ViscountException {
        String pattern = "event\\s(.*\\S.*)\\s/from\\s(.*\\S.*)\\s/to\\s(.*\\S.*)";
        Pattern deadlinePattern = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = deadlinePattern.matcher(inputString);
        if (!matcher.matches()) {
            throw new ViscountException("Invalid event command, please provide a valid event"
                    + "\nevent <description> /from <date> /to <date>");
        }
        return new AddCommand(matcher.group(1), matcher.group(2), matcher.group(3));
    }

    private static Command handleFind(String inputString) throws ViscountException {
        String searchTerm = inputString.substring(5).trim();
        if (inputString.length() < 5 || searchTerm.isEmpty()) {
            throw new ViscountException("Invalid find command, please provide a search term"
                    + "\nfind <search term>");
        }
        return new FindCommand(searchTerm);
    }

    private static Command handleDelete(String inputString) throws ViscountException {
        String[] splitString = inputString.split(" ");
        if (splitString.length != 2) {
            throw new ViscountException("Invalid delete command,"
                    + " please only state one index to delete");
        }
        if (!splitString[1].matches("\\d+")) {
            throw new ViscountException("Invalid delete command, please enter a numerical index");
        }
        return new DeleteCommand(splitString[1]);
    }

    /**
     * Parses a given input string and returns the corresponding Command object.
     * This method determines the type of command based on the first word of the
     * input and delegates the processing to appropriate handlers.
     *
     * @param inputString The input string entered by the user, representing a command.
     * @return A Command object representing the user's intended action.
     * @throws ViscountException If the input string format does not match the intended
     *                           command
     */
    public static Command parse(String inputString) throws ViscountException {
        return switch (inputString.toLowerCase().split(" ")[0]) {
            case "todo" -> handleTodo(inputString);
            case "deadline" -> handleDeadline(inputString);
            case "event" -> handleEvent(inputString);
            case "list" -> new ListCommand();
            case "find" -> handleFind(inputString);
            case "bye" -> new ByeCommand();
            case "toggle" -> handleToggle(inputString);
            case "delete" -> handleDelete(inputString);
            case "undo" -> new UndoCommand();
            default -> new UnknownCommand();
        };
    }
}

