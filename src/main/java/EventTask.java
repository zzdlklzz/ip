import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class EventTask extends Task {

    private LocalDate from;
    private LocalDate to;

    public EventTask(String text, String from, String to) throws NoobException {
        super(text);
        try {
            this.from = LocalDate.parse(from);
            this.to = LocalDate.parse(to);
        } catch (DateTimeParseException e) {
            throw new NoobException("Invalid date formats, please provide a date of the form yyyy-mm-dd");
        }
    }

    public String getFormattedFrom() {
        return this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public String getFormattedTo() {
        return this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public String getIsoFrom() {
        return this.from.toString();
    }

    public String getIsoTo() {
        return this.to.toString();
    }

    @Override
    public TaskType getType() {
        return TaskType.EVENT;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (from: %s to: %s)", getFormattedFrom(), getFormattedTo());
    }
}
