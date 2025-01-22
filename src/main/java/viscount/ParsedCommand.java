package viscount;

public class ParsedCommand {
    private final Command command;
    private final String[] arguments;

    private ParsedCommand(Command command, String... arguments) {
        this.command = command;
        this.arguments = arguments;
    }

    public static ParsedCommand parsedUnknownCommand() {
        return new ParsedCommand(Command.UNKNOWN);
    }

    private static ParsedCommand handleToggle(String inputString) throws ViscountException {
        String[] splitString = inputString.split(" ");
        if (splitString.length != 2) {
            throw new ViscountException("Invalid toggle command, please only state one index to toggle");
        }
        return new ParsedCommand(Command.TOGGLE, splitString[1]);
    }

    private static ParsedCommand handleTodo(String inputString) throws ViscountException {
        if (inputString.length() < 5 || inputString.substring(5).trim().isEmpty()) {
            throw new ViscountException("No description provided");
        }
        return new ParsedCommand(Command.TODO, inputString.substring(5).trim());
    }

    public static ParsedCommand parse(String inputString) throws ViscountException {
        return switch (inputString.toLowerCase().split(" ")[0]) {
            case "todo" -> handleTodo(inputString);
            case "list" -> new ParsedCommand(Command.LIST);
            case "bye" -> new ParsedCommand(Command.BYE);
            case "toggle" -> handleToggle(inputString);
            default -> new ParsedCommand(Command.UNKNOWN);
        };
    }

    protected Command getCommand() {
        return this.command;
    }

    public String[] getArguments() {
        return this.arguments;
    }
}

