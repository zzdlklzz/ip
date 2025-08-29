package noob.command;

import noob.storage.Storage;
import noob.task.TaskList;
import noob.ui.Ui;

public class ExitCommand extends Command {

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {

    }

    @Override
    public boolean equals(Object o) {
        return o instanceof ExitCommand;
    }
}
