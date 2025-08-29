package noob.ui;

import noob.NoobException;

public class Ui {
    private String LINE_SPACING = "     ";

    /**
     * Indents bot replies
     *
     * @param text Text to be indented
     */
    private void indentedReply(String text) {
        String[] lineSplit = text.split("\n");
        for (String s : lineSplit) {
            System.out.println(LINE_SPACING + s);
        }
    }

    public void displayInitializationError(NoobException e) {
        System.out.println(e.getMessage() + "\n");
    }

    public void displayError(NoobException e) {
        indentedReply(e.getMessage());
    }

    public void displayMessage(String msg) {
        indentedReply(msg);
    }

    /**
     * Greets the user upon bot start up
     */
    public void greet() {
        System.out.println("Hello! I'm Noob\nWhat can I do for you?");
    }

    /**
     * Exits conversation with a message
     */
    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
