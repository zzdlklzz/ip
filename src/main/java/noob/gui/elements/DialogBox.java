package noob.gui.elements;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DialogBox extends HBox {
    private Label text;
    private ImageView imageView;

    public DialogBox(String text, Image image) {
        this.text = new Label(text);
        this.imageView = new ImageView(image);
        this.getChildren().addAll(this.text, this.imageView);
    }
}
