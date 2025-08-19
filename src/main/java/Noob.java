import java.util.Scanner;

public class Noob {
    private String LINE_SPACING = "     ";
    private Scanner scanner;
    private Task[] memory = new Task[100];
    private int numItems = 0;

    /**
     * Initialises a new scanner and starts the bot
     */
    public void start() {
        this.scanner = new Scanner(System.in);
        this.greet();

        // Chat loop
        while (true) {
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("bye")) { // Exit
                this.exit();
                break;
            } else if (input.equalsIgnoreCase("list")) { // Display list
                displayList();
            } else if (input.toLowerCase().startsWith("mark ")) { // Mark task done
                try {
                    int i = Integer.parseInt(input.split(" ")[1]);
                    markTask(i, true);
                } catch (NumberFormatException e) {
                    indentedReply("Please input a valid task number to mark as done");
                }
            } else if (input.toLowerCase().startsWith("unmark ")) { // Unmark task done
                try {
                    int i = Integer.parseInt(input.split(" ")[1]);
                    markTask(i, false);
                } catch (NumberFormatException e) {
                    indentedReply("Please input a valid task number to mark as done");
                }
            } else {
                addToList(input);
                indentedReply("added: " + input);
            }
        }
    }

    /**
     * Marks task done or undone
     * @param taskNum 1-base indexed task number to mark as done or undone
     */
    private void markTask(int taskNum, boolean markDone) {
        int i = taskNum - 1;

        if (i < 0 || i >= numItems) {
            indentedReply("No task numbered " + taskNum);
            return;
        }

        if (markDone) {
            memory[i].markDone();
        } else {
            memory[i].unmarkDone();
        }

        String msg = markDone
                ? "Nice! I've marked this task as done:\n"
                : "Ok, I've marked this task as not done yet\n";

        indentedReply(msg + LINE_SPACING + "  " + memory[i]);
    }

    /**
     * Display a numbered list based on items in memory
     */
    private void displayList() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < memory.length; i++) {
            Task task = memory[i];

            if (task == null) {
                break;
            }

            sb.append(i + 1);
            sb.append(".");
            sb.append(task).append("\n").append(LINE_SPACING);
        }

        String reply = sb.isEmpty()
                ? "No tasks to display"
                : sb.toString().trim();

        indentedReply(reply);
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

        memory[numItems++] = new Task(text);
    }

    /**
     * Indent bot replies
     * @param text to be indented
     */
    private void indentedReply(String text) {
        System.out.println(LINE_SPACING + text);
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
