package noob.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import noob.Noob;
import noob.gui.element.SendButton;
import noob.gui.element.UserInput;
import noob.gui.layout.DialogContainer;
import noob.gui.layout.MainLayout;
import noob.gui.layout.MainScrollPane;

public class Main extends Application {
    private static final int MAIN_LAYOUT_HEIGHT = 600;
    private static final int MAIN_LAYOUT_WIDTH = 400;
    private static final int MAIN_SCROLLPANE_HEIGHT = MAIN_LAYOUT_HEIGHT * 29 / 30;
    private static final int MAIN_SCROLLPANE_WIDTH = MAIN_LAYOUT_WIDTH * 39 / 40;
    private static final int USER_INPUT_WIDTH = MAIN_LAYOUT_WIDTH * 13 / 16;
    private static final int SEND_BUTTON_WIDTH = MAIN_LAYOUT_WIDTH / 8;

    private MainScrollPane mainScrollPane;
    private DialogContainer dialogContainer;
    private UserInput userInput;
    private SendButton sendButton;
    private Scene scene;
    private MainLayout mainLayout;

    private Noob noob = new Noob();

    @Override
    public void start(Stage stage) {

        // Create components
        mainScrollPane = new MainScrollPane();
        dialogContainer = new DialogContainer();
        mainScrollPane.setContent(dialogContainer);

        userInput = new UserInput(dialogContainer, noob);
        sendButton = new SendButton("Send", dialogContainer, userInput, noob);

        mainLayout = new MainLayout();
        mainLayout.getChildren().addAll(mainScrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        // Components styling
        setUpStage(stage);
        setUpComponents();
    }

    private void setUpComponents() {
        mainLayout.setStyle(MAIN_LAYOUT_WIDTH, MAIN_LAYOUT_HEIGHT);
        mainScrollPane.setStyle(MAIN_SCROLLPANE_WIDTH, MAIN_SCROLLPANE_HEIGHT);
        dialogContainer.setStyle(mainScrollPane);
        userInput.setStyle(USER_INPUT_WIDTH);
        sendButton.setStyle(SEND_BUTTON_WIDTH);

        AnchorPane.setTopAnchor(mainScrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }

    private void setUpStage(Stage stage) {
        stage.setTitle("NoobBot");
        stage.setResizable(false);
        stage.setMinHeight(MAIN_LAYOUT_HEIGHT);
        stage.setMinWidth(MAIN_LAYOUT_WIDTH);
    }
}
