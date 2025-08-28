import java.util.Scanner;

public class Noob {
    private String LINE_SPACING = "     ";
    private String FILE_PATH = "data/tasks-file.txt";
    private FileOperator fileOperator = new FileOperator();
    private Scanner scanner;
    private TaskList tasks;

    public Noob() {
        // Load in tasks
        try {
            this.tasks = new TaskList(FILE_PATH);
        } catch (NoobException e) {
            System.out.println(e.getMessage() + "\n");
            this.tasks = new TaskList();
        }
    }

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
            } else if (input.toLowerCase().startsWith("mark")) { // Mark task done
                try {
                    String[] parsedInput = input.split(" ");

                    if (parsedInput.length <= 1) {
                        indentedReply("Not sure what to mark!");
                        continue;
                    }

                    int i = Integer.parseInt(input.split(" ")[1]);
                    markTask(i, true);
                    updateTxtFile();
                } catch (NumberFormatException e) {
                    indentedReply("Please input a valid task number to mark as done");
                }
            } else if (input.toLowerCase().startsWith("unmark")) { // Unmark task done
                try {
                    String[] parsedInput = input.split(" ");

                    if (parsedInput.length <= 1) {
                        indentedReply("Not sure what to unmark!");
                        continue;
                    }

                    int i = Integer.parseInt(parsedInput[1]);
                    markTask(i, false);
                    updateTxtFile();
                } catch (NumberFormatException e) {
                    indentedReply("Please input a valid task number to mark as done");
                }
            } else if (input.toLowerCase().startsWith("deadline")) { // Add deadline task
                try {
                    DeadlineTask task = parseDeadlineInput(input);
                    addToList(task);
                    updateTxtFile();
                } catch (NoobException e) {
                    indentedReply(e.getMessage());
                }
            } else if (input.toLowerCase().startsWith("todo")) { // Add todo task
                try {
                    TodoTask task = parseTodoInput(input);
                    addToList(task);
                    updateTxtFile();
                } catch (NoobException e) {
                    indentedReply(e.getMessage());
                }
            } else if (input.toLowerCase().startsWith("event")) { // Add event task
                try {
                    EventTask task = parseEventInput(input);
                    addToList(task);
                    updateTxtFile();
                } catch (NoobException e) {
                    indentedReply(e.getMessage());
                }
            } else if (input.toLowerCase().startsWith("delete")) { // Delete task
                try {
                    String[] parsedInput = input.split(" ");

                    if (parsedInput.length <= 1) {
                        indentedReply("Not sure what to delete!");
                        continue;
                    }

                    int i = Integer.parseInt(parsedInput[1]);
                    deleteTask(i);
                    updateTxtFile();
                } catch (NumberFormatException e) {
                    indentedReply("Please input a valid task number to be deleted");
                }
            } else {
                indentedReply("Please input a valid task type of either deadline, todo or event");
            }
        }
    }

    /**
     * Updates data/tasks-file.txt
     */
    public void updateTxtFile() {
        try {
            fileOperator.writeTasksToFile(FILE_PATH, tasks);
        } catch (NoobException e) {
            indentedReply(e.getMessage());
        }
    }

    /**
     * Deletes a specified task index from the list
     *
     * @param i Task index to be deleted
     */
    private void deleteTask(int i) {
        try {
            Task deletedTask = this.tasks.deleteTask(i);

            String msg = "Noted. I've removed this task:\n";
            String numTasks = String.format("Now you have %d tasks in the list.", this.tasks.getNumTasks());

            indentedReply(msg + "  " + deletedTask + "\n" + numTasks);
        } catch (NoobException e) {
            indentedReply(e.getMessage());
        }
    }

    /**
     * Parses the expected input string for an event task and returns the task object
     *
     * @param input Event string input
     * @return EventTask object
     */
    private EventTask parseEventInput(String input) throws NoobException {
        String[] parsedInput = input.split("event");

        if (parsedInput.length <= 1) {
            throw new NoobException("Task description cannot be empty");
        }

        String[] descAndFromTo = parsedInput[1].split("/from");

        if (descAndFromTo.length <= 1) {
            throw new NoobException("Please use /from to indicate start of event");
        }

        String desc = descAndFromTo[0].trim();

        if (desc.isEmpty()) {
            throw new NoobException("Task description cannot be empty");
        }

        String[] fromTo = descAndFromTo[1].split("/to");

        if (fromTo.length <= 1) {
            throw new NoobException("Please use /to to indicate end of event");
        }

        String from = fromTo[0].trim();
        String to = fromTo[1].trim();

        return new EventTask(desc, from, to);
    }

    /**
     * Parses the expected input string for a todo task and returns the task object
     *
     * @param input Todo string input
     * @return TodoTask object
     */
    private TodoTask parseTodoInput(String input) throws NoobException {
        String[] parsedInput = input.split("todo");

        if (parsedInput.length <= 1) {
            throw new NoobException("Task description cannot be empty");
        }

        String desc = parsedInput[1].trim();

        return new TodoTask(desc);
    }

    /**
     * Parses the expected input string for a deadline task and returns the task object
     *
     * @param input Deadline string input
     * @return DeadlineTask object
     */
    private DeadlineTask parseDeadlineInput(String input) throws NoobException {
        String[] parsedInput = input.split("deadline");

        if (parsedInput.length <= 1) {
            throw new NoobException("Task description cannot be empty");
        }

        String[] descAndDeadline = parsedInput[1].split("/by");

        if (descAndDeadline.length <= 1) {
            throw new NoobException("Please use /by to indicate deadline");
        }

        String desc = descAndDeadline[0].trim();

        if (desc.isEmpty()) {
            throw new NoobException("Task description cannot be empty");
        }

        String deadline = descAndDeadline[1].trim();

        return new DeadlineTask(desc, deadline);
    }

    /**
     * Marks task done or undone
     *
     * @param i 1-base indexed task number to mark as done or undone
     */
    private void markTask(int i, boolean isDone) {
        try {
            Task task = this.tasks.markTask(i, isDone);

            String msg = isDone
                    ? "Nice! I've marked this task as done:\n"
                    : "Ok, I've marked this task as not done yet\n";

            indentedReply(msg + "  " + task);
        } catch (NoobException e) {
            indentedReply(e.getMessage());
        }
    }

    /**
     * Displays a numbered list based on items in memory
     */
    private void displayList() {
        indentedReply(this.tasks.toString());
    }

    /**
     * Adds task to TaskList
     *
     * @param task Task to be added to TaskList
     */
    private void addToList(Task task) {
        int num = this.tasks.addTask(task);
        String s = "Got it. I've added this task:\n";
        String numTasks = String.format("Now you have %d tasks in the list.", num);
        indentedReply(s + "  " + task.toString() + "\n" + numTasks);
    }

    /**
     * Indents bot replies
     *
     * @param text Text to be indented
     */
    private void indentedReply(String text) {
        String[] lineSplit = text.split("\n");
        for (String s : lineSplit) {
            System.out.println(LINE_SPACING + s);
        }
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
