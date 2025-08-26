public class DeadlineTask extends Task {

    private String deadline;

    public DeadlineTask(String text, String deadline) {
        super(text);
        this.deadline = deadline;
    }

    @Override
    public TaskType getType() {
        return TaskType.DEADLINE;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)", deadline);
    }
}
