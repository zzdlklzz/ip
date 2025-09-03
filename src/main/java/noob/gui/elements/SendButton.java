package noob.gui.elements;

import javafx.scene.control.Button;

public class SendButton extends Button {

    public SendButton(String label) {
        super(label);
    }

    public void setStyle(int width) {
        this.setPrefWidth(width);
    }
}
