package noob.gui.element;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DialogBox extends HBox {
    private Label text;
    private ImageView imageView;

    private DialogBox(String text, Image image) {
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

    public static DialogBox getUserDialogBox(String s, Image i) {
        return new DialogBox(s, i);
    }

    public static DialogBox getNoobDialogBox(String s, Image i) {
        DialogBox dialogBox = new DialogBox(s, i);
        dialogBox.flip();
        return dialogBox;
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }
}
