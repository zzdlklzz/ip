package noob.parser;

import noob.task.DeadlineTask;
import noob.task.EventTask;
import noob.exception.NoobException;
import noob.task.TodoTask;
import noob.command.AddCommand;
import noob.command.Command;
import noob.command.DeleteCommand;
import noob.command.ExitCommand;
import noob.command.ListCommand;
import noob.command.MarkCommand;

public class Parser {

    public static Command parse(String input) throws NoobException {
        String trimmedInput = input.trim();

        if (trimmedInput.equalsIgnoreCase("bye")) {
            return new ExitCommand();
        }

        if (trimmedInput.equalsIgnoreCase("list")) {
            return new ListCommand();
        }

        if (trimmedInput.toLowerCase().startsWith("mark")) {
            int taskNum = Parser.parseMarkInput(trimmedInput);
            return new MarkCommand(taskNum, true);
        }

        if (trimmedInput.toLowerCase().startsWith("unmark")) {
            int taskNum = Parser.parseMarkInput(trimmedInput);
            return new MarkCommand(taskNum, false);
        }

        if (trimmedInput.toLowerCase().startsWith("todo")) {
            TodoTask task = Parser.parseTodoInput(trimmedInput);
            return new AddCommand(task);
        }

        if (trimmedInput.toLowerCase().startsWith("deadline")) {
            DeadlineTask task = Parser.parseDeadlineInput(trimmedInput);
            return new AddCommand(task);
        }

        if (trimmedInput.toLowerCase().startsWith("event")) {
            EventTask task = Parser.parseEventInput(trimmedInput);
            return new AddCommand(task);
        }

        if (trimmedInput.toLowerCase().startsWith("delete")) {
            int taskNum = Parser.parseDeleteInput(trimmedInput);
            return new DeleteCommand(taskNum);
        }

        throw new NoobException("Please input a valid command");
    }

    private static int parseDeleteInput(String input) throws NoobException {
        try {
            String[] parsedInput = input.split(" ");

            if (parsedInput.length <= 1) {
                throw new NoobException("Not sure what to delete!");
            }

            return Integer.parseInt(parsedInput[1]);
        } catch (NumberFormatException e) {
            throw new NoobException("Please input a valid task number to be deleted");
        }
    }

    private static int parseMarkInput(String input) throws NoobException {
        try {
            String[] parsedInput = input.split(" ");

            if (parsedInput.length <= 1) {
                throw new NoobException("Not sure what to mark!");
            }

            return Integer.parseInt(input.split(" ")[1]);
        } catch (NumberFormatException e) {
            throw new NoobException("Please input a valid task number to mark as done");
        }
    }

    /**
     * Parses the expected input string for a todo task and returns the task object
     *
     * @param input Todo string input
     * @return TodoTask object
     */
    private static TodoTask parseTodoInput(String input) throws NoobException {
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
    private static DeadlineTask parseDeadlineInput(String input) throws NoobException {
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
     * Parses the expected input string for an event task and returns the task object
     *
     * @param input Event string input
     * @return EventTask object
     */
    private static EventTask parseEventInput(String input) throws NoobException {
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
}
