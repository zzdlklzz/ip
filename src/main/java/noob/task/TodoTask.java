package noob.task;

public class TodoTask extends Task {

    public TodoTask(String text) {
        super(text);
    }

    @Override
    public TaskType getType() {
        return TaskType.TODO;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
