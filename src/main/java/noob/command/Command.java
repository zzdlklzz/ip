package noob.command;

import noob.storage.Storage;
import noob.task.TaskList;
import noob.ui.Ui;

public abstract class Command {

    /**
     * Checks if this is an ExitCommand
     *
     * @return True if this is an ExitCommand
     */
    public abstract boolean isExit();

    /**
     * Executes the corresponding command based on the subclass instance type
     *
     * @param storage Storage object to load or write tasks to
     * @param tasks List of tasks stored
     * @param ui User interaction object to display messages
     */
    public abstract void execute(Storage storage, TaskList tasks, Ui ui);
}
