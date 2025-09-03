package noob.gui.element;

import javafx.scene.control.TextField;
import noob.Noob;
import noob.gui.handler.UserInputHandler;
import noob.gui.layout.DialogContainer;

public class UserInput extends TextField {
    private DialogContainer dialogContainer;
    private Noob noob;

    public UserInput(DialogContainer d, Noob n) {
        this.dialogContainer = d;
        this.noob = n;
        this.setOnAction((event -> {
            UserInputHandler.handleInput(dialogContainer, this, noob);
        }));
    }

    public void setStyle(int width) {
        this.setPrefWidth(width);
    }
}
