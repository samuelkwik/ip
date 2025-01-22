package viscount;

public class ParsedCommand {
    private final Command command;
    private final String[] arguments;

    private ParsedCommand(Command command, String... arguments) {
        this.command = command;
        this.arguments = arguments;
    }

    private static ParsedCommand handleToggle(String inputString) {
        String[] splitString = inputString.split(" ");
        if (splitString.length != 2) {
            return null;
        }
        return new ParsedCommand(Command.TOGGLE, splitString[1]);
    }

    public static ParsedCommand parse(String inputString) {
        return switch (inputString.toLowerCase().split(" ")[0]) {
            case "list" -> new ParsedCommand(Command.LIST);
            case "bye" -> new ParsedCommand(Command.BYE);
            case "toggle" -> handleToggle(inputString);
            default -> new ParsedCommand(Command.ADD);
        };
    }
    protected Command getCommand() {
        return this.command;
    }
    public String[] getArguments() {
        return this.arguments;
    }
}

