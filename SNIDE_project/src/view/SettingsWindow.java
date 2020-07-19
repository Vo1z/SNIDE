package view;

import autilities.WindowType;
import controller.MainController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class SettingsWindow
{
    private MainController mainController;

    //Scene elements
    private Scene settingsScene;
    private Pane root;

    public SettingsWindow(MainController mainController)
    {
        this.mainController = mainController;

        this.root = createRootPane();
        this.settingsScene = new Scene(this.root);
    }

    private Pane createRootPane()
    {
        VBox rootPane = new VBox();

        //Adding to pane
        rootPane.getChildren().add(createBottomButtons());

        return rootPane;
    }

    private Pane createBottomButtons()
    {
        HBox buttonButtonsPane = new HBox();
        Button saveButton = new Button("Save");
        Button backButton = new Button("Back");

        //Configuration
        saveButton.setOnAction(e -> System.out.println("Not implemented")); //TODO

        backButton.setOnAction(
                e ->
                {
                    if (this.mainController.getPreviousWindowType() == WindowType.LAUNCH_WINDOW)
                    {
                        this.mainController.openLaunchWindow();
                    }
                    else if (this.mainController.getPreviousWindowType() == WindowType.EDITOR_WINDOW)
                    {
                        this.mainController.openEditorWindow();
                    }
                }
        );

        //Adding to pane
        buttonButtonsPane.getChildren().add(saveButton);
        buttonButtonsPane.getChildren().add(backButton);

        return buttonButtonsPane;
    }

    public Scene getConfiguredSettingsScene()
    {
        return this.settingsScene;
    }
}