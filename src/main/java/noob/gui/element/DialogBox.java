package noob.gui.element;

import javafx.geometry.Pos;
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

        // Styling the dialog box
        this.text.setWrapText(true);
        this.imageView.setFitWidth(100.0);
        this.imageView.setFitHeight(100.0);
        this.imageView.setPreserveRatio(true);
        this.setAlignment(Pos.TOP_RIGHT);

        this.getChildren().addAll(this.text, this.imageView);
    }
}
