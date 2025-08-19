import java.util.Scanner;

public class Noob {
    private Scanner scanner;
    private String[] memory = new String[100];
    private int numItems = 0;

    /**
     * Initialises a new scanner and starts the bot
     */
    public void start() {
        this.scanner = new Scanner(System.in);
        this.greet();

        // Chat loop
        while (true) {
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                this.exit();
                break;
            } else if (input.equalsIgnoreCase("list")) {
                displayList();
            } else {
                addToList(input);
                indentedReply("added: " + input);
            }
        }
    }

    private void displayList() {

    }

    /**
     * Saves input text to memory
     * @param text to be added to memory
     */
    private void addToList(String text) {
        if (numItems >= memory.length) {
            String reply = "Memory is full";
            indentedReply(reply);
            return;
        }

        memory[numItems++] = text;
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
