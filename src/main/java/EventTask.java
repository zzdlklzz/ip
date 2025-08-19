public class EventTask extends Task {

    public EventTask(String text) {
        super(text);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString();
    }
}
