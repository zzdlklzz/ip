package noob.gui.element;

import javafx.scene.control.Button;
import noob.Noob;
import noob.gui.handler.UserInputHandler;
import noob.gui.layout.DialogContainer;

public class SendButton extends Button {
    private DialogContainer dialogContainer;
    private UserInput userInput;
    private Noob noob;

    public SendButton(String label, DialogContainer d, UserInput u, Noob n) {
        super(label);
        this.dialogContainer = d;
        this.userInput = u;
        this.noob = n;
        this.setOnMouseClicked((event -> {
            UserInputHandler.handleInput(dialogContainer, userInput, noob);
        }));
    }

    public void setStyle(int width) {
        this.setPrefWidth(width);
    }


}
