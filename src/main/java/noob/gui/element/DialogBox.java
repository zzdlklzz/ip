package noob.gui.element;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Simple dialog box display
 */
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

    /**
     * Returns the user's dialog box
     *
     * @param s User's input message
     * @param i User's display image
     * @return User's dialog box with image and text
     */
    public static DialogBox getUserDialogBox(String s, Image i) {
        return new DialogBox(s, i);
    }

    /**
     * Returns the bot's dialog box response
     *
     * @param s Bot response message
     * @param i Bot's display image
     * @return Bot's dialog box with image and text, left aligned
     */
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
