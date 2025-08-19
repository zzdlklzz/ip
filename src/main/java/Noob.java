import java.util.Scanner;

public class Noob {
    Scanner scanner;

    /**
     * Initialises a new scanner and starts the bot
     */
    public void start() {
        this.scanner = new Scanner(System.in);
        this.greet();

        // Chat loop
        while (true) {
            String input = scanner.nextLine();

            if (!input.equalsIgnoreCase("bye")) {
                repeatText(input);
            } else {
                this.exit();
                break;
            }
        }
    }

    /**
     * Indent bot replies
     * @param text to be indented
     */
    private void indentedReply(String text) {
        String spacing = "     ";
        System.out.println(spacing + text);
    }

    /**
     * Echoes the input string
     * @param text input string to be echoed to user
     */
    private void repeatText(String text) {
        indentedReply(text);
    }

    /**
     * Greets the user upon bot start up
     */
    private void greet() {
        System.out.println("Hello! I'm Noob\nWhat can I do for you?");
    }

    /**
     * Exits conversation and closes scanner
     */
    private void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        scanner.close();
    }

    public static void main(String[] args) {
        Noob bot = new Noob();
        bot.start();
    }
}
