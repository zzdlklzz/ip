package noob.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import noob.exception.NoobException;

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

    public String getFormattedDeadline() {
        return this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public String getIsoDeadline() {
        return this.deadline.toString();
    }

    @Override
    public TaskType getType() {
        return TaskType.DEADLINE;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)", getFormattedDeadline());
    }
}
