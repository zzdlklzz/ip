package noob.gui.layout;

import javafx.scene.layout.AnchorPane;

/**
 * Main layout container for the entire GUI
 */
public class MainLayout extends AnchorPane {

    /**
     * Sets the styling for the main container of the GUI
     *
     * @param width Preferred GUI width
     * @param height Preferred GUI height
     */
    public void setStyle(int width, int height) {
        this.setPrefSize(width, height);
    }
}
