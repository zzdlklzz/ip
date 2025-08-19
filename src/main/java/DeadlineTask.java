public class DeadlineTask extends Task {

    public DeadlineTask(String text) {
        super(text);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString();
    }
}
