public class EventTask extends Task {

    private String from;
    private String to;

    public EventTask(String text, String from, String to) {
        super(text);
        this.from = from;
        this.to = to;
    }

    @Override
    public TaskType getType() {
        return TaskType.EVENT;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (from: %s to: %s)", from, to);
    }
}
