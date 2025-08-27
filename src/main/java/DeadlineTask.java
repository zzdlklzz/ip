import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DeadlineTask extends Task {

    private LocalDate deadline;

    public DeadlineTask(String text, String deadline) throws NoobException {
        super(text);
        try {
            this.deadline = LocalDate.parse(deadline);
        } catch (DateTimeParseException e) {
            throw new NoobException("Invalid deadline format, please provide a date of the form yyyy-mm-dd");
        }
    }

    public String getDeadline() {
        return this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public TaskType getType() {
        return TaskType.DEADLINE;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)", getDeadline());
    }
}
