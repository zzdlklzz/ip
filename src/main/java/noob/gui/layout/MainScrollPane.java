package noob.gui.layout;

import javafx.scene.control.ScrollPane;

public class MainScrollPane extends ScrollPane {
    public void setStyle(int width, int height) {
        this.setPrefSize(width, height);
        this.setHbarPolicy(ScrollBarPolicy.NEVER);
        this.setVbarPolicy(ScrollBarPolicy.ALWAYS);

        this.setVvalue(1.0);
        this.setFitToWidth(true);
    }
}
