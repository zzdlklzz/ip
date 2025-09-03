package noob.gui.handler;

import noob.Noob;
import noob.gui.element.DialogBox;
import noob.gui.element.UserInput;
import noob.gui.image.UserImage;
import noob.gui.layout.DialogContainer;

public class UserInputHandler {
    public static void handleInput(DialogContainer dialogContainer, UserInput userInput, Noob noob) {
        dialogContainer.getChildren().addAll(new DialogBox(userInput.getText(), new UserImage()));
        userInput.clear();
    }
}
