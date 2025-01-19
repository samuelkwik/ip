public class Viscount {
    private static void promptUser(String text) {
        System.out.println("____________________________________________________________");
        System.out.println(text);
        System.out.println("____________________________________________________________");
    }
    public static void startChat() {
        String logo = " __     ___                           _  \n" +
                " \\ \\   / (_)___  ___ ___  _   _ _ __ | |_ \n" +
                "  \\ \\ / /| / __|/ __/ _ \\| | | | '_ \\| __|\n" +
                "   \\ V / | \\__ \\ (_| (_) | |_| | | | | |_ \n" +
                "    \\_/  |_|___/\\___\\___/ \\__,_|_| |_|\\__|\n";

        System.out.println("Hello from\n" + logo);
    }

    public static void sayHello() {
        promptUser("Hello! I'm Viscount! \n What can I do for you?");
    }

    public static void sayGoodbye() {
        promptUser("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        startChat();
        sayHello();
        sayGoodbye();

    }
}
