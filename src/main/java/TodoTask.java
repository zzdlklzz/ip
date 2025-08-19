public class TodoTask extends Task {

    public TodoTask(String text) {
        super(text);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
