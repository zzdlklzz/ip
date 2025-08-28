import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private File file;
    private String filePath;

    public Storage(String filePath) throws NoobException {
        this.filePath = filePath;
        this.file = new File(filePath);
        if (!file.exists()) {
            this.initialiseStorageFile(file);
        }
    }

    /**
     * Creates file as specified by input
     *
     * @param file File object with defined file path
     * @throws NoobException If IOException occurs on file creation
     */
    private void initialiseStorageFile(File file) throws NoobException {
        file.getParentFile().mkdirs();
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new NoobException("Error creating .txt file storage");
        }
    }

    /**
     * Reads the text contents of the specified file path and converts them to a string
     *
     * @param filePath Relative path of file to be read
     * @return String containing file text
     * @throws FileNotFoundException If file is not found
     */
    private String readFileContents(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner sc = new Scanner(file);
        StringBuilder sb = new StringBuilder();

        while (sc.hasNext()) {
            sb.append(sc.nextLine());
            sb.append("\n");
        }

        return sb.toString().trim();
    }

    /**
     * Writes input text to specified file path
     *
     * @param text     Text to write to file path
     * @throws IOException If an error occurs on file writing
     */
    private void writeToFile(String text) throws IOException {
        FileWriter fileWriter = new FileWriter(this.filePath);
        fileWriter.write(text);
        fileWriter.close();
    }

    /**
     * Takes a list of tasks and writes it into a specified file path
     *
     * @param tasks List of tasks to store
     * @throws NoobException If an error occurs on file writing
     */
    public void writeTasksToFile(TaskList tasks) throws NoobException {
        StringBuilder sb = new StringBuilder();
        int n = tasks.getNumTasks();

        for (int i = 1; i <= n; i++) {
            Task task = tasks.getTask(i);
            String desc = task.getDesc();
            TaskType taskType = task.getType();
            int isDone = task.isDone() ? 1 : 0;

            switch (taskType) {
            case TODO:
                String todoStr = taskType + " | " + isDone + " | " + desc;
                sb.append(todoStr).append("\n");
                break;
            case DEADLINE:
                DeadlineTask deadlineTask = (DeadlineTask) task;
                String deadlineStr = taskType + " | " + isDone + " | " + desc + " | " + deadlineTask.getIsoDeadline();
                sb.append(deadlineStr).append("\n");
                break;
            case EVENT:
                EventTask eventTask = (EventTask) task;
                String eventStr = taskType + " | " + isDone + " | " + desc + " | " + eventTask.getIsoFrom()
                        + " | " + eventTask.getIsoTo();
                sb.append(eventStr).append("\n");
            }
        }

        try {
            writeToFile(sb.toString().trim());
        } catch (IOException e) {
            throw new NoobException("Error writing tasks to file");
        }
    }

    /**
     * Gets a list of tasks based on the txt file specified
     *
     * @return List of tasks as parsed in the storage file
     * @throws NoobException If file is not found or text formats are invalid
     */
    public ArrayList<Task> getListOfTasks() throws NoobException {
        try {
            String text = readFileContents(this.filePath);

            // .txt file was just created and is empty
            if (text.isEmpty()) {
                return new ArrayList<>();
            }

            String[] taskStrings = text.split("\n");
            ArrayList<Task> tasks = new ArrayList<>();

            for (String str : taskStrings) {
                String[] taskDetails = str.split(" \\| ");
                TaskType taskType = TaskType.valueOf(taskDetails[0]);
                boolean isDone = Integer.parseInt(taskDetails[1]) == 1;
                String desc = taskDetails[2];

                switch (taskType) {
                case TODO:
                    TodoTask todoTask = new TodoTask(desc);
                    if (isDone) {
                        todoTask.markDone();
                    }
                    tasks.add(todoTask);
                    break;
                case DEADLINE:
                    String deadline = taskDetails[3];
                    DeadlineTask deadlineTask = new DeadlineTask(desc, deadline);
                    if (isDone) {
                        deadlineTask.markDone();
                    }
                    tasks.add(deadlineTask);
                    break;
                case EVENT:
                    String from = taskDetails[3];
                    String to = taskDetails[4];
                    EventTask eventTask = new EventTask(desc, from, to);
                    if (isDone) {
                        eventTask.markDone();
                    }
                    tasks.add(eventTask);
                    break;
                }
            }

            return tasks;

        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (NumberFormatException e) {
            throw new NoobException("Invalid done status");
        } catch (IllegalArgumentException e) {
            throw new NoobException("Invalid task types in txt file");
        } catch (IndexOutOfBoundsException e) {
            throw new NoobException("Task type and details mismatch");
        }
    }
}
