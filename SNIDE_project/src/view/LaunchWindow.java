package view;

import autilities.Themes;
import controller.MainController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class LaunchWindow
{
    private MainController mainController;

    private Pane root;
    private Scene launchScene;

    public LaunchWindow(MainController mainController)
    {
        //Assign
        this.mainController = mainController;
        this.root = createRootPane();
        this.launchScene = new Scene(root);

        //Stage properties
        this.mainController.getMainStage().setResizable(false);
        this.mainController.getMainStage().setWidth(400);
        this.mainController.getMainStage().setHeight(600);
    }

    private Pane createRootPane()
    {
        //Assign
        VBox createdRootPane = new VBox();
        Button createButton = new Button("Create");
        Button openButton = new Button("Open");
        Button settingsButton = new Button("Settings");
        Button gitHubButton = new Button("GitHub");

        //Buttons configuration
        createButton.setOnAction(event -> this.mainController.openEditorWindow());

        openButton.setOnAction(e -> System.out.println("Not implemented")); //TODO

        settingsButton.setOnAction(e -> this.mainController.openSettingsWindow());

        gitHubButton.setOnAction(event -> this.mainController.openGitHubInBrowser());

        //Adding to pane
        createdRootPane.getChildren().addAll(createButton, openButton, settingsButton, gitHubButton);

        //Root pane configuration
        createdRootPane.getStylesheets().add(Themes.LAUNCH_WINDOW_THEME_1);

        return createdRootPane;
    }

    //Getters
    public Scene getLaunchScene()
    {
        return this.launchScene;
    }

    public Scene getConfiguredLaunchScene()
    {
        return this.launchScene;
    }
}
