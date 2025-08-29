package noob.command;

import noob.storage.Storage;
import noob.task.TaskList;
import noob.ui.Ui;

public abstract class Command {

    public abstract boolean isExit();

    public abstract void execute(Storage s, TaskList t, Ui u);
}
