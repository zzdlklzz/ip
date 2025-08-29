package noob.command;

import noob.NoobException;
import noob.Storage;
import noob.Task;
import noob.TaskList;
import noob.ui.Ui;

public class MarkCommand extends Command {
    private boolean isDone;
    private int taskNum;

    public MarkCommand(int taskNum, boolean isDone) {
        this.isDone = isDone;
        this.taskNum = taskNum;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        try {
            Task task = tasks.markTask(this.taskNum, isDone);
            storage.writeTasksToFile(tasks);
            String msg = isDone
                    ? "Nice! I've marked this task as done:\n"
                    : "Ok, I've marked this task as not done yet\n";

            ui.displayMessage(msg + "  " + task);
        } catch (NoobException e) {
            ui.displayError(e);
        }
    }
}
