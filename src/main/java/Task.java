public class Task {
    private boolean isDone = false;
    private String desc;

    public Task(String desc) {
        this.desc = desc;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        String s = isDone ? "[X]" : "[ ]";
        return s + " " + desc;
    }
}

