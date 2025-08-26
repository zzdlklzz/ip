public abstract class Task {
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

    public boolean isDone() {
        return this.isDone;
    }

    public abstract TaskType getType();

    public String getDesc() {
        return this.desc;
    }

    @Override
    public String toString() {
        String s = isDone ? "[X]" : "[ ]";
        return s + " " + desc;
    }
}

