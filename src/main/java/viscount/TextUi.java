package viscount;

/**
 * The TextUi class provides various utility methods for user interface in the
 * Viscount chatbot application.
 * It is responsible for displaying messages, prompts, and interacting with the user
 * through the console.
 */
public class TextUi {
    /**
     * Prints a horizontal line of underscores to the console to visually separate sections of output.
     */
    public void printLine() {
        System.out.println("\t_____________________________________________________________");
    }

    /**
     * Displays the provided text prefixed by a horizontal line and indented within the console.
     * Each line of the input text replaces its newlines and is indented for better formatting.
     * The displayed text is enclosed between two horizontal lines to distinguish it clearly.
     *
     * @param text The text string to be displayed, where newlines in the input will
     *             result in properly indented multi-line output.
     */
    public void displayViscountText(String text) {
        printLine();
        System.out.println("\t" + text.replaceAll("\n", "\n\t"));
        printLine();
    }

    /**
     * Displays the set greeting message.
     */
    public void sayHello() {
        displayViscountText("Hello! I'm Viscount! \nWhat can I do for you?");
    }

    /**
     * Displays the set goodbye message.
     */
    public void sayGoodbye() {
        displayViscountText("Bye. Hope to see you again soon!");
    }

    /**
     * Prompts the user for input by displaying a "You >> " prompt in the console.
     */
    public void showUserPrompt() {
        System.out.print("You >> ");
    }

    /**
     * Displays the chatbot's ASCII logo and title to the console,
     * providing a welcoming visual introduction upon starting the application.
     */
    public void startChat() {
        String logo = "    __     ___                           _  \n"
                + "    \\ \\   / (_)___  ___ ___  _   _ _ __ | |_ \n"
                + "     \\ \\ / /| / __|/ __/ _ \\| | | | '_ \\| __|\n"
                + "      \\ V / | \\__ \\ (_| (_) | |_| | | | | |_ \n"
                + "       \\_/  |_|___/\\___\\___/ \\__,_|_| |_|\\__|";
        printLine();
        System.out.println(logo + " the okayish chatbot");
    }
}
