package noob.command;

import noob.Storage;
import noob.TaskList;
import noob.ui.Ui;

public abstract class Command {

    public abstract boolean isExit();

    public abstract void execute(Storage s, TaskList t, Ui u);
}
