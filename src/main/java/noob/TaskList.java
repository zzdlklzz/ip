package noob;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    private int numTasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
        this.numTasks = 0;
    }

    public TaskList(Storage storage) throws NoobException {
        this.tasks = storage.getListOfTasks();
        this.numTasks = this.tasks.size();
    }

    /**
     * Gets the specified task number from the noob.TaskList
     *
     * @param i 1-based index task number to be retrieved
     * @return noob.Task numbered i
     * @throws NoobException If input integer is out of range of the size of noob.TaskList
     */
    public Task getTask(int i) throws NoobException {
        if (i <= 0 || i > numTasks) {
            throw new NoobException("noob.Task number " + i + "does not exist");
        }
        return this.tasks.get(i - 1);
    }

    /**
     * Adds input task to the noob.TaskList
     *
     * @param task noob.Task to be added to noob.TaskList
     * @return The number of items in the noob.TaskList
     */
    public int addTask(Task task) {
        this.tasks.add(task);
        this.numTasks++;
        return this.numTasks;
    }

    /**
     * Deletes the specified task number from the noob.TaskList
     *
     * @param i 1-based index task number to be deleted
     * @return The deleted noob.Task
     * @throws NoobException If input integer is out of range of the size of noob.TaskList
     */
    public Task deleteTask(int i) throws NoobException {
        if (this.numTasks == 0) {
            throw new NoobException("No tasks to delete");
        }

        if (i <= 0 || i > this.numTasks) {
            throw new NoobException("noob.Task number " + i + " does not exist");
        }

        Task task = this.tasks.remove(i - 1);
        this.numTasks--;
        return task;
    }

    /**
     * Marks the specified task as isDone
     *
     * @param i noob.Task number to be marked
     * @param isDone True if task is to be marked as done, false otherwise
     * @return noob.Task that is marked
     * @throws NoobException If input integer is out of range of the size of noob.TaskList
     */
    public Task markTask(int i, boolean isDone) throws NoobException {
        if (i <= 0 || i > this.numTasks) {
            throw new NoobException("No task numbered " + i);
        }

        Task task = this.tasks.get(i - 1);

        if (isDone) {
            task.markDone();
        } else {
            task.unmarkDone();
        }

        return task;
    }

    /**
     * Gets the number of tasks in the noob.TaskList
     *
     * @return The number of tasks in the noob.TaskList
     */
    public int getNumTasks() {
        return this.numTasks;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < this.tasks.size(); i++) {
            Task task = this.tasks.get(i);

            sb.append(i + 1);
            sb.append(".");
            sb.append(task).append("\n");
        }

        return sb.isEmpty()
                ? "No tasks to display"
                : sb.toString().trim();
    }
}
