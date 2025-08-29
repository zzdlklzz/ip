package noob.command;

import noob.Storage;
import noob.TaskList;
import noob.ui.Ui;

public class ExitCommand extends Command {

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {

    }
}
