package noob.command;

import noob.Storage;
import noob.TaskList;
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
}
