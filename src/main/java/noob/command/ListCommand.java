package noob.command;

import noob.storage.Storage;
import noob.task.TaskList;
import noob.ui.Ui;

public class ListCommand extends Command {

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        ui.displayMessage(tasks.toString());
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof ListCommand;
    }
}
