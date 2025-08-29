package noob.command;

import noob.exception.NoobException;
import noob.storage.Storage;
import noob.task.Task;
import noob.task.TaskList;
import noob.ui.Ui;

public class DeleteCommand extends Command {
    private int taskNum;

    public DeleteCommand(int i) {
        this.taskNum = i;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        try {
            Task deletedTask = tasks.deleteTask(this.taskNum);
            storage.writeTasksToFile(tasks);
            String msg = "Noted. I've removed this task:\n";
            String numTasks = String.format("Now you have %d tasks in the list.", tasks.getNumTasks());

            ui.displayMessage(msg + "  " + deletedTask + "\n" + numTasks);
        } catch (NoobException e) {
            ui.displayError(e);
        }
    }
}
